package biz.buildit.rest.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import java.lang.reflect.Method;
import java.net.URI;
import java.util.List;

import javax.sql.rowset.WebRowSet;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import biz.buildit.exception.InvalidDatesException;
import biz.buildit.main.PlantHireRequest;
import biz.buildit.repository.PlantHireRequestRepository;
import biz.buildit.rest.PlantHireRequestResource;
import biz.buildit.rest.PlantHireRequestResourceList;
import biz.buildit.rest.PurchaseOrderResource;
import biz.buildit.rest.PurchaseOrderResourceList;
import biz.buildit.util.Approval;
import biz.buildit.util.ExtendedLink;
import biz.buildit.util.PlantHireRequestResourceAssembler;
import biz.buildit.util.PlantResourceAssembler;
import biz.buildit.util.ResourceDisassembler;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

@Controller
@RequestMapping(value="/planthirerequests")
public class PlantHireRequestRestController{

	@Autowired PlantHireRequestRepository plantHireRequestRepository;
	Client client;
	WebResource webResource;
	ClientResponse clientResponse;
	
	public PlantHireRequestRestController(){
		client = new Client();
	}

	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> createPHR(@RequestBody PlantHireRequestResource plantHireRequestResource) throws InvalidDatesException{
		PlantHireRequest plantHireRequest = ResourceDisassembler.getPlantHireRequest(plantHireRequestResource);
		plantHireRequest.setApproval(Approval.PENDINGAPPROVAL);
		HttpHeaders headers = new HttpHeaders(); 
		URI location = 
		ServletUriComponentsBuilder.fromCurrentRequestUri(). 
		 pathSegment(Long.toString(plantHireRequest.getId())).build().toUri(); 
		 
		 headers.setLocation(location); 
		 
		 ResponseEntity<Void> response = new 
		ResponseEntity<>(headers, HttpStatus.CREATED); 
		 return response;
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="/{id}")
	public ResponseEntity<Void> updatePHR(@RequestBody PlantHireRequestResource plantHireRequestResource,@PathVariable Long id){
			PlantHireRequest plantHireRequest = ResourceDisassembler.modifyPlantHireRequest(PlantHireRequest.findPlantHireRequest(id),plantHireRequestResource);
			HttpHeaders headers = new HttpHeaders();
			URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().
					pathSegment(Long.toString(id)).build().toUri();
			headers.setLocation(uri);
			ResponseEntity<Void> responseEntity;
			if(plantHireRequest.getApproval() == Approval.REJECTED ||
					plantHireRequest.getApproval() == Approval.PENDINGAPPROVAL){
				plantHireRequest.setApproval(Approval.PENDINGAPPROVAL);
				responseEntity = new ResponseEntity<>(headers,HttpStatus.OK);
			}
			else if(plantHireRequest.getApproval() == Approval.APPROVED && 
					plantHireRequest.getExtensionDate()!=null){
				extendPurchaseOrder(plantHireRequest);
				responseEntity = new ResponseEntity<>(headers,HttpStatus.OK);
			}
			else
				responseEntity = new ResponseEntity<>(headers,HttpStatus.METHOD_NOT_ALLOWED);
		return responseEntity;
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value="/{id}")
	public ResponseEntity<Void> cancelPHR(@PathVariable Long id) throws InvalidDatesException{
		PlantHireRequest plantHireRequest = PlantHireRequest.findPlantHireRequest(id);
		ResponseEntity<Void> responseEntity;
		if(((plantHireRequest.getStartDate().getTime() > System.currentTimeMillis()))
				&& plantHireRequest.getApproval() != Approval.APPROVED){
			plantHireRequest.remove();
			responseEntity = new ResponseEntity<>(HttpStatus.OK);
		}
		else if(plantHireRequest.getApproval() != Approval.APPROVED){
			
			// todo : cancel po email
			responseEntity = new ResponseEntity<>(HttpStatus.ALREADY_REPORTED);
		}
		else{
			responseEntity = new ResponseEntity<>(HttpStatus.METHOD_NOT_ALLOWED);
		}
		return responseEntity;
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/{id}")
	public ResponseEntity<PlantHireRequestResource> getPHR(@PathVariable Long id) throws NoSuchMethodException, SecurityException{
		PlantHireRequest plantHireRequest = PlantHireRequest.findPlantHireRequest(id);
		PlantHireRequestResource plantHireRequestResource = PlantHireRequestResourceAssembler.getPlantHireRequestResource(plantHireRequest);
		
		switch (plantHireRequest.getApproval()) { 
		case PENDINGAPPROVAL: 
			Method _rejectPHR=PlantHireRequestRestController.class.getMethod("rejectPHR",Long.class); 
			Method _approvePHR=PlantHireRequestRestController.class.getMethod("approvePHR",Long.class); 
			 
			String approveLink = linkTo(_approvePHR, plantHireRequest.getId()).toUri().toString(); 
			plantHireRequestResource.add(new ExtendedLink(approveLink, "approvePHR", "POST")); 

			String rejectLink = linkTo(_rejectPHR, plantHireRequest.getId()).toUri().toString(); 
			plantHireRequestResource.add(new ExtendedLink(rejectLink, "rejectPHR", "DELETE")); 
			
			Method _updatePHR=PlantHireRequestRestController.class.getMethod("updatePHR",Long.class);
			
			String updateLink = linkTo(_updatePHR, plantHireRequest.getId()).toUri().toString(); 
			plantHireRequestResource.add(new ExtendedLink(updateLink, "updatePHR", "PUT")); 

			break; 
			
		case REJECTED:
			_updatePHR=PlantHireRequestRestController.class.getMethod("updatePHR",Long.class);
			
			updateLink = linkTo(_updatePHR, plantHireRequest.getId()).toUri().toString(); 
			plantHireRequestResource.add(new ExtendedLink(updateLink, "updatePHR", "PUT")); 
			
			break;
			
		default: 
			break; 
		} 
		
		ResponseEntity<PlantHireRequestResource> responseEntity = new ResponseEntity<>(plantHireRequestResource,HttpStatus.ACCEPTED);
		return responseEntity;
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/{id}/approval") 
	public ResponseEntity<Void> approvePHR(@PathVariable("id") Long id) { 
		PlantHireRequest plantHireRequest = PlantHireRequest.findPlantHireRequest(id); 
		ResponseEntity<Void> response; 

		if (plantHireRequest.getApproval().equals(Approval.PENDINGAPPROVAL)) { 
			plantHireRequest.setApproval(Approval.APPROVED);
			generatePurchaseOrder(plantHireRequest);
			response = new ResponseEntity<>(HttpStatus.OK); 
		} else 
			response = new ResponseEntity<>(HttpStatus.METHOD_NOT_ALLOWED); 

		return response; 
	} 
	
	private void generatePurchaseOrder(PlantHireRequest plantHireRequest){
		PurchaseOrderResource purchaseOrderResource = new PurchaseOrderResource();
		purchaseOrderResource.setPlantId(PlantResourceAssembler.getResource(plantHireRequest.getPlant()));
		purchaseOrderResource.setCompanyName("BuildIt Corp Ltd");
		purchaseOrderResource.setStartDate(plantHireRequest.getStartDate().getTime());
		purchaseOrderResource.setEndDate(plantHireRequest.getEndDate().getTime());
		purchaseOrderResource.setSiteId(plantHireRequest.getSiteId());
		purchaseOrderResource.setPrice(plantHireRequest.getPrice());
		
		
		webResource = client.resource("");
		
		clientResponse = webResource.type(MediaType.APPLICATION_XML)
					.accept(MediaType.APPLICATION_XML)
						.post(ClientResponse.class,
							purchaseOrderResource);
		plantHireRequest.setPurchaseOrderId(clientResponse.getLocation());
	}
	
	private void extendPurchaseOrder(PlantHireRequest plantHireRequest){
		webResource = client.resource(plantHireRequest.getPurchaseOrderId());
		clientResponse = webResource.get(ClientResponse.class);
		PurchaseOrderResource purchaseOrderResource = clientResponse.getEntity(PurchaseOrderResource.class);
		purchaseOrderResource.setEndDate(plantHireRequest.getEndDate().getTime());
		
		webResource.type(MediaType.APPLICATION_XML)
					.post(PurchaseOrderResource.class);
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/orders")
	public PurchaseOrderResourceList getPurchaseOrders(){
		webResource = client.resource("");
		
		clientResponse = webResource.type(MediaType.APPLICATION_XML)
									.accept(MediaType.APPLICATION_XML)
									.get(ClientResponse.class);
		
		return clientResponse.getEntity(PurchaseOrderResourceList.class);
		
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}/approval") 
	public ResponseEntity<Void> rejectPHR(@PathVariable("id") Long id) { 
		PlantHireRequest plantHireRequest = PlantHireRequest.findPlantHireRequest(id); 
		ResponseEntity<Void> response; 

		if (plantHireRequest.getApproval().equals(Approval.PENDINGAPPROVAL)) { 
			plantHireRequest.setApproval(Approval.REJECTED);
			response = new ResponseEntity<>(HttpStatus.OK); 
		} else 
			response = new ResponseEntity<>(HttpStatus.METHOD_NOT_ALLOWED); 

		return response; 
	} 
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<PlantHireRequestResourceList> getPHRs(){
		List<PlantHireRequest> plantHireRequestList = PlantHireRequest.findAllPlantHireRequests();
		PlantHireRequestResourceList plantHireRequestResourceList = PlantHireRequestResourceAssembler.getPlantHireRequestResourceList(plantHireRequestList);
		
		ResponseEntity<PlantHireRequestResourceList> responseEntity = new ResponseEntity<>(plantHireRequestResourceList,HttpStatus.ACCEPTED);
		return responseEntity;
	}
	
	//todo : make this shit work
	@RequestMapping(method=RequestMethod.GET, value="/status/{id}")
	public ResponseEntity<PlantHireRequestResource> getPHRStatus(@PathVariable Long id){
		PlantHireRequest plantHireRequest = PlantHireRequest.findPlantHireRequest(id);
		ResponseEntity<PlantHireRequestResource> responseEntity = new ResponseEntity<PlantHireRequestResource>(PlantHireRequestResourceAssembler.getPlantHireRequestResource(plantHireRequest),HttpStatus.ACCEPTED);
		return responseEntity;
	}
}
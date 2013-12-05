package biz.buildit.rest.controller;

import java.net.URI;

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

import biz.buildit.main.PlantHireRequest;
import biz.buildit.repository.PlantHireRequestRepository;
import biz.buildit.rest.PlantHireRequestResource;
import biz.buildit.util.ResourceAssembler;
import biz.buildit.util.ResourceDisassembler;

@Controller
@RequestMapping(value="/request")
public class RequestController {

	@Autowired PlantHireRequestRepository plantHireRequestRepository;
	
	@RequestMapping(method=RequestMethod.POST, value="/planthirerequest")
	public ResponseEntity<Void> createPlantHireRequest(@RequestBody PlantHireRequestResource plantHireRequestResource){
		PlantHireRequest plantHireRequest = ResourceDisassembler.getPlantHireRequest(plantHireRequestResource);
		
		HttpHeaders headers = new HttpHeaders(); 
		URI location = 
		ServletUriComponentsBuilder.fromCurrentRequestUri(). 
		 pathSegment(Long.toString(plantHireRequest.getId())).build().toUri(); 
		 
		 headers.setLocation(location); 
		 
		 ResponseEntity<Void> response = new 
		ResponseEntity<>(headers, HttpStatus.CREATED); 
		 return response;
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="/planthirerequest/{id}")
	public ResponseEntity<Void> modifyPlantHireRequest(@RequestBody PlantHireRequestResource plantHireRequestResource,@PathVariable Long id){
		PlantHireRequest plantHireRequest = 
			ResourceDisassembler.modifyPlantHireRequest(PlantHireRequest.findPlantHireRequest(id),plantHireRequestResource);
		
		HttpHeaders headers = new HttpHeaders();
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().
				pathSegment(Long.toString(plantHireRequest.
						getId())).build().toUri();
		headers.setLocation(uri);
		ResponseEntity<Void> responseEntity = new ResponseEntity<>(headers,HttpStatus.OK);
		return responseEntity;
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value="/planthirerequest/{id}")
	public ResponseEntity<Void> cancelPlantHireRequest(@PathVariable Long id){
		PlantHireRequest plantHireRequest = PlantHireRequest.findPlantHireRequest(id);
		plantHireRequest.remove();
		
		ResponseEntity<Void> responseEntity = new ResponseEntity<>(HttpStatus.OK);
		return responseEntity;
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/planthirerequest/{id}")
	public ResponseEntity<PlantHireRequestResource> getPlantHireRequest(@PathVariable Long id){
		PlantHireRequest plantHireRequest = PlantHireRequest.findPlantHireRequest(id);
		PlantHireRequestResource plantHireRequestResource = ResourceAssembler.getPlantHireRequestResource(plantHireRequest);
		
		ResponseEntity<PlantHireRequestResource> responseEntity = new ResponseEntity<>(plantHireRequestResource,HttpStatus.ACCEPTED);
		return responseEntity;
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/planthirerequest/getstatus/{id}")
	public ResponseEntity<PlantHireRequestResource> getPlantHireRequestStatus(@PathVariable Long id){
		PlantHireRequest plantHireRequest = PlantHireRequest.findPlantHireRequest(id);
		ResponseEntity<PlantHireRequestResource> responseEntity = new ResponseEntity<PlantHireRequestResource>(ResourceAssembler.getPlantHireRequestResource(plantHireRequest),HttpStatus.ACCEPTED);
		return responseEntity;
	}
}

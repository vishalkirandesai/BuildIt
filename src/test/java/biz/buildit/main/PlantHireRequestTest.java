package biz.buildit.main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import junit.framework.Assert;

import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;

import biz.buildit.rest.PlantHireRequestResource;
import biz.buildit.util.Approval;
import biz.buildit.util.ResourceAssembler;

public class PlantHireRequestTest {
	
	PlantHireRequest plantHireRequest;
	PlantCatalogue plantCatalogue;
	SiteEngineer siteEngineer;
	WorksEngineer worksEngineer;
	Properties properties;
	String url;
	
	public PlantHireRequestTest() throws FileNotFoundException, IOException{
		
		properties = new Properties();
		properties.load(new FileInputStream(new File("system.properties")));
		url = properties.getProperty("app.url");
		
		plantCatalogue = new PlantCatalogue();
		plantCatalogue.setId(1);
		plantCatalogue.setPrice(50);
		plantCatalogue.setName("Yamaha");
		plantCatalogue.setDescription("440x-Full power");
		
		siteEngineer = new SiteEngineer();
		siteEngineer.setId(202);
		siteEngineer.setName("John Carlisle");
		
		worksEngineer = new WorksEngineer();
		worksEngineer.setId(10);
		worksEngineer.setName("David Southam");
		
		plantHireRequest = new PlantHireRequest();
		plantHireRequest.setSiteId(15);
		plantHireRequest.setPrice(50);
		plantHireRequest.setStartDate(new Date(1385596800000L));
		plantHireRequest.setEndDate(new Date(1386115200000L));
		plantHireRequest.setPlantId(plantCatalogue);
		plantHireRequest.setEngId(siteEngineer);
		plantHireRequest.setWEngId(worksEngineer);
		plantHireRequest.setComments("bazinga!");
		plantHireRequest.setApproval(Approval.NEW);
		
Client client = new Client();
		
		WebResource webResource = client.resource(url+"/request/planthirerequest");
		
		ClientResponse clientResponse = 
				webResource.type(MediaType.APPLICATION_XML)
							.accept(MediaType.APPLICATION_XML)
							.post(ClientResponse.class,ResourceAssembler.getPlantHireRequestResource(plantHireRequest));
		
		
	}
	
	@Test
	public void testCreatePlantHireRequest(){
		
		Client client = new Client();
		
		WebResource webResource = client.resource(url+"/request/planthirerequest");
		
		ClientResponse clientResponse = 
				webResource.type(MediaType.APPLICATION_XML)
							.accept(MediaType.APPLICATION_XML)
							.post(ClientResponse.class,ResourceAssembler.getPlantHireRequestResource(plantHireRequest));
		
		Assert.assertEquals(Response.Status.CREATED.getStatusCode(), clientResponse.getStatus());

	}
	
	@Test
	public void testUpdatePlantHireRequest(){
		
Client client = new Client();
		
		WebResource webResource = client.resource(url+"/request/planthirerequest");
		
		ClientResponse clientResponse = 
				webResource.type(MediaType.APPLICATION_XML)
							.accept(MediaType.APPLICATION_XML)
							.post(ClientResponse.class,ResourceAssembler.getPlantHireRequestResource(plantHireRequest));
		
		Assert.assertEquals(Response.Status.CREATED.getStatusCode(), clientResponse.getStatus());
        
		
		
		WebResource webResourc = client.resource(clientResponse.getLocation());  //url+"/request/planthirerequest/"+plantHireRequest.getId());
		
		plantHireRequest.setExtensionDate(new Date(1386288000000L));
		
		ClientResponse clientRespons = 
				webResourc.type(MediaType.APPLICATION_XML)
							.accept(MediaType.APPLICATION_XML)
							.put(ClientResponse.class,ResourceAssembler.getPlantHireRequestResource(plantHireRequest));
		
		Assert.assertTrue(Response.Status.OK.getStatusCode() == clientRespons.getStatus());
	}
	
	@Test
	public void testDeletePlantHireRequest(){
		
		Client client = new Client();
		
		WebResource webResource = client.resource(url+"/request/planthirerequest");
		
		ClientResponse clientResponse = 
				webResource.type(MediaType.APPLICATION_XML)
							.accept(MediaType.APPLICATION_XML)
							.post(ClientResponse.class,ResourceAssembler.getPlantHireRequestResource(plantHireRequest));
		
		Assert.assertTrue(clientResponse.getStatus() == Response.Status.CREATED.getStatusCode());
		
		WebResource webResource1 = client.resource(clientResponse.getLocation());
		System.out.println(clientResponse.getLocation());
		ClientResponse clientResponse2 = 
				webResource1.type(MediaType.APPLICATION_XML)
							.accept(MediaType.APPLICATION_XML)
							.delete(ClientResponse.class);
		
		Assert.assertTrue(clientResponse2.getStatus() == Response.Status.OK.getStatusCode());
	}
	
	@Test
	public void testPlantHireRequestStatus() throws IOException{
		Client client = new Client();
		
		WebResource webResource = client.resource(url+"/request/planthirerequest");
		
		ClientResponse clientResponse = 
				webResource.type(MediaType.APPLICATION_XML)
							.accept(MediaType.APPLICATION_XML)
							.post(ClientResponse.class,ResourceAssembler.getPlantHireRequestResource(plantHireRequest));
		
		String[] temp = clientResponse.getLocation().toString().split("/");
		String id = temp[temp.length-1];
		
		Assert.assertTrue(clientResponse.getStatus() == Response.Status.CREATED.getStatusCode());
		System.out.println(clientResponse.getLocation());
		WebResource webResource2 = client.resource(url+
		"/request/planthirerequest/"+id);
		
		
		
		ClientResponse clientResponse2 = webResource2.type(MediaType.APPLICATION_XML)
													.accept(MediaType.APPLICATION_XML)
													.get(ClientResponse.class);
		
		Assert.assertEquals(Approval.NEW,clientResponse2.getEntity(PlantHireRequestResource.class).getApproval());
	}
}

package biz.buildit.util;

import java.util.ArrayList;
import java.util.List;

import biz.buildit.main.PlantHireRequest;
import biz.buildit.rest.PlantHireRequestResource;
import biz.buildit.rest.PlantHireRequestResourceList;

public class ResourceAssembler {

	public static PlantHireRequestResource getPlantHireRequestResource(PlantHireRequest plantHireRequest){
		PlantHireRequestResource plantHireRequestResource = new PlantHireRequestResource();
		//plantHireRequestResource.setId(plantHireRequest.getId());
		plantHireRequestResource.setEngId(plantHireRequest.getEngId());
		plantHireRequestResource.setSiteId(plantHireRequest.getSiteId());
		plantHireRequestResource.setPlantId(plantHireRequest.getPlantId());
		plantHireRequestResource.setStartDate(plantHireRequest.getStartDate());
		plantHireRequestResource.setEndDate(plantHireRequest.getEndDate());
		plantHireRequestResource.setExtensionDate(plantHireRequest.getExtensionDate());
		plantHireRequestResource.setWEngId(plantHireRequest.getWEngId());
		plantHireRequestResource.setPrice(plantHireRequest.getPrice());
		plantHireRequestResource.setApproval(plantHireRequest.getApproval());
		plantHireRequestResource.setComments(plantHireRequest.getComments());
		return plantHireRequestResource;
	}
	
	public static PlantHireRequestResourceList getPlantHireRequestResourceList(List<PlantHireRequest> plantHireRequests){
		List<PlantHireRequestResource> plantHireRequestResources = new ArrayList<PlantHireRequestResource>();
		for(PlantHireRequest plantHireRequest:plantHireRequests){
			plantHireRequestResources.add(getPlantHireRequestResource(plantHireRequest));
		}
		return new PlantHireRequestResourceList(plantHireRequestResources);
	}
}

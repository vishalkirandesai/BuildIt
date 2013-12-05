package biz.buildit.util;

import biz.buildit.main.PlantHireRequest;
import biz.buildit.rest.PlantHireRequestResource;

public class ResourceDisassembler {

	public static PlantHireRequest getPlantHireRequest(PlantHireRequestResource plantHireRequestResource){
		PlantHireRequest plantHireRequest = new PlantHireRequest();
		plantHireRequest.setId(plantHireRequestResource.getId());
		plantHireRequest.setEngId(plantHireRequestResource.getEngId());
		plantHireRequest.setSiteId(plantHireRequestResource.getSiteId());
		plantHireRequest.setPlantId(plantHireRequestResource.getPlantId());
		plantHireRequest.setStartDate(plantHireRequestResource.getStartDate());
		if(plantHireRequestResource.getEndDate() != null)
			plantHireRequest.setEndDate(plantHireRequestResource.getEndDate());
		plantHireRequest.setExtensionDate(plantHireRequestResource.getExtensionDate());
		plantHireRequest.setWEngId(plantHireRequestResource.getWEngId());
		plantHireRequest.setPrice(plantHireRequestResource.getPrice());
		plantHireRequest.setApproval(plantHireRequest.getApproval());
		plantHireRequest.setComments(plantHireRequestResource.getComments());
		return plantHireRequest;
	}
	
	public static PlantHireRequest modifyPlantHireRequest(PlantHireRequest plantHireRequest,PlantHireRequestResource plantHireRequestResource){
		//plantHireRequest.setId(plantHireRequestResource.getId());
		plantHireRequest.setEngId(plantHireRequestResource.getEngId());
		plantHireRequest.setSiteId(plantHireRequestResource.getSiteId());
		plantHireRequest.setPlantId(plantHireRequestResource.getPlantId());
		plantHireRequest.setStartDate(plantHireRequestResource.getStartDate());
		plantHireRequest.setEndDate(plantHireRequestResource.getExtensionDate());
		plantHireRequest.setExtensionDate(null);
		plantHireRequest.setWEngId(plantHireRequestResource.getWEngId());
		plantHireRequest.setPrice(plantHireRequestResource.getPrice());
		plantHireRequest.setApproval(plantHireRequestResource.getApproval());
		plantHireRequest.setComments(plantHireRequestResource.getComments());
		return plantHireRequest;
	}
}

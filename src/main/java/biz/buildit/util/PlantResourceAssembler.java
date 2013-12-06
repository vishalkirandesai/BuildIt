package biz.buildit.util;

import biz.buildit.main.Plant;
import biz.buildit.rest.PlantResource;

public class PlantResourceAssembler {

	public static PlantResource getResource(Plant plant){
		PlantResource plantResource = new PlantResource();
		plantResource.setId(plant.getId());
		plantResource.setPrice(plant.getPrice());
		plantResource.setDescription(plant.getDescription());
		if(plant.getStartDate() != null)
			plantResource.setStartDate(plant.getStartDate().getTime());
		if(plant.getEndDate() != null)
		plantResource.setEndDate(plant.getEndDate().getTime());
		return plantResource;
	}
}

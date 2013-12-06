package biz.buildit.rest;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.roo.addon.javabean.RooJavaBean;

import biz.buildit.main.Plant;

@RooJavaBean
@XmlRootElement(name="plants")
public class PlantResourceList {

	List<Plant> plantList;
	
	public PlantResourceList(){
		plantList = new ArrayList<Plant>();
	}
	
	public PlantResourceList(List<Plant> plantList){
		this.plantList = plantList;
	}
}

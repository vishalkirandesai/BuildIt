// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package biz.buildit.main;

import biz.buildit.main.PlantCatalogue;
import biz.buildit.main.PlantHireRequest;
import biz.buildit.main.SiteEngineer;
import biz.buildit.main.WorksEngineer;
import java.util.Date;

privileged aspect PlantHireRequest_Roo_JavaBean {
    
    public int PlantHireRequest.getId() {
        return this.id;
    }
    
    public void PlantHireRequest.setId(int id) {
        this.id = id;
    }
    
    public int PlantHireRequest.getSiteId() {
        return this.siteId;
    }
    
    public void PlantHireRequest.setSiteId(int siteId) {
        this.siteId = siteId;
    }
    
    public SiteEngineer PlantHireRequest.getEngId() {
        return this.engId;
    }
    
    public void PlantHireRequest.setEngId(SiteEngineer engId) {
        this.engId = engId;
    }
    
    public Date PlantHireRequest.getStartDate() {
        return this.startDate;
    }
    
    public void PlantHireRequest.setStartDate(Date startDate) {
        this.startDate = startDate;
    }
    
    public Date PlantHireRequest.getEndDate() {
        return this.endDate;
    }
    
    public void PlantHireRequest.setEndDate(Date endDate) {
        this.endDate = endDate;
    }
    
    public Date PlantHireRequest.getExtensionDate() {
        return this.extensionDate;
    }
    
    public void PlantHireRequest.setExtensionDate(Date extensionDate) {
        this.extensionDate = extensionDate;
    }
    
    public int PlantHireRequest.getPrice() {
        return this.price;
    }
    
    public void PlantHireRequest.setPrice(int price) {
        this.price = price;
    }
    
    public String PlantHireRequest.getComments() {
        return this.comments;
    }
    
    public void PlantHireRequest.setComments(String comments) {
        this.comments = comments;
    }
    
    public String PlantHireRequest.getApproval() {
        return this.approval;
    }
    
    public void PlantHireRequest.setApproval(String approval) {
        this.approval = approval;
    }
    
    public PlantCatalogue PlantHireRequest.getPlantId() {
        return this.plantId;
    }
    
    public void PlantHireRequest.setPlantId(PlantCatalogue plantId) {
        this.plantId = plantId;
    }
    
    public WorksEngineer PlantHireRequest.getWEngId() {
        return this.wEngId;
    }
    
    public void PlantHireRequest.setWEngId(WorksEngineer wEngId) {
        this.wEngId = wEngId;
    }
    
}

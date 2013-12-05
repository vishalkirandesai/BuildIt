package biz.buildit.rest;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.roo.addon.javabean.RooJavaBean;

import biz.buildit.main.PlantCatalogue;
import biz.buildit.main.SiteEngineer;
import biz.buildit.main.WorksEngineer;
import biz.buildit.util.Approval;

@RooJavaBean
@XmlRootElement(name="planthirerequest")
public class PlantHireRequestResource {

    private long id;

    private int siteId;

    private SiteEngineer engId;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date startDate;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date endDate;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date extensionDate;

    private int price;

    private String comments;

    private PlantCatalogue plantId;

    private WorksEngineer wEngId;
    
    private Approval approval;
    
}

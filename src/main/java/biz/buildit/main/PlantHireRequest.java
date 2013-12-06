package biz.buildit.main;
import java.net.URI;
import java.util.Date;

import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

import biz.buildit.util.Approval;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class PlantHireRequest {
////

    /**
     */
    private int siteId;

    /**
     */
    @ManyToOne
    private SiteEngineer siteEng;

    /**
     */
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date startDate;

    /**
     */
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date endDate;

    /**
     */
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date extensionDate;

    /**
     */
    private String comments;

    /**
     */
    @ManyToOne
    private Plant plant;

    /**
     */
    @ManyToOne
    private WorksEngineer wEng;
    
    private Approval approval;
    
    private URI purchaseOrderId;
    
    public float getPrice(){
    	return (getStartDate().getTime()-getEndDate().getTime())
    			*getPlant().getPrice()/86400000.0f;
    }
}

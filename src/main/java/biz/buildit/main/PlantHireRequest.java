package biz.buildit.main;
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
    private SiteEngineer engId;

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
    private int price;

    /**
     */
    private String comments;

    /**
     */
    @ManyToOne
    private PlantCatalogue plantId;

    /**
     */
    @ManyToOne
    private WorksEngineer wEngId;
    
    private Approval approval;
    
}

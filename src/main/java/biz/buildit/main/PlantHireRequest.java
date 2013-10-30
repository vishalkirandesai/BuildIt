package biz.buildit.main;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;
import javax.persistence.ManyToOne;
import java.util.Date;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.format.annotation.DateTimeFormat;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class PlantHireRequest {

    /**
     */
    private int id;

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
    private String approval;

    /**
     */
    @ManyToOne
    private PlantCatalogue plantId;

    /**
     */
    @ManyToOne
    private WorksEngineer wEngId;
}

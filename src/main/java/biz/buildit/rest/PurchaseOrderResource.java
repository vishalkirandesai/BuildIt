package biz.buildit.rest;

import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.roo.addon.javabean.RooJavaBean;

@RooJavaBean
@XmlRootElement(name="purchaseorder")
public class PurchaseOrderResource {

    private int siteId;

    private String companyName;

    private Long startDate;
    private Long endDate;

    private float price;

    private PlantResource plantId;
    
}

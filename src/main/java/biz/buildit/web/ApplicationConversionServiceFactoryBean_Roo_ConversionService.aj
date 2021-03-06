// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package biz.buildit.web;

import biz.buildit.main.Invoice;
import biz.buildit.main.PlantCatalogue;
import biz.buildit.main.PlantHireRequest;
import biz.buildit.main.SiteEngineer;
import biz.buildit.main.WorksEngineer;
import biz.buildit.web.ApplicationConversionServiceFactoryBean;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistry;

privileged aspect ApplicationConversionServiceFactoryBean_Roo_ConversionService {
    
    declare @type: ApplicationConversionServiceFactoryBean: @Configurable;
    
    public Converter<Invoice, String> ApplicationConversionServiceFactoryBean.getInvoiceToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<biz.buildit.main.Invoice, java.lang.String>() {
            public String convert(Invoice invoice) {
                return new StringBuilder().append(invoice.getId()).append(' ').append(invoice.getDueDate()).append(' ').append(invoice.getApproval()).toString();
            }
        };
    }
    
    public Converter<Long, Invoice> ApplicationConversionServiceFactoryBean.getIdToInvoiceConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Long, biz.buildit.main.Invoice>() {
            public biz.buildit.main.Invoice convert(java.lang.Long id) {
                return Invoice.findInvoice(id);
            }
        };
    }
    
    public Converter<String, Invoice> ApplicationConversionServiceFactoryBean.getStringToInvoiceConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, biz.buildit.main.Invoice>() {
            public biz.buildit.main.Invoice convert(String id) {
                return getObject().convert(getObject().convert(id, Long.class), Invoice.class);
            }
        };
    }
    
    public Converter<PlantCatalogue, String> ApplicationConversionServiceFactoryBean.getPlantCatalogueToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<biz.buildit.main.PlantCatalogue, java.lang.String>() {
            public String convert(PlantCatalogue plantCatalogue) {
                return new StringBuilder().append(plantCatalogue.getId()).append(' ').append(plantCatalogue.getPrice()).append(' ').append(plantCatalogue.getDescription()).append(' ').append(plantCatalogue.getStartDate()).toString();
            }
        };
    }
    
    public Converter<Long, PlantCatalogue> ApplicationConversionServiceFactoryBean.getIdToPlantCatalogueConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Long, biz.buildit.main.PlantCatalogue>() {
            public biz.buildit.main.PlantCatalogue convert(java.lang.Long id) {
                return PlantCatalogue.findPlantCatalogue(id);
            }
        };
    }
    
    public Converter<String, PlantCatalogue> ApplicationConversionServiceFactoryBean.getStringToPlantCatalogueConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, biz.buildit.main.PlantCatalogue>() {
            public biz.buildit.main.PlantCatalogue convert(String id) {
                return getObject().convert(getObject().convert(id, Long.class), PlantCatalogue.class);
            }
        };
    }
    
    public Converter<PlantHireRequest, String> ApplicationConversionServiceFactoryBean.getPlantHireRequestToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<biz.buildit.main.PlantHireRequest, java.lang.String>() {
            public String convert(PlantHireRequest plantHireRequest) {
                return new StringBuilder().append(plantHireRequest.getId()).append(' ').append(plantHireRequest.getSiteId()).append(' ').append(plantHireRequest.getStartDate()).append(' ').append(plantHireRequest.getEndDate()).toString();
            }
        };
    }
    
    public Converter<Long, PlantHireRequest> ApplicationConversionServiceFactoryBean.getIdToPlantHireRequestConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Long, biz.buildit.main.PlantHireRequest>() {
            public biz.buildit.main.PlantHireRequest convert(java.lang.Long id) {
                return PlantHireRequest.findPlantHireRequest(id);
            }
        };
    }
    
    public Converter<String, PlantHireRequest> ApplicationConversionServiceFactoryBean.getStringToPlantHireRequestConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, biz.buildit.main.PlantHireRequest>() {
            public biz.buildit.main.PlantHireRequest convert(String id) {
                return getObject().convert(getObject().convert(id, Long.class), PlantHireRequest.class);
            }
        };
    }
    
    public Converter<SiteEngineer, String> ApplicationConversionServiceFactoryBean.getSiteEngineerToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<biz.buildit.main.SiteEngineer, java.lang.String>() {
            public String convert(SiteEngineer siteEngineer) {
                return new StringBuilder().append(siteEngineer.getId()).append(' ').append(siteEngineer.getName()).toString();
            }
        };
    }
    
    public Converter<Long, SiteEngineer> ApplicationConversionServiceFactoryBean.getIdToSiteEngineerConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Long, biz.buildit.main.SiteEngineer>() {
            public biz.buildit.main.SiteEngineer convert(java.lang.Long id) {
                return SiteEngineer.findSiteEngineer(id);
            }
        };
    }
    
    public Converter<String, SiteEngineer> ApplicationConversionServiceFactoryBean.getStringToSiteEngineerConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, biz.buildit.main.SiteEngineer>() {
            public biz.buildit.main.SiteEngineer convert(String id) {
                return getObject().convert(getObject().convert(id, Long.class), SiteEngineer.class);
            }
        };
    }
    
    public Converter<WorksEngineer, String> ApplicationConversionServiceFactoryBean.getWorksEngineerToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<biz.buildit.main.WorksEngineer, java.lang.String>() {
            public String convert(WorksEngineer worksEngineer) {
                return new StringBuilder().append(worksEngineer.getId()).append(' ').append(worksEngineer.getName()).toString();
            }
        };
    }
    
    public Converter<Long, WorksEngineer> ApplicationConversionServiceFactoryBean.getIdToWorksEngineerConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Long, biz.buildit.main.WorksEngineer>() {
            public biz.buildit.main.WorksEngineer convert(java.lang.Long id) {
                return WorksEngineer.findWorksEngineer(id);
            }
        };
    }
    
    public Converter<String, WorksEngineer> ApplicationConversionServiceFactoryBean.getStringToWorksEngineerConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, biz.buildit.main.WorksEngineer>() {
            public biz.buildit.main.WorksEngineer convert(String id) {
                return getObject().convert(getObject().convert(id, Long.class), WorksEngineer.class);
            }
        };
    }
    
    public void ApplicationConversionServiceFactoryBean.installLabelConverters(FormatterRegistry registry) {
        registry.addConverter(getInvoiceToStringConverter());
        registry.addConverter(getIdToInvoiceConverter());
        registry.addConverter(getStringToInvoiceConverter());
        registry.addConverter(getPlantCatalogueToStringConverter());
        registry.addConverter(getIdToPlantCatalogueConverter());
        registry.addConverter(getStringToPlantCatalogueConverter());
        registry.addConverter(getPlantHireRequestToStringConverter());
        registry.addConverter(getIdToPlantHireRequestConverter());
        registry.addConverter(getStringToPlantHireRequestConverter());
        registry.addConverter(getSiteEngineerToStringConverter());
        registry.addConverter(getIdToSiteEngineerConverter());
        registry.addConverter(getStringToSiteEngineerConverter());
        registry.addConverter(getWorksEngineerToStringConverter());
        registry.addConverter(getIdToWorksEngineerConverter());
        registry.addConverter(getStringToWorksEngineerConverter());
    }
    
    public void ApplicationConversionServiceFactoryBean.afterPropertiesSet() {
        super.afterPropertiesSet();
        installLabelConverters(getObject());
    }
    
}

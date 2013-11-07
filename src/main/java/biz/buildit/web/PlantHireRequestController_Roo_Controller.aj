// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package biz.buildit.web;

import biz.buildit.main.PlantCatalogue;
import biz.buildit.main.PlantHireRequest;
import biz.buildit.main.SiteEngineer;
import biz.buildit.main.WorksEngineer;
import biz.buildit.web.PlantHireRequestController;
import java.io.UnsupportedEncodingException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.joda.time.format.DateTimeFormat;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriUtils;
import org.springframework.web.util.WebUtils;

privileged aspect PlantHireRequestController_Roo_Controller {
    
    @RequestMapping(method = RequestMethod.POST, produces = "text/html")
    public String PlantHireRequestController.create(@Valid PlantHireRequest plantHireRequest, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, plantHireRequest);
            return "planthirerequests/create";
        }
        uiModel.asMap().clear();
        plantHireRequest.persist();
        return "redirect:/planthirerequests/" + encodeUrlPathSegment(plantHireRequest.getId_().toString(), httpServletRequest);
    }
    
    @RequestMapping(params = "form", produces = "text/html")
    public String PlantHireRequestController.createForm(Model uiModel) {
        populateEditForm(uiModel, new PlantHireRequest());
        return "planthirerequests/create";
    }
    
    @RequestMapping(value = "/{id_}", produces = "text/html")
    public String PlantHireRequestController.show(@PathVariable("id_") Long id_, Model uiModel) {
        addDateTimeFormatPatterns(uiModel);
        uiModel.addAttribute("planthirerequest", PlantHireRequest.findPlantHireRequest(id_));
        uiModel.addAttribute("itemId", id_);
        return "planthirerequests/show";
    }
    
    @RequestMapping(produces = "text/html")
    public String PlantHireRequestController.list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("planthirerequests", PlantHireRequest.findPlantHireRequestEntries(firstResult, sizeNo));
            float nrOfPages = (float) PlantHireRequest.countPlantHireRequests() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("planthirerequests", PlantHireRequest.findAllPlantHireRequests());
        }
        addDateTimeFormatPatterns(uiModel);
        return "planthirerequests/list";
    }
    
    @RequestMapping(method = RequestMethod.PUT, produces = "text/html")
    public String PlantHireRequestController.update(@Valid PlantHireRequest plantHireRequest, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, plantHireRequest);
            return "planthirerequests/update";
        }
        uiModel.asMap().clear();
        plantHireRequest.merge();
        return "redirect:/planthirerequests/" + encodeUrlPathSegment(plantHireRequest.getId_().toString(), httpServletRequest);
    }
    
    @RequestMapping(value = "/{id_}", params = "form", produces = "text/html")
    public String PlantHireRequestController.updateForm(@PathVariable("id_") Long id_, Model uiModel) {
        populateEditForm(uiModel, PlantHireRequest.findPlantHireRequest(id_));
        return "planthirerequests/update";
    }
    
    @RequestMapping(value = "/{id_}", method = RequestMethod.DELETE, produces = "text/html")
    public String PlantHireRequestController.delete(@PathVariable("id_") Long id_, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        PlantHireRequest plantHireRequest = PlantHireRequest.findPlantHireRequest(id_);
        plantHireRequest.remove();
        uiModel.asMap().clear();
        uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
        uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
        return "redirect:/planthirerequests";
    }
    
    void PlantHireRequestController.addDateTimeFormatPatterns(Model uiModel) {
        uiModel.addAttribute("plantHireRequest_startdate_date_format", DateTimeFormat.patternForStyle("M-", LocaleContextHolder.getLocale()));
        uiModel.addAttribute("plantHireRequest_enddate_date_format", DateTimeFormat.patternForStyle("M-", LocaleContextHolder.getLocale()));
        uiModel.addAttribute("plantHireRequest_extensiondate_date_format", DateTimeFormat.patternForStyle("M-", LocaleContextHolder.getLocale()));
    }
    
    void PlantHireRequestController.populateEditForm(Model uiModel, PlantHireRequest plantHireRequest) {
        uiModel.addAttribute("plantHireRequest", plantHireRequest);
        addDateTimeFormatPatterns(uiModel);
        uiModel.addAttribute("plantcatalogues", PlantCatalogue.findAllPlantCatalogues());
        uiModel.addAttribute("siteengineers", SiteEngineer.findAllSiteEngineers());
        uiModel.addAttribute("worksengineers", WorksEngineer.findAllWorksEngineers());
    }
    
    String PlantHireRequestController.encodeUrlPathSegment(String pathSegment, HttpServletRequest httpServletRequest) {
        String enc = httpServletRequest.getCharacterEncoding();
        if (enc == null) {
            enc = WebUtils.DEFAULT_CHARACTER_ENCODING;
        }
        try {
            pathSegment = UriUtils.encodePathSegment(pathSegment, enc);
        } catch (UnsupportedEncodingException uee) {}
        return pathSegment;
    }
    
}
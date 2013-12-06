package biz.buildit.web;
import biz.buildit.main.Plant;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/plantcatalogues")
@Controller
@RooWebScaffold(path = "plantcatalogues", formBackingObject = Plant.class)
public class PlantCatalogueController {
}

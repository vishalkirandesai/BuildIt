package biz.buildit.web;
import biz.buildit.main.WorksEngineer;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/worksengineers")
@Controller
@RooWebScaffold(path = "worksengineers", formBackingObject = WorksEngineer.class)
public class WorksEngineerController {
}

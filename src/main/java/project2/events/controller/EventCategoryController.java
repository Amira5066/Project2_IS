package project2.events.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import project2.events.model.EventCategory;
import project2.events.service.EventCategoryService;


@Controller
@RequestMapping("eventCategory")
public class EventCategoryController {
    private EventCategoryService eventCategoryService;
    @Autowired
    public EventCategoryController(EventCategoryService eventCategoryService) {
        this.eventCategoryService = eventCategoryService;
    }

    @GetMapping
    public String displayAllCategories(Model model) {
        model.addAttribute("eventCategories", eventCategoryService.findAll());
        model.addAttribute("title", "All Categories");
        return "eventCategories/index";
    }

    @GetMapping("create")
    public String displayCreateEventCategoryForm(Model model) {
        model.addAttribute("title", "Create Event Category");
        model.addAttribute(new EventCategory());
        return "eventCategories/create";
    }

    @PostMapping("create")
    public String processCreateEventCategoryForm(@Validated @ModelAttribute EventCategory newEventCategory, Errors errors, Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("title", "Create Event Category");
            return "eventCategories/create";
        }
        eventCategoryService.save(newEventCategory);
        return "redirect:/eventCategory/create";
    }
}

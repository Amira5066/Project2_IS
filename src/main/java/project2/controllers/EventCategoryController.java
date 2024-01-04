package project2.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import project2.data.EventCategoryRepository;
import project2.models.EventCategory;


@Controller
@RequestMapping("eventCategory")
public class EventCategoryController {
    @Autowired
    private EventCategoryRepository eventCategoryRepository;

    @GetMapping
    public String displayAllCategories(Model model) {
        model.addAttribute("eventCategories", eventCategoryRepository.findAll());
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
        eventCategoryRepository.save(newEventCategory);
        return "redirect:/eventCategories/create";
    }
}

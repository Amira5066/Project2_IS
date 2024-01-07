package project2.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import project2.models.Event;
import project2.models.EventCategory;
import project2.models.Tag;
import project2.models.dto.EventTagDTO;
import project2.service.EventCategoryService;
import project2.service.EventService;
import project2.service.TagService;

import java.util.Optional;


@Controller
@RequestMapping("events")
public class EventController {

    private EventCategoryService eventCategoryService;
    private EventService eventService;
    private TagService tagService;
    @Autowired
    public EventController(EventCategoryService eventCategoryService, EventService eventService, TagService tagService) {
        this.eventCategoryService = eventCategoryService;
        this.eventService = eventService;
        this.tagService = tagService;
    }

    @GetMapping
    public String displayAllEvents(@RequestParam(required = false) Integer categoryId,  Model model) {
        if (categoryId == null) {
            model.addAttribute("events", eventService.findAll());
            model.addAttribute("title", "All Events");
        } else {
            Optional<EventCategory> result = eventCategoryService.findById(categoryId);
            if (result.isEmpty()) {
                model.addAttribute("title", "Invalid Category ID: " + categoryId);
            } else {
                EventCategory category = result.get();
                model.addAttribute("title", "Events in category: " + category.getName());
                model.addAttribute("events", category.getEvents());
            }
        }

        return "events/index";
    }

    @GetMapping("create")
    public String displayCreateEventForm(Model model) {
        model.addAttribute("title", "Create Events");
        model.addAttribute(new Event());
        model.addAttribute("categories", eventCategoryService.findAll());
        return "events/create";
    }

    @PostMapping("create")
    public String processCreateEventForm(@Validated @ModelAttribute Event newEvent, Errors errors, Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("title", "Create Event");
            return "events/create";
        }
        eventService.save(newEvent);
        return "redirect:/events/create";
    }

    @GetMapping("delete")
    public String displayDeleteEventForm(Model model) {
        model.addAttribute("title", "Delete Events");
        model.addAttribute("events", eventService.findAll());
        return "events/delete";
    }

    @PostMapping("delete")
    public String processDeleteEventsForm(@RequestParam(required = false) int[] eventIds) {
        if (eventIds == null) return "redirect:/events/delete";
        for (int id : eventIds) {
            eventService.deleteById(id);
        }
        return "redirect:/events/delete";
    }

    @GetMapping("detail")
    public String displayEventDetails(@RequestParam(required = true) Integer eventId, Model model) {
        Optional<Event> result = eventService.findById(eventId);

        if (result.isEmpty()) {
            model.addAttribute("title", "Invalid Event ID: " + eventId);
        } else {
            Event event = result.get();
            model.addAttribute("title", event.getName()+ " Details");
            model.addAttribute("event", event);
            model.addAttribute("tags", event.getTags());
        }
        return "/events/detail";
    }

    @GetMapping("add-tag")
    public String displayAddTagFrom(@RequestParam Integer eventId, Model model) {
        Optional<Event> result = eventService.findById(eventId);
        Event event = result.get();
        model.addAttribute("title", "Add Tag to " + event.getName());
        model.addAttribute("tags", tagService.findAll());
        EventTagDTO eventTag = new EventTagDTO();
        eventTag.setEvent(event);
        model.addAttribute("eventTag", eventTag);
        return "/events/add-tag";
    }

    @PostMapping("add-tag")
    public String processAddTagFrom(@ModelAttribute @Validated EventTagDTO eventTag, Model model, Errors errors) {
        if (!errors.hasErrors()) {
            Event event = eventTag.getEvent();
            Tag tag = eventTag.getTag();
            if (!event.getTags().contains(tag)) {
                event.addTag(tag);
                eventService.save(event);
            }
            return "redirect:detail?eventId=" + event.getId();
        }
        return "redirect:/events/add-tag";
    }
}

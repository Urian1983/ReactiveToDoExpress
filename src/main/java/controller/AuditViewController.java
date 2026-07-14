package controller;

import dto.AuditRequest;
import jakarta.validation.Valid;
import model.LogLevel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import service.AuditService;
import webform.AuditFormDTO;

@Controller
@RequestMapping("/audits")
public class AuditViewController {

    private final AuditService service;


    public AuditViewController(AuditService serviceervice) {
        this.service = serviceervice;
    }

    @GetMapping
    public Mono<String> list(Model model) {
        return service.getAllAudits()
                .collectList()
                .doOnNext(audits -> model.addAttribute("audits", audits))
                .thenReturn("audits/list");
    }

    @GetMapping("/new")
    public String newForm(Model model) {
        model.addAttribute("auditForm", new AuditFormDTO());
        model.addAttribute("levels", LogLevel.values());
        return "audits/form";
    }

    @PostMapping
    public Mono<String> create(@Valid @ModelAttribute("auditForm") AuditFormDTO form,
                               BindingResult result,
                               Model model) {
        if (result.hasErrors()) {
            model.addAttribute("levels", LogLevel.values());
            return Mono.just("audits/form");
        }

        var request = new AuditRequest(form.getLevel(), form.getTaskId(), form.getMessage());
        return service.createAudit(request).thenReturn("redirect:/audits");
    }
}

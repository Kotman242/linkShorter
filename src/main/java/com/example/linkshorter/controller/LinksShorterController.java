package com.example.linkshorter.controller;

import com.example.linkshorter.model.dto.LinkTO;
import com.example.linkshorter.service.LinkService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequiredArgsConstructor
public class LinksShorterController {

    private final LinkService linkService;

    @PostMapping("/")
    ResponseEntity<LinkTO> createShortLink(@Valid @RequestBody LinkTO link) {
        String shortLink = linkService.createShortLink(link.getLink());
        LinkTO response = new LinkTO(shortLink);
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }

    @GetMapping("/{link}")
    RedirectView redirect(@PathVariable String link) {
        return new RedirectView(linkService.getLongLink(link));
    }
}

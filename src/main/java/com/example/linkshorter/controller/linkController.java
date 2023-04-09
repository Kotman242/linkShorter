package com.example.linkshorter.controller;

import com.example.linkshorter.model.dto.RequestLink;
import com.example.linkshorter.service.LinkService;
import com.example.linkshorter.service.ShortLinkService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.URL;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequiredArgsConstructor
public class linkController {

    private final LinkService linkService;
    @PostMapping("/")
    ResponseEntity<String> createShortLink(@Valid @RequestBody RequestLink link ){
        String shortLink = linkService.createShortLink(link.getLink());
        return ResponseEntity.ok(shortLink);
    }

    @GetMapping("/{link}")
    RedirectView redirect(@PathVariable String link){
        System.out.println(linkService.getLongLink(link));

        return new RedirectView(linkService.getLongLink(link));
    }
}

package com.example.linkshorter.controller;

import com.example.linkshorter.service.LinkService;
import lombok.RequiredArgsConstructor;
import lombok.Value;
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
    ResponseEntity<String> createShortLink(@RequestBody @URL String link){
        System.out.println("post");
        System.out.println(link);
        String shortLink = linkService.createShortLink(link);
        return ResponseEntity.ok(shortLink);
    }

    @GetMapping("/{link}")
    RedirectView redirect(@PathVariable String link){
        System.out.println("get");
        System.out.println(linkService.getLongLink(link));

        return new RedirectView(linkService.getLongLink(link));
    }
}

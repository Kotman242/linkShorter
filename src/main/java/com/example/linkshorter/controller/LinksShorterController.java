package com.example.linkshorter.controller;

import com.example.linkshorter.model.Link;
import com.example.linkshorter.model.LinkTO;
import com.example.linkshorter.service.LinkService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class LinksShorterController {

    private final LinkService linkService;

    @PostMapping("${application.point.generate}")
    String createShortLink(@ModelAttribute("link") @Valid LinkTO link, Model model) {
        String userLogin = SecurityContextHolder.getContext().getAuthentication().getName();
        String shortLink = linkService.createShortLink(link.getLink(), userLogin);
        LinkTO response = new LinkTO(shortLink);
        model.addAttribute("link", shortLink);
        return "showShortLink";
    }

    @GetMapping("${application.point.redirect}")
    RedirectView redirect(@PathVariable String link) {
        return new RedirectView(linkService.getLongLink(link));
    }


    @GetMapping("${application.point.all}")
    String getAllLink(Model model) {
        String userLogin = SecurityContextHolder.getContext().getAuthentication().getName();
        List<Link> linkList = linkService.getAllLink(userLogin);
        System.out.println(linkList);
        model.addAttribute("lists", linkList);
        return "allList";
    }

}

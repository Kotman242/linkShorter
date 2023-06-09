package com.example.linkshorter.validation;

import com.example.linkshorter.repository.LinkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class CheckerOldLink {

    private final LinkRepository linkRepository;
    private LocalDate lastCheckDate;
    @Value("${shortLink.saveLink}")
    private int saveLink;

    public void checkOldLink() {
        if (saveLink <= 0) {
            return;
        } else if (lastCheckDate == null ||
                lastCheckDate.isBefore(LocalDate.now())) {
            linkRepository.deleteOldLink(saveLink);
            lastCheckDate = LocalDate.now();
        }
    }
}

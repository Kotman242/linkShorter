package com.example.linkshorter.repository;

import com.example.linkshorter.model.Link;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LinkRepository extends JpaRepository<Link, Long> {

    boolean existsLinkByLongLink(String longLink);

    boolean existsLinkByShortLink(String ShortLink);

    Link getLinkByLongLink(String longLink);

    Link getLinkByShortLink(String shortLink);

    @Query("DELETE FROM Link WHERE date <= now() - INTERVAL(:days)")
    void deleteOldLink(@Param("days") String days);
}

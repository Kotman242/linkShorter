package com.example.linkshorter.repository;

import com.example.linkshorter.model.Link;
import com.example.linkshorter.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface LinkRepository extends JpaRepository<Link, Long> {

    boolean existsLinkByLongLink(String longLink);

    boolean existsLinkByShortLink(String ShortLink);

    Link getLinkByLongLink(String longLink);

    Link getLinkByShortLink(String shortLink);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM links WHERE create_date <= now() - make_interval(days => :days )",
            nativeQuery = true)
    void deleteOldLink(@Param("days") int days);

    List<Link> getLinksByPerson(Person person);
}

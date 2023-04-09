package com.example.linkshorter.repository;

import com.example.linkshorter.model.Link;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ShortLinkRepository implements LinkRepository{

    private final JdbcTemplate jdbcTemplate;

    @Override
    public void addLink(Link link){
        jdbcTemplate.update("INSERT INTO links(short_link,long_link,date) VALUES (?, ?, NOW())",
                link.getShortLink(),link.getLongLink());
    }
    @Override
    public boolean linkExistShortLink(String link){
        return !jdbcTemplate.query("SELECT * FROM links WHERE short_link=?"
                ,new Object[]{link}, new BeanPropertyRowMapper<>(Link.class)).isEmpty();
    }
    @Override
    public boolean linkExistLongLink(String link){
        return !jdbcTemplate.query("SELECT * FROM links WHERE long_link=?"
                ,new Object[]{link}, new BeanPropertyRowMapper<>(Link.class)).isEmpty();
    }
    @Override
    public Link getLinkWithShortLink(String shortLink){
        return jdbcTemplate.query("SELECT * FROM links WHERE short_link=?"
                ,new Object[]{shortLink}, new BeanPropertyRowMapper<>(Link.class)).stream()
                .findAny().orElse(null);
    }
    @Override
    public Link getLinkWithLongtLink(String shortLink){
        return jdbcTemplate.query("SELECT * FROM links WHERE long_link=?"
                        ,new Object[]{shortLink}, new BeanPropertyRowMapper<>(Link.class)).stream()
                .findAny().orElse(null);
    }
    @Override
    public void deleteOldLink(String daySaveLink){
        jdbcTemplate.update("DROP FROM links WHERE date< now()- INTERVAL(?)",daySaveLink);
    }
}

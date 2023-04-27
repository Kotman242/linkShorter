package com.example.linkshorter.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "links")
@Builder
public class Link {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(unique = true,
            nullable = false)
    private String shortLink;
    @Column(unique = true,
            nullable = false)
    private String longLink;

    @Temporal(TemporalType.DATE)
    @Column(name = "create_date",
            nullable = false)
    @ColumnDefault("now()")
    private Date date;
}

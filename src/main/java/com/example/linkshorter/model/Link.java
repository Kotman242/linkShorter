package com.example.linkshorter.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@NoArgsConstructor
@Data
@AllArgsConstructor
@Table(name = "links")
@Builder
public class Link {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "short_link",
            unique = true,
            nullable = false)
    private String shortLink;
    @Column(name = "long_link",
            nullable = false)
    private String longLink;

    @Temporal(TemporalType.DATE)
    @Column(name = "create_date",
            nullable = false)
    private Date date;
    @JoinColumn(name = "id_user")
    @ManyToOne
    private Person person;

}

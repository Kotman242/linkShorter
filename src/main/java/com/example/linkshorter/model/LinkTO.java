package com.example.linkshorter.model;


import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonAutoDetect
public class LinkTO {
    @NotNull(message = "Link cannot be null")
    @NotBlank(message = "Link cannot be empty")
    @URL(message = "Link must be a valid URL address")
    private String link;

}

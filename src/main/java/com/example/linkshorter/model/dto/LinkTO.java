package com.example.linkshorter.model.dto;


import com.fasterxml.jackson.annotation.JsonAutoDetect;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;
import org.springframework.boot.jackson.JsonComponent;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonAutoDetect
public class LinkTO {

    @NotNull(message = "Link cannot be null")
    @NotBlank(message = "Link cannot be empty")
    @URL(message = "Link must be a valid URL address")
    private String Link;

}

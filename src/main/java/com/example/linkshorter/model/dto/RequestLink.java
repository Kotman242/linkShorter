package com.example.linkshorter.model.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestLink {

    @NotNull(message = "Link cannot be null")
    @NotBlank(message = "Link cannot be empty")
    @URL(message = "Link must be a valid URL address")
    private String Link;

}

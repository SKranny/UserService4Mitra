package com.example.UserService.dto;

import lombok.Data;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
public class UpdatePersonRequest {
    @NotBlank
    String firstName;

    @NotBlank
    String lastName;

    @NotBlank
    private String email;
    @NotNull
    private LocalDate birthDay;

}

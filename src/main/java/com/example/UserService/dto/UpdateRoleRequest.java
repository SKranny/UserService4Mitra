package com.example.UserService.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UpdateRoleRequest {
    @NotBlank
    String role;
}

package com.ms.user.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public record UserRecordSaveDTO(
        @NotBlank String name,
        @NotBlank @Email String email) {
}

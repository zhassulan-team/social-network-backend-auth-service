package kata.academy.auth.model.dto;

import javax.validation.constraints.NotBlank;

public record AccountValidateRequestDto(
        @NotBlank String username,
        @NotBlank String password) {
}

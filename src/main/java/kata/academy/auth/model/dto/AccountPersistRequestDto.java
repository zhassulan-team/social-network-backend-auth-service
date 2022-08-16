package kata.academy.auth.model.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public record AccountPersistRequestDto(
        @NotBlank String username,
        @NotBlank String password,
        @NotNull String name) {
}

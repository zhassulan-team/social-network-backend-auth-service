package kata.academy.eurekaauthservice.model.dto;

import javax.validation.constraints.NotBlank;

public record UserDto(
        @NotBlank String email,
        @NotBlank String password) {
}

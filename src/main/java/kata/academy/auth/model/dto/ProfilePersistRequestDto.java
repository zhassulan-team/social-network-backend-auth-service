package kata.academy.auth.model.dto;

import lombok.Builder;

@Builder
public record ProfilePersistRequestDto(
        Long accountId,
        String username,
        String name) {
}

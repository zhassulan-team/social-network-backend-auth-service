package kata.academy.auth.model.dto;

import lombok.Builder;

@Builder
public record AccountClaimsResponseDto(
        Long accountId,
        String role) {
}

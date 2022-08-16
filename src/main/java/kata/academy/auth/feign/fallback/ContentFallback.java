package kata.academy.auth.feign.fallback;

import kata.academy.auth.exception.FeignRequestException;
import kata.academy.auth.feign.ContentFeignClient;
import kata.academy.auth.model.dto.ProfilePersistRequestDto;

record ContentFallback(Throwable cause) implements ContentFeignClient {

    @Override
    public void persistProfile(ProfilePersistRequestDto dto) {
        throw new FeignRequestException("Сервис временно недоступен. Причина -> %s"
                .formatted(cause.getMessage()), cause);
    }
}

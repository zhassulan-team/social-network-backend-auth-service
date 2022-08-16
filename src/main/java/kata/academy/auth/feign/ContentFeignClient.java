package kata.academy.auth.feign;

import kata.academy.auth.feign.fallback.ContentFallbackFactory;
import kata.academy.auth.model.dto.ProfilePersistRequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "content", fallbackFactory = ContentFallbackFactory.class)
public interface ContentFeignClient {

    @PostMapping("/api/inner/v1/profiles")
    void persistProfile(@RequestBody ProfilePersistRequestDto dto);
}

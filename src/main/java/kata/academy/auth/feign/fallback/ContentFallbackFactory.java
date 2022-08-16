package kata.academy.auth.feign.fallback;

import kata.academy.auth.feign.ContentFeignClient;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class ContentFallbackFactory implements FallbackFactory<ContentFeignClient> {

    @Override
    public ContentFeignClient create(Throwable cause) {
        return new ContentFallback(cause);
    }
}

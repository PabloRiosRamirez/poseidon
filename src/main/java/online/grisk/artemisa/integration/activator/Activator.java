package online.grisk.poseidon.integration.activator;

import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;

import java.util.Map;

public interface Activator {
    public Map<String, Object> invoke(@Payload Map<String, Object> payload, @Headers Map<String, Object> header);
}

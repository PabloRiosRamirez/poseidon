package online.grisk.poseidon.integration.activator.impl;

import online.grisk.poseidon.integration.activator.Activator;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Component
public class MatrizServiceActivator implements Activator {

    public Map<String, Object> invoke(@Payload Map<String, Object> payload, @Headers Map<String, Object> header) {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Map<String, Object> attribute = new HashMap<>();
        for (int i = 0; i < new Random().nextInt(15) + 2; i++) {
            attribute.put("nivel_" + i, new Random().nextInt(35000) + 1 + "");
        }
        payload.put("matrizRisk", attribute);
        return payload;
    }
}

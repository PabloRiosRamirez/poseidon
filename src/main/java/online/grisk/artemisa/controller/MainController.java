package online.grisk.poseidon.controller;

import online.grisk.poseidon.integration.gateway.GatewayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
public class MainController {
    @Autowired
    GatewayService gatewayService;

    @PostMapping(value = "/v1/rest/api/consumer")
    public Map<String, Object> report(@RequestBody Map payload) {
        HashMap<String, Object> response = new HashMap<>();
        response.put("uuid", UUID.randomUUID());
        response.put("timestamp", new Date());
        response.put("request", payload);
        return gatewayService.process(MessageBuilder.withPayload(response).build());
    }
}

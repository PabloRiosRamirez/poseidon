package online.grisk.poseidon.integration.splitter;

import org.springframework.integration.splitter.AbstractMessageSplitter;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class IndicatorsSplitter extends AbstractMessageSplitter {

    @Override
    protected List<Message<Map<String, Object>>> splitMessage(Message<?> message) {
        List messages = new ArrayList();
        messages.add(message);
        messages.add(message);
        messages.add(message);
        return messages;
    }
}

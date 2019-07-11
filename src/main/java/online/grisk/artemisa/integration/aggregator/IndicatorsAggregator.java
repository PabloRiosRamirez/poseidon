package online.grisk.poseidon.integration.aggregator;

import org.springframework.integration.aggregator.AbstractAggregatingMessageGroupProcessor;
import org.springframework.integration.store.MessageGroup;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class IndicatorsAggregator extends AbstractAggregatingMessageGroupProcessor {

    @Override
    protected Object aggregatePayloads(MessageGroup messageGroup, Map<String, Object> map) {
        return messageGroup.getOne();
    }
}

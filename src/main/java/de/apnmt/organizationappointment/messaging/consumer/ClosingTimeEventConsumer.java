package de.apnmt.organizationappointment.messaging.consumer;

import de.apnmt.common.TopicConstants;
import de.apnmt.common.event.ApnmtEvent;
import de.apnmt.common.event.value.ClosingTimeEventDTO;
import de.apnmt.organizationappointment.common.service.ClosingTimeService;
import de.apnmt.organizationappointment.messaging.SubscriptionConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

@Service
public class ClosingTimeEventConsumer extends de.apnmt.organizationappointment.common.async.controller.ClosingTimeEventConsumer {

    private final Logger log = LoggerFactory.getLogger(ClosingTimeEventConsumer.class);

    public ClosingTimeEventConsumer(ClosingTimeService closingTimeService) {
        super(closingTimeService);
    }

    @Override
    @JmsListener(destination = TopicConstants.CLOSING_TIME_CHANGED_TOPIC, containerFactory = "topicJmsListenerContainerFactory",
        subscription = SubscriptionConstants.CLOSING_TIME_CHANGED_SUBSCRIPTION)
    public void receiveEvent(ApnmtEvent<ClosingTimeEventDTO> message) {
        log.info("Received event {} from subscription {}", message, SubscriptionConstants.CLOSING_TIME_CHANGED_SUBSCRIPTION);
        super.receiveEvent(message);
    }

}

package de.apnmt.organizationappointment.messaging.consumer;

import de.apnmt.common.TopicConstants;
import de.apnmt.common.event.ApnmtEvent;
import de.apnmt.common.event.value.OpeningHourEventDTO;
import de.apnmt.organizationappointment.common.service.OpeningHourService;
import de.apnmt.organizationappointment.messaging.SubscriptionConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

@Service
public class OpeningHourEventConsumer extends de.apnmt.organizationappointment.common.async.controller.OpeningHourEventConsumer {

    private final Logger log = LoggerFactory.getLogger(OpeningHourEventConsumer.class);

    public OpeningHourEventConsumer(OpeningHourService openingHourService) {
        super(openingHourService);
    }

    @Override
    @JmsListener(destination = TopicConstants.OPENING_HOUR_CHANGED_TOPIC, containerFactory = "topicJmsListenerContainerFactory",
        subscription = SubscriptionConstants.OPENING_HOUR_CHANGED_SUBSCRIPTION)
    public void receiveEvent(ApnmtEvent<OpeningHourEventDTO> message) {
        log.info("Received event {} from subscription {}", message, SubscriptionConstants.OPENING_HOUR_CHANGED_SUBSCRIPTION);
        super.receiveEvent(message);
    }

}

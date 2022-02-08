package de.apnmt.organizationappointment.messaging.consumer;

import de.apnmt.common.TopicConstants;
import de.apnmt.common.event.ApnmtEvent;
import de.apnmt.common.event.value.WorkingHourEventDTO;
import de.apnmt.organizationappointment.common.service.WorkingHourService;
import de.apnmt.organizationappointment.messaging.SubscriptionConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

@Service
public class WorkingHourEventConsumer extends de.apnmt.organizationappointment.common.async.controller.WorkingHourEventConsumer {

    private final Logger log = LoggerFactory.getLogger(WorkingHourEventConsumer.class);

    public WorkingHourEventConsumer(WorkingHourService workingHourService) {
        super(workingHourService);
    }

    @Override
    @JmsListener(destination = TopicConstants.WORKING_HOUR_CHANGED_TOPIC, containerFactory = "topicJmsListenerContainerFactory",
        subscription = SubscriptionConstants.WORKING_HOUR_CHANGED_SUBSCRIPTION)
    public void receiveEvent(ApnmtEvent<WorkingHourEventDTO> message) {
        log.info("Received event {} from queue {}", message, SubscriptionConstants.WORKING_HOUR_CHANGED_SUBSCRIPTION);
        super.receiveEvent(message);
    }

}

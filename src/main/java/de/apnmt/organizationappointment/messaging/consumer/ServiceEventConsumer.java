package de.apnmt.organizationappointment.messaging.consumer;

import de.apnmt.common.TopicConstants;
import de.apnmt.common.event.ApnmtEvent;
import de.apnmt.common.event.value.ServiceEventDTO;
import de.apnmt.organizationappointment.common.service.ServiceService;
import de.apnmt.organizationappointment.messaging.SubscriptionConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

@Service
public class ServiceEventConsumer extends de.apnmt.organizationappointment.common.async.controller.ServiceEventConsumer {

    private final Logger log = LoggerFactory.getLogger(ServiceEventConsumer.class);

    public ServiceEventConsumer(ServiceService serviceService) {
        super(serviceService);
    }

    @Override
    @JmsListener(destination = TopicConstants.SERVICE_CHANGED_TOPIC, containerFactory = "topicJmsListenerContainerFactory",
        subscription = SubscriptionConstants.SERVICE_CHANGED_SUBSCRIPTION)
    public void receiveEvent(ApnmtEvent<ServiceEventDTO> message) {
        log.info("Received event {} from subscription {}", message, SubscriptionConstants.SERVICE_CHANGED_SUBSCRIPTION);
        super.receiveEvent(message);
    }

}

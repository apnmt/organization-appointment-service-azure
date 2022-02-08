package de.apnmt.organizationappointment.messaging.consumer;

import de.apnmt.common.TopicConstants;
import de.apnmt.common.event.ApnmtEvent;
import de.apnmt.common.event.value.AppointmentEventDTO;
import de.apnmt.organizationappointment.common.service.AppointmentService;
import de.apnmt.organizationappointment.messaging.SubscriptionConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

@Service
public class AppointmentEventConsumer extends de.apnmt.organizationappointment.common.async.controller.AppointmentEventConsumer {

    private final Logger log = LoggerFactory.getLogger(AppointmentEventConsumer.class);

    public AppointmentEventConsumer(AppointmentService appointmentService) {
        super(appointmentService);
    }

    @Override
    @JmsListener(destination = TopicConstants.APPOINTMENT_CHANGED_TOPIC, containerFactory = "topicJmsListenerContainerFactory",
        subscription = SubscriptionConstants.APPOINTMENT_CHANGED_SUBSCRIPTION)
    public void receiveEvent(ApnmtEvent<AppointmentEventDTO> message) {
        log.info("Received event {} from subscription {}", message, SubscriptionConstants.APPOINTMENT_CHANGED_SUBSCRIPTION);
        super.receiveEvent(message);
    }
}

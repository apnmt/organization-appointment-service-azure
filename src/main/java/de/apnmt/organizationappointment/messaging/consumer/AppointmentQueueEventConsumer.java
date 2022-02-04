package de.apnmt.organizationappointment.messaging.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.apnmt.common.event.ApnmtEvent;
import de.apnmt.common.event.value.AppointmentEventDTO;
import de.apnmt.organizationappointment.common.async.controller.AppointmentEventConsumer;
import de.apnmt.organizationappointment.common.service.AppointmentService;
import de.apnmt.organizationappointment.messaging.QueueConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class AppointmentQueueEventConsumer extends AppointmentEventConsumer {

    private static final TypeReference<ApnmtEvent<AppointmentEventDTO>> EVENT_TYPE = new TypeReference<>() {
    };

    private final Logger log = LoggerFactory.getLogger(AppointmentQueueEventConsumer.class);

    private final ObjectMapper objectMapper;

    public AppointmentQueueEventConsumer(AppointmentService appointmentService, ObjectMapper objectMapper) {
        super(appointmentService);
        this.objectMapper = objectMapper;
    }

    public void receiveEvent(String message) {
        try {
            log.info("Received event {} from queue {}", message, QueueConstants.APPOINTMENT_QUEUE);
            ApnmtEvent<AppointmentEventDTO> event = this.objectMapper.readValue(message, EVENT_TYPE);
            super.receiveEvent(event);
        } catch (JsonProcessingException e) {
            log.error("Malformed message {} for queue {}. Event will be ignored.", message, QueueConstants.APPOINTMENT_QUEUE);
        }
    }

}

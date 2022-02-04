package de.apnmt.organizationappointment.messaging.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.apnmt.common.event.ApnmtEvent;
import de.apnmt.common.event.value.ServiceEventDTO;
import de.apnmt.organizationappointment.common.async.controller.ServiceEventConsumer;
import de.apnmt.organizationappointment.common.service.ServiceService;
import de.apnmt.organizationappointment.messaging.QueueConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ServiceQueueEventConsumer extends ServiceEventConsumer {

    private static final TypeReference<ApnmtEvent<ServiceEventDTO>> EVENT_TYPE = new TypeReference<>() {
    };

    private final Logger log = LoggerFactory.getLogger(ServiceQueueEventConsumer.class);

    private final ObjectMapper objectMapper;

    public ServiceQueueEventConsumer(ServiceService serviceService, ObjectMapper objectMapper) {
        super(serviceService);
        this.objectMapper = objectMapper;
    }

    public void receiveEvent(String message) {
        try {
            log.info("Received event {} from queue {}", message, QueueConstants.SERVICE_QUEUE);
            ApnmtEvent<ServiceEventDTO> event = this.objectMapper.readValue(message, EVENT_TYPE);
            super.receiveEvent(event);
        } catch (JsonProcessingException e) {
            log.error("Malformed message {} for queue {}. Event will be ignored.", message, QueueConstants.SERVICE_QUEUE);
        }
    }

}

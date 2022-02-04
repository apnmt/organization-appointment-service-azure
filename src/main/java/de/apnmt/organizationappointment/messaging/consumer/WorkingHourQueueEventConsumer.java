package de.apnmt.organizationappointment.messaging.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.apnmt.common.event.ApnmtEvent;
import de.apnmt.common.event.value.WorkingHourEventDTO;
import de.apnmt.organizationappointment.common.async.controller.WorkingHourEventConsumer;
import de.apnmt.organizationappointment.common.service.WorkingHourService;
import de.apnmt.organizationappointment.messaging.QueueConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class WorkingHourQueueEventConsumer extends WorkingHourEventConsumer {

    private static final TypeReference<ApnmtEvent<WorkingHourEventDTO>> EVENT_TYPE = new TypeReference<>() {
    };

    private final Logger log = LoggerFactory.getLogger(WorkingHourQueueEventConsumer.class);

    private final ObjectMapper objectMapper;

    public WorkingHourQueueEventConsumer(WorkingHourService workingHourService, ObjectMapper objectMapper) {
        super(workingHourService);
        this.objectMapper = objectMapper;
    }

    public void receiveEvent(String message) {
        try {
            log.info("Received event {} from queue {}", message, QueueConstants.WORKING_HOUR_QUEUE);
            ApnmtEvent<WorkingHourEventDTO> event = this.objectMapper.readValue(message, EVENT_TYPE);
            super.receiveEvent(event);
        } catch (JsonProcessingException e) {
            log.error("Malformed message {} for queue {}. Event will be ignored.", message, QueueConstants.WORKING_HOUR_QUEUE);
        }
    }

}

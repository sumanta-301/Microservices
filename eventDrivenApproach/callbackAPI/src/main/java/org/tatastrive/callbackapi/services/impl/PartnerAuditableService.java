package org.tatastrive.callbackapi.services.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.tatastrive.callbackapi.entity.Messages;
import org.tatastrive.callbackapi.entity.PartnerAuditTable;
import org.tatastrive.callbackapi.repositories.PartnerAuditRepository;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;

@Slf4j
@Service
public class PartnerAuditableService {
    private final PartnerAuditRepository auditRepository;

    public PartnerAuditableService(PartnerAuditRepository auditRepository) {
        this.auditRepository = auditRepository;
    }
    public Boolean updateMessageProcessedTimestamp(String messageId) {
        PartnerAuditTable message = auditRepository.findById(messageId).orElseThrow(() -> new EntityNotFoundException("No record found for id: " + messageId));
        message.setMessageProcessedTimestamp(LocalDateTime.now());
        auditRepository.save(message);
        return true;
    }
    public PartnerAuditTable updateResponse(String messageId, String response) {
        PartnerAuditTable message = auditRepository.findById(messageId)
                .orElse(null);
        if (message != null) {
            message.setResponseStatus(response);
            return auditRepository.save(message);
        }
       return null;
    }

    public Boolean updateMessageReceivedTimestamp(String messageId, Messages callbackentity) {
        PartnerAuditTable message = auditRepository.findById(messageId).orElseThrow(()-> new EntityNotFoundException("No record found for id " + messageId));
        log.debug("message by messageId::{}", message);
        if(message != null) {
            long engagementId = callbackentity.getEngagementId();
            long dbUserId= callbackentity.getDbUserId();
            message.setEngagementId(engagementId);
            message.setDbUserId(dbUserId);
            message.setMessageReceivedTimestamp(LocalDateTime.now());
            auditRepository.save(message);
            return true;
        }
        else {
            return false;
        }

    }

}

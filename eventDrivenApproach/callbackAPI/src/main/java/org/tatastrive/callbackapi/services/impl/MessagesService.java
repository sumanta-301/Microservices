package org.tatastrive.callbackapi.services.impl;

import org.springframework.stereotype.Service;
import org.tatastrive.callbackapi.entity.Messages;
import org.tatastrive.callbackapi.repositories.MessagesRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class MessagesService {
    private final MessagesRepository repository;

    public MessagesService(MessagesRepository repository) {
        this.repository = repository;
    }

    public Messages saveParkingQueueMessagesToDB(Messages messages){
       return repository.saveAndFlush(messages);
    }
    public List<Messages> checkForCallbackURlChanges() {
        List<Messages> byMessageProcessedStatusFalse = repository.findByMessageProcessedStatusFalse();
        List<Messages> newMessages = new ArrayList<>();
        if(!byMessageProcessedStatusFalse.isEmpty()) {
            for (Messages messages : byMessageProcessedStatusFalse) {

                if (!messages.getCallbackUrl().equals(messages.getUpdatedCallbackUrl())) {
                    messages.setCallbackUrl(messages.getUpdatedCallbackUrl());
                    newMessages.add(messages);
                }
            }
        }
        return newMessages;

    }

    public void updateMessageProcessedStatusAndTimeStamp(Long id){
        Messages messagesFromDb = repository.findById(id).orElseThrow(RuntimeException::new);
        messagesFromDb.setMessageProcessedStatus(true);
        //messagesFromDb.setMessageProcessedTimeStamp(LocalDateTime.now());
        repository.saveAndFlush(messagesFromDb);
    }

}

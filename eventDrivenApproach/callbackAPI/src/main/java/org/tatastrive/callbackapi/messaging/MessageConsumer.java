package org.tatastrive.callbackapi.messaging;

import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.tatastrive.callbackapi.entity.*;
import org.tatastrive.callbackapi.externalServices.RestTemplateRequest;
import org.tatastrive.callbackapi.mail.MailProcessor;
import org.tatastrive.callbackapi.repositories.MessagesRepository;
import org.tatastrive.callbackapi.services.impl.MessagesService;
import org.tatastrive.callbackapi.services.impl.PartnerAuditableService;

import java.util.List;
//import org.tatastrive.callbackapi.services.impl.PartnerAndServiceUrlServiceImpl;

@Service
@RequiredArgsConstructor
public class MessageConsumer {
    private final RestTemplateRequest request;
    private final PartnerAuditableService auditService;
    private final  RabbitTemplate rabbitTemplate;
    private final MessagesRepository repository;
    private final MessagesService messagesService;
    private final MailProcessor mailProcessor;

   // private static final Logger log = LoggerFactory.getLogger(MessageConsumer.class);
    private static final Logger log = LogManager.getLogger(MessageConsumer.class);



    public enum PayloadType {
        ADDRESS,
        DOCUMENT,
        INT_INV,
        BASIC_BENEFICIARY
    }


@RabbitListener(queues = "addressQueue")
    public void consumeStudentAddressService(Messages callbackentity){
        // String url ="https://karyapath-dev.tatastrive.com/sservices-v1/callback/student";
//        System.out.println("printing the messages..."+ callbackentity)
        log.info("inside the Rabbit listener");
        String messageId = callbackentity.getMessageId();
        String url = callbackentity.getCallbackUrl();
        log.debug("printing the message properties :{}", callbackentity);

        callbackentity.setPayloadType(PayloadType.ADDRESS);

    MessagesForAddress messagesForAddress = messagesToAddressPayloadConverter(callbackentity);

    log.debug("printing the message Id from the message... {}", messageId);

        Boolean status=auditService.updateMessageReceivedTimestamp(messageId,callbackentity);
        if(status  && !url.isEmpty() && !messageId.isEmpty()){
            sendPayloadToExternalURL(callbackentity, url, messagesForAddress, messageId);
        }
        else {
            if(messageId==null)log.error("can not found the message with the message Id, request to external url failed{}",messageId);
            if(url==null || url.isEmpty()) log.error("please check the callback url{}", url);
        }


    }


    private  MessagesForAddress messagesToAddressPayloadConverter(Messages callbackentity) {
        MessagesForAddress messagesForAddress = new MessagesForAddress();
        messagesForAddress.setDbUserId(callbackentity.getDbUserId());
        messagesForAddress.setPincode(Math.toIntExact(callbackentity.getPincode()));
        messagesForAddress.setDistrict(callbackentity.getDistrict());
        return messagesForAddress;
    }

    @RabbitListener(queues = "intInvQueue")
    public void consumeStudentIntInv(Messages callbackentity){
        // String url ="https://karyapath-dev.tatastrive.com/sservices-v1/callback/student";
//        System.out.println("printing the messages..."+ callbackentity);
        log.debug("printing current thread{}",Thread.currentThread().getId());
        log.debug("inside the Rabbit listener");
        String messageId = callbackentity.getMessageId();
        String url = callbackentity.getCallbackUrl();
        log.debug("printing the message properties :{}", callbackentity);
        callbackentity.setPayloadType(PayloadType.INT_INV);
        MessagesIntInvPayload messagesIntInvPayload = messagesToIntInvPayloadConverter(callbackentity);
        log.debug("printing the message Id from the message... {}", messageId);
        Boolean status=auditService.updateMessageReceivedTimestamp(messageId,callbackentity);
        if(status  && !url.isEmpty() && !messageId.isEmpty()){
            sendPayloadToExternalURL(callbackentity,url,messagesIntInvPayload,messageId);
        }
        else {
            if(messageId==null)log.error("can not found the message with the message Id, request to external url failed{}",messageId);
            if(url==null || url.isEmpty()) log.error("please check the callback url{}", url);
        }


    }

    private  MessagesIntInvPayload messagesToIntInvPayloadConverter(Messages callbackentity) {
        MessagesIntInvPayload messagesIntInvPayload= new MessagesIntInvPayload();

        messagesIntInvPayload.setHollandcode(callbackentity.getHollandcode());
        messagesIntInvPayload.setReverificationCode(callbackentity.getReverificationCode());
        messagesIntInvPayload.setEngagementId(callbackentity.getEngagementId());
        return messagesIntInvPayload;
    }

    @RabbitListener(queues = "documentQueue")
    public void consumeStudentDocument(Messages callbackentity){
        // String url ="https://karyapath-dev.tatastrive.com/sservices-v1/callback/student";
//        System.out.println("printing the messages..."+ callbackentity);
        log.debug("printing current thread{}",Thread.currentThread().getId());
        log.debug("inside the Rabbit listener");
        String messageId = callbackentity.getMessageId();
        String url = callbackentity.getCallbackUrl();
        log.debug("printing the message properties :{}", callbackentity);
        callbackentity.setPayloadType(PayloadType.DOCUMENT);
        MessagesForDocument messagesForDocument = messageToDocumentPayloadConverter(callbackentity);
        log.debug("printing the message Id from the message... {}", messageId);
        Boolean status=auditService.updateMessageReceivedTimestamp(messageId,callbackentity);
        if(status  && !url.isEmpty() && !messageId.isEmpty()){
            sendPayloadToExternalURL(callbackentity,url,messagesForDocument,messageId);
        }
        else {
            if(messageId==null)log.error("can not found the message with the message Id, request to external url failed{}",messageId);
            if(url==null || url.isEmpty()) log.error("please check the callback url{}", url);
        }


    }

    private MessagesForDocument messageToDocumentPayloadConverter(Messages callbackentity) {
        MessagesForDocument messagesForDocument = new MessagesForDocument();
        messagesForDocument.setEngagementId(callbackentity.getEngagementId());
        messagesForDocument.setSuggestedCourses(callbackentity.getSuggestedCourses());
        messagesForDocument.setPdfDownloadUrl(callbackentity.getPdfDownloadUrl());
        messagesForDocument.setHollandcode(callbackentity.getHollandcode());
        messagesForDocument.setReverificationCode(callbackentity.getReverificationCode());
        return messagesForDocument;
    }


    @RabbitListener(queues = "BasicBeneficiaryQueue")
    public void consumeStudentBasicBeneficiary(Messages callbackentity){
        // String url ="https://karyapath-dev.tatastrive.com/sservices-v1/callback/student";
//        System.out.println("printing the messages..."+ callbackentity);
        log.debug("printing current thread{}",Thread.currentThread().getId());
        log.debug("inside the Rabbit listener");
        String messageId = callbackentity.getMessageId();
        String url = callbackentity.getCallbackUrl();
        log.debug("printing the message properties :{}", callbackentity);
        callbackentity.setPayloadType(PayloadType.BASIC_BENEFICIARY);
        MessagesForBasicBeneficiary messagesForBasicBeneficiary = messageToBasicBeneficiaryPayloadConverter(callbackentity);

        log.debug("printing the message Id from the message... {}", messageId);
        Boolean status=auditService.updateMessageReceivedTimestamp(messageId,callbackentity);
        if(status  && !url.isEmpty() && !messageId.isEmpty()){
            sendPayloadToExternalURL(callbackentity,url,messagesForBasicBeneficiary,messageId);
        }
        else {
            if(messageId==null)log.error("can not found the message with the message Id, request to external url failed{}",messageId);
            if(url==null || url.isEmpty()) log.error("please check the callback url{}", url);
        }


    }

    private  MessagesForBasicBeneficiary messageToBasicBeneficiaryPayloadConverter(Messages callbackentity) {
        MessagesForBasicBeneficiary messagesForBasicBeneficiary = new MessagesForBasicBeneficiary();
        messagesForBasicBeneficiary.setDbUserId(callbackentity.getDbUserId());
        messagesForBasicBeneficiary.setFirstName(callbackentity.getFirstName());
        messagesForBasicBeneficiary.setLastName(callbackentity.getLastName());
        messagesForBasicBeneficiary.setGender(callbackentity.getGender());
        messagesForBasicBeneficiary.setDob(callbackentity.getDob());
        messagesForBasicBeneficiary.setQualification(callbackentity.getQualification());
        messagesForBasicBeneficiary.setMobileNumber(callbackentity.getMobileNumber());
        return messagesForBasicBeneficiary;
    }

    @Async
    @RabbitListener(queues= "deadLetterQueue")
   public void consumeDeadLetterQueue(Messages messages) {
        String callbackUrl = messages.getCallbackUrl();
       String payloadType = String.valueOf(messages.getPayloadType());


        int maxRetries = 3;
        int retryDelayMillis = 10000; // 100 second initial delay
        int attempt;
        for (attempt = 1; attempt <= maxRetries; attempt++) {
            try {
                Object payload;
                switch (payloadType) {
                    case "ADDRESS":
                        payload = messagesToAddressPayloadConverter(messages);
                        break;
                    case "DOCUMENT":
                        payload = messageToDocumentPayloadConverter(messages);
                        break;
                    case "INT_INV":
                        payload = messagesToIntInvPayloadConverter(messages);
                        break;
                    case "BASIC_BENEFICIARY":
                        payload = messageToBasicBeneficiaryPayloadConverter(messages);
                        break;
                    default:
                        throw new IllegalArgumentException("Invalid payload type: " + payloadType);
                }

                ResponseEntity<String> responseEntity = request.callExternalEndpoint(callbackUrl, payload);
                if (responseEntity.getStatusCode().is2xxSuccessful()) {
                    log.info("Data transfer completed: {}", responseEntity.getStatusCode());
                    auditService.updateMessageProcessedTimestamp(messages.getMessageId());
                   auditService.updateResponse(messages.getMessageId(), responseEntity.getBody());
                    // Exit the loop if the call succeeds
                    break;
                } else {
                    log.error("Error sending the data. Retrying... Attempt: {}", attempt);
                    Thread.sleep(retryDelayMillis);
                    retryDelayMillis *= 2; // Exponential backoff
                }
            } catch (Exception e) {
                log.warn("Error sending the data. Retrying... Attempt: {}", attempt);
                log.error("Error message: {}", e.getMessage());
                try {
                    Thread.sleep(retryDelayMillis);
                    retryDelayMillis *= 2; // Exponential backoff
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt(); // Preserve interrupted status
                }
            }
        }

        if (attempt > maxRetries) {
            // All retry attempts failed, send the message to  parking queue
            rabbitTemplate.convertAndSend("parkingQueue", messages);
            log.error("All retry attempts failed. Sending message to  parking queue{}", messages.getMessageId());
        }
    }

    @RabbitListener(queues = "parkingQueue")
    public void consumeParkingQueue(Messages messages) {
        messagesService.saveParkingQueueMessagesToDB(messages);

    }

    private void sendPayloadToExternalURL(Messages callbackentity, String url, Object payload, String messageId) {
        try {
            ResponseEntity<String> responseEntity = request.callExternalEndpoint(url, payload);
            if(responseEntity.getStatusCode().is2xxSuccessful()) {
                auditService.updateMessageProcessedTimestamp(messageId);
                log.info("Data Transfer is successful{}", responseEntity.getStatusCode());
                auditService.updateResponse(messageId,responseEntity.getBody());
            } else {
                log.error("some error occurred while saving data at partner end {}", responseEntity.getStatusCodeValue());
                auditService.updateResponse(messageId, responseEntity.getBody());
                rabbitTemplate.convertAndSend("deadLetterQueue", callbackentity);
            }
        } catch (HttpClientErrorException ex) {
            log.error("Http client error occurred while making the request: {}", ex.getMessage() );
            rabbitTemplate.convertAndSend("deadLetterQueue", callbackentity);
        }
        catch (Exception ex) {
            ex.printStackTrace();
            rabbitTemplate.convertAndSend("deadLetterQueue", callbackentity);
        }
    }
    @Value("${scheduler.interval.delay}")
    @Scheduled(fixedRateString = "${scheduler.interval.delay}")
    private void schedulerForCheckingParkingQueue() {
        long countOfFailedMessaged = repository.countByMessageProcessedStatusFalse();
        List<Messages> messagesFoundFromDb;
        if (countOfFailedMessaged >0) {
            String body = String.format("Attention:%n%n %d records have failed to transfer to our partners.%n%n Regards %n TataSTRIVE", countOfFailedMessaged);

            mailProcessor.sendRecordTransferFailMail("Alert Karyapath Youth Data Transfer failed to partner",body);
        }
        messagesFoundFromDb = messagesService.checkForCallbackURlChanges();

        log.debug("messagesFoundFromDb is not empty{}", messagesFoundFromDb);
        if (!messagesFoundFromDb.isEmpty() ){


            for (Messages message : messagesFoundFromDb){
                String payloadType= String.valueOf(message.getPayloadType());
                log.info("Checking for the message{}", payloadType);
                switch (payloadType){
                    case "ADDRESS":
                        rabbitTemplate.convertAndSend("addressQueue", message);
                        messagesService.updateMessageProcessedStatusAndTimeStamp(message.getId());
                        break;
                    case "DOCUMENT":
                        rabbitTemplate.convertAndSend("documentQueue", message);
                        messagesService.updateMessageProcessedStatusAndTimeStamp(message.getId());
                        break;
                    case "INT_INV":
                        rabbitTemplate.convertAndSend("intInvQueue", message);
                        messagesService.updateMessageProcessedStatusAndTimeStamp(message.getId());
                        break;
                    case "BASIC_BENEFICIARY" :
                        rabbitTemplate.convertAndSend("BasicBeneficiaryQueue", message);
                        messagesService.updateMessageProcessedStatusAndTimeStamp(message.getId());
                        break;
                    default:
                        throw new IllegalArgumentException("unknown message type: " + message);
                }

            }
        }
    }





}

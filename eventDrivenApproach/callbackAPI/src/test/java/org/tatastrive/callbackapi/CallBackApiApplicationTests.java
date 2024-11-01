//package org.tatastrive.callbackapi;
//
//import org.junit.jupiter.api.Test;
//import org.mockito.ArgumentCaptor;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.tatastrive.callbackapi.entity.Messages;
//import org.tatastrive.callbackapi.entity.MessagesForAddress;
//import org.tatastrive.callbackapi.externalServices.RestTemplateRequest;
//import org.tatastrive.callbackapi.messaging.MessageConsumer;
//import org.tatastrive.callbackapi.services.impl.PartnerAuditableService;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.ArgumentMatchers.anyString;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
//@SpringBootTest
//class CallBackApiApplicationTests {
//	@Autowired
//	private MessageConsumer messageConsumer;
//
//	@MockBean
//	private RestTemplateRequest restTemplateRequest;
//
//	@MockBean
//	private PartnerAuditableService partnerAuditableService;
//
//	@MockBean
//	private RabbitTemplate rabbitTemplate;
//
//	@Test
//	void contextLoads() {
//	}
//
////	@Test
//	public void testConsumeDeadLetterQueue() throws InterruptedException {
//		// Given
//		Messages message = new Messages();
//		message.setMessageId("123");
//		message.setCallbackUrl("http://example.com");
//		message.setPayloadType(MessageConsumer.PayloadType.ADDRESS);
//		when(partnerAuditableService.updateMessageProcessedTimestamp(any())).thenReturn(true);
//		when(restTemplateRequest.callExternalEndpoint(anyString(), any())).thenReturn(new ResponseEntity<>("OK", HttpStatus.OK));
//
//		// When
//		messageConsumer.consumeDeadLetterQueue(message);
//
//		// Then
//		ArgumentCaptor<String> urlCaptor = ArgumentCaptor.forClass(String.class);
//		ArgumentCaptor<MessagesForAddress> payloadCaptor = ArgumentCaptor.forClass(MessagesForAddress.class);
//		verify(restTemplateRequest).callExternalEndpoint(urlCaptor.capture(), payloadCaptor.capture());
//		assertEquals("http://example.com", urlCaptor.getValue());
//		assertNotNull(payloadCaptor.getValue());
//	}
//
//}

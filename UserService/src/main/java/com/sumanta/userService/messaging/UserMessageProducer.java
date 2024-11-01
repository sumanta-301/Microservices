package com.sumanta.userService.messaging;

import com.sumanta.userService.dto.ItemMessage;
import com.sumanta.userService.entities.Item;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserMessageProducer {
// private final RabbitTemplate rabbitTemplate;
    private final AmqpTemplate amqpTemplate;

    public UserMessageProducer(AmqpTemplate amqpTemplate) {
        this.amqpTemplate = amqpTemplate;
    }

//    public UserMessageProducer(RabbitTemplate rabbitTemplate) {
//        this.rabbitTemplate = rabbitTemplate;
//    }

    public void sendMessage(ItemMessage item) {
        ItemMessage itemMessage = new ItemMessage();
       String randomUUID=UUID.randomUUID().toString();
        itemMessage.setId(randomUUID);
        itemMessage.setBatchNumber(item.getBatchNumber());
        itemMessage.setBrandName(item.getBrandName());
        itemMessage.setMedicineCategory(item.getMedicineCategory());
        itemMessage.setMedicineCount(item.getMedicineCount());
        itemMessage.setMedicineMrp(item.getMedicineMrp());
        itemMessage.setGenericName(item.getGenericName());
        itemMessage.setRetailerId(item.getRetailerId());
        //rabbitTemplate.convertAndSend("userItemsQueue", itemMessage);
       amqpTemplate.convertAndSend("userItemQueue", itemMessage);
        System.out.println("inside the rabbit mq producer"+ itemMessage);




    }
}

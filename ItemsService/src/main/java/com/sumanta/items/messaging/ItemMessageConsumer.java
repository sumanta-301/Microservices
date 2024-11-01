package com.sumanta.items.messaging;

import com.sumanta.items.dto.ItemMessage;
import com.sumanta.items.service.impl.ItemServiceImpl;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component

public class ItemMessageConsumer {
    private final ItemServiceImpl itemService;

    public ItemMessageConsumer(ItemServiceImpl itemService) {
        this.itemService = itemService;
    }
    @RabbitListener(queues = "userItemQueue")
    public void consumeMessage(ItemMessage itemMessage) {
        System.out.println("inside the rabbit mq consumer");
        itemService.updateItem(itemMessage);
    }


}

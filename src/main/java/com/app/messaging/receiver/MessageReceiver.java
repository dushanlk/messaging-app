package com.app.messaging.receiver;

import com.app.messaging.pojo.Message;
import com.app.messaging.repository.MessageRepository;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

/**
 * Authored by dushan.p@viewqwest.com on 15/10/19.
 * http://dushan.lk
 */
@Component
public class MessageReceiver {

    private final MessageRepository repository;

    private final JmsTemplate jmsTemplate;

    public MessageReceiver(MessageRepository repository, JmsTemplate jmsTemplate) {
        this.repository = repository;
        this.jmsTemplate = jmsTemplate;
    }

    @JmsListener(destination = "cart")
    public void receive(Message message) {

        CompletableFuture.runAsync(() -> {
            System.out.println("Message received" + message);

            Message savedMessage = null;
            try {
                savedMessage = repository.save(message);
                System.out.println("Saved message " + savedMessage);
            } catch (Exception e) {
                System.out.println("Operation failed when saving the message : " + e.getLocalizedMessage());
            }

            if (savedMessage != null) {
                Iterable<Message> messages = repository.findAll();
                System.out.println("---------------- ALL MESSAGES ----------------");
                messages.forEach(System.out::println);
                System.out.println("----------------------------------------------");
            } else {
                System.out.println("Publishing back to the queue.");
                jmsTemplate.convertAndSend("cart", message);
            }
        }).join();
    }
}

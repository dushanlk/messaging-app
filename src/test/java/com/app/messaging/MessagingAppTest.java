package com.app.messaging;

import com.app.messaging.pojo.Message;
import com.app.messaging.repository.MessageRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Authored by dushan.p@viewqwest.com on 15/10/19.
 * http://dushan.lk
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MessagingAppTest {

    @Autowired
    private JmsTemplate jmsTemplate;

    @Autowired
    private MessageRepository repository;

    @Test
    public void test() {
        final var message = new Message(1L, 2L, 3);
        this.jmsTemplate.convertAndSend("cart", message);

        jmsTemplate.setReceiveTimeout(1000);
        final var messages = StreamSupport.stream(repository.findAll().spliterator(), false)
                .collect(Collectors.toList());

        Assert.assertEquals(1, messages.size());
        Assert.assertEquals(message, messages.get(0));
    }
}
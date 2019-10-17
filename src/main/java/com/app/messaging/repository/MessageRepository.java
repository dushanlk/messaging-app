package com.app.messaging.repository;

import com.app.messaging.pojo.Message;
import org.springframework.data.repository.CrudRepository;

/**
 * Authored by dushan.p@viewqwest.com on 16/10/19.
 * http://dushan.lk
 */
public interface MessageRepository extends CrudRepository<Message, Long> {

    @Override
    <S extends Message> S save(S s);
}

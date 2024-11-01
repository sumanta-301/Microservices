package org.tatastrive.callbackapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.tatastrive.callbackapi.entity.Messages;

import java.util.List;

public interface MessagesRepository extends JpaRepository<Messages, Long> {
   List<Messages> findByMessageProcessedStatusFalse();
   long countByMessageProcessedStatusFalse();

}

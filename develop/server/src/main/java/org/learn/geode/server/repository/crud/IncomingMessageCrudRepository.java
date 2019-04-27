package org.learn.geode.server.repository.crud;

import org.learn.geode.server.domain.IncomingMessage;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository(value="incomingMsgCrudRepository")
public interface IncomingMessageCrudRepository extends CrudRepository<IncomingMessage, Long> {

}

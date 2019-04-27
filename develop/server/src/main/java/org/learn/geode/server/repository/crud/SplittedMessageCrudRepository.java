package org.learn.geode.server.repository.crud;

import org.learn.geode.server.domain.SplittedMessage;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository(value="splittedMsgCrudRepository")
public interface SplittedMessageCrudRepository extends CrudRepository<SplittedMessage, Long> {

}

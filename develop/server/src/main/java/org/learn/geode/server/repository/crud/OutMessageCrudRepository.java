package org.learn.geode.server.repository.crud;

import org.learn.geode.server.domain.OutMessage;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository(value="outMsgCrudRepository")
public interface OutMessageCrudRepository extends CrudRepository<OutMessage, Long> {

}

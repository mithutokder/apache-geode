package org.learn.geode.server.repository.crud;

import org.learn.geode.server.domain.SplittedMessageAttribute;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository(value="splittedAttributeCrudRepository")
public interface SplittedMsgAttributecCrudRepository extends CrudRepository<SplittedMessageAttribute, Long> {

}

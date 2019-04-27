package org.learn.geode.server.repository.crud;

import org.learn.geode.server.domain.SplittedOutMessageXref;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository(value="splitOutXrefCrudRepository")
public interface SplittedMsgOutXrefCrudRepository extends CrudRepository<SplittedOutMessageXref, Long> {

}

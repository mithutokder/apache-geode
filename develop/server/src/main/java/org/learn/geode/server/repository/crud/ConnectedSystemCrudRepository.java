package org.learn.geode.server.repository.crud;

import org.learn.geode.server.domain.ConnectedSystem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository(value="connectedSystemCrudRepository")
public interface ConnectedSystemCrudRepository extends CrudRepository<ConnectedSystem, Long> {

}

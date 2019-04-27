package org.learn.geode.server.generator;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import org.learn.geode.server.enums.KeyType;
import org.springframework.stereotype.Service;

@Service
public class DefaultPrimaryGenerator implements PrimaryKeyGenerator {

	Map<KeyType, AtomicLong> pkStore = new HashMap<>();
	
	@Override
	public Long generate(KeyType keyType) {
		return pkStore.get(keyType).incrementAndGet();
	}
	
	@Override
	public void populate(KeyType keyType, AtomicLong maxPk) {
		pkStore.put(keyType, maxPk);
	}

}

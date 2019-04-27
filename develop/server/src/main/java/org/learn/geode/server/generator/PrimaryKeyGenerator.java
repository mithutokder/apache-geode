package org.learn.geode.server.generator;

import java.util.concurrent.atomic.AtomicLong;

import org.learn.geode.server.enums.KeyType;

public interface PrimaryKeyGenerator {

	Long generate(KeyType keyType);

	void populate(KeyType keyType, AtomicLong maxPk);
}

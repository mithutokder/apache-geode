package org.learn.geode.server.domain.loader;

import java.util.List;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicLong;

import javax.persistence.EntityManager;

import org.apache.geode.cache.Region;
import org.learn.geode.common.helper.ResourceLoader;
import org.learn.geode.server.domain.ConnectedSystem;
import org.learn.geode.server.domain.IncomingMessage;
import org.learn.geode.server.domain.OutMessage;
import org.learn.geode.server.domain.SplittedMessage;
import org.learn.geode.server.domain.SplittedMessageAttribute;
import org.learn.geode.server.domain.SplittedOutMessageXref;
import org.learn.geode.server.enums.KeyType;
import org.learn.geode.server.generator.PrimaryKeyGenerator;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class GeodeDataLoader implements BeanPostProcessor {
	
	@Autowired
    private EntityManager entityManager;
	
	@Autowired
	private PrimaryKeyGenerator generator;

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		return bean;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		if (beanName.equals("connected_system") && bean instanceof Region) {
			loadConnectedSystem((Region<Long, ConnectedSystem>)bean);
		} else if (beanName.equals("incoming_msg") && bean instanceof Region) {
			loadIncomingMessage((Region<Long, IncomingMessage>)bean);
		} else if (beanName.equals("out_msg") && bean instanceof Region) {
			loadOutMessage((Region<Long, OutMessage>)bean);
		} else if (beanName.equals("splitted_msg") && bean instanceof Region) {
			loadSplittedMessage((Region<Long, SplittedMessage>)bean);
		} else if (beanName.equals("splitted_attribute") && bean instanceof Region) {
			loadSplitAttr((Region<Long, SplittedMessageAttribute>)bean);
		} else if (beanName.equals("split_out_xref") && bean instanceof Region) {
			loadSplitXrefMessage((Region<Long, SplittedOutMessageXref>)bean);
		} else if (beanName.equals("system_attribute") && bean instanceof Region) {
			loadSystemAttribute((Region<String, String>)bean);
		}
		return bean;
	}

	private void loadSystemAttribute(Region<String, String> bean) {
		Properties prop = ResourceLoader.loadProperties("server.properties");
		prop.entrySet()
		.stream()
		.forEach(e -> bean.put((String)e.getKey(), 
				(String)e.getValue())
				);
	}

	private void loadSplitXrefMessage(Region<Long, SplittedOutMessageXref> bean) {
		List<SplittedOutMessageXref> results = entityManager.createNamedQuery("SplittedOutMessageXref.getAll", SplittedOutMessageXref.class)
				.getResultList();
		results.stream().forEach(i -> bean.put(i.getXrefPk(), i));
		Long maxPk = results.stream().mapToLong(i -> i.getXrefPk()).max().orElse(0L);
		generator.populate(KeyType.SplitOutXref, new AtomicLong(maxPk));
	}

	private void loadSplitAttr(Region<Long, SplittedMessageAttribute> bean) {
		List<SplittedMessageAttribute> results = entityManager.createNamedQuery("SplittedMessageAttribute.getAll", SplittedMessageAttribute.class)
				.getResultList();
		results.stream().forEach(i -> bean.put(i.getSplitAttributePk(), i));
		
		Long maxPk = results.stream().mapToLong(i -> i.getSplitAttributePk()).max().orElse(0L);
		generator.populate(KeyType.SplittedAttribute, new AtomicLong(maxPk));
	}

	private void loadSplittedMessage(Region<Long, SplittedMessage> bean) {
		List<SplittedMessage> results = entityManager.createNamedQuery("SplittedMessage.getAll", SplittedMessage.class)
				.getResultList();
		results.stream().forEach(i -> bean.put(i.getSplittedMessagePk(), i));
		
		Long maxPk = results.stream().mapToLong(i -> i.getSplittedMessagePk()).max().orElse(0L);
		generator.populate(KeyType.SplittedMessage, new AtomicLong(maxPk));
	}

	private void loadConnectedSystem(Region<Long, ConnectedSystem> bean) {
		List<ConnectedSystem> results = entityManager.createNamedQuery("ConnectedSystem.getAll", ConnectedSystem.class)
				.getResultList();
		results.stream().forEach(i -> bean.put(i.getConnectedSystemPk(), i));
		
		Long maxPk = results.stream().mapToLong(i -> i.getConnectedSystemPk()).max().orElse(0L);
		generator.populate(KeyType.ConnectedSystem, new AtomicLong(maxPk));
	}
	
	private void loadIncomingMessage(Region<Long, IncomingMessage> bean) {
		List<IncomingMessage> results = entityManager.createNamedQuery("IncomingMessage.getAll", IncomingMessage.class)
				.getResultList();
		results.stream().forEach(i -> bean.put(i.getIncomingMessagePk(), i));
		
		Long maxPk = results.stream().mapToLong(i -> i.getIncomingMessagePk()).max().orElse(0L);
		generator.populate(KeyType.IncomingMessage, new AtomicLong(maxPk));
	}
	
	private void loadOutMessage(Region<Long, OutMessage> bean) {
		List<OutMessage> results = entityManager.createNamedQuery("OutMessage.getAll", OutMessage.class)
				.getResultList();
		results.stream().forEach(i -> bean.put(i.getOutMessagePk(), i));
		
		Long maxPk = results.stream().mapToLong(i -> i.getOutMessagePk()).max().orElse(0L);
		generator.populate(KeyType.SplitOutXref, new AtomicLong(maxPk));
	}

}

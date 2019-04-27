package com.org.learn.geode.console.route;

import java.util.Date;

import org.apache.camel.spring.SpringRouteBuilder;
import org.learn.geode.common.dto.ConnectedSystemDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.org.learn.geode.console.function.ConnectedSystemFunction;
import com.org.learn.geode.console.parser.ConnectedSystemMessageParser;

@Component("FileLoaderRouteBuilder")
public class FileLoaderRouteBuilder extends SpringRouteBuilder {
	
	@Autowired(required=false)
	private ConnectedSystemMessageParser parser;
	
	@Autowired(required=false)
	private ConnectedSystemFunction function;

	@Override
	public void configure() throws Exception {
		
		if(parser == null) {
			parser = lookup(ConnectedSystemMessageParser.class);
		}
		if(function == null) {
			function = lookup(ConnectedSystemFunction.class);
		}
		onException(Exception.class)
		.process(e -> {
			System.out.println("Exception occured");
			e.getException().printStackTrace();
		});
		
		from("file://home/geode/systems")
		.split(body().tokenize(System.lineSeparator())).parallelProcessing()
		.process(ex -> {
			String body = ex.getIn().getBody(String.class);
			ConnectedSystemDto parsedContent = parser.parse(body);
			function.enterConnectedSystem(parsedContent);
			System.out.println("Success : " + new Date());
		})
		.end();
		
	}

}

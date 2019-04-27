package org.learn.geode.server;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.learn.geode.server.domain.ConnectedSystem;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class AppTest {
	
	private String s = "{\"createdBy\":\"mithut\",\"creationDate\":1521925237690,\"updatedBy\":\"mithut\",\"updatedDate\":1521925237690,\"versionNo\":null,\"connectedSystemPk\":null,\"connectedSystemId\":\"EIGER\",\"t\":null}\n";

	@Test
	public void test() throws Exception {
		Map<String, Integer> fieldCount = new HashMap<>();
		fieldCount.put("Trade", 0);
        fieldCount.put("Split", 0);
        fieldCount.put("Dividend", 0);
        
        Integer value = fieldCount.get("Trade");
        fieldCount.put("Trade", value+1);
        System.out.println(fieldCount.get("Trade"));
	}
}

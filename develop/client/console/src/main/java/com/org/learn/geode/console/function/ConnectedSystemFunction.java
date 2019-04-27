package com.org.learn.geode.console.function;

import java.util.List;
import org.learn.geode.common.dto.ConnectedSystemDto;
import org.springframework.data.gemfire.function.annotation.FunctionId;
import org.springframework.data.gemfire.function.annotation.OnRegion;
import org.springframework.stereotype.Component;

@Component
@OnRegion(region = "connected_system")
public interface ConnectedSystemFunction {

	@FunctionId("ConnectedSystemEntry")
	List<ConnectedSystemDto> enterConnectedSystem(ConnectedSystemDto connectedSystemDto);
}

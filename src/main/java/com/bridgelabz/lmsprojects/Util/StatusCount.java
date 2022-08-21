package com.bridgelabz.lmsprojects.Util;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StatusCount {
	
	private long inPregressCount;
	private long completedCount;
	private long droppedCount;
	private long remappedCount;

}

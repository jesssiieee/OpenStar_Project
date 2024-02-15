package com.openstar.batch;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class TestBatch {
	
	// 특정 시간에 실행시킬 메소드들
	
	@Scheduled(cron = "0 */1 * * * *") // 초, 분, 시간, 일, * <= 매 1분마다
	public void task() {
		log.info("###### batch 수행 ######");
	}

}

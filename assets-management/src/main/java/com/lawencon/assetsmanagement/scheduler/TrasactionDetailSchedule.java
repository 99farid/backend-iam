package com.lawencon.assetsmanagement.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.lawencon.assetsmanagement.service.DetailTransactionsOutService;

@Component
public class TrasactionDetailSchedule {
	
	@Autowired
	private DetailTransactionsOutService detailService;
	
	@Scheduled(fixedDelay = 60000)
	public void dueDateReminder() throws Exception{
		detailService.sendReportDueDate();
	}
}

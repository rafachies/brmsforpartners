package com.redhat.brmsdemo.brms.workitem;

import java.util.Random;

import org.drools.ClassObjectFilter;
import org.drools.process.instance.WorkItemHandler;
import org.drools.runtime.StatefulKnowledgeSession;
import org.drools.runtime.process.WorkItem;
import org.drools.runtime.process.WorkItemManager;

import com.redhat.brmsdemo.model.Customer;

public class SCPCWorkItemHandler implements WorkItemHandler{

	private StatefulKnowledgeSession knowledgeSession;
	
	public SCPCWorkItemHandler(StatefulKnowledgeSession knowledgeSession) {
		this.knowledgeSession = knowledgeSession;
	}
	
	@Override
	public void abortWorkItem(WorkItem arg0, WorkItemManager workItemManager) {}

	@Override
	public void executeWorkItem(WorkItem workItem, WorkItemManager workItemManager) {
		int score = new Random().nextInt(100);
		Customer customer = (Customer) knowledgeSession.getObjects(new ClassObjectFilter(Customer.class)).toArray()[0];
		customer.setScpcScore(score);
		knowledgeSession.update(knowledgeSession.getFactHandle(customer), customer);
		workItemManager.completeWorkItem(workItem.getId(), null);
	}

}

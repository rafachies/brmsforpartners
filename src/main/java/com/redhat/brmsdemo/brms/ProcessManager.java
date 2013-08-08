package com.redhat.brmsdemo.brms;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.drools.runtime.StatefulKnowledgeSession;
import org.drools.runtime.process.ProcessInstance;
import org.jbpm.persistence.processinstance.ProcessInstanceInfo;
import org.jbpm.process.audit.JPAWorkingMemoryDbLogger;
import org.jbpm.process.instance.impl.demo.SystemOutWorkItemHandler;
import org.jbpm.task.service.hornetq.CommandBasedHornetQWSHumanTaskHandler;

public class ProcessManager implements Serializable {

	private static final long serialVersionUID = -5759149019263724733L;

	@Inject private KnowledgeBaseManager knowledgeBaseManager;
	@Inject private HumanTaskManager userTaskClient;

	public ProcessInstance startProcess(String processId) throws Exception {
		StatefulKnowledgeSession knowledgeSession = knowledgeBaseManager.getKnowledgeSession();
		configureWorkItemHandlers(knowledgeSession);
		new JPAWorkingMemoryDbLogger(knowledgeSession);
		ProcessInstance processInstance = createProcess(knowledgeSession, processId);
		knowledgeSession.startProcessInstance(processInstance.getId());
		return processInstance;
	}

	public List<ProcessInstanceInfo> getProcessInstances() throws Exception {
		return null; //TODO: handle this
	}
	
	private void configureWorkItemHandlers(StatefulKnowledgeSession knowledgeSession) {
		CommandBasedHornetQWSHumanTaskHandler humanTaskHandler = new CommandBasedHornetQWSHumanTaskHandler(knowledgeSession);
		humanTaskHandler.setClient(userTaskClient.getTaskClient());
		knowledgeSession.getWorkItemManager().registerWorkItemHandler("Human Task", humanTaskHandler);
		knowledgeSession.getWorkItemManager().registerWorkItemHandler("Log", new SystemOutWorkItemHandler());
	}


	private ProcessInstance createProcess(StatefulKnowledgeSession knowledgeSession, String processId) throws Exception {
		Map<String, Object> processParameters = new HashMap<String, Object>();
		ProcessInstance processInstance = knowledgeSession.createProcessInstance(processId, processParameters);
		return processInstance;
	}
	
}

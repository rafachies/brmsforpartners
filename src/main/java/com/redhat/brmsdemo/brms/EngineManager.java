package com.redhat.brmsdemo.brms;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.drools.runtime.StatefulKnowledgeSession;
import org.drools.runtime.process.ProcessInstance;

public class EngineManager implements Serializable {

	private static final long serialVersionUID = -5759149019263724733L;

	@Inject private ProcessStore processStore;
	@Inject private KnowledgeBaseManager knowledgeBaseManager;

	public ProcessInstance startProcess(String processId, Map<String, Object> processVariables) throws Exception {
		StatefulKnowledgeSession knowledgeSession = knowledgeBaseManager.getKnowledgeSession();
		ProcessInstance processInstance = createProcess(knowledgeSession, processId, processVariables);
		knowledgeSession.startProcessInstance(processInstance.getId());
		return processInstance;
	}
	
	public void insertFact(Object fact) throws Exception {
		StatefulKnowledgeSession knowledgeSession = knowledgeBaseManager.getKnowledgeSession();
		knowledgeSession.insert(fact);
	}
	
	

	public List<ProcessInstance> getProcessInstances() throws Exception {
		return processStore.getProcesses();
	}

	private ProcessInstance createProcess(StatefulKnowledgeSession knowledgeSession, String processId, Map<String, Object> processVariables) throws Exception {
		ProcessInstance processInstance = knowledgeSession.createProcessInstance(processId, processVariables);
		processStore.addProcess(processInstance);
		return processInstance;
	}
	
}

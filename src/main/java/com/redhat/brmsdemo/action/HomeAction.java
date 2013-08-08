package com.redhat.brmsdemo.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.jbpm.persistence.processinstance.ProcessInstanceInfo;
import org.jbpm.task.query.TaskSummary;

import com.redhat.brmsdemo.bean.Process;
import com.redhat.brmsdemo.bean.Task;
import com.redhat.brmsdemo.brms.HumanTaskManager;
import com.redhat.brmsdemo.brms.ProcessManager;

@Named("homeAction")
@ApplicationScoped
public class HomeAction {

	@Inject private ProcessManager processManager;
	@Inject private HumanTaskManager humanTaskManager;
	
	private String processId;
	private List<Task> tasks;
	private List<Process> processes;

	public List<Task> getTasks() throws Exception {
		tasks = new ArrayList<Task>();
		List<TaskSummary> taskSummaryList = humanTaskManager.getTasks("admin");
		for (TaskSummary taskSummary : taskSummaryList) {
			Task task = new Task();
			task.setId(taskSummary.getId());
			task.setName(taskSummary.getName());
			task.setProcessInstanceId(taskSummary.getProcessInstanceId());
			tasks.add(task);
		}
		return tasks;
	}
	
	public List<Process> getProcesses() throws Exception {
		Collection<ProcessInstanceInfo> processInstances = processManager.getProcessInstances();
		processes = new ArrayList<Process>();
		for (ProcessInstanceInfo instance : processInstances) {
			Process process = new Process();
			process.setProcessId(instance.getProcessId());
			process.setInstanceId(instance.getId());
			process.setStatus(instance.getState());
			processes.add(process);
		}
		return processes; 
	}

	public String createProcess() throws Exception {
		processManager.startProcess(processId);
		return "home";
	}

	public String getProcessId() {
		return processId;
	}

	public void setProcessId(String processId) {
		this.processId = processId;
	}
}

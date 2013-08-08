package com.redhat.brmsdemo.brms;

import java.util.ArrayList;
import java.util.List;

import org.drools.runtime.process.ProcessInstance;

public class ProcessStore {

	private List<ProcessInstance> processes = new ArrayList<ProcessInstance>();
	
	public void addProcess(ProcessInstance processInstance) {
		processes.add(processInstance);
	}

	public List<ProcessInstance> getProcesses() {
		return processes;
	}

	public void setProcesses(List<ProcessInstance> processes) {
		this.processes = processes;
	}
}

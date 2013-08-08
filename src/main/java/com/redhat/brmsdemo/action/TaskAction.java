package com.redhat.brmsdemo.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.jbpm.task.query.TaskSummary;

import com.redhat.brmsdemo.bean.FormParameter;
import com.redhat.brmsdemo.bean.Task;
import com.redhat.brmsdemo.brms.HumanTaskManager;
import com.redhat.brmsdemo.model.Customer;

@ApplicationScoped
@Named("taskAction")
public class TaskAction {
	
	@Inject private HumanTaskManager humanTaskManager;

	private List<FormParameter> formParameters;
	private List<Task> tasks = new ArrayList<Task>();
	private Long taskId;
	private String actor = new String();
	private Customer customer;
	
	
	public void refreshActor() throws Exception {
		getTasks();
	}
	
	public List<Task> getTasks() throws Exception {
		tasks = new ArrayList<Task>();
		List<TaskSummary> taskSummaryList = humanTaskManager.getTasks(actor);
		for (TaskSummary taskSummary : taskSummaryList) {
			Task task = new Task();
			task.setId(taskSummary.getId());
			task.setName(taskSummary.getName());
			task.setProcessInstanceId(taskSummary.getProcessInstanceId());
			tasks.add(task);
		}
		return tasks;
	}
	
	public String completeTask(boolean approved) throws Exception {
		Map<String, Object> outputData = new HashMap<String, Object>();
		customer.setApproved(approved);
		outputData.put("customerOuput", customer);
		humanTaskManager.endTask(taskId, actor, outputData);
		FacesContext.getCurrentInstance().renderResponse();
		return "process?faces-redirect=true";
	}
	
	public String openTask(Long taskId) throws Exception {
		this.taskId = taskId;
		humanTaskManager.startTask(taskId, actor);
		Map<String, Object> taskInput = humanTaskManager.getDataInput(taskId);
		customer = (Customer) taskInput.get("customerInput");
		return "taskForm?faces-redirect=true";
	}
	
	public Long getTaskId() {
		return taskId;
	}

	public List<FormParameter> getFormParameters() {
		return formParameters;
	}

	public String getActor() {
		return actor;
	}

	public void setActor(String actor) {
		this.actor = actor;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
}

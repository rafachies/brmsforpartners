package com.redhat.brmsdemo.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.jbpm.task.query.TaskSummary;

import com.redhat.brmsdemo.bean.FormParameter;
import com.redhat.brmsdemo.bean.Task;
import com.redhat.brmsdemo.brms.HumanTaskManager;

@ApplicationScoped
@Named("taskAction")
public class TaskAction {
	
	@Inject private HumanTaskManager humanTaskManager;

	private List<FormParameter> formParameters;
	private List<Task> tasks = new ArrayList<Task>();
	private Long taskId;
	private String actor = new String();
	
	
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
	
	public String completeTask() throws Exception {
		Map<String, Object> outputData = new HashMap<String, Object>();
		for (FormParameter formParameter : formParameters) {
			outputData.put(formParameter.getName(), formParameter.getValue());
		}
		humanTaskManager.endTask(taskId, "admin", outputData);
		FacesContext.getCurrentInstance().renderResponse();
		return "home?faces-redirect=true";
	}
	
	public String openTask(Long taskId, String taskName) throws Exception {
		this.taskId = taskId;
		humanTaskManager.startTask(taskId, actor);
		String url = "http://localhost:8080/jboss-brms/org.drools.guvnor.Guvnor/webdav/packages/redhat/" + taskName + "-taskform.ftl";
		String formHtml = sendGet(url);
		Pattern pattern = Pattern.compile("<input name=\"(.*?)\" type=\"text\" class=\"textbox\"");
		Matcher matcher = pattern.matcher(formHtml);
		formParameters = new ArrayList<FormParameter>();
		while(matcher.find()){
			FormParameter formParameter = new FormParameter();
			formParameter.setName(matcher.group(1));
			formParameters.add(formParameter);
		}
		return "taskForm?faces-redirect=true";
	}
	
	private String sendGet(String url) throws Exception {
		DefaultHttpClient httpClient = new DefaultHttpClient();
		httpClient.getCredentialsProvider().setCredentials(AuthScope.ANY, new UsernamePasswordCredentials("admin", "admin"));
		String responseString = null;
		HttpGet httpPost = new HttpGet(url);
		HttpResponse response = httpClient.execute(httpPost);
		responseString = EntityUtils.toString(response.getEntity());
		EntityUtils.consume(response.getEntity());
		return responseString;
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
}

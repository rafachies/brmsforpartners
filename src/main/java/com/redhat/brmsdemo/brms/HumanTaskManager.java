package com.redhat.brmsdemo.brms;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;
import javax.inject.Singleton;

import org.drools.SystemEventListenerFactory;
import org.jbpm.task.AccessType;
import org.jbpm.task.Content;
import org.jbpm.task.Task;
import org.jbpm.task.TaskData;
import org.jbpm.task.query.TaskSummary;
import org.jbpm.task.service.ContentData;
import org.jbpm.task.service.TaskClient;
import org.jbpm.task.service.hornetq.HornetQTaskClientConnector;
import org.jbpm.task.service.hornetq.HornetQTaskClientHandler;
import org.jbpm.task.service.responsehandlers.BlockingGetContentResponseHandler;
import org.jbpm.task.service.responsehandlers.BlockingGetTaskResponseHandler;
import org.jbpm.task.service.responsehandlers.BlockingTaskOperationResponseHandler;
import org.jbpm.task.service.responsehandlers.BlockingTaskSummaryResponseHandler;

@Singleton
public class HumanTaskManager implements Serializable {

	private static final long serialVersionUID = 1364946303229891269L;

	@Inject private Logger logger;

	private TaskClient taskClient;

	@PostConstruct
	public void onConstruct() {
		try {
			logger.info("Creating a new task client ...");
			taskClient = new TaskClient(new HornetQTaskClientConnector("tasksQueue/client" + UUID.randomUUID().toString(), new HornetQTaskClientHandler(SystemEventListenerFactory.getSystemEventListener())));;
			taskClient.connect("localhost", 5153);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public TaskClient getTaskClient() {
		return taskClient;
	}

	public TaskSummary getNextTask(String username) throws Exception {
		BlockingTaskSummaryResponseHandler taskSummaryHandler = new BlockingTaskSummaryResponseHandler();
		taskClient.getTasksAssignedAsPotentialOwner(username, "en-UK", taskSummaryHandler);
		List<TaskSummary> tasks = taskSummaryHandler.getResults();
		checkTasks(tasks);
		return tasks.get(0);
	}

	public List<TaskSummary> getTasks(String username) throws Exception {
		BlockingTaskSummaryResponseHandler taskSummaryHandler = new BlockingTaskSummaryResponseHandler();
		taskClient.getTasksAssignedAsPotentialOwner(username, "en-UK", taskSummaryHandler);
		List<TaskSummary> result = taskSummaryHandler.getResults();
		return result;
	}

	public void startTask(Long taskId, String user) throws Exception {
		BlockingTaskOperationResponseHandler taskOperationHandler = new BlockingTaskOperationResponseHandler();
		taskClient.start(taskId, user, taskOperationHandler);
	}

	public void endTask(Long taskId, String username, Map<String, Object> outputMap) {
		try {
			BlockingTaskOperationResponseHandler taskOperationHandler = new BlockingTaskOperationResponseHandler();
			ContentData contentData = createOutputData(outputMap);
			taskClient.complete(taskId, username, contentData, taskOperationHandler);
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Error while finishing human task", e);
		}
	}

	private void checkTasks(List<TaskSummary> tasks) {
		if (tasks.size() < 1) {
			throw new RuntimeException("No tasks found");
		}
	}

	private ContentData createOutputData(Map<String, Object> outputMap) {
		try {
			ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
			objectOutputStream.writeObject(outputMap);
			objectOutputStream.close();
			ContentData contentData = new ContentData();
			contentData.setContent(byteArrayOutputStream.toByteArray());
			contentData.setAccessType(AccessType.Inline);
			return contentData;
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Error creating task output data", e);
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public Map<String, Object> getDataInput(Long taskId) {
		try {
			BlockingGetTaskResponseHandler handler = new BlockingGetTaskResponseHandler();
			taskClient.getTask(taskId, handler);
			Task task = handler.getTask();
			TaskData taskData = task.getTaskData();
			BlockingGetContentResponseHandler contentHandler = new BlockingGetContentResponseHandler();
			taskClient.getContent(taskData.getDocumentContentId(), contentHandler);
			Content content = contentHandler.getContent();
			ByteArrayInputStream bais = new ByteArrayInputStream(content.getContent());
			ObjectInputStream objectInputStream = new ObjectInputStream(bais);
			return (Map<String, Object>) objectInputStream.readObject();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	@PreDestroy
	public void onDestriy() {
		try {
			taskClient.disconnect();
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Error while disconnecting from Human Task Server", e);
		}
	}
	
	
}

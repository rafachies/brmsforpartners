package com.redhat.brmsdemo.brms;

import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.drools.KnowledgeBase;
import org.drools.WorkingMemory;
import org.drools.agent.KnowledgeAgent;
import org.drools.agent.KnowledgeAgentConfiguration;
import org.drools.agent.KnowledgeAgentFactory;
import org.drools.command.impl.CommandBasedStatefulKnowledgeSession;
import org.drools.command.impl.KnowledgeCommandContext;
import org.drools.event.AgendaEventListener;
import org.drools.event.DefaultAgendaEventListener;
import org.drools.event.RuleFlowGroupActivatedEvent;
import org.drools.impl.StatefulKnowledgeSessionImpl;
import org.drools.io.ResourceChangeScannerConfiguration;
import org.drools.io.ResourceFactory;
import org.drools.runtime.StatefulKnowledgeSession;

@Singleton
public class KnowledgeBaseManager implements Serializable {

	private static final long serialVersionUID = 2080952334714512871L;

	@Inject private Logger logger;

	private KnowledgeBase knowledgeBase;
	private KnowledgeAgent knowledgeAgent;
	private StatefulKnowledgeSession knowledgeSession;


	public StatefulKnowledgeSession getKnowledgeSession() {
		if (knowledgeSession == null) {
			createSession();
		}
		return knowledgeSession;
	}

	private void createSession() {
		try {
			createKnowledgeBase();
			knowledgeSession = knowledgeBase.newStatefulKnowledgeSession();
			configureRulesFirePolicy(knowledgeSession);
		} catch (Exception e) {
			logger.log(Level.WARNING, "Erro ao criar Knowledge Session", e);
			throw new RuntimeException(e);
		}
	}

	private void createKnowledgeBase() throws MalformedURLException {
		URL changesetUrl = Thread.currentThread().getContextClassLoader().getResource("change-set.xml");
		KnowledgeAgentConfiguration conf = KnowledgeAgentFactory.newKnowledgeAgentConfiguration();
		conf.setProperty("drools.agent.newInstance", "false");
		conf.setProperty( "drools.resource.scanner.interval", "10" );
		knowledgeAgent = KnowledgeAgentFactory.newKnowledgeAgent( "MyAgent", conf );
		knowledgeAgent.applyChangeSet( ResourceFactory.newUrlResource(changesetUrl));
		knowledgeBase = knowledgeAgent.getKnowledgeBase();
		startScannerService();
	}

	private void startScannerService() {
		ResourceChangeScannerConfiguration configuration = ResourceFactory.getResourceChangeScannerService().newResourceChangeScannerConfiguration();
		configuration.setProperty( "drools.resource.scanner.interval", "10" );
		ResourceFactory.getResourceChangeScannerService().configure( configuration );
		ResourceFactory.getResourceChangeNotifierService().start();
		ResourceFactory.getResourceChangeScannerService().start();
	}

	private void configureRulesFirePolicy(StatefulKnowledgeSession knowledgeSession) {
		final AgendaEventListener agendaEventListener = new DefaultAgendaEventListener() {
			public void afterRuleFlowGroupActivated(RuleFlowGroupActivatedEvent event, WorkingMemory workingMemory) {
				workingMemory.fireAllRules();
			}
		} ;
		((StatefulKnowledgeSessionImpl)  ((KnowledgeCommandContext) ((CommandBasedStatefulKnowledgeSession) knowledgeSession)
				.getCommandService().getContext()).getStatefulKnowledgesession() )
				.session.addEventListener(agendaEventListener) ;
	}
}

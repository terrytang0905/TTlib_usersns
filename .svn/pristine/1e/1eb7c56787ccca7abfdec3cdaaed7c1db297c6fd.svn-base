package com.newroad.user.sns.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ReloadListener implements ServletContextListener {

	private static final Logger LOG = LoggerFactory.getLogger(ReloadListener.class);
	
//	private MongoManager mongoManager;
	
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		LOG.debug("Servlet context initializing...");
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		LOG.debug("Servlet context destroying...");
//		WebApplicationContext springContext = WebApplicationContextUtils.getWebApplicationContext(sce.getServletContext());
		//1.shut down quartz jobs
//		try{
//			Scheduler  supernoteQuartzScheduler = (Scheduler)springContext.getBean("supernote_quartz_scheduler");
//			if(supernoteQuartzScheduler.isStarted()){
//				supernoteQuartzScheduler.shutdown();
//				Thread.sleep(1000);
//				LOG.info("supernote_quartz_scheduler SHUT DOWN!");
//			}
//		} catch(Exception e) {
//			LOG.error("Catch an exception when shut down supernote_quartz_scheduler", e);
//		}
		
		//2.stop amq
//		PooledConnectionFactory jmsFactory = (PooledConnectionFactory)springContext.getBean("jmsFactory");
//		jmsFactory.stop();
//		DefaultThreadPools.getDefaultTaskRunnerFactory().shutdown();
		
		//3.close mongodb
//	    mongoManager = (MongoManager)springContext.getBean("mongoManager");
//	    mongoManager.closeDB();
//	    LOG.info("MONGO DB CLOSED!");
	}

}

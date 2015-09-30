package com.newroad.user.sns.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @info  : 事件 （用于挂载到各个事件点） 
 * @author: xiangping_yu
 * @data  : 2013-11-5
 * @since : 1.5
 */
public interface Event<R> {
	
  Logger log = LoggerFactory.getLogger(Event.class);
	
	R doEvent(Object ...q);
}

/**
 * Copyright 2008-2019 Dahai Cao
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.cloudibpm.runtime.engine;

import java.io.Serializable;

import org.apache.log4j.Logger;
import org.slf4j.LoggerFactory;

import com.cloudibpm.core.runtime.event.Event;
import com.cloudibpm.runtime.cache.LogCache;
import com.cloudibpm.runtime.server.SaaSServer;


/**
 * @author Dahai Cao created on 2012-06-24, last updated on 2018-03-23
 *
 */
public class LogEngine implements Runnable, Serializable {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -5603900595075829538L;
	protected static Logger logger = Logger.getLogger(LogEngine.class.getName());
	// 0: shutdown; 1: startup
	private int command = 0;
	private String id = "";

	/**
	 * 
	 */
	public LogEngine() {
		ch.qos.logback.classic.LoggerContext loggerContext = (ch.qos.logback.classic.LoggerContext) LoggerFactory
				.getILoggerFactory();
		ch.qos.logback.classic.Logger rootLogger = loggerContext.getLogger("org.mongodb.driver");
		rootLogger.setLevel(ch.qos.logback.classic.Level.OFF);
	}

	/**
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		try {
			//System.out.println("Log engine started");
			if (command == 1) {
				while (!Thread.interrupted()) {
					if (SaaSServer.getInstance().getLogcache().count() >= LogCache.BATCH_SAVE_COUNTING) {
						Event[] eventlogs = SaaSServer.getInstance().getLogcache().fetchLogs();
						//System.out.println("Log engine starts to work.");
						//RuntimeEventBlo.getInstance().saveLogs(eventlogs);
						//System.out.println("Batch saving " + eventlogs.length + " logs.");
						for (Event event : eventlogs) {
							System.out.println(event);
						}
						//System.out.println("Batch saved " + eventlogs.length + " logs.");
					} else {
						Thread.sleep(2000);
						//System.out.println("There were " + SaaSServer.getInstance().getLogcache().count() + " logs.");
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @return the command
	 */
	public int getCommand() {
		return command;
	}

	/**
	 * @param command
	 *            the command to set
	 */
	public void setCommand(int command) {
		this.command = command;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}

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
package com.cloudibpm.core.repository;

import org.apache.log4j.BasicConfigurator;
//import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.support.DefaultTransactionDefinition;

/**
 * @author Dahai Cao created on 20180321 for storing the runtime data.
 *
 */
public abstract class ExecuteNoSQLObject {

	protected static Logger logger = Logger.getLogger(ExecuteNoSQLObject.class.getName());
	protected long spendtime = 0;
	protected DataSourceTransactionManager transactionManager = TransactionManagerFactory.getTansactionManager();
	protected DefaultTransactionDefinition transactionDefinition;
	protected MongoTemplate nosqlTemplate = DBDataSourceFactory.getNoSqlDataSource();
	// the following codes is to prevent MongoDB from printing cluster update
	// logs to console.
	//static ch.qos.logback.classic.Logger root = (ch.qos.logback.classic.Logger) LoggerFactory
			//.getLogger(ch.qos.logback.classic.Logger.ROOT_LOGGER_NAME);
	static {
		//root.setLevel(ch.qos.logback.classic.Level.INFO);
//		
//		ch.qos.logback.classic.LoggerContext loggerContext = (ch.qos.logback.classic.LoggerContext) LoggerFactory.getILoggerFactory();
//		ch.qos.logback.classic.Logger rootLogger = loggerContext.getLogger("org.mongodb.driver");
//		rootLogger.setLevel(ch.qos.logback.classic.Level.OFF); 
		
//		LogManager.getLogger("org.mongodb.driver.connection").setLevel(org.apache.log4j.Level.OFF);
//		LogManager.getLogger("org.mongodb.driver.management").setLevel(org.apache.log4j.Level.OFF);
//		LogManager.getLogger("org.mongodb.driver.cluster").setLevel(org.apache.log4j.Level.OFF);
//		LogManager.getLogger("org.mongodb.driver.protocol.insert").setLevel(org.apache.log4j.Level.OFF);
//		LogManager.getLogger("org.mongodb.driver.protocol.query").setLevel(org.apache.log4j.Level.OFF);
//		LogManager.getLogger("org.mongodb.driver.protocol.update").setLevel(org.apache.log4j.Level.OFF);
	}
	/**
	 * 
	 */
	public ExecuteNoSQLObject() {
		BasicConfigurator.configure();
		transactionDefinition = new DefaultTransactionDefinition();
		transactionDefinition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		// jdbcTemplate = new MongoTemplate(new SimpleMongoDbFactory(new
		// MongoClient("localhost", 27017), "test"));
	}

}

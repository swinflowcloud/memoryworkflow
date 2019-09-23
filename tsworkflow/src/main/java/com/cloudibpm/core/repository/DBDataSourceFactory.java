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

import javax.sql.DataSource;

import org.springframework.context.ApplicationContext;
import org.springframework.data.mongodb.core.MongoTemplate;

/**
 * CSC database data source factory.
 */
public class DBDataSourceFactory {

	private static DataSource ds = initDataSource();
	private static MongoTemplate nosqlds = initNoSqlDataSource();

	/**
	 * Returns the single instance of CSC database data source.
	 * 
	 * @return The single instance of CSC database data source
	 */
	public final static DataSource getDataSource() {
		return ds;
	}

	/**
	 * Initializes the single instance of CSC database data source.
	 */
	private static DataSource initDataSource() {
		ApplicationContext ctx = ApplicationContextFactory.getApplicationContext();
		return (DataSource) ctx.getBean("RepositoryDBDataSource");
	}

	public final static MongoTemplate getNoSqlDataSource() {
		return nosqlds;
	}

	private static MongoTemplate initNoSqlDataSource() {
		ApplicationContext ctx = ApplicationContextFactory.getApplicationContext();
		return (MongoTemplate) ctx.getBean("mongoTemplate");
	}
}

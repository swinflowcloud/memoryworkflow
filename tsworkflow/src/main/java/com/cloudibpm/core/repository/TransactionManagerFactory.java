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

import org.springframework.jdbc.datasource.DataSourceTransactionManager;

/**
 * @author dcao
 * 
 */
public class TransactionManagerFactory {
	private static DataSourceTransactionManager transactionManager = new DataSourceTransactionManager(
			DBDataSourceFactory.getDataSource());

	public static DataSourceTransactionManager getTansactionManager() {
		return transactionManager;
	}
}

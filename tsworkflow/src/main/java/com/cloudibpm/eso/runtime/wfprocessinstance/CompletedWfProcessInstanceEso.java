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
package com.cloudibpm.eso.runtime.wfprocessinstance;

import com.cloudibpm.core.repository.ExecuteNoSQLObject;
import com.cloudibpm.core.runtime.wfprocess.WfProcessInstance;
import org.apache.log4j.Logger;

/**
 * @author Dahai Cao created on 2018-03-21
 *         https://segmentfault.com/a/1190000005829384
 *         https://blog.csdn.net/congcong68/article/details/47183209
 */
public class CompletedWfProcessInstanceEso extends ExecuteNoSQLObject {
	private final String collectionname = "process_instance";
	/**
	 * 
	 */
	public CompletedWfProcessInstanceEso() {
		super();
		logger = Logger.getLogger(CompletedWfProcessInstanceEso.class.getName());
	}

	public void insert(WfProcessInstance instanceString) throws Exception {
		nosqlTemplate.save(instanceString, collectionname);
	}

	public WfProcessInstance query(String piid) {
		return nosqlTemplate.findById(piid, WfProcessInstance.class, collectionname);
	}
}
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
package com.cloudibpm.blo.runtime.wfprocessinstance;

import com.cloudibpm.core.repository.BusinessLogicObject;
import com.cloudibpm.core.runtime.util.WfProcessInstanceUncloner;
import com.cloudibpm.core.runtime.wfprocess.WfProcessInstance;
import com.cloudibpm.eso.runtime.wfprocessinstance.CompletedWfProcessInstanceEso;

import javax.transaction.Transactional;

/**
 * 未使用
 */
@Transactional
public class ProcessInstanceBlo extends BusinessLogicObject {

	private final static ProcessInstanceBlo instance = new ProcessInstanceBlo();

	private ProcessInstanceBlo() {
	}

	public static ProcessInstanceBlo getInstance() {
		return instance;
	}

	public WfProcessInstance getProcessInstance(String piid) throws Exception {
        CompletedWfProcessInstanceEso piEso = new CompletedWfProcessInstanceEso();
		WfProcessInstance instance = piEso.query(piid);
		return WfProcessInstanceUncloner.unclone(instance);
    }
}

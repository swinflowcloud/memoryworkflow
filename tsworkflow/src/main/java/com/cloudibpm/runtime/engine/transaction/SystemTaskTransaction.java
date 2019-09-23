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
package com.cloudibpm.runtime.engine.transaction;

import com.cloudibpm.core.buildtime.wfprocess.task.SystemTask;
import com.cloudibpm.core.data.Constant;
import com.cloudibpm.core.data.variable.DataVariable;
import com.cloudibpm.core.runtime.wfprocess.WfProcessInstance;
import com.cloudibpm.core.runtime.wfprocess.task.SystemTaskInstance;

/**
 * 
 * @author Dahai Cao created on 2011-08-20, last updated at 19:02 on 2018-08-01
 */
public class SystemTaskTransaction extends AbstractTaskTransaction<SystemTask> {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -2997592497429187374L;
	private Object originalValue = null;

	public SystemTaskTransaction(SystemTask task, WfProcessInstance process) {
		super(task, process);
	}

	@Override
	public void begin() throws Exception {
		SystemTaskInstance invokeTask = (SystemTaskInstance) taskInstance;
		DataVariable dv = invokeTask.getReturnObject();
		invokeTask.setStartTime(System.currentTimeMillis());
		if (dv != null) {
			Object o = dv.getValue();
			if (o instanceof Constant) {
				originalValue = ((Constant) o).clone(this.getWfProcessInstance());
			}
		}
	}

	/**
	 * 系统自动任务
	 */
	@Override
	public void commit() throws Exception {
		System.out.println(taskInstance.getName() + " is running.....");
		SystemTaskInstance invokeTask = (SystemTaskInstance) taskInstance;
//		WebAppService ws = MicroServiceBlo.getInstance().getAppService(invokeTask.getAppServiceId());
//		if (ws.getMethodName().equals("GET")) {
//			RTHttpExecutor.doGetAction(ws, invokeTask, this.getWfProcessInstance());
//		} else if (ws.getMethodName().equals("POST")) {
//			RTHttpExecutor.doPostAction(ws, invokeTask, this.getWfProcessInstance());
//		}
		invokeTask.setEndTime(System.currentTimeMillis());
	}

	@Override
	public void rollback() throws Exception {
		SystemTaskInstance invokeTask = (SystemTaskInstance) taskInstance;
		DataVariable dv = invokeTask.getReturnObject();
		if (dv != null) {
			Object o = dv.getValue();
			if (o instanceof Constant) {
				dv.setValue(originalValue);
			}
		}
	}
}
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
package com.cloudibpm.core.runtime.event;

import com.cloudibpm.core.buildtime.wfprocess.task.AbstractTask;
import com.cloudibpm.core.runtime.wfprocess.WfProcessInstance;

/**
 * 
 * @author cdh
 * @date 2017-10-03 last updated
 */
public class TaskStatusChangedEvent extends WorkflowEvent {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -5287325756967609391L;

	/**
	 * 
	 */
	public TaskStatusChangedEvent() {
		super();
	}

	/**
	 * Task status changed event.
	 * 
	 * @param type
	 * @param eventSource
	 * @param eventSourceParent
	 */
	public TaskStatusChangedEvent(int type, AbstractTask eventSource, WfProcessInstance sourceOwner) {
		super();
		this.setType(type);
		this.setEventSourceId(eventSource.getId());
		this.setStatus(eventSource.getStatus());
		String eventName = sourceOwner.getName() + "(" + sourceOwner.getVersion() + ")" + "." + eventSource.getName();
		this.setName(eventName);
		if (type == TASK_ENABLED)
			eventName = eventName + " enabled.";
		else if (type == TASK_RUNNED)
			eventName = eventName + " running.";
		else if (type == TASK_COMPLETED)
			eventName = eventName + " completed.";
		else if (type == TASK_UNUSED)
			eventName = eventName + " unused.";
		else if (type == TASK_EXCEPTION)
			eventName = eventName + " has exception.";
		else if (type == TASK_SKIPPED)
			eventName = eventName + " skipped.";
		else if (type == TASK_TERMINATED)
			eventName = eventName + " terminated.";
		this.setDescription(eventName);
		this.setParent(sourceOwner.getId());
		this.setCurrOwner(eventSource.getCurrOwner());
		this.setOwner(eventSource.getOwner());
	}

	/**
	 * 
	 * @param type
	 * @param eventSource
	 * @param cause
	 * @param eventSourceParent
	 */
	public TaskStatusChangedEvent(int type, AbstractTask eventSource, String cause, WfProcessInstance sourceOwner) {
		super();
		this.setType(type);
		this.setEventSourceId(eventSource.getId());
		this.setName(sourceOwner.getName() + "(" + sourceOwner.getVersion() + ")" + "." + eventSource.getName());
		this.setStatus(eventSource.getStatus());
		this.setDescription(this.getName() + "'s status is changed by " + cause);
		this.setParent(sourceOwner.getId());
		this.setCurrOwner(eventSource.getCurrOwner());
		this.setOwner(eventSource.getOwner());
	}
}

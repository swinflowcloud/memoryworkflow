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

import com.cloudibpm.core.buildtime.wfprocess.Transition;
import com.cloudibpm.core.buildtime.wfprocess.task.AbstractTask;
import com.cloudibpm.core.runtime.wfprocess.WfProcessInstance;

/**
 * @author cdh
 * 
 */
public class TransitionStatusChangedEvent extends WorkflowEvent {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 4810651986863879799L;

	/**
	 * 
	 */
	public TransitionStatusChangedEvent() {
		super();
	}

	/**
	 * Transition status changed event.
	 * 
	 * @param type
	 * @param eventSource
	 * @param description
	 */
	public TransitionStatusChangedEvent(int type, Transition eventSource, WfProcessInstance eventOwner) {
		super();
		this.setType(type);
		this.setEventSourceId(eventSource.getId());
		this.setStatus(eventSource.getStatus());
		String eventName = eventOwner.getName() + "(" + eventOwner.getVersion() + ")" + "."
				+ ((AbstractTask) eventSource.getSource()).getName() + "->" + eventSource.getName() + "->"
				+ ((AbstractTask) eventSource.getTarget()).getName();
		this.setName(eventName);
		if (type == TRANSITION_ENABLED)
			eventName = eventName + " enabled.";
		else if (type == TRANSITION_COMPLETED)
			eventName = eventName + " completed.";
		else if (type == TRANSITION_UNUSED)
			eventName = eventName + " unused.";
		else if (type == TRANSITION_EXCEPTION)
			eventName = eventName + " has exception!";
		this.setDescription(eventName);
		this.setParent(eventOwner.getId());
		this.setCurrOwner(eventOwner.getCurrOwner());
		this.setOwner(eventSource.getOwner());
	}
}

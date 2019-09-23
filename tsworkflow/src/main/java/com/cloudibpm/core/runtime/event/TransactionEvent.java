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
 * @author Dahai Cao
 * @date 2017-10-03 last updated.
 */
public class TransactionEvent extends WorkflowEvent {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 9077397354637182053L;

	/**
	 * 
	 */
	public TransactionEvent() {
		super();
	}

	/**
	 * The transaction is only for task instance execution.
	 */
	public TransactionEvent(int type, WfProcessInstance sourceOwner, AbstractTask eventSource) {
		super();
		this.setType(TRANSACTION_COMPLETED);
		this.setEventSourceId(eventSource.getId());
		this.setStatus(eventSource.getStatus());
		String eventName = sourceOwner.getName() + "(" + sourceOwner.getVersion() + ")" + "'s " + eventSource.getName();
		this.setName(eventName);
		if (type == TRANSACTION_STARTED)
			eventName = eventName + " transaction started.";
		else if (type == TRANSACTION_COMPLETED)
			eventName = eventName + " transaction completed.";
		else if (type == TRANSACTION_TRMINATED)
			eventName = eventName + " transaction terminated.";
		else if (type == TRANSACTION_ROLLBACK)
			eventName = eventName + " transaction rollback.";
		this.setDescription(eventName);
		this.setParent(eventSource.getId());
		this.setOwner(eventSource.getOwner());
	}

	// public TransactionEvent(int type, WfProcess eventSource) {
	// super();
	// this.setType(TRANSACTION_COMPLETED);
	// this.setEventSourceId(eventSource.getId());
	// this.setName(eventSource.getName());
	// this.setStatus(eventSource.getStatus());
	// String description = "";
	// if (type == TRANSACTION_STARTED)
	// description = eventSource.getName() + " transaction started.";
	// else if (type == TRANSACTION_COMPLETED)
	// description = eventSource.getName() + " transaction completed.";
	// else if (type == TRANSACTION_TRMINATED)
	// description = eventSource.getName() + " transaction terminated.";
	// else if (type == TRANSACTION_ROLLBACK)
	// description = eventSource.getName() + " transaction rollback.";
	// this.setDescription(description);
	// this.setParent(eventSource.getId());
	// this.setOwner(eventSource.getOwner());
	// }

}

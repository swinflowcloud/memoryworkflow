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
package com.cloudibpm.core.buildtime.wfprocess.task;

public class EmailReceivingTask extends AbstractTask {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -7776640433784092154L;

	public EmailReceivingTask() {
		setName("Email Receiving Task");
		setClasstypename(this.getClass().getSimpleName());
	}

	public EmailReceivingTask(String id) {
		super(id);
		setName("Email Receiving Task");
		setClasstypename(this.getClass().getSimpleName());
	}

}

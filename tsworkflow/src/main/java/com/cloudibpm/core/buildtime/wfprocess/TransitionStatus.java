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
package com.cloudibpm.core.buildtime.wfprocess;

public interface TransitionStatus {
	/**
	 * This status of transition is default status. that is, it is unused
	 * status.
	 */
	public static final int DEFAULT = 0;

	/**
	 * This status of transition is enabled
	 */
	public static final int ENABLED = 1;

	/**
	 * This status of transition is executed, that is, it has completed
	 * transition.
	 */
	public static final int COMPLETED = 2;

	/**
	 * This status of transition is unused, that is, it did not be use during
	 * process execution.
	 */
	public static final int UNUSED = 3;

	/**
	 * This status of transition is unused, that is, it did not be use during
	 * process execution.
	 */
	public static final int EXCEPTION = 4;

	public void setStatus(int status);

	public int getStatus();

	public String getStatusName();
}

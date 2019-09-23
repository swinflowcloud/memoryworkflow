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

public interface WfProcessStatus {
	/**
	 * This is a default status of a process or a process instance
	 */
	public static final int DEFAULT = 0;
	/**
	 * Process is unlocked. This property is used to support team work for
	 * process modeling.
	 */
	public static final int UNLOCKED = 1;
	/**
	 * Process is locked. This property is used to support team work for process
	 * modeling.
	 */
	public static final int LOCKED = 2;
	/**
	 * Process is released to web
	 */
	public static final int RELEASED = 3;
	/**
	 * Process is instantiated and launched.
	 */
	public static final int LAUNCHED = 4;
	/**
	 * Process is running
	 */
	public static final int RUNNING = 5;
	/**
	 * Process is suspended
	 */
	public static final int SUSPENDED = 6;
	/**
	 * Process is terminated
	 */
	public static final int TERMINATED = 7;
	/**
	 * Process completed
	 */
	public static final int COMPLETED = 8;

	public void setStatus(int status);

	public int getStatus();
	
	public String toNameWithStatus();
	
	public String getStatusName();
}

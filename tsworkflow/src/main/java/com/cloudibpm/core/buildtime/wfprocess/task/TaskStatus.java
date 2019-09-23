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

public interface TaskStatus {
	/**
	 * This property describes default status of task.
	 */
	public static final int DEFAULT = 0;
	/**
	 * This property describes enabled status of task. This status will be set
	 * after task initiation.
	 */
	public static final int ENABLED = 1;
	/**
	 * This property describes running status of task. This status will be set
	 * when task is running.
	 */
	public static final int RUNNING = 2;
	/**
	 * This property describes completed status of task. This status will be set
	 * after task completed.
	 */
	public static final int COMPLETED = 3;
	/**
	 * This property describes unused status of task. This status will be set if
	 * task does not be executed when business process launches.
	 */
	public static final int UNUSED = 4;
	/**
	 * This property describes exception status of task. This status will be set
	 * when exception occurs during task executing.
	 */
	public static final int EXCEPTION = 5;
	/**
	 * This property describes skipped status of task. This status will be set
	 * when current task is skipped by case administrator.
	 */
	public static final int SKIPPED = 6;
	/**
	 * This property describes terminated status of task. This status will be
	 * set when current task is terminated forcibly by case administrator.
	 */
	public static final int TERMINATED = 7;
	
	public void setStatus(int status);

	public int getStatus();
	
	public String getStatusName();
}

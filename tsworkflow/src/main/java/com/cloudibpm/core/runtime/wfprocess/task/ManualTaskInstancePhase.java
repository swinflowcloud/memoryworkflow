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
package com.cloudibpm.core.runtime.wfprocess.task;

/**
 * @author Dahai Cao created on 20180313 Manual task instance submission phases:
 *         <UL>
 *         <li>-1: by default;</li>
 *         <li>0: wait for fetching;</li>
 *         <li>1: fetched but not submit yet;</li>
 *         <li>2: submitted;</li>
 *         <li>3: returned;</li>
 *         </UL>
 *
 */
public interface ManualTaskInstancePhase {
	/**
	 * This property describes initial phase of task instance.
	 */
	int DEFAULT = -1;
	int WAIT_FOR_FETCHING = 0;
	int FETCHED_BUT_NOT_SUBMIT = 1;
	int SUBMITTED = 2;
	int RETURNED = 3;
}

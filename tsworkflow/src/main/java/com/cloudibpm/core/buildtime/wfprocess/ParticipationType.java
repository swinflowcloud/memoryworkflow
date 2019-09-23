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

import java.io.Serializable;

/**
 * @author Dahai Cao created on 2018-03-15
 *
 */
public interface ParticipationType extends Serializable {
	/**
	 * This is a default status of a process or a process instance
	 */
	public static final int NO_PARTICIPANT_APP = 0;
	/**
	 * Process is unlocked. This property is used to support team work for
	 * process modeling.
	 */
	public static final int SINGLE_PARTICIPANT_APP = 1;
	/**
	 * Process is locked. This property is used to support team work for process
	 * modeling.
	 */
	public static final int MULTIPLE_PARTICIPANT_APP = 2;
}

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
 * work assignment priority:
 * <UL>
 * <li>-1: by default;</li>
 * <li>0: normal; (low priority or general priority)</li>
 * <li>1: important; (higher priority)</li>
 * <li>2: urgent; (high priority)</li>
 * </UL>
 * 
 * @author Dahai Cao created on 2018-03-15
 */
public interface ManualTaskInstancePriority {

	public static final int DEFAULT = -1;
	
	public static final int NORMAL = 0;
	
	public static final int IMPORTANT = 1;
	
	public static final int URGENT = 2;
}

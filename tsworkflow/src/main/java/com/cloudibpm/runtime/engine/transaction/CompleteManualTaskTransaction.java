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
package com.cloudibpm.runtime.engine.transaction;

import com.cloudibpm.core.runtime.wfprocess.task.ManualTaskInstance;
import com.cloudibpm.runtime.engine.PEngine;

/**
 * @author Caodahai created on 2016-11-05, last updated on 2018-03-07
 *
 */
public class CompleteManualTaskTransaction extends AbstractTaskTransaction<ManualTaskInstance> {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -1925583170755963684L;
	// private String tempId = null;
//	private PEngine pengine = null;

	public CompleteManualTaskTransaction(ManualTaskInstance task, PEngine pengine) {
		super(task, pengine.getInstance());
		//this.pengine = pengine;
	}

	@Override
	public void begin() throws Exception {
	}

	@Override
	public void commit() throws Exception {
		//System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>11");
		taskInstance.setEndTime(System.currentTimeMillis());
	}

	@Override
	public void rollback() throws Exception {

	}

}

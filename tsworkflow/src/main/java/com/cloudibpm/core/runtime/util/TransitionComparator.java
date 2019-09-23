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
package com.cloudibpm.core.runtime.util;

import java.util.Comparator;

import com.cloudibpm.core.buildtime.wfprocess.Transition;

/**
 * @author cdh
 * @version 1.0.0
 */
public class TransitionComparator implements Comparator<Transition> {

	/**
	 * @return a negative integer, zero, or a positive integer as the first
	 *         argument is less than, equal to, or greater than the second.
	 */
	@Override
	public int compare(Transition arg0, Transition arg1) {
		return arg0.getOrderNumber() - arg1.getOrderNumber();
	}

}

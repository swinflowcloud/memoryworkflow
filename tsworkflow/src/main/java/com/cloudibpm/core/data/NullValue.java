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

package com.cloudibpm.core.data;

import com.cloudibpm.core.WorkflowEntity;

/**
 * 
 * @author Dahai Cao created on 2011-08-11
 * @date 2017-10-10 last updated.
 */
public class NullValue extends WorkflowEntity {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 2076002430478997100L;

	/**
	 * Constructor with a null variable.
	 */
	public NullValue() {
		setId("null");
		setName("null");
	}
	
	public Object clone() {
		return new NullValue();
	}

	/**
	 * This name can be "null" or "NULL" or "Null" or "空", etc.
	 * 
	 * @param name
	 */
	public NullValue(String name) {
		setId("null");
		setName(name);
	}

	/**
	 * Returns a string of this null value. The string format is
	 * <p>
	 * N@null@null
	 * </p>
	 * <p>
	 * Note: this string is used to store the object into repository.
	 * </p>
	 * 
	 * @author Dahai CAO
	 * @date 25/03/2011 9:30:15 PM
	 * @return
	 */
	public String toExpressionString() {
		return "N@null@null";//$NON-NLS-1$
	}

	/**
	 * 
	 * @author Dahai CAO
	 * @date 25/03/2011 9:39:31 PM
	 * @return
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return "空值";
	}
}

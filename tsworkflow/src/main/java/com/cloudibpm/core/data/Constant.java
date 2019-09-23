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
import com.cloudibpm.core.buildtime.wfprocess.WfProcess;

/**
 * This class can represent all kinds of constants, such as String,
 * 
 * @author Dahai Cao created on 2011-09-11
 * @date lastupdated on 2017-11-27
 */
public class Constant extends WorkflowEntity {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 766936052256900580L;
	private String value = "";
	private String datatype = "Constant";

	/**
	 * 
	 */
	public Constant() {
	}

	public Object clone(WfProcess owner) {
		return new Constant();
	}
	
	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}

	/**
	 * Serializes this constant to a string for storage.
	 * 
	 * @return
	 */
	public String toExpressionString() {
		return "C@constant@constant"; //$NON-NLS-1$
	}

	/**
	 * Parse <code>constantstring</code> to set the properties to this constant.
	 * 
	 * @param constantstring,
	 *            String
	 */
	public void parseString(String constantstring) {
	}

	/**
	 * 
	 */
	public String toString() {
		return this.value;
	}

	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @param value
	 *            the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * @return the data type
	 */
	public String getDatatype() {
		return datatype;
	}

	/**
	 * @param datatype
	 *            the data type to set
	 */
	public void setDatatype(String datatype) {
		this.datatype = datatype;
	}

}

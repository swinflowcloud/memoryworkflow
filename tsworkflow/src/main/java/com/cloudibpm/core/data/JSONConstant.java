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

import com.cloudibpm.core.buildtime.wfprocess.WfProcess;
import com.cloudibpm.core.util.encode.Base64Util;

/**
 * @author Dahai Cao created on 2017-11-27
 *
 */
public class JSONConstant extends Constant {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1178570725811294443L;

	/**
	 * 
	 */
	public JSONConstant() {
		this.setName("空JSON对象");
		this.setValue("");
		this.setDatatype(DataType.JSON);
	}

	@Override
	public Object clone(WfProcess owner) {
		JSONConstant d = new JSONConstant();
		d.setName(this.getName());
		d.setValue(this.getValue());
		return d;
	};

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof JSONConstant) {
			if ((this.getValue() == null && ((JSONConstant) obj).getValue() == null)
					|| (this.getValue() != null && this.getValue().equals(((JSONConstant) obj).getValue()))) {
				return true;
			}
		} else if (obj instanceof String) {// it is used to recognize Expression
			if (this.getValue() != null && this.getValue().equals(obj)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public String toExpressionString() {
		return "C@" + this.getDatatype() + "@" + Base64Util.encode(this.getValue());
	}

	@Override
	public void parseString(String str) {
		String[] ary = str.split("@");
		this.setDatatype(ary[1]);
		if (ary.length > 2)
			this.setValue(Base64Util.decode(ary[2]));
	};

	public String toString() {
		if (this.getValue() != null && this.getValue() != "")
			return this.getValue();
		return this.getName();
	};

}

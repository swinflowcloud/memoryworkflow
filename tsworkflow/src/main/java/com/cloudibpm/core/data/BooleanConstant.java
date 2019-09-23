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

/**
 * 
 * @author Dahai Cao created on 2017-11-27
 */
public class BooleanConstant extends Constant {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -905368186341673938L;

	/**
	 * BooleanConstant
	 */
	public BooleanConstant() {
		this.setValue("false");
		this.setDatatype(DataType.BOOLEAN);
	}

	public BooleanConstant(String value) {
		this.setValue(value);
		this.setDatatype(DataType.BOOLEAN);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof BooleanConstant) {
			if ((this.getValue() == null && ((BooleanConstant) obj).getValue() == null) || (this.getValue() != null
					&& this.getValue().toLowerCase().equals(((BooleanConstant) obj).getValue().toLowerCase()))) {
				return true;
			}
		} else if (obj instanceof String) {// it is used to recognize Expression
			if (this.getValue() != null && this.getValue().toLowerCase().equals(obj)) {
				return true;
			}
		} else if (obj instanceof Boolean) {
			if (this.getValue() != null && this.getValue().toLowerCase().equals(((Boolean) obj).toString())) {
				return true;
			}
		}
		return false;
	}

	@Override
	public Object clone(WfProcess owner) {
		BooleanConstant d = new BooleanConstant();
		d.setValue(this.getValue());
		return d;
	}

	@Override
	public String toExpressionString() {
		return "C@" + this.getDatatype() + "@" + this.getValue();
	};

	@Override
	public void parseString(String str) {
		String[] ary = str.split("@");
		this.setDatatype(ary[1]);
		if (ary.length > 2)
			this.setValue(ary[2]);
	};

	public String toString() {
		return this.getValue().toLowerCase();
	}

}

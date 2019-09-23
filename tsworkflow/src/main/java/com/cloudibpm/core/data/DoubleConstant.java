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
 * @author Dahai Cao created on 2017-11-27
 *
 */
public class DoubleConstant extends Constant implements Comparable<Constant> {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 5823925672504444973L;

	/**
	 * DoubleConstant
	 */
	public DoubleConstant() {
		this.setValue("");
		this.setDatatype(DataType.DOUBLE);
	}

	public DoubleConstant(String val) {
		this.setValue(val);
		this.setDatatype(DataType.DOUBLE);
	}

	@Override
	public Object clone(WfProcess owner) {
		DoubleConstant d = new DoubleConstant();
		d.setValue(this.getValue());
		return d;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof DoubleConstant) {
			if ((this.getValue() == null && ((DoubleConstant) obj).getValue() == null)
					|| (this.getValue() != null && this.getValue().equals(((DoubleConstant) obj).getValue()))) {
				return true;
			}
		} else if (obj instanceof String) {// it is used to recognize Expression
			if (this.getValue() != null && this.getValue().equals(obj)) {
				return true;
			}
		} else if (obj instanceof Integer) {
			if (this.getValue() != null) {
				try {
					if (Double.parseDouble(this.getValue()) == (Integer) obj) {
						return true;
					} else
						return false;
				} catch (Exception e) {
					return false;
				}
			}
		} else if (obj instanceof Double) {
			if (this.getValue() != null) {
				try {
					if (Double.parseDouble(this.getValue()) == (Double) obj) {
						return true;
					} else
						return false;
				} catch (Exception e) {
					return false;
				}
			}
		} else if (obj instanceof Float) {
			if (this.getValue() != null) {
				try {
					if (Double.parseDouble(this.getValue()) == (Float) obj) {
						return true;
					} else
						return false;
				} catch (Exception e) {
					return false;
				}
			}
		}
		return false;
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
		return this.getValue();
	}

	@Override
	public int compareTo(Constant o) {
		return 0;
	}
}
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
 * @author dcao
 * 
 */
public class Operator extends WorkflowEntity {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -6909251772687414001L;
	private String symbol = null;

	/**
	 * 
	 */
	public Operator() {
	}

	/**
	 * 
	 */
	public Operator(String operator) {
		this();
		setId(operator);
		setName(operator);
		setSymbol(operator);
	}

	public Object clone() {
		Operator op = new Operator(this.symbol);
		return op;
	}

	public String toExpressionString() {
		return toString();
	}

	public String toString() {
		return getSymbol();
	}

	/**
	 * @return the symbol
	 */
	public String getSymbol() {
		return symbol;
	}

	/**
	 * @param symbol
	 *            the symbol to set
	 */
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

}

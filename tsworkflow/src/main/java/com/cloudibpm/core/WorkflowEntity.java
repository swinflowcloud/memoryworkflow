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
package com.cloudibpm.core;

import java.io.Serializable;

/**
 * @author Dahai Cao
 */
public class WorkflowEntity implements Cloneable, Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -4596730357618650257L;
	private String id = null;
	private String name = null;
	private String owner = null;
	private String currOwner = null;

	/**
	 * 
	 */
	public WorkflowEntity() {
		super();
	}

	/**
	 * 
	 * @param id
	 *            String
	 */
	public WorkflowEntity(String id) {
		this();
		this.id = id;
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (!(obj instanceof WorkflowEntity))
			return false;
		if (this.getId() != null && ((WorkflowEntity) obj).getId() != null) {
			if (this.getId().equals(((WorkflowEntity) obj).getId()))
				return true;
			else
				return false;
		}
		return super.equals(obj);

	}

	/**
	 * 
	 */
	@Override
	public Object clone() throws CloneNotSupportedException {
		WorkflowEntity o = null;
		try {
			o = (WorkflowEntity) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return o;
	}

	/**
	 * 
	 */
	public String getId() {
		return this.id;
	}

	/**
	 * 
	 */
	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getOwner() {
		return this.owner;
	}

	/**
	 * @return the currOwner
	 */
	public String getCurrOwner() {
		return currOwner;
	}

	/**
	 * @param currOwner
	 *            the currOwner to set
	 */
	public void setCurrOwner(String currOwner) {
		this.currOwner = currOwner;
	}

	public String toString() {
		return getName();
	}

}
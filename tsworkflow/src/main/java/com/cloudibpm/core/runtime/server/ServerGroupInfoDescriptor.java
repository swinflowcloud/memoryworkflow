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
package com.cloudibpm.core.runtime.server;

import java.util.Date;

import com.cloudibpm.core.TreeNode;

/**
 * @author Dahai Cao created on 2018-02-05
 *
 */
public class ServerGroupInfoDescriptor extends TreeNode {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -3525565453006803635L;
	/** 注册时间 */
	private Date createDatetime = null;
	/** 最后更新 */
	private Date Lastupdate = null;

	/**
	 * 
	 */
	public ServerGroupInfoDescriptor() {
	}

	/**
	 * @param id
	 */
	public ServerGroupInfoDescriptor(String id) {
		super(id);
	}

	/**
	 * @return the createDatetime
	 */
	public Date getCreateDatetime() {
		return createDatetime;
	}

	/**
	 * @param createDatetime
	 *            the createDatetime to set
	 */
	public void setCreateDatetime(Date createDatetime) {
		this.createDatetime = createDatetime;
	}

	/**
	 * @return the lastupdate
	 */
	public Date getLastupdate() {
		return Lastupdate;
	}

	/**
	 * @param lastupdate
	 *            the lastupdate to set
	 */
	public void setLastupdate(Date lastupdate) {
		Lastupdate = lastupdate;
	}

}

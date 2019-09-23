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
package com.cloudibpm.core.runtime.workitem;

import com.cloudibpm.core.Page;

public class WorkitemInfoDescriptorPage extends Page {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 5721994383674855493L;
	private WorkitemInfoDescriptor[] pageEntities = new WorkitemInfoDescriptor[0];

	public WorkitemInfoDescriptorPage() {
	}

	public WorkitemInfoDescriptorPage(int pageNo, int pageSize) {
		super(pageNo, pageSize);
	}

	/**
	 * @return the pageEntities
	 */
	public WorkitemInfoDescriptor[] getPageEntities() {
		return pageEntities;
	}

	/**
	 * @param pageEntities
	 *            the pageEntities to set
	 */
	public void setPageEntities(WorkitemInfoDescriptor[] pageEntities) {
		this.pageEntities = pageEntities;
	}

}

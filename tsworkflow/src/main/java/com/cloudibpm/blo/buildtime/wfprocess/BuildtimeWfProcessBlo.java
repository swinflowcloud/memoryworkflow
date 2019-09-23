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
package com.cloudibpm.blo.buildtime.wfprocess;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import com.cloudibpm.blo.buildtime.id.NewBuildtimeEntityIdAssignerBlo;
import com.cloudibpm.core.TreeNode;
import com.cloudibpm.core.WorkflowEntity;
import com.cloudibpm.core.buildtime.util.json.WfProcess2JSON;
import com.cloudibpm.core.buildtime.util.json.WfProcessJSONParser;
import com.cloudibpm.core.buildtime.wfprocess.WfProcess;
import com.cloudibpm.core.data.expression.ExpressionParser;
import com.cloudibpm.core.repository.BusinessLogicObject;
import com.cloudibpm.eso.buildtime.wfprocess.BuildtimeWfProcessEso;

/**
 * @author Caodahai
 * @version 1.0
 */
public class BuildtimeWfProcessBlo extends BusinessLogicObject {
	private BuildtimeWfProcessEso processEso;
	private NewBuildtimeEntityIdAssignerBlo newBuildtimeEntityIdAssignerBlo;

	public BuildtimeWfProcessBlo() {
		this.processEso = new BuildtimeWfProcessEso();
		this.newBuildtimeEntityIdAssignerBlo = new NewBuildtimeEntityIdAssignerBlo();
	}

	/**
	 * Returns all workflow processes in current repository. Dahai updated on
	 * 20170808
	 *
	 * @param parent
	 * @param owner
	 * @return
	 * @throws Exception
	 */
	public WfProcess[] getProcesses(TreeNode parent, WorkflowEntity owner) throws Exception {
		List<WfProcess> procRos = processEso.queryAll(parent.getId(), owner.getId());
		return procRos.toArray(new WfProcess[procRos.size()]);
	}

	/**
	 * Return a process through specified <code>id</code>.
	 *
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public String getProcessContent(String id) throws Exception {
		return processEso.queryProcessContentByPK(id);
	}

	public WfProcess getProcessById(String id) throws Exception {
		return processEso.queryByPK(id);
	}

	/**
	 * Arithmetic: delete all the old tasks and old transitions of business process.
	 * Save business tasks and transitions of business process. Update time stamp of
	 * business process.
	 *
	 * @param process BusinessProcess
	 * @throws Exception
	 */

	public String saveWfProcess(WfProcess process) throws Exception {
		processEso.update(process);
		return "1";
	}

	public String[] getAllProcesses(String ownerId) throws Exception {
		List<String> procRos = processEso.queryAllOfOwner(ownerId);
		return procRos.toArray(new String[procRos.size()]);
	}
	
	public WfProcess[] getAllProcesses() throws Exception {
		List<WfProcess> procRos = processEso.queryAll();
		return procRos.toArray(new WfProcess[procRos.size()]);
	}

	/**
	 * Create new workflow process definition.
	 *
	 * @param process BusinessProcess
	 * @throws SQLException
	 */
	public void createNewWfProcess(WfProcess process) throws Exception {
		processEso.insert(process);
	}

	/**
	 * Save a business process that is drafted.
	 *
	 * @param process draft version business process object.
	 * @throws Exception
	 */

	public void updateProcessName(WfProcess process) throws Exception {
		processEso.updateName(process.getId(), process.getName(), process.getProcessContent(), process.getLastupdate());
	}

	public void moveWfProcess(String pid, String parent, String content) throws Exception {
		processEso.updateParent(pid, parent, content, new Date());

	}

	/**
	 * Delete a wfprocess.
	 *
	 * @param processId
	 * @throws Exception
	 */
	public void deleteWfProcess(String processId) throws Exception {
		processEso.delete(processId);
	}

	/**
	 * Delete multiple wfprocess.
	 *
	 * @param processIds
	 * @throws Exception
	 */
	public void deleteWfProcesses(String[] processIds) throws Exception {
		if (processIds != null && processIds.length > 0) {
			for (String id : processIds) {
				processEso.delete(id);
			}
		}
	}

	public void copyNewWfProcess(WfProcess process) throws SQLException {
		processEso.insert(process);
	}

	public WfProcess copyWfProcess(String pid) throws Exception {
		WfProcess p = processEso.queryByPK(pid);
		WfProcess proc = WfProcessJSONParser.parseWfProcess(p.getProcessContent());
		proc.setName(p.getName() + "_副本");
		ExpressionParser.parseExpressions(proc);
		// assign new ID and change process ID.
		newBuildtimeEntityIdAssignerBlo.assignNewEntityId(proc);
		// AssignOwnerIDUtil.changeCurrOwner(proc, proc.getId());
		String processContent = WfProcess2JSON.toPJSON(proc);
		proc.setProcessContent(processContent);
		processEso.insert(proc);
		return proc;
	}
}

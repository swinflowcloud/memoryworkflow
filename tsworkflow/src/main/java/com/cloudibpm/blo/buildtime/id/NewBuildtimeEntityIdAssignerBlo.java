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
package com.cloudibpm.blo.buildtime.id;

import org.json.JSONObject;

import com.cloudibpm.core.TreeNode;
import com.cloudibpm.core.WorkflowEntity;
import com.cloudibpm.core.buildtime.wfprocess.Transition;
import com.cloudibpm.core.buildtime.wfprocess.WfProcess;
import com.cloudibpm.core.buildtime.wfprocess.task.AbstractTask;
import com.cloudibpm.core.buildtime.wfprocess.task.AssignTask;
import com.cloudibpm.core.buildtime.wfprocess.task.EmailReceivingTask;
import com.cloudibpm.core.buildtime.wfprocess.task.EmailSendingTask;
import com.cloudibpm.core.buildtime.wfprocess.task.EndPoint;
import com.cloudibpm.core.buildtime.wfprocess.task.ManualTask;
import com.cloudibpm.core.buildtime.wfprocess.task.SMSReceivingTask;
import com.cloudibpm.core.buildtime.wfprocess.task.SMSSendingTask;
import com.cloudibpm.core.buildtime.wfprocess.task.StartPoint;
import com.cloudibpm.core.buildtime.wfprocess.task.SubprocessPoint;
import com.cloudibpm.core.buildtime.wfprocess.task.SystemTask;
import com.cloudibpm.core.buildtime.wfprocess.task.WaitTask;
import com.cloudibpm.core.data.variable.AccessibleVariable;
import com.cloudibpm.core.data.variable.DataVariable;

public class NewBuildtimeEntityIdAssignerBlo {
	
	private final BuildtimeIDGenerator buildtimeIDGenerator;

	public NewBuildtimeEntityIdAssignerBlo() {
		this.buildtimeIDGenerator = new BuildtimeIDGenerator();
	}

	/**
	 * Assign a new build time entity id to <code>parent</code> node and its
	 * children.
	 * 
	 * @param process
	 * @throws Exception
	 */
	public void assignNewEntityId(WfProcess process) throws Exception {
		assignChildrenNewEntityId(process, process);
		for (WorkflowEntity task : process.getChildren()) {
			if (task instanceof AbstractTask) {
				if (((AbstractTask) task).hasOutputs()) {
					for (Transition t : ((AbstractTask) task).getOutputs()) {
						t.setId(buildtimeIDGenerator.getNewBuildTimeID());
					}
				}
			}
		}
	}

	/**
	 * 为新的流程实例赋新的Build time ID值。
	 *
	 * @param node,
	 *            it maybe process instance, task instance, data variable;
	 * @param owner,
	 *            process instance;
	 * @throws Exception
	 */
	private void assignChildrenNewEntityId(TreeNode node, TreeNode owner) throws Exception {
		String id = buildtimeIDGenerator.getNewBuildTimeID();
		if (node instanceof DataVariable) {
			String oldId = node.getId();// 数据变量Build time ID
			((DataVariable) node).setDefinitionId(oldId);
			for (TreeNode child : owner.getChildren()) {
				if (child instanceof StartPoint) {
					Object frm = ((StartPoint) child).getLaunchFormContent();
					if (frm != null) {// 替换表单中绑定的数据变量ID
						// 这段代码是为了替换表单中的变量ID，而写的一个变通办法，其实很不好，
						// 需要优化，通过表单来实现替换ID。
						if (frm instanceof JSONObject) {
							String f = frm.toString();
							String newf = f.replaceAll(oldId, id);
							JSONObject fj = new JSONObject(newf);
							((StartPoint) child).setLaunchFormContent(fj);
						}
					}
					if (((StartPoint) child).getAccessibleVars() != null
							&& ((StartPoint) child).getAccessibleVars().length > 0) {
						for (AccessibleVariable ac : ((StartPoint) child).getAccessibleVars()) {
							if (ac.getVarId().equals(oldId)) {
								ac.setVarId(id);
							}
							ac.setCurrOwner(owner.getId());
						}
					}
				} else if (child instanceof EndPoint) {
					String frm = ((EndPoint) child).getEndFormContent();
					if (frm != null) {
						String newcontent = frm.replaceAll(oldId, id);
						((EndPoint) child).setEndFormContent(newcontent);
					}
					if (((EndPoint) child).getAccessibleVars() != null
							&& ((EndPoint) child).getAccessibleVars().length > 0) {
						for (AccessibleVariable ac : ((EndPoint) child).getAccessibleVars()) {
							if (ac.getVarId().equals(oldId)) {
								ac.setVarId(id);
							}
							ac.setCurrOwner(owner.getId());
						}
					}
				} else if (child instanceof ManualTask) {
//					String frm = ((ManualTask) child).getFormContent();
					Object frm = ((ManualTask) child).getFormContent();
					if (frm != null) {
//						String newcontent = frm.replaceAll(oldId, id);
//						((ManualTask) child).setFormContent(newcontent);
						if (frm instanceof JSONObject){
							String s = frm.toString();
							String str = s.replaceAll(oldId, id);
							JSONObject newContent = new JSONObject(str);
							((ManualTask) child).setFormContent(newContent);
						}
					}
					if (((ManualTask) child).getAccessibleVars() != null
							&& ((ManualTask) child).getAccessibleVars().length > 0) {
						for (AccessibleVariable ac : ((ManualTask) child).getAccessibleVars()) {
							if (ac.getVarId().equals(oldId)) {
								ac.setVarId(id);
							}
							ac.setCurrOwner(owner.getId());
						}
					}
				}
			}
		} else if (node instanceof AbstractTask) {
			if (node instanceof StartPoint) {
				if (((StartPoint) node).getAccessibleVars() != null
						&& ((StartPoint) node).getAccessibleVars().length > 0) {
					for (AccessibleVariable ac : ((StartPoint) node).getAccessibleVars()) {
						ac.setTaskId(id);
						ac.setParent(null);
					}
				}
			} else if (node instanceof EndPoint) {
				if (((EndPoint) node).getAccessibleVars() != null && ((EndPoint) node).getAccessibleVars().length > 0) {
					for (AccessibleVariable ac : ((EndPoint) node).getAccessibleVars()) {
						ac.setTaskId(id);
						ac.setParent(null);
					}
				}
			} else if (node instanceof ManualTask) {
				if (((ManualTask) node).getAccessibleVars() != null
						&& ((ManualTask) node).getAccessibleVars().length > 0) {
					for (AccessibleVariable ac : ((ManualTask) node).getAccessibleVars()) {
						ac.setTaskId(id);
						ac.setParent(null);
					}
				}
			} else if (node instanceof AssignTask) {
			} else if (node instanceof EmailReceivingTask) {
			} else if (node instanceof EmailSendingTask) {
			} else if (node instanceof SMSReceivingTask) {
			} else if (node instanceof SMSSendingTask) {
			} else if (node instanceof SubprocessPoint) {
			} else if (node instanceof SystemTask) {
			} else if (node instanceof WaitTask) {
			}
		}
		node.setId(id);
		for (TreeNode child : node.getChildren()) {
			child.setParent(id);
			assignChildrenNewEntityId(child, owner);
		}
	}
}

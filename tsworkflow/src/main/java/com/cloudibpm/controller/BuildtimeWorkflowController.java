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
package com.cloudibpm.controller;

import org.apache.commons.lang.StringEscapeUtils;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cloudibpm.blo.buildtime.id.BuildtimeIDGenerator;
import com.cloudibpm.blo.buildtime.wfprocess.BuildtimeWfProcessBlo;
import com.cloudibpm.blo.buildtime.wfprocess.ReleasedWfProcessBlo;
import com.cloudibpm.core.buildtime.release.wfprocess.ReleasedWfProcess;
import com.cloudibpm.core.buildtime.wfprocess.WfProcess;
import com.cloudibpm.core.folder.JSTreeNode;
import com.cloudibpm.core.util.SystemConfig;
import com.cloudibpm.core.util.file.FileUploadUtils;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/service1")
public class BuildtimeWorkflowController {

	@RequestMapping(value = "/api0", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	private JSTreeNode[] getProcesses() throws Exception {
		JSTreeNode root = new JSTreeNode();
		root.id = "000000R";
		root.text = "根节点";
		root.icon = "glyphicon glyphicon-home";
		root.data = "1|null";
		BuildtimeWfProcessBlo buildtimeWfProcessBlo = new BuildtimeWfProcessBlo();
		WfProcess[] processes = buildtimeWfProcessBlo.getAllProcesses();
		if (processes.length > 0) {
			JSTreeNode[] jstnodes = new JSTreeNode[processes.length];
			for (int i = 0; i < processes.length; i++) {
				WfProcess node = processes[i];
				JSTreeNode jstnode = new JSTreeNode();
				jstnode.id = node.getId();
				jstnode.text = JSTreeNode.parseUTF8(node.getName());
				jstnode.parentId = node.getParent();
				jstnode.icon = "";
				if (node instanceof ReleasedWfProcess) {
					jstnode.icon = "glyphicon glyphicon-fire";
					// add some spare information
					jstnode.data = "3|" + node.getOwner() + "|" + ((ReleasedWfProcess) node).getCode() + "|R|"
							+ ((ReleasedWfProcess) node).getVersion();
					jstnode.text = JSTreeNode
							.parseUTF8(node.getName() + "(" + ((ReleasedWfProcess) node).getVersion() + ")");
				} else if (node instanceof WfProcess) {
					jstnode.icon = "glyphicon glyphicon-flash";
					// add some spare information
					jstnode.data = "3|" + node.getOwner() + "|" + ((WfProcess) node).getCode() + "|P";
				}
				if (node.getParent() != null) {
					jstnode.parentId = node.getParent();
				}
				jstnodes[i] = jstnode;
			}
			root.setChildren(jstnodes);
		}
		
		return new JSTreeNode[] {root};
	};

	@RequestMapping(value = "/api1", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public String getNewIDandSerialNumber() {
		try {
			BuildtimeIDGenerator buildtimeIDGenerator = new BuildtimeIDGenerator();
			String id = buildtimeIDGenerator.getNewBuildTimeID();
			String sn = buildtimeIDGenerator.getNewBuildTimeCode();// serialNumber
			return id + "|" + sn;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "0";
	}

	@RequestMapping(value = "/api2", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public String getNewID() {
		try {
			BuildtimeIDGenerator buildtimeIDGenerator = new BuildtimeIDGenerator();
			String id = buildtimeIDGenerator.getNewBuildTimeID();
			return id;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/api4", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public JSTreeNode createWfProcess(String entityname, String parentid, String ownerid, String authorid,
			String authorname, String workflowtype) {
		try {
			WfProcess newprocess = new WfProcess();
			BuildtimeIDGenerator buildtimeIDGenerator = new BuildtimeIDGenerator();
			String id = buildtimeIDGenerator.getNewBuildTimeID();
			String code = buildtimeIDGenerator.getNewBuildTimeCode();
			newprocess.setId(id);
			newprocess.setCode(code);
			newprocess.setName(entityname);
			newprocess.setLastupdate(System.currentTimeMillis());
			newprocess.setAuthorId(authorid);
			newprocess.setAuthor(authorname);
			newprocess.setParent(parentid);
			newprocess.setOwner(ownerid);
			newprocess.setWorkflowType(Integer.parseInt(workflowtype));
			ObjectMapper mapper = new ObjectMapper();
			String strWfProcess = mapper.writeValueAsString(newprocess);
			newprocess.setProcessContent(strWfProcess);
			BuildtimeWfProcessBlo buildtimeWfProcessBlo = new BuildtimeWfProcessBlo();
			buildtimeWfProcessBlo.createNewWfProcess(newprocess);
			JSTreeNode wfprocess = new JSTreeNode();
			wfprocess.id = id;
			wfprocess.text = entityname;
			wfprocess.parentId = parentid;
			wfprocess.data = "3|" + ownerid + "|" + newprocess.getCode();
			return wfprocess; // failed
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Create and save a new user into repository.
	 */
	@RequestMapping(value = "/api5", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public String saveWfProcess(String process) {
		try {
			JSONObject obj = new JSONObject(process);
			WfProcess newprocess = new WfProcess();
			newprocess.setId(obj.getString("id"));
			newprocess.setCode(obj.getString("code"));
			newprocess.setName(obj.getString("name"));
			newprocess.setWorkflowType(obj.getInt("workflowType"));
			newprocess.setProcessType(obj.getInt("processType"));
			newprocess.setAccessLevel(obj.getInt("accessLevel"));
			if (!obj.isNull("keywords")) {
				newprocess.setKeywords(obj.getString("keywords"));
			}
			if (!obj.isNull("description")) {
				newprocess.setDescription(obj.getString("description"));
			}
			if (!obj.isNull("authorId")) {
				newprocess.setAuthorId(obj.getString("authorId"));
			}
			if (!obj.isNull("author")) {
				newprocess.setAuthor(obj.getString("author"));
			}
			newprocess.setParent(obj.getString("parent"));
			newprocess.setOwner(obj.getString("owner"));
			newprocess.setLastupdate(obj.getLong("lastupdate"));
			newprocess.setStatus(obj.getInt("status"));
			if (!obj.isNull("purchasePrice")) {
				newprocess.setPurchasePrice(obj.getDouble("purchasePrice"));
			}
			if (!obj.isNull("usagePrice")) {
				newprocess.setUsagePrice(obj.getDouble("usagePrice"));
			}
			newprocess.setProcessContent(process);
			BuildtimeWfProcessBlo buildtimeWfProcessBlo = new BuildtimeWfProcessBlo();
			buildtimeWfProcessBlo.saveWfProcess(newprocess);
			return "{\"status\": \"1\"}"; // success
		} catch (Exception e) {
			e.printStackTrace();
			return "{\"status\": \"0\"}"; // failed
		}
	}

	// void : @ResponseStatus(value = HttpStatus.OK)
	@RequestMapping(value = "/api6", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String getWfProcess(@RequestParam("id") String id) {
		try {
			BuildtimeWfProcessBlo buildtimeWfProcessBlo = new BuildtimeWfProcessBlo();
			return buildtimeWfProcessBlo.getProcessContent(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/api7", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public int updateWfProcessName(String id, String entityname, String lastupdate) {
		try {
			BuildtimeWfProcessBlo buildtimeWfProcessBlo = new BuildtimeWfProcessBlo();
			WfProcess p = buildtimeWfProcessBlo.getProcessById(id);
			String oldname = p.getName();
			String name = StringEscapeUtils.escapeSql(entityname);
			p.setName(name);
			String content = p.getProcessContent();
			if (content != null) {
				String newcontent = content.replaceAll(oldname, name);
				p.setProcessContent(newcontent);
			}
			p.setLastupdate(Long.parseLong(lastupdate));
			buildtimeWfProcessBlo.updateProcessName(p);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 1;
	}

	@RequestMapping(value = "/api9", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String[] getAllWfProcesses(String ownerid) {
		// WfProcess[] processes = null;
		try {
			BuildtimeWfProcessBlo buildtimeWfProcessBlo = new BuildtimeWfProcessBlo();
			return buildtimeWfProcessBlo.getAllProcesses(ownerid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Create and save a new released process into repository.
	 */
	@RequestMapping(value = "/api10", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public String releaseWfProcess(String pid, String version, String releaser, String releaserid, String versionnote,
			double purchaseprice, double usageprice, String parent, String orgid) {
		try {
			ReleasedWfProcessBlo releasedWfProcessBlo = new ReleasedWfProcessBlo();
			String id = releasedWfProcessBlo.sendWfProcessForApproval(pid, version, releaser, releaserid, versionnote,
					purchaseprice, usageprice, parent, orgid);
			return "{\"status\": \"1\", \"id\": \"" + id + "\"}"; // success
		} catch (Exception e) {
			e.printStackTrace();
			return "{\"status\": \"0\"}"; // failed
		}
	}

	@RequestMapping(value = "/api11", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public String deleteWfProcess(String processid, String ownerid) {
		try {
			BuildtimeWfProcessBlo buildtimeWfProcessBlo = new BuildtimeWfProcessBlo();
			buildtimeWfProcessBlo.deleteWfProcess(processid);
			FileUploadUtils.removeAllFiles(ownerid, "pm", processid);
			ReleasedWfProcessBlo releasedWfProcessBlo = new ReleasedWfProcessBlo();
			releasedWfProcessBlo.deleteWfProcess(processid);
			FileUploadUtils.removeAllFiles(ownerid, "rlp", processid);

			return "{\"status\": \"1\"}"; // success
		} catch (Exception e) {
			e.printStackTrace();
			return "{\"status\": \"0\"}"; // failed
		}
	}

	// void : @ResponseStatus(value = HttpStatus.OK)
	@RequestMapping(value = "/api13", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	@ResponseBody
	public ReleasedWfProcess getReleasedWfProcess(@RequestParam("id") String id) {
		try {
			ReleasedWfProcessBlo releasedWfProcessBlo = new ReleasedWfProcessBlo();
			return releasedWfProcessBlo.getReleasedProcess(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/api15", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public String updateReleaseWfProcess(String process) {
		try {
			JSONObject obj = new JSONObject(process);
			ReleasedWfProcess releasedProcess = new ReleasedWfProcess();
			releasedProcess.setId(obj.getString("id"));
			releasedProcess.setName(obj.getString("name"));
			if (!obj.isNull("version")) {
				releasedProcess.setVersion(obj.getString("version"));
			}
			if (!obj.isNull("releaser")) {
				releasedProcess.setReleaser(obj.getString("releaser"));
			}
			if (!obj.isNull("releaserId")) {
				releasedProcess.setReleaserId(obj.getString("releaserId"));
			}
			if (!obj.isNull("releaseStatement")) {
				releasedProcess.setReleaseStatement(obj.getString("releaseStatement"));
			}
			if (!obj.isNull("purchasePrice")) {
				releasedProcess.setPurchasePrice(obj.getDouble("purchasePrice"));
			}
			if (!obj.isNull("usagePrice")) {
				releasedProcess.setUsagePrice(obj.getDouble("usagePrice"));
			}
			if (!obj.isNull("trialPeriod")) {
				releasedProcess.setTrialPeriod(obj.getInt("trialPeriod"));
			}
			if (!obj.isNull("owner")) {
				releasedProcess.setOwner(obj.getString("owner"));
			}
			ReleasedWfProcessBlo releasedWfProcessBlo = new ReleasedWfProcessBlo();
			releasedWfProcessBlo.updateReleasedWfProcess(releasedProcess);
		} catch (Exception e) {
			e.printStackTrace();
			return "{\"status\": \"0\"}"; // failed
		}
		return "{\"status\": \"1\"}"; // success
	}

	// release/withdraw process from process service store
	@RequestMapping(value = "/api16", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public String releaseWfProcess(String id, String deprecated, String comment, String owner, String userId,
			String userfullname, String ownername) {
		try {
			ReleasedWfProcessBlo releasedWfProcessBlo = new ReleasedWfProcessBlo();
			String v = SystemConfig.getProp("xq.product.service.approval");
			if (v.equals("0")) { // does not support approval service
				releasedWfProcessBlo.modifyWfProcessStatus(id, Integer.parseInt(deprecated),
						System.currentTimeMillis());
			} else if (v.equals("1")) { // supports approval service
				releasedWfProcessBlo.releasedWfProcess(id, Integer.parseInt(deprecated));
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "{\"status\": \"0\"}"; // failed
		}
		return "{\"status\": \"1\"}"; // success
	}

	/**
	 * Move a wfprocess from one folder to another folder. The type is 100, the
	 * process folder; 109, the released process folder;
	 *
	 * @param pid
	 * @param parent
	 * @param type   wfprocess type : "R", "P"
	 * @return
	 */
	@RequestMapping(value = "/api22", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public int moveWfProcess(String pid, String parent, String type) {
		try {
			if (type.equals("P")) {
				BuildtimeWfProcessBlo buildtimeWfProcessBlo = new BuildtimeWfProcessBlo();
				WfProcess p = buildtimeWfProcessBlo.getProcessById(pid);
				String oldparent = p.getParent();
				String content = p.getProcessContent();
				String newcontent = content.replaceAll(oldparent, parent);
				p.setProcessContent(newcontent);
				buildtimeWfProcessBlo.moveWfProcess(pid, parent, content);
			} else if (type.equals("R")) {
				ReleasedWfProcessBlo releasedWfProcessBlo = new ReleasedWfProcessBlo();
				ReleasedWfProcess rp = releasedWfProcessBlo.prepReleasedWfProcess(pid);
				String oldparent = rp.getParent();
				String content = rp.getProcessContent();
				String newcontent = content.replaceAll(oldparent, parent);
				rp.setProcessContent(newcontent);
				releasedWfProcessBlo.moveReleasedWfProcess(pid, parent, content);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 1;
	}

	@RequestMapping(value = "/api24", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public String copyWfProcess(String pid, String parent, String orgid, String type) {
		try {
			if (type.equals("R")) {
				ReleasedWfProcessBlo releasedWfProcessBlo = new ReleasedWfProcessBlo();
				WfProcess newprocess = releasedWfProcessBlo.copyReleasedWfProcess(pid);
				// copy the files in the folder
				return "{\"status\": \"1\", \"id\": \"" + newprocess.getId() + "\"}"; // success
			} else if (type.equals("P")) {
				BuildtimeWfProcessBlo buildtimeWfProcessBlo = new BuildtimeWfProcessBlo();
				WfProcess newprocess = buildtimeWfProcessBlo.copyWfProcess(pid);
				// copy the files in the folder
				return "{\"status\": \"1\", \"id\": \"" + newprocess.getId() + "\"}"; // success
			}
		} catch (Exception e) {
			e.printStackTrace();

		}
		return "{\"status\": \"0\"}"; // failed
	}

	// purchase a process from process service store
	@RequestMapping(value = "/api25", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public JSTreeNode buyWfProcessById(String id, String parent, String owner, String modify) {
		try {
			// payment codes here.
			if (true) {
				ReleasedWfProcessBlo releasedWfProcessBlo = new ReleasedWfProcessBlo();
				WfProcess p = releasedWfProcessBlo.buyWfProcess(id, parent, owner, modify);
				JSTreeNode jstnode = new JSTreeNode();
				jstnode.id = p.getId();
				jstnode.text = JSTreeNode.parseUTF8(p.getName());
				jstnode.parentId = p.getParent();
				jstnode.icon = "";
				if (p instanceof ReleasedWfProcess) {
					jstnode.icon = "glyphicon glyphicon-fire";
					jstnode.data = "3|" + p.getOwner() + "|" + ((ReleasedWfProcess) p).getCode() + "|R|"
							+ ((ReleasedWfProcess) p).getVersion();
					jstnode.text = JSTreeNode.parseUTF8(p.getName() + "(" + ((ReleasedWfProcess) p).getVersion() + ")");
				} else if (p instanceof WfProcess) {
					jstnode.icon = "glyphicon glyphicon-flash";
					jstnode.data = "3|" + p.getOwner() + "|" + ((WfProcess) p).getCode() + "|P";
				}
				return jstnode;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

}

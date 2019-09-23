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

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cloudibpm.blo.buildtime.id.BuildtimeIDGenerator;
import com.cloudibpm.blo.buildtime.wfprocess.ReleasedWfProcessBlo;
import com.cloudibpm.core.buildtime.release.wfprocess.ReleasedWfProcess;
import com.cloudibpm.core.buildtime.wfprocess.WfProcess;
import com.cloudibpm.core.buildtime.wfprocess.WfProcessListPage;
import com.cloudibpm.core.folder.JSTreeNode;
import com.cloudibpm.core.runtime.admin.AdminSearchResult;
import com.cloudibpm.core.runtime.admin.AdminSearchResultPage;

@RestController
@RequestMapping("/service13")
public class AdministrateWorkflowController {

	@RequestMapping(value = "/api0", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	private JSTreeNode[] getProcesses() throws Exception {
		JSTreeNode root = new JSTreeNode();
		root.id = "000000R";
		root.text = "根节点";
		root.icon = "glyphicon glyphicon-home";
		root.data = "1|null";
		ReleasedWfProcessBlo releasedWfProcessBlo = new ReleasedWfProcessBlo();
		ReleasedWfProcess[] processes = releasedWfProcessBlo.getAllProcesses();
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

		return new JSTreeNode[] { root };
	};

	// void : @ResponseStatus(value = HttpStatus.OK)
	@RequestMapping(value = "/api2", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String getWfProcessInstance(@RequestParam("id") String id) {
		try {
			CloseableHttpClient httpClient = HttpClientBuilder.create().build();
			CloseableHttpResponse response1 = null;
			String apiserver = "http://localhost:8080/tsworkflow";// SystemConfig.getProp("paas.server.domainname");
			String url = apiserver + "/service11/api7";
			List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
			urlParameters.add(new BasicNameValuePair("instanceId", id));
			String str = EntityUtils.toString(new UrlEncodedFormEntity(urlParameters, "utf-8"));
			HttpGet httpGet = new HttpGet(url);
			try {
				httpGet.setURI(new URI(httpGet.getURI().toString() + "?" + str));
			} catch (URISyntaxException e) {
				e.printStackTrace();
			}
			response1 = httpClient.execute(httpGet);
			if (response1.getStatusLine().getStatusCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + response1.getStatusLine().getStatusCode());
			}
			HttpEntity entity = response1.getEntity();
			String responseJson = EntityUtils.toString(entity, "UTF-8").trim();
			httpClient.close();
			httpGet.abort();
			return responseJson;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@RequestMapping(value = "/api6", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public WfProcessListPage queryWfProcess(int deprecated, String condition, int pageno, int pagesize) {
		try {
			ReleasedWfProcessBlo releasedWfProcessBlo = new ReleasedWfProcessBlo();
			return releasedWfProcessBlo.searchWfProcess(deprecated, condition, pageno, pagesize);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/api7", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public String modifyWfProcessStatus(int deprecated, String rid, String lastupdate, String comment, String owner,
			String userId, String userfullname, String ownername) {
		try {
			ReleasedWfProcessBlo releasedWfProcessBlo = new ReleasedWfProcessBlo();
			releasedWfProcessBlo.modifyWfProcessStatus(rid, deprecated, Long.parseLong(lastupdate));
			return "{\"status\": \"1\"}";
		} catch (Exception e) {
			return "{\"status\": \"0\"}";
		}
	}

	@RequestMapping(value = "/api16", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public String getNewRuntimeID() {
		try {
			BuildtimeIDGenerator buildtimeIDGenerator = new BuildtimeIDGenerator();
			String id = buildtimeIDGenerator.getNewRunTimeID();
			return id;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 *
	 * @param oid   organization ID
	 * @param pn
	 * @param psz
	 * @param cond1 search value
	 * @param cond2 status
	 * @param cond3 from date
	 * @param cond4 to date
	 * @return
	 */
	@RequestMapping(value = "/api30", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	@ResponseBody
	public AdminSearchResultPage searchAllApplicationServices(String oid, int pn, int psz, String cond1, String cond2,
			String cond3, String cond4) {
		try {
			// 在这里服务应该调用另一个服务获取所有当前运行的服务器，通过一个一个服务器访问，来搜索符合条件的流程实例。
			CloseableHttpClient httpClient = HttpClientBuilder.create().build();
			String apiserver = "http://localhost:8080/tsworkflow";// SystemConfig.getProp("paas.server.domainname");
			String url = apiserver + "/service11/api6";
			List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
			urlParameters.add(new BasicNameValuePair("oid", oid));
			urlParameters.add(new BasicNameValuePair("cond1", cond1));
			urlParameters.add(new BasicNameValuePair("cond2", cond2));
			urlParameters.add(new BasicNameValuePair("cond3", cond3));
			urlParameters.add(new BasicNameValuePair("cond4", cond4));
			String str = EntityUtils.toString(new UrlEncodedFormEntity(urlParameters, "utf-8"));
			HttpGet httpGet = new HttpGet(url);
			httpGet.setURI(new URI(httpGet.getURI().toString() + "?" + str));
			CloseableHttpResponse response1 = httpClient.execute(httpGet);
			if (response1.getStatusLine().getStatusCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + response1.getStatusLine().getStatusCode());
			}
			HttpEntity entity = response1.getEntity();
			String responseJson = EntityUtils.toString(entity, "UTF-8").trim();
			httpClient.close();
			httpGet.abort();
			List<AdminSearchResult> list = new ArrayList<AdminSearchResult>();
			if (!responseJson.equals("")) {
				JSONArray jsonarr = new JSONArray(responseJson);
				if (jsonarr.length() > 0) {
					for (int i = 0; i < jsonarr.length(); i++) {
						list.add(parseAdminSearchResult(jsonarr.getJSONObject(i)));
					}
				}
			}
			return createPage(list, pn, psz);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 *
	 * @param pid  process definition Id
	 * @param pn
	 * @param psz
	 * @param cond
	 * @return
	 */
	@RequestMapping(value = "/api31", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	@ResponseBody
	public AdminSearchResultPage searchApplicationProcService(String pid, int pn, int psz, String cond1, String cond2,
			String cond3, String cond4) {
		try {
			// 在这里服务应该调用另一个服务获取所有当前运行的服务器，通过一个一个服务器访问，来搜索符合条件的流程实例。
			CloseableHttpClient httpClient = HttpClientBuilder.create().build();
			String apiserver = "http://localhost:8080/tsworkflow";// SystemConfig.getProp("paas.server.domainname");
			String url = apiserver + "/service11/api12";
			List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
			urlParameters.add(new BasicNameValuePair("pid", pid));
			urlParameters.add(new BasicNameValuePair("cond1", cond1));
			urlParameters.add(new BasicNameValuePair("cond2", cond2));
			urlParameters.add(new BasicNameValuePair("cond3", cond3));
			urlParameters.add(new BasicNameValuePair("cond4", cond4));
			String str = EntityUtils.toString(new UrlEncodedFormEntity(urlParameters, "utf-8"));
			HttpGet httpGet = new HttpGet(url);
			httpGet.setURI(new URI(httpGet.getURI().toString() + "?" + str));
			CloseableHttpResponse response1 = httpClient.execute(httpGet);
			if (response1.getStatusLine().getStatusCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + response1.getStatusLine().getStatusCode());
			}
			HttpEntity entity = response1.getEntity();
			String responseJson = EntityUtils.toString(entity, "UTF-8").trim();
			httpClient.close();
			httpGet.abort();
			List<AdminSearchResult> list = new ArrayList<AdminSearchResult>();
			if (!responseJson.equals("")) {
				JSONArray jsonarr = new JSONArray(responseJson);
				if (jsonarr.length() > 0) {
					for (int i = 0; i < jsonarr.length(); i++) {
						list.add(parseAdminSearchResult(jsonarr.getJSONObject(i)));
					}
				}
			}
			return createPage(list, pn, psz);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private AdminSearchResult parseAdminSearchResult(JSONObject json) {
		AdminSearchResult r = new AdminSearchResult();
		r.setInstanceId(json.getString("instanceId"));
		r.setDefinitionId(json.getString("definitionId"));
		if (!json.isNull("processName")) {
			r.setProcessName(json.getString("processName"));
		}
		if (!json.isNull("processVersion")) {
			r.setProcessVersion(json.getString("processVersion"));
		}
		if (!json.isNull("status")) {
			r.setStatus(json.getInt("status"));
		}
		if (!json.isNull("launcher")) {
			r.setLauncher(json.getString("launcher"));
		}
		if (!json.isNull("idType")) {
			r.setIdType(json.getString("idType"));
		}
		if (!json.isNull("idNumber")) {
			r.setIdNumber(json.getString("idNumber"));
		}
		if (!json.isNull("startTime")) {
			r.setStartTime(json.getLong("startTime"));
		}
		if (!json.isNull("suspensionTime")) {
			r.setSuspensionTime(json.getLong("suspensionTime"));
		}
		if (!json.isNull("updateTime")) {
			r.setUpdateTime(json.getLong("updateTime"));
		}
		if (!json.isNull("server")) {
			r.setServer(json.getString("server"));
		}
		return r;
	}

	private AdminSearchResultPage createPage(List<AdminSearchResult> list, int pn, int psz) {
		AdminSearchResultPage page = new AdminSearchResultPage(pn, psz);
		int total = 1;
		int pagesize = psz;
		int pageno = pn;
		if (total == 0) {
			page.setPageSize(pagesize);
			page.setPageNo(0);
			page.setAllEntitiesCount(0);
			page.setAllPagesCount(0);
			page.setPageIndex(0);
		} else {
			page.setPageSize(pagesize);
			page.setPageNo(pageno);
			page.setAllEntitiesCount(total);
			int n = total / pagesize;
			int m = total % pagesize;
			if (m > 0) {
				n = n + 1;
			}
			page.setAllPagesCount(n);
			int pageindex = (pageno - 1) * pagesize;
			page.setPageIndex(pageindex);
			page.setPageEntities(list.toArray(new AdminSearchResult[list.size()]));
		}
		return page;
	}

	/**
	 *
	 * @param pid  process definition Id
	 * @param pn
	 * @param psz
	 * @param cond
	 * @return
	 */
	@RequestMapping(value = "/api32", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String addComment(String pid, String tid, String comment) {
		try {
			// 在这里服务应该调用另一个服务获取所有当前运行的服务器，通过一个一个服务器访问，来搜索符合条件的流程实例。
			CloseableHttpClient httpClient = HttpClientBuilder.create().build();
			String apiserver = "http://localhost:8080/tsworkflow";// SystemConfig.getProp("paas.server.domainname");
			String url = apiserver + "/service11/api13";
			List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
			urlParameters.add(new BasicNameValuePair("pid", pid));
			urlParameters.add(new BasicNameValuePair("tid", tid));
			urlParameters.add(new BasicNameValuePair("comment", comment));
			String str = EntityUtils.toString(new UrlEncodedFormEntity(urlParameters, "utf-8"));
			HttpGet httpGet = new HttpGet(url);
			httpGet.setURI(new URI(httpGet.getURI().toString() + "?" + str));
			CloseableHttpResponse response1 = httpClient.execute(httpGet);
			if (response1.getStatusLine().getStatusCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + response1.getStatusLine().getStatusCode());
			}
			HttpEntity entity = response1.getEntity();
			String responseJson = EntityUtils.toString(entity, "UTF-8").trim();
			httpClient.close();
			httpGet.abort();
			List<AdminSearchResult> list = new ArrayList<AdminSearchResult>();
			if (!responseJson.equals("")) {
				JSONArray jsonarr = new JSONArray(responseJson);
				if (jsonarr.length() > 0) {
					for (int i = 0; i < jsonarr.length(); i++) {
						list.add(parseAdminSearchResult(jsonarr.getJSONObject(i)));
					}
				}
			}
			return "{\"status\": \"1\"}";
		} catch (Exception e) {
			e.printStackTrace();
			return "{\"status\": \"0\"}";
		}
	}

	@RequestMapping(value = "/api33", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String addChatMessage(String pid, String tid, String message) {
		try {
			// 在这里服务应该调用另一个服务获取所有当前运行的服务器，通过一个一个服务器访问，来搜索符合条件的流程实例。
			CloseableHttpClient httpClient = HttpClientBuilder.create().build();
			String apiserver = "http://localhost:8080/tsworkflow";// SystemConfig.getProp("paas.server.domainname");
			String url = apiserver + "/service11/api14";
			List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
			urlParameters.add(new BasicNameValuePair("pid", pid));
			urlParameters.add(new BasicNameValuePair("tid", tid));
			urlParameters.add(new BasicNameValuePair("message", message));
			String str = EntityUtils.toString(new UrlEncodedFormEntity(urlParameters, "utf-8"));
			HttpGet httpGet = new HttpGet(url);
			httpGet.setURI(new URI(httpGet.getURI().toString() + "?" + str));
			CloseableHttpResponse response1 = httpClient.execute(httpGet);
			if (response1.getStatusLine().getStatusCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + response1.getStatusLine().getStatusCode());
			}
			HttpEntity entity = response1.getEntity();
			String responseJson = EntityUtils.toString(entity, "UTF-8").trim();
			httpClient.close();
			httpGet.abort();
			List<AdminSearchResult> list = new ArrayList<AdminSearchResult>();
			if (!responseJson.equals("")) {
				JSONArray jsonarr = new JSONArray(responseJson);
				if (jsonarr.length() > 0) {
					for (int i = 0; i < jsonarr.length(); i++) {
						list.add(parseAdminSearchResult(jsonarr.getJSONObject(i)));
					}
				}
			}
			return "{\"status\": \"1\"}";
		} catch (Exception e) {
			e.printStackTrace();
			return "{\"status\": \"0\"}";
		}
	}

	/**
	 * Add user id information who will be copied to.
	 * 
	 * @param pid
	 * @param tid
	 * @param copyto
	 * @return
	 */
	@RequestMapping(value = "/api34", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String addInvitation(String pid, String tid, String copyto) {
		try {
			// 在这里服务应该调用另一个服务获取所有当前运行的服务器，通过一个一个服务器访问，来搜索符合条件的流程实例。
			CloseableHttpClient httpClient = HttpClientBuilder.create().build();
			String apiserver = "http://localhost:8080/tsworkflow";// SystemConfig.getProp("paas.server.domainname");
			String url = apiserver + "/service11/api15";
			List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
			urlParameters.add(new BasicNameValuePair("pid", pid));
			urlParameters.add(new BasicNameValuePair("tid", tid));
			urlParameters.add(new BasicNameValuePair("invitation", copyto));
			String str = EntityUtils.toString(new UrlEncodedFormEntity(urlParameters, "utf-8"));
			HttpGet httpGet = new HttpGet(url);
			httpGet.setURI(new URI(httpGet.getURI().toString() + "?" + str));
			CloseableHttpResponse response1 = httpClient.execute(httpGet);
			if (response1.getStatusLine().getStatusCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + response1.getStatusLine().getStatusCode());
			}
			HttpEntity entity = response1.getEntity();
			String responseJson = EntityUtils.toString(entity, "UTF-8").trim();
			httpClient.close();
			httpGet.abort();
			List<AdminSearchResult> list = new ArrayList<AdminSearchResult>();
			if (!responseJson.equals("")) {
				JSONArray jsonarr = new JSONArray(responseJson);
				if (jsonarr.length() > 0) {
					for (int i = 0; i < jsonarr.length(); i++) {
						list.add(parseAdminSearchResult(jsonarr.getJSONObject(i)));
					}
				}
			}
			return "{\"status\": \"1\"}";
		} catch (Exception e) {
			e.printStackTrace();
			return "{\"status\": \"0\"}";
		}
	}

}

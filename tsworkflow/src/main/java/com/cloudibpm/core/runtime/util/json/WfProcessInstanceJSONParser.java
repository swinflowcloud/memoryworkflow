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
package com.cloudibpm.core.runtime.util.json;

import com.cloudibpm.core.Location;
import com.cloudibpm.core.buildtime.util.json.WfProcessJSONParser;
import com.cloudibpm.core.buildtime.wfprocess.task.*;
import com.cloudibpm.core.communication.ChatMessage;
import com.cloudibpm.core.communication.Comment;
import com.cloudibpm.core.communication.SecondaryComment;
import com.cloudibpm.core.data.FileConstant;
import com.cloudibpm.core.data.variable.AccessibleVariable;
import com.cloudibpm.core.data.variable.ArrayDataVariable;
import com.cloudibpm.core.data.variable.DataVariable;
import com.cloudibpm.core.runtime.wfprocess.WfProcessInstance;
import com.cloudibpm.core.runtime.wfprocess.task.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used to parse business process from JSON to Java object.
 * 基本思想是，通过新建对象，并对JSON对象的每一个属性逐一访问和获取，并赋值给新对象。
 * 可以使的处理思路清晰和简单，效率可能有点低，但是由于JSON对象中，有很多字符串需要解析，
 * 如表达式字符串，在访问JSON对象属性过程中就可以对其进行解析。
 * 
 * @author Dahai created on 20170808 last updated on 2017-11-28, last updated at
 *         16:21 on 2018-09-02 (My 46th birthday)
 *
 */
public class WfProcessInstanceJSONParser extends WfProcessJSONParser {

	/**
	 * 将字符串反序列化成WfProcessInstance对象。
	 * 
	 * @date Dahai Cao last updated at 16:29 on Sunday 2018-09-02
	 * @param jsonWfProcess String
	 * @return WfProcessInstance
	 * @throws JSONException
	 * @throws Exception
	 */
	public static WfProcessInstance parseWfProcessInstance(String jsonWfProcess) throws JSONException, Exception {
		JSONObject obj = new JSONObject(jsonWfProcess);
		WfProcessInstance newprocess = new WfProcessInstance();
		parseCommonProps(newprocess, obj);
		if (!obj.isNull("purchasePrice")) {
			newprocess.setPurchasePrice(obj.getDouble("purchasePrice"));
		}
		if (!obj.isNull("usagePrice")) {
			newprocess.setUsagePrice(obj.getDouble("usagePrice"));
		}
		if (!obj.isNull("wfProcessId")) {
			newprocess.setWfProcessId(obj.getString("wfProcessId"));
		}
		if (!obj.isNull("launchTime")) {
			newprocess.setLaunchTime(obj.getLong("launchTime"));
		}
		if (!obj.isNull("code")) {
			newprocess.setCode(obj.getString("code"));
		}
		if (!obj.isNull("deprecated")) {
			newprocess.setDeprecated(obj.getInt("deprecated"));
		}
		if (!obj.isNull("staffLaunched")) {
			newprocess.setStaffLaunched(obj.getInt("staffLaunched"));
		}
		if (!obj.isNull("trialPeriod")) {
			newprocess.setTrialPeriod(obj.getInt("trialPeriod"));
		}
		if (!obj.isNull("startTime")) {
			newprocess.setStartTime(obj.getLong("startTime"));
		}
		if (!obj.isNull("workflowType")) {
			newprocess.setWorkflowType(obj.getInt("workflowType"));
		}
		if (!obj.isNull("totalUseCounting")) {
			newprocess.setTotalUseCounting(obj.getLong("totalUseCounting"));
		}
		if (!obj.isNull("processType")) {
			newprocess.setProcessType(obj.getInt("processType"));
		}
		if (!obj.isNull("id")) {
			newprocess.setId(obj.getString("id"));
		}
		if (!obj.isNull("owner")) {
			newprocess.setOwner(obj.getString("owner"));
		}
		if (!obj.isNull("accessLevel")) {
			newprocess.setAccessLevel(obj.getInt("accessLevel"));
		}
		if (!obj.isNull("releaseDate")) {
			newprocess.setReleaseDate(obj.getLong("releaseDate"));
		}
		if (!obj.isNull("successCounting")) {
			newprocess.setSuccessCounting(obj.getLong("successCounting"));
		}
		if (!obj.isNull("terminationTime")) {
			newprocess.setTerminationTime(obj.getLong("terminationTime"));
		}
		if (!obj.isNull("name")) {
			newprocess.setName(obj.getString("name"));
		}
		if (!obj.isNull("terminationCounting")) {
			newprocess.setTerminationCounting(obj.getLong("terminationCounting"));
		}
		if (!obj.isNull("endTime")) {
			newprocess.setEndTime(obj.getLong("endTime"));
		}
		if (!obj.isNull("lastupdate")) {
			newprocess.setLastupdate(obj.getLong("lastupdate"));
		}
		if (!obj.isNull("totalDownloading")) {
			newprocess.setTotalDownloading(obj.getLong("totalDownloading"));
		}
		if (!obj.isNull("suspensionTime")) {
			newprocess.setSuspensionTime(obj.getLong("suspensionTime"));
		}
		if (!obj.isNull("status")) {
			newprocess.setStatus(obj.getInt("status"));
		}
		if (!obj.isNull("version")) {
			newprocess.setVersion(obj.getString("version"));
		}
		if (!obj.isNull("ver")) {
			newprocess.setVer(obj.getLong("ver"));
		}
		if (!obj.isNull("version")) {
			newprocess.setVersion(obj.getString("version"));
		}
		if (!obj.isNull("launchUserId")) {
			newprocess.setLaunchUserId(obj.getString("launchUserId"));
		}
		if (!obj.isNull("launchUser")) {
			newprocess.setLaunchUser(obj.getString("launchUser"));
		}
		if (!obj.isNull("idType")) {
			newprocess.setIdType(obj.getString("idType"));
		}
		if (!obj.isNull("idNumber")) {
			newprocess.setIdNumber(obj.getString("idNumber"));
		}
		if (!obj.isNull("mobileNumber")) {
			newprocess.setMobileNumber(obj.getString("mobileNumber"));
		}
		if (!obj.isNull("mobileNumber")) {
			newprocess.setMobileNumber(obj.getString("mobileNumber"));
		}
		if (!obj.isNull("ipv4")) {
			newprocess.setIpv4(obj.getString("ipv4"));
		}
		if (!obj.isNull("ipv6")) {
			newprocess.setIpv6(obj.getString("ipv6"));
		}
		if (!obj.isNull("serverIp")) {
			newprocess.setServerIp(obj.getString("serverIp"));
		}
		if (!obj.isNull("device")) {
			newprocess.setDevice(obj.getString("device"));
		}
		if (!obj.isNull("longitude")) {
			newprocess.setLongitude(obj.getString("longitude"));
		}
		if (!obj.isNull("latitude")) {
			newprocess.setLatitude(obj.getString("latitude"));
		}
		JSONArray jsonarr = obj.getJSONArray("children");
		if (jsonarr.length() > 0) { // parsing data variables and tasks
			for (int i = 0; i < jsonarr.length(); i++) {
				parseChildren(jsonarr.getJSONObject(i), newprocess);
			}
		}
		parseTransitionInstance(jsonarr, newprocess);
		return newprocess;
	}

	private static void parseChildren(JSONObject obj, WfProcessInstance newprocess) throws Exception {
		String currOwner = newprocess.getId();
		String owner = newprocess.getOwner();
		if (obj.getString("classtypename").equals(ArrayDataVariable.class.getSimpleName())) {
			newprocess.addChild(parseArrayDataVariable(obj, currOwner, owner));
		} else if (obj.getString("classtypename").equals(DataVariable.class.getSimpleName())) {
			newprocess.addChild(parseDataVariable(obj, currOwner, owner));
		} else if (obj.getString("classtypename").equals(StartPoint.class.getSimpleName())
				|| obj.getString("classtypename").equals(StartPointInstance.class.getSimpleName())) {
			StartPointInstance task = new StartPointInstance();
			setCommonTaskProps(task, obj, currOwner, owner);
			if (!obj.isNull("startTime")) {
				task.setStartTime(obj.getLong("startTime"));
			}
			if (!obj.isNull("endTime")) {
				task.setEndTime(obj.getLong("endTime"));
			}
			if (!obj.isNull("definitionId")) {
				task.setDefinitionId(obj.getString("definitionId"));
			}
			if (!obj.isNull("launchUIType")) {
				task.setLaunchUIType(obj.getInt("launchUIType"));
			}
			if (!obj.isNull("launchUIUrl")) {
				task.setLaunchUIUrl(obj.getString("launchUIUrl"));
			}
			if (!obj.isNull("launchFormContent")) {
				JSONObject o = obj.getJSONObject(("launchFormContent"));
				// task.setLaunchFormContent(o.toString());
				task.setLaunchFormContent(o);// 直接的JSON Object存进去
//				String content = obj.getString("launchFormContent");
//				System.out.println(content);
//				task.setLaunchFormContent(content);
			}
			newprocess.addChild(task);
			JSONArray jsonarr = obj.getJSONArray("accessibleVars");
			if (jsonarr.length() > 0) {
				List<AccessibleVariable> list = new ArrayList<AccessibleVariable>();
				for (int i = 0; i < jsonarr.length(); i++) {
					list.add(parseAccessibleVariables(jsonarr.getJSONObject(i)));
				}
				task.setAccessibleVars(list.toArray(new AccessibleVariable[list.size()]));
			}
		} else if (obj.getString("classtypename").equals(EndPoint.class.getSimpleName())
				|| obj.getString("classtypename").equals(EndPointInstance.class.getSimpleName())) {
			EndPointInstance task = new EndPointInstance();
			setCommonTaskProps(task, obj, currOwner, owner);
			if (!obj.isNull("startTime")) {
				task.setStartTime(obj.getLong("startTime"));
			}
			if (!obj.isNull("endTime")) {
				task.setEndTime(obj.getLong("endTime"));
			}
			if (!obj.isNull("definitionId")) {
				task.setDefinitionId(obj.getString("definitionId"));
			}
			if (!obj.isNull("processInstanceId")) {
				task.setProcessInstanceId(obj.getString("processInstanceId"));
			}
			if (!obj.isNull("processId")) {
				task.setProcessId(obj.getString("processId"));
			}
			if (!obj.isNull("endUIType")) {
				task.setEndUIType(obj.getInt("endUIType"));
			}
			if (!obj.isNull("endUIUrl")) {
				task.setEndUIUrl(obj.getString("endUIUrl"));
			}
			if (!obj.isNull("endFormContent")) {
				JSONObject o = obj.getJSONObject(("endFormContent"));
				task.setEndFormContent(o.toString());
			}
			newprocess.addChild(task);
			JSONArray jsonarr = obj.getJSONArray("accessibleVars");
			if (jsonarr.length() > 0) {
				List<AccessibleVariable> list = new ArrayList<AccessibleVariable>();
				for (int i = 0; i < jsonarr.length(); i++) {
					list.add(parseAccessibleVariables(jsonarr.getJSONObject(i)));
				}
				task.setAccessibleVars(list.toArray(new AccessibleVariable[list.size()]));
			}
		} else if (obj.getString("classtypename").equals(AssignTask.class.getSimpleName())
				|| obj.getString("classtypename").equals(AssignTaskInstance.class.getSimpleName())) {
			AssignTaskInstance task = new AssignTaskInstance();
			setCommonTaskProps(task, obj, currOwner, owner);
			if (!obj.isNull("startTime")) {
				task.setStartTime(obj.getLong("startTime"));
			}
			if (!obj.isNull("endTime")) {
				task.setEndTime(obj.getLong("endTime"));
			}
			if (!obj.isNull("definitionId")) {
				task.setDefinitionId(obj.getString("definitionId"));
			}
			JSONArray jsonarr = obj.getJSONArray("assignments");
			if (jsonarr.length() > 0) {
				List<Assignment> list = new ArrayList<Assignment>();
				for (int i = 0; i < jsonarr.length(); i++) {
					Assignment a = new Assignment();
					a.setType(0);
					parseAssignments(a, jsonarr.getJSONObject(i), currOwner, owner);
					list.add(a);
				}
				task.setAssignments(list.toArray(new Assignment[list.size()]));
			}
			newprocess.addChild(task);
		} else if (obj.getString("classtypename").equals(ManualTask.class.getSimpleName())
				|| obj.getString("classtypename").equals(ManualTaskInstance.class.getSimpleName())) {
			ManualTaskInstance task = new ManualTaskInstance();
			setCommonTaskProps(task, obj, currOwner, owner);
			if (!obj.isNull("startTime")) {
				task.setStartTime(obj.getLong("startTime"));
			}
			if (!obj.isNull("endTime")) {
				task.setEndTime(obj.getLong("endTime"));
			}
			if (!obj.isNull("definitionId")) {
				task.setDefinitionId(obj.getString("definitionId"));
			}
			if (!obj.isNull("enabledTime")) {
				task.setEnabledTime(obj.getLong("enabledTime"));
			}
			if (!obj.isNull("expiryDateTime")) {
				task.setExpiryDateTime(obj.getLong("expiryDateTime"));
			}
			if (!obj.isNull("alarmDateTime")) {
				task.setAlarmDateTime(obj.getLong("alarmDateTime"));
			}
			if (!obj.isNull("expiryHandlerWfProcessId")) {
				task.setExpiryHandlerWfProcessId(obj.getString("expiryHandlerWfProcessId"));
			}
			if (!obj.isNull("expiryHandlerInstanceId")) {
				task.setExpiryHandlerInstanceId(obj.getString("expiryHandlerInstanceId"));
			}
			if (!obj.isNull("phase")) {
				task.setPhase(obj.getInt("phase"));
			}
			if (!obj.isNull("submitterId")) {
				task.setSubmitterId(obj.getString("submitterId"));
			}
			if (!obj.isNull("submitter")) {
				task.setSubmitter(obj.getString("submitter"));
			}
			if (!obj.isNull("submitterIp")) {
				task.setSubmitterIp(obj.getString("submitterIp"));
			}
			if (!obj.isNull("autoSubmitted")) {
				task.setAutoSubmitted(obj.getInt("autoSubmitted"));
			}
			if (!obj.isNull("priority")) {
				task.setPriority(obj.getInt("priority"));
			}
			if (!obj.isNull("uiType")) {
				task.setUiType(obj.getInt("uiType"));
			}
			if (!obj.isNull("uiUrl")) {
				task.setUiUrl(obj.getString("uiUrl"));
			}
			if (!obj.isNull("formContent")) {
//				JSONObject o = obj.getJSONObject(("formContent"));
//				task.setFormContent(o.toString());
				JSONObject o = obj.getJSONObject(("formContent"));
				task.setFormContent(o);
//				String fcontent = obj.getString("formContent");
//				task.setFormContent(fcontent);
			}
			task.setDeadlineDays(obj.getInt("deadlineDays"));
			task.setAlarmDays(obj.getInt("alarmDays"));
			task.setAlarmFrequency(obj.getInt("alarmFrequency"));
			task.setAlarmMethod(obj.getInt("alarmMethod"));
			if (!obj.isNull("priority")) {
				task.setPriority(obj.getInt("priority"));
			}
			if (!obj.isNull("accessibleVars")) {
				JSONArray jsonarr = obj.getJSONArray("accessibleVars");
				if (jsonarr != null && jsonarr.length() > 0) {
					List<AccessibleVariable> list = new ArrayList<AccessibleVariable>();
					for (int i = 0; i < jsonarr.length(); i++) {
						list.add(parseAccessibleVariables(jsonarr.getJSONObject(i)));
					}
					task.setAccessibleVars(list.toArray(new AccessibleVariable[list.size()]));
				}
			}
			if (!obj.isNull("participants")) {
				JSONArray j1 = obj.getJSONArray("participants");
				if (j1 != null && j1.length() > 0) {
					List<Participant> list = new ArrayList<Participant>();
					for (int i = 0; i < j1.length(); i++) {
						list.add(parseParticipants(j1.getJSONObject(i)));
					}
					task.setParticipants(list.toArray(new Participant[list.size()]));
				}
			}
			if (!obj.isNull("candidates")) {
				JSONArray j2 = obj.getJSONArray("candidates");
				if (j2 != null && j2.length() > 0) {
					List<String> list = new ArrayList<String>();
					for (int i = 0; i < j2.length(); i++) {
						list.add(j2.getString(i));
					}
					task.setCandidates(list.toArray(new String[list.size()]));
				}
			}
			if (!obj.isNull("invitations")) {
				JSONArray j3 = obj.getJSONArray("invitations");
				if (j3 != null && j3.length() > 0) {
					List<String> list = new ArrayList<String>();
					for (int i = 0; i < j3.length(); i++) {
						list.add(j3.getString(i));
					}
					task.setInvitations(list.toArray(new String[list.size()]));
				}
			}
			if (!obj.isNull("messages")) {
				JSONArray j4 = obj.getJSONArray("messages");
				if (j4 != null && j4.length() > 0) {
					List<ChatMessage> list = new ArrayList<ChatMessage>();
					for (int i = 0; i < j4.length(); i++) {
						list.add(parseChatMessage(j4.getJSONObject(i)));
					}
					task.setMessages(list.toArray(new ChatMessage[list.size()]));
				}
			}
			if (!obj.isNull("comments")) {
				JSONArray j5 = obj.getJSONArray("comments");
				if (j5 != null && j5.length() > 0) {
					List<Comment> list = new ArrayList<Comment>();
					for (int i = 0; i < j5.length(); i++) {
						list.add(parseComment(j5.getJSONObject(i)));
					}
					task.setComments(list.toArray(new Comment[list.size()]));
				}
			}
			newprocess.addChild(task);
		} else if (obj.getString("classtypename").equals(EmailReceivingTask.class.getSimpleName())
				|| obj.getString("classtypename").equals(EmailReceivingTaskInstance.class.getSimpleName())) {
			EmailReceivingTaskInstance task = new EmailReceivingTaskInstance();
			setCommonTaskProps(task, obj, currOwner, owner);
			if (!obj.isNull("startTime")) {
				task.setStartTime(obj.getLong("startTime"));
			}
			if (!obj.isNull("endTime")) {
				task.setEndTime(obj.getLong("endTime"));
			}
			if (!obj.isNull("definitionId")) {
				task.setDefinitionId(obj.getString("definitionId"));
			}
			newprocess.addChild(task);
		} else if (obj.getString("classtypename").equals(EmailSendingTask.class.getSimpleName())
				|| obj.getString("classtypename").equals(EmailSendingTaskInstance.class.getSimpleName())) {
			EmailSendingTaskInstance task = new EmailSendingTaskInstance();
			setCommonTaskProps(task, obj, currOwner, owner);
			if (!obj.isNull("startTime")) {
				task.setStartTime(obj.getLong("startTime"));
			}
			if (!obj.isNull("endTime")) {
				task.setEndTime(obj.getLong("endTime"));
			}
			if (!obj.isNull("definitionId")) {
				task.setDefinitionId(obj.getString("definitionId"));
			}
			if (!obj.isNull("subject")) {
				task.setSubject(obj.getString("subject"));
			}
			if (!obj.isNull("templateId")) {
				task.setTemplateId(obj.getString("templateId"));
			}
			if (!obj.isNull("template")) {
				task.setTemplate(obj.getString("template"));
			}
			JSONArray jsonarrs = obj.getJSONArray("receivers");
			if (jsonarrs.length() > 0) {
				List<MessageReceiver> list = new ArrayList<MessageReceiver>();
				for (int i = 0; i < jsonarrs.length(); i++) {
					list.add(parseMessageReceivers(jsonarrs.getJSONObject(i)));
				}
				task.setReceivers(list.toArray(new MessageReceiver[list.size()]));
			}
			JSONArray jsonarrs1 = obj.getJSONArray("attachments");
			if (jsonarrs1.length() > 0) {
				List<FileConstant> list = new ArrayList<FileConstant>();
				for (int i = 0; i < jsonarrs1.length(); i++) {
					list.add(parseAttachement(jsonarrs1.getString(i)));
				}
				task.setAttachments(list.toArray(new FileConstant[list.size()]));
			}
			JSONArray jsonarrs2 = obj.getJSONArray("variables");
			if (jsonarrs2.length() > 0) {
				List<String> list = new ArrayList<String>();
				for (int i = 0; i < jsonarrs2.length(); i++) {
					list.add(jsonarrs2.getString(i));
				}
				task.setVariables(list.toArray(new String[list.size()]));
			}
			newprocess.addChild(task);
		} else if (obj.getString("classtypename").equals(SMSReceivingTask.class.getSimpleName())
				|| obj.getString("classtypename").equals(SMSReceivingTaskInstance.class.getSimpleName())) {
			SMSReceivingTaskInstance task = new SMSReceivingTaskInstance();
			setCommonTaskProps(task, obj, currOwner, owner);
			if (!obj.isNull("startTime")) {
				task.setStartTime(obj.getLong("startTime"));
			}
			if (!obj.isNull("endTime")) {
				task.setEndTime(obj.getLong("endTime"));
			}
			if (!obj.isNull("definitionId")) {
				task.setDefinitionId(obj.getString("definitionId"));
			}
			newprocess.addChild(task);
		} else if (obj.getString("classtypename").equals(SMSSendingTask.class.getSimpleName())
				|| obj.getString("classtypename").equals(SMSSendingTaskInstance.class.getSimpleName())) {
			SMSSendingTaskInstance task = new SMSSendingTaskInstance();
			setCommonTaskProps(task, obj, currOwner, owner);
			if (!obj.isNull("startTime")) {
				task.setStartTime(obj.getLong("startTime"));
			}
			if (!obj.isNull("endTime")) {
				task.setEndTime(obj.getLong("endTime"));
			}
			if (!obj.isNull("definitionId")) {
				task.setDefinitionId(obj.getString("definitionId"));
			}
			if (!obj.isNull("templateId")) {
				task.setTemplateId(obj.getString("templateId"));
			}
			if (!obj.isNull("template")) {
				task.setTemplate(obj.getString("template"));
			}
			JSONArray jsonarrs = obj.getJSONArray("receivers");
			if (jsonarrs.length() > 0) {
				List<MessageReceiver> list = new ArrayList<MessageReceiver>();
				for (int i = 0; i < jsonarrs.length(); i++) {
					list.add(parseMessageReceivers(jsonarrs.getJSONObject(i)));
				}
				task.setReceivers(list.toArray(new MessageReceiver[list.size()]));
			}
			newprocess.addChild(task);
		} else if (obj.getString("classtypename").equals(SubprocessPoint.class.getSimpleName())
				|| obj.getString("classtypename").equals(SubprocessPointInstance.class.getSimpleName())) {
			SubprocessPointInstance task = new SubprocessPointInstance();
			setCommonTaskProps(task, obj, currOwner, owner);
			if (!obj.isNull("startTime")) {
				task.setStartTime(obj.getLong("startTime"));
			}
			if (!obj.isNull("endTime")) {
				task.setEndTime(obj.getLong("endTime"));
			}
			if (!obj.isNull("definitionId")) {
				task.setDefinitionId(obj.getString("definitionId"));
			}
			if (!obj.isNull("subprocessInstanceId")) {
				task.setSubprocessInstanceId(obj.getString("subprocessInstanceId"));
			}
			if (!obj.isNull("subprocessId")) {
				task.setSubprocessId(obj.getString("subprocessId"));
			}
			task.setSynchronised(obj.getBoolean("synchronised"));
			JSONArray jsonarr1 = obj.getJSONArray("subprocessInputs");
			if (jsonarr1.length() > 0) {
				List<Assignment> list = new ArrayList<Assignment>();
				for (int i = 0; i < jsonarr1.length(); i++) {
					Assignment a = new Assignment();
					a.setType(1);
					parseAssignments(a, jsonarr1.getJSONObject(i), currOwner, owner);
					list.add(a);
				}
				task.setSubprocessInputs(list.toArray(new Assignment[list.size()]));
			}
			JSONArray jsonarr2 = obj.getJSONArray("subprocessOutputs");
			if (jsonarr2.length() > 0) {
				List<Assignment> list = new ArrayList<Assignment>();
				for (int i = 0; i < jsonarr2.length(); i++) {
					Assignment a = new Assignment();
					parseAssignments(a, jsonarr2.getJSONObject(i), currOwner, owner);
					a.setType(2);
					list.add(a);
				}
				task.setSubprocessOutputs(list.toArray(new Assignment[list.size()]));
			}
			newprocess.addChild(task);
		} else if (obj.getString("classtypename").equals(SystemTask.class.getSimpleName())
				|| obj.getString("classtypename").equals(SystemTaskInstance.class.getSimpleName())) {
			SystemTaskInstance task = new SystemTaskInstance();
			setCommonTaskProps(task, obj, currOwner, owner);
			if (!obj.isNull("startTime")) {
				task.setStartTime(obj.getLong("startTime"));
			}
			if (!obj.isNull("endTime")) {
				task.setEndTime(obj.getLong("endTime"));
			}
			if (!obj.isNull("definitionId")) {
				task.setDefinitionId(obj.getString("definitionId"));
			}
			if (!obj.isNull("appServiceType")) {
				task.setAppServiceType(obj.getInt("appServiceType"));
			}
			if (!obj.isNull("appServiceId")) {
				task.setAppServiceId(obj.getString("appServiceId"));
			}
			if (!obj.isNull("appServiceName")) {
				task.setAppServiceName(obj.getString("appServiceName"));
			}
			if (!obj.isNull("hasSecurityAccessKey")) {
				task.setHasSecurityAccessKey(obj.getInt("hasSecurityAccessKey"));
			}
			if (!obj.isNull("securityAccessKey")) {
				task.setSecurityAccessKey(obj.getString("securityAccessKey"));
			}
			if (!obj.isNull("apiName")) {
				task.setAPIName(obj.getString("apiName"));
			}
			if (!obj.isNull("apiMethod")) {
				task.setAPIMethod(obj.getString("apiMethod"));
			}
			if (!obj.isNull("pathParameterString")) {
				task.setPathParameterString(obj.getString("pathParameterString"));
			}
			if (!obj.isNull("formParameterString")) {
				task.setFormParameterString(obj.getString("formParameterString"));
			}
			if (!obj.isNull("returnString")) {
				task.setReturnString(obj.getString("returnString"));
			}
			newprocess.addChild(task);
		} else if (obj.getString("classtypename").equals(WaitTask.class.getSimpleName())
				|| obj.getString("classtypename").equals(WaitTaskInstance.class.getSimpleName())) {
			WaitTaskInstance task = new WaitTaskInstance();
			setCommonTaskProps(task, obj, currOwner, owner);
			if (!obj.isNull("startTime")) {
				task.setStartTime(obj.getLong("startTime"));
			}
			if (!obj.isNull("endTime")) {
				task.setEndTime(obj.getLong("endTime"));
			}
			if (!obj.isNull("definitionId")) {
				task.setDefinitionId(obj.getString("definitionId"));
			}
			if (!obj.isNull("progress")) {
				task.setProgress(obj.getDouble("progress"));
			}
			if (!obj.isNull("waitTime")) {
				task.setWaitTime(obj.getLong("waitTime"));
			}
			// true: specific(fixed) time period; false: variable time period
			task.setSpecificDuration(obj.getBoolean("specificDuration"));
			if (!obj.isNull("timeRule")) {
				task.setTimeRule(obj.getString("timeRule"));
			}
			if (!obj.isNull("timeUnit")) {
				task.setTimeUnit(obj.getInt("timeUnit"));
			}
			if (!obj.isNull("largeDuration")) {
				task.setLargeDuration(obj.getInt("largeDuration"));
			}
			if (!obj.isNull("largeDurationUnit")) {
				// 0:day; 1:week; 2:fortnight: 3:month; 4:quarter
				task.setLargeDurationUnit(obj.getInt("largeDurationUnit"));
			}
			if (!obj.isNull("hours")) {
				task.setHours(obj.getInt("hours"));
			}
			if (!obj.isNull("minutes")) {
				task.setMinutes(obj.getInt("minutes"));
			}
			if (!obj.isNull("seconds")) {
				task.setSeconds(obj.getInt("seconds"));
			}
			if (!obj.isNull("milliseconds")) {
				task.setMilliseconds(obj.getInt("milliseconds"));
			}
			newprocess.addChild(task);
		}
	}

	private static void parseTransitionInstance(JSONArray jsonarr, WfProcessInstance newprocess) {
		// parsing input/output transitions
		for (int i = 0; i < jsonarr.length(); i++) {
			JSONObject child = jsonarr.getJSONObject(i);
			String ctype = child.getString("classtypename");
			if (!ctype.endsWith("Variable")) {
				JSONArray jsonarrOutputs = child.getJSONArray("outputs");
				if (jsonarrOutputs.length() > 0) {
					for (int j = 0; j < jsonarrOutputs.length(); j++) {
						JSONObject jst = jsonarrOutputs.getJSONObject(j);
						TransitionInstance t = new TransitionInstance();
						t.setId(jst.getString("id"));
						t.setName(jst.getString("name"));
						t.setOrderNumber(jst.getInt("orderNumber"));
						t.setOwner(jst.getString("owner"));
						t.setCurrOwner(jst.getString("currOwner"));
						t.setAlwaysTrue(jst.getBoolean("alwaysTrue"));
						if (!jst.isNull("navigationRule")) {
							t.setNavigationRule(jst.getString("navigationRule"));
						}
						if (!jst.isNull("definitionId")) {
							t.setDefinitionId(jst.getString("definitionId"));
						}
						AbstractTask source = (AbstractTask) newprocess.seekChildByID(jst.getString("source"));
						t.setSource(source);
						source.addOutput(t);
						AbstractTask target = (AbstractTask) newprocess.seekChildByID(jst.getString("target"));
						t.setTarget(target);
						target.addInput(t);
						if (!jst.isNull("bendpoints")) {
							JSONObject json = jst.getJSONObject("bendpoints");
							double x = json.getDouble("x");
							double y = json.getDouble("y");
							t.setBendPoint(new Location(x, y));
						}
					}
				}
			}
		}
	}

	public static ChatMessage parseChatMessage(JSONObject obj) throws Exception {
		ChatMessage msg = new ChatMessage();
		if (!obj.isNull("messageId"))
			msg.setMessageId(obj.getString("messageId"));
		if (!obj.isNull("message"))
			msg.setMessage(obj.getString("message"));
		if (!obj.isNull("senderId"))
			msg.setSenderId(obj.getString("senderId"));
		if (!obj.isNull("senderAvatarUrl"))
			msg.setSenderAvatarUrl(obj.getString("senderAvatarUrl"));
		if (!obj.isNull("senderName"))
			msg.setSenderName(obj.getString("senderName"));
		if (!obj.isNull("receiverId"))
			msg.setReceiverId(obj.getString("receiverId"));
		if (!obj.isNull("receiverAvatarUrl"))
			msg.setReceiverAvatarUrl(obj.getString("receiverAvatarUrl"));
		if (!obj.isNull("receiverName"))
			msg.setReceiverName(obj.getString("receiverName"));
		if (!obj.isNull("sendTime"))
			msg.setSendTime(obj.getLong("sendTime"));
		if (!obj.isNull("checkTime"))
			msg.setCheckTime(obj.getLong("checkTime"));
		if (!obj.isNull("receiveTime"))
			msg.setReceiveTime(obj.getLong("receiveTime"));
		if (!obj.isNull("messageStatus"))
			msg.setMessageType(obj.getString("messageStatus"));
		if (!obj.isNull("messageType"))
			msg.setMessageType(obj.getString("messageType"));
		JSONArray attachments = obj.getJSONArray("attachments");
		if (attachments != null && attachments.length() > 0) {
			List<FileConstant> list = new ArrayList<FileConstant>();
			for (int j = 0; j < attachments.length(); j++) {
				JSONObject jst = attachments.getJSONObject(j);
				FileConstant fc = new FileConstant();
				if (!jst.isNull("id"))
					fc.setId(jst.getString("id"));
				if (!jst.isNull("name"))
					fc.setName(jst.getString("name"));
				if (!jst.isNull("currOwner"))
					fc.setCurrOwner(jst.getString("currOwner"));
				if (!jst.isNull("owner"))
					fc.setOwner(jst.getString("owner"));
				if (!jst.isNull("value"))
					fc.setValue(jst.getString("value"));
				if (!jst.isNull("datatype"))
					fc.setDatatype(jst.getString("datatype"));
				if (!jst.isNull("suffix"))
					fc.setSuffix(jst.getString("suffix"));
				if (!jst.isNull("size"))
					fc.setSize(jst.getLong("size"));
				if (!jst.isNull("size"))
					fc.setLastupdate(jst.getString("lastupdate"));
				if (!jst.isNull("fileCount"))
					fc.setFileCount(jst.getLong("fileCount"));
				if (!jst.isNull("filetype"))
					fc.setFiletype(jst.getString("filetype"));
				if (!jst.isNull("isDirctory"))
					fc.setIsDirctory(jst.getInt("isDirctory"));
				list.add(fc);
			}
			msg.setAttachments(list.toArray(new FileConstant[list.size()]));
		}
		return msg;
	}

	public static Comment parseComment(JSONObject obj) throws Exception {
		Comment cmt = new Comment();
		if (!obj.isNull("commentId"))
			cmt.setCommentId(obj.getString("commentId"));
		if (!obj.isNull("senderId"))
			cmt.setSenderId(obj.getString("senderId"));
		if (!obj.isNull("senderAvatarUrl"))
			cmt.setSenderAvatarUrl(obj.getString("senderAvatarUrl"));
		if (!obj.isNull("senderName"))
			cmt.setSenderName(obj.getString("senderName"));
		if (!obj.isNull("articleId"))
			cmt.setArticleId(obj.getString("articleId"));
		if (!obj.isNull("sendTime"))
			cmt.setSendTime(obj.getLong("sendTime"));
		if (!obj.isNull("content"))
			cmt.setContent(obj.getString("content"));
		if (!obj.isNull("messageType"))
			cmt.setMessageType(obj.getString("messageType"));
		if (!obj.isNull("likeNumber"))
			cmt.setLikeNumber(obj.getLong("likeNumber"));
		JSONArray attachments = obj.getJSONArray("attachments");
		if (attachments != null && attachments.length() > 0) {
			List<FileConstant> list = new ArrayList<FileConstant>();
			for (int j = 0; j < attachments.length(); j++) {
				JSONObject jst = attachments.getJSONObject(j);
				FileConstant fc = new FileConstant();
				if (!jst.isNull("id"))
					fc.setId(jst.getString("id"));
				if (!jst.isNull("name"))
					fc.setName(jst.getString("name"));
				if (!jst.isNull("currOwner"))
					fc.setCurrOwner(jst.getString("currOwner"));
				if (!jst.isNull("owner"))
					fc.setOwner(jst.getString("owner"));
				if (!jst.isNull("value"))
					fc.setValue(jst.getString("value"));
				if (!jst.isNull("datatype"))
					fc.setDatatype(jst.getString("datatype"));
				if (!jst.isNull("suffix"))
					fc.setSuffix(jst.getString("suffix"));
				if (!jst.isNull("size"))
					fc.setSize(jst.getLong("size"));
				if (!jst.isNull("size"))
					fc.setLastupdate(jst.getString("lastupdate"));
				if (!jst.isNull("fileCount"))
					fc.setFileCount(jst.getLong("fileCount"));
				if (!jst.isNull("filetype"))
					fc.setFiletype(jst.getString("filetype"));
				if (!jst.isNull("isDirctory"))
					fc.setIsDirctory(jst.getInt("isDirctory"));
				list.add(fc);
			}
			cmt.setAttachments(list.toArray(new FileConstant[list.size()]));
		}
		JSONArray twoComments = obj.getJSONArray("attachments"); // 二级评论
		if (twoComments != null && twoComments.length() > 0) { // 二级评论
			List<SecondaryComment> list = new ArrayList<SecondaryComment>();
			for (int j = 0; j < twoComments.length(); j++) {
				JSONObject jst = twoComments.getJSONObject(j);
				SecondaryComment sc = new SecondaryComment();
				if (!jst.isNull("commentId"))
					sc.setCommentId(jst.getString("commentId"));
				if (!jst.isNull("senderId"))
					sc.setSenderId(jst.getString("senderId"));
				if (!jst.isNull("senderAvatarUrl"))
					sc.setSenderAvatarUrl(jst.getString("senderAvatarUrl"));
				if (!jst.isNull("senderName"))
					sc.setSenderName(jst.getString("senderName"));
				if (!jst.isNull("articleId"))
					sc.setArticleId(jst.getString("articleId"));
				if (!jst.isNull("sendTime"))
					sc.setSendTime(jst.getLong("sendTime"));
				if (!jst.isNull("content"))
					sc.setContent(jst.getString("content"));
				if (!jst.isNull("messageType"))
					sc.setMessageType(jst.getString("messageType"));
				if (!jst.isNull("likeNumber"))
					sc.setLikeNumber(jst.getLong("likeNumber"));
				if (!jst.isNull("receiverId"))
					sc.setReceiverId(jst.getString("receiverId"));
				if (!jst.isNull("receiverAvatarUrl"))
					sc.setReceiverAvatarUrl(jst.getString("receiverAvatarUrl"));
				if (!jst.isNull("receiverName"))
					sc.setReceiverName(jst.getString("receiverName"));
				list.add(sc);
			}
			cmt.setTwoComments(list.toArray(new SecondaryComment[list.size()]));
		}
		return cmt;
	}

}
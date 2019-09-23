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
package com.cloudibpm.core.communication;

import java.io.Serializable;

import com.cloudibpm.core.data.FileConstant;

public class ChatMessage implements Serializable, Comparable<ChatMessage> {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 5821231441377183466L;
	private String messageId;// 消息ID
	private String message; // 消息正文
	private String senderId; // 发送者的id，user ID
	private String senderAvatarUrl; // 发送者头像url
	private String senderName; // 发送者姓名
	private String receiverId; // 接受者的id
	private String receiverAvatarUrl; // 接受者的头像url
	private String receiverName; // 接受者的姓名
	private long sendTime = 0; // 消息的发送时间
	private long checkTime = 0; // 消息的重发时间
	private long receiveTime = 0; // 消息的接收时间
	private String messageStatus; // 消息状态 0 服务器端收到, 1 消息已被接收人接收
	private String messageType;// 消息类型 0 图片 ,1 文本
	private FileConstant[] attachments = new FileConstant[0];

	public String getMessageId() {
		return messageId;
	}

	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getSenderId() {
		return senderId;
	}

	public void setSenderId(String senderId) {
		this.senderId = senderId;
	}

	public String getSenderAvatarUrl() {
		return senderAvatarUrl;
	}

	public void setSenderAvatarUrl(String senderAvatarUrl) {
		this.senderAvatarUrl = senderAvatarUrl;
	}

	public String getSenderName() {
		return senderName;
	}

	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}

	public String getReceiverId() {
		return receiverId;
	}

	public void setReceiverId(String receiverId) {
		this.receiverId = receiverId;
	}

	public String getReceiverAvatarUrl() {
		return receiverAvatarUrl;
	}

	public void setReceiverAvatarUrl(String receiverAvatarUrl) {
		this.receiverAvatarUrl = receiverAvatarUrl;
	}

	public String getReceiverName() {
		return receiverName;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}

	public String getMessageStatus() {
		return messageStatus;
	}

	public void setMessageStatus(String messageStatus) {
		this.messageStatus = messageStatus;
	}

	public String getMessageType() {
		return messageType;
	}

	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}

	public long getSendTime() {
		return sendTime;
	}

	public void setSendTime(long sendTime) {
		this.sendTime = sendTime;
	}

	public long getCheckTime() {
		return checkTime;
	}

	public void setCheckTime(long checkTime) {
		this.checkTime = checkTime;
	}

	public long getReceiveTime() {
		return receiveTime;
	}

	public void setReceiveTime(long receiveTime) {
		this.receiveTime = receiveTime;
	}

	public FileConstant[] getAttachments() {
		return attachments;
	}

	public void setAttachments(FileConstant[] attachments) {
		this.attachments = attachments;
	}

	@Override
	public int compareTo(ChatMessage o) {
		if (this.getSendTime() - o.getSendTime() > 0) {
			return 1;
		} else if (this.getSendTime() - o.getSendTime() < 0) {
			return -1;
		}
		return 0;
	}

}

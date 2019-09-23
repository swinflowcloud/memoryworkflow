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

/**
 * @Titel: 标题
 * @Description: 描述
 * @Author: 作者
 * @CreateDate: 2019/3/18 16:55
 * @Version: 1.0
 */
public class SecondaryComment implements Comparable<SecondaryComment> {
	// 二级评论
	private String commentId = null; // 评论ID
	private String senderId = null; // 评论人的id
	private String senderAvatarUrl = null; // 评论人头像url
	private String senderName = null; // 评论人姓名
	private String receiverId = null; // 被评论人的id
	private String receiverAvatarUrl = null; // 被评论人头像url
	private String receiverName = null; // 被评论人姓名
	private String articleId = null; // 被评论的文章id
	private long sendTime = 0; // 评论的发送时间
	private String content = null; // 评论内容
	private String messageType = null; // 评论类型 0 图片 ,1 文本
	private long likeNumber = 0; // 点赞数量

	public String getCommentId() {
		return commentId;
	}

	public void setCommentId(String commentId) {
		this.commentId = commentId;
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

	public String getArticleId() {
		return articleId;
	}

	public void setArticleId(String articleId) {
		this.articleId = articleId;
	}

	public long getSendTime() {
		return sendTime;
	}

	public void setSendTime(long sendTime) {
		this.sendTime = sendTime;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getMessageType() {
		return messageType;
	}

	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}

	public long getLikeNumber() {
		return likeNumber;
	}

	public void setLikeNumber(long likeNumber) {
		this.likeNumber = likeNumber;
	}

	@Override
	public int compareTo(SecondaryComment o) {
		if (this.getSendTime() - o.getSendTime() > 0) {
			return 1;
		} else if (this.getSendTime() - o.getSendTime() < 0) {
			return -1;
		}
		return 0;
	}
}

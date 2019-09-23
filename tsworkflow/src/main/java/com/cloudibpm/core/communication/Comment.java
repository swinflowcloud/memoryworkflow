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

import com.cloudibpm.core.WorkflowEntity;
import com.cloudibpm.core.data.FileConstant;

public class Comment extends WorkflowEntity implements Comparable<Comment> {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 4960454391975761821L;
	// 一级评论
	private String commentId = null; // 评论ID
	private String senderId = null; // 评论人的id
	private String senderAvatarUrl = null; // 评论人头像url
	private String senderName = null; // 评论人姓名
	private String articleId = null; // 被评论的文章id
	private long sendTime = 0; // 评论的发送时间
	private String content = null; // 评论内容
	private String messageType = null; // 评论类型 0 图片 ,1 文本
	private long likeNumber = 0; // 点赞数量
	private SecondaryComment[] twoComments = new SecondaryComment[0]; // 二级评论
	private FileConstant[] attachments = new FileConstant[0]; // 二级评论

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

	public SecondaryComment[] getTwoComments() {
		return twoComments;
	}

	public void setTwoComments(SecondaryComment[] twoComments) {
		this.twoComments = twoComments;
	}

	public FileConstant[] getAttachments() {
		return attachments;
	}

	public void setAttachments(FileConstant[] attachments) {
		this.attachments = attachments;
	}

	@Override
	public int compareTo(Comment o) {
		if (this.getSendTime() - o.getSendTime() > 0) {
			return 1;
		} else if (this.getSendTime() - o.getSendTime() < 0) {
			return -1;
		}
		return 0;
	}

}

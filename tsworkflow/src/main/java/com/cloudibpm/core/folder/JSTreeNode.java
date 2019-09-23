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
package com.cloudibpm.core.folder;

import org.apache.commons.lang3.StringEscapeUtils;

public class JSTreeNode {
	public String id;
	public String text;
	public JSTreeNode[] children = null;
	public String parentId;
	public String data;
	public String icon;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public JSTreeNode[] getChildren() {
		return children;
	}

	public void setChildren(JSTreeNode[] children) {
		this.children = children;
	}

	public String toString() {
		return this.text;
	}

	public static String parseUTF8(String name) {
		return StringEscapeUtils.unescapeJava(name);
	}

	public static String toUTF8(String name) {
		return StringEscapeUtils.escapeJava(name);
	}

}

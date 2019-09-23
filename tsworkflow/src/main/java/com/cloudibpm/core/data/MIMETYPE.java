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

package com.cloudibpm.core.data;

import java.util.HashMap;
import java.util.Map;

public class MIMETYPE {
	private final static MIMETYPE instance = new MIMETYPE();
	private final static Map<String, String> types = new HashMap<String, String>();

	// https://www.sitepoint.com/mime-types-complete-list/
	// http://www.52im.net/thread-452-1-1.html
	private MIMETYPE() {
		types.put(".doc", "application/msword");
		types.put(".dot", "application/msword");
		types.put(".htm", "text/html");
		types.put(".html", "text/html");
		types.put(".xls", "application/vnd.ms-excel");
		types.put(".pdf", "	application/pdf");
		types.put(".jpeg", "image/jpeg");
		types.put(".jpg", "application/x-jpg");
		types.put(".gif", "image/gif");
	}

	public static MIMETYPE getInstance() {
		return instance;
	}

	public String getMIMEType(String sufix) {
		return types.get(sufix);
	}

}

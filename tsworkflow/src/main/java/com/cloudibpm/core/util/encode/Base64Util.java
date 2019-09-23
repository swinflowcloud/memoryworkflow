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
package com.cloudibpm.core.util.encode;

import java.util.Base64;

public class Base64Util {
	public static String encodeUrl(String url) {
		// encode data on your side using BASE64
		byte[] bytesEncoded = Base64.getUrlEncoder().encode(url.getBytes());
		return new String(bytesEncoded);
	}

	public static String decodeUrl(String url) {
		// Decode data on other side, by processing encoded data
		byte[] bytesDecoded = Base64.getUrlDecoder().decode(url.getBytes());
		return new String(bytesDecoded);
	}

	public static String encode(String str) {
		byte[] bytesEncoded = Base64.getEncoder().encode(str.getBytes());
		return new String(bytesEncoded);
	}

	public static String decode(String str) {
		byte[] bytesDecoded = Base64.getDecoder().decode(str.getBytes());
		return new String(bytesDecoded);
	}
}

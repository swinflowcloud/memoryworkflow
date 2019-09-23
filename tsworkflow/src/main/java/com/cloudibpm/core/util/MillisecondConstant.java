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
package com.cloudibpm.core.util;

public interface MillisecondConstant {
	/**
	 * Half-second is 500 milliseconds;
	 */
	int HALFSECOND = 500;
	/**
	 * One second is 1000 milliseconds;
	 */
	int SECOND = 1000;
	/**
	 * One minute is 1000 * 60 milliseconds;
	 */
	int MINUTE = 60000;
	/**
	 * One hour is 1000 * 60 * 60 milliseconds;
	 */
	int HOUR = 3600000;
	/**
	 * One day is 1000 * 60 * 60 * 24 milliseconds.
	 */
	int DAY = 86400000;
	/**
	 * One day is 1000 * 60 * 60 * 24 * 7 milliseconds;
	 */
	int WEEK = 604800000;
	/**
	 * One year is 1000 * 60 * 60 * 24 * 365 milliseconds;
	 */
	long YEAR = 31536000000L;
}

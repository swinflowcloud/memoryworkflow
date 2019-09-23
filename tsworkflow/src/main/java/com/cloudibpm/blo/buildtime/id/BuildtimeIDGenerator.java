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
package com.cloudibpm.blo.buildtime.id;

import com.cloudibpm.core.repository.BusinessLogicObject;
import com.cloudibpm.eso.idcache.IDGeneratorEso;

public class BuildtimeIDGenerator extends BusinessLogicObject {
	private IDGeneratorEso idGeneratorEso;

	public BuildtimeIDGenerator() {
		this.idGeneratorEso = new IDGeneratorEso();
	}

	public String getNewRunTimeID() throws Exception {
		return idGeneratorEso.generateRuntimeId();
	}

	
	public String getNewBuildTimeID() throws Exception {
		return idGeneratorEso.generateBuildtimeId();
	}

	
	public String getNewBuildTimeCode() throws Exception {
		return idGeneratorEso.generateBuildtimeSerialCode();
	}

	
	public String getNewBuildTimeVersionNo() throws Exception {
		return idGeneratorEso.generateNewVersionNo();
	}

	
	public String getNewBuildTimeUniCounting() throws Exception {
		return idGeneratorEso.generateBuildtimeUniCounting();
	}

//	public static void main(String [] args) {
//		try {
//			// 获取一个ID
//			String id = getNewBuildTimeID();
//			System.out.println(id);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

}

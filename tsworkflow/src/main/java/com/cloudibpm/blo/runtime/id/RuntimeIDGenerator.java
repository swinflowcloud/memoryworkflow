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
package com.cloudibpm.blo.runtime.id;

import javax.transaction.Transactional;

import com.cloudibpm.eso.runtime.idcache.IDGeneratorEso;
import com.cloudibpm.runtime.cache.EntityIdCache;

public class RuntimeIDGenerator {

	private static RuntimeIDGenerator instance = null;
	private final EntityIdCache idCache = new EntityIdCache();
	private final EntityIdCache codeCache = new EntityIdCache();

	private RuntimeIDGenerator() {
	}

	public static RuntimeIDGenerator getInstance() {
		if (instance == null) {
			instance = new RuntimeIDGenerator();
		}
		return instance;
	}

	@Transactional
	public EntityIdCache getCodeCache() {
		return codeCache;
	}

	@Transactional
	public EntityIdCache getIdCache() {
		return idCache;
	}

	@Transactional
	public String getNewRID() throws Exception {
		return idCache.fetchId();
	}

	@Transactional
	public String getNewWfCode() throws Exception {
		return codeCache.fetchId();
	}

	@Transactional
	public String getNewRunTimeID() throws Exception {
		return IDGeneratorEso.getInstance().generateRuntimeId();
	}

}

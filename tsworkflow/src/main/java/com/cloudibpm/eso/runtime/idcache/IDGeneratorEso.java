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
package com.cloudibpm.eso.runtime.idcache;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.TransactionStatus;

import com.cloudibpm.core.repository.ExecuteSQLObject;

/**
 * @author cdh
 * @date updated on 2017-09-28
 */
public class IDGeneratorEso extends ExecuteSQLObject {
	private final static IDGeneratorEso instance = new IDGeneratorEso();
	public final static byte MIN_CODE = 48;
	public final static byte MAX_CODE = 90;
	public final static byte MIN_ID = 48;
	public final static byte MAX_ID = 122;
	public final static int R_ID_SIZE = 32;
	public final static int B_ID_SIZE = 16;
	public final static int CODE_SIZE = 10;

	private byte runtimCode[] = new byte[R_ID_SIZE];
	
	public static IDGeneratorEso getInstance() {
		return instance;
	}

	private IDGeneratorEso() {
		logger = Logger.getLogger(IDGeneratorEso.class.getName());
	}

	/**
	 * Compute next new runtime object id.
	 * 
	 * @return
	 */
	private String getNextRuntimeID() {
		for (int i = runtimCode.length - 1; i >= 2; i--) {
			byte code = (byte) (runtimCode[i] + 1);
			boolean carryUp = false;
			byte newCode = code;
			if (code > MAX_ID) {
				newCode = MIN_ID;
				carryUp = true;
			}
			if (newCode == 58) {
				newCode = 65;
			}
			if (newCode == 90) {
				newCode = 97;
			}
			runtimCode[i] = newCode;
			if (!carryUp) {
				break;
			}
		}
		return new String(runtimCode);
	}

	/**
	 * Generate new <code>count</code> number of runtime IDs; These ID can be
	 * used to support the usage of whole bpm/workflow instances
	 * 
	 * @param count
	 * @return
	 * @throws Exception
	 */
	public String[] generateRuntimeIds(int count) throws Exception {
		String rids[] = null;
		TransactionStatus status = transactionManager.getTransaction(transactionDefinition);
		try {
			// get current id from repository;
			Object rid = jdbcTemplate.queryForObject("select RuntimeCode from idcache", new RowMapper<Object>() {
				public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
					return rs.getString(1);
				}
			});
			// compute next count (e.g. 1000) of id.
			runtimCode = ((String) rid).getBytes();
			rids = new String[count];
			for (int i = 0; i < count; i++) {
				rids[i] = getNextRuntimeID();
			}
			// update the new id into repository
			jdbcTemplate.update("update idcache set RuntimeCode=?", new PreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement stmt) throws SQLException {
					stmt.setString(1, new String(runtimCode));
				}
			});
		} catch (DataAccessException e) {
			transactionManager.rollback(status);
			throw e;
		}
		transactionManager.commit(status);
		return rids;
	}

	public String generateRuntimeId() throws Exception {
		String newRid = null;
		TransactionStatus status = transactionManager.getTransaction(transactionDefinition);
		try {
			// get current id from repository;
			Object rid = jdbcTemplate.queryForObject("select RuntimeCode from idcache", new RowMapper<Object>() {
				public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
					return rs.getString(1);
				}
			});
			// compute next count (e.g. 1000) of id.
			runtimCode = ((String) rid).getBytes();
			newRid = getNextRuntimeID();
			// update the new id into repository
			jdbcTemplate.update("update idcache set RuntimeCode=?", new PreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement stmt) throws SQLException {
					stmt.setString(1, new String(runtimCode));
				}
			});
		} catch (DataAccessException e) {
			transactionManager.rollback(status);
			throw e;
		}
		transactionManager.commit(status);
		return newRid;
	}
}
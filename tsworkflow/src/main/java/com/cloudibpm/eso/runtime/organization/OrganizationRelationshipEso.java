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
package com.cloudibpm.eso.runtime.organization;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;

//import com.cloudibpm.core.organization.OrganizationRelationship;
import com.cloudibpm.core.repository.ExecuteSQLObject;

public class OrganizationRelationshipEso extends ExecuteSQLObject {

	public OrganizationRelationshipEso() {
		super();
		logger = Logger.getLogger(OrganizationRelationshipEso.class.getName());
	}

//	public List<OrganizationRelationship> queryAllforSelect(String fk_organization, int valid, int[] relationships)
//			throws Exception {
//		spendtime = System.currentTimeMillis();
//		String sql = "select Fk_OrganizationB from om_organization_relationship "
//				+ "where Fk_OrganizationA=? and Valid=? and Relationship in (?, ?, ?, ?, ?)";
//		List<OrganizationRelationship> lst = jdbcTemplate.query(sql, new Object[] { fk_organization, valid,
//				relationships[0], relationships[1], relationships[2], relationships[3], relationships[4] },
//				new RowMapper<OrganizationRelationship>() {
//					public OrganizationRelationship mapRow(ResultSet rs, int rowNum) throws SQLException {
//						OrganizationRelationship r = new OrganizationRelationship();
//						r.setOrgBId(rs.getString("Fk_OrganizationB"));
//						return r;
//					}
//				});
//		logger.info((System.currentTimeMillis() - spendtime) + "ms");
//		return lst;
//	}

}

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
package com.cloudibpm.core.repository;

/**
 * Business Logic Object 是业务逻辑对象，这个类的设计初衷是，与界面的控制逻辑进行分离，<br>
 * 所有的业务逻辑都实现在该层，该层连接着下面的数据库访问层SQL执行对象(Execute SQL Object)，<br>
 * 该层的业务逻辑实现可以为将来设计工作流管理系统的访问接口提供基础。该层可以直接调用SQL执行对象，<br>
 * 同时也可以调用同层的其它业务逻辑对象，该层的所有方法现都设计成静态方法。
 * 
 * @author CAO Dahai
 * 
 */
public abstract class BusinessLogicObject {

}

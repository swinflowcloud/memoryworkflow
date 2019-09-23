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
package com.cloudibpm.core;

public class PageObject extends Page {

    private static final long serialVersionUID = -1047357675888370673L;
    private Object[] pageEntities = new Object[0];

    public PageObject(){

    }

    public PageObject(int pageNo, int pageSize){
        super(pageNo,pageSize);
    }

    public Object[] getPageEntities() {
        return pageEntities;
    }

    public void setPageEntities(Object[] pageEntities) {
        this.pageEntities = pageEntities;
    }

}
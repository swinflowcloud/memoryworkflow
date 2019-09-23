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
package com.cloudibpm.core.util.serviceresult;

import java.io.Serializable;

public class ServiceResult<T> implements Serializable {

    /**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1454429685846003884L;
	private T data;
    private boolean success =false;
    private CodeMessage codeMessage=new CodeMessage();

    public static <T> ServiceResult<T> success(){
        ServiceResult<T> serviceResult=new ServiceResult<>();
        serviceResult.success =true;
        CodeMessage codeMessage=new CodeMessage();
        codeMessage.setCode(1);
        codeMessage.setMessage("success");
        serviceResult.setCodeMessage(codeMessage);
        return serviceResult;
    }

    public static <T> ServiceResult<T> success(T data){
        ServiceResult<T> serviceResult=new ServiceResult<>();
        serviceResult.success =true;
        serviceResult.data=data;
        CodeMessage codeMessage=new CodeMessage();
        codeMessage.setCode(1);
        codeMessage.setMessage("success");
        serviceResult.setCodeMessage(codeMessage);
        return serviceResult;
    }

    public static <T> ServiceResult<T> error(int code, String message){
        ServiceResult<T> serviceResult=new ServiceResult<>();
        CodeMessage codeMessage=new CodeMessage();
        codeMessage.setCode(code);
        codeMessage.setMessage(message);
        serviceResult.codeMessage=codeMessage;
        return serviceResult;
    }

    public static <T> ServiceResult<T> exception(){
        ServiceResult<T> serviceResult=new ServiceResult<>();
        CodeMessage codeMessage=new CodeMessage();
        codeMessage.setCode(-1);
        codeMessage.setMessage("服务异常");
        serviceResult.codeMessage=codeMessage;
        return serviceResult;
    }

    public static <T> ServiceResult<T> exception(T data){
        ServiceResult<T> serviceResult=new ServiceResult<>();
        CodeMessage codeMessage=new CodeMessage();
        codeMessage.setCode(-1);
        codeMessage.setMessage("服务异常");
        serviceResult.codeMessage=codeMessage;
        serviceResult.data=data;
        return serviceResult;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public CodeMessage getCodeMessage() {
        return codeMessage;
    }

    public void setCodeMessage(CodeMessage codeMessage) {
        this.codeMessage = codeMessage;
    }
}

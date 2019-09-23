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
package com.cloudibpm.core.code;

public interface StatusCode {
    /* <pre>
     * Status code: positive is success code, negative is error code
     *
     * 2: email sent successfully
     * 1: successful
     * 0: user does't exist;
     * -1: password incorrect;
     * -2: be not a staff in all organizations;
     * -3: be a staff in some organization but has no authorizations in the organization yet;
     * -4: invalid login (might be hacker login);
     * -5: other error, initially, the last error code is -5,
     *     but it can't accurately describe the error, so added new error codes.
     * -6: email does not exists
     * -7: sent email failed
     * -8: security code checking failed.
     * -9: password expired;
     * -10: this account has been banned permanently;
     *
     * </pre>
     */
    int SUCCESS = 1;
    int USER_NOT_EXISTS = 0;
    int PASSWORD_INCORRECT = -1;
    int NOT_A_STAFF = -2;
    int NO_AUTHORIZATION = -3;
    int INVALID_LOGIN = -4;
    int OTHER_ERROR = -5;
    int EMAIL_ERROR = -6;
    int SEND_EMAIL_ERRROR = -7;
    int SECURITY_CODE_ERROR = -8;
    int PASSWORD_ERROR = -9;
    int BANNED = -10;

}

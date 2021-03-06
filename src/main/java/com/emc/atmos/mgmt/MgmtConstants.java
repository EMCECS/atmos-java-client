/*
 * BSD 3-Clause License
 *
 * Copyright (c) 2013-2018, Dell EMC
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *  Redistributions of source code must retain the above copyright notice, this
 *   list of conditions and the following disclaimer.
 *
 *  Redistributions in binary form must reproduce the above copyright notice,
 *   this list of conditions and the following disclaimer in the documentation
 *   and/or other materials provided with the distribution.
 *
 *  Neither the name of the copyright holder nor the names of its
 *   contributors may be used to endorse or promote products derived from
 *   this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 */
package com.emc.atmos.mgmt;

public final class MgmtConstants {
    public static final String X_PREFIX = "x-atmos-";

    public static final String XHEADER_SYSTEM_ADMIN = X_PREFIX + "systemadmin";
    public static final String XHEADER_SYSTEM_ADMIN_PASSWORD = X_PREFIX + "systemadminpassword";
    public static final String XHEADER_AUTH_TYPE = X_PREFIX + "authType";

    public static final String XHEADER_TENANT_ADMIN = X_PREFIX + "tenantadmin";
    public static final String XHEADER_TENANT_ADMIN_PASSWORD = X_PREFIX + "tenantadminpassword";
    public static final String XHEADER_SUB_TENANT_ADMIN = X_PREFIX + "subtenantadmin";
    public static final String XHEADER_SUB_TENANT_ADMIN_PASSWORD = X_PREFIX + "subtenantadminpassword";

    public static final String AUTHTYPE_PASSWORD = "password";

    public static final String PARAM_TENANT_NAME = "tenant_name";
    public static final String PARAM_SUB_TENANT_NAME = "sub_tenant_name";
    public static final String PARAM_USERNAME = "username";
    public static final String PARAM_PASSWORD = "password";

    private MgmtConstants() {
    }
}

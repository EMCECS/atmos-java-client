/*
 * Copyright (c) 2013-2016, EMC Corporation.
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted provided that the following conditions are met:
 *
 * + Redistributions of source code must retain the above copyright notice,
 *   this list of conditions and the following disclaimer.
 * + Redistributions in binary form must reproduce the above copyright
 *   notice, this list of conditions and the following disclaimer in the
 *   documentation and/or other materials provided with the distribution.
 * + The name of EMC Corporation may not be used to endorse or promote
 *   products derived from this software without specific prior written
 *   permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED
 * TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS
 * BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */
package com.emc.atmos.mgmt;

import com.sun.jersey.core.header.OutBoundHeaders;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SystemMgmtConfig extends AbstractMgmtConfig {
    private static final String PARAM_AUTH_TYPE = "auth_type";
    private static final String PARAM_USERNAME = "username";
    private static final String PARAM_PASSWORD = "password";

    public SystemMgmtConfig(String username, String password, URI... endpoints) {
        super(username, password, endpoints);
    }

    @Override
    public Map<String, List<Object>> getRestAuthenticationHeaders() {
        OutBoundHeaders authHeaders = new OutBoundHeaders();

        authHeaders.putSingle(MgmtConstants.XHEADER_SYSTEM_ADMIN, getUsername());
        authHeaders.putSingle(MgmtConstants.XHEADER_SYSTEM_ADMIN_PASSWORD, getPassword());
        authHeaders.putSingle(MgmtConstants.XHEADER_AUTH_TYPE, MgmtConstants.AUTHTYPE_PASSWORD);

        return authHeaders;
    }

    @Override
    public String getPoxLoginPath() {
        return "/mgmt_login/verify";
    }

    @Override
    public Map<String, String> getPoxLoginParams() {
        Map<String, String> loginParams = new HashMap<String, String>();
        loginParams.put(PARAM_AUTH_TYPE, "local");
        loginParams.put(PARAM_USERNAME, getUsername());
        loginParams.put(PARAM_PASSWORD, getPassword());
        return loginParams;
    }
}

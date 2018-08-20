/*
 * Copyright (c) 2018, EMC Corporation.
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
import java.util.List;
import java.util.Map;

public class SubTenantMgmtConfig extends AbstractMgmtConfig {
    public SubTenantMgmtConfig(String tenant, String subTenant, String username, String password, URI... endpoints) {
        super(username, password, endpoints);
        setContext(getContext() + "/tenants/" + tenant + "/subtenants/" + subTenant);
    }

    @Override
    public Map<String, List<Object>> getAuthenticationHeaders() {
        OutBoundHeaders authHeaders = new OutBoundHeaders();

        authHeaders.putSingle(MgmtConstants.XHEADER_TENANT_ADMIN, getUsername());
        authHeaders.putSingle(MgmtConstants.XHEADER_TENANT_ADMIN_PASSWORD, getPassword());
        authHeaders.putSingle(MgmtConstants.XHEADER_AUTH_TYPE, MgmtConstants.AUTHTYPE_PASSWORD);

        return authHeaders;
    }
}

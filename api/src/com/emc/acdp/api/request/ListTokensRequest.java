/**
 * 
 */
package com.emc.acdp.api.request;

import java.text.MessageFormat;

import com.emc.cdp.services.rest.model.TokenList;

/**
 * Lists tokens in a token group.
 * @author cwikj
 *
 */
public class ListTokensRequest extends AcdpXmlResponseRequest<TokenList> {
    private String accountId;
    private String adminSessionId;
    private int start;
    private int pageSize;
    private String subscriptionId;
    private String tokenGroupId;

    public ListTokensRequest(String accountId, String subscriptionId,
            String tokenGroupId, String adminSessionId) {
        this(accountId, subscriptionId, tokenGroupId, adminSessionId, 1, 1000);
    }

    public ListTokensRequest(String accountId, String subscriptionId,
            String tokenGroupId,
            String adminSessionId, int start, int count) {
        this.accountId = accountId;
        this.subscriptionId = subscriptionId;
        this.tokenGroupId = tokenGroupId;
        this.adminSessionId = adminSessionId;
        this.start = start;
        this.pageSize = count;
    }

    @Override
    public String getRequestPath() {
        return MessageFormat.format(
                "/cdp-rest/v1/admin/accounts/{0}/storage/{1}/tokengroups/{2}/tokens",
                accountId, subscriptionId, tokenGroupId);
    }

    @Override
    public String getRequestQuery() {
        return MessageFormat.format("{0}={1}&{2}={3}&{4}={5}",
                CDP_SESSION_PARAM, adminSessionId, CDP_START_PARAM, ""+start,
                CDP_COUNT_PARAM, ""+pageSize);
    }

    @Override
    public String getMethod() {
        return GET_METHOD;
    }

    @Override
    public long getRequestSize() {
        return -1;
    }

    @Override
    public byte[] getRequestData() {
        return null;
    }

    /**
     * @return the start
     */
    public int getStart() {
        return start;
    }

    /**
     * @param start the start to set
     */
    public void setStart(int start) {
        this.start = start;
    }

    /**
     * @return the pageSize
     */
    public int getPageSize() {
        return pageSize;
    }

    /**
     * @param pageSize the pageSize to set
     */
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

}

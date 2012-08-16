package com.emc.acdp.api.jaxrs;

import com.emc.acdp.api.AcdpConfig;
import com.emc.acdp.api.AcdpException;
import com.sun.jersey.api.client.ClientHandlerException;
import com.sun.jersey.api.client.ClientRequest;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.filter.ClientFilter;
import com.sun.jersey.core.util.MultivaluedMapImpl;

import javax.ws.rs.core.MultivaluedMap;
import java.net.URI;
import java.net.URISyntaxException;

public class AuthFilter extends ClientFilter {
    private static final String PARAM_SESSION_TOKEN = "cdp_session";
    private static final String PARAM_USER_ID = "cdp-identity-id";
    private static final String PARAM_PASSWORD = "cdp-password";

    private AcdpConfig config;

    public AuthFilter( AcdpConfig config ) {
        this.config = config;
    }

    @Override
    public ClientResponse handle( ClientRequest request ) throws ClientHandlerException {
        if ( config.getSessionToken() == null ) {

            // must login
            login( request );
        } else {
            attachSessionToken( request );
        }

        ClientResponse response = null;
        try {
            response = getNext().handle( request );

            // if unauthorized, try one more time after logging in
        } catch ( AcdpException e ) {
            if ( e.getHttpCode() == 401 ) {
                login( request );

                response = getNext().handle( (request) );
            } else {
                throw e;
            }
        }

        return response;
    }

    private void attachSessionToken( ClientRequest request ) {

        // append session token to query in URI
        String uriStr = request.getURI().toString();

        URI uri = request.getURI();
        if ( uri.getQuery() != null && uri.getQuery().length() > 0 )
            uriStr += "&";
        else
            uriStr += "?";

        uriStr += PARAM_SESSION_TOKEN + "=" + config.getSessionToken();

        try {
            request.setURI( new URI( uriStr ) );
        } catch ( URISyntaxException e ) {
            throw new RuntimeException( e );
        }
    }

    private void login( ClientRequest request ) {

        // hold existing request configuration (we can't create a new request here)
        String holdMethod = request.getMethod();
        URI holdUri = request.getURI();
        Object holdEntity = request.getEntity();
        String holdType = (String) request.getHeaders().getFirst( RestUtil.HEADER_CONTENT_TYPE );

        // login
        MultivaluedMap<String, String> params = new MultivaluedMapImpl();
        params.putSingle( PARAM_USER_ID, config.getUsername() );
        params.putSingle( PARAM_PASSWORD, config.getPassword() );

        request.setMethod( RestUtil.METHOD_POST );
        request.setURI( request.getURI().resolve( "/cdp-rest/v1/admin/login" ) );
        request.setEntity( params );
        request.getHeaders().putSingle( RestUtil.HEADER_CONTENT_TYPE, RestUtil.TYPE_FORM_DATA );

        ClientResponse response = getNext().handle( request );

        // get token from response
        String token = response.getEntity( String.class );
        //String token = new java.util.Scanner( response.getEntityInputStream(), "UTF-8" ).useDelimiter( "\\A" ).next();
        config.setSessionToken( token );

        // reset initial request configuration
        request.setMethod( holdMethod );
        request.setURI( holdUri );
        request.setEntity( holdEntity );
        request.getHeaders().putSingle( RestUtil.HEADER_CONTENT_TYPE, holdType );

        attachSessionToken( request );
    }
}

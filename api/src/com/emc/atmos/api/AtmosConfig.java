package com.emc.atmos.api;

import com.emc.atmos.AbstractConfig;
import org.apache.commons.codec.binary.Base64;

import java.io.UnsupportedEncodingException;
import java.net.URI;

public class AtmosConfig extends AbstractConfig {
    public static final String DEFAULT_CONTEXT = "/rest";

    private String tokenId;
    private byte[] secretKey;
    private long serverClockSkew;

    public AtmosConfig() {
        super( DEFAULT_CONTEXT );
    }

    public AtmosConfig( String tokenId, String secretKey, URI... endpoints ) {
        super( DEFAULT_CONTEXT, endpoints );
        this.tokenId = tokenId;
        try {
            this.secretKey = Base64.decodeBase64( secretKey.getBytes( "UTF-8" ) );
        } catch ( UnsupportedEncodingException e ) {
            throw new RuntimeException( "UTF-8 encoding isn't supported on this system", e ); // unrecoverable
        }
    }

    public byte[] getSecretKey() {
        return secretKey;
    }

    public void setSecretKey( byte[] secretKey ) {
        this.secretKey = secretKey;
    }

    public long getServerClockSkew() {
        return serverClockSkew;
    }

    public void setServerClockSkew( long serverClockSkew ) {
        this.serverClockSkew = serverClockSkew;
    }

    public String getTokenId() {
        return tokenId;
    }

    public void setTokenId( String tokenId ) {
        this.tokenId = tokenId;
    }
}

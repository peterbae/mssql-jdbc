package com.microsoft.sqlserver.jdbc;

import java.util.Date;

class SqlFedAuthToken {
     final Date expiresOn;
     final String accessToken;
     final String refreshToken;

    SqlFedAuthToken(final String accessToken, final String refreshToken,
            final long expiresIn) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;

        Date now = new Date();
        now.setTime(now.getTime() + (expiresIn * 1000));
        this.expiresOn = now;
    }
    
    SqlFedAuthToken(final String accessToken, final String refreshToken,
            final Date expiresOn) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.expiresOn = expiresOn;
    }

    Date getExpiresOnDate() {
        return expiresOn;
    }
}

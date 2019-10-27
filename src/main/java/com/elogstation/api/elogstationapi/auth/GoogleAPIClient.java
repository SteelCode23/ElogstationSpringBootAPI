package com.elogstation.api.elogstationapi.auth;

import feign.Param;
import feign.RequestLine;

public interface GoogleAPIClient {
    @RequestLine("GET /tokeninfo?id_token={id_token}")
    GoogleAPI getByTokenId(@Param("id_token") String id_token);

}

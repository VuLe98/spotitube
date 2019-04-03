package com.spotitube.services.login;

import javax.ws.rs.core.Response;

public interface LoginService {
    Response login(String username, String password);
}

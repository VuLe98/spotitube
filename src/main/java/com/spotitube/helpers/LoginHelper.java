package com.spotitube.helpers;

import com.spotitube.Token;
import com.spotitube.User;
import com.spotitube.exceptions.LoginException;

public interface LoginHelper {
    Token login(User user) throws LoginException;
}

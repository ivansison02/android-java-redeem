package com.ivan.sison.redeem.data.responses;

import com.ivan.sison.redeem.data.entities.User;

public class LoginResponse {

    private Response response;
    private User user;

    public LoginResponse(Response response, User user) {
        this.response = response;
        this.user = user;
    }

    public Response getResponse() {
        return response;
    }

    public User getUser() {
        return user;
    }
}

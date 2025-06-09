package com.freded.task.server.common;

import com.freded.task.server.common.annotation.LoggedInUser;
import io.quarkus.security.identity.SecurityIdentity;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;

@ApplicationScoped
public class UserProducer {

    @Inject
    SecurityIdentity securityIdentity;

    @Produces
    @LoggedInUser
    @RequestScoped
    public LoggedInUserInfo getLoggedInUser() {
        String username = securityIdentity.getPrincipal().getName();
        return new LoggedInUserInfo(username);
    }
}

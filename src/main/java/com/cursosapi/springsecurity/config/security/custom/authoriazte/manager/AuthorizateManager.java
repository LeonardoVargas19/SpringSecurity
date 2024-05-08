package com.cursosapi.springsecurity.config.security.custom.authoriazte.manager;

import com.cursosapi.springsecurity.persistence.entity.security.Operation;
import com.cursosapi.springsecurity.persistence.repository.OperationRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Supplier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class AuthorizateManager implements AuthorizationManager<RequestAuthorizationContext> {

    @Autowired
    private OperationRepository operationRepository;
    @Override
    public void verify(Supplier<Authentication> authentication, RequestAuthorizationContext object) {
        AuthorizationManager.super.verify(authentication, object);
    }

    @Override
    public AuthorizationDecision check(Supplier<Authentication> authentication, RequestAuthorizationContext requestContex) {
        HttpServletRequest request = requestContex.getRequest();

        System.out.println(request.getRequestURI());
        String url = extracURL(request);
        String httpMethod = request.getMethod();

        boolean isPublic = isPublic(url,httpMethod);

        return new AuthorizationDecision(isPublic);
    }

    private boolean isPublic(String url, String httpMethod) {



        List<Operation> publicAccessEndpoints = operationRepository
                .findByPucliccAcces();

        boolean isPublic = publicAccessEndpoints.stream().anyMatch( operation ->{
            String basePath = operation.getModule().getBasePath();
            Pattern pattern = Pattern.compile(basePath.concat(operation.getPath()));
            Matcher matcher = pattern.matcher(url);
            return matcher.matches();
        });
        System.out.println("Is Public"+isPublic);



        return isPublic;

    }

    private String extracURL(HttpServletRequest request) {

        String contextPath = request.getContextPath();
        String url = request.getRequestURI();
        url=url.replace(contextPath,"");
        System.out.println(url);
        return url;

    }
}

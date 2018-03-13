package com.blacknebula.gisthub.github;

import com.blacknebula.gisthub.config.ApplicationProperties;
import com.blacknebula.gisthub.security.OauthAuthenticationToken;
import com.blacknebula.gisthub.security.jwt.JWTConfigurer;
import com.blacknebula.gisthub.security.jwt.TokenProvider;
import com.blacknebula.gisthub.user.User;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/api")
public class GithubOauthController {

    private final GithubService githubService;
    private final TokenProvider tokenProvider;
    private final AuthenticationManager authenticationManager;
    private final ApplicationProperties applicationProperties;

    public GithubOauthController(GithubService githubService, TokenProvider tokenProvider, AuthenticationManager authenticationManager, ApplicationProperties applicationProperties) {
        this.githubService = githubService;
        this.tokenProvider = tokenProvider;
        this.authenticationManager = authenticationManager;
        this.applicationProperties = applicationProperties;
    }

    @RequestMapping(value = "/github/oauth", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView redirectToDropboxOauthUri() {
        return new ModelAndView("redirect:" + githubService.buildOauthUri());
    }

    @RequestMapping(value = "/github/callback", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView callback(@RequestParam(name = "code", defaultValue = "") String code, @RequestParam(name = "state", defaultValue = "") String state) {
        githubService.oauthCallback(code, state);
        return new ModelAndView("redirect:" + applicationProperties.getGithub().getRedirectUrl());
    }

    @RequestMapping(value = "/github/callback/success", method = RequestMethod.GET)
    @ResponseBody
    public String redirect() {
        return "success!";
    }
}

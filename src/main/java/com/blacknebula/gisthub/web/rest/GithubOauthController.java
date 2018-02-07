package com.blacknebula.gisthub.web.rest;

import com.blacknebula.gisthub.service.GithubService;
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

    public GithubOauthController(GithubService githubService) {
        this.githubService = githubService;
    }

    @RequestMapping(value = "/github/oauth", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView redirectToDropboxOauthUri() {
        return new ModelAndView("redirect:" + githubService.buildOauthUri());
    }

    @RequestMapping(value = "/github/callback", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView callback(@RequestParam(name = "code", defaultValue = "") String code, @RequestParam(name = "state", defaultValue = "") String state) {
        return new ModelAndView("redirect:" + githubService.oauthCallback(code, state));
    }

    @RequestMapping(value = "/github/callback/success", method = RequestMethod.GET)
    @ResponseBody
    public String redirect() {
        return "success!";
    }
}

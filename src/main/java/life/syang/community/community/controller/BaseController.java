package life.syang.community.community.controller;

import life.syang.community.community.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BaseController {
    @Autowired
    protected UserUtil userUtil;
    @Autowired
    protected HttpServletRequest request;
    @Autowired
    protected HttpServletResponse response;
}

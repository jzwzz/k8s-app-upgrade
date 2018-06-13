package com.jzwzz.k8sdemo.k8sappupgrade;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class SessionTestController {
    @RequestMapping(value = "/getSessionId")
    @ResponseBody
    public String getSessionId(HttpServletRequest request) {

        Object o = request.getSession().getAttribute("springboot");
        if (o == null) {
            o = "create by port of " + request.getLocalPort();
            request.getSession().setAttribute("springboot", o);
        }

        return "port=" + request.getLocalPort() + " sessionId=" + request.getSession().getId() + " = " + o;
    }
}

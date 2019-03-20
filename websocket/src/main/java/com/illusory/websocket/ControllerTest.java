package com.illusory.websocket;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author illusory
 */
@Controller
public class ControllerTest {
    @RequestMapping(value = "/test")
    @ResponseBody
    public String test() {
        return "hello websocket";
    }


}

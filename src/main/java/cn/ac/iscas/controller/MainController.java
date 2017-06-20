package cn.ac.iscas.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by admin on 2017/4/19.
 */
public class MainController {
    public static final String INDEX = "index";

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return INDEX;
    }
}

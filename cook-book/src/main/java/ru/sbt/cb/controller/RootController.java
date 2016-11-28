package ru.sbt.cb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Root контроллер.
 */
@Controller
public class RootController {

    /**
     * Root страница приложения.
     *
     * @return root страницу
     */
    @RequestMapping(value = {"/"})
    public String root() {
        return "root/root";
    }
}

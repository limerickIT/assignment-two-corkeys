/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sd4.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Jordan
 */
@RestController

public class ExceptonController {
    
        @ExceptionHandler(value=Exception.class)
    public ModelAndView HandleException(HttpServletRequest req, Exception ex)
    {
        System.out.println("That coursed an problem "+ex);
        
        return new ModelAndView("/error","message",ex);
    }
}

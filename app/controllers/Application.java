package controllers;

import play.*;
import play.mvc.*;

import java.util.*;
import java.io.File;

import models.*;

public class Application extends Controller {

    public static void index() {
        Admin.index();
    }
    
    static void onDisconnected() {
        Admin.index();
    }
    
    public static void onAuthenticated() {
        Admin.index();
    }

    public static void upload(){
        Admin.upload();
    }
    
    public static void charts(Long id){        
        render();
    }
    
    public static void student(Long id){
        Student student = Student.findById(id);
        render(student);
    }
    
}
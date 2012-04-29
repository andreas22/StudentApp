/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import models.*;

/**
 *
 * @author ac1982
 */
public class Security extends Secure.Security {
    static boolean authenticate(String username, String password) {        
        return User.connect(username, password) != null;
    }
    
    static boolean isConnected(){
        return true;
    }
    
    static String connected(){
        return "andreas22@gmail.com";
    }
}

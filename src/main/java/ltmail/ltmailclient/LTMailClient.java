package ltmail.ltmailclient;

import java.io.IOException;
import java.net.Socket;
import ltmail.ltmailclient.forms.LoginForm;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



/**
 *
 * @author qquan
 */
public class LTMailClient {
    
    public static void main(String[] args) {
        
        boolean initSuccessfully = true;
        
        try {
            Application.init();
        } catch (IOException e) {
            initSuccessfully = false;
            System.out.println("Unable to connect to the server. "
                    + "Shutting down...");
        }
        
        if (initSuccessfully) {
            Application.getInstance().run();
        }
        
    }
    
}

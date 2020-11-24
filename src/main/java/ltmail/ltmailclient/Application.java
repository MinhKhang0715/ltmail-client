/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ltmail.ltmailclient;

import java.io.IOException;
import java.net.Socket;
import ltmail.ltmailclient.forms.LoginForm;

/**
 *
 * @author qquan
 */
public class Application {
    
    public static final String SERVER_ADDRESS = "127.0.0.1";
    public static final int SERVER_PORT = 5000;
    
    public static Application getInstance() {
        if (instance == null) {
            try {
                instance = new Application();
                System.out.println("Connected to server");
            } catch(IOException ie) {
                System.out.println("Unable to connect to server. Shutting down...");
            }
        }
        
        return instance;
    }
    
    public void run() {
        loginForm.setEnabled(true);
    }

    private Application() throws IOException {
        socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
        
        loginForm = new LoginForm();
    }
    
    private static Application instance;
    private final Socket socket;
    
    private LoginForm loginForm;
    
}


package ltmail.ltmailclient;

import java.util.logging.Logger;
import java.net.Socket;
import java.io.IOException;

public class Application {
    
    public static final String SERVER_ADDRESS = "127.0.0.1";
    public static final int SERVER_PORT = 5000;
    
    public static Application getInstance() {
        if (instance == null) {
            try {
                instance = new Application();
                Logger.getGlobal().info("Connected to server");
            } catch(IOException ie) {
                Logger.getGlobal().severe("Cannot connect to server. Shutting down...");
                System.exit(-1);
            }
        }
        
        return instance;
    }
    
    public void run() {
        Logger.getGlobal().info("Application began running");
    }

    private Application() throws IOException {
        socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
    }
    
    private static Application instance;
    private final Socket socket;
}

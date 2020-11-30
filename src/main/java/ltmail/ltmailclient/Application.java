
package ltmail.ltmailclient;

import java.util.logging.Logger;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Scanner;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import org.json.JSONObject;

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
                System.exit(0);
            }
        }
        
        return instance;
    }
    
    public void run() {
        Logger.getGlobal().info("Application began running");
    }
    
    public JSONObject sendRequest(JSONObject req) {
        try {
            output.write(req.toString());
            output.append("\n");
            output.flush();
        } catch(IOException e) {
            return new JSONObject()
                    .put("success", false)
                    .put("errorCode", "UNEXPECTEDLY_DISCONNECTED");
        }
        
        LocalDateTime start = LocalDateTime.now();
        
        while (!input.hasNext()) {
            
            if (Duration.between(start, LocalDateTime.now())
                    == Duration.ofSeconds(20)) {
                
                return new JSONObject()
                        .put("success", false)
                        .put("errorCode", "TIMEOUT");
            }
            
        }

        return new JSONObject(input.nextLine());
        
    }

    private Application() throws IOException {
        socket = (SSLSocket) SSLSocketFactory
                .getDefault()
                .createSocket(SERVER_ADDRESS, SERVER_PORT);
        input = new Scanner(socket.getInputStream());
        output = new OutputStreamWriter(socket.getOutputStream());
    }
    
    private static Application instance;
    private final SSLSocket socket;
    private final Scanner input;
    private final OutputStreamWriter output;
}

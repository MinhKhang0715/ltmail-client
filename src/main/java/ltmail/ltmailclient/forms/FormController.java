    
package ltmail.ltmailclient.forms;

import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import ltmail.ltmailclient.Application;
import org.json.JSONObject;

public class FormController {
    
    public FormController(Application app) {
        forms = new HashMap<>();
        this.app = app;
        
        if (this.app != null) {
            Logger.getGlobal().severe("Failed to construct FormController:"
                    + "`app` field is required");
            System.exit(0);
        }
    }
    
    public FormController addForm(String name, JFrame frame) {
        
        if (forms.containsKey(name)) {
            
            Logger.getGlobal().log(Level.WARNING,
                    "Adding form `{0}` failed. The form already exists", name);
            return this;
            
        } else {
            
            forms.put(name, frame);
            Logger.getGlobal().log(Level.INFO,
                    "Form `{0}` added successfully", name);
            return this;
            
        }
    }
    
    public JFrame changeForm(String name) {
        if (!forms.containsKey(name)) {
            
            Logger.getGlobal().log(Level.WARNING,
                    "Form `{0}` doesn''t exists", name);
            return null;
            
        } else {
            
            Logger.getGlobal().log(Level.INFO,
                    "Changing to form `{0}`...", name);
            return forms.get(name);
            
        }
    }
    
    public JSONObject sendRequest(JSONObject req) {
        return app.sendRequest(req);
    }
    
    private final HashMap<String, JFrame> forms;
    private final Application app;
}

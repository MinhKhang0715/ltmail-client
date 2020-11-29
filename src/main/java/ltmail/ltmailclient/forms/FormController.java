
package ltmail.ltmailclient.forms;

import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

public class FormController {
    
    public FormController() {
        forms = new HashMap<>();
    }
    
    public FormController addForm(String name, JFrame frame) {
        
        if (forms.containsKey(name)) {
            
            Logger.getGlobal().log(Level.WARNING,
                    "Form `{0}` already exists. Skipping the operation...", name);
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
    
    private final HashMap<String, JFrame> forms;
    
}

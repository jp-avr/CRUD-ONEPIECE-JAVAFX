package gui.util;

import javafx.event.ActionEvent;
import javafx.stage.Stage;
import javafx.scene.Node;

public class Utils {
    
    public static Stage currentStage(ActionEvent event){
        return (Stage) ((Node) event.getSource()).getScene().getWindow(); //Serve para acessar o Stage onde o controller que recebeu o evento está
    }

    public static Integer tryParseToInt(String str) {
        try{
            return Integer.parseInt(str);
        }catch(NumberFormatException e) {
            return null;
        }
        
    }
}

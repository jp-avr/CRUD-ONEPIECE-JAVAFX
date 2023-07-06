package gui;

import java.net.URL;
import java.util.ResourceBundle;

import gui.util.Constraints;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class AliancaFormController implements Initializable {

    @FXML
    private TextField txtCodAlianca;

    @FXML
    private TextField txtNome;

    @FXML
    private Label labelErrorCodAlianca;

    @FXML
    private Label labelErrorName;

    @FXML
    private Button btSalvar;

    @FXML
    private Button btCancelar;

    @FXML
    public void onBtSalvarAction(){
        System.out.println("onBtSalvarAction");
    }

    @FXML
    public void onBtCancelarAction(){
        System.out.println("onBtCancelarAction");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {  
        initializeNodes();      
    }

    private void initializeNodes(){
        Constraints.setTextFieldInteger(txtCodAlianca);
        Constraints.setTextFieldMaxLength(txtNome, 30);
    }
    
    
}

package gui;

import java.net.URL;
import java.util.ResourceBundle;

import gui.util.Constraints;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class AkumaNoMiFormController implements Initializable {

    @FXML
    private TextField txtCodFruta;

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtCodTipo;

    @FXML
    private TextField txtCodPersonagem;

    @FXML
    private Label labelErrorCodFruta;

    @FXML
    private Label labelErrorName;

    @FXML
    private Label labelErrorCodTipo;

    @FXML
    private Label labelErrorCodPersonagem;

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
        Constraints.setTextFieldInteger(txtCodFruta);
        Constraints.setTextFieldInteger(txtCodTipo);
        Constraints.setTextFieldInteger(txtCodPersonagem);
        Constraints.setTextFieldMaxLength(txtNome, 30);
    }
    
    
}

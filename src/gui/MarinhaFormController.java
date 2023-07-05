package gui;

import java.net.URL;
import java.util.ResourceBundle;

import gui.util.Constraints;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class MarinhaFormController implements Initializable {

    @FXML
    private TextField txtCodMarinha;

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtRecompensa;

    @FXML
    private TextField txtCodIlha;

    @FXML
    private TextField txtCodTripulacao;

    @FXML
    private Label labelErrorCodMarinha;

    @FXML
    private Label labelErrorName;

    @FXML
    private Label labelErrorRecompensa;

    @FXML
    private Label labelErrorCodIlha;

    @FXML
    private Label labelErrorCodTripulacao;

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
        Constraints.setTextFieldInteger(txtCodIlha);
        Constraints.setTextFieldInteger(txtCodMarinha);
        Constraints.setTextFieldInteger(txtCodTripulacao);
        Constraints.setTextFieldInteger(txtRecompensa);
        Constraints.setTextFieldMaxLength(txtNome, 30);
    }
    
    
}

package gui;

import java.net.URL;
import java.util.ResourceBundle;

import gui.util.Constraints;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.entities.Pirata;

public class PirataFormController implements Initializable {

    private Pirata entity;

    @FXML
    private TextField txtCodPirata;

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtRecompensa;

    @FXML
    private TextField txtCodIlha;

    @FXML
    private TextField txtCodTripulacao;

    @FXML
    private Label labelErrorCodPirata;

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

    public void setPirata(Pirata entity) {
        this.entity = entity;
    }

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
        Constraints.setTextFieldInteger(txtCodPirata);
        Constraints.setTextFieldInteger(txtCodTripulacao);
        Constraints.setTextFieldInteger(txtRecompensa);
        Constraints.setTextFieldMaxLength(txtNome, 30);
    }

    public void updateFormData() {
        if (entity == null) {
            throw new IllegalStateException("Entity tava vazio");
        }
        txtCodPirata.setText(String.valueOf(entity.getCod_pirata())); //Como ele só trabalha com string usamos o valueOf
        txtNome.setText(entity.getNome());
        txtRecompensa.setText(String.valueOf(entity.getRecompensa()));
        txtCodIlha.setText(String.valueOf(entity.getCod_ilha()));
        txtCodTripulacao.setText(String.valueOf(entity.getCod_tripulacao()));

    }
    
    
}

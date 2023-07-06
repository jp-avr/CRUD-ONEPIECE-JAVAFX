package gui;

import java.net.URL;
import java.util.ResourceBundle;

import gui.util.Constraints;
import gui.util.Utils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.entities.Marinha;
import model.services.MarinhaService;
import db.DbException;
import gui.util.Alerts;
import javafx.scene.control.Alert.AlertType;
import javafx.event.ActionEvent;

public class MarinhaFormController implements Initializable {

    private Marinha entity;

    private MarinhaService service;

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

    public void setMarinha(Marinha entity) {
        this.entity = entity;
    }

    public void setMarinhaService(MarinhaService service){
        this.service = service;
    }

    @FXML
    public void onBtSalvarAction(ActionEvent event){
	if (entity == null) {
		throw new IllegalStateException("Entitiy nulo");
	}
	if (service == null) {
		throw new IllegalStateException("Service nulo");
	}
	try {
        	entity = getFormData(); //Responsável por pegar os dados do formulário
        	service.saveOrUpdate(entity);
		Utils.currentStage(event).close();
	}catch (DbException e) {
		Alerts.showAlert("Error Saving Object", null, e.getMessage(), AlertType.ERROR);
    	}
}

    private Marinha getFormData() { //ELE PEGA OS DADOS DO FORMULÁRIO E RETORNA O DADO PRA MIM
        Marinha obj = new Marinha();

        obj.setCod_marinha(Utils.tryParseToInt(txtCodMarinha.getText()));
        obj.setNome(txtNome.getText());
        obj.setRecompensa(Utils.tryParseToInt(txtRecompensa.getText()));
        obj.setCod_ilha(Utils.tryParseToInt(txtCodIlha.getText())); 
        obj.setCod_tripulacao(Utils.tryParseToInt(txtCodTripulacao.getText()));

        return obj;
    }

    @FXML
    public void onBtCancelarAction(ActionEvent event){
        Utils.currentStage(event).close();
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

    public void updateFormData() {
        
        txtCodMarinha.setText(String.valueOf(entity.getCod_marinha())); //Como ele só trabalha com string usamos o valueOf
        txtNome.setText(entity.getNome());
        txtRecompensa.setText(String.valueOf(entity.getRecompensa()));
        txtCodIlha.setText(String.valueOf(entity.getCod_ilha()));
        txtCodTripulacao.setText(String.valueOf(entity.getCod_tripulacao())); 
    }
    
    
}

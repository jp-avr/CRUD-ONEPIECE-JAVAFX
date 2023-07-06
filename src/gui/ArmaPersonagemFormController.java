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
import model.entities.ArmaPersonagem;
import model.services.ArmaPersonagemService;
import db.DbException;
import gui.util.Alerts;
import javafx.scene.control.Alert.AlertType;
import javafx.event.ActionEvent;

public class ArmaPersonagemFormController implements Initializable {

    private ArmaPersonagem entity;

    private ArmaPersonagemService service;

    @FXML
    private TextField txtCodArmaPersonagem;

    @FXML
    private TextField txtCodArma;

    @FXML
    private TextField txtCodPersonagem;

    @FXML
    private Label labelErrorCodArmaPersonagem;

    @FXML
    private Label labelErrorCodArma;

    @FXML
    private Label labelErrorCodPersonagem;

    @FXML
    private Button btSalvar;

    @FXML
    private Button btCancelar;

    public void setArmaPersonagem(ArmaPersonagem entity) {
        this.entity = entity;
    }

    public void setArmaPersonagemService(ArmaPersonagemService service){
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

    private ArmaPersonagem getFormData() { //ELE PEGA OS DADOS DO FORMULÁRIO E RETORNA O DADO PRA MIM
        ArmaPersonagem obj = new ArmaPersonagem();

        obj.setCod_armapersonagem(Utils.tryParseToInt(txtCodArmaPersonagem.getText()));
        obj.setCod_personagem(Utils.tryParseToInt(txtCodPersonagem.getText()));
        obj.setCod_arma(Utils.tryParseToInt(txtCodArma.getText()));

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
        Constraints.setTextFieldInteger(txtCodArmaPersonagem);
        Constraints.setTextFieldInteger(txtCodArma);
        Constraints.setTextFieldInteger(txtCodPersonagem);
    }

    public void updateFormData() {
        txtCodArmaPersonagem.setText(String.valueOf(entity.getCod_armapersonagem()));
        txtCodPersonagem.setText(String.valueOf(entity.getCod_personagem())); //Como ele só trabalha com string usamos o valueOf
        txtCodArma.setText(String.valueOf(entity.getCod_arma()));
    }
    
    
}

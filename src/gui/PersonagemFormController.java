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
import model.entities.Personagem;
import model.services.PersonagemService;
import db.DbException;
import gui.util.Alerts;
import javafx.scene.control.Alert.AlertType;
import javafx.event.ActionEvent;

public class PersonagemFormController implements Initializable {

    private Personagem entity;

    private PersonagemService service;

    @FXML
    private TextField txtCodPersonagem;

    @FXML
    private TextField txtCodPirata;

    @FXML
    private TextField txtCodMarinha;    

    @FXML
    private Label labelErrorCodPersonagem;

    @FXML
    private Label labelErrorCodPirata;

    @FXML
    private Label labelErrorCodMarinha;

    @FXML
    private Button btSalvar;

    @FXML
    private Button btCancelar;

    public void setPersonagem(Personagem entity) {
        this.entity = entity;
    }

    public void setPersonagemService(PersonagemService service){
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

    private Personagem getFormData() { //ELE PEGA OS DADOS DO FORMULÁRIO E RETORNA O DADO PRA MIM
        Personagem obj = new Personagem();

        obj.setCod_personagem(Utils.tryParseToInt(txtCodPersonagem.getText()));
        obj.setCod_pirata(Utils.tryParseToInt(txtCodPirata.getText()));
        obj.setCod_marinha(Utils.tryParseToInt(txtCodMarinha.getText()));


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
        Constraints.setTextFieldInteger(txtCodPersonagem);
        Constraints.setTextFieldInteger(txtCodPirata);
        Constraints.setTextFieldInteger(txtCodMarinha);
    }

    public void updateFormData() {
        
        txtCodPersonagem.setText(String.valueOf(entity.getCod_personagem())); //Como ele só trabalha com string usamos o valueOf
        txtCodPirata.setText(String.valueOf(entity.getCod_pirata()));
        txtCodMarinha.setText(String.valueOf(entity.getCod_marinha()));
 
    }
    
    
}

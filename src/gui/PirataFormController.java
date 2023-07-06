package gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import db.DbException;
import gui.listener.DataChangeListener;
import gui.util.Alerts;
import javafx.scene.control.Alert.AlertType;
import javafx.event.ActionEvent;
import gui.util.Constraints;
import gui.util.Utils;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import model.entities.Pirata;
import model.services.PirataService;

public class PirataFormController implements Initializable {

    private Pirata entity;

    private PirataService service;

    private List<DataChangeListener> dataChangeListeners = new ArrayList<>(); //permite outros objetos se inscreverem e receberem o evento

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

    public void setPirataService(PirataService service){
        this.service = service;
    }

    public void subscribeDataChangeListener(DataChangeListener listener) {
        dataChangeListeners.add(listener);
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
            notifyDataChangeListeners();
		    Utils.currentStage(event).close();
	}catch (DbException e) {
		Alerts.showAlert("Error Saving Object", null, e.getMessage(), AlertType.ERROR);
    	}
    }

    private void notifyDataChangeListeners() {
        for  (DataChangeListener listener : dataChangeListeners) {
            listener.onDataChanged();
        }
    }

    private Pirata getFormData() { //ELE PEGA OS DADOS DO FORMULÁRIO E RETORNA O DADO PRA MIM
        Pirata obj = new Pirata();

        obj.setCod_pirata(Utils.tryParseToInt(txtCodPirata.getText()));
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
        Constraints.setTextFieldInteger(txtCodPirata);
        Constraints.setTextFieldInteger(txtCodTripulacao);
        Constraints.setTextFieldInteger(txtRecompensa);
        Constraints.setTextFieldMaxLength(txtNome, 30);
    }

    public void updateFormData() {
        
        txtCodPirata.setText(String.valueOf(entity.getCod_pirata())); //Como ele só trabalha com string usamos o valueOf
        txtNome.setText(entity.getNome());
        txtRecompensa.setText(String.valueOf(entity.getRecompensa()));
        txtCodIlha.setText(String.valueOf(entity.getCod_ilha()));
        txtCodTripulacao.setText(String.valueOf(entity.getCod_tripulacao()));
    }
    
    
}

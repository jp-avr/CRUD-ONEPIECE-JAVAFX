package gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import db.DbException;
import gui.listener.DataChangeListener;
import gui.util.Alerts;
import gui.util.Constraints;
import gui.util.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.entities.Arco;
import model.services.ArcoService;

public class ArcoFormController implements Initializable {

    private Arco entity;

    private ArcoService service;

    private List<DataChangeListener> dataChangeListeners = new ArrayList<>();

    @FXML
    private TextField txtCodArco;

    @FXML
    private TextField txtNome;

    @FXML
    private Label labelErrorCodArco;

    @FXML
    private Label labelErrorName;

    @FXML
    private Button btSalvar;

    @FXML
    private Button btCancelar;

    public void setArco(Arco entity) {
        this.entity = entity;
    }

    public void setArcoService(ArcoService service){
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

    private Arco getFormData() { //ELE PEGA OS DADOS DO FORMULÁRIO E RETORNA O DADO PRA MIM
        Arco obj = new Arco();

        obj.setCod_arco(Utils.tryParseToInt(txtCodArco.getText()));
        obj.setNome(txtNome.getText());

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
        Constraints.setTextFieldInteger(txtCodArco);
        Constraints.setTextFieldMaxLength(txtNome, 30);
    }

    public void updateFormData() {
        
        txtCodArco.setText(String.valueOf(entity.getCod_arco())); //Como ele só trabalha com string usamos o valueOf
        txtNome.setText(entity.getNome());
    }
    
    
}

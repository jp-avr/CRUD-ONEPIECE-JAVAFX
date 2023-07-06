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
import model.entities.Alianca;
import model.services.AliancaService;

public class AliancaFormController implements Initializable {

    private Alianca entity;

    private AliancaService service;

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

    public void setAlianca(Alianca entity) {
        this.entity = entity;
    }

    public void setAliancaService(AliancaService service){
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

    private Alianca getFormData() { //ELE PEGA OS DADOS DO FORMULÁRIO E RETORNA O DADO PRA MIM
        Alianca obj = new Alianca();

        obj.setCod_alianca(Utils.tryParseToInt(txtCodAlianca.getText()));
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
        Constraints.setTextFieldInteger(txtCodAlianca);
        Constraints.setTextFieldMaxLength(txtNome, 30);
    }

    public void updateFormData() {
        
        txtCodAlianca.setText(String.valueOf(entity.getCod_alianca())); //Como ele só trabalha com string usamos o valueOf
        txtNome.setText(entity.getNome()); 
    }
    
    
}

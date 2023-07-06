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
import model.entities.Arco;
import model.services.ArcoService;

public class ArcoFormController implements Initializable {

    private Arco entity;

    private ArcoService service;
    

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

    @FXML
    public void onBtSalvarAction(){
        entity = getFormData(); //Responsável por pegar os dados do formulário
        service.saveOrUpdate(entity);
    }

    private Arco getFormData() { //ELE PEGA OS DADOS DO FORMULÁRIO E RETORNA O DADO PRA MIM
        Arco obj = new Arco();

        obj.setCod_arco(Utils.tryParseToInt(txtCodArco.getText()));
        obj.setNome(txtNome.getText());

        return obj;
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
        Constraints.setTextFieldInteger(txtCodArco);
        Constraints.setTextFieldMaxLength(txtNome, 30);
    }

    public void updateFormData() {
        
        txtCodArco.setText(String.valueOf(entity.getCod_arco())); //Como ele só trabalha com string usamos o valueOf
        txtNome.setText(entity.getNome());
    }
    
    
}

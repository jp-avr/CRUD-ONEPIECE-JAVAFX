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
import model.entities.Arma;
import model.services.ArmaService;

public class ArmaFormController implements Initializable {

    private Arma entity;

    private ArmaService service;

    @FXML
    private TextField txtCodArma;

    @FXML
    private TextField txtNome;

    @FXML
    private Label labelErrorCodArma;

    @FXML
    private Label labelErrorName;

    @FXML
    private Button btSalvar;

    @FXML
    private Button btCancelar;

    public void setArma(Arma entity) {
        this.entity = entity;
    }

    public void setArmaService(ArmaService service){
        this.service = service;
    }

    @FXML
    public void onBtSalvarAction(){
        entity = getFormData(); //Responsável por pegar os dados do formulário
        service.saveOrUpdate(entity);
    }

    private Arma getFormData() { //ELE PEGA OS DADOS DO FORMULÁRIO E RETORNA O DADO PRA MIM
        Arma obj = new Arma();

        obj.setCod_arma(Utils.tryParseToInt(txtCodArma.getText()));
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
        Constraints.setTextFieldInteger(txtCodArma);
        Constraints.setTextFieldMaxLength(txtNome, 30);
    }

     public void updateFormData() {
        
        txtCodArma.setText(String.valueOf(entity.getCod_arma())); //Como ele só trabalha com string usamos o valueOf
        txtNome.setText(entity.getNome());
    }
    
    
}

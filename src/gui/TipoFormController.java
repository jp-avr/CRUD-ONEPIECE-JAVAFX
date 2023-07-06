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
import model.entities.Tipo;
import model.services.TipoService;

public class TipoFormController implements Initializable {

    private Tipo entity;

    private TipoService service;

    @FXML
    private TextField txtCodTipo;

    @FXML
    private TextField txtNome;

    @FXML
    private Label labelErrorCodTipo;

    @FXML
    private Label labelErrorName;

    @FXML
    private Button btSalvar;

    @FXML
    private Button btCancelar;

    public void setTipo(Tipo entity) {
        this.entity = entity;
    }

    public void setTipoService(TipoService service){
        this.service = service;
    }

    @FXML
    public void onBtSalvarAction(){
        entity = getFormData(); //Responsável por pegar os dados do formulário
        service.saveOrUpdate(entity);
    }

    private Tipo getFormData() { //ELE PEGA OS DADOS DO FORMULÁRIO E RETORNA O DADO PRA MIM
        Tipo obj = new Tipo();

        obj.setCod_tipo(Utils.tryParseToInt(txtCodTipo.getText()));
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
        Constraints.setTextFieldInteger(txtCodTipo);
        Constraints.setTextFieldMaxLength(txtNome, 30);
    }
    
    public void updateFormData() {
        
        txtCodTipo.setText(String.valueOf(entity.getCod_tipo())); //Como ele só trabalha com string usamos o valueOf
        txtNome.setText(entity.getNome());
    }
    
}

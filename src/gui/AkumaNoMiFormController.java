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
import model.entities.AkumaNoMi;
import model.services.AkumaNoMiService;

public class AkumaNoMiFormController implements Initializable {

    private AkumaNoMi entity;

    private AkumaNoMiService service;

    @FXML
    private TextField txtCodFruta;

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtCodTipo;

    @FXML
    private TextField txtCodPersonagem;

    @FXML
    private Label labelErrorCodFruta;

    @FXML
    private Label labelErrorName;

    @FXML
    private Label labelErrorCodTipo;

    @FXML
    private Label labelErrorCodPersonagem;

    @FXML
    private Button btSalvar;

    @FXML
    private Button btCancelar;

    public void setAkumaNoMi(AkumaNoMi entity) {
        this.entity = entity;
    }

    public void setAkumaNoMiService(AkumaNoMiService service){
        this.service = service;
    }

    @FXML
    public void onBtSalvarAction(){
        entity = getFormData(); //Responsável por pegar os dados do formulário
        service.saveOrUpdate(entity);
    }

    private AkumaNoMi getFormData() { //ELE PEGA OS DADOS DO FORMULÁRIO E RETORNA O DADO PRA MIM
        AkumaNoMi obj = new AkumaNoMi();

        obj.setCod_fruta(Utils.tryParseToInt(txtCodFruta.getText()));
        obj.setNome(txtNome.getText());
        obj.setCod_tipo(Utils.tryParseToInt(txtCodTipo.getText()));
        obj.setCod_personagem(Utils.tryParseToInt(txtCodPersonagem.getText()));

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
        Constraints.setTextFieldInteger(txtCodFruta);
        Constraints.setTextFieldInteger(txtCodTipo);
        Constraints.setTextFieldInteger(txtCodPersonagem);
        Constraints.setTextFieldMaxLength(txtNome, 30);
    }

    public void updateFormData() {
        
        txtCodFruta.setText(String.valueOf(entity.getCod_fruta())); //Como ele só trabalha com string usamos o valueOf
        txtNome.setText(entity.getNome());
        txtCodTipo.setText(String.valueOf(entity.getCod_tipo()));
        txtCodPersonagem.setText(String.valueOf(entity.getCod_personagem()));
    }
    
    
}

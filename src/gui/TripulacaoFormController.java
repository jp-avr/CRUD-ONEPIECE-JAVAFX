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
import model.entities.Tripulacao;
import model.services.TripulacaoService;

public class TripulacaoFormController implements Initializable {

    private Tripulacao entity;

    private TripulacaoService service;

    @FXML
    private TextField txtCodTripulacao;

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtCodAlianca;


    @FXML
    private Label labelErrorCodTripulacao;

    @FXML
    private Label labelErrorName;

    @FXML
    private Label labelErrorCodAlianca;

    @FXML
    private Button btSalvar;

    @FXML
    private Button btCancelar;

    public void setTripulacao(Tripulacao entity) {
        this.entity = entity;
    }

    public void setTripulacaoService(TripulacaoService service){
        this.service = service;
    }

    @FXML
    public void onBtSalvarAction(){
        entity = getFormData(); //Responsável por pegar os dados do formulário
        service.saveOrUpdate(entity);
    }

    private Tripulacao getFormData() { //ELE PEGA OS DADOS DO FORMULÁRIO E RETORNA O DADO PRA MIM
        Tripulacao obj = new Tripulacao();

        obj.setCod_tripulacao(Utils.tryParseToInt(txtCodTripulacao.getText()));
        obj.setNome(txtNome.getText());
        obj.setCod_alianca(Utils.tryParseToInt(txtCodAlianca.getText()));

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
        Constraints.setTextFieldInteger(txtCodTripulacao);
        Constraints.setTextFieldInteger(txtCodAlianca);
        Constraints.setTextFieldMaxLength(txtNome, 30);
    }

    public void updateFormData() {
        
        txtCodTripulacao.setText(String.valueOf(entity.getCod_tripulacao())); //Como ele só trabalha com string usamos o valueOf
        txtNome.setText(entity.getNome());
        txtCodAlianca.setText(String.valueOf(entity.getCod_alianca()));
 
    }
    
    
}

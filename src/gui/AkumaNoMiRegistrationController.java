package gui;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.entities.AkumaNoMi;
import model.services.AkumaNoMiService;

public class AkumaNoMiRegistrationController implements Initializable{

    private AkumaNoMiService service;

    @FXML
    private TableView<AkumaNoMi> tableViewPirata;

    @FXML
    private TableColumn<AkumaNoMi, Integer> TableColumnCodFruta;

    @FXML
    private TableColumn<AkumaNoMi, String> TableColumnNome;

    @FXML
    private TableColumn<AkumaNoMi, Integer> TableColumnCodTipo;

    @FXML
    private TableColumn<AkumaNoMi, Integer> TableColumnCodPersonagem;

    @FXML
    private Button btNew;

    private ObservableList<AkumaNoMi> obsList;

    @FXML
    public void onBtNewAction() {
        System.out.println("onBtNewAction");
    }

    public void setAkumaNoMiService(AkumaNoMiService service) {
        this.service = service;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initializeNodes();
    }

    private void initializeNodes() {
        TableColumnCodFruta.setCellValueFactory(new PropertyValueFactory<>("cod_fruta"));
        TableColumnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        TableColumnCodTipo.setCellValueFactory(new PropertyValueFactory<>("cod_tipo"));
        TableColumnCodPersonagem.setCellValueFactory(new PropertyValueFactory<>("cod_personagem"));

        Stage stage = (Stage) Main.getMainScene().getWindow(); //Como Window é superclasse do Stage fazemos um down casting
        tableViewPirata.prefHeightProperty().bind(stage.heightProperty()); //Somente para o TableView acompanhar o tamanho da janela

    }

    public void updateTableView() {
        if (service == null) {
            throw new IllegalStateException("Service was null"); 
        }
        List<AkumaNoMi> list = service.findAll();
        obsList = FXCollections.observableArrayList(list);
        tableViewPirata.setItems(obsList);
    }
    
}

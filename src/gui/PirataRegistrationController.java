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
import model.entities.Pirata;
import model.services.PirataService;

public class PirataRegistrationController implements Initializable{

    private PirataService service;

    @FXML
    private TableView<Pirata> tableViewPirata;

    @FXML
    private TableColumn<Pirata, Integer> TableColumnCodPirata;

    @FXML
    private TableColumn<Pirata, String> TableColumnNome;

    @FXML
    private TableColumn<Pirata, Double> TableColumnRecompensa;

    @FXML
    private TableColumn<Pirata, Integer> TableColumnCodIlha;

    @FXML
    private TableColumn<Pirata, Integer> TableColumnCodTripulacao;

    @FXML
    private Button btNew;

    private ObservableList<Pirata> obsList;

    @FXML
    public void onBtNewAction() {
        System.out.println("onBtNewAction");
    }

    public void setPirataService(PirataService service) {
        this.service = service;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initializeNodes();
    }

    private void initializeNodes() {
        TableColumnCodPirata.setCellValueFactory(new PropertyValueFactory<>("cod_pirata"));
        TableColumnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        TableColumnRecompensa.setCellValueFactory(new PropertyValueFactory<>("recompensa"));
        TableColumnCodIlha.setCellValueFactory(new PropertyValueFactory<>("cod_ilha"));
        TableColumnCodTripulacao.setCellValueFactory(new PropertyValueFactory<>("cod_tripulacao"));

        Stage stage = (Stage) Main.getMainScene().getWindow(); //Como Window é superclasse do Stage fazemos um down casting
        tableViewPirata.prefHeightProperty().bind(stage.heightProperty()); //Somente para o TableView acompanhar o tamanho da janela

    }

    public void updateTableView() {
        if (service == null) {
            throw new IllegalStateException("Service was null"); 
        }
        List<Pirata> list = service.findAll();
        obsList = FXCollections.observableArrayList(list);
        tableViewPirata.setItems(obsList);
    }
    
}

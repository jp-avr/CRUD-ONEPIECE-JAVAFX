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
import model.entities.Marinha;
import model.services.MarinhaService;

public class MarinhaRegistrationController implements Initializable{

    private MarinhaService service;

    @FXML
    private TableView<Marinha> tableViewPirata;

    @FXML
    private TableColumn<Marinha, Integer> TableColumnCodMarinha;

    @FXML
    private TableColumn<Marinha, String> TableColumnNome;

    @FXML
    private TableColumn<Marinha, Double> TableColumnRecompensa;

    @FXML
    private TableColumn<Marinha, Integer> TableColumnCodIlha;

    @FXML
    private TableColumn<Marinha, Integer> TableColumnCodTripulacao;

    @FXML
    private Button btNew;

    private ObservableList<Marinha> obsList;

    @FXML
    public void onBtNewAction() {
        System.out.println("onBtNewAction");
    }

    public void setMarinhaService(MarinhaService service) {
        this.service = service;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initializeNodes();
    }

    private void initializeNodes() {
        TableColumnCodMarinha.setCellValueFactory(new PropertyValueFactory<>("cod_marinha"));
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
        List<Marinha> list = service.findAll();
        obsList = FXCollections.observableArrayList(list);
        tableViewPirata.setItems(obsList);
    }
    
}

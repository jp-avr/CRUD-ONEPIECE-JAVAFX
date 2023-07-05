package gui;

import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.entities.Pirata;

public class PirataRegistrationController implements Initializable{

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

    @FXML
    public void onBtNewAction() {
        System.out.println("onBtNewAction");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initializeNodes();
    }

    private void initializeNodes() {
        TableColumnCodPirata.setCellValueFactory(new PropertyValueFactory<>("cod_pirata"));
        TableColumnNome.setCellValueFactory(new PropertyValueFactory<>("name"));
        TableColumnRecompensa.setCellValueFactory(new PropertyValueFactory<>("recompensa"));
        TableColumnCodIlha.setCellValueFactory(new PropertyValueFactory<>("cod_ilha"));
        TableColumnCodTripulacao.setCellValueFactory(new PropertyValueFactory<>("cod_tripulacao"));

        Stage stage = (Stage) Main.getMainScene().getWindow(); //Como Window é superclasse do Stage fazemos um down casting
        tableViewPirata.prefHeightProperty().bind(stage.heightProperty()); //Somente para o TableView acompanhar o tamanho da janela

    }
    
}

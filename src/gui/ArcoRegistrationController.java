package gui;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import application.Main;
import gui.util.Alerts;
import gui.util.Utils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.entities.Arco;
import model.services.ArcoService;

public class ArcoRegistrationController implements Initializable{

    private ArcoService service;

    @FXML
    private TableView<Arco> tableViewPirata;

    @FXML
    private TableColumn<Arco, Integer> TableColumnCodArco;

    @FXML
    private TableColumn<Arco, String> TableColumnNome;

    @FXML
    private Button btNew;

    private ObservableList<Arco> obsList;

    @FXML
    public void onBtNewAction(ActionEvent event) {
        Stage parentStage = Utils.currentStage(event);
        createDialogForm("/gui/ArcoForm.fxml", parentStage);
    }

    public void setArcoService(ArcoService service) {
        this.service = service;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initializeNodes();
    }

    private void initializeNodes() {
        TableColumnCodArco.setCellValueFactory(new PropertyValueFactory<>("cod_arco"));
        TableColumnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));

        Stage stage = (Stage) Main.getMainScene().getWindow(); //Como Window é superclasse do Stage fazemos um down casting
        tableViewPirata.prefHeightProperty().bind(stage.heightProperty()); //Somente para o TableView acompanhar o tamanho da janela

    }

    public void updateTableView() {
        if (service == null) {
            throw new IllegalStateException("Service was null"); 
        }
        List<Arco> list = service.findAll();
        obsList = FXCollections.observableArrayList(list);
        tableViewPirata.setItems(obsList);
    }

    //FUNÇÃO PARA CARREGAR OS DADOS DO FORMULÁRIO
    private void createDialogForm(String absoluteName, Stage parentStage) {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
            Pane pane = loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Preencha os dados");
            dialogStage.setScene(new Scene(pane));
            dialogStage.setResizable(false);
            dialogStage.initOwner(parentStage);
            dialogStage.initModality(Modality.WINDOW_MODAL); //Enquanto não fechar não pode acessar a janela de trás
            dialogStage.showAndWait();
        }catch(IOException e) {
            Alerts.showAlert("IO Exception", "ERROR loading view", e.getMessage(), AlertType.ERROR);
        }
    }
    
}

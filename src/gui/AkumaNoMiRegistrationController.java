package gui;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import application.Main;
import gui.listener.DataChangeListener;
import gui.util.Alerts;
import gui.util.Utils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.entities.AkumaNoMi;
import model.services.AkumaNoMiService;

public class AkumaNoMiRegistrationController implements Initializable, DataChangeListener{

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
    public void onBtNewAction(ActionEvent event) {
        Stage parentStage = Utils.currentStage(event);
        AkumaNoMi obj = new AkumaNoMi();
        createDialogForm(obj, "/gui/AkumaNoMiForm.fxml", parentStage);
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

    private void createDialogForm(AkumaNoMi obj, String absoluteName, Stage parentStage) {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
            Pane pane = loader.load();
            
            AkumaNoMiFormController controller = loader.getController();
            controller.setAkumaNoMi(obj);
            controller.setAkumaNoMiService(new AkumaNoMiService());
            controller.subscribeDataChangeListener(this);
            controller.updateFormData();

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

    public void onDataChanged() {
        updateTableView();
    }
    
}

package gui;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import application.Main;
import gui.listener.DataChangeListener;
import gui.util.Alerts;
import gui.util.Utils;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.entities.Marinha;
import model.services.MarinhaService;

public class MarinhaRegistrationController implements Initializable, DataChangeListener{

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
    private TableColumn<Marinha, Marinha> TableColumnEDIT;

    @FXML
    private Button btNew;

    private ObservableList<Marinha> obsList;

    @FXML
    public void onBtNewAction(ActionEvent event) {
        Stage parentStage = Utils.currentStage(event);
        Marinha obj = new Marinha();
        createDialogForm(obj,"/gui/MarinhaForm.fxml", parentStage);        
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
        initEditButtons();
    }

    private void createDialogForm(Marinha obj, String absoluteName, Stage parentStage) {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
            Pane pane = loader.load();

            MarinhaFormController controller = loader.getController();
            controller.setMarinha(obj);
            controller.setMarinhaService(new MarinhaService());
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

    private void initEditButtons() {
        TableColumnEDIT.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
        TableColumnEDIT.setCellFactory(param -> new TableCell<Marinha, Marinha>() {
            private final Button button = new Button("Editar");

            @Override
            protected void updateItem(Marinha obj, boolean empty) {
                super.updateItem(obj, empty);

                if (obj == null) {
                    setGraphic(null);
                    return;
                }

                setGraphic(button);
                button.setOnAction(
                    event -> createDialogForm(obj, "/gui/MarinhaForm.fxml", Utils.currentStage(event)));
            }           
        });
    }
    
}

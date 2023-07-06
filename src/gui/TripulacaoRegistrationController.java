package gui;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import application.Main;
import db.DbIntegrityException;
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
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.entities.Tripulacao;
import model.services.TripulacaoService;

public class TripulacaoRegistrationController implements Initializable, DataChangeListener{

    private TripulacaoService service;

    @FXML
    private TableView<Tripulacao> tableViewPirata;

    @FXML
    private TableColumn<Tripulacao, Integer> TableColumnCodTripulacao;

    @FXML
    private TableColumn<Tripulacao, String> TableColumnNome;

    @FXML
    private TableColumn<Tripulacao, Integer> TableColumnCodAlianca;

    @FXML
    private TableColumn<Tripulacao, Tripulacao> TableColumnEDIT;

    @FXML
    private TableColumn<Tripulacao, Tripulacao> TableColumnREMOVE;

    @FXML
    private Button btNew;

    private ObservableList<Tripulacao> obsList;

    @FXML
    public void onBtNewAction(ActionEvent event) {
        Stage parentStage = Utils.currentStage(event);
        Tripulacao obj = new Tripulacao();
        createDialogForm(obj, "/gui/TripulacaoForm.fxml", parentStage);
    }

    public void setTripulacaoService(TripulacaoService service) {
        this.service = service;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initializeNodes();
    }

    private void initializeNodes() {
        TableColumnCodTripulacao.setCellValueFactory(new PropertyValueFactory<>("cod_tripulacao"));
        TableColumnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        TableColumnCodAlianca.setCellValueFactory(new PropertyValueFactory<>("cod_alianca"));

        Stage stage = (Stage) Main.getMainScene().getWindow(); //Como Window é superclasse do Stage fazemos um down casting
        tableViewPirata.prefHeightProperty().bind(stage.heightProperty()); //Somente para o TableView acompanhar o tamanho da janela

    }

    public void updateTableView() {
        if (service == null) {
            throw new IllegalStateException("Service was null"); 
        }
        List<Tripulacao> list = service.findAll();
        obsList = FXCollections.observableArrayList(list);
        tableViewPirata.setItems(obsList);
        initEditButtons();
        initRemoveButtons();
    }

    //FUNÇÃO PARA CARREGAR OS DADOS DO FORMULÁRIO
    private void createDialogForm(Tripulacao obj, String absoluteName, Stage parentStage) {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
            Pane pane = loader.load();

            TripulacaoFormController controller = loader.getController();
            controller.setTripulacao(obj);
            controller.setTripulacaoService(new TripulacaoService());
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
        TableColumnEDIT.setCellFactory(param -> new TableCell<Tripulacao, Tripulacao>() {
            private final Button button = new Button("Editar");

            @Override
            protected void updateItem(Tripulacao obj, boolean empty) {
                super.updateItem(obj, empty);

                if (obj == null) {
                    setGraphic(null);
                    return;
                }

                setGraphic(button);
                button.setOnAction(
                    event -> createDialogForm(obj, "/gui/TripulacaoForm.fxml", Utils.currentStage(event)));
            }           
        });
    }

    private void initRemoveButtons() {
        TableColumnREMOVE.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
        TableColumnREMOVE.setCellFactory(param -> new TableCell<Tripulacao, Tripulacao>() {
            private final Button button = new Button("Remover");

            @Override
            protected void updateItem(Tripulacao obj, boolean empty) {
                super.updateItem(obj, empty);

                if (obj == null) {
                    setGraphic(null);
                    return;
                }

                setGraphic(button);
                button.setOnAction(event -> removeEntity(obj));
            }           
        });
    }

    private void removeEntity(Tripulacao obj) {
        Optional<ButtonType> result = Alerts.showConfirmation("Confirmation", "Tem certeza que irá deletar?");

        if (result.get() == ButtonType.OK) {
            if (service == null) {
                throw new IllegalStateException("Service estava nulo");
            }
            try {
                service.remove(obj);
                updateTableView();
            }catch (DbIntegrityException e) {
                Alerts.showAlert("ERRO AO REMOVER OBJETO", null, e.getMessage(), AlertType.ERROR);
            }
            
        }
    }
    
}

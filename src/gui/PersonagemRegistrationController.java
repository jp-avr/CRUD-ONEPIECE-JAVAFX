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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.entities.Personagem;
import model.services.PersonagemService;

public class PersonagemRegistrationController implements Initializable, DataChangeListener{

    private PersonagemService service;

    @FXML
    private TableView<Personagem> tableViewPirata;

    @FXML
    private TableColumn<Personagem, Integer> TableColumnCodPersonagem;

    @FXML
    private TableColumn<Personagem, String> TableColumnCodPirata;

    @FXML
    private TableColumn<Personagem, Integer> TableColumnCodMarinha;

    @FXML
    private TableColumn<Personagem, Personagem> TableColumnEDIT;

    @FXML
    private TableColumn<Personagem, Personagem> TableColumnREMOVE;

    @FXML
    private Button btNew;

    private ObservableList<Personagem> obsList;

    @FXML
    public void onBtNewAction(ActionEvent event) {
        Stage parentStage = Utils.currentStage(event);
        Personagem obj = new Personagem();
        createDialogForm(obj, "/gui/PersonagemForm.fxml", parentStage);
    }

    public void setPersonagemService(PersonagemService service) {
        this.service = service;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initializeNodes();
    }

    private void initializeNodes() {
        TableColumnCodPersonagem.setCellValueFactory(new PropertyValueFactory<>("cod_personagem"));
        TableColumnCodPirata.setCellValueFactory(new PropertyValueFactory<>("cod_pirata"));
        TableColumnCodMarinha.setCellValueFactory(new PropertyValueFactory<>("cod_marinha"));

        Stage stage = (Stage) Main.getMainScene().getWindow(); //Como Window é superclasse do Stage fazemos um down casting
        tableViewPirata.prefHeightProperty().bind(stage.heightProperty()); //Somente para o TableView acompanhar o tamanho da janela

    }

    public void updateTableView() {
        if (service == null) {
            throw new IllegalStateException("Service was null"); 
        }
        List<Personagem> list = service.findAll();
        obsList = FXCollections.observableArrayList(list);
        tableViewPirata.setItems(obsList);
        initEditButtons();
        initRemoveButtons();
    }

    private void createDialogForm(Personagem obj, String absoluteName, Stage parentStage) {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
            Pane pane = loader.load();

            PersonagemFormController controller = loader.getController();
            controller.setPersonagem(obj);
            controller.setPersonagemService(new PersonagemService());
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
        TableColumnEDIT.setCellFactory(param -> new TableCell<Personagem, Personagem>() {
            private final Button button = new Button("Editar");

            @Override
            protected void updateItem(Personagem obj, boolean empty) {
                super.updateItem(obj, empty);

                if (obj == null) {
                    setGraphic(null);
                    return;
                }

                setGraphic(button);
                button.setOnAction(
                    event -> createDialogForm(obj, "/gui/PersonagemForm.fxml", Utils.currentStage(event)));
            }           
        });
    }

    private void initRemoveButtons() {
        TableColumnREMOVE.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
        TableColumnREMOVE.setCellFactory(param -> new TableCell<Personagem, Personagem>() {
            private final Button button = new Button("Remover");

            @Override
            protected void updateItem(Personagem obj, boolean empty) {
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

    private void removeEntity(Personagem obj) {
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

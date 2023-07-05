package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import gui.util.Alerts;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.VBox;

public class MainViewController implements Initializable{

    //TODOS OS DE PERSONAGEM
    @FXML
    private MenuItem menuItemInserirPersonagem;

    @FXML
    private MenuItem menuItemLerDadosPersonagem;

    @FXML
    private MenuItem menuItemDeletarPersonagem;

    //TODOS OS DE PIRATA
    @FXML
    private MenuItem menuItemInserirPirata;

    @FXML
    private MenuItem menuItemLerDadosPirata;

    @FXML
    private MenuItem menuItemRecompensaPirata;

    //TODOS DE MARINHA
    @FXML
    private MenuItem menuItemInserirMarinha;

    @FXML
    private MenuItem menuItemLerDadosMarinha;

    @FXML
    private MenuItem menuItemRecompensaMarinha;

    //TODOS DE ILHA
    @FXML
    private MenuItem menuItemInserirIlha;

    @FXML
    private MenuItem menuItemLerDadosIlha;

    //TODOS DE ARMA
    @FXML
    private MenuItem menuItemInserirArma;

    @FXML
    private MenuItem menuItemLerDadosArma;

    @FXML
    private MenuItem menuItemArco;

    @FXML
    private MenuItem menuItemAkumaNoMi;

    @FXML
    private MenuItem menuItemTripulacao;

    @FXML
    private MenuItem menuItemTipo;

    @FXML
    private MenuItem menuItemAlianca;

    @FXML
    private MenuItem menuItemAbout;


    //ACTIONS DE PERSONAGENS
    @FXML
    public void onMenuItemInserirPersonagemAction(){
        System.out.println("onMenuItemInserirPersonagemAction");
    }

    @FXML
    public void onMenuItemLerDadosPersonagemAction(){
        System.out.println("onMenuItemLerDadosPersonagemAction");
    }

    @FXML
    public void onMenuItemDeletarPersonagemAction(){
        System.out.println("onMenuItemDeletarPersonagemAction");
    }

    //ACTIONS DE PIRATAS
    @FXML
    public void onMenuItemInserirPirataAction(){
        loadView("/gui/PirataRegistration.fxml");;
    }

    @FXML
    public void onMenuItemLerDadosPirataAction(){
        System.out.println("onMenuItemLerDadosPirataAction");
    }

    @FXML
    public void onMenuItemRecompensaPirataAction(){
        System.out.println("onMenuItemDeletarPirataAction");
    }

    //ACTIONS DE MARINHA
    @FXML
    public void onMenuItemInserirMarinhaAction(){
        System.out.println("onMenuItemMarinhaAction");
    }

    @FXML
    public void onMenuItemLerDadosMarinhaAction(){
        System.out.println("onMenuItemLerDadosMarinhaAction");
    }

    @FXML
    public void onMenuItemRecompensaMarinhaAction(){
        System.out.println("onMenuItemRecompensaMarinhaAction");
    }

    //ACTIONS DE ILHA
    @FXML
    public void onMenuItemInserirIlhaAction(){
        System.out.println("onMenuItemInserirIlhaAction");
    }

    @FXML
    public void onMenuItemLerDadosIlhaAction(){
        System.out.println("onMenuItemLerDadosIlhaAction");
    }

    //ACTIONS DE ARMA
    @FXML
    public void onMenuItemInserirArmaAction(){
        System.out.println("onMenuItemInserirArmaAction");
    }
    @FXML
    public void onMenuItemLerDadosArmaAction(){
        System.out.println("onMenuItemLerDadosArmaAction");
    }

    //ACTIONS DE ARCO
    @FXML
    public void onMenuItemArcoAction(){
        System.out.println("onMenuItemArcoAction");
    }

    @FXML
    public void onMenuItemAkumaNoMiAction(){
        System.out.println("onMenuItemAkumaNoMiAction");
    }

    @FXML
    public void onMenuItemTripulacaoAction(){
        System.out.println("onMenuItemTripulacaoAction");
    }

    @FXML
    public void onMenuItemTipoAction(){
        System.out.println("onMenuItemTipoAction");
    }

    @FXML
    public void onMenuItemAliancaAction(){
        System.out.println("onMenuItemAliancaAction");
    }

    @FXML
    public void onMenuItemAboutAction() {
        loadView("/gui/Abouts.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {        
    }

    private synchronized void loadView(String absoluteName) {
        try{
        FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
        VBox newVBox = loader.load(); 

        Scene mainScene = Main.getMainScene();
        VBox mainVBox = (VBox) ((ScrollPane) mainScene.getRoot()).getContent(); //Pega o conteúdo da primeira linha do VBox

        Node mainMenu = mainVBox.getChildren().get(0);//Pega os 'filhos' do VBox e get o primeiro filho
        mainVBox.getChildren().clear(); //Limpa todos os filhos do mainVBox
        mainVBox.getChildren().add(mainMenu);
        mainVBox.getChildren().addAll(newVBox.getChildren());

        }catch (IOException e) {
            Alerts.showAlert("IO Exception", "Error loading view", e.getMessage(), AlertType.ERROR);
        }
    }
    
}

package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Consumer;

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
import model.services.PirataService;

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

    //TODOS DE ARMA DO PERSONAGEM
    @FXML MenuItem menuItemInserirArmaPersonagem;

    @FXML MenuItem menuItemLerDadosArmaPersonagem;

    //TODOS DE ARCO
    @FXML
    private MenuItem menuItemInserirArco;

    @FXML
    private MenuItem menuItemLerDadosArco;

    //TODOS DE AKUMA NO MI
    @FXML
    private MenuItem menuItemInserirAkumaNoMi;

    @FXML
    private MenuItem menuItemLerDadosAkumaNoMi;

    //Todos de Tripulação
    @FXML
    private MenuItem menuItemInserirTripulacao;

    @FXML
    private MenuItem menuItemLerDadosTripulacao;

    //Todos de Tipo
    @FXML
    private MenuItem menuItemInserirTipo;

    @FXML
    private MenuItem menuItemLerDadosTipo;

    //Todos de Aliança
    @FXML
    private MenuItem menuItemInserirAlianca;

    @FXML
    private MenuItem menuItemLerDadosAlianca;

    //Sobre o programa
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
        loadView("/gui/PirataRegistration.fxml", (PirataRegistrationController controller) -> {
            controller.setPirataService(new PirataService());
            controller.updateTableView();
        });
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

    //ACTIONS DE ARMA DO PERSONAGEM
    @FXML
    public void onMenuItemInserirArmaPersonagemAction(){
        System.out.println("onMenuItemInserirArmaPersonagemAction");
    }

    @FXML
    public void onMenuItemLerDadosArmaPersonagemAction(){
        System.out.println("onMenuItemLerDadosArmaPersonagemAction");
    }

    //ACTIONS DE ARCO
    @FXML
    public void onMenuItemInserirArcoAction(){
        System.out.println("onMenuItemInserirArcoAction");
    }

    @FXML
    public void onMenuItemLerDadosArcoAction(){
        System.out.println("onMenuItemLerDadosArcoAction");
    }

    //ACTIONS DE AKUMANOMI
    @FXML
    public void onMenuItemInserirAkumaNoMiAction(){
        System.out.println("onMenuItemInserirAkumaNoMiAction");
    }

    @FXML
    public void onMenuItemLerDadosAkumaNoMiAction(){
        System.out.println("onMenuItemLerDadosAkumaNoMiAction");
    }

    //ACTIONS DE TRIPULACAO
    @FXML
    public void onMenuItemInserirTripulacaoAction(){
        System.out.println("onMenuItemInserirTripulacaoAction");
    }

    @FXML
    public void onMenuItemLerDadosTripulacaoAction(){
        System.out.println("onMenuItemLerDadosTripulacaoAction");
    }
    
    //ACTIONS TIPO
    @FXML
    public void onMenuItemInserirTipoAction(){
        System.out.println("onMenuItemInserirTipoAction");
    }

    @FXML
    public void onMenuItemLerDadosTipoAction(){
        System.out.println("onMenuItemLerDadosTipoAction");
    }   

    //ACTIONS DE ALIANCA
    @FXML
    public void onMenuItemInserirAliancaAction(){
        System.out.println("onMenuItemInserirAliancaAction");
    }

    @FXML
    public void onMenuItemLerDadosAliancaAction(){
        System.out.println("onMenuItemLerDadosAliancaAction");
    }   

    //ACTIONS DO SOBRE
    @FXML
    public void onMenuItemAboutAction() {
        loadView("/gui/Abouts.fxml", x -> {});
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {        
    }

    private synchronized <T> void loadView(String absoluteName, Consumer<T> initializingAction) { //Foi necessário parametrizar para não criar várias funções que fazem a mesma coisa
        try{
        FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
        VBox newVBox = loader.load(); 

        Scene mainScene = Main.getMainScene();
        VBox mainVBox = (VBox) ((ScrollPane) mainScene.getRoot()).getContent(); //Pega o conteúdo da primeira linha do VBox

        Node mainMenu = mainVBox.getChildren().get(0);//Pega os 'filhos' do VBox e get o primeiro filho
        mainVBox.getChildren().clear(); //Limpa todos os filhos do mainVBox
        mainVBox.getChildren().add(mainMenu);
        mainVBox.getChildren().addAll(newVBox.getChildren());

        T controller = loader.getController(); //Um controller genérico T que funciona para qualquer View
        initializingAction.accept(controller); //Inicializando o controller

        }catch (IOException e) {
            Alerts.showAlert("IO Exception", "Error loading view", e.getMessage(), AlertType.ERROR);
        }
    }    
}

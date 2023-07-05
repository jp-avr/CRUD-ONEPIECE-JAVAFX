package application;
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;

public class Main extends Application{

    private static Scene mainScene;

    @Override
    public void start(Stage primaryStage){
        try{
           FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/MainView.fxml")); //Instancear o objeto loader do tipo FXMLLoader
           //Serve para manipular a tela antes de carregar
           ScrollPane sp = loader.load();//Chamar o load e carrega a view

           sp.setFitToHeight(true);
           sp.setFitToWidth(true);     


           mainScene = new Scene(sp);//Cena principal e passando como argumento o objeto principal da view
           primaryStage.setScene(mainScene);//Setar a cena como a cena principal
           primaryStage.setTitle("ONE PIECE");//Titulo
           primaryStage.show();//Mostrar o palco
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static Scene getMainScene() {
        return mainScene;
    }

    public static void main(String[] args){
        launch(args);
    }
}

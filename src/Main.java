import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application{

    @Override
    public void start(Stage primaryStage){
        try{
           FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/MainView.fxml")); //Instancear o objeto loader do tipo FXMLLoader
           //Serve para manipular a tela antes de carregar
           Parent parent = loader.load();//Chamar o load e carrega a view

           Scene mainScene = new Scene(parent);//Cena principal e passando como argumento o objeto principal da view
           primaryStage.setScene(mainScene);//Setar a cena como a cena principal
           primaryStage.setTitle("ONE PIECE");//Titulo
           primaryStage.show();//Mostrar o palco
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        launch(args);
    }
}

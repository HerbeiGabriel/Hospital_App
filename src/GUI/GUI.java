package GUI;

import Service.Service;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.FileReader;
import java.net.URL;
import java.util.Properties;

public class GUI extends Application{

    private static Scene scene;
    private static Scene scene2;
    private static Stage stage;
    private static Service service=new Service();
    private static Controller2<Object> controller2;
    private static Controller<Object> controller;

    @Override
    public void start(Stage primarystage) throws Exception{
        stage = primarystage;
        FXMLLoader loader1 = new FXMLLoader(GUI.class.getResource("Hospital.fxml"));
        FXMLLoader loader2 = new FXMLLoader(GUI.class.getResource("Hospital_newTab.fxml"));
        try{
            controller=new Controller<Object>(service);
            controller2=new Controller2<Object>(service);
            loader1.setController(controller);
            loader2.setController(controller2);
            scene=new Scene(loader1.load(), 630, 450);
            scene2=new Scene(loader2.load());
            scene.getStylesheets().add(GUI.class.getResource("Style.css").toExternalForm());
            scene2.getStylesheets().add(GUI.class.getResource("Style2.css").toExternalForm());
            URL url=GUI.class.getResource("/Resources/fonts/Gopesta.ttf");
            if (url != null) {
                Font.loadFont(url.toExternalForm(), 20);
            } else {
                System.out.println("Font file not found!");
            }
            stage.setScene(scene);
            stage.setResizable(false);
            stage.setTitle("Hospital");
            stage.sizeToScene();
            stage.show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void changeScene(Service service){
        stage.setScene(scene2);
        stage.show();
    }

    public void changeSceneOriginal(){
            controller.refresh();
            stage.setScene(scene);
            stage.show();
    }
}

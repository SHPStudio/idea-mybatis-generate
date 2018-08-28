package db;

import db.mysql.env.RuntimeEnv;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * db
 *
 * @author ASUS
 * @date 2017/10/21 10:15
 */
public class Main extends Application {
    public static void main(String[] args) throws IOException {
        RuntimeEnv.reader();
//        MainFrame mainFrame=new MainFrame();
        launch(args);
        RuntimeEnv.storage();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/generator.fxml"));
        primaryStage.setTitle("Mybatis Generator");
        primaryStage.setScene(new Scene(root, 500, 400));
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/images/bird.png")));
        primaryStage.show();

    }
}


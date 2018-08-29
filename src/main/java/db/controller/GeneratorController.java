package db.controller;

import db.model.SelectModel;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.EventObject;

/**
 * Created with IntelliJ IDEA.
 * User: jiluohao@yixin.im
 * Date: 2018-08-27 14:07
 * Description:
 */
public class GeneratorController {
    @FXML
    public ChoiceBox<String> jdbcSelector;
    @FXML
    public ChoiceBox<SelectModel<Integer,String>> jdbcJarSelector;

    @FXML
    public void initialize() {
        // jdbc 类型选择初始化
        jdbcSelector.setItems(FXCollections.observableArrayList("Mysql","Postgresql"));
        jdbcSelector.setValue("Mysql");
        // jdbcJar 选择初始化
        SelectModel<Integer, String> selectModel = new SelectModel<>(1, "插件自带");
        jdbcJarSelector.setItems(FXCollections.observableArrayList(selectModel, new SelectModel<>(2, "自己选择")));
        jdbcJarSelector.setValue(selectModel);
    }


    public void jdbcJarHiding(Event event) {
        if (jdbcJarSelector.getValue().getKey().equals(2)){
            Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
            FileChooser fileChooser = new FileChooser();
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
            fileChooser.getExtensionFilters().add(extFilter);
            File file = fileChooser.showOpenDialog(stage);
            System.out.println(file.getAbsolutePath());
            jdbcJarSelector.getValue().setInfo(file.getAbsolutePath());
            jdbcJarSelector.show();

        }
    }
}

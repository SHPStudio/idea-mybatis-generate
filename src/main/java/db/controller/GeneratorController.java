package db.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;

/**
 * Created with IntelliJ IDEA.
 * User: jiluohao@yixin.im
 * Date: 2018-08-27 14:07
 * Description:
 */
public class GeneratorController {
    public Label hellContent;

    public void sayHello(ActionEvent actionEvent) {
        hellContent.setText("hello world");
    }
}

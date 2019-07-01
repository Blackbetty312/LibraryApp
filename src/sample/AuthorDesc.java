package sample;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

public class AuthorDesc {
    private DatabaseConnect dbconnect = Connect.dbconnect;
    private int id;
    @FXML
    private TextArea descArea;

    @FXML
    public void initialize() {
    }

    public void initData(String desc) {
        descArea.setText(desc);
    }
}

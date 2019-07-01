package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Collection;


public class Login {


    private DatabaseConnect dbconnect = Connect.dbconnect;

    @FXML
    private BorderPane loginScene;
    @FXML
    private Button openRegisterBtn;
    @FXML
    private TextField emailBtn;
    @FXML
    private PasswordField passBtn;
    @FXML
    private Label warning;


    @FXML
    public void initialize() {

    }

    @FXML
    private void openRegister() throws IOException {
        BorderPane borderPane = FXMLLoader.load(getClass().getResource("register.fxml"));
        loginScene.getChildren().setAll(borderPane);
    }

    @FXML
    private void login() throws IOException {
        Queries queries = new Queries(dbconnect);
        Users user = new Users();
        user.setPassword(GFG.getSHA256(passBtn.getText()));
        user.setEmail(emailBtn.getText());
        int n = queries.checkLogin(user);
        if(n == 2) {
            warning.setText("Niepoprawne dane logowania(Niepoprawny login lub hasło)");
        } else if(n == 1) {
            warning.setText("Witamy pracownika");
        } else {
            warning.setText("Witamy czytelnika");
        }
        BorderPane borderPane = FXMLLoader.load(getClass().getResource("readerView.fxml"));
        loginScene.getChildren().setAll(borderPane);

        dbconnect.closeSession();
    }


}

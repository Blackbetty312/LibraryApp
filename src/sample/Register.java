package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import org.hibernate.query.Query;

import java.io.IOException;
import java.sql.Date;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

public class Register {

    private DatabaseConnect dbconnect = Connect.dbconnect;

    @FXML
    private BorderPane registerScene;
    @FXML
    private Button registerBtn;
    @FXML
    private TextField email;
    @FXML
    private PasswordField pass;
    @FXML
    private TextField firstname;
    @FXML
    private TextField lastname;
    @FXML
    private TextField phone;
    @FXML
    private TextField personalId;
    @FXML
    private Label warning;


    @FXML
    private void addInfo() throws IOException {

        warning.setWrapText(true);
        Users user = new Users();
        Reader reader = new Reader();
        user.setEmail(email.getText());
        user.setPassword(GFG.getSHA256(pass.getText()));
        reader.setName(firstname.getText());
        reader.setLastName(lastname.getText());
        reader.setTelephone(phone.getText());
        reader.setPersonalId(personalId.getText());
        reader.setUsersByUsersIdUser(user);


        Queries queries = new Queries(dbconnect);
        if(email.getText().isEmpty() || personalId.getText().isEmpty() || pass.getText().isEmpty() || firstname.getText().isEmpty() || lastname.getText().isEmpty() || phone.getText().isEmpty()) {
            warning.setText("Proszę wypełnić wszystkie pola w formularzu");
        } else if (pass.getText().length() < 8){
            warning.setText("Podane hasło jest za krótkie (Min. 8 znaków)");
        } else if (personalId.getText().length() != 11) {
            warning.setText("Niepoprawny numer pesel");
        } else {
            if (!queries.existUser(user)) {
                queries.createReader(reader);
                warning.setText("Utworzono pomyślnie konto");
            } else {
                warning.setText("Użytkownik o podanym emailu już znajduje się w bazie danych");
            }
        dbconnect.closeSession();
        }

    }

    @FXML
    private void leave() throws IOException {
        BorderPane borderPane = FXMLLoader.load(getClass().getResource("login.fxml"));
        registerScene.getChildren().setAll(borderPane);
    }

}

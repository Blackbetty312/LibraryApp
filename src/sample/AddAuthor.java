package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.sql.Date;


public class AddAuthor {

    @FXML
    private TextField name;
    @FXML
    private TextField lastname;
    @FXML
    private TextArea desc;
    @FXML
    private DatePicker datePicker;
    @FXML
    private BorderPane addAuthorView;

    @FXML
    public void addAuthor() {
        Alert alertInf = new Alert(Alert.AlertType.INFORMATION);
        Author a = new Author();
        a.setFirstName(name.getText());
        a.setLastName(lastname.getText());
        a.setBiography(desc.getText());
        a.setBirthDate(new Date(datePicker.getValue().getYear() - 1900, datePicker.getValue().getMonthValue() - 1, datePicker.getValue().getDayOfMonth()));
        System.out.println(a);
        Queries queries = new Queries(Connect.dbconnect);
        queries.createAuthor(a);
        alertInf.setTitle("Udano");
        alertInf.setHeaderText(null);
        alertInf.setContentText("Pomyślnie dodano");
        alertInf.showAndWait();
    }
    @FXML
    public void leave() throws IOException {
        BorderPane borderPane = FXMLLoader.load(getClass().getResource("readerView.fxml"));
        addAuthorView.getChildren().setAll(borderPane);
    }
}

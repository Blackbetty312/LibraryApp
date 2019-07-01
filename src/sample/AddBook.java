package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class AddBook {
    private DatabaseConnect dbconnect = Connect.dbconnect;

    @FXML
    private TextField isbn;
    @FXML
    private TextField title;
    @FXML
    private TextArea desc;
    @FXML
    private TextField release;
    @FXML
    private ChoiceBox publishing;
    @FXML
    private BorderPane addBookView;
    @FXML
    private Label warning;

    @FXML
    public void addBook() {
        Queries queries = new Queries(dbconnect);
        Book b = new Book();
        if(isbn.getText().isEmpty() || title.getText().isEmpty() || release.getText().isEmpty()) {
            warning.setText("Musisz uzupełnić niezbędne pola(opis niewymagany)");
        } else {
            b.setIsbn(isbn.getText());
            b.setTitle(title.getText());
            b.setDescription(desc.getText());
            b.setReleaseYear(Integer.valueOf(release.getText()));
            Alert alertInf = new Alert(Alert.AlertType.INFORMATION);
            if (queries.createBook(b, queries.getPublishingFromName(publishing.getSelectionModel().getSelectedItem().toString())) == 1) {
                warning.setText("");
                alertInf.setTitle("Udano");
                alertInf.setHeaderText(null);
                alertInf.setContentText("Pomyślnie dodano książkę");
            } else {
                alertInf.setTitle("Nie udano");
                alertInf.setHeaderText(null);
                alertInf.setContentText("Nie udało się dodać książki");
            }
            alertInf.showAndWait();
        }
        dbconnect.closeSession();
    }
    @FXML
    public void leave() throws IOException {
        BorderPane borderPane = FXMLLoader.load(getClass().getResource("readerView.fxml"));
        addBookView.getChildren().setAll(borderPane);
    }
    @FXML
    public void initialize() {
        Queries queries = new Queries(dbconnect);
        publishing.getItems().setAll(queries.getAllPublishing());
        publishing.getSelectionModel().selectFirst();
        dbconnect.closeSession();
    }
}

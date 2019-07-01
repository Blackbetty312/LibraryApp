package sample;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

import java.util.HashMap;
import java.util.Map;

public class Controller {

    @FXML
    private VBox box;
    @FXML
    private ListView<Author> lista = new ListView();
    @FXML
    private TextArea authorDesc;
    @FXML
    private Label birth_date;

    @FXML
    public void initialize() {

//        DatabaseConnect dbconnect = new DatabaseConnect();
//        Queries queries = new Queries(dbconnect);
//        dbconnect.startSession();
//        for(Object o : queries.getAllAuthors()) {
//            Author a = (Author) o;
//            lista.getItems().add(a);
//        }
//        authorDesc.setText(lista.getItems().get(0).getBiography());
//        birth_date.setText("Rok urodzenia: " + lista.getItems().get(0).getBirthDateString());
//        lista.setOnMouseClicked(new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent event) {
//                System.out.println(lista.getSelectionModel().getSelectedItem().getBiography());
//                authorDesc.setText(lista.getSelectionModel().getSelectedItem().getBiography());
//                birth_date.setText("Rok urodzenia: " + lista.getSelectionModel().getSelectedItem().getBirthDateString());
//            }
//        });
    }
}

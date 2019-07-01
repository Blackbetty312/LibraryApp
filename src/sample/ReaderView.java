package sample;

import javafx.beans.property.*;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Callback;

import javax.transaction.Transactional;
import java.io.IOException;
import java.sql.Date;
import java.util.Arrays;
import java.util.Optional;

public class ReaderView {

    private DatabaseConnect dbconnect = Connect.dbconnect;

    @FXML
    private TableView<Author> authorsTable;
    @FXML
    private TableView<Book> bookTable;
    @FXML
    private TableColumn<Author, String> nameColumn;
    @FXML
    private TableColumn<Author, String> lastNameColumn;
    @FXML
    private TableColumn<Author, Date> birthdayColumn;
    @FXML
    private TableColumn<Book, String> isbnCol;
    @FXML
    private TableColumn<Book, String> titleCol;
    @FXML
    private TableColumn<Book, Integer> releaseCol;
    @FXML
    private TableColumn<Book, String> publisherCol;
    @FXML
    private ContextMenu contextMenuAuthor;
    @FXML
    private ContextMenu contextMenuBook;
    @FXML
    private BorderPane readerViewScene;

    @FXML
    private TextField filterField;
    @FXML
    private MenuItem d;

    private int flag = 2;


    @FXML
    public void deleteBook() {
        Book b = bookTable.getSelectionModel().getSelectedItem();
        System.out.println(b.getIsbn());
    }

    @FXML
    public void addBook() throws IOException {
        Book b = bookTable.getSelectionModel().getSelectedItem();
        System.out.println(b.getIdBook());
        BorderPane borderPane = FXMLLoader.load(getClass().getResource("addBook.fxml"));
        readerViewScene.getChildren().setAll(borderPane);
    }

    @FXML
    public void deleteAuthor() {
        Author a = authorsTable.getSelectionModel().getSelectedItem();
        Alert alertConf = new Alert(Alert.AlertType.CONFIRMATION);
        Alert alertInf = new Alert(Alert.AlertType.INFORMATION);
        alertConf.setTitle("Usuwanie");
        alertConf.setHeaderText("Czy napewno chcesz usunąć?");
        alertConf.setContentText(a.getFirstName() + " " + a.getLastName());
        Optional<ButtonType> result = alertConf.showAndWait();
        if (result.get() == ButtonType.OK){
            Queries queries = new Queries(dbconnect);
            if(queries.deleteAuthor(a.getIdAuthor()) != 0) {
                alertInf.setTitle("Udano");
                alertInf.setHeaderText(null);
                alertInf.setContentText("Pomyślnie usunięto");
            } else {
                alertInf.setTitle("Nie udano");
                alertInf.setHeaderText(null);
                alertInf.setContentText("Nie udało się usunąć");
            }
            alertInf.showAndWait();
            System.out.println(a.getIdAuthor());
            dbconnect.closeSession();
            initialize();
        }
    }
    @FXML
    public void showDescAuthor() throws IOException {
        Author a = authorsTable.getSelectionModel().getSelectedItem();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("authorDesc.fxml"));
        Stage stage = new Stage();
        stage.setScene(
                new Scene((BorderPane) loader.load())
        );
        stage.setTitle("Opis");

        AuthorDesc controller = loader.<AuthorDesc>getController();
        Queries queries = new Queries(dbconnect);
        Author aa = (Author) queries.getAuthorFromId(a.getIdAuthor()).get(0);

        controller.initData(aa.getBiography());
        dbconnect.closeSession();
        stage.show();

    }

    @FXML
    public void addAuthor() throws IOException {
        BorderPane borderPane = FXMLLoader.load(getClass().getResource("addAuthor.fxml"));
        readerViewScene.getChildren().setAll(borderPane);

    }
    public void initialize() {
        Queries queries = new Queries(dbconnect);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        birthdayColumn.setCellValueFactory(new PropertyValueFactory<>("birthDate"));

        System.out.println(Arrays.toString(queries.getAllBooks().toArray()));

        isbnCol.setCellValueFactory(new PropertyValueFactory<>("isbn"));
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        releaseCol.setCellValueFactory(new PropertyValueFactory<>("releaseYear"));
        publisherCol.setCellValueFactory(new PropertyValueFactory<>("PublisherName"));

//        Book b = (Book) queries.getAllBooks().get(0);
//        System.out.println(b.getPublishingByPublishingIdPublishing().getName());
        ObservableList<Book> listBooks = FXCollections.observableArrayList(queries.getAllBooks());
        ObservableList<Author> listAuthors = FXCollections.observableArrayList(queries.getAllAuthors());

        FilteredList<Book> filteredData = new FilteredList<>(listBooks, p -> true);
        filterField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(person -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                if (person.getTitle().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (person.getIsbn().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (person.getPublishingByPublishingIdPublishing().getName().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (person.getReleaseYear().toString().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }
                return false;
            });
        });
        SortedList<Book> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(bookTable.comparatorProperty());

        bookTable.setItems(sortedData);
//        bookTable.setItems(listBooks);
        authorsTable.setItems(listAuthors);


        authorsTable.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(event.getButton() == MouseButton.SECONDARY) {
                    contextMenuAuthor.show(authorsTable, event.getScreenX(), event.getScreenY());
                }
            }
        });
        bookTable.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(event.getButton() == MouseButton.SECONDARY) {
                    contextMenuBook.show(bookTable, event.getScreenX(), event.getScreenY());
                }
            }
        });
        dbconnect.closeSession();
    }

    public void initData(int flag) {
        this.flag = flag;
    }

}

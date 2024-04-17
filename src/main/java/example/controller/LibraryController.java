package example.controller;

import example.mybatis.MyBatisConnectionFactory;
import example.service.BookService;
import example.vo.BookVO;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class LibraryController implements Initializable {
    @FXML
    private TextField searchKeyword;
    @FXML
    private Button searchBtn;
    @FXML
    private Button deleteBtn;
    @FXML
    private TableView<BookVO> tableView;
    @FXML
    private TableColumn<BookVO, String> isbn;
    @FXML
    private TableColumn<BookVO, String> title;
    @FXML
    private TableColumn<BookVO, Integer> price;
    @FXML
    private TableColumn<BookVO, String> author;
    @FXML
    private TextField updateTitle;
    @FXML
    private TextField updatePrice;
    @FXML
    private TextField updateAuthor;
    @FXML
    private Button updateBtn;

    private final BookService service;
    private String deleteIsbn;

    public LibraryController() {
        this.service = new BookService(MyBatisConnectionFactory.getSqlSessionFactory());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        isbn.setCellValueFactory(new PropertyValueFactory<>("bisbn"));
        title.setCellValueFactory(new PropertyValueFactory<>("btitle"));
        price.setCellValueFactory(new PropertyValueFactory<>("bprice"));
        author.setCellValueFactory(new PropertyValueFactory<>("bauthor"));

        tableView.setRowFactory(e -> {
            TableRow<BookVO> row = new TableRow<>();
            row.setOnMouseClicked(mouseEvent -> {
                if (mouseEvent.getClickCount() == 1 && !row.isEmpty()) {
                    BookVO bookVO = row.getItem();
                    deleteIsbn = bookVO.getBisbn();
                }
            });
            return row;
        });

        searchBtn.setOnAction(e -> {
            ObservableList<BookVO> data = service.searchBooksByKeyword(searchKeyword.getText());
            tableView.setItems(data);
        });

        deleteBtn.setOnAction(e -> {
            BookVO selectedItem = tableView.getSelectionModel().getSelectedItem();
            if (selectedItem == null) {
                System.out.println("아이템이 선택되지 않았습니다!");
                return;
            }
            service.deleteBookByISBN(selectedItem.getBisbn());
            tableView.setItems(service.searchBooksByKeyword(searchKeyword.getText()));
        });

        updateBtn.setOnAction(e -> {
            BookVO selectedItem = tableView.getSelectionModel().getSelectedItem();
            if (selectedItem == null) {
                System.out.println("아이템이 선택되지 않았습니다!");
                return;
            }
            if (!updateTitle.getText().isBlank() && updatePrice.getText().isBlank() && updateAuthor.getText().isBlank()) {
                service.updateBookTitle(selectedItem.getBisbn(),  updateTitle.getText());
            } else if (updateTitle.getText().isBlank() && !updatePrice.getText().isBlank() && updateAuthor.getText().isBlank()) {
                // update price
                service.updateBookPrice(selectedItem.getBisbn(), Integer.valueOf(updatePrice.getText()));
            } else if (updateTitle.getText().isBlank() && updatePrice.getText().isBlank() && !updateAuthor.getText().isBlank()) {
                // update author
                service.updateBookAuthor(selectedItem.getBisbn(), updateAuthor.getText());
            } else if (!updateTitle.getText().isBlank() && !updatePrice.getText().isBlank() && updateAuthor.getText().isBlank()) {
                // update title and price
                service.updateBookTitleAndPrice(selectedItem.getBisbn(), updateTitle.getText(), Integer.valueOf(updatePrice.getText()));
            } else if (!updateTitle.getText().isBlank() && updatePrice.getText().isBlank() && !updateAuthor.getText().isBlank()) {
                // update title and author
                service.updateBookTitleAndAuthor(selectedItem.getBisbn(), updateTitle.getText(), updateAuthor.getText());
            } else if (updateTitle.getText().isBlank() && !updatePrice.getText().isBlank() && !updateAuthor.getText().isBlank()) {
                // update price and author
                service.updateBookPriceAndAuthor(selectedItem.getBisbn(), Integer.valueOf(updatePrice.getText()), updateAuthor.getText());
            } else {
                // update all
                service.updateAll(selectedItem.getBisbn(), updateTitle.getText(), Integer.valueOf(updatePrice.getText()), updateAuthor.getText());
            }
            tableView.setItems(service.searchBookByISBN(selectedItem.getBisbn()));
        });
    }
}

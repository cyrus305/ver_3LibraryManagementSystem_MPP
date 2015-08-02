package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import dao.Impl.DataAccessFacade;
import dao.service.BookService;
import dao.service.DataAccess;
import helper.LibraryException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import jfx.messagebox.MessageBox;
import model.Author;
import model.Book;

public class BookController {
	@FXML
	private TextField txtISBN;
	@FXML
	private TextField txtTitle;
	@FXML
	private ComboBox<String> txtMaxCheckoutLength;
	@FXML
	private Label lblID;
	@FXML
	private Label lblISBN;
	@FXML
	private Button btnAddAuthor;
	BookService bs;

	static private List<Author> authors = new ArrayList<Author>();

	public BookController() {
		bs = new BookService();
	}
	
	public void addBooks()throws Exception{
		addNewBook(txtISBN.getText(), txtTitle.getText(),Integer.parseInt(txtMaxCheckoutLength.getValue()) ,authors);
	}
	
	public void addNewBook(String isbn,String title, int combo, List<Author> author) throws LibraryException{
		DataAccess dataAccess = new DataAccessFacade();
		HashMap<String, Book> bookMap = dataAccess.readBooksMap();
		if (!bookMap.containsKey(txtISBN)) {
			bs.saveNewBook(new Book(isbn, title, combo, author));
			
		} else {
			MessageBox.show(null,
	    		    "Book cannot be added",
	    		    "This book already exitsts in database!", 
	    		    MessageBox.ICON_INFORMATION | MessageBox.OK);
		}
	}
}
package dao.service;

import java.util.HashMap;

import dao.Impl.DataAccessFacade;
import helper.EnumCollection;
import helper.LibraryException;
import model.Book;

public class BookService {
	DataAccessFacade daf;

	public BookService() {
		daf = new DataAccessFacade();
	}

	// save new lendable item
	public void saveNewBook(Book book) throws LibraryException {
		HashMap<String, Book> bookMap = daf.readBooksMap();
		String isbn = book.getIsbn();
		bookMap.put(isbn, book);
		try {
			DataAccessFacade.saveToStorage(EnumCollection.StorageType.BOOKS, bookMap);
		} catch (LibraryException ex) {
			throw new LibraryException("Error on adding book.");
		}
	}

	public Book searchBook(String isbn) throws LibraryException {
		HashMap<String, Book> booksMap = daf.readBooksMap();
		Book b = booksMap.get(isbn);
		if (b == null)
			throw new LibraryException("Book doesnot exits");

		return b;
	}
}

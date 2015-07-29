package dao.service;

import java.util.HashMap;
import model.Book;
import model.LibraryMember;
import model.User;

public interface DataAccess {
	////// read methods
	public HashMap<String, Book> readBooksMap();

	public HashMap<String, User> readUserMap();

	public HashMap<String, LibraryMember> readMemberMap();
}

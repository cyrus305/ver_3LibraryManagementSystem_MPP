package dao.Impl;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;

import dao.service.DataAccess;
import helper.EnumCollection;
import helper.LibraryException;
import model.Auth;
import model.Book;
import model.LibraryMember;
import model.User;

public class DataAccessFacade implements DataAccess {

	public static final String OUTPUT_DIR = System.getProperty("user.dir") + "\\src\\dataaccess\\storage";
	public static final String DATE_PATTERN = "MM/dd/yyyy";

	//// specialized lookup methods
	// public LibraryMember searchMember(String memberId) {
	// implement
	// }

	public Auth login(String id, String pwd) {
		HashMap<String, User> userMap = readUserMap();
		if (!userMap.containsKey(id))
			return null;
		User user = userMap.get(id);
		if (!pwd.equals(user.getPassword())) {
			return null;
		}
		return user.getAuthorization();
	}

	/////// save methods
	// saveNewMember
	// public void saveNewMember(LibraryMember member)

	// public void updateMember(LibraryMember member)
	// save new lendable item
	////// read methods that return full maps
	@SuppressWarnings("unchecked")
	public HashMap<String, Book> readBooksMap() {
		return (HashMap<String, Book>) readFromStorage(EnumCollection.StorageType.BOOKS);
	}

	@SuppressWarnings("unchecked")
	public HashMap<String, LibraryMember> readMemberMap() {
		return (HashMap<String, LibraryMember>) readFromStorage(EnumCollection.StorageType.MEMBERS);
	}

	@SuppressWarnings("unchecked")
	public HashMap<String, User> readUserMap() {
		return (HashMap<String, User>) readFromStorage(EnumCollection.StorageType.USERS);
	}

	///// load methods - these place test data into the storage area
	///// - used just once at startup
	static void loadMemberMap(List<LibraryMember> memberList) throws LibraryException {
		HashMap<String, LibraryMember> members = new HashMap<>();
		memberList.forEach(member -> members.put(member.getMemberId(), member));
		saveToStorage(EnumCollection.StorageType.MEMBERS, members);

	}

	static void loadBookMap(List<Book> bookList) throws LibraryException {
		HashMap<String, Book> books = new HashMap<String, Book>();
		bookList.forEach(book -> books.put(book.getIsbn(), book));
		saveToStorage(EnumCollection.StorageType.BOOKS, books);
	}

	static void loadUserMap(List<User> userList) throws LibraryException {
		HashMap<String, User> users = new HashMap<String, User>();
		userList.forEach(user -> users.put(user.getName(), user));
		saveToStorage(EnumCollection.StorageType.USERS, users);
	}

	public static void saveToStorage(EnumCollection.StorageType type, Object ob) throws LibraryException {
		ObjectOutputStream out = null;
		try {
			Path path = FileSystems.getDefault().getPath(OUTPUT_DIR, type.toString());
			out = new ObjectOutputStream(Files.newOutputStream(path));
			out.writeObject(ob);
		} catch (IOException e) {
			throw new LibraryException();
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (Exception e) {
				}
			}
		}
	}

	public static Object readFromStorage(EnumCollection.StorageType type) {
		ObjectInputStream in = null;
		Object retVal = null;
		try {
			Path path = FileSystems.getDefault().getPath(OUTPUT_DIR, type.toString());
			in = new ObjectInputStream(Files.newInputStream(path));
			retVal = in.readObject();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (Exception e) {
				}
			}
		}
		return retVal;
	}

	final static class Pair<S, T> implements Serializable {

		S first;
		T second;

		Pair(S s, T t) {
			first = s;
			second = t;
		}

		@Override
		public boolean equals(Object ob) {
			if (ob == null)
				return false;
			if (this == ob)
				return true;
			if (ob.getClass() != getClass())
				return false;
			@SuppressWarnings("unchecked")
			Pair<S, T> p = (Pair<S, T>) ob;
			return p.first.equals(first) && p.second.equals(second);
		}

		@Override
		public int hashCode() {
			return first.hashCode() + 5 * second.hashCode();
		}

		@Override
		public String toString() {
			return "(" + first.toString() + ", " + second.toString() + ")";
		}

		private static final long serialVersionUID = 5399827794066637059L;
	}


}

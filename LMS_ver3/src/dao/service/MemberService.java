package dao.service;

import helper.LibraryException;
import model.LibraryMember;

public interface MemberService {
	public void saveMember(LibraryMember member) throws Exception;
	public LibraryMember searchMember(String id) throws LibraryException ;
	public void editMember(LibraryMember member) throws Exception;
} 
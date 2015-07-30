package dao.service;

import java.util.HashMap;

import dao.Impl.DataAccessFacade;
import helper.EnumCollection;
import helper.LibraryException;
import model.LibraryMember;

public class MemberServiceImpl implements MemberService {

	DataAccess dataAccess = new DataAccessFacade();

	@Override
	public void saveMember(LibraryMember member) throws Exception {

		HashMap<String, LibraryMember> memberMap = dataAccess.readMemberMap();
		String memberId = member.getMemberId();
		memberMap.put(memberId, member);
		DataAccessFacade.saveToStorage(EnumCollection.StorageType.MEMBERS, memberMap);
	}

	@Override
	public LibraryMember searchMember(String id) throws LibraryException {
		HashMap<String, LibraryMember> libMemberMap = dataAccess.readMemberMap();
		LibraryMember libMember = libMemberMap.get(id);
		if (libMember == null)
			throw new LibraryException("Library member not found.");
		return libMember;
	}

	@Override
	public void editMember(LibraryMember member) throws Exception {
		HashMap<String, LibraryMember> memberMap = dataAccess.readMemberMap();
		String memberId = member.getMemberId();
		memberMap.remove(memberId);
		memberMap.put(memberId, member);
		DataAccessFacade.saveToStorage(EnumCollection.StorageType.MEMBERS, memberMap);
		System.out.println(dataAccess.readMemberMap());

	}

}

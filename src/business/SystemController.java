package business;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import dataaccess.Auth;
import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;
import dataaccess.User;

public class SystemController implements ControllerInterface {
	public static Auth currentAuth = null;
	
	public void login(String id, String password) throws LoginException {
		DataAccess da = new DataAccessFacade();
		HashMap<String, User> map = da.readUserMap();
		if(!map.containsKey(id)) {
			throw new LoginException("ID " + id + " not found");
		}
		String passwordFound = map.get(id).getPassword();
		if(!passwordFound.equals(password)) {
			throw new LoginException("Password incorrect");
		}
		currentAuth = map.get(id).getAuthorization();
		
	}
	@Override
	public List<String> allMemberIds() {
		DataAccess da = new DataAccessFacade();
		List<String> retval = new ArrayList<>();
		retval.addAll(da.readMemberMap().keySet());
		return retval;
	}
	
	@Override
	public List<String> allBookIds() {
		DataAccess da = new DataAccessFacade();
		List<String> retval = new ArrayList<>();
		retval.addAll(da.readBooksMap().keySet());
		return retval;
	}

	@Override
	public void checkoutBook(String memberID, String isbn) throws LibrarySystemException {
		DataAccess accessFacade = new DataAccessFacade();
		HashMap<String, Book> books = accessFacade.readBooksMap();
		Book b = books.get(isbn);
		HashMap<String, LibraryMember> members = accessFacade.readMemberMap();
		LibraryMember m = members.get(memberID);
		if(b == null || m == null)
			throw new LibrarySystemException("Error Member ID or ISBN!"+isbn);

		DataAccessFacade.checkOutBook(b, m);
		accessFacade.saveMemberMap(members);
		accessFacade.saveBookMap(books);
	}

	@Override
	public List<String[]> getMemberCheckoutItems(String memberID) throws LibrarySystemException {
		DataAccess da = new DataAccessFacade();
		LibraryMember m = da.readMemberMap().get(memberID);
		if(m == null){
			throw new LibrarySystemException("No such User!");
		}

		if(m.getMemberCheckoutHistory() == null){
			m.setMemberCheckoutHistory(new MemberCheckoutHistory(m));
			m.getMemberCheckoutHistory().setMemberCheckoutHistoryItems(new ArrayList<>());
		}
		MemberCheckoutHistory memberCheckoutHistory = m.getMemberCheckoutHistory();
		List<MemberCheckoutHistoryItem> memberCheckoutHistoryItems = memberCheckoutHistory.getMemberCheckoutHistoryItems();
		List<String[]> columns = new ArrayList<>();
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		for (MemberCheckoutHistoryItem his: memberCheckoutHistoryItems){
			String[] row = new String[]{
					memberID,
					m.getFirstName() + " " + m.getLastName(),
					his.getBookCopy().getBook().getIsbn(),
					his.getBookCopy().getBook().getTitle(),
					his.getBookCopy().getCopyNum()+"",
					his.getCheckoutDate().toString(),
					his.getDueDate().toString()
			};
			columns.add(row);
		}
		return columns;
	}

	@Override
	public void addBook(String isbn, String title, int maxCheckoutLength, List<Author> authors) throws LibrarySystemException {
		DataAccess da = new DataAccessFacade();
		HashMap<String, Book> books = da.readBooksMap();
		Book b = books.get(isbn);
		if(b != null){
			throw new LibrarySystemException("Already a Book with same ISBN!");
		}
		b = new Book(isbn, title, maxCheckoutLength, authors);
		da.saveNewBook(b);
	}

	@Override
	public void addBookCopy(String isbn) throws LibrarySystemException {
		DataAccess da = new DataAccessFacade();
		HashMap<String, Book> books = da.readBooksMap();
		Book b = books.get(isbn);
		if(b == null){
			throw new LibrarySystemException("There is no such book with the ISBN!");
		}
		b.addCopy();
		da.saveBookMap(books);
	}

	@Override
	public void addNewMember(String memberID, String firstName, String lastName, String cell, String street, String city, String state, String zip) throws LibrarySystemException {
		DataAccess da = new DataAccessFacade();
		LibraryMember member = da.readMemberMap().get(memberID);
		if(member != null){
			throw new LibrarySystemException("There already is a member with the same ID!");
		}
		member = new LibraryMember(memberID, firstName, lastName, cell, new Address(street, city, state, zip));
		da.saveNewMember(member);
	}
}
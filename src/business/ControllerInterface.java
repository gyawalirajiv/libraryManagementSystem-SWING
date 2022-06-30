package business;

import java.util.List;

public interface ControllerInterface {
	public void login(String id, String password) throws LoginException;
	public List<String> allMemberIds();
	public List<String> allBookIds();
	public void checkoutBook(String memID, String isbn) throws LibrarySystemException;
	public List<String[]> getMemberCheckoutItems(String memberID) throws LibrarySystemException;
	public void addBook(String isbn, String title, int parseInt, List<Author> authors) throws LibrarySystemException;
	public void addBookCopy(String bookID) throws LibrarySystemException;
	public void addNewMember(String id, String firstName, String lastName, String cell, String street, String city, String state, String zip) throws LibrarySystemException;
}

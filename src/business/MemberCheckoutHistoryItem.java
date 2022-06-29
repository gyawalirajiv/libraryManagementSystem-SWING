package business;

import java.io.Serializable;
import java.time.LocalDate;

public class MemberCheckoutHistoryItem implements Serializable {

    private static final long serialVersionUID = -1234155185193491230L;

    private BookCopy bookCopy;
    private LocalDate checkoutDate;
    private LocalDate dueDate;


    public MemberCheckoutHistoryItem(BookCopy book, LocalDate checkoutDate, LocalDate dueDate) {
        this.bookCopy = book;
        this.checkoutDate = checkoutDate;
        this.dueDate = dueDate;
    }

    public LocalDate getCheckoutDate() {
        return checkoutDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public BookCopy getBookCopy() {
        return bookCopy;
    }

    public boolean equals(Object ob) {
        if(ob == null) return false;
        if(!(ob instanceof MemberCheckoutHistoryItem)) return false;
        MemberCheckoutHistoryItem copy = (MemberCheckoutHistoryItem) ob;
        return copy.checkoutDate.equals(checkoutDate)
                && copy.dueDate.equals(dueDate);
    }

}

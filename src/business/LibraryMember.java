package business;

import java.io.Serializable;
import java.time.LocalDate;


import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;

final public class LibraryMember extends Person implements Serializable {
	private String memberId;
	private MemberCheckoutHistory memberCheckoutHistory;
	
	public LibraryMember(String memberId, String fname, String lname, String tel,Address add) {
		super(fname,lname, tel, add);
		this.memberId = memberId;
		this.memberCheckoutHistory = new MemberCheckoutHistory(this);
	}


	public String getMemberId() {
		return memberId;
	}

	public MemberCheckoutHistory getMemberCheckoutHistory() {
		return memberCheckoutHistory;
	}

	public void setMemberCheckoutHistory(MemberCheckoutHistory memberCheckoutHistory) {
		this.memberCheckoutHistory = memberCheckoutHistory;
	}

	@Override
	public String toString() {
		return "Member Info: " + "ID: " + memberId + ", name: " + getFirstName() + " " + getLastName() + 
				", " + getTelephone() + " " + getAddress();
	}

	private static final long serialVersionUID = -2226197306790714013L;
}

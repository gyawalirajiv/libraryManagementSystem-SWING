package business;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MemberCheckoutHistory implements Serializable {

    private static final long serialVersionUID = -3459882501123050153L;

    private List<MemberCheckoutHistoryItem> memberCheckoutHistoryItems;
    private LibraryMember member;

    public List<MemberCheckoutHistoryItem> getMemberCheckoutHistoryItems() {
        return memberCheckoutHistoryItems;
    }

    public void setMemberCheckoutHistoryItems(List<MemberCheckoutHistoryItem> memberCheckoutHistoryItems) {
        this.memberCheckoutHistoryItems = memberCheckoutHistoryItems;
    }

    public MemberCheckoutHistory(LibraryMember member) {
        this.member = member;
        memberCheckoutHistoryItems = new ArrayList<>();
    }

    public void addMemberCheckoutHistoryItem(MemberCheckoutHistoryItem entry) {
        this.memberCheckoutHistoryItems.add(entry);
    }

    public void removeMemberCheckoutHistoryItem(MemberCheckoutHistoryItem memberCheckoutHistoryItem) {
        this.memberCheckoutHistoryItems.remove(memberCheckoutHistoryItem);
    }
}

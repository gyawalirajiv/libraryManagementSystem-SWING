package librarysystem;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.List;

import javax.swing.*;

import business.ControllerInterface;
import business.SystemController;
import dataaccess.Auth;


public class LibrarySystem extends JFrame implements LibWindow {
	public static final String ALL_BOOKS = "All Books";
	public static final String CHECKOUT_BOOK = "Checkout Book";
	public static final String CHECKOUT_MEMBER_RECORD = "Checkout Member Record";
	public static final String ADD_BOOK = "Add Book";
	public static final String ADD_BOOK_COPY = "Add Book's Copy";
	public static final String ADD_NEW_MEMBER = "Add New Member";
	public static final String CHECK_OVERDUE_BOOK_COPY = "Check Overdue Book Copy";
	public static final String DASHBOARD = "Dashboard";
	ControllerInterface ci = new SystemController();
	public static LibrarySystem INSTANCE =new LibrarySystem();
	JPanel mainPanel;
	JMenuBar menuBar;
	JMenu options;
	JMenuItem logout;
	String pathToImage;
	private boolean isInitialized = false;
	JSplitPane splitPane;
	JList leftList;
	JPanel cardLayout;

	private static LibWindow[] allWindows = {
			LibrarySystem.INSTANCE,
			LoginWindow.INSTANCE,
			AllMemberIdsWindow.INSTANCE,
			AllBookIdsWindow.INSTANCE
	};

	private static Class[] librarianPages = {AllBookIdsWindow.class};
	private static Class[] adminPages = {AllMemberIdsWindow.class};

	public static void hideAllWindows() {

		for(LibWindow frame: allWindows) {
			frame.setVisible(false);

		}
	}

	private LibrarySystem() {}

	public void init() {
		formatContentPane();
		setPathToImage();
		insertSplashImage();

		createMenus();
		//pack();
		setSize(900,500);
		isInitialized = true;
	}

	private void formatContentPane() {
		mainPanel = new JPanel();
		mainPanel.setLayout(new GridLayout(1,1));
		createSplitPane();
	}

	private void createSplitPane() {
		splitPane = new JSplitPane();
		getContentPane().add(splitPane);

		createLeftSplitPane();
	}

	private void createLeftSplitPane() {
		leftList = new JList();

		String[] values;
		if(ci.getRole() == Auth.LIBRARIAN)
			values = new String[] {DASHBOARD, ALL_BOOKS, CHECKOUT_BOOK, CHECKOUT_MEMBER_RECORD, CHECK_OVERDUE_BOOK_COPY};
		else if(ci.getRole() == Auth.ADMIN)
			values = new String[] {DASHBOARD, ALL_BOOKS, ADD_BOOK, ADD_BOOK_COPY, ADD_NEW_MEMBER, CHECK_OVERDUE_BOOK_COPY};
		else
			values = new String[] {DASHBOARD, ALL_BOOKS, CHECKOUT_BOOK, CHECKOUT_MEMBER_RECORD, ADD_BOOK, ADD_BOOK_COPY, ADD_NEW_MEMBER, CHECK_OVERDUE_BOOK_COPY};
		leftList.setModel(new AbstractListModel() {
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		leftList.setSelectedIndex(0);
		splitPane.setLeftComponent(leftList);

		cardLayout = new JPanel();
		splitPane.setRightComponent(cardLayout);
		cardLayout.setLayout(new CardLayout(0, 0));
		cardLayout.add(new DashboardWindow().getMainPanel(), DASHBOARD);
		cardLayout.add(AllBookIdsWindow.getInstance().getMainPanel(), ALL_BOOKS);
		cardLayout.add(new CheckoutBookWindow().getMainPanel(), CHECKOUT_BOOK);
		cardLayout.add(new CheckoutMemberRecordWindow().getMainPanel(), CHECKOUT_MEMBER_RECORD);
		cardLayout.add(new AddBookWindow().getMainPanel(), ADD_BOOK);
		cardLayout.add(new AddBookCopyWindow().getMainPanel(), ADD_BOOK_COPY);
		cardLayout.add(new AddNewMemberWindow().getMainPanel(), ADD_NEW_MEMBER);
		cardLayout.add(new CheckOverDueBookCopyWindow().getMainPanel(), CHECK_OVERDUE_BOOK_COPY);

		leftList.addListSelectionListener(e -> {
			String value = leftList.getSelectedValue().toString();
			CardLayout cl = (CardLayout) (cardLayout.getLayout());
			cl.show(cardLayout, value);
		});
	}

	private void setPathToImage() {
		String currDirectory = System.getProperty("user.dir");
		pathToImage = currDirectory+"\\src\\librarysystem\\library.jpg";
	}

	private void insertSplashImage() {
		ImageIcon image = new ImageIcon(pathToImage);
		mainPanel.add(new JLabel(image));
	}
	private void createMenus() {
		menuBar = new JMenuBar();
		menuBar.setBorder(BorderFactory.createRaisedBevelBorder());
		addMenuItems();
		setJMenuBar(menuBar);
	}

	private void addMenuItems() {
		options = new JMenu("Options");
		menuBar.add(options);
		logout = new JMenuItem("Logout");
		logout.addActionListener(new LogoutListener());
		options.add(logout);
	}

	public void showLogin() {
		LibrarySystem.hideAllWindows();
		LoginWindow.INSTANCE.init();
		Util.centerFrameOnDesktop(LoginWindow.INSTANCE);
		LoginWindow.INSTANCE.setVisible(true);
	}

	class LogoutListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			LibrarySystem.hideAllWindows();
//			LoginWindow.INSTANCE.init();
			Util.centerFrameOnDesktop(LoginWindow.INSTANCE);
			LoginWindow.INSTANCE.setVisible(true);
		}

	}
	class AllBookIdsListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			LibrarySystem.hideAllWindows();
			AllBookIdsWindow.INSTANCE.init();

			List<String> ids = ci.allBookIds();
			Collections.sort(ids);
			StringBuilder sb = new StringBuilder();
			for(String s: ids) {
				sb.append(s + "\n");
			}
			System.out.println(sb.toString());
			AllBookIdsWindow.INSTANCE.setData(sb.toString());
			AllBookIdsWindow.INSTANCE.pack();
			//AllBookIdsWindow.INSTANCE.setSize(660,500);
			Util.centerFrameOnDesktop(AllBookIdsWindow.INSTANCE);
			AllBookIdsWindow.INSTANCE.setVisible(true);

		}

	}

	class AllMemberIdsListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			LibrarySystem.hideAllWindows();
			AllMemberIdsWindow.INSTANCE.init();
			AllMemberIdsWindow.INSTANCE.pack();
			AllMemberIdsWindow.INSTANCE.setVisible(true);


			LibrarySystem.hideAllWindows();
			AllBookIdsWindow.INSTANCE.init();

			List<String> ids = ci.allMemberIds();
			Collections.sort(ids);
			StringBuilder sb = new StringBuilder();
			for(String s: ids) {
				sb.append(s + "\n");
			}
			System.out.println(sb.toString());
			AllMemberIdsWindow.INSTANCE.setData(sb.toString());
			AllMemberIdsWindow.INSTANCE.pack();
			//AllMemberIdsWindow.INSTANCE.setSize(660,500);
			Util.centerFrameOnDesktop(AllMemberIdsWindow.INSTANCE);
			AllMemberIdsWindow.INSTANCE.setVisible(true);


		}

	}

	@Override
	public boolean isInitialized() {
		return isInitialized;
	}


	@Override
	public void isInitialized(boolean val) {
		isInitialized =val;

	}

	@Override
	public void setVisible(boolean b) {
		this.createLeftSplitPane();
		super.setVisible(b);
	}
}

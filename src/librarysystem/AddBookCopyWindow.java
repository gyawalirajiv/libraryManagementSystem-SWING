package librarysystem;

import business.ControllerInterface;
import business.LibrarySystemException;
import business.SystemController;

import javax.swing.*;
import java.awt.*;

public class AddBookCopyWindow {
    private final ControllerInterface ci = new SystemController();
    private final JPanel mainPanel;
    private JPanel topPanel;
    private JPanel outerMiddle;


    private JTextField isbn;

    public AddBookCopyWindow() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        defineTopPanel();
        defineOuterMiddle();
        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(outerMiddle, BorderLayout.CENTER);
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public void clearData() {
        isbn.setText("");
    }

    public void defineTopPanel() {
        topPanel = new JPanel();
        JLabel AddBookLabel = new JLabel("Add Book's Copy");
        Util.adjustLabelFont(AddBookLabel, Util.DARK_BLUE, true);
        topPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        topPanel.add(AddBookLabel);
    }

    public void defineOuterMiddle() {
        outerMiddle = new JPanel();
        outerMiddle.setLayout(new BorderLayout());

        //set up left and right panels
        JPanel middlePanel = new JPanel();
        FlowLayout fl = new FlowLayout(FlowLayout.CENTER, 25, 25);
        middlePanel.setLayout(fl);
        JPanel leftPanel = new JPanel();
        JPanel rightPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));

        JLabel bookIsbn = new JLabel("Enter ISBN");

        isbn = new JTextField(10);

        leftPanel.add(bookIsbn);
        leftPanel.add(Box.createRigidArea(new Dimension(0, 12)));


        rightPanel.add(isbn);
        rightPanel.add(Box.createRigidArea(new Dimension(0, 8)));

        middlePanel.add(leftPanel);
        middlePanel.add(rightPanel);
        outerMiddle.add(middlePanel, BorderLayout.NORTH);

        //add button at bottom
        JButton addBookButton = new JButton("Save");
        attachAddBookButtonListener(addBookButton);
        JPanel addBookButtonPanel = new JPanel();
        addBookButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        addBookButtonPanel.add(addBookButton);
        outerMiddle.add(addBookButtonPanel, BorderLayout.CENTER);

    }

    private void attachAddBookButtonListener(JButton butn) {
        butn.addActionListener(evt -> {
            String bookID = isbn.getText();

            if (bookID.length() == 0) {
                JOptionPane.showMessageDialog(mainPanel, "ISBN cannot be empty");
                return;
            }
            try {
                ci.addBookCopy(bookID);
                JOptionPane.showMessageDialog(mainPanel,"Book copy has been successfully added");
            } catch (LibrarySystemException e) {
                JOptionPane.showMessageDialog(mainPanel, e.getMessage());
            }
        });
    }
}

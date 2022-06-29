package librarysystem;

import business.ControllerInterface;
import business.LibrarySystemException;
import business.SystemController;

import javax.swing.*;
import java.awt.*;

public class CheckoutBookWindow extends JFrame {
    ControllerInterface ci = new SystemController();

    private final JPanel mainPanel;
    private JPanel topPanel;
    private JPanel middlePanel;
    private JPanel lowerPanel;

    private JTextField jTextField;
    private JTextField memberIdTextField;

    private JPanel innerPanel;

    private JButton proceedButton;

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public CheckoutBookWindow() {
        mainPanel = new JPanel();
        defineTopPanel();
        defineMiddlePanel();
        defineLowerPanel();
        BorderLayout bl = new BorderLayout();
        bl.setVgap(30);
        mainPanel.setLayout(bl);

        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(middlePanel, BorderLayout.CENTER);
        mainPanel.add(lowerPanel, BorderLayout.SOUTH);
    }

    private void defineTopPanel() {
        topPanel = new JPanel();
        JLabel label = new JLabel("Checkout Book for Member");
        Util.adjustLabelFont(label, Util.INFO_MESSAGE_COLOR, true);
        topPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        topPanel.add(label);
    }

    private void defineMiddlePanel() {
        middlePanel = new JPanel();
        BorderLayout bl = new BorderLayout();
        bl.setVgap(30);
        middlePanel.setLayout(bl);

        innerPanel = new JPanel();
        FlowLayout fl = new FlowLayout(FlowLayout.CENTER, 25, 25);
        innerPanel.setLayout(fl);

        JPanel leftPanel = new JPanel();
        JPanel rightPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));

        JLabel memberIdLabel = new JLabel("Member's ID");
        JLabel isbnLabel = new JLabel("Book's ISBN");

        memberIdTextField = new JTextField(10);
        jTextField = new JTextField(10);

        leftPanel.add(memberIdLabel);
        leftPanel.add(Box.createRigidArea(new Dimension(0,12)));
        leftPanel.add(isbnLabel);

        rightPanel.add(memberIdTextField);
        rightPanel.add(Box.createRigidArea(new Dimension(0,8)));
        rightPanel.add(jTextField);

        innerPanel.add(leftPanel);
        innerPanel.add(rightPanel);
        middlePanel.add(innerPanel, BorderLayout.NORTH);
    }

    private void defineLowerPanel() {
        lowerPanel = new JPanel();
        FlowLayout fl = new FlowLayout(FlowLayout.CENTER);
        lowerPanel.setLayout(fl);
        proceedButton = new JButton("Proceed");
        addCheckoutButtonListener(proceedButton);
        lowerPanel.add(proceedButton);
    }

    private void addCheckoutButtonListener(JButton checkoutBtn) {
        checkoutBtn.addActionListener(evt -> {
            String isbn = jTextField.getText().trim();
            String memberID = memberIdTextField.getText().trim();

            try {
                ci.checkoutBook(memberID, isbn);
                JOptionPane.showMessageDialog(mainPanel,"Success... Book: " + isbn + " Member ID: " + memberID);
            } catch (LibrarySystemException e) {
                JOptionPane.showMessageDialog(mainPanel, e.getMessage());
            }
        });
    }
}

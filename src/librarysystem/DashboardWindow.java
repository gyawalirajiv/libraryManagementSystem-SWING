package librarysystem;

import business.ControllerInterface;
import business.LibrarySystemException;
import business.SystemController;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class DashboardWindow extends JFrame {

    private final JPanel mainPanel;
    String pathToImage;

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public DashboardWindow() {
        mainPanel = new JPanel();

        BorderLayout bl = new BorderLayout();
        bl.setVgap(30);
        mainPanel.setLayout(bl);

        setPathToImage();
        insertSplashImage();
    }

    private void setPathToImage() {
        String currDirectory = System.getProperty("user.dir");
        pathToImage = currDirectory+"\\src\\librarysystem\\img.png";
    }

    private void insertSplashImage() {
        ImageIcon image = new ImageIcon(pathToImage);
        mainPanel.add(new JLabel(image));
    }
}

package librarysystem;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.*;


public class Main {

	public static void main(String[] args) {
		EventQueue.invokeLater(() ->
		{
			LibrarySystem.INSTANCE.setTitle("4 Idiots Library Management");
			LoginWindow.INSTANCE.setTitle("4 Idiots Library Management");
			LibrarySystem.INSTANCE.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

			LibrarySystem.INSTANCE.init();
			centerFrameOnDesktop(LibrarySystem.INSTANCE);
//			LibrarySystem.INSTANCE.setVisible(true);
			LibrarySystem.INSTANCE.showLogin();
		});
	}

	public static void centerFrameOnDesktop(Component f) {
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		int height = toolkit.getScreenSize().height;
		int width = toolkit.getScreenSize().width;
		int frameHeight = f.getSize().height;
		int frameWidth = f.getSize().width;
		f.setLocation(((width - frameWidth) / 2), (height - frameHeight) / 3);
	}
}

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.io.File;

public class DemoPopupMenu extends JFrame {
	JPopupMenu popupMenu;
	JMenuItem saveItem, cutItem, copyItem, pasteItem, helpItem;
	JTextArea textArea;

	public DemoPopupMenu() {
		textArea = new JTextArea("Click the mouse right button inside \nthe frame.");
		textArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
		getContentPane().add(textArea);

		// textArea.setSize(500,500);

		popupMenu = new JPopupMenu("Test Popup Menu");
		saveItem = new JMenuItem("Save");
		popupMenu.add(saveItem);
		popupMenu.addSeparator();
		cutItem = new JMenuItem("Cut");
		popupMenu.add(cutItem);
		copyItem = new JMenuItem("Copy");
		popupMenu.add(copyItem);
		pasteItem = new JMenuItem("Paste");
		popupMenu.add(pasteItem);
		popupMenu.addSeparator();
		helpItem = new JMenuItem("Help");
		popupMenu.add(helpItem);

		PopupMenuListener pml = new PopupMenuListener();
		textArea.addMouseListener(pml);
	}

	public void Dir(String path, String space) {
		File f = new File(path);
		String[] names = f.list();

		for (int i = 0; i < names.length; i++) {
			File fi = new File(path + "/" + names[i]);

			if (fi.isDirectory()) {
				textArea.append(space + "<DIR> " + names[i] + "\n");
				// System.out.print(space + "<DIR> "+names[i]);
				// System.out.println(names[i]);
				Dir(path + "/" + names[i], space + "|  ");
			} else
				textArea.append(space + "+" + names[i] + "\n");
		}
	}

	class PopupMenuListener extends MouseAdapter {
		public void mousePressed(MouseEvent me) {
			showPopup(me);
		}

		public void mouseReleased(MouseEvent me) {
			showPopup(me);
		}

		private void showPopup(MouseEvent me) {
			if (me.isPopupTrigger()) {
				popupMenu.show(me.getComponent(), me.getX(), me.getY());
			}
		}
	}

	public static void main(String[] args) {
		DemoPopupMenu frame = new DemoPopupMenu();
		frame.setTitle("Demo PopupMenu");
		frame.setSize(700, 800);
		frame.setVisible(true);
		frame.Dir("D:/", "");
	}
}
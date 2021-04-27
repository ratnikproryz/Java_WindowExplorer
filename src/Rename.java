import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

class Rename extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JTextField tfNewName;
	private JButton btRename;
	private File fileRename=null;
	/**
	 * Create the frame.
	 */
	public Rename( File file) {
		this.fileRename=file;
		setBounds(100, 100, 293, 205);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lbRename = new JLabel("New name:");
		lbRename.setBounds(15, 48, 95, 20);
		contentPane.add(lbRename);
		
		tfNewName = new JTextField();
		tfNewName.setBounds(110, 45, 146, 26);
		contentPane.add(tfNewName);
		tfNewName.setColumns(10);
		
		btRename = new JButton("OK");
		btRename.addActionListener(this);
		btRename.setBounds(73, 104, 115, 29);
		contentPane.add(btRename);
		
		tfNewName.setText(file.getAbsolutePath());
		
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	public Rename() {
		// TODO Auto-generated constructor stub
	}

	public String getAddress() {
		return tfNewName.getText();
	}
	public void renameFile(File file) {
		if(file.renameTo(new File(tfNewName.getText()))==true) {
			JOptionPane.showMessageDialog(null, "Success!");
		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		renameFile(fileRename);
	}
}

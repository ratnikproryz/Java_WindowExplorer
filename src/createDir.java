import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JRadioButton;

public class createDir extends JFrame {

	private JPanel contentPane;
	private JTextField tfDir;
	private JRadioButton rdDir,rdFile;
	private ButtonGroup bGroup=new ButtonGroup();

	/**
	 * Create the frame.
	 */
	public createDir(String add) {
		
		setBounds(100, 100, 427, 288);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lbCreateDir = new JLabel("New Directory:");
		lbCreateDir.setBounds(15, 64, 131, 40);
		contentPane.add(lbCreateDir);
		
		tfDir = new JTextField();
		tfDir.setText(add);
		tfDir.setBounds(135, 64, 228, 40);
		contentPane.add(tfDir);
		tfDir.setColumns(10);
		
		JButton btDir = new JButton("OK");
		btDir.setBounds(146, 160, 131, 40);
		contentPane.add(btDir);
		
		rdFile = new JRadioButton("File");
		rdFile.setBounds(219, 119, 113, 29);
		contentPane.add(rdFile);
		
		rdDir = new JRadioButton("Directory");
		rdDir.setBounds(99, 119, 113, 29);
		contentPane.add(rdDir);
		
		bGroup.add(rdDir);
		bGroup.add(rdFile);
		
		btDir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String add= tfDir.getText();
				File file=new File(add);
				if (rdDir.isSelected()) {
					createDirctory(file);
				}
				else {
					createFile(file);
				}
			}
		});
		System.out.println("Ok");
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	public void createDirctory(File file) {
		if(!file.exists()) {
			if(file.mkdirs()) {
				JOptionPane.showMessageDialog(null, "Success!");
				dispose();
			}
			else {
				JOptionPane.showMessageDialog(null,"Error!");
			}
		}
	}
	public void createFile(File file){
		try {
			file.createNewFile();
			JOptionPane.showMessageDialog(null,"Success!");
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null,e.getMessage());
		}
	}
}

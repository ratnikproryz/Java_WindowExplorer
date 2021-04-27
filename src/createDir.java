import java.awt.BorderLayout;
import java.awt.EventQueue;
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

public class createDir extends JFrame {

	private JPanel contentPane;
	private JTextField tfDir;


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
		btDir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				File file=new File(tfDir.getText());
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
		});
		System.out.println("Ok");
		setLocationRelativeTo(null);
		setVisible(true);
	}

}

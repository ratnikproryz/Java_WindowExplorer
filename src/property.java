import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.File;
import java.io.IOException;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

public class property extends JFrame {
	private File file= null;
	private JPanel contentPane;
	private JLabel lbNameValue, lbLocationValue, lbSizeValue, lbModifyValue, lbCreateDay;
	public property() {
		this.file=file;
		setBounds(100, 100, 335, 413);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lbName = new JLabel("Name:");
		lbName.setBounds(15, 50, 69, 20);
		contentPane.add(lbName);
		
		lbNameValue = new JLabel("null");
		lbNameValue.setBounds(143, 50, 155, 20);
		contentPane.add(lbNameValue);
		
		JLabel lbLocation = new JLabel("Location:");
		lbLocation.setBounds(15, 105, 69, 20);
		contentPane.add(lbLocation);
		
		lbLocationValue = new JLabel("null");
		lbLocationValue.setBounds(143, 105, 155, 20);
		contentPane.add(lbLocationValue);
		
		JLabel lbSize = new JLabel("Size:");
		lbSize.setBounds(15, 153, 69, 20);
		contentPane.add(lbSize);
		
		lbSizeValue = new JLabel("null");
		lbSizeValue.setBounds(143, 153, 155, 20);
		contentPane.add(lbSizeValue);
		
		JLabel lbCreate = new JLabel("Created");
		lbCreate.setBounds(15, 207, 69, 20);
		contentPane.add(lbCreate);
		
		lbCreateDay = new JLabel("null");
		lbCreateDay.setBounds(143, 207, 155, 20);
		contentPane.add(lbCreateDay);
		
		JLabel lbModify = new JLabel(" Modified");
		lbModify.setBounds(15, 261, 69, 20);
		contentPane.add(lbModify);
		
		lbModifyValue = new JLabel("null");
		lbModifyValue.setBounds(143, 261, 155, 20);
		contentPane.add(lbModifyValue);
		setLocationRelativeTo(null);
		setVisible(true);
		
	}
	public void getProperty(File file) {
		lbNameValue.setText(file.getName());
		lbLocationValue.setText(file.getAbsolutePath());
//		lbSizeValue.setText(file.length()+" bytes");
		lbSizeValue.setText(file.length()+" bytes");
		lbModifyValue.setText(new Date(file.lastModified()).toString());
	}
}

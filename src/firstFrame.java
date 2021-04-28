import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.Panel;
import java.awt.PopupMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.util.Arrays;

import javax.swing.DefaultListCellRenderer;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.EmptyBorder;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.filechooser.FileSystemView;
import javax.swing.text.DefaultEditorKit.PasteAction;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;

import org.omg.CORBA.PUBLIC_MEMBER;

import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.JScrollPane;
import javax.swing.JScrollBar;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JButton;

public class firstFrame extends JFrame implements TreeSelectionListener{
	private JPanel contentPane;
	private JTextField tfAddress;
//	private File file=null;
	private DefaultMutableTreeNode treeThisPC;
	private JTree treeExplore;
	private String address;
	private JPopupMenu popupMenu, subMenu;
	private JMenuItem newItem, cutItem, copyItem, pasteItem, deleteItem, propertiesItem, renameItem, dirItem, fileItem;
	private JScrollPane scrollPane_1;
	private JList jList = new JList();
	private JPanel panel;
	private String srcPath, desPath;
	private boolean cut=false;
	private firstFrame frame;
	private JButton btReset;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					firstFrame frame = new firstFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public firstFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1117, 718);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
//
		popupMenu = new JPopupMenu("Popup Menu");
		newItem = new JMenuItem("New");
		cutItem = new JMenuItem("Cut");
		copyItem = new JMenuItem("Copy");
		pasteItem = new JMenuItem("Paste");
		propertiesItem = new JMenuItem("Properties");
		deleteItem = new JMenuItem("Delete");
		renameItem=new JMenuItem("Rename");
		
		popupMenu.add(copyItem);
		popupMenu.addSeparator();//tao duong phan cach giua cac item
		popupMenu.add(cutItem);
		popupMenu.addSeparator();
		popupMenu.add(pasteItem);
		popupMenu.addSeparator();
		popupMenu.add(newItem);
		popupMenu.addSeparator();
		popupMenu.add(deleteItem);
		popupMenu.addSeparator();
		popupMenu.add(propertiesItem);
		popupMenu.addSeparator();
		popupMenu.add(renameItem);
		popupMenu.addSeparator();
		
//		subMenu= new JPopupMenu("Sub Menu");
//		dirItem=new JMenuItem("Directoty");
//		fileItem= new JMenuItem("File");
//		subMenu.add(dirItem);
//		subMenu.addSeparator();
//		subMenu.add(fileItem);
//		popupMenu.add(dirItem);
//		popupMenu.addSeparator();
//		popupMenu.add(fileItem);
//		
		// add actionListener for jmenuitem
		copyItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cut=false;
				srcPath=tfAddress.getText();
			}
		});
		cutItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				srcPath=tfAddress.getText();
				cut=true;
			}
		});
		pasteItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				desPath=tfAddress.getText();
				String src=srcPath;
				//lay dia chi thu muc nguon de tao thanh thu muc dich
				for(int i=src.length()-1; i>0; i--) {
					if(src.charAt(i)== '\\' || src.charAt(i)=='/') {
						src=src.substring(i, src.length());
						System.out.println(src);
						break;
					}
				}
				
				pasteFile(new File(srcPath), new File(desPath+src));
				if (cut) {
					//neu chon cut thi sau khi paste phai xoa file nguon
					deleteDir(new File(srcPath));
				}
			}
		});
		//
		newItem.addMouseListener(new MouseListener() {
			public void mouseReleased(MouseEvent arg0) {

			}
			public void mousePressed(MouseEvent arg0) {
//				subMenu.show(arg0.getComponent(), arg0.getX(), arg0.getY());
				createDir crDir= new createDir(tfAddress.getText());
			}
			public void mouseExited(MouseEvent arg0) {
				
			}
			public void mouseEntered(MouseEvent arg0) {
				
			}
			public void mouseClicked(MouseEvent arg0) {
			}
		});
		//
		propertiesItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new property().getProperty(new File(tfAddress.getText()));
			}
		});	
		//
		deleteItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int choice= JOptionPane.showConfirmDialog(null, "Delete this file"+ tfAddress.getText(), "Delete", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if(choice==0) {
					int check=deleteDir(new File(tfAddress.getText()));
					if(check==1) {
						JOptionPane.showMessageDialog(null, "Deleted");
					}
				}
				listFile(address);
			}
		});
		
		renameItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Rename r= new Rename(new File(tfAddress.getText()));						
			}
		});
		
		//gan hanh dong cho submenu
		
//		dirItem.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent arg0) {
//				System.out.println("create dir");
//				createDir crDir= new createDir(tfAddress.getText());
//			}
//		});
//		fileItem.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent arg0) {
//				System.out.println("file");
//			}
//		});
		
		// jscroolpane has a tree exxplore
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(28, 69, 182, 544);
		contentPane.add(scrollPane);

		// create tree explorer
		treeExplore = null;
		treeThisPC = new DefaultMutableTreeNode("This PC");
		// 
		java.util.List<File> files = Arrays.asList(File.listRoots());
		for (File drv : files) {
			String harddisk = FileSystemView.getFileSystemView().getSystemDisplayName(drv);
			if (harddisk.equals("Windows (C:)")) {
				continue;
			}
			DefaultMutableTreeNode harddiskNode = new DefaultMutableTreeNode(harddisk);
			treeThisPC.add(harddiskNode);
			harddisk = harddisk.substring(harddisk.length() - 3, harddisk.length() - 1) + "/";
			createTreeExplorer(harddiskNode, harddisk);
		}
		treeExplore = new JTree(treeThisPC);
//		add action to the tree explorer
		treeExplore.getSelectionModel().addTreeSelectionListener((TreeSelectionListener) this);
		DefaultTreeCellRenderer renderer = (DefaultTreeCellRenderer) treeExplore.getCellRenderer();
		Icon openIcon = new ImageIcon("folder.png");
		renderer.setClosedIcon(openIcon);
		renderer.setOpenIcon(openIcon);

		scrollPane.setViewportView(treeExplore);

		// display the link of the file
		tfAddress = new JTextField();
		tfAddress.setBounds(207, 34, 737, 37);
		contentPane.add(tfAddress);
		tfAddress.setColumns(10);

		JLabel lbAddress = new JLabel("Address");
		lbAddress.setBounds(127, 38, 83, 28);
		contentPane.add(lbAddress);

		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(208, 69, 853, 544);
		contentPane.add(scrollPane_1);
		
		btReset = new JButton("Reset");
		btReset.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String newAdd=tfAddress.getText();
				for(int i=newAdd.length()-1; i>0; i--) {
					if(newAdd.charAt(i)== '\\' || newAdd.charAt(i)=='/') {
						newAdd=newAdd.substring(0,i+1);
						break;
					}
				}
				listFile(newAdd);
			}
		});
		btReset.setBounds(941, 34, 120, 37);
		contentPane.add(btReset);

		jList.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				if(SwingUtilities.isRightMouseButton(arg0)){
					JList list = (JList)arg0.getSource();
					String address= list.getSelectedValue().toString();
//					address=convertPath(address);
					tfAddress.setText(address);
		            int row = list.locationToIndex(arg0.getPoint());
		            list.setSelectedIndex(row);
		            showPopupMenu(arg0);		            
				};
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				if(SwingUtilities.isRightMouseButton(arg0)){
					tfAddress.setText(address);
					JList list = (JList)arg0.getSource();
		            int row = list.locationToIndex(arg0.getPoint());
		            list.setSelectedIndex(row);
		            showPopupMenu(arg0);		            
				};
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				openDirectory(arg0);
			}
		});
	}
	
	public void createTreeExplorer(DefaultMutableTreeNode dNode, String path) {
		try {
			File file = new File(path);
			String[] fileList = file.list();
			if (fileList != null) {
				for (int i = 0; i < fileList.length; i++) {
					String nameOfSubPath = fileList[i];
					String subPath = path + "/" + nameOfSubPath;
					DefaultMutableTreeNode subNode = new DefaultMutableTreeNode(nameOfSubPath);
					dNode.add(subNode);
					File subFile = new File(subPath);
					if (subFile.isDirectory()) {
						createTreeExplorer(subNode, subPath);
					}
				}
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}

	}

	@Override
	public void valueChanged(TreeSelectionEvent arg0) {
		// TODO Auto-generated method stub
		address = arg0.getPath().toString();// lay dia chi tu cay thu muc
		int x = arg0.getPath().getPathCount();
		address = getPathTree(x, arg0);// chuyen dia chi o cay htu muc thanh dia chi trong may tinh
		listFile(address);
	}

	public String getPathTree(int x, TreeSelectionEvent arg0) {
		String treeAddress = arg0.getPath().toString();
		String add = "";
		int length = treeAddress.length();
		for (int i = 0; i < length; i++) {
			if (treeAddress.charAt(i) == '(') {
				add = treeAddress.charAt(i + 1) + "";
			}
			if (treeAddress.charAt(i) == ')') {
				add += treeAddress.charAt(i - 1);
				break;
			}
		}
		if(x==2) {
			add+="/";
		}
		else {
			for (int i = 2; i < x; i++) {
				add += "/" + arg0.getPath().getPathComponent(i);
			}
		}
		tfAddress.setText(add);
		return add;
	}
	public void listFile(String path) {
		try {
			jList.setListData(new File(path).listFiles());
			jList.setCellRenderer(new MyCellRenderer());
			jList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
			jList.setLayoutOrientation(javax.swing.JList.VERTICAL);
			scrollPane_1.setViewportView(jList);
			tfAddress.setText(path);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}

	private static class MyCellRenderer extends DefaultListCellRenderer {

		private static final long serialVersionUID = 1L;

		@Override
		public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected,
				boolean cellHasFocus) {
			if (value instanceof File) {
				File file = (File) value;
				setText(file.getName());
				setIcon(FileSystemView.getFileSystemView().getSystemIcon(file));
				if (isSelected) {
					setBackground(list.getSelectionBackground());
					setForeground(list.getSelectionForeground());
				} else {
					setBackground(list.getBackground());
					setForeground(list.getForeground());
				}
				setEnabled(list.isEnabled());
				setFont(list.getFont());
				setOpaque(true);
			}
			return this;
		}
	}
	public void openDirectory(MouseEvent event) {
		if (event.getClickCount()==1) {
			address= jList.getSelectedValue()+"";//lay duong dan file duoc chon	
			tfAddress.setText(address);
		}
		if(event.getClickCount()==2 ) {
			File file = new File(address);
			if(file.isDirectory()) {
				listFile(address);//neu file la thu muc thi hien thi cac tep/thu muc con
			}
			else {
				Desktop desktop= Desktop.getDesktop();//mo tep bang desktop
				try {
					desktop.open(file);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e.getMessage());
				}
			}
		}
	}
	public void showPopupMenu(MouseEvent event) {
		if(event.isPopupTrigger()) {
			popupMenu.show(event.getComponent(), event.getX(), event.getY());
		}
	}
	public static void pasteFile(File src, File des) {
		if(src.isDirectory()) {
			copyDirectory(src,des);
		}else {
			try {
				copyFile(src, des);
//				JOptionPane.showMessageDialog(null, "Completed!");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, e.getMessage());
			}
		}
	}
	public static void copyDirectory(File sourceFile, File destinationFile) {
		if(!destinationFile.exists()) {
			destinationFile.mkdir();// neu chua ton tai thu muc thif tao thu muc
		}
		String f[]=sourceFile.list();
		if(f!=null) {
			for(int i=0; i<f.length; i++) {
				pasteFile(new File(sourceFile, f[i]), new File(destinationFile, f[i]));
			} 
			
		}
	}
	private static void copyFile(File sourceFile, File destinationFile) 
			  throws IOException {
			    try (InputStream in = new FileInputStream(sourceFile); 
			    	OutputStream out = new FileOutputStream(destinationFile)) {
			        byte[] buf = new byte[1024];
			        int length;
			        while ((length = in.read(buf)) > 0) {
			            out.write(buf, 0, length);
			        }
			    }
			}
	public int deleteDir(File file) {
		int check=1;
		try {
			File fi[]= file.listFiles();
			if(fi!=null) {
				for(int i=0;i<fi.length; i++) {
					deleteDir(fi[i]);
				}
			}
			file.delete();
			listFile(address);
		} catch (Exception e) {
			check=0;
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		return check;
	}

	
}

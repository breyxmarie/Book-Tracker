package bookTracker;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientConfig;

import net.codejava.ws.Book;

import java.awt.Color;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.BoxLayout;
import javax.swing.DropMode;

public class GuiAddBook extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextArea textField_3;

	/**
	 * Launch the application.
	 */
	
	/////// establish web target
	 private static String baseURI = "http://localhost:8080/ProjectWebsite/rest/books";
	 
	    static WebTarget getWebTarget() {
	        ClientConfig config = new ClientConfig();
	        Client client = ClientBuilder.newClient(config);
	         
	        return client.target(baseURI);     
	    }
	     
	    
	    
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GuiAddBook frame = new GuiAddBook();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GuiAddBook() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1280, 791);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(229, 233, 236));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(253, 231, 76));
		panel.setBounds(0, 0, 1264, 60);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_3 = new JLabel("BookTracker\r\n");
		lblNewLabel_3.setFont(new Font("SansSerif", Font.BOLD, 32));
		lblNewLabel_3.setBounds(102, 8, 196, 42);
		panel.add(lblNewLabel_3);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(new javax.swing.ImageIcon(GuiMyBooks.class.getResource("/bookTracker/download.png")).getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
		lblNewLabel.setBounds(1072, 10, 46, 40);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Hi, User!");
		lblNewLabel_1.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(1128, 10, 110, 40);
		//panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_4 = new JLabel("New label");
		lblNewLabel_4.setIcon(new ImageIcon(new javax.swing.ImageIcon(GuiMyBooks.class.getResource("/bookTracker/book.png")).getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
		lblNewLabel_4.setBounds(38, 1, 52, 49);
		panel.add(lblNewLabel_4);
		
		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setBorder(null);
		comboBox_2.setEditable(true);
		comboBox_2.setBounds(1237, 19, 17, 22);
		panel.add(comboBox_2);
		comboBox_2.setBackground(new Color(253, 231, 76));
		comboBox_2.setForeground(new Color(253, 231, 76));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(91, 124, 153));
		panel_1.setBounds(0, 59, 303, 693);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		final JButton btnNewButton = new JButton("  Home");
		btnNewButton.setHorizontalAlignment(SwingConstants.LEFT);
		btnNewButton.setSelectedIcon(new ImageIcon(new javax.swing.ImageIcon(GuiMyBooks.class.getResource("/bookTracker/home (1).png")).getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH)));
		btnNewButton.setIcon(new ImageIcon(new javax.swing.ImageIcon(GuiMyBooks.class.getResource("/bookTracker/home (1).png")).getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH)));
		btnNewButton.setFont(new Font("SansSerif", Font.BOLD, 24));
		btnNewButton.setBackground(Color.decode("#4F7AA3"));
		btnNewButton.setBounds(0, 72, 320, 55);
		panel_1.add(btnNewButton);
		
		btnNewButton.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseEntered(MouseEvent e) {
		    	btnNewButton.setBackground(Color.decode("#515373"));
		    }

		    @Override
		    public void mouseExited(MouseEvent e) {
		    	btnNewButton.setBackground(Color.decode("#4F7AA3"));
		    }
		});
		
		final JButton btnMyBooks = new JButton("  My Books\r\n");
		btnMyBooks.setHorizontalAlignment(SwingConstants.LEFT);
		btnMyBooks.setIcon(new ImageIcon(new javax.swing.ImageIcon(GuiMyBooks.class.getResource("/bookTracker/book (1).png")).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH)));
		btnMyBooks.setFont(new Font("SansSerif", Font.BOLD, 24));
		btnMyBooks.setBackground(Color.decode("#4F7AA3"));
		btnMyBooks.setBounds(0, 148, 320, 55);
		panel_1.add(btnMyBooks);
		
		btnMyBooks.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseEntered(MouseEvent e) {
		    	btnMyBooks.setBackground(Color.decode("#515373"));
		    }

		    @Override
		    public void mouseExited(MouseEvent e) {
		    	btnMyBooks.setBackground(Color.decode("#4F7AA3"));
		    }
		});
		
		final JButton btnAddBooks = new JButton("  Add Book\r\n\r\n");
		btnAddBooks.setHorizontalAlignment(SwingConstants.LEFT);
		btnAddBooks.setIcon(new ImageIcon(new javax.swing.ImageIcon(GuiMyBooks.class.getResource("/bookTracker/book (2).png")).getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH)));
		btnAddBooks.setFont(new Font("SansSerif", Font.BOLD, 24));
		btnAddBooks.setBackground(Color.decode("#4F7AA3"));
		btnAddBooks.setBounds(0, 217, 320, 55);
		panel_1.add(btnAddBooks);
		
		btnAddBooks.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseEntered(MouseEvent e) {
		    	btnAddBooks.setBackground(Color.decode("#515373"));
		    }

		    @Override
		    public void mouseExited(MouseEvent e) {
		    	btnAddBooks.setBackground(Color.decode("#4F7AA3"));
		    }
		});
		
		
		////////////////////////////////nav bar button mouse click event

		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GuiMain mains = new GuiMain();
				setVisible(false);
				mains.setVisible(true);
			}
		});

		btnMyBooks.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GuiMyBooks mainss = new GuiMyBooks();
				setVisible(false);
				mainss.setVisible(true);
			}
		});

		btnAddBooks.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GuiAddBook mainsss = new GuiAddBook();
				setVisible(false);
				mainsss.setVisible(true);
			}
		});
////////////////////////////////////////////////////////////////
		
		JLabel lblNewLabel_1_1 = new JLabel("ADD BOOK\r\n");
		lblNewLabel_1_1.setBackground(new Color(240, 240, 240));
		lblNewLabel_1_1.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1_1.setFont(new Font("SansSerif", Font.BOLD, 40));
		lblNewLabel_1_1.setBounds(352, 102, 368, 42);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_2 = new JLabel("Title:");
		lblNewLabel_2.setFont(new Font("SansSerif", Font.BOLD, 24));
		lblNewLabel_2.setBounds(352, 194, 63, 21);
		contentPane.add(lblNewLabel_2);
		
		textField = new JTextField();
		textField.setBounds(425, 181, 303, 34);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_2_1 = new JLabel("Author:");
		lblNewLabel_2_1.setFont(new Font("SansSerif", Font.BOLD, 24));
		lblNewLabel_2_1.setBounds(820, 194, 86, 21);
		contentPane.add(lblNewLabel_2_1);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(916, 181, 303, 34);
		contentPane.add(textField_1);
		
		JLabel lblNewLabel_2_1_1 = new JLabel("Publish Year:");
		lblNewLabel_2_1_1.setFont(new Font("SansSerif", Font.BOLD, 24));
		lblNewLabel_2_1_1.setBounds(352, 272, 169, 21);
		contentPane.add(lblNewLabel_2_1_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(531, 260, 124, 34);
		contentPane.add(textField_2);
		
		JLabel lblNewLabel_2_1_1_1 = new JLabel("Genre:");
		lblNewLabel_2_1_1_1.setFont(new Font("SansSerif", Font.BOLD, 24));
		lblNewLabel_2_1_1_1.setBounds(709, 272, 93, 21);
		contentPane.add(lblNewLabel_2_1_1_1);
		
		String[] optionsGenre = {"Romance", 
								"Horror", 
								"Thriller",
								"Fiction",
								"Mystery",
								"Drama",
								"Nonfiction",
								"Drama",
								"Poetry",
								"Folktale",
								"Novel"};
		
		final JComboBox comboBox = new JComboBox(optionsGenre);
		comboBox.setBounds(812, 259, 108, 34);
		contentPane.add(comboBox);
		
		JLabel lblNewLabel_2_1_1_1_1 = new JLabel("Status:\r\n");
		lblNewLabel_2_1_1_1_1.setFont(new Font("SansSerif", Font.BOLD, 24));
		lblNewLabel_2_1_1_1_1.setBounds(990, 272, 100, 30);
		contentPane.add(lblNewLabel_2_1_1_1_1);
		
		
		String[] options = {"To Be Read", "Currently Reading", "Done Reading"};
        
		final JComboBox comboBox_1 = new JComboBox(options);
		comboBox_1.setBounds(1088, 259, 128, 50);
		contentPane.add(comboBox_1);
		
		JLabel lblNewLabel_2_1_1_2 = new JLabel("Plot:");
		lblNewLabel_2_1_1_2.setFont(new Font("SansSerif", Font.BOLD, 24));
		lblNewLabel_2_1_1_2.setBounds(352, 385, 152, 21);
		contentPane.add(lblNewLabel_2_1_1_2);
		
		textField_3 = new JTextArea();
		textField_3.setLineWrap(true);
		textField_3.setWrapStyleWord(true);
		textField_3.setFont(new Font("SansSerif", Font.PLAIN, 14));
		textField_3.setColumns(10);
		textField_3.setBounds(352, 420, 876, 192);
		contentPane.add(textField_3);
		
		JButton btnNewButton_1 = new JButton("ADD");
		btnNewButton_1.setFont(new Font("SansSerif", Font.PLAIN, 24));
		btnNewButton_1.setBounds(461, 652, 124, 42);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_1_1 = new JButton("SAVE & ADD ANOTHER");
		btnNewButton_1_1.setFont(new Font("SansSerif", Font.PLAIN, 24));
		btnNewButton_1_1.setBounds(616, 652, 304, 42);
		//contentPane.add(btnNewButton_1_1);
		
		JButton btnNewButton_1_2 = new JButton("RESET");
		btnNewButton_1_2.setFont(new Font("SansSerif", Font.PLAIN, 24));
		btnNewButton_1_2.setForeground(new Color(0, 0, 0));
		btnNewButton_1_2.setBounds(954, 652, 124, 42);
		contentPane.add(btnNewButton_1_2);
		
		
		
		
		btnNewButton_1.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	  WebTarget target = getWebTarget();
				    Book book = new Book(textField.getText(),
				    					textField_1.getText(),
				    					textField_2.getText(),
				    					String.valueOf(comboBox.getSelectedItem()),
				    					textField_3.getText(),
				    					String.valueOf(comboBox_1.getSelectedItem())
				    					);
				    Response response = target.request()
				            .post(Entity.entity(book, MediaType.APPLICATION_JSON), Response.class);
				    JOptionPane.showMessageDialog(null, "Book Added!");
					textField.setText("");
					textField_1.setText("");
					textField_2.setText("");
					comboBox.setSelectedIndex(0);
					comboBox_1.setSelectedIndex(0);
					textField_3.setText("");
		    }
		});
		
		
		btnNewButton_1_2.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	textField.setText("");
				textField_1.setText("");
				textField_2.setText("");
				comboBox.setSelectedIndex(0);
				comboBox_1.setSelectedIndex(0);
				textField_3.setText("");
				
		    }
		});
	}
}

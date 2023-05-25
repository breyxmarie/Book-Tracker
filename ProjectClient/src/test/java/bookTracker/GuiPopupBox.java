package bookTracker;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientConfig;

import net.codejava.ws.Book;

@SuppressWarnings("serial")
public class GuiPopupBox extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextArea textField_5;

	/**
	 * Launch the application.
	 */

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
					Book books = new Book();

					GuiPopupBox frame = new GuiPopupBox(books, "");
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
	public GuiPopupBox(final Book book, final String screen) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		System.out.println(book.getAuthor());
		// setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 500);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(229, 233, 236));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("");
		// lblNewLabel.setIcon(new ImageIcon(new
		// javax.swing.ImageIcon(GuiMain.class.getResource("/bookTracker/default
		// book.png")).getImage().getScaledInstance(160, 180, Image.SCALE_SMOOTH)));
		ImageIcon imageIcon = new ImageIcon(book.getCover());

		// get the Image from the ImageIcon
		Image image = imageIcon.getImage();

		// set the scale of the Image using the getScaledInstance() method
		Image scaledImage = image.getScaledInstance(120, 180, Image.SCALE_SMOOTH);

		// create a new ImageIcon from the scaled Image
		ImageIcon scaledImageIcon = new ImageIcon(scaledImage);

		lblNewLabel.setIcon(scaledImageIcon);

		lblNewLabel.setBounds(41, 33, 126, 178);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_2 = new JLabel("TITLE:");
		lblNewLabel_2.setFont(new Font("SansSerif", Font.BOLD, 16));
		lblNewLabel_2.setBounds(220, 33, 57, 21);
		contentPane.add(lblNewLabel_2);

		JLabel lblNewLabel_2_1 = new JLabel("AUTHOR:\r\n");
		lblNewLabel_2_1.setFont(new Font("SansSerif", Font.BOLD, 16));
		lblNewLabel_2_1.setBounds(220, 67, 81, 21);
		contentPane.add(lblNewLabel_2_1);

		textField = new JTextField();

		textField.setColumns(10);
		textField.setText(book.getTitle());
		textField.setBounds(352, 33, 192, 21);
		contentPane.add(textField);

		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setText(book.getAuthor());
		textField_1.setBounds(352, 67, 192, 21);
		contentPane.add(textField_1);

		JLabel lblNewLabel_2_1_1 = new JLabel("PUBLISH YEAR:");
		lblNewLabel_2_1_1.setFont(new Font("SansSerif", Font.BOLD, 16));
		lblNewLabel_2_1_1.setBounds(220, 119, 126, 21);
		contentPane.add(lblNewLabel_2_1_1);

		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setText(book.getPubYear());
		textField_2.setBounds(352, 122, 192, 21);
		contentPane.add(textField_2);

		JLabel lblNewLabel_2_1_2 = new JLabel("GENRE:\r\n");
		lblNewLabel_2_1_2.setFont(new Font("SansSerif", Font.BOLD, 16));
		lblNewLabel_2_1_2.setBounds(220, 151, 81, 21);
		contentPane.add(lblNewLabel_2_1_2);

		JLabel lblNewLabel_2_1_3 = new JLabel("STATUS:\r\n");
		lblNewLabel_2_1_3.setFont(new Font("SansSerif", Font.BOLD, 16));
		lblNewLabel_2_1_3.setBounds(220, 183, 81, 21);
		contentPane.add(lblNewLabel_2_1_3);

		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setText(book.getGenre());
		textField_3.setBounds(352, 151, 192, 21);
		// contentPane.add(textField_3);

		String[] optionsGenre = { "Romance", "Horror", "Thriller", "Fiction", "Mystery", "Drama", "Nonfiction", "Drama",
				"Poetry", "Folktale", "Novel" };

		final JComboBox comboBoxs = new JComboBox(optionsGenre);
		comboBoxs.setBounds(352, 151, 192, 21);
		contentPane.add(comboBoxs);
		comboBoxs.setSelectedItem(book.getGenre());

		// textField_4 = new JTextField();
		// textField_4.setColumns(10);
		// textField_4.setBounds(352, 179, 192, 21);
		// contentPane.add(textField_4);

		// Create a new menu bar
		String[] options = { "To Be Read", "Currently Reading", "Done Reading" };

		// Create a combo box and add the options to it
		final JComboBox<String> comboBox = new JComboBox(options);
		comboBox.setBounds(352, 179, 192, 21);
		comboBox.setSelectedItem(book.getStatus());
		contentPane.add(comboBox);

		JLabel lblNewLabel_2_1_3_1 = new JLabel("PLOT:\r\n");
		lblNewLabel_2_1_3_1.setFont(new Font("SansSerif", Font.BOLD, 16));
		lblNewLabel_2_1_3_1.setBounds(41, 222, 81, 21);
		contentPane.add(lblNewLabel_2_1_3_1);

		textField_5 = new JTextArea();
		textField_5.setColumns(10);
		textField_5.setLineWrap(true);
		textField_5.setWrapStyleWord(true);
		textField_5.setText(book.getPlot());
		textField_5.setBounds(41, 253, 503, 137);
		contentPane.add(textField_5);

		JButton btnNewButton_1 = new JButton("UPDATE & EDIT");
		btnNewButton_1.setFont(new Font("SansSerif", Font.PLAIN, 16));
		btnNewButton_1.setBounds(116, 408, 161, 42);
		contentPane.add(btnNewButton_1);

		JButton btnNewButton_1_1 = new JButton("DELETE");
		btnNewButton_1_1.setFont(new Font("SansSerif", Font.PLAIN, 16));
		btnNewButton_1_1.setBounds(324, 408, 124, 42);
		contentPane.add(btnNewButton_1_1);

		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Code to be executed when the button is clicked
				WebTarget target = getWebTarget();
				// Book product = new Product("ZenFoneX", 100f);
				Book product = new Book(textField.getText(), textField_1.getText(), textField_2.getText(),
						comboBoxs.getSelectedItem().toString(), textField_5.getText(),
						comboBox.getSelectedItem().toString());
				// String productId = "7";
				String Bid = String.valueOf(book.getId());
				Response response = target.path(Bid).request().put(Entity.entity(product, MediaType.APPLICATION_JSON),
						Response.class);
				System.out.println(response);
				JOptionPane.showMessageDialog(null, "Book Updated!");

				// GuiMyBooks mainss = new GuiMyBooks();

				// mainss.setVisible(true);
				// setVisible(true);

				if (screen == "main") {
					GuiMain mainsss = new GuiMain();

					mainsss.setVisible(true);
					// setVisible(false);
					// setVisible(false);

					// GuiPopupBox pops = new GuiPopupBox(book, "main");
					// pops.setVisible(true);
					// dispose();
				} else {
					GuiMyBooks mainss = new GuiMyBooks();

					// mainss.setVisible(true);
					// setVisible(true);
				}
			}
		});

		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WebTarget target = getWebTarget();
				// String productId = "9";
				String Bid = String.valueOf(book.getId());
				Response response = target.path(Bid).request().delete(Response.class);
				System.out.println(response);
				JOptionPane.showMessageDialog(null, "Book Deleted!");
				dispose();
			}
		});
	}
}

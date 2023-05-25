package bookTracker;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientConfig;

import net.codejava.ws.Book;

@SuppressWarnings("serial")
public class PopUpUser extends JFrame {

	private JPanel contentPane;
	private JLabel textField;
	private JLabel textField_1;
	private JLabel textField_2;
	private JLabel textField_3;
	private JLabel textField_4;
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
		ClientConfig config = new ClientConfig();
		Client client = ClientBuilder.newClient(config);
		WebTarget target = client.target(baseURI);
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Book books = new Book();

					PopUpUser frame = new PopUpUser(books, "", new User());
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
	public PopUpUser(final Book book, final String screen, final User userss) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		System.out.println(book.getId());
		// setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 500);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(229, 233, 236));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel ratingsCom1 = new JPanel();

		JLabel user1 = new JLabel("user");
		JLabel rating1 = new JLabel("rating");
		JLabel comment1 = new JLabel("comment");

		user1.setBounds(10, 10, 100, 30);
		rating1.setBounds(10, 50, 100, 30);
		comment1.setBounds(10, 70, 100, 30);
		ratingsCom1.add(user1);
		ratingsCom1.add(rating1);
		ratingsCom1.add(comment1);

		ratingsCom1.setBorder(BorderFactory.createLineBorder(Color.black));
		ratingsCom1.setBounds(550, 0, 900, 150);
		ratingsCom1.setLayout(null);
		// contentPane.add(ratingsCom1);

		JPanel ratingsCom2 = new JPanel();

		JLabel user2 = new JLabel("user");
		JLabel rating2 = new JLabel("rating");
		JLabel comment2 = new JLabel("comment");

		user2.setBounds(10, 10, 100, 30);
		rating2.setBounds(10, 50, 100, 30);
		comment2.setBounds(10, 70, 100, 30);
		ratingsCom2.add(user2);
		ratingsCom2.add(rating2);
		ratingsCom2.add(comment2);

		ratingsCom2.setBorder(BorderFactory.createLineBorder(Color.black));
		ratingsCom2.setBounds(550, 150, 900, 150);
		ratingsCom2.setLayout(null);
		// contentPane.add(ratingsCom2);

		JPanel ratingsCom3 = new JPanel();

		JLabel user3 = new JLabel("user");
		JLabel rating3 = new JLabel("rating");
		JLabel comment3 = new JLabel("comment");

		user3.setBounds(10, 10, 100, 30);
		rating3.setBounds(10, 50, 100, 30);
		comment3.setBounds(10, 70, 100, 30);
		ratingsCom3.add(user3);
		ratingsCom3.add(rating3);
		ratingsCom3.add(comment3);

		ratingsCom3.setBorder(BorderFactory.createLineBorder(Color.black));
		ratingsCom3.setBounds(550, 300, 900, 150);
		ratingsCom3.setLayout(null);
		// contentPane.add(ratingsCom3);

		JLabel lblNewLabel = new JLabel("");

		// convert byte image to image icon
		ImageIcon imageIcon = new ImageIcon(book.getCover());
		Image image = imageIcon.getImage();
		Image scaledImage = image.getScaledInstance(120, 180, Image.SCALE_SMOOTH);
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

		textField = new JLabel();

		// textField.setColumns(10);
		textField.setText(book.getTitle());
		textField.setBounds(352, 33, 192, 21);
		contentPane.add(textField);

		textField_1 = new JLabel();
		// textField_1.setColumns(10);
		textField_1.setText(book.getAuthor());
		textField_1.setBounds(352, 67, 192, 21);
		contentPane.add(textField_1);

		JLabel lblNewLabel_2_1_1 = new JLabel("PUBLISH YEAR:");
		lblNewLabel_2_1_1.setFont(new Font("SansSerif", Font.BOLD, 16));
		lblNewLabel_2_1_1.setBounds(220, 119, 126, 21);
		contentPane.add(lblNewLabel_2_1_1);

		textField_2 = new JLabel();
		// textField_2.setColumns(10);
		textField_2.setText(book.getPubYear());
		textField_2.setBounds(352, 122, 192, 21);
		contentPane.add(textField_2);

		JLabel lblNewLabel_2_1_2 = new JLabel("GENRE:\r\n");
		lblNewLabel_2_1_2.setFont(new Font("SansSerif", Font.BOLD, 16));
		lblNewLabel_2_1_2.setBounds(220, 151, 81, 21);
		contentPane.add(lblNewLabel_2_1_2);

		textField_3 = new JLabel();
		// textField_3.setColumns(10);
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

		JButton btnNewButton_1 = new JButton("Add To Library");
		btnNewButton_1.setFont(new Font("SansSerif", Font.PLAIN, 16));
		btnNewButton_1.setBounds(116, 408, 161, 42);
		contentPane.add(btnNewButton_1);

		final WebTarget target = getWebTarget();

		btnNewButton_1.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				Map<String, Integer> data = new HashMap<String, Integer>();
				data.put("user_id", userss.getUserId());
				data.put("book_id", book.getId());

				Response response = target.path("users/{user_id}/books/{book_id}")
						.resolveTemplate("user_id", userss.getUserId()).resolveTemplate("book_id", book.getId())
						.request().post(Entity.entity(data, MediaType.APPLICATION_JSON), Response.class);
				JOptionPane.showMessageDialog(null, "Book Added!");
			}

		});

		JButton btnNewButton_1_1 = new JButton("DELETE");
		btnNewButton_1_1.setFont(new Font("SansSerif", Font.PLAIN, 16));
		btnNewButton_1_1.setBounds(324, 408, 124, 42);
		// contentPane.add(btnNewButton_1_1);

		JPanel Contents = new JPanel();
		Contents.setLayout(new BoxLayout(Contents, BoxLayout.Y_AXIS));

		// for (String item : data) {

		LinkedHashMap<String, LinkedHashMap<String, Object>> ratingsComment = target.path("rate")
				.queryParam("book_id", book.getId()).request().accept(MediaType.APPLICATION_JSON)
				.get(new GenericType<LinkedHashMap<String, LinkedHashMap<String, Object>>>() {
				});

		System.out.println(ratingsComment);

		try {

			for (Map.Entry<String, LinkedHashMap<String, Object>> entry : ratingsComment.entrySet()) {
				JPanel ratingsComs = new JPanel();
				LinkedHashMap<String, Object> valueMap = entry.getValue();
				ratingsComs.setLayout(new BorderLayout());
				ratingsComs.setSize(100, 500);
				ratingsComs.setBorder(BorderFactory.createLineBorder(Color.black));

				JLabel users = new JLabel((String) valueMap.get("title"));
				JLabel ratings = new JLabel((String) valueMap.get("ratings"));
				JLabel comments = new JLabel((String) valueMap.get("comment"));

				users.setBounds(10, 10, 100, 30);
				ratings.setBounds(10, 50, 100, 30);
				comments.setBounds(10, 70, 100, 30);

				ratingsComs.add(users, BorderLayout.NORTH);
				ratingsComs.add(ratings, BorderLayout.CENTER);
				ratingsComs.add(comments, BorderLayout.SOUTH);
				Contents.add(ratingsComs);
			}
		}

		catch (Exception e) {
			System.out.println(e);
		}

		JScrollPane CommentPane = new JScrollPane(Contents);

		CommentPane.setBounds(550, 0, 330, 465);
		// CommentPane.setMaximumSize(new Dimension(200, 50));
		CommentPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		contentPane.add(CommentPane);
		// CommentPane.setViewportView(Contents);

		contentPane.add(CommentPane);
	}
}

package bookTracker;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Base64;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.client.ClientConfig;

import net.codejava.ws.Book;

public class UserHome extends JFrame {

	private JPanel contentPane = new JPanel();
	private JPanel NavBar = new JPanel();
	private JPanel top = new JPanel();
	private JPanel BookInfo = new JPanel();
	private JLabel LibraryLabel = new JLabel();
	private JLabel SearchLabel = new JLabel("Search");
	private JTextField SeachTxtField = new JTextField();
	private JButton searchBtn = new JButton("🔎");
	private JTable SearchResult = new JTable();
	private JButton HomeBtn = new JButton("Home");

	private JButton LibraryBtn = new JButton("My Library");
	private JButton LogOutBtn = new JButton("Log Out");
	private JLabel LogoImg = new JLabel();
	private JLabel BookLabel = new JLabel("BookTracker");
	private JLabel userImg = new JLabel("Hi! ");
	private JLabel userNameLbl = new JLabel("Hi! ");
	private JLabel CoverImg = new JLabel();
	private JLabel TitleLbl = new JLabel("Title");
	private JLabel AuthorLbl = new JLabel("Author");
	private JLabel YearLbl = new JLabel("Year");
	private JLabel GenreLbl = new JLabel("Genre");
	private JLabel PlotLbl = new JLabel("Plot");
	private JLabel PlotShowLbl = new JLabel("Plot");
	private JLabel StatusLbl = new JLabel();
	private JLabel StatusShowLbl = new JLabel();
	private JLabel StartLbl = new JLabel("Start Date");
	private JLabel StartShowLbl = new JLabel();
	private JLabel EndLbl = new JLabel("End Date");
	private JLabel EndShowLbl = new JLabel();
	private JLabel RatingLbl = new JLabel("Rating");
	private JLabel RatingShowLbl = new JLabel();
	private JLabel CommentLbl = new JLabel("Comment");
	private JLabel CommentShowLbl = new JLabel();
	private JButton SaveBtn = new JButton("Save");

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
					UserHome frame = new UserHome(new User());
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public UserHome(final User users) {
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBounds(10, 10, 1280, 789);

		contentPane.setBackground(new Color(229, 233, 236));
		contentPane.setBorder(new LineBorder(new Color(128, 128, 128)));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		HomeBtn.setIcon(new ImageIcon(new javax.swing.ImageIcon(GuiMain.class.getResource("/bookTracker/book (2).png"))
				.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH)));
		HomeBtn.setBounds(0, 80, 250, 55);
		HomeBtn.setBackground(Color.decode("#4F7AA3"));
		NavBar.add(HomeBtn);

		HomeBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				HomeBtn.setBackground(Color.decode("#515373"));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				HomeBtn.setBackground(Color.decode("#4F7AA3"));
			}
		});

		LibraryBtn
				.setIcon(new ImageIcon(new javax.swing.ImageIcon(GuiMain.class.getResource("/bookTracker/book (1).png"))
						.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH)));
		LibraryBtn.setBounds(0, 150, 250, 55);
		LibraryBtn.setBackground(Color.decode("#4F7AA3"));
		NavBar.add(LibraryBtn);

		LibraryBtn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				GuiLibrary library = new GuiLibrary(users);
				library.setVisible(true);
				setVisible(false);
			}

		});
		LibraryBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				LibraryBtn.setBackground(Color.decode("#515373"));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				LibraryBtn.setBackground(Color.decode("#4F7AA3"));
			}
		});

		LogOutBtn
				.setIcon(new ImageIcon(new javax.swing.ImageIcon(GuiMain.class.getResource("/bookTracker/book (1).png"))
						.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH)));
		LogOutBtn.setBounds(0, 220, 250, 55);
		LogOutBtn.setBackground(Color.decode("#4F7AA3"));
		NavBar.add(LogOutBtn);

		LogOutBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				LogOutBtn.setBackground(Color.decode("#515373"));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				LogOutBtn.setBackground(Color.decode("#4F7AA3"));
			}
		});

		LogOutBtn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				GuiLogIn logOut = new GuiLogIn();
				logOut.setVisible(true);
				dispose();
			}

		});

		NavBar.setBackground(Color.decode("#060713"));
		NavBar.setBounds(0, 60, 250, 900);
		NavBar.setBorder(new LineBorder(new Color(128, 128, 128)));
		NavBar.setLayout(null);
		contentPane.add(NavBar);

		top.setBackground(new Color(253, 231, 76));
		top.setBounds(0, 0, 1264, 59);
		contentPane.add(top);
		top.setLayout(null);

		userImg.setIcon(new ImageIcon(new javax.swing.ImageIcon(GuiMain.class.getResource("/bookTracker/download.png"))
				.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
		userImg.setText("Hi " + users.getName());
		userImg.setBounds(1017, 10, 206, 42);
		top.add(userImg);

		BookLabel.setIcon(new ImageIcon(new javax.swing.ImageIcon(GuiMain.class.getResource("/bookTracker/book.png"))
				.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
		BookLabel.setBounds(22, 0, 500, 42);
		BookLabel.setFont(new Font("SansSerif", Font.BOLD, 32));
		top.add(BookLabel);

		SearchLabel.setVerticalAlignment(SwingConstants.TOP);
		SearchLabel.setHorizontalAlignment(SwingConstants.LEFT);
		SearchLabel.setFont(new Font("SansSerif", Font.BOLD, 30));
		SearchLabel.setBounds(950, 70, 368, 53);
		contentPane.add(SearchLabel);

		SeachTxtField.setForeground(new Color(128, 128, 128));
		SeachTxtField.setBackground(new Color(192, 192, 192));
		SeachTxtField.setFont(new Font("Tahoma", Font.PLAIN, 24));
		SeachTxtField.setText("Search Title");
		SeachTxtField.setBounds(840, 120, 350, 42);
		contentPane.add(SeachTxtField);

		SeachTxtField.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) {
				if (SeachTxtField.getText().equals("Search Title")) {
					SeachTxtField.setText("");
				}
			}

			public void focusLost(FocusEvent e) {
				if (SeachTxtField.getText().equals("")) {
					SeachTxtField.setText("Search Title");
				}
			}
		});

		searchBtn.setBounds(1200, 120, 60, 42);
		contentPane.add(searchBtn);

		final WebTarget target = getWebTarget();

		LinkedHashMap<String, LinkedHashMap<String, Object>> responseMapTitle = target.path("RecommendBooks")
				.queryParam("userId", users.getUserId()).request().accept(MediaType.APPLICATION_JSON)
				.get(new GenericType<LinkedHashMap<String, LinkedHashMap<String, Object>>>() {
				});

		HashMap<String, Book> recommendBooks = new HashMap<String, Book>();

		LinkedHashMap<String, LinkedHashMap<String, Object>> recommendsBooks = target.path("RecommendBooks")
				.queryParam("userId", users.getUserId()).request().accept(MediaType.APPLICATION_JSON)
				.get(new GenericType<LinkedHashMap<String, LinkedHashMap<String, Object>>>() {
				});
		try {

			for (Map.Entry<String, LinkedHashMap<String, Object>> entry : responseMapTitle.entrySet()) {
				String key = entry.getKey();
				// LinkedHashMap<String, Object> book = entry.getValue();
				LinkedHashMap<String, Object> valueMap = entry.getValue();
				System.out.println(entry.getValue());
				// System.out.println(entry.getValue());
				// Extract the Book data from the valueMap
				String title = (String) valueMap.get("title");
				String author = (String) valueMap.get("author");
				String pub_year = (String) valueMap.get("pubYear");
				String genre = (String) valueMap.get("genre");
				String plot = (String) valueMap.get("plot");
				// byte[] images = valueMap.get("cover");

				String str = valueMap.get("cover").toString();

				Object coverObj = valueMap.get("cover");
				byte[] byteArray = null;

				try {

					// byteArray = (byte[]) coverObj;

					String base64String = (String) coverObj;
					byteArray = Base64.getDecoder().decode(base64String);

				} catch (Exception t) {
					System.out.println(t);
				}

				Book book = new Book(Integer.parseInt(key), title, author, pub_year, genre, plot, byteArray);
				// book.setCover(images);
				recommendBooks.put(key, book);
			}
		}

		catch (Exception es) {
			System.out.println(es);
			JOptionPane.showMessageDialog(null, "No Books Found");
		}

		SearchResult.setFont(new Font("Tahoma", Font.PLAIN, 17));
		String[] columns = { "All Books" };
		DefaultTableModel modelsResults = new DefaultTableModel(columns, 0);
		SearchResult.setModel(modelsResults);

		for (Map.Entry<String, Book> entry : recommendBooks.entrySet()) {
			String key = entry.getKey();
			Book book = entry.getValue();
			// System.out.println("Key: " + key + ", Book: " + book.getTitle());
			Object[] row = { book.getTitle() };
			modelsResults.addRow(row);
		}

		SearchResult.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = SearchResult.rowAtPoint(e.getPoint());
				int col = SearchResult.columnAtPoint(e.getPoint());
				Object value = SearchResult.getValueAt(row, col);
				WebTarget targets = getWebTarget();
				System.out.println("Clicked cell value: " + value);
				String sampleData = value.toString();
				Book product = targets.path(sampleData).request().accept(MediaType.APPLICATION_JSON).get(Book.class);

				// System.out.println(product.getAuthor());
				// System.out.println(product.getPlot());

				PopUpUser PopUp = new PopUpUser(product, "pop", users);
				PopUp.setVisible(true);
			}
		});

		JScrollPane scrollPanesCur = new JScrollPane(SearchResult);
		scrollPanesCur.setBounds(860, 232, 380, 408);
		contentPane.add(scrollPanesCur);

		searchBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Handle the button click event here
				LinkedHashMap<String, LinkedHashMap<String, Object>> responseMapTitle = null;

				try {
					responseMapTitle = target.path("titleResultInLibrary/{title}/{user_id}")
							.resolveTemplate("title", SeachTxtField.getText())
							.resolveTemplate("user_id", users.getUserId()).request()

							.accept(MediaType.APPLICATION_JSON)
							.get(new GenericType<LinkedHashMap<String, LinkedHashMap<String, Object>>>() {
							});
					// Create a new HashMap to hold the converted Book objects

				}
				// Loop through the responseMap and convert each entry to a Book object

				catch (Exception ds) {
					JOptionPane.showMessageDialog(null, "No Books Found");
				}

				HashMap<String, Book> bookMapTitle = new HashMap<String, Book>();

				try {

					for (Map.Entry<String, LinkedHashMap<String, Object>> entry : responseMapTitle.entrySet()) {
						String key = entry.getKey();
						LinkedHashMap<String, Object> valueMap = entry.getValue();
						// System.out.println(entry.getValue());
						// Extract the Book data from the valueMap
						String title = (String) valueMap.get("title");
						String author = (String) valueMap.get("author");
						String pub_year = (String) valueMap.get("pubYear");
						String genre = (String) valueMap.get("genre");
						String plot = (String) valueMap.get("plot");
						String status = (String) valueMap.get("status");

						// Create a new Book object
						Book book = new Book(Integer.parseInt(key), title, author, pub_year, genre, plot, status);
						bookMapTitle.put(key, book);
					}
				}

				catch (Exception es) {
					JOptionPane.showMessageDialog(null, "No Books Found");
				}

				String[] columns = { "All Books" };
				DefaultTableModel modelsTitle = new DefaultTableModel(columns, 0);
				for (Map.Entry<String, Book> entry : bookMapTitle.entrySet()) {
					String key = entry.getKey();
					Book book = entry.getValue();
					// System.out.println("Key: " + key + ", Book: " + book.getTitle());
					Object[] row = { book.getTitle() };
					modelsTitle.addRow(row);
				}

				SearchResult.setModel(modelsTitle);
			}
		});

		CoverImg.setIcon(
				new ImageIcon(new javax.swing.ImageIcon(GuiLibrary.class.getResource("/bookTracker/default book.png"))
						.getImage().getScaledInstance(160, 180, Image.SCALE_SMOOTH)));
		CoverImg.setBounds(330, 160, 161, 190);
		// contentPane.add(CoverImg);

		JLabel recom = (new JLabel("Recommended For You"));
		recom.setBounds(300, 15, 400, 190);
		recom.setFont(new Font("Tahoma", Font.BOLD, 30));
		contentPane.add(recom);

		TitleLbl.setFont(new Font("SansSerif", Font.BOLD, 17));
		TitleLbl.setForeground(Color.black);
		TitleLbl.setBounds(390, 260, 161, 190);
		// contentPane.add(TitleLbl);

		AuthorLbl.setFont(new Font("SansSerif", Font.BOLD, 17));
		AuthorLbl.setForeground(Color.black);
		AuthorLbl.setBounds(385, 280, 161, 190);
		// contentPane.add(AuthorLbl);

		JButton title1 = new JButton("See Book");
		title1.setBounds(360, 400, 100, 30);
		// contentPane.add(title1);

		JPanel RBooks = new JPanel();
		int RowCount = recommendBooks.size() / 2;
		RBooks.setLayout(new GridLayout(0, 2));
		JScrollPane recPane = new JScrollPane(RBooks);
		// recPane.add(RBooks);

		for (Map.Entry<String, Book> entry : recommendBooks.entrySet()) {
			String key = entry.getKey();
			Book book = entry.getValue();
			// System.out.println("Key: " + key + ", Book: " + book.getTitle());
			Object[] row = { book.getTitle() };
			modelsResults.addRow(row);

			JPanel panel = new JPanel();
			panel.setLayout(new BorderLayout());

			ImageIcon imageIcon = new ImageIcon(book.getCover());
			// System.out.println(book.getCover());
			Image image = imageIcon.getImage();
			Image scaledImage = image.getScaledInstance(120, 180, Image.SCALE_SMOOTH);
			ImageIcon scaledImageIcon = new ImageIcon(scaledImage);

			// System.out.println(book.getCover());
			JLabel imageLabel = new JLabel();
			imageLabel.setIcon(scaledImageIcon);
			JPanel labelContainers = new JPanel(new FlowLayout(FlowLayout.CENTER));
			labelContainers.add(imageLabel);
			panel.add(labelContainers, BorderLayout.NORTH);

			JLabel textLabel = new JLabel(book.getTitle());
			JPanel labelContainer = new JPanel(new FlowLayout(FlowLayout.CENTER));

			// Add the label to the container
			labelContainer.add(textLabel);
			panel.add(labelContainer, BorderLayout.CENTER);

			JButton button = new JButton("Show Info");
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					WebTarget targets = getWebTarget();
					// System.out.println("Clicked cell value: " + value);
					String sampleData = book.getTitle();
					Book product = targets.path(sampleData).request().accept(MediaType.APPLICATION_JSON)
							.get(Book.class);

					// System.out.println(product.getAuthor());
					// System.out.println(product.getPlot());

					PopUpUser PopUp = new PopUpUser(product, "pop", users);
					PopUp.setVisible(true);
				}

			});

			JPanel labelContainerss = new JPanel(new FlowLayout(FlowLayout.CENTER));
			labelContainerss.add(button);
			panel.add(labelContainerss, BorderLayout.SOUTH);

			RBooks.add(panel);
		}

		recPane.setBounds(300, 170, 520, 500);
		// RBooks.setBounds(390, 260, 800, 300);
		contentPane.add(recPane);

	}

}

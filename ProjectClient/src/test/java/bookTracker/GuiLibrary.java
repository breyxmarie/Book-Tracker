package bookTracker;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientConfig;
import org.jdatepicker.JDatePicker;
import org.jdatepicker.UtilDateModel;

import net.codejava.ws.Book;

public class GuiLibrary extends JFrame {

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
	private JButton LogOutBtn = new JButton("Log Out");
	private JButton LibraryBtn = new JButton("My Library");
	private JLabel LogoImg = new JLabel();
	private JLabel BookLabel = new JLabel("BookTracker");
	private JLabel userImg = new JLabel("Hi! ");
	private JLabel userNameLbl = new JLabel("Hi! ");
	private JLabel CoverImg = new JLabel();
	private JLabel TitleLbl = new JLabel("Title", JLabel.CENTER);
	private JLabel AuthorLbl = new JLabel("Author");
	private JLabel YearLbl = new JLabel("Year");
	private JLabel GenreLbl = new JLabel("Genre");
	private JLabel PlotLbl = new JLabel("Plot");
	private JTextArea PlotShowLbl = new JTextArea();
	private JLabel StatusLbl = new JLabel();
	private JLabel StatusShowLbl = new JLabel("Status: ");
	private JLabel StartLbl = new JLabel("Start Date");
	private JLabel StartShowLbl = new JLabel();
	private JLabel EndLbl = new JLabel("End Date");
	private JLabel EndShowLbl = new JLabel();
	private JLabel RatingLbl = new JLabel("Rating");
	private JTextField RatingShowLbl = new JTextField();
	private JLabel CommentLbl = new JLabel("Comment");
	private JTextArea CommentShowLbl = new JTextArea();
	private JButton SaveBtn = new JButton("Save");
	String sDate1 = "2023-04-17";
	String sDate2 = "";
	int id = 0;

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
					GuiLibrary frame = new GuiLibrary(new User());
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public GuiLibrary(final User users) {
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBounds(100, 10, 1280, 789);

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

		HomeBtn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				UserHome homes = new UserHome(users);
				homes.setVisible(true);
				dispose();
			}

		});

		LibraryBtn
				.setIcon(new ImageIcon(new javax.swing.ImageIcon(GuiMain.class.getResource("/bookTracker/book (1).png"))
						.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH)));
		LibraryBtn.setBounds(0, 150, 250, 55);
		LibraryBtn.setBackground(Color.decode("#4F7AA3"));
		NavBar.add(LibraryBtn);

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

		NavBar.setBackground(Color.decode("#060713"));
		NavBar.setBounds(0, 60, 250, 900);
		NavBar.setBorder(new LineBorder(new Color(128, 128, 128)));
		NavBar.setLayout(null);
		contentPane.add(NavBar);

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

		top.setBackground(new Color(253, 231, 76));
		top.setBounds(0, 0, 1264, 59);
		contentPane.add(top);
		top.setLayout(null);

		userImg.setIcon(new ImageIcon(new javax.swing.ImageIcon(GuiMain.class.getResource("/bookTracker/download.png"))
				.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
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
		SearchLabel.setBounds(270, 146, 368, 53);
		contentPane.add(SearchLabel);

		SeachTxtField.setForeground(new Color(128, 128, 128));
		SeachTxtField.setBackground(new Color(192, 192, 192));
		SeachTxtField.setFont(new Font("Tahoma", Font.PLAIN, 24));
		SeachTxtField.setText("Search Title");
		SeachTxtField.setBounds(390, 146, 390, 42);
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

		searchBtn.setBounds(800, 150, 60, 42);
		contentPane.add(searchBtn);

		JScrollPane scrollPanesCur = new JScrollPane(SearchResult);
		scrollPanesCur.setBounds(340, 262, 443, 408);
		contentPane.add(scrollPanesCur);

		final WebTarget target = getWebTarget();

		LinkedHashMap<String, LinkedHashMap<String, Object>> responseMapTitle = target.path("Library/{user_id}")
				.resolveTemplate("user_id", users.getUserId()).request().accept(MediaType.APPLICATION_JSON)
				.get(new GenericType<LinkedHashMap<String, LinkedHashMap<String, Object>>>() {
				});

		HashMap<String, Book> recommendBooks = new HashMap<String, Book>();

		try {

			for (Map.Entry<String, LinkedHashMap<String, Object>> entry : responseMapTitle.entrySet()) {
				String key = entry.getKey();
				LinkedHashMap<String, Object> valueMap = entry.getValue();
				System.out.println(entry.getValue());
				// Extract the Book data from the valueMap
				String title = (String) valueMap.get("title");
				String author = (String) valueMap.get("author");
				String pub_year = (String) valueMap.get("pubYear");
				String genre = (String) valueMap.get("genre");
				String plot = (String) valueMap.get("plot");
				// String status = (String) valueMap.get("status");

				// Create a new Book object
				Book book = new Book(Integer.parseInt(key), title, author, pub_year, genre, plot);
				book.setCover(null);
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

		searchBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Handle the button click event here
				LinkedHashMap<String, LinkedHashMap<String, Object>> responseMapTitle = null;

				try {
					responseMapTitle = target.path("titleResultInLibrary/{title}/{user_id}")
							.resolveTemplate("title", SeachTxtField.getText())
							.resolveTemplate("user_id", users.getUserId()).request().accept(MediaType.APPLICATION_JSON)
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
						System.out.println(entry.getValue());
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
		CoverImg.setAlignmentX(Component.CENTER_ALIGNMENT);
		BookInfo.add(CoverImg);

		TitleLbl.setFont(new Font("SansSerif", Font.BOLD, 13));
		TitleLbl.setForeground(Color.black);
		TitleLbl.setAlignmentX(Component.CENTER_ALIGNMENT);

		BookInfo.add(TitleLbl);

		AuthorLbl.setFont(new Font("SansSerif", Font.BOLD, 20));
		AuthorLbl.setForeground(Color.black);
		AuthorLbl.setBounds(140, 175, 161, 190);
		AuthorLbl.setAlignmentX(Component.CENTER_ALIGNMENT);
		BookInfo.add(AuthorLbl);

		YearLbl.setFont(new Font("SansSerif", Font.BOLD, 20));
		YearLbl.setForeground(Color.black);
		YearLbl.setBounds(50, 205, 161, 190);
		YearLbl.setAlignmentX(Component.CENTER_ALIGNMENT);
		BookInfo.add(YearLbl);

		GenreLbl.setFont(new Font("SansSerif", Font.BOLD, 20));
		GenreLbl.setForeground(Color.black);
		GenreLbl.setBounds(200, 205, 161, 190);
		GenreLbl.setAlignmentX(Component.CENTER_ALIGNMENT);
		BookInfo.add(GenreLbl);

		PlotShowLbl.setFont(new Font("SansSerif", Font.BOLD, 12));
		PlotShowLbl.setForeground(Color.black);
		// PlotShowLbl.setBounds(60, 260, 161, 190);
		PlotShowLbl.setMaximumSize(new Dimension(400, 100));
		PlotShowLbl.setLineWrap(true); // Enable line wrapping
		PlotShowLbl.setWrapStyleWord(true);
		// GenreLbl.setAlignmentX(Component.CENTER_ALIGNMENT);
		BookInfo.add(PlotShowLbl);

		StatusLbl.setFont(new Font("SansSerif", Font.BOLD, 20));
		StatusLbl.setForeground(Color.black);
//		StatusLbl.setBounds(60, 280, 161, 190);
//		StatusLbl.setText("Status");
//		StatusLbl.setAlignmentX(Component.CENTER_ALIGNMENT);
//		BookInfo.add(StatusLbl);

		StatusShowLbl.setFont(new Font("SansSerif", Font.BOLD, 20));
		StatusShowLbl.setForeground(Color.black);

		JPanel containssss = new JPanel(new FlowLayout());
		containssss.setBackground(Color.decode("#242853"));
		containssss.add(StatusShowLbl);
		containssss.add(StatusLbl);
		containssss.setMaximumSize(new Dimension(400, 60));
		BookInfo.add(containssss);

		LocalDate initialDate = LocalDate.now();
		UtilDateModel models = new UtilDateModel();
		models.setDate(initialDate.getYear(), initialDate.getMonthValue() - 1, initialDate.getDayOfMonth()); // Note:
		models.setSelected(true);
		Date selectedDates = (Date) models.getValue();

		StartLbl.setFont(new Font("SansSerif", Font.BOLD, 20));
		StartLbl.setForeground(Color.black);

		final LocalDate date = LocalDate.parse(sDate1, DateTimeFormatter.ISO_DATE);
		final UtilDateModel model = new UtilDateModel();
		// model.setDate(1990, 7, 24);
		model.setDate(date.getYear(), date.getMonthValue(), date.getDayOfMonth());
		model.setSelected(true);
		JDatePicker datePicker = new JDatePicker(models);
		datePicker.setBounds(60, 400, 150, 30);
		datePicker.setMaximumSize(new Dimension(200, 50));
		datePicker.setAlignmentX(Component.CENTER_ALIGNMENT);
		JPanel contain = new JPanel(new FlowLayout());
		contain.setBackground(Color.decode("#242853"));
		contain.add(StartLbl);
		contain.add(datePicker);
		contain.setMaximumSize(new Dimension(400, 35));
		BookInfo.add(contain);

		// StartLbl.setText(book.getStartDate());
		// EndLbl.setText(book.getEndDate());

		EndLbl.setFont(new Font("SansSerif", Font.BOLD, 20));
		EndLbl.setForeground(Color.black);
		Date selectedDate = (Date) datePicker.getModel().getValue();
		UtilDateModel modelsEnd = new UtilDateModel();
		modelsEnd.setDate(initialDate.getYear(), initialDate.getMonthValue() - 1, initialDate.getDayOfMonth());
		modelsEnd.setSelected(true);
		JDatePicker datePickerEnd = new JDatePicker(modelsEnd);
		datePickerEnd.setBounds(60, 600, 150, 30);
		datePickerEnd.setMaximumSize(new Dimension(200, 50));
		datePickerEnd.setAlignmentX(Component.CENTER_ALIGNMENT);

		JPanel contains = new JPanel(new FlowLayout());
		contains.setBackground(Color.decode("#242853"));
		contains.add(EndLbl);
		contains.add(datePickerEnd);
		contains.setMaximumSize(new Dimension(400, 35));
		BookInfo.add(contains);

		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");

//		RatingLbl.setFont(new Font("SansSerif", Font.BOLD, 20));
		RatingLbl.setForeground(Color.black);
//		RatingLbl.setBounds(80, 400, 161, 190);

		RatingShowLbl.setFont(new Font("SansSerif", Font.BOLD, 20));
		RatingShowLbl.setForeground(Color.black);
		// RatingShowLbl.setBounds(80, 400, 161, 50);
		// RatingShowLbl.setAlignmentX(Component.CENTER_ALIGNMENT);
		RatingShowLbl.setColumns(10);
		RatingShowLbl.setMaximumSize(new Dimension(200, 50));

		JPanel containss = new JPanel(new FlowLayout());
		containss.setBackground(Color.decode("#242853"));
		containss.add(RatingLbl);
		containss.add(RatingShowLbl);
		containss.setMaximumSize(new Dimension(400, 35));
		BookInfo.add(containss);

		CommentLbl.setFont(new Font("SansSerif", Font.BOLD, 20));
		CommentLbl.setForeground(Color.black);
		CommentLbl.setBounds(40, 430, 161, 190);

		CommentShowLbl.setFont(new Font("SansSerif", Font.BOLD, 15));
		CommentShowLbl.setForeground(Color.black);
		CommentShowLbl.setColumns(15);
		CommentShowLbl.setRows(3);
		CommentShowLbl.setMaximumSize(new Dimension(400, 100));
		CommentShowLbl.setLineWrap(true); // Enable line wrapping
		CommentShowLbl.setWrapStyleWord(true);
//		CommentShowLbl.setBounds(40, 430, 161, 50);
//		CommentShowLbl.setMaximumSize(new Dimension(200, 50));
//		CommentShowLbl.setAlignmentX(Component.CENTER_ALIGNMENT);

		JPanel containsss = new JPanel(new FlowLayout());
		containsss.setBackground(Color.decode("#242853"));
		containsss.add(CommentLbl);
		containsss.add(CommentShowLbl);
		containsss.setMaximumSize(new Dimension(400, 60));
		BookInfo.add(containsss);
		// BookInfo.add(CommentShowLbl);

		SaveBtn.setForeground(Color.black);
		SaveBtn.setBounds(70, 590, 161, 30);
		SaveBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
		BookInfo.add(SaveBtn);

		SaveBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				Book bookTempo = new Book();

				bookTempo.setId(id);
				bookTempo.setRatings(RatingShowLbl.getText());
				bookTempo.setComment(CommentShowLbl.getText());
				bookTempo.setStatus(StatusLbl.getText());

				// bookTempo.setEndDate(sDate2);
				LocalDate selectedDates = LocalDate.of(models.getYear(), models.getMonth() + 1, models.getDay());
				bookTempo.setStartDate(selectedDates.toString());

				LocalDate dateEnds = LocalDate.parse(sDate2, DateTimeFormatter.ISO_DATE);
				bookTempo.setEndDate(dateEnds.toString());

				System.out.println(sDate1);
				Date Dates = model.getValue();
				LocalDate selectedDate = LocalDate.of(models.getYear(), models.getMonth() + 1, models.getDay());
				// System.out.println(selectedDate);

				Response response = target.path("users/{user_id}").resolveTemplate("user_id", users.getUserId())
						.request().put(Entity.entity(bookTempo, MediaType.APPLICATION_JSON), Response.class);
				System.out.println(response);

			}

		});

		SearchResult.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = SearchResult.rowAtPoint(e.getPoint());
				int col = SearchResult.columnAtPoint(e.getPoint());
				Object value = SearchResult.getValueAt(row, col);
				WebTarget targets = getWebTarget();
				System.out.println("Clicked cell value: " + value);
				String sampleData = value.toString();
				Book book = target.path("userBooksDeets/{user_id}/{title}")
						.resolveTemplate("user_id", users.getUserId()).resolveTemplate("title", sampleData).request()

						.accept(MediaType.APPLICATION_JSON).get(Book.class);

				model.setDate(date.getYear(), date.getMonthValue(), date.getDayOfMonth());
				model.setSelected(true);
				ImageIcon imageIcon = new ImageIcon(book.getCover());

				// get the Image from the ImageIcon
				Image image = imageIcon.getImage();

				// set the scale of the Image using the getScaledInstance() method
				Image scaledImage = image.getScaledInstance(120, 180, Image.SCALE_SMOOTH);

				// create a new ImageIcon from the scaled Image
				ImageIcon scaledImageIcon = new ImageIcon(scaledImage);

				CoverImg.setIcon(scaledImageIcon);

				SwingUtilities.invokeLater(() -> {

				});
				id = book.getId();
				TitleLbl.setText(book.getTitle());
				TitleLbl.setAlignmentX(Component.CENTER_ALIGNMENT);
				AuthorLbl.setText(book.getAuthor());
				YearLbl.setText(book.getPubYear());
				GenreLbl.setText(book.getGenre());
				PlotShowLbl.setText(book.getPlot());
				StatusLbl.setText(book.getStatus());
				// StartLbl.setText(book.getStartDate());
				// EndLbl.setText(book.getEndDate());
				// RatingLbl.setText(book.getRatings());
				RatingShowLbl.setText(book.getRatings());

				// CommentLbl.setText(book.getComment());
				CommentShowLbl.setText(book.getComment());
				sDate1 = book.getStartDate();
				// System.out.println(book.getStartDate());
				LocalDate datesss = LocalDate.parse(sDate1, DateTimeFormatter.ISO_DATE);
				models.setDate(datesss.getYear(), datesss.getMonthValue() - 1, datesss.getDayOfMonth());
				datePicker.getModel().setSelected(true);
				sDate2 = book.getEndDate();
				LocalDate dateEnd = LocalDate.parse(sDate2, DateTimeFormatter.ISO_DATE);
				modelsEnd.setDate(dateEnd.getYear(), dateEnd.getMonthValue() - 1, dateEnd.getDayOfMonth());
				datePickerEnd.getModel().setSelected(true);

			}
		});

		BookInfo.setBackground(Color.decode("#242853"));
		BookInfo.setBounds(890, 60, 375, 1000);
		// BookInfo.setLayout(null);
		BookInfo.setLayout(new BoxLayout(BookInfo, BoxLayout.Y_AXIS));
		contentPane.add(BookInfo);
	}

}

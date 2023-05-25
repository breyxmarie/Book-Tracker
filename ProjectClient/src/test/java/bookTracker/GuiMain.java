package bookTracker;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
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

@SuppressWarnings("serial")
public class GuiMain extends JFrame {

	private JPanel contentPane;

	private static String baseURI = "http://localhost:8080/ProjectWebsite/rest/books";

	static WebTarget getWebTarget() {
		ClientConfig config = new ClientConfig();
		Client client = ClientBuilder.newClient(config);

		return client.target(baseURI);
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GuiMain frame = new GuiMain();
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
	public GuiMain() {
		WebTarget target = getWebTarget();

		//////////////////////////////// get data for to be read books
		LinkedHashMap<String, LinkedHashMap<String, Object>> responseMap = target.path("toread").request()
				.accept(MediaType.APPLICATION_JSON)
				.get(new GenericType<LinkedHashMap<String, LinkedHashMap<String, Object>>>() {
				});
		// Create a new HashMap to hold the converted Book objects
		final HashMap<String, Book> bookMap = new HashMap<String, Book>();
		// Loop through the responseMap and convert each entry to a Book object
		for (Map.Entry<String, LinkedHashMap<String, Object>> entry : responseMap.entrySet()) {
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
			bookMap.put(key, book);
		}

		ArrayList<Book> current = new ArrayList<Book>();

		for (Map.Entry<String, Book> entry : bookMap.entrySet()) {
			String key = entry.getKey();
			Book book = entry.getValue();
			// System.out.println("Key: " + key + ", Book: " + book.getTitle());
			// Object[] row = { book.getTitle() };
			// models.addRow(row);
			current.add(book);
		}
		////////////////////////////////
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBounds(100, 100, 1280, 789);
		contentPane = new JPanel();
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0)));
		contentPane.setBackground(new Color(229, 233, 236));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(253, 231, 76));
		panel.setBounds(0, 0, 1264, 59);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel_3 = new JLabel("BookTracker\r\n");
		lblNewLabel_3.setBounds(83, 10, 196, 42);
		panel.add(lblNewLabel_3);
		lblNewLabel_3.setFont(new Font("SansSerif", Font.BOLD, 32));

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1
				.setIcon(new ImageIcon(new javax.swing.ImageIcon(GuiMain.class.getResource("/bookTracker/book.png"))
						.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
		lblNewLabel_1.setBounds(22, 0, 60, 42);
		panel.add(lblNewLabel_1);

		JLabel lblNewLabel_3_1 = new JLabel("Hi! USERNAME!");
		lblNewLabel_3_1
				.setIcon(new ImageIcon(new javax.swing.ImageIcon(GuiMain.class.getResource("/bookTracker/download.png"))
						.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
		lblNewLabel_3_1.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblNewLabel_3_1.setBounds(1017, 10, 206, 42);
		// panel.add(lblNewLabel_3_1);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(91, 124, 153));
		panel_1.setBounds(0, 59, 302, 691);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		final JButton btnNewButton = new JButton("Home");
		btnNewButton
				.setIcon(new ImageIcon(new javax.swing.ImageIcon(GuiMain.class.getResource("/bookTracker/home (1).png"))
						.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH)));
		btnNewButton.setBackground(Color.decode("#4F7AA3"));
		btnNewButton.setFont(new Font("SansSerif", Font.BOLD, 24));
		btnNewButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(0, 74, 320, 55);
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

		final JButton btnMyBooks = new JButton("My Books\r\n");
		btnMyBooks
				.setIcon(new ImageIcon(new javax.swing.ImageIcon(GuiMain.class.getResource("/bookTracker/book (1).png"))
						.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH)));
		btnMyBooks.setFont(new Font("SansSerif", Font.BOLD, 24));
		btnMyBooks.setBackground(Color.decode("#4F7AA3"));
		btnMyBooks.setBorder(null);
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

		final JButton btnAddBooks = new JButton("Add Book\r\n\r\n");
		btnAddBooks
				.setIcon(new ImageIcon(new javax.swing.ImageIcon(GuiMain.class.getResource("/bookTracker/book (2).png"))
						.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH)));
		btnAddBooks.setFont(new Font("SansSerif", Font.BOLD, 24));
		btnAddBooks.setBackground(Color.decode("#4F7AA3"));
		btnAddBooks.setBounds(0, 218, 320, 55);
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

		//////////////////////////////// nav bar button mouse click event

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

		JLabel lblNewLabel = new JLabel("BookTracker");
		lblNewLabel.setFont(new Font("SansSerif", Font.BOLD, 32));
		lblNewLabel.setBounds(106, 11, 205, 31);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1_1 = new JLabel("BOOKS FOR YOU\r\n");
		lblNewLabel_1_1.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1_1.setFont(new Font("SansSerif", Font.BOLD, 41));
		lblNewLabel_1_1.setBounds(352, 102, 368, 42);
		contentPane.add(lblNewLabel_1_1);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(217, 217, 217));
		panel_2.setBounds(352, 155, 161, 190);
		contentPane.add(panel_2);

		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setIcon(
				new ImageIcon(new javax.swing.ImageIcon(GuiMain.class.getResource("/bookTracker/default book.png"))
						.getImage().getScaledInstance(160, 180, Image.SCALE_SMOOTH)));
		panel_2.add(lblNewLabel_4);

		JPanel panel_2_1 = new JPanel();
		panel_2_1.setBackground(new Color(217, 217, 217));
		panel_2_1.setBounds(559, 155, 161, 190);
		contentPane.add(panel_2_1);

		JLabel lblNewLabel_4_1 = new JLabel("");
		lblNewLabel_4_1.setIcon(
				new ImageIcon(new javax.swing.ImageIcon(GuiMain.class.getResource("/bookTracker/default book.png"))
						.getImage().getScaledInstance(160, 180, Image.SCALE_SMOOTH)));
		panel_2_1.add(lblNewLabel_4_1);

		JPanel panel_2_2 = new JPanel();
		panel_2_2.setBackground(new Color(217, 217, 217));
		panel_2_2.setBounds(766, 155, 161, 190);
		// contentPane.add(panel_2_2);

		JLabel lblNewLabel_4_1_1 = new JLabel("");
		lblNewLabel_4_1_1.setIcon(
				new ImageIcon(new javax.swing.ImageIcon(GuiMain.class.getResource("/bookTracker/default book.png"))
						.getImage().getScaledInstance(160, 180, Image.SCALE_SMOOTH)));
		// panel_2_2.add(lblNewLabel_4_1_1);

		JPanel panel_2_3 = new JPanel();
		panel_2_3.setBackground(new Color(217, 217, 217));
		panel_2_3.setBounds(976, 155, 161, 190);
		// contentPane.add(panel_2_3);

		JLabel lblNewLabel_4_1_1_1 = new JLabel("");
		lblNewLabel_4_1_1_1.setIcon(
				new ImageIcon(new javax.swing.ImageIcon(GuiMain.class.getResource("/bookTracker/default book.png"))
						.getImage().getScaledInstance(160, 180, Image.SCALE_SMOOTH)));
		// panel_2_3.add(lblNewLabel_4_1_1_1);

		JLabel lblNewLabel_1_2 = new JLabel("Currently Reading\r\n\r\n");
		lblNewLabel_1_2.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel_1_2.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1_2.setFont(new Font("SansSerif", Font.BOLD, 32));
		lblNewLabel_1_2.setBounds(352, 425, 277, 42);
		// contentPane.add(lblNewLabel_1_2);

		JPanel panel_2_4 = new JPanel();
		panel_2_4.setBackground(new Color(217, 217, 217));
		panel_2_4.setBounds(362, 478, 161, 190);
		// contentPane.add(panel_2_4);

		JLabel lblNewLabel_4_1_1_1_1 = new JLabel("");
		lblNewLabel_4_1_1_1_1.setIcon(
				new ImageIcon(new javax.swing.ImageIcon(GuiMain.class.getResource("/bookTracker/default book.png"))
						.getImage().getScaledInstance(160, 180, Image.SCALE_SMOOTH)));
		// panel_2_4.add(lblNewLabel_4_1_1_1_1);

		JLabel lblNewLabel_2 = new JLabel(current.get(0).getTitle());
		lblNewLabel_2.setFont(new Font("SansSerif", Font.BOLD, 16));
		lblNewLabel_2.setBounds(400, 356, 250, 21);
		contentPane.add(lblNewLabel_2);

		JLabel lblNewLabel_2_1 = new JLabel(current.get(0).getAuthor());
		lblNewLabel_2_1.setFont(new Font("SansSerif", Font.BOLD, 16));
		lblNewLabel_2_1.setBounds(373, 388, 250, 21);
		contentPane.add(lblNewLabel_2_1);

		JLabel lblNewLabel_2_2 = new JLabel(current.get(1).getTitle());
		lblNewLabel_2_2.setFont(new Font("SansSerif", Font.BOLD, 16));
		lblNewLabel_2_2.setBounds(570, 356, 250, 21);
		contentPane.add(lblNewLabel_2_2);

		JLabel lblNewLabel_2_1_2 = new JLabel(current.get(1).getAuthor());
		lblNewLabel_2_1_2.setFont(new Font("SansSerif", Font.BOLD, 16));
		lblNewLabel_2_1_2.setBounds(607, 388, 250, 21);
		contentPane.add(lblNewLabel_2_1_2);

		/////////////////////////////////////////

		// To be Read Table

		// LinkedHashMap<String, LinkedHashMap<String, Object>> responseMap =
		// target.path("toread").request()
		// .accept(MediaType.APPLICATION_JSON)
		// .get(new GenericType<LinkedHashMap<String, LinkedHashMap<String, Object>>>()
		// {});
		// Create a new HashMap to hold the converted Book objects
		// final HashMap<String, Book> bookMap = new HashMap<String, Book>();
		// Loop through the responseMap and convert each entry to a Book object
		for (Map.Entry<String, LinkedHashMap<String, Object>> entry : responseMap.entrySet()) {
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
			bookMap.put(key, book);
		}
		System.out.println(bookMap.size());
		// System.out.println(bookMap.get("5").getTitle());
		// Loop through the bookMap and print the Book objects
		String[] columnNames = { "To Be Read" };
		// DefaultTableModel models = new DefaultTableModel(bookMap.size(),1);
		DefaultTableModel models = new DefaultTableModel(columnNames, 0);
		models.setColumnIdentifiers(columnNames);

		for (Map.Entry<String, Book> entry : bookMap.entrySet()) {
			String key = entry.getKey();
			Book book = entry.getValue();
			// System.out.println("Key: " + key + ", Book: " + book.getTitle());
			Object[] row = { book.getTitle() };
			models.addRow(row);
		}

		// DefaultTableModel modelss = new DefaultTableModel(columnNamess,0);

		// Create a JTable object and set its model
		final JTable tables = new JTable(models);

		// Add the JTable to a JScrollPane and add the scroll pane to a container
		JScrollPane scrollPanes = new JScrollPane(tables);
		scrollPanes.setBounds(852, 152, 343, 208);
		contentPane.add(scrollPanes);

		tables.setRowHeight(58);
		// tablesDones.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		tables.setBackground(new Color(192, 192, 192));
		tables.setBounds(352, 292, 243, 408);
		tables.setFont(new Font("Tahoma", Font.PLAIN, 15));

		tables.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = tables.rowAtPoint(e.getPoint());
				int col = tables.columnAtPoint(e.getPoint());
				Object value = tables.getValueAt(row, col);
				WebTarget targets = getWebTarget();
				System.out.println("Clicked cell value: " + value);
				String sampleData = value.toString();
				Book product = targets.path(sampleData).request().accept(MediaType.APPLICATION_JSON).get(Book.class);

				System.out.println(product.getAuthor());
				System.out.println(product.getPlot());

				GuiPopupBox PopUp = new GuiPopupBox(product, "main");
				PopUp.setVisible(true);
			}
		});

		//////////////////////////////////////////

		//////////////////////////////////////////////

		// Currently Reading Table
		LinkedHashMap<String, LinkedHashMap<String, Object>> responseMapCurrently = target.path("currently").request()
				.accept(MediaType.APPLICATION_JSON)
				.get(new GenericType<LinkedHashMap<String, LinkedHashMap<String, Object>>>() {
				});
		// Create a new HashMap to hold the converted Book objects
		HashMap<String, Book> bookMapCur = new HashMap<String, Book>();

		// Loop through the responseMap and convert each entry to a Book object
		for (Map.Entry<String, LinkedHashMap<String, Object>> entry : responseMapCurrently.entrySet()) {
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
			bookMapCur.put(key, book);
		}

		String[] columns = { "Currently Reading" };
		DefaultTableModel modelsCurs = new DefaultTableModel(columns, 0);
		for (Map.Entry<String, Book> entry : bookMapCur.entrySet()) {
			String key = entry.getKey();
			Book book = entry.getValue();
			// System.out.println("Key: " + key + ", Book: " + book.getTitle());
			Object[] row = { book.getTitle() };
			modelsCurs.addRow(row);
		}

		// DefaultTableModel modelss = new DefaultTableModel(columnNamess,0);

		// Create a JTable object and set its model
		final JTable tablesCurs = new JTable(modelsCurs);

		// Add the JTable to a JScrollPane and add the scroll pane to a container
		JScrollPane scrollPanesCur = new JScrollPane(tablesCurs);
		scrollPanesCur.setBounds(363, 462, 343, 208);
		contentPane.add(scrollPanesCur);

		tablesCurs.setRowHeight(58);
		// tablesDones.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		tablesCurs.setBackground(new Color(192, 192, 192));
		tablesCurs.setBounds(352, 292, 243, 408);
		tablesCurs.setFont(new Font("Tahoma", Font.PLAIN, 15));

		tablesCurs.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = tablesCurs.rowAtPoint(e.getPoint());
				int col = tablesCurs.columnAtPoint(e.getPoint());
				Object value = tablesCurs.getValueAt(row, col);
				WebTarget targets = getWebTarget();
				System.out.println("Clicked cell value: " + value);
				String sampleData = value.toString();
				Book product = targets.path(sampleData).request().accept(MediaType.APPLICATION_JSON).get(Book.class);

				System.out.println(product.getAuthor());
				System.out.println(product.getPlot());

				GuiPopupBox PopUp = new GuiPopupBox(product, "main");
				PopUp.setVisible(true);
			}
		});
		///////////////////////////////////////////////////////////////////////////
		//////////////////////////////////////////////

		// Done Reading Table

		LinkedHashMap<String, LinkedHashMap<String, Object>> responseMapDone = target.path("done").request()
				.accept(MediaType.APPLICATION_JSON)
				.get(new GenericType<LinkedHashMap<String, LinkedHashMap<String, Object>>>() {
				});
		// Create a new HashMap to hold the converted Book objects
		HashMap<String, Book> bookMapDone = new HashMap<String, Book>();

		// Loop through the responseMap and convert each entry to a Book object
		for (Map.Entry<String, LinkedHashMap<String, Object>> entry : responseMapDone.entrySet()) {
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
			bookMapDone.put(key, book);
		}

		String[] columnsDone = { "Done Reading" };
		DefaultTableModel modelsDones = new DefaultTableModel(columnsDone, 0);
		for (Map.Entry<String, Book> entry : bookMapDone.entrySet()) {
			String key = entry.getKey();
			Book book = entry.getValue();
			// System.out.println("Key: " + key + ", Book: " + book.getTitle());
			Object[] row = { book.getTitle() };
			modelsDones.addRow(row);
		}

		// DefaultTableModel modelss = new DefaultTableModel(columnNamess,0);

		// Create a JTable object and set its model
		final JTable tablesDones = new JTable(modelsDones);

		// Add the JTable to a JScrollPane and add the scroll pane to a container
		JScrollPane scrollPanesDone = new JScrollPane(tablesDones);
		scrollPanesDone.setBounds(813, 460, 343, 208);
		contentPane.add(scrollPanesDone);

		scrollPanesDone.setBackground(new Color(192, 192, 192));
		scrollPanesDone.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		tablesDones.setRowHeight(58);
		// tablesDones.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		tablesDones.setBackground(new Color(192, 192, 192));
		tablesDones.setBounds(352, 292, 243, 408);
		tablesDones.setFont(new Font("Tahoma", Font.PLAIN, 15));

		tablesDones.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = tablesDones.rowAtPoint(e.getPoint());
				int col = tablesDones.columnAtPoint(e.getPoint());
				Object value = tablesDones.getValueAt(row, col);
				WebTarget targets = getWebTarget();
				System.out.println("Clicked cell value: " + value);
				String sampleData = value.toString();
				Book product = targets.path(sampleData).request().accept(MediaType.APPLICATION_JSON).get(Book.class);

				System.out.println(product.getAuthor());
				System.out.println(product.getPlot());

				GuiPopupBox PopUp = new GuiPopupBox(product, "");
				PopUp.setVisible(true);
			}
		});

		///////////////////////////////////////////////////

		JLabel lblNewLabel_2_3 = new JLabel(current.get(2).getTitle());
		lblNewLabel_2_3.setFont(new Font("SansSerif", Font.BOLD, 16));
		lblNewLabel_2_3.setBounds(820, 356, 63, 21);
		// contentPane.add(lblNewLabel_2_3);

		JLabel lblNewLabel_2_4 = new JLabel(current.get(3).getTitle());
		lblNewLabel_2_4.setFont(new Font("SansSerif", Font.BOLD, 16));
		lblNewLabel_2_4.setBounds(1028, 356, 63, 21);
		// contentPane.add(lblNewLabel_2_4);

		JLabel lblNewLabel_2_1_1 = new JLabel(current.get(0).getAuthor());
		lblNewLabel_2_1_1.setFont(new Font("SansSerif", Font.BOLD, 16));
		lblNewLabel_2_1_1.setBounds(599, 388, 86, 21);
		// contentPane.add(lblNewLabel_2_1_1);

		JLabel lblNewLabel_2_1_3 = new JLabel(current.get(2).getAuthor());
		lblNewLabel_2_1_3.setFont(new Font("SansSerif", Font.BOLD, 16));
		lblNewLabel_2_1_3.setBounds(1015, 388, 86, 21);
		// contentPane.add(lblNewLabel_2_1_3);

		JLabel lblNewLabel_2_5 = new JLabel("[TITLE]");
		lblNewLabel_2_5.setFont(new Font("SansSerif", Font.BOLD, 16));
		lblNewLabel_2_5.setBounds(416, 679, 63, 21);
		// contentPane.add(lblNewLabel_2_5);

		JLabel lblNewLabel_2_1_4 = new JLabel("[AUTHOR]");
		lblNewLabel_2_1_4.setFont(new Font("SansSerif", Font.BOLD, 16));
		lblNewLabel_2_1_4.setBounds(405, 715, 86, 21);
		// contentPane.add(lblNewLabel_2_1_4);

		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_3.setBounds(780, 478, 147, 100);
		// contentPane.add(panel_3);
		panel_3.setLayout(null);

		JLabel lblNewLabel_5 = new JLabel("Total Books");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_5.setBounds(38, 66, 75, 24);
		panel_3.add(lblNewLabel_5);

		JLabel lblNewLabel_6 = new JLabel("123");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblNewLabel_6.setBounds(53, 26, 60, 40);
		panel_3.add(lblNewLabel_6);

		JPanel panel_3_2 = new JPanel();
		panel_3_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_3_2.setLayout(null);
		panel_3_2.setBounds(780, 600, 147, 100);
		// contentPane.add(panel_3_2);

		JLabel lblNewLabel_5_1 = new JLabel("Currently Reading");
		lblNewLabel_5_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_5_1.setBounds(20, 66, 117, 24);
		panel_3_2.add(lblNewLabel_5_1);

		JLabel lblNewLabel_6_1 = new JLabel("1");
		lblNewLabel_6_1.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblNewLabel_6_1.setBounds(65, 25, 44, 40);
		panel_3_2.add(lblNewLabel_6_1);

		JPanel panel_3_1 = new JPanel();
		panel_3_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_3_1.setLayout(null);
		panel_3_1.setBounds(954, 478, 147, 100);
		// contentPane.add(panel_3_1);

		JLabel lblNewLabel_5_2 = new JLabel("To Be Read");
		lblNewLabel_5_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_5_2.setBounds(38, 66, 75, 24);
		panel_3_1.add(lblNewLabel_5_2);

		JLabel lblNewLabel_6_2 = new JLabel("64");
		lblNewLabel_6_2.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblNewLabel_6_2.setBounds(59, 26, 60, 40);
		panel_3_1.add(lblNewLabel_6_2);

		JPanel panel_3_3 = new JPanel();
		panel_3_3.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_3_3.setLayout(null);
		panel_3_3.setBounds(954, 600, 147, 100);
		// contentPane.add(panel_3_3);

		JLabel lblNewLabel_5_3 = new JLabel("Done Reading");
		lblNewLabel_5_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_5_3.setBounds(33, 66, 88, 24);
		panel_3_3.add(lblNewLabel_5_3);

		JLabel lblNewLabel_6_3 = new JLabel("50");
		lblNewLabel_6_3.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblNewLabel_6_3.setBounds(61, 26, 44, 40);
		panel_3_3.add(lblNewLabel_6_3);
	}
}

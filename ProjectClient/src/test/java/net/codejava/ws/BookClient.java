package net.codejava.ws;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientConfig;

public class BookClient {
	 private static String baseURI = "http://localhost:8080/ProjectWebsite/rest/books";
	 
	    static WebTarget getWebTarget() {
	        ClientConfig config = new ClientConfig();
	        Client client = ClientBuilder.newClient(config);
	         
	        return client.target(baseURI);     
	    }
	     
	    public static void main(String[] args) {
	        // call a test method: testList(), testAdd(), testGet()...
	    	
	    	ClientConfig config = new ClientConfig();
			Client client = ClientBuilder.newClient(config);
			WebTarget target = client.target(baseURI);
			
		//	String response = target.request()
			//				 .accept(MediaType.APPLICATION_JSON)
			//				 .get(String.class);
			 System.out.println("Hello");
			 testListssss();
			// testGet();
			// testAdd();
			// testUpdate();
			// testDelete();
			// testList();
	    }
	    
/*	    static void testList() {
		    WebTarget target = getWebTarget();
		     
		    String response = target.request().accept(MediaType.APPLICATION_JSON).get(String.class);
		     
		    System.out.println(response);      
		    
		   // response.
		    
		  //  List<Book> datas = target.request().accept(MediaType.APPLICATION_JSON).get(List.class);
		    Map<String, Book> datas = (Map<String, Book>) target.request().accept(MediaType.APPLICATION_JSON).get();
		   // System.out.println(datas.get(4).getTitle());   
		    
		  // Book sample = datas.get("1");
		   // System.out.println(datas.get("1"));   
		    
		    System.out.println(response.split("}"));
		   
		    for (Map.Entry<String, Book> entry : datas.entrySet())
		    {
		        //What you need to do with your map
		    //	System.out.println(entry.getValue());
		    	Book trys =  entry.getValue();
		    	System.out.println(trys);
		    	
		    }
		    
		   
	    } */
	    
	    
	    static void testListssss() {
		  /*  WebTarget target = getWebTarget();
		     
		    Response response = target.path("toread")
		            .request()
		            .accept(MediaType.APPLICATION_JSON)
		            .get();
		        Map<String, Book> books = response.readEntity(new GenericType<Map<String, Book>>(){});
		        System.out.println("Hello");
		        System.out.println(books); */
		        
		        
		        
		        
		        
		        
		        WebTarget target = getWebTarget();
		         
		        // Get the response as a LinkedHashMap
		        LinkedHashMap<String, LinkedHashMap<String, Object>> responseMap = target.path("currently").request()
		                .accept(MediaType.APPLICATION_JSON)
		                .get(new GenericType<LinkedHashMap<String, LinkedHashMap<String, Object>>>() {});
		        
		        // Create a new HashMap to hold the converted Book objects
		        HashMap<String, Book> bookMap = new HashMap<String, Book>();
		        
		        // Loop through the responseMap and convert each entry to a Book object
		        for (Map.Entry<String, LinkedHashMap<String, Object>> entry : responseMap.entrySet()) {
		            String key = entry.getKey();
		            LinkedHashMap<String, Object> valueMap = entry.getValue();
		            
		            // Extract the Book data from the valueMap
		            String title = (String) valueMap.get("title");
		            String author = (String) valueMap.get("author");
		            String isbn = (String) valueMap.get("isbn");
		            
		            // Create a new Book object
		            Book book = new Book();
		            book.setId(Integer.parseInt(key));
		            book.setTitle(title);
		            book.setAuthor(author);
		          //  book.setIsbn(isbn);
		            
		            // Add the Book object to the bookMap
		            bookMap.put(key, book);
		        }
		        
		        // Loop through the bookMap and print the Book objects
		        for (Map.Entry<String, Book> entry : bookMap.entrySet()) {
		            String key = entry.getKey();
		            Book book = entry.getValue();
		            System.out.println("Key: " + key + ", Book: " + book.getTitle());
		        }
	    }
		   
	    
	    static void testList() {
	        WebTarget target = getWebTarget();
	         
	        // Get the response as a LinkedHashMap
	        LinkedHashMap<String, LinkedHashMap<String, Object>> responseMap = target.request()
	                .accept(MediaType.APPLICATION_JSON)
	                .get(new GenericType<LinkedHashMap<String, LinkedHashMap<String, Object>>>() {});
	        
	        // Create a new HashMap to hold the converted Book objects
	        HashMap<String, Book> bookMap = new HashMap<String, Book>();
	        
	        // Loop through the responseMap and convert each entry to a Book object
	        for (Map.Entry<String, LinkedHashMap<String, Object>> entry : responseMap.entrySet()) {
	            String key = entry.getKey();
	            LinkedHashMap<String, Object> valueMap = entry.getValue();
	            
	            // Extract the Book data from the valueMap
	            String title = (String) valueMap.get("title");
	            String author = (String) valueMap.get("author");
	            String isbn = (String) valueMap.get("isbn");
	            
	            // Create a new Book object
	            Book book = new Book();
	            book.setId(Integer.parseInt(key));
	            book.setTitle(title);
	            book.setAuthor(author);
	          //  book.setIsbn(isbn);
	            
	            // Add the Book object to the bookMap
	            bookMap.put(key, book);
	        }
	        
	        // Loop through the bookMap and print the Book objects
	        for (Map.Entry<String, Book> entry : bookMap.entrySet()) {
	            String key = entry.getKey();
	            Book book = entry.getValue();
	            System.out.println("Key: " + key + ", Book: " + book.getTitle());
	        }
	    }
	    
	    static void testGet() {
	        WebTarget target = getWebTarget();
	        String productId = "6";
	        //int productId = 1;
	        Book product = target.path(productId)
	                    .request().accept(MediaType.APPLICATION_JSON)
	                    .get(Book.class);
	         
	       System.out.println(product);       
	        
	     //   System.out.println(product.getAuthor());    
	    }
	    
	    static void testAdd() {
		    WebTarget target = getWebTarget();
		    Book book = new Book("test","test","test","test","test","test");
		    Response response = target.request()
		            .post(Entity.entity(book, MediaType.APPLICATION_JSON), Response.class);
		     
		  //  System.out.println(response.getLocation().toString());
		}
	    
	    private static void testUpdate() {
		    WebTarget target = getWebTarget();
		    //Book product = new Product("ZenFoneX", 100f);
		    Book product = new Book("test", "test","test","test","test","test" );
		    String productId = "7";
		    Response response = target.path(productId).request()
		            .put(Entity.entity(product, MediaType.APPLICATION_JSON), Response.class);
		    System.out.println(response);      
		}
	    
		private static void testDelete() {
		    WebTarget target = getWebTarget();
		    String productId = "9";
		    Response response = target.path(productId).request()
		            .delete(Response.class);
		    System.out.println(response);      
		}
}

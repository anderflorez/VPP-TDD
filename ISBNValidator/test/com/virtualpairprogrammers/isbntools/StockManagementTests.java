package com.virtualpairprogrammers.isbntools;
import static org.junit.Assert.*;
import org.junit.Test;

public class StockManagementTests {

	@Test
	public void testCanGetACorrectLocatorCode() {

		ExternalISBNDataService setWebService = new ExternalISBNDataService() {

			@Override
			public Book lookup(String isbn) {
				return new Book(isbn, "Of Mice and Man", "J. Stainbeck");
			}
		};
		
		ExternalISBNDataService setDatabaseService = new ExternalISBNDataService() {

			@Override
			public Book lookup(String isbn) {
				return null;
			}
			
		};

		String isbn = "0140177396";
		StockManager stockManager = new StockManager(); 
		stockManager.setWebService(setWebService);
		stockManager.setDatabaseService(setDatabaseService);
		String locatorCode = stockManager.getLocatorCode(isbn);
		assertEquals("7396J4", locatorCode);
	}
	
	@Test
	public void databaseIsUsedIfDataIsPresent() {
		fail();
	}
	
	@Test
	public void webserviceIsUsedIfDataIsNotPresentInDatabase() {
		fail();
	}

}

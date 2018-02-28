package com.virtualpairprogrammers.isbntools;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;

public class StockManagementTests {
	
//	// This test uses Stubs
//	@Test
//	public void testCanGetACorrectLocatorCode() {
//		ExternalISBNDataService testWebService = new ExternalISBNDataService() {
//
//			@Override
//			public Book lookup(String isbn) {
//				return new Book(isbn, "Of Mice and Man", "J. Stainbeck");
//			}
//		};
//		
//		ExternalISBNDataService testDatabaseService = new ExternalISBNDataService() {
//
//			@Override
//			public Book lookup(String isbn) {
//				return null;
//			}
//		};
//
//		StockManager stockManager = new StockManager(); 
//		stockManager.setWebService(testWebService);
//		stockManager.setDatabaseService(testDatabaseService);
//
//		String isbn = "0140177396";
//		String locatorCode = stockManager.getLocatorCode(isbn);
//		assertEquals("7396J4", locatorCode);
//	}
	
	ExternalISBNDataService testWebService;
	ExternalISBNDataService testDatabaseService;
	StockManager stockManager;
	
	@Before
	public void setup() {
		testWebService = mock(ExternalISBNDataService.class);
		testDatabaseService = mock(ExternalISBNDataService.class);
		stockManager = new StockManager();
		stockManager.setWebService(testWebService);
		stockManager.setDatabaseService(testDatabaseService);
	}
	
	// This test uses Mock (mockito)
	@Test
	public void testCanGetACorrectLocatorCode() {

		
		String isbn = "0140177396";		
		when(testWebService.lookup(isbn)).thenReturn(new Book("0140177396", "Of Mice and Man", "J. Stainbeck"));
		when(testDatabaseService.lookup(isbn)).thenReturn(null);

		String locatorCode = stockManager.getLocatorCode(isbn);
		assertEquals("7396J4", locatorCode);
	}
	
	@Test
	public void databaseIsUsedIfDataIsPresent() {
		// This method uses Mocks (Mockito)
		
		when(testDatabaseService.lookup("0140177396")).thenReturn(new Book("0140177396", "abc", "abc"));
		
		String isbn = "0140177396";
		String locatorCode = stockManager.getLocatorCode(isbn);
		
		verify(testDatabaseService, times(1)).lookup("0140177396");
		verify(testWebService, never()).lookup(anyString());
	}
	
	@Test
	public void webserviceIsUsedIfDataIsNotPresentInDatabase() {
		// This method uses Mocks (Mockito)
		
		when(testDatabaseService.lookup("0140177396")).thenReturn(null);
		when(testWebService.lookup("0140177396")).thenReturn(new Book("0140177396", "abc", "abc"));

		String isbn = "0140177396";
		String locatorCode = stockManager.getLocatorCode(isbn);
		
		verify(testDatabaseService).lookup("0140177396");
		verify(testWebService).lookup("0140177396");
	}

}

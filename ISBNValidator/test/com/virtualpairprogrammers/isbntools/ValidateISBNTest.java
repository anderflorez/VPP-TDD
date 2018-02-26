package com.virtualpairprogrammers.isbntools;

import static org.junit.Assert.*;
import org.junit.Test;

import com.virtualpairprogrammers.IsbnTools.ValidateISBN;

public class ValidateISBNTest {

	@Test
	public void checkAValid10DigitISBN() {
		ValidateISBN validator = new ValidateISBN();
		
		boolean result = validator.checkISBN("0140449116");
		assertTrue("First value", result);
		
		result = validator.checkISBN("0140177396");
		assertTrue("Second value", result);
	}
	
	@Test
	public void checkAValid13DigitISBN() {
		ValidateISBN validator = new ValidateISBN();
		
		boolean result = validator.checkISBN("9781853260087");
		assertTrue("13 Digit ISBN value", result);
		
		result = validator.checkISBN("9781853267338");
		assertTrue("13 Digit ISBN Second value", result);
	}
	
	@Test
	public void tenDigitISBNNumbersEndingInAnXAreValid() {
		ValidateISBN validator = new ValidateISBN();
		boolean result = validator.checkISBN("012000030X");
		assertTrue(result);
	}
	
	@Test
	public void checkAnInvalid10DigitISBN() {
		ValidateISBN validator = new ValidateISBN();
		boolean result = validator.checkISBN("0140449117");
		assertFalse(result);
	}
	
	@Test
	public void checkAnInvalid13DigitISBN() {
		ValidateISBN validator = new ValidateISBN();
		boolean result = validator.checkISBN("9781853267336");
		assertFalse(result);
	}
	
	@Test(expected = NumberFormatException.class)
	public void nineDigitISBNsAreNotAllowed() {
		ValidateISBN validator = new ValidateISBN();
		validator.checkISBN("123456789");
	}
	
	@Test(expected = NumberFormatException.class)
	public void nonNumericISBNsAreNotAllowed() {
		ValidateISBN validator = new ValidateISBN();
		validator.checkISBN("helloworld");
	}

}

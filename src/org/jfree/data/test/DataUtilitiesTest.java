package org.jfree.data.test;

import org.jfree.data.DataUtilities;
import org.jfree.data.DefaultKeyedValues;
import org.jfree.data.DefaultKeyedValues2D;
import org.jfree.data.KeyedValues;
import org.junit.Test;

import junit.framework.TestCase;

public class DataUtilitiesTest extends TestCase {
	
	@Test
	public void testCalculateColumnTotalThrowsIllegalArgumentExceptionForNullParameter() {
		DefaultKeyedValues2D defaultKeyedValues2D = null;
		try {
			DataUtilities.calculateColumnTotal(defaultKeyedValues2D, 0);
			fail("Exception should have been thrown");
		} catch (IllegalArgumentException e) {
			return;
		}
	}
	
	@Test
	public void testCalculateColumnTotalReturnsZeroForInvalidData() {
		DefaultKeyedValues2D defaultKeyedValues2D = new DefaultKeyedValues2D();
		assertEquals("Invalid column so the default total of zero should be returned", 0.0, 
				DataUtilities.calculateColumnTotal(defaultKeyedValues2D, -1));
	}
		
	@Test
	public void testCalculateColumnTotalReturnsZeroForEmptyData() {
		DefaultKeyedValues2D defaultKeyedValues2D = new DefaultKeyedValues2D();
		assertEquals("No data exists so the default total of zero should be returned", 0.0, 
				DataUtilities.calculateColumnTotal(defaultKeyedValues2D, 0));
	}
	
	@Test
	public void testCalculateColumnTotalReturnsCorrectValueForValidColumn() {
		DefaultKeyedValues2D defaultKeyedValues2D = new DefaultKeyedValues2D();
		defaultKeyedValues2D.addValue(10, 0, 0);
		defaultKeyedValues2D.addValue(5, 1, 0);
		defaultKeyedValues2D.addValue(20, 2, 0);
		assertEquals("The column has the values 10, 5 and 20 so the total should be 35", 35.0, 
				DataUtilities.calculateColumnTotal(defaultKeyedValues2D, 0));
	}
	
	@Test
	public void testCalculateRowTotalThrowsIllegalArgumentExceptionForNullParameter() {
		DefaultKeyedValues2D defaultKeyedValues2D = null;
		try {
			DataUtilities.calculateRowTotal(defaultKeyedValues2D, 0);
			fail("Exception should have been thrown");
		} catch (IllegalArgumentException e) {
			return;
		}
	}
	
	@Test
	public void testCalculateRowTotalReturnsZeroForInvalidData() {
		DefaultKeyedValues2D defaultKeyedValues2D = new DefaultKeyedValues2D();
		assertEquals("No data exists so the default total of zero should be returned", 0.0, 
				DataUtilities.calculateRowTotal(defaultKeyedValues2D, -1));
	}
	
	@Test
	public void testCalculateRowTotalReturnsZeroForEmptyData() {
		DefaultKeyedValues2D defaultKeyedValues2D = new DefaultKeyedValues2D();
		assertEquals("No data exists so the default total of zero should be returned", 0.0, 
				DataUtilities.calculateRowTotal(defaultKeyedValues2D, 0));
	}
	
	@Test
	public void testCalculateRowTotalReturnsCorrectValueForValidRow() {
		DefaultKeyedValues2D defaultKeyedValues2D = new DefaultKeyedValues2D();
		defaultKeyedValues2D.addValue(15, 0, 0);
		defaultKeyedValues2D.addValue(3, 0, 1);
		defaultKeyedValues2D.addValue(7, 0, 2);
		assertEquals("The row has the values 15, 3 and 7 so the total should be 25", 25.0, 
				DataUtilities.calculateRowTotal(defaultKeyedValues2D, 0));
	}	
	
	@Test
	public void testCreateNumberArrayThrowsIllegalArgumentExceptionForNullParameter() {
			double[] doubleArray = null;
			try {
				DataUtilities.createNumberArray(doubleArray);
				fail("Exception should have been thrown");
			} catch (IllegalArgumentException e) {
				return;
			}
	}
	
	@Test
	public void testCreateNumberArrayCreatesEmptyNumberArrayFromEmptyDoubleArray() {
			double[] doubleArray = new double[0];
			Number[] numberArray = DataUtilities.createNumberArray(doubleArray);
			assertEquals("numberArray should be of size 0", 0, numberArray.length);
	}
	
	@Test
	public void testCreateNumberArrayCreatesPopulatedNumberArrayFromValidDoubleArray() {
			double[] doubleArray = {25.0, 0.2, 19.99, 20};
			Number[] numberArray = DataUtilities.createNumberArray(doubleArray);
			assertEquals("numberArray should be of size 4", 4, numberArray.length);
			assertEquals(25.0, numberArray[0]);
			assertEquals(0.2, numberArray[1]);
			assertEquals(19.99, numberArray[2]);
			assertEquals(20.00, numberArray[3]);
	}
	
	@Test
	public void testCreateNumberArray2DThrowsIllegalArgumentExceptionForNullParameter() {
			double[][] twoDimensionalDoubleArray = null;
			try {
				DataUtilities.createNumberArray2D(twoDimensionalDoubleArray);
				fail("Exception should have been thrown");
			} catch (IllegalArgumentException e) {
				return;
			}
	}
	
	@Test
	public void testCreateNumberArray2DCreatesEmpty2dNumberArrayFromEmpty2dDoubleArray() {
			double[][] twoDimensionalDoubleArray = new double[0][0];
			Number[][] numberArray = DataUtilities.createNumberArray2D(twoDimensionalDoubleArray);
			assertEquals("numberArray should be of size 0", 0, numberArray.length);
	}
	
	@Test
	public void testCreateNumberArray2DCreatesPopulated2dNumberArrayFromValid2dDoubleArray() {
			double[][] twoDimensionalDoubleArray = {{0.5, 11.0}, {25.0, 0.2}};
			Number[][] numberArray = DataUtilities.createNumberArray2D(twoDimensionalDoubleArray);
			assertEquals("numberArray should have 2 rows", 2, numberArray.length);
			assertEquals(0.5, numberArray[0][0]);
			assertEquals(11.0, numberArray[0][1]);
			assertEquals(25.0, numberArray[1][0]);
			assertEquals(0.2, numberArray[1][1]);
	}
	
	@Test
	public void testGetCumulativePercentagesThrowsIllegalArgumentExceptionForNullParameter() {
		KeyedValues keyedValues = null;
		try {
			DataUtilities.getCumulativePercentages(keyedValues);
			fail("Exception should have been thrown");
		} catch (IllegalArgumentException e) {
			return;
		}
	}
	
	@Test
	public void testGetCumulativePercentagesReturnsEmptyKeyedValuesFromEmptyKeyedValuesParameter() {
		KeyedValues keyedValues = new DefaultKeyedValues();
		KeyedValues cumulativePercentagesKeyedValues = DataUtilities.getCumulativePercentages(keyedValues);
		assertEquals("returned KeyedValues instance should be of size 0", 0, cumulativePercentagesKeyedValues.getItemCount());
	}
	
	@Test
	public void testGetCumulativePercentagesReturnsCorrectKeyedValuesFromValidKeyedValuesParameter() {
		DefaultKeyedValues keyedValues = new DefaultKeyedValues();
		keyedValues.addValue((Comparable<?>) 0, 5);
		keyedValues.addValue((Comparable<?>) 1, 9);
		keyedValues.addValue((Comparable<?>) 2, 2);
		KeyedValues cumulativePercentagesKeyedValues = DataUtilities.getCumulativePercentages(keyedValues);
		assertEquals("returned KeyedValues instance should be of size 3", 3, cumulativePercentagesKeyedValues.getItemCount());
		assertEquals(0.3125, cumulativePercentagesKeyedValues.getValue(0));
		assertEquals(0.875, cumulativePercentagesKeyedValues.getValue(1));
		assertEquals(1.0, cumulativePercentagesKeyedValues.getValue(2));
	}
	
}

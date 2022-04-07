package org.jfree.data.test;

import org.jfree.data.Range;
import org.junit.Test;

import junit.framework.TestCase;

public class RangeTest extends TestCase {
	
	@Test
	public void testCombineReturnsNullRangeInstanceForTwoNullParameters() {
		Range rangeObjectUnderTest = null;
		Range otherRangeObjectUnderTest = null;
		Range combinedRange = Range.combine(rangeObjectUnderTest, otherRangeObjectUnderTest);
		assertNull(combinedRange);
	}
	
	@Test
	public void testCombineReturnsNonNullRangeInstanceForOneNullParameter() {
		Range rangeObjectUnderTest = new Range(0.0, 2.0);
		Range otherRangeObjectUnderTest = null;
		Range combinedRange = Range.combine(rangeObjectUnderTest, otherRangeObjectUnderTest);
		assertEquals("The non-null Range should be returned", new Range(0.0, 2.0), combinedRange);
	}
	
	@Test
	public void testCombineReturnsCorrectRangeInstanceForValidParameters() {
		Range rangeObjectUnderTest = new Range(3.0, 7.0);
		Range otherRangeObjectUnderTest = new Range(2.0, 6.0);
		Range combinedRange = Range.combine(rangeObjectUnderTest, otherRangeObjectUnderTest);
		assertEquals("The combined range should take the lower bound from parameter 2 and the"
				+ "upper bound from parameter 1", new Range(2.0, 7.0), combinedRange);
	}
	
	@Test
	public void testConstrainReturnsLowerBoundWhenParameterIsLessThanLowerBound() {
		Range rangeObjectUnderTest = new Range(5.0, 20.0);
		assertEquals("value is lower than lower bound so lower bound should be returned", 
				5.0, rangeObjectUnderTest.constrain(3.0));
	}
	
	@Test
	public void testConstrainReturnsUpperBoundWhenParameterIsMoreThanUpperBound() {
		Range rangeObjectUnderTest = new Range(5.0, 20.0);
		assertEquals("value is higher than upper bound so upper bound should be returned", 
				20.0, rangeObjectUnderTest.constrain(30.0));
	}
	
	@Test
	public void testConstrainReturnsParameterWhenParameterIsInRange() {
		Range rangeObjectUnderTest = new Range(-10.0, 10.0);
		assertEquals("value is within range so should be returned", 
				5.0, rangeObjectUnderTest.constrain(5.0));
	}
	
	@Test
	public void testContainsReturnsFalseForParameterLessThanLowerBound() {
		Range rangeObjectUnderTest = new Range(5.0, 20.0);
		assertFalse("2.0 is less than the lower bound of 5.0 so method should return false",
				rangeObjectUnderTest.contains(2.0));
	}
	
	@Test
	public void testContainsReturnsFalseForParameterGreaterThanUpperBound() {
		Range rangeObjectUnderTest = new Range(5.0, 20.0);
		assertFalse("30.0 is more than the upper bound of 20.0 so method should return false",
				rangeObjectUnderTest.contains(30.0));
	}
	
	@Test
	public void testContainsReturnsTrueForLowerBound() {
		Range rangeObjectUnderTest = new Range(5.0, 20.0);
		assertTrue("Lower bound is within the range so method should return true",
				rangeObjectUnderTest.contains(5.0));
	}
	
	@Test
	public void testContainsReturnsTrueForUpperBound() {
		Range rangeObjectUnderTest = new Range(5.0, 20.0);
		assertTrue("Upper bound is within the range so method should return true",
				rangeObjectUnderTest.contains(20.0));
	}
	
	@Test
	public void testContainsReturnsTrueForValueComfortablyInsideRange() {
		Range rangeObjectUnderTest = new Range(5.0, 20.0);
		assertTrue("10.0 is well within the range so method should return true",
				rangeObjectUnderTest.contains(10.0));
	}
	
	@Test
	public void testExpandToIncludeCreatesOneLengthRangeWhenRangeParameterIsNull() {
		Range rangeObjectUnderTest = null;
		Range expandToIncludeResult = Range.expandToInclude(rangeObjectUnderTest, 25.0);
		assertEquals("Range parameter is null so a one value range should be returned",
				new Range(25.0, 25.0), expandToIncludeResult);
	}
	
	@Test
	public void testExpandToIncludeDoesNotChangeRangeForLowerBoundaryValue() {
		Range rangeObjectUnderTest = new Range(5.0, 20.0);
		Range expandToIncludeResult = Range.expandToInclude(rangeObjectUnderTest, 5.0);
		assertEquals("value is lower boundary but within the range so no change to range should occur", 
				new Range(5.0, 20.0), expandToIncludeResult);
	}
	
	@Test
	public void testExpandToIncludeDoesNotChangeRangeForUpperBoundaryValue() {
		Range rangeObjectUnderTest = new Range(5.0, 20.0);
		Range expandToIncludeResult = Range.expandToInclude(rangeObjectUnderTest, 20.0);
		assertEquals("value is upper boundary but within the range so no change to range should occur",
				new Range(5.0, 20.0), expandToIncludeResult);
	}
	
	@Test
	public void testExpandToIncludeDoesNotChangeRangeForValueComfortablyInsideRange() {
		Range rangeObjectUnderTest = new Range(5.0, 20.0);
		Range expandToIncludeResult = Range.expandToInclude(rangeObjectUnderTest, 10.0);
		assertEquals("value is within the range so no change to range should occur",
				new Range(5.0, 20.0), expandToIncludeResult);
	}
	
	@Test
	public void testExpandToIncludeCorrectlyExtendsRangeForValueJustOutsideLowerBound() {
		Range rangeObjectUnderTest = new Range(5.0, 20.0);
		Range expandToIncludeResult = Range.expandToInclude(rangeObjectUnderTest, 4.0);
		assertEquals("value is below the range so lower boundary should change",
				new Range(4.0, 20.0), expandToIncludeResult);
	}
	
	@Test
	public void testExpandToIncludeCorrectlyExtendsRangeForValueJustOutsideUpperBound() {
		Range rangeObjectUnderTest = new Range(5.0, 20.0);
		Range expandToIncludeResult = Range.expandToInclude(rangeObjectUnderTest, 21.0);
		assertEquals("value is above the range so upper boundary should change",
				new Range(5.0, 21.0), expandToIncludeResult);
	}
	
	@Test
	public void testShiftThrowsIllegalArgumentExceptionForNullRangeParameter() {
		Range rangeObjectUnderTest = null;
		try {
			Range.shift(rangeObjectUnderTest, 2.0);
			fail("Exception should have been thrown");
		} catch (IllegalArgumentException e) {
			return;
		}
	}
	
	@Test
	public void testShiftCorrectlyDoesNotAlterRangeForDeltaOfZero() {
		Range rangeObjectUnderTest = new Range(5.0, 10.0);
		Range shiftResult = Range.shift(rangeObjectUnderTest, 0.0);
		assertEquals("A shift of 0 means the range should be the same",
				new Range(5.0, 10.0), shiftResult);
	}
	
	@Test
	public void testShiftCorrectlyDoesNotZeroCrossWhenPartOfRangeGoesNegativeToPositive() {
		Range rangeObjectUnderTest = new Range(-2.0, 3.0);
		Range shiftResult = Range.shift(rangeObjectUnderTest, 7.0);
		assertEquals("the lower boundary cannot cross 0 into the positives so should be set to 0",
				new Range(0.0, 10.0), shiftResult);
	}
	
	@Test
	public void testShiftCorrectlyDoesNotZeroCrossWhenPartOfRangeGoesPositiveToNegative() {
		Range rangeObjectUnderTest = new Range(5.0, 15.0);
		Range shiftResult = Range.shift(rangeObjectUnderTest, -10.0);
		assertEquals("the lower boundary cannot cross 0 into the negatives so should be set to 0",
				new Range(0.0, 5.0), shiftResult);
	}
	
	@Test
	public void testShiftCorrectlyShiftsRangeDownWhenThereIsNoZeroCrossing() {
		Range rangeObjectUnderTest = new Range(5.0, 10.0);
		Range shiftResult = Range.shift(rangeObjectUnderTest, -2.0);
		assertEquals("A shift of -2 means the range should go down by 2",
				new Range(3.0, 8.0), shiftResult);
	}
	
	@Test
	public void testShiftCorrectlyShiftsRangeUpWhenThereIsNoZeroCrossing() {
		Range rangeObjectUnderTest = new Range(5.0, 10.0);
		Range shiftResult = Range.shift(rangeObjectUnderTest, 2.0);
		assertEquals("A shift of 2 means the range should go up by 2",
				new Range(7.0, 12.0), shiftResult);
	}

}

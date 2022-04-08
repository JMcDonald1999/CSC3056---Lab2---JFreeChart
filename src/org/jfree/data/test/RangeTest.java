package org.jfree.data.test;

import org.jfree.data.Range;
import org.junit.Test;

import junit.framework.TestCase;

public class RangeTest extends TestCase {
	
	@Test
	public void testCombineReturnsNullRangeInstanceForTwoNullArguments() {
		Range rangeObjectUnderTest = null;
		Range otherRangeObjectUnderTest = null;
		Range combinedRange = Range.combine(rangeObjectUnderTest, otherRangeObjectUnderTest);
		assertNull(combinedRange);
	}
	
	@Test
	public void testCombineReturnsFirstRangeArgumentWhenSecondRangeArgumentIsNull() {
		Range rangeObjectUnderTest = new Range(3.0, 7.0);
		Range otherRangeObjectUnderTest = null;
		Range combinedRange = Range.combine(rangeObjectUnderTest, otherRangeObjectUnderTest);
		assertEquals("The non-null Range should be returned", new Range(3.0, 7.0), combinedRange);
	}
	
	@Test
	public void testCombineReturnsSecondRangeArgumentWhenFirstRangeArgumentIsNull() {
		Range rangeObjectUnderTest = null;
		Range otherRangeObjectUnderTest = new Range (2.0, 6.0);
		Range combinedRange = Range.combine(rangeObjectUnderTest, otherRangeObjectUnderTest);
		assertEquals("The non-null Range should be returned", new Range(2.0, 6.0), combinedRange);
	}
	
	@Test
	public void testCombineReturnsCorrectRangeInstanceForValidArguments() {
		Range rangeObjectUnderTest = new Range(3.0, 7.0);
		Range otherRangeObjectUnderTest = new Range(2.0, 6.0);
		Range combinedRange = Range.combine(rangeObjectUnderTest, otherRangeObjectUnderTest);
		assertEquals("The combined range should take the lower bound from Argument 2 and the"
				+ "upper bound from Argument 1", new Range(2.0, 7.0), combinedRange);
	}
	
	@Test
	public void testConstrainReturnsLowerBoundWhenArgumentIsLessThanLowerBound() {
		Range rangeObjectUnderTest = new Range(5.0, 15.0);
		assertEquals("value is lower than lower bound so lower bound should be returned", 
				5.0, rangeObjectUnderTest.constrain(4.9));
	}
	
	@Test
	public void testConstrainReturnsArgumentWhenArgumentIsLowerBound() {
		Range rangeObjectUnderTest = new Range(5.0, 15.0);
		assertEquals("value is lower bound so should be returned", 
				5.0, rangeObjectUnderTest.constrain(5.0));
	}
	
	@Test
	public void testConstrainReturnsArgumentWhenArgumentIsComfortablyInRange() {
		Range rangeObjectUnderTest = new Range(5.0, 15.0);
		assertEquals("value is within range so should be returned", 
				10.0, rangeObjectUnderTest.constrain(10.0));
	}
	
	@Test
	public void testConstrainReturnsArgumentWhenArgumentIsUpperBound() {
		Range rangeObjectUnderTest = new Range(5.0, 15.0);
		assertEquals("value is upper bound so should be returned", 
				15.0, rangeObjectUnderTest.constrain(15.0));
	}
	
	@Test
	public void testConstrainReturnsUpperBoundWhenArgumentIsMoreThanUpperBound() {
		Range rangeObjectUnderTest = new Range(5.0, 15.0);
		assertEquals("value is higher than upper bound so upper bound should be returned", 
				15.0, rangeObjectUnderTest.constrain(15.1));
	}
	
	@Test
	public void testContainsReturnsFalseForArgumentLessThanLowerBound() {
		Range rangeObjectUnderTest = new Range(5.0, 15.0);
		assertFalse("4.9 is less than the lower bound of 5.0 so method should return false",
				rangeObjectUnderTest.contains(4.9));
	}
	
	@Test
	public void testContainsReturnsTrueForLowerBound() {
		Range rangeObjectUnderTest = new Range(5.0, 15.0);
		assertTrue("Lower bound is within the range so method should return true",
				rangeObjectUnderTest.contains(5.0));
	}
	
	@Test
	public void testContainsReturnsTrueForArgumentComfortablyInsideRange() {
		Range rangeObjectUnderTest = new Range(5.0, 15.0);
		assertTrue("10.0 is well within the range so method should return true",
				rangeObjectUnderTest.contains(10.0));
	}
		
	@Test
	public void testContainsReturnsTrueForUpperBound() {
		Range rangeObjectUnderTest = new Range(5.0, 15.0);
		assertTrue("Upper bound is within the range so method should return true",
				rangeObjectUnderTest.contains(15.0));
	}
	
	@Test
	public void testContainsReturnsFalseForArgumentGreaterThanUpperBound() {
		Range rangeObjectUnderTest = new Range(5.0, 15.0);
		assertFalse("15.1 is more than the upper bound of 15.0 so method should return false",
				rangeObjectUnderTest.contains(15.1));
	}
	
	@Test
	public void testExpandToIncludeCreatesOneLengthRangeWhenRangeArgumentIsNull() {
		Range rangeObjectUnderTest = null;
		Range expandToIncludeResult = Range.expandToInclude(rangeObjectUnderTest, 25.0);
		assertEquals("Range Argument is null so a one value range should be returned",
				new Range(25.0, 25.0), expandToIncludeResult);
	}
	
	@Test
	public void testExpandToIncludeCorrectlyExtendsRangeForValueJustOutsideLowerBound() {
		Range rangeObjectUnderTest = new Range(5.0, 15.0);
		Range expandToIncludeResult = Range.expandToInclude(rangeObjectUnderTest, 4.9);
		assertEquals("value is below the range so lower boundary should change",
				new Range(4.9, 15.0), expandToIncludeResult);
	}
	
	@Test
	public void testExpandToIncludeDoesNotChangeRangeForLowerBoundaryValue() {
		Range rangeObjectUnderTest = new Range(5.0, 15.0);
		Range expandToIncludeResult = Range.expandToInclude(rangeObjectUnderTest, 5.0);
		assertEquals("value is lower boundary but within the range so no change to range should occur", 
				new Range(5.0, 15.0), expandToIncludeResult);
	}
	
	@Test
	public void testExpandToIncludeDoesNotChangeRangeForValueComfortablyInsideRange() {
		Range rangeObjectUnderTest = new Range(5.0, 15.0);
		Range expandToIncludeResult = Range.expandToInclude(rangeObjectUnderTest, 10.0);
		assertEquals("value is within the range so no change to range should occur",
				new Range(5.0, 15.0), expandToIncludeResult);
	}
	
	@Test
	public void testExpandToIncludeDoesNotChangeRangeForUpperBoundaryValue() {
		Range rangeObjectUnderTest = new Range(5.0, 15.0);
		Range expandToIncludeResult = Range.expandToInclude(rangeObjectUnderTest, 15.0);
		assertEquals("value is upper boundary but within the range so no change to range should occur",
				new Range(5.0, 15.0), expandToIncludeResult);
	}
		
	@Test
	public void testExpandToIncludeCorrectlyExtendsRangeForValueJustOutsideUpperBound() {
		Range rangeObjectUnderTest = new Range(5.0, 15.0);
		Range expandToIncludeResult = Range.expandToInclude(rangeObjectUnderTest, 15.1);
		assertEquals("value is above the range so upper boundary should change",
				new Range(5.0, 15.1), expandToIncludeResult);
	}
	
	@Test
	public void testShiftThrowsIllegalArgumentExceptionForNullRangeArgument() {
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
		Range rangeObjectUnderTest = new Range(20.0, 30.0);
		Range shiftResult = Range.shift(rangeObjectUnderTest, 0.0);
		assertEquals("A shift of 0 means the range should be the same", new Range(20.0, 30.0), shiftResult);
	}
	
	@Test
	public void testShiftCorrectlyDoesNotZeroCrossWhenPartOfRangeGoesNegativeToPositive() {
		Range zeroCrossingRange = new Range(-5.0, 5.0);
		Range shiftResult = Range.shift(zeroCrossingRange, 10.0);
		assertEquals("the lower boundary cannot cross 0 into the positives so should be set to 0",
				new Range(0.0, 15.0), shiftResult);
	}
	
	@Test
	public void testShiftCorrectlyDoesNotZeroCrossWhenPartOfRangeGoesPositiveToNegative() {
		Range zeroCrossingRange = new Range(-5.0, 5.0);
		Range shiftResult = Range.shift(zeroCrossingRange, -10.0);
		assertEquals("the upper boundary cannot cross 0 into the negatives so should be set to 0",
				new Range(-15.0, 0.0), shiftResult);
	}
	
	@Test
	public void testShiftCorrectlyShiftsRangeDownWhenThereIsNoZeroCrossing() {
		Range safeRange = new Range(20.0, 30.0);
		Range shiftResult = Range.shift(safeRange, -10.0);
		assertEquals("A shift of -10 means the range should go down by 10", new Range(10.0, 20.0), shiftResult);
	}
	
	@Test
	public void testShiftCorrectlyShiftsRangeUpWhenThereIsNoZeroCrossing() {
		Range safeRange = new Range(20.0, 30.0);
		Range shiftResult = Range.shift(safeRange, 10.0);
		assertEquals("A shift of 10 means the range should go up by 10", new Range(30.0, 40.0), shiftResult);
	}

}

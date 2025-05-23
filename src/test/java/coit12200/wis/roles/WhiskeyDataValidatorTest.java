package coit12200.wis.roles;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WhiskeyDataValidatorTest {
    WhiskeyDataValidator wdv = new WhiskeyDataValidator();

    @Test
    public void validAgeRangeTest() {
        var result = wdv.checkAgeRange("5", "10");
        assertTrue(result.result());
        assertEquals(5, result.r().lower());
        assertEquals(10, result.r().upper());
        assertEquals("", result.message());
    }

    @Test
    public void sameLowerAndUpperAgeTest() {
        var result = wdv.checkAgeRange("7", "7");
        assertEquals(7, result.r().lower());
        assertEquals(7, result.r().upper());
    }

    @Test
    public void lowerGreaterThanUpperTest() {
        var result = wdv.checkAgeRange("10", "5");
        assertEquals("Lower age must be less than the higher age.", result.message());
    }

    @Test
    public void negativeAgeTest() {
        var result = wdv.checkAgeRange("-1", "10");
        assertEquals("Ages must be positive numbers.", result.message());
    }

    @Test
    public void nonIntegerAgeTest() {
        var result = wdv.checkAgeRange("abc", "10");
        assertEquals("Both ages must be a number.", result.message());
    }


    @Test
    public void validRegionTest() {
        var result = wdv.checkRegion("Highland");
        assertEquals("", result.message());
    }

    @Test
    public void emptyRegionTest() {
        var result = wdv.checkRegion("");
        assertEquals("Region can't be empty.", result.message());
    }

    @Test
    public void whitespaceRegionTest() {
        var result = wdv.checkRegion("   ");
        assertEquals("Region can't be empty.", result.message());
    }

    @Test
    public void nullRegionTest() {
        var result = wdv.checkRegion(null);
        assertEquals("Region can't be empty.", result.message());
    }
}
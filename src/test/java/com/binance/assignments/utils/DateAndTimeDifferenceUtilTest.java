package com.binance.assignments.utils;

import org.junit.Test;

import static org.junit.Assert.*;

public class DateAndTimeDifferenceUtilTest {

    @Test
    public void correctValueEntered_getDateDifferenceInHours() throws Exception {
      assertEquals(DateAndTimeDifferenceUtil.getDateDifferenceInHours("2017-04-03","12:00","15:00"),3);
    }

    @Test(expected = Exception.class)
    public void throwsException_whenDateIsMissing() throws Exception {
        DateAndTimeDifferenceUtil.getDateDifferenceInHours("","12:00","15:00");
    }
    @Test(expected = Exception.class)
    public void throwsException_whenStartTimeIsMissing() throws Exception {
        DateAndTimeDifferenceUtil.getDateDifferenceInHours("2017-04-03",null,"15:00");
    }
    @Test(expected = Exception.class)
    public void throwsException_whenEndTimeIsMissing() throws Exception {
        DateAndTimeDifferenceUtil.getDateDifferenceInHours("2017-04-03","12:00",null);
    }

    @Test(expected = Exception.class)
    public void throwsException_whenDateNotFormedProperly() throws Exception {
        DateAndTimeDifferenceUtil.getDateDifferenceInHours("2017-04--03","12:00","15:00");
    }

}
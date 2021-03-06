package org.ei.drishti.domain;

import static junit.framework.Assert.assertEquals;
import static org.ei.drishti.domain.ReportIndicator.CONDOM;
import static org.ei.drishti.domain.ReportIndicator.parseToReportIndicator;

import org.junit.Test;

public class ReportIndicatorTest {

    @Test
    public void shouldParseStringToReportIndicator() throws Exception {
        assertEquals(CONDOM, parseToReportIndicator("condom"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenValueIsNotAValidReportIndicator() throws Exception {
        parseToReportIndicator("invalid value");
    }
}

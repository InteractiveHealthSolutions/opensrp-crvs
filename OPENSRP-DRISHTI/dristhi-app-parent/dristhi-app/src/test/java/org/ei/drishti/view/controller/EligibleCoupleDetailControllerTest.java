package org.ei.drishti.view.controller;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import java.util.ArrayList;
import java.util.HashMap;

import org.ei.drishti.domain.EligibleCouple;
import org.ei.drishti.domain.TimelineEvent;
import org.ei.drishti.repository.AllEligibleCouples;
import org.ei.drishti.repository.AllTimelineEvents;
import org.ei.drishti.util.DateUtil;
import org.ei.drishti.view.contract.Child;
import org.ei.drishti.view.contract.CoupleDetails;
import org.ei.drishti.view.contract.ECDetail;
import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.robolectric.RobolectricTestRunner;

import android.content.Context;

import com.google.gson.Gson;

@RunWith(RobolectricTestRunner.class)
public class EligibleCoupleDetailControllerTest {
    @Mock
    Context context;
    @Mock
    private AllEligibleCouples allEligibleCouples;
    @Mock
    private AllTimelineEvents allTimelineEvents;

    private String caseId = "1234-5678-1234";
    private EligibleCoupleDetailController controller;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        DateUtil.fakeIt(new LocalDate(2012, 8, 1));
        controller = new EligibleCoupleDetailController(context, caseId, allEligibleCouples, allTimelineEvents);
    }

    @Test
    public void shouldGetANCDetailsAsJSON() {
        TimelineEvent pregnancyEvent = TimelineEvent.forStartOfPregnancyForEC(caseId, "TC 1", "2011-10-21", "2011-10-21");
        TimelineEvent fpEvent = TimelineEvent.forChangeOfFPMethod(caseId, "condom", "iud", "2011-12-22");
        TimelineEvent eventVeryCloseToCurrentDate = TimelineEvent.forChangeOfFPMethod(caseId, "iud", "condom", "2012-07-29");

        HashMap<String, String> details = new HashMap<String, String>();
        details.put("ashaName", "Shiwani");
        details.put("isHighPriority", "1");

        when(allEligibleCouples.findByCaseID(caseId)).thenReturn(new EligibleCouple("EC CASE 1", "Woman 1", "Husband 1", "EC Number 1", "Village 1", "Subcenter 1", details));
        when(allTimelineEvents.forCase(caseId)).thenReturn(asList(pregnancyEvent, fpEvent, eventVeryCloseToCurrentDate));

        ECDetail expectedDetail = new ECDetail(caseId, "Village 1", "Subcenter 1", "EC Number 1", true, null, null, new ArrayList<Child>(), new CoupleDetails("Woman 1", "Husband 1", "EC Number 1", false), details)
                .addTimelineEvents(asList(eventFor(eventVeryCloseToCurrentDate, "29-07-2012"), eventFor(fpEvent, "22-12-2011"), eventFor(pregnancyEvent, "21-10-2011")));

        String actualJson = controller.get();
        ECDetail actualDetail = new Gson().fromJson(actualJson, ECDetail.class);

        assertEquals(expectedDetail, actualDetail);
    }

    private org.ei.drishti.view.contract.TimelineEvent eventFor(TimelineEvent pregnancyEvent, String expectedRelativeTime) {
        return new org.ei.drishti.view.contract.TimelineEvent(pregnancyEvent.type(), pregnancyEvent.title(), new String[]{pregnancyEvent.detail1(), pregnancyEvent.detail2()}, expectedRelativeTime);
    }
}

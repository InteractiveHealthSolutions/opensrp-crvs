package org.ei.drishti.view.contract;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.ei.drishti.view.dialog.AllClientsFilter;
import org.ei.drishti.view.dialog.AllEligibleCoupleServiceMode;
import org.ei.drishti.view.dialog.BPLSort;
import org.ei.drishti.view.dialog.ECNumberSort;
import org.ei.drishti.view.dialog.ECSearchOption;
import org.ei.drishti.view.dialog.HighPrioritySort;
import org.ei.drishti.view.dialog.NameSort;
import org.ei.drishti.view.dialog.OutOfAreaFilter;
import org.ei.drishti.view.dialog.SCSort;
import org.ei.drishti.view.dialog.STSort;
import org.ei.drishti.view.dialog.VillageFilter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

@RunWith(RobolectricTestRunner.class)
public class SmartRegisterClientsTest {

    @Test
    public void emptyStringSearchShouldReturnAllTheResults() {
        SmartRegisterClients originalClients = getUniformSmartRegisterClients(10);
        SmartRegisterClients filteredClients = originalClients.applyFilter(
                new AllClientsFilter(),
                new AllEligibleCoupleServiceMode(),
                new ECSearchOption(""),
                new NameSort());

        assertEquals(originalClients, filteredClients);
    }

    @Test
    public void ShouldReturnFilteredResultsForSearchString() {
        SmartRegisterClients originalClients = getSmartRegisterClientsWithProperDetails();

        SmartRegisterClients filteredClients = originalClients.applyFilter(
                new AllClientsFilter(),
                new AllEligibleCoupleServiceMode(),
                new ECSearchOption("a"),
                new NameSort());

        assertEquals(3, filteredClients.size());
        for (int i = 0; i < 3; i++) {
            assertTrue(filteredClients.get(i).name().startsWith("A"));
        }
    }

    @Test
    public void ShouldReturnSortedResultsForNameSortOption() {
        SmartRegisterClients originalClients = getSmartRegisterClientsWithProperDetails();

        SmartRegisterClients filteredClients = originalClients.applyFilter(
                new AllClientsFilter(),
                new AllEligibleCoupleServiceMode(),
                new ECSearchOption(""),
                new NameSort());

        ECClients expectedClients = new ECClients();
        expectedClients.addAll(
                asList(
                        new ECClient("abcd1", "Adhiti", "Rama", "Battiganahalli", 69).withIsHighPriority(true).withIsOutOfArea(true),
                        new ECClient("abcd2", "Akshara", "Rajesh", "Half bherya", 500).withCaste("SC").withEconomicStatus("BPL").withIsHighPriority(true),
                        new ECClient("abcd3", "Anitha", "Chandan", "Half bherya", 87).withCaste("SC").withIsOutOfArea(true),
                        new ECClient("abcd6", "Bhagya", "Ramesh", "Hosa agrahara", 93).withIsHighPriority(true).withCaste("ST"),
                        new ECClient("abcd4", "Bhavani", "Ravi", "Gowrikoppalu", 140).withEconomicStatus("BPL"),
                        new ECClient("abcd5", "Chaitra", "Rams", "Somanahalli colony", 36).withCaste("ST")
                ));
        assertEquals(expectedClients, filteredClients);
    }

    @Test
    public void ShouldReturnSortedResultsForECNumberSortOption() {
        SmartRegisterClients originalClients = getSmartRegisterClientsWithProperDetails();

        SmartRegisterClients filteredClients = originalClients.applyFilter(
                new AllClientsFilter(),
                new AllEligibleCoupleServiceMode(),
                new ECSearchOption(""),
                new ECNumberSort());

        assertEquals(6, filteredClients.size());
        assertEquals("Adhiti", filteredClients.get(1).name());
        assertEquals("Akshara", filteredClients.get(5).name());
        assertEquals("Anitha", filteredClients.get(2).name());
        assertEquals("Bhagya", filteredClients.get(3).name());
        assertEquals("Bhavani", filteredClients.get(4).name());
        assertEquals("Chaitra", filteredClients.get(0).name());
    }

    @Test
    public void ShouldReturnSortedResultsForBPLSortOption() {
        SmartRegisterClients originalClients = getSmartRegisterClientsWithProperDetails();
        SmartRegisterClients filteredClients = originalClients.applyFilter(
                new AllClientsFilter(),
                new AllEligibleCoupleServiceMode(),
                new ECSearchOption(""),
                new BPLSort());
        assertEquals(6, filteredClients.size());
        assertEquals("Adhiti", filteredClients.get(2).name());
        assertEquals("Akshara", filteredClients.get(0).name());
        assertEquals("Anitha", filteredClients.get(3).name());
        assertEquals("Bhagya", filteredClients.get(4).name());
        assertEquals("Bhavani", filteredClients.get(1).name());
        assertEquals("Chaitra", filteredClients.get(5).name());
    }

    @Test
    public void ShouldReturnSortedResultsForHPSortOption() {
        SmartRegisterClients originalClients = getSmartRegisterClientsWithProperDetails();
        SmartRegisterClients filteredClients = originalClients.applyFilter(
                new AllClientsFilter(),
                new AllEligibleCoupleServiceMode(),
                new ECSearchOption(""),
                new HighPrioritySort());
        assertEquals(6, filteredClients.size());
        assertEquals("Adhiti", filteredClients.get(0).name());
        assertEquals("Akshara", filteredClients.get(1).name());
        assertEquals("Anitha", filteredClients.get(3).name());
        assertEquals("Bhagya", filteredClients.get(2).name());
        assertEquals("Bhavani", filteredClients.get(4).name());
        assertEquals("Chaitra", filteredClients.get(5).name());
    }

    @Test
    public void ShouldReturnSortedResultsForSCSortOption() {
        SmartRegisterClients originalClients = getSmartRegisterClientsWithProperDetails();
        SmartRegisterClients filteredClients = originalClients.applyFilter(
                new AllClientsFilter(),
                new AllEligibleCoupleServiceMode(),
                new ECSearchOption(""),
                new SCSort());
        assertEquals(6, filteredClients.size());
        assertEquals("Adhiti", filteredClients.get(2).name());
        assertEquals("Akshara", filteredClients.get(0).name());
        assertEquals("Anitha", filteredClients.get(1).name());
        assertEquals("Bhagya", filteredClients.get(3).name());
        assertEquals("Bhavani", filteredClients.get(4).name());
        assertEquals("Chaitra", filteredClients.get(5).name());
    }

    @Test
    public void ShouldReturnSortedResultsForSTSortOption() {
        SmartRegisterClients originalClients = getSmartRegisterClientsWithProperDetails();
        SmartRegisterClients filteredClients = originalClients.applyFilter(
                new AllClientsFilter(),
                new AllEligibleCoupleServiceMode(),
                new ECSearchOption(""),
                new STSort());
        assertEquals(6, filteredClients.size());
        assertEquals("Adhiti", filteredClients.get(2).name());
        assertEquals("Akshara", filteredClients.get(3).name());
        assertEquals("Anitha", filteredClients.get(4).name());
        assertEquals("Bhagya", filteredClients.get(0).name());
        assertEquals("Bhavani", filteredClients.get(5).name());
        assertEquals("Chaitra", filteredClients.get(1).name());
    }

    @Test
    public void ShouldReturn2ResultsFor_Half_Bherya_VillageFilterOption() {
        SmartRegisterClients originalClients = getSmartRegisterClientsWithProperDetails();
        SmartRegisterClients filteredClients = originalClients.applyFilter(
                new VillageFilter("Half bherya"),
                new AllEligibleCoupleServiceMode(),
                new ECSearchOption(""),
                new NameSort());
        assertEquals(2, filteredClients.size());
        assertEquals("Akshara", filteredClients.get(0).name());
        assertEquals("Anitha", filteredClients.get(1).name());
    }

    @Test
    public void ShouldReturn2ResultsFor_Hosa_agrahara_VillageFilterOption() {
        SmartRegisterClients originalClients = getSmartRegisterClientsWithProperDetails();
        SmartRegisterClients filteredClients = originalClients.applyFilter(
                new VillageFilter("Hosa agrahara"),
                new AllEligibleCoupleServiceMode(),
                new ECSearchOption(""),
                new NameSort());
        assertEquals(1, filteredClients.size());
        assertEquals("Bhagya", filteredClients.get(0).name());
    }

    @Test
    public void ShouldReturn1ResultsFor_Battiganahalli_VillageFilterOption() {
        SmartRegisterClients originalClients = getSmartRegisterClientsWithProperDetails();
        SmartRegisterClients filteredClients = originalClients.applyFilter(
                new VillageFilter("Battiganahalli"),
                new AllEligibleCoupleServiceMode(),
                new ECSearchOption(""),
                new NameSort());
        assertEquals(1, filteredClients.size());
        assertEquals("Adhiti", filteredClients.get(0).name());
    }

    @Test
    public void ShouldReturn1ResultsFor_Somanahalli_colony_VillageFilterOption() {
        SmartRegisterClients originalClients = getSmartRegisterClientsWithProperDetails();
        SmartRegisterClients filteredClients = originalClients.applyFilter(
                new VillageFilter("Somanahalli colony"),
                new AllEligibleCoupleServiceMode(),
                new ECSearchOption(""),
                new NameSort());
        assertEquals(1, filteredClients.size());
        assertEquals("Chaitra", filteredClients.get(0).name());
    }

    @Test
    public void ShouldReturn1ResultsForOutOfAreaFilterOption() {
        SmartRegisterClients originalClients = getSmartRegisterClientsWithProperDetails();
        SmartRegisterClients filteredClients = originalClients.applyFilter(
                new OutOfAreaFilter(),
                new AllEligibleCoupleServiceMode(),
                new ECSearchOption(""),
                new NameSort());
        assertEquals(2, filteredClients.size());
        assertEquals("Adhiti", filteredClients.get(0).name());
        assertEquals("Anitha", filteredClients.get(1).name());
    }

    @Test
    public void ShouldReturnCascadedResultsApplyingMultipleFilterOption() {
        SmartRegisterClients originalClients = getSmartRegisterClientsWithProperDetails();

        SmartRegisterClients filteredClients = originalClients.applyFilter(
                new VillageFilter("Hosa agrahara"),
                new AllEligibleCoupleServiceMode(),
                new ECSearchOption("bh"),
                new NameSort());

        assertEquals(1, filteredClients.size());
        assertEquals("Bhagya", filteredClients.get(0).name());
    }

    public SmartRegisterClients getUniformSmartRegisterClients(int clientCount) {
        SmartRegisterClients clients = new SmartRegisterClients();
        for (int i = 0; i < clientCount; i++) {
            clients.add(new ECClient("CASE " + i, "Wife" + i, "Husband" + i, "Village" + i, 100 + i));
        }
        return clients;
    }

    public ECClients getSmartRegisterClientsWithProperDetails() {
        ECClients clients = new ECClients();
        clients.add(new ECClient("abcd2", "Akshara", "Rajesh", "Half bherya", 500).withCaste("SC").withEconomicStatus("BPL").withIsHighPriority(true));
        clients.add(new ECClient("abcd1", "Adhiti", "Rama", "Battiganahalli", 69).withIsHighPriority(true).withIsOutOfArea(true));
        clients.add(new ECClient("abcd5", "Chaitra", "Rams", "Somanahalli colony", 36).withCaste("ST"));
        clients.add(new ECClient("abcd4", "Bhavani", "Ravi", "Gowrikoppalu", 140).withEconomicStatus("BPL"));
        clients.add(new ECClient("abcd6", "Bhagya", "Ramesh", "Hosa agrahara", 93).withIsHighPriority(true).withCaste("ST"));
        clients.add(new ECClient("abcd3", "Anitha", "Chandan", "Half bherya", 87).withCaste("SC").withIsOutOfArea(true));
        return clients;
    }
}

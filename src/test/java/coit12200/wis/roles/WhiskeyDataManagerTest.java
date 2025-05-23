package coit12200.wis.roles;

import coit12200.wis.data.WhiskeyData;
import org.junit.jupiter.api.Test;
import coit12200.wis.data.WhiskeyData.WhiskeyDetails;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WhiskeyDataManagerTest {

    @Test
    void findAllMaltsGoldenPathTest() {
    }

    @Test
    void findMaltsFromRegionGoldenPathTest() {
    }

    @Test
    void findMaltsInAgeRangeGoldenPathTest() {
    }

    @Test
    void firstGoldenPathTest() {
    }

    @Test
    void nextGoldenPathTest() {
    }

    @Test
    public void nextWithNoRecordsTest() {
        WhiskeyDataManager wdm = new WhiskeyDataManager(null);
        WhiskeyData.WhiskeyDetails[] details = {};
        wdm.setDetails(details);
        assertEquals(null,wdm.next());
    }

    @Test
    public void nextWithOneRecordTest() {
        WhiskeyData wd = new WhiskeyData();
        WhiskeyDataManager wdm = new WhiskeyDataManager(wd);
        WhiskeyDetails[] details = {new WhiskeyDetails("1", 1, "1", 1)};
        wdm.setDetails(details);
        wdm.first();
        assertEquals(null, wdm.next());
    }

    @Test
    public void nextWithTwoRecordsTest() {
        WhiskeyData wd = new WhiskeyData();
        WhiskeyDataManager wdm = new WhiskeyDataManager(wd);
        WhiskeyDetails[] details = {
                new WhiskeyDetails("1", 1, "1", 1),
                new WhiskeyDetails("2", 2, "2", 2)
        };
        wdm.setDetails(details);
        WhiskeyDetails first = wdm.first();
        WhiskeyDetails second = wdm.next();
        assertEquals(details[1], second);
    }


    @Test
    public void previousWithZeroRecordsTest() {
        WhiskeyData wd = new WhiskeyData();
        WhiskeyDataManager wdm = new WhiskeyDataManager(wd);
        WhiskeyDetails[] details = {};
        wdm.setDetails(details);
        assertNull(wdm.previous());
    }

    @Test
    public void previousWithOneRecordTest() {
        WhiskeyData wd = new WhiskeyData();
        WhiskeyDataManager wdm = new WhiskeyDataManager(wd);
        WhiskeyDetails[] details = {
                new WhiskeyDetails("1", 1, "1", 1)
        };
        wdm.setDetails(details);
        wdm.first();
        assertNull(wdm.previous());
    }

    @Test
    public void previousWithTwoRecordsTest() {
        WhiskeyData wd = new WhiskeyData();
        WhiskeyDataManager wdm = new WhiskeyDataManager(wd);
        WhiskeyDetails[] details = {
                new WhiskeyDetails("1", 1, "1", 1),
                new WhiskeyDetails("2", 2, "2", 2)
        };
        wdm.setDetails(details);
        wdm.first();
        wdm.next();
        WhiskeyDetails first = wdm.previous();
        assertEquals(details[0], first);
    }

    @Test
    void connectGoldenPathTest() {
    }

    @Test
    void disconnectGoldenPathTest() {
    }

    @Test
    void setDetailsGoldenPathTest() {
    }
}
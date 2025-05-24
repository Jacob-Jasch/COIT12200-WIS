package coit12200.wis.roles;

import coit12200.wis.data.WhiskeyData;
import coit12200.wis.data.WhiskeyData.WhiskeyDetails;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WhiskeyDataManager {
    private WhiskeyData wd;
    private List<WhiskeyDetails> records;
    private int numberOfRecords;
    private int currentIndex;
    private WhiskeyDetails currentRecord;

    public WhiskeyDataManager(WhiskeyData wd) {
        this.wd = wd;

    }

    public int findAllMalts() {
        records = wd.getAllMalts();
        if (!records.isEmpty()) {
            currentIndex = 0;
            return records.size();
        } else {
            currentIndex = -1;
            return 0;
        }
    }

    public int findMaltsFromRegion(String r)
    {
        records = wd.getMaltsFromRegion(r);
        if (!records.isEmpty()) {
            currentIndex = 0;
            return records.size();
        } else {
            currentIndex = -1;
            return 0;
        }
    }

    public int findMaltsInAgeRange(int r1, int r2)
    {
        records = wd.getMaltsInAgeRange(r1, r2);
        if (!records.isEmpty()) {
            currentIndex = 0;
            return records.size();
        } else {
            currentIndex = -1;
            return 0;
        }
    }

    public WhiskeyDetails first()
    {
        if (!records.isEmpty()) {
            currentIndex = 0;
            return records.get(currentIndex);
        }
        return null;
    }

    public WhiskeyDetails next() {
        if (records == null || currentIndex == -1 || currentIndex >= records.size() - 1) {
            return null;
        }
        currentIndex++;
        currentRecord = records.get(currentIndex);
        return currentRecord;
    }

    public WhiskeyDetails previous() {
        if (records == null || currentIndex <= 0) {
            return null;
        }
        currentIndex--;
        currentRecord = records.get(currentIndex);
        return currentRecord;
    }

    public void connect()
    {
        wd.connect();
    }

    public void disconnect()
    {
        wd.disconnect();
    }

    //THIS IS ONLY FOR TESTING
    public void setDetails(WhiskeyDetails[] details) {
        List list = Arrays.asList(details);
        records = new ArrayList<>(list);
        numberOfRecords = records.size();
        currentIndex   = ( numberOfRecords == 0 ) ? -1 : 0;
        currentRecord = ( numberOfRecords == 0 ) ? null : records.get( 0 );
    }

}

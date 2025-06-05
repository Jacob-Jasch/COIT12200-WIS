package coit12200.wis.roles;

import coit12200.wis.data.WhiskeyData;
import coit12200.wis.data.WhiskeyData.WhiskeyDetails;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * WhiskeyDataManager class manages whiskey data operations.
 * It provides methods to find malts by region, age range, and retrieve all malts.
 * It also allows navigation through the records of whiskey details.
 * @author Jacob Duckworth 
 */
public class WhiskeyDataManager {
    private WhiskeyData wd;
    private List<WhiskeyDetails> records;
    private int numberOfRecords;
    private int currentIndex;
    private WhiskeyDetails currentRecord;

    /**
     * Constructor for WhiskeyDataManager class.
     * @param wd the WhiskeyData instance to manage whiskey data operations
     */
    public WhiskeyDataManager(WhiskeyData wd) {
        this.wd = wd;

    }

    /**
     * Finds all malts in the database.
     * It retrieves all whiskey details and sets the current index to the first record.
     * @return the number of malts found
     */
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

    /**
     * Finds malts from a specific region.
     * It retrieves whiskey details from the specified region and sets the current index to the first record.
     * @param r the region to search for malts
     * @return the number of malts found in the specified region
     */
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

    /**
     * Finds malts within a specific age range.
     * It retrieves whiskey details that fall within the specified age range and sets the current index to the first record.
     * @param r1 the lower bound of the age range
     * @param r2 the upper bound of the age range
     * @return the number of malts found within the specified age range
     */
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

    /**
     * Retrieves the current record of whiskey details.
     * If no records are available, it returns null.
     * @return the current WhiskeyDetails record or null if no records are available
     */
    public WhiskeyDetails first()
    {
        if (!records.isEmpty()) {
            currentIndex = 0;
            return records.get(currentIndex);
        }
        return null;
    }

    /**
     * Retrieves the current record of whiskey details.
     * If no records are available or the current index is out of bounds, it returns null.
     * @return the current WhiskeyDetails record or null if no records are available
     */
    public WhiskeyDetails next() {
        if (records == null || currentIndex == -1 || currentIndex >= records.size() - 1) {
            return null;
        }
        currentIndex++;
        currentRecord = records.get(currentIndex);
        return currentRecord;
    }

    /**
     * Retrieves the previous record of whiskey details.
     * If no records are available or the current index is already at the first record, it returns null.
     * @return the previous WhiskeyDetails record or null if no records are available or at the first record
     */
    public WhiskeyDetails previous() {
        if (records == null || currentIndex <= 0) {
            return null;
        }
        currentIndex--;
        currentRecord = records.get(currentIndex);
        return currentRecord;
    }

    /**
     * Connects to the whiskey data source.
     */
    public void connect()
    {
        wd.connect();
    }

    /**
     * Disconnects from the whiskey data source.
     */
    public void disconnect()
    {
        wd.disconnect();
    }

    /**
     * Sets the details of whiskey records for testing purposes.
     * @param details the array of WhiskeyDetails to set as records
     */
    //THIS IS ONLY FOR TESTING
    public void setDetails(WhiskeyDetails[] details) {
        List list = Arrays.asList(details);
        records = new ArrayList<>(list);
        numberOfRecords = records.size();
        currentIndex   = ( numberOfRecords == 0 ) ? -1 : 0;
        currentRecord = ( numberOfRecords == 0 ) ? null : records.get( 0 );
    }

}

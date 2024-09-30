package HistoryManagement;

import java.time.Duration;
import java.time.Instant;
import java.util.Comparator;

/**
 * Sorts list of HistoryItems by Instant time.
 */
public class HistoryComparator implements Comparator<HistoryItem> {

    @Override
    public int compare(HistoryItem h1, HistoryItem h2) {
        return h1.getTime().compareTo(h2.getTime());
    }
}
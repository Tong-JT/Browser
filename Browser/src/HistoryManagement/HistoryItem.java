package HistoryManagement;

import java.time.Duration;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class HistoryItem {
    
    private Instant time;
    private String link;
    private String favicon;
    private String title;

    public HistoryItem(Instant time, String link, String favicon, String title) {
        this.time = time;
        this.link = link;
        this.favicon = favicon;
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setTime(Instant time) {
        this.time = time;
    }

    public Instant getTime() {
        return time;
    }

    public String getFavicon() {
        return favicon;
    }

    public String getTitle() {
        return title;
    }

    public String getFormattedTime() {
        Instant now = Instant.now();
        Duration duration = Duration.between(time, now);

        if (duration.toMinutes() < 1) {
            return "just now";
        } else if (duration.toMinutes() < 30) {
                return duration.toMinutes() + " minutes ago";
        } else if (duration.toHours() < 24) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm a")
                    .withZone(ZoneId.systemDefault());
            return formatter.format(time);
        } else {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM")
                    .withZone(ZoneId.systemDefault());
            return formatter.format(time);
        }
    }
}

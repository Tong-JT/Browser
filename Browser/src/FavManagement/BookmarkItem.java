package FavManagement;

import javafx.scene.image.ImageView;

public class BookmarkItem {
    
    private String link;
    private ImageView favicon;
    private String title;

    public BookmarkItem(String link, String title, ImageView favicon) {
        this.link = link;
        this.title = title;
        this.favicon = favicon;
    }

    public String getLink() {
        return link;
    }

    public ImageView getFavicon() {
    	favicon.setFitWidth(17);
        favicon.setFitHeight(17);
        return favicon;
    }

    public String getTitle() {
        return title;
    }

}

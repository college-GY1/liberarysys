package KAN69;
public class book {
    private String name;
    private String description;
    private String genre;
    private int pageCount;
    private String author;
    private boolean isAvailable;
    public book(String name, String description, String genre, int pageCount, String author, boolean isAvailable) {
        this.name = name;
        this.description = description;
        this.genre = genre;
        this.pageCount = pageCount;
        this.author = author;
        this.isAvailable = isAvailable;
    }
    public book(String name, String description, String genre, int pageCount, String author) {
        this(name, description, genre, pageCount, author, true);
    }
    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
    public String getGenre() {
        return genre;
    }
    public int getPageCount() {
        return pageCount;
    }
    public String getAuthor() {
        return author;
    }
    public boolean isAvailable() {
        return isAvailable;
    }
    public void setAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }
    @Override
    public String toString() {
        return "[" + genre + "] '" + name + "' by " + author + " | " + pageCount + " pages | Status: " + (isAvailable ? "Available" : "On Loan");
    }
}

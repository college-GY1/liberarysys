package KAN69;
public class LibrarySystem {
    private User[] users;
    private int userCount;
    private book[] books;
    private int bookCount;
    private String[] authors;
    private int authorCount;
    private String[] genres;
    private int genreCount;
    public LibrarySystem() {
        this.users = new User[100];
        this.userCount = 0;
        this.books = new book[500];
        this.bookCount = 0;
        this.authors = new String[100];
        this.authorCount = 0;
        this.genres = new String[50];
        this.genreCount = 0;
    }
    public User[] getUsers() {
        return users;
    }
    public int getUserCount() {
        return userCount;
    }
    public book[] getBooks() {
        return books;
    }
    public int getBookCount() {
        return bookCount;
    }
    public String[] getAuthors() {
        return authors;
    }
    public int getAuthorCount() {
        return authorCount;
    }
    public String[] getGenres() {
        return genres;
    }
    public int getGenreCount() {
        return genreCount;
    }
    public void registerGenre(String genre) {
        if (genre == null || genre.trim().isEmpty()) return;
        for (int i = 0; i < genreCount; i++) {
            if (genres[i].equalsIgnoreCase(genre.trim())) return;
        }
        if (genreCount < genres.length) {
            genres[genreCount++] = genre.trim();
        }
    }
    public void registerAuthor(String author) {
        if (author == null || author.trim().isEmpty()) return;
        for (int i = 0; i < authorCount; i++) {
            if (authors[i].equalsIgnoreCase(author.trim())) return;
        }
        if (authorCount < authors.length) {
            authors[authorCount++] = author.trim();
        }
    }
    public boolean addBook(User user, String name, String description, String genre, int pageCount, String author) {
        if (user == null) {
            System.out.println("Error: Null User");
            return false;
        }
        if (!user.canAddMoreBooks()) {
            System.out.println("Book limit reached for user: " + user.getUsername());
            return false;
        }
        if (bookCount >= books.length) {
            System.out.println("Error: Library full");
            return false;
        }
        book newBook = new book(name, description, genre, pageCount, author, true);
        books[bookCount++] = newBook;
        user.addBookToCart(newBook);
        registerGenre(genre);
        registerAuthor(author);
        return true;
    }
}

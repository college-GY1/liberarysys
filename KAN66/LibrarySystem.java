package kan_66;
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
    public boolean isUsernameUnique(String username) {
        if (username == null || username.trim().isEmpty()) return false;
        for (int i = 0; i < userCount; i++) {
            if (users[i].getUsername().equalsIgnoreCase(username.trim())) {
                return false;
            }
        }
        return true;
    }
    public boolean isValidPassword(String password) {
        if (password == null || password.length() < 1) return false;
        boolean hasDigit = false;
        boolean hasSpecial = false;
        for (int i = 0; i < password.length(); i++) {
            char c = password.charAt(i);
            if (Character.isDigit(c)) {
                hasDigit = true;
            } else if (c == '$' || c == '%' || c == '_') {
                hasSpecial = true;
            }
        }
        return hasDigit && hasSpecial;
    }
    public boolean isValidID(String id) {
        if (id == null || id.length() != 9) return false;
        for (int i = 0; i < id.length(); i++) {
            if (!Character.isDigit(id.charAt(i))) return false;
        }
        return true;
    }
    public User createUser(String username, String password, String id, boolean isPremium) {
        if (!isUsernameUnique(username) || !isValidPassword(password) || !isValidID(id)) {
            return null;
        }
        if (userCount >= users.length) {
            return null;
        }
        User newUser = new User(username, password, id, isPremium);
        users[userCount++] = newUser;
        return newUser;
    }
    public User cheakAcountDetails(String username, String password) {
        if (username == null || password == null) return null;
        for (int i = 0; i < userCount; i++) {
            if (users[i].getUsername().equalsIgnoreCase(username.trim())) {
                if (users[i].checkPassword(password)) {
                    return users[i];
                }
            }
        }
        return null; 
    }
    public User login(String username, String password) {
        return cheakAcountDetails(username, password);
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
    public boolean addBook(book b) {
        if (b == null || bookCount >= books.length) return false;
        books[bookCount++] = b;
        registerGenre(b.getGenre());
        registerAuthor(b.getAuthor());
        return true;
    }
}

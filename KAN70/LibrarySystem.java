package KAN70;
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
    public User createUser(String username, String password, String id, boolean isPremium) {
        if (userCount >= users.length) {
            return null;
        }
        User newUser = new User(username, password, id, isPremium);
        users[userCount++] = newUser;
        return newUser;
    }
    public User login(String username, String password) {
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
    public boolean addBook(User user, String name, String description, String genre, int pageCount, String author) {
        if (user == null || !user.canAddMoreBooks() || bookCount >= books.length) {
            return false;
        }
        book newBook = new book(name, description, genre, pageCount, author, true);
        books[bookCount++] = newBook;
        user.addBookToCart(newBook);
        return true;
    }
    public boolean removeBook(User u, int cartIndex) {
        if (u == null || cartIndex < 0 || cartIndex >= u.getCartCount()) {
            return false;
        }
        book target = u.getCart()[cartIndex];
        if (target == null) {
            return false;
        }
        u.removeBookFromCart(cartIndex);
        for (int i = 0; i < bookCount; i++) {
            if (books[i] == target) {
                for (int j = i; j < bookCount - 1; j++) {
                    books[j] = books[j + 1];
                }
                books[--bookCount] = null;
                break;
            }
        }
        return true;
    }
}

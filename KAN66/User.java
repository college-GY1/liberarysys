package kan_66;
public class User {
    private String username;
    private String password;
    private boolean isPremium;
    private book[] cart;
    private String ID;
    private int cartCount;
    public User(String username, String password, String ID, boolean isPremium){
        this.username = username;
        this.password = password;
        this.ID = ID;
        this.isPremium = isPremium;
        this.cart = new book[10]; 
        this.cartCount = 0;
    }
    public String getUsername() {
        return username;
    }
    public boolean isPremium() {
        return isPremium;
    }
    public book[] getCart() {
        return cart;
    }
    public String getID() {
        return ID;
    }
    public int getCartCount() {
        return cartCount;
    }
    public boolean checkPassword(String pass) {
        if (this.password == null || pass == null) {
            return false;
        }
        return this.password.equals(pass);
    }
    public boolean cheakAcountDetails(String inputUsername, String inputPassword) {
        if (inputUsername == null || inputPassword == null) {
            return false;
        }
        return this.username.equalsIgnoreCase(inputUsername.trim()) && checkPassword(inputPassword);
    }
    public int getMaxAllowedBooks() {
        return isPremium ? 10 : 3;
    }
    public boolean canAddMoreBooks() {
        return cartCount < getMaxAllowedBooks();
    }
    public boolean addBookToCart(book b) {
        if (!canAddMoreBooks() || b == null) {
            return false;
        }
        cart[cartCount++] = b;
        return true;
    }
    public boolean removeBookFromCart(int index) {
        if (index < 0 || index >= cartCount) {
            return false;
        }
        for (int i = index; i < cartCount - 1; i++) {
            cart[i] = cart[i + 1];
        }
        cart[--cartCount] = null;
        return true;
    }
    @Override
    public String toString() {
        return "User: " + username + " | ID: " + ID + " | Type: " + (isPremium ? "Premium" : "Regular")
                + " | Cart: " + cartCount + "/" + getMaxAllowedBooks();
    }
}

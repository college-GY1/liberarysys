package KAN70;
import java.util.Scanner;
public class Main {
    private static LibrarySystem system = new LibrarySystem();
    private static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        System.out.println("==================================================");
        System.out.println("   ברוכים הבאים למערכת ניהול הספרייה העירונית      ");
        System.out.println("==================================================");
        while (true) {
            showMainMenu();
        }
    }
    private static void showMainMenu() {
        System.out.println("\n--- תפריט ראשי ---");
        System.out.println("1 — יצירת חשבון חדש (הרשמה)");
        System.out.println("2 — התחברות למערכת");
        System.out.println("3 — יציאה מהמערכת");
        System.out.print("בחר אפשרות (1-3): ");
        if (!scanner.hasNextLine()) {
            System.exit(0);
        }
        String choice = scanner.nextLine().trim();
        switch (choice) {
            case "1":
                handleRegister();
                break;
            case "2":
                handleLogin();
                break;
            case "3":
                System.out.println("תודה שהשתמשת במערכת הספרייה! להתראות.");
                System.exit(0);
                break;
            default:
                System.out.println("אפשרות לא תקינה, אנא נסה שוב.");
        }
    }
    private static void handleRegister() {
        System.out.println("\n--- יצירת חשבון חדש ---");
        System.out.print("הכנס שם משתמש: ");
        if (!scanner.hasNextLine()) return;
        String username = scanner.nextLine().trim();
        System.out.print("הכנס סיסמה (חייבת לכלול ספרה ותו מתוך $, %, _): ");
        if (!scanner.hasNextLine()) return;
        String password = scanner.nextLine().trim();
        System.out.print("הכנס מספר תעודת זהות (9 ספרות בלבד): ");
        if (!scanner.hasNextLine()) return;
        String id = scanner.nextLine().trim();
        System.out.print("בחר סוג מנוי (1 - רגיל [עד 3 ספרים], 2 - פרימיום [עד 10 ספרים]): ");
        if (!scanner.hasNextLine()) return;
        String subChoice = scanner.nextLine().trim();
        boolean isPremium = subChoice.equals("2");
        User u = system.createUser(username, password, id, isPremium);
        if (u != null) {
            System.out.println("✓ החשבון נוצר בהצלחה! כעת תוכל להתחבר.");
        } else {
            System.out.println("שגיאה ביצירת החשבון.");
        }
    }
    private static void handleLogin() {
        System.out.println("\n--- התחברות למערכת ---");
        System.out.print("שם משתמש: ");
        if (!scanner.hasNextLine()) return;
        String username = scanner.nextLine().trim();
        System.out.print("סיסמה: ");
        if (!scanner.hasNextLine()) return;
        String password = scanner.nextLine().trim();
        User user = system.login(username, password);
        if (user != null) {
            System.out.println("✓ התחברת בהצלחה! ברוך הבא " + user.getUsername());
            showUserMenu(user);
        } else {
            System.out.println("שם משתמש או סיסמה שגויים.");
        }
    }
    private static void showUserMenu(User u) {
        while (true) {
            System.out.println("\n==================================================");
            System.out.println(" שלום " + u.getUsername() + " (" + (u.isPremium() ? "חבר פרימיום" : "חבר רגיל") + ") | ספרים בסל: " + u.getCartCount() + "/" + u.getMaxAllowedBooks());
            System.out.println("==================================================");
            System.out.println("1 — הוספת ספר חדש לאוסף");
            System.out.println("2 — הסרת ספר מהאוסף");
            System.out.println("3 — הצגת כל הספרים שאני מחזיק כרגע");
            System.out.println("4 — התנתקות");
            System.out.print("בחר אפשרות (1-4): ");
            if (!scanner.hasNextLine()) return;
            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1":
                    handleAddBook(u);
                    break;
                case "2":
                    handleRemoveBook(u);
                    break;
                case "3":
                    showUserBooks(u);
                    break;
                case "4":
                    System.out.println("התנתקת בהצלחה מהמערכת.");
                    return;
                default:
                    System.out.println("אפשרות לא תקינה.");
            }
        }
    }
    private static void handleAddBook(User u) {
        System.out.println("\n--- הוספת ספר חדש לאוסף ---");
        System.out.print("כותרת הספר: ");
        if (!scanner.hasNextLine()) return;
        String title = scanner.nextLine().trim();
        System.out.print("תיאור קצר: ");
        if (!scanner.hasNextLine()) return;
        String desc = scanner.nextLine().trim();
        System.out.print("ז'אנר: ");
        if (!scanner.hasNextLine()) return;
        String genre = scanner.nextLine().trim();
        System.out.print("מספר עמודים: ");
        if (!scanner.hasNextLine()) return;
        int pages = Integer.parseInt(scanner.nextLine().trim());
        System.out.print("שם המחבר: ");
        if (!scanner.hasNextLine()) return;
        String author = scanner.nextLine().trim();
        boolean ok = system.addBook(u, title, desc, genre, pages, author);
        if (ok) {
            System.out.println("✓ הספר '" + title + "' הוסף בהצלחה למערכת ולסל שלך!");
        } else {
            System.out.println("שגיאה בהוספת הספר.");
        }
    }
    private static void handleRemoveBook(User u) {
        System.out.println("\n--- הסרת ספר מהאוסף ---");
        showUserBooks(u);
        if (u.getCartCount() == 0) return;
        System.out.print("בחר את מספר הספר להסרה (1-" + u.getCartCount() + "): ");
        if (!scanner.hasNextLine()) return;
        int idx = Integer.parseInt(scanner.nextLine().trim()) - 1;
        boolean ok = system.removeBook(u, idx);
        if (ok) {
            System.out.println("✓ הספר הוסר בהצלחה מהאוסף וממערכת הספרייה!");
        } else {
            System.out.println("מספר ספר לא תקין.");
        }
    }
    private static void showUserBooks(User u) {
        if (u.getCartCount() == 0) {
            System.out.println("אין לך ספרים באוסף כרגע.");
            return;
        }
        System.out.println("=== הספרים שלך (סה\"כ: " + u.getCartCount() + ") ===");
        for (int i = 0; i < u.getCartCount(); i++) {
            System.out.println((i + 1) + ". " + u.getCart()[i]);
        }
    }
}

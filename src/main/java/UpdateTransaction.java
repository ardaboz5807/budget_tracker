import java.util.Scanner;

// Zuständig für das Bearbeiten vorhandener Transaktionen.
public class UpdateTransaction {
    Scanner scanner;
    Database dtb;

    // Übernimmt Scanner und Datenbank aus der Homepage.
    public UpdateTransaction(Scanner scanner, Database dtb){
        this.scanner = scanner;
        this.dtb = dtb;
    }

    // Fragt die ID ab und lässt einzelne oder alle Werte bearbeiten.
    public void start() {
        System.out.println("========================================");
        System.out.println("         TRANSAKTION BEARBEITEN         ");
        System.out.println("========================================");
        System.out.print("ID der Transaktion: ");
        int id = scanner.nextInt();
        try {
            Transaction transaction = dtb.getTransaction(id);
            boolean valid = true;
            System.out.println();
            System.out.println("Was möchtest du ändern?");
            System.out.println();
            System.out.println("1. Typ");
            System.out.println("2. Betrag");
            System.out.println("3. Beschreibung");
            System.out.println("4. Datum");
            System.out.println("5. Alles");
            System.out.println();
            System.out.println("6. Beenden");
            System.out.print("Auswahl: ");
            while (valid) {
                int choice = scanner.nextInt();
                scanner.nextLine(); // Enter nach nextInt() entfernen
                switch (choice) {
                    case 1:
                        System.out.print("Neuer Typ: ");
                        transaction.type = scanner.nextLine();
                        valid = false;
                        break;
                    case 2:
                        System.out.print("Neuer Betrag: ");
                        transaction.amount = scanner.nextDouble();
                        scanner.nextLine(); // Enter entfernen
                        valid = false;
                        break;
                    case 3:
                        System.out.print("Neue Beschreibung: ");
                        transaction.description = scanner.nextLine();
                        valid = false;
                        break;
                    case 4:
                        System.out.print("Neues Datum: ");
                        transaction.date = scanner.nextLine();
                        valid = false;
                        break;
                    case 5:
                        System.out.println("Bitte alle neuen Werte eingeben:");
                        System.out.print("Typ: ");
                        transaction.type = scanner.nextLine();
                        System.out.print("Betrag: ");
                        transaction.amount = scanner.nextDouble();
                        scanner.nextLine(); // ganz wichtig
                        System.out.print("Beschreibung: ");
                        transaction.description = scanner.nextLine();
                        System.out.print("Datum: ");
                        transaction.date = scanner.nextLine();
                        valid = false;
                        break;
                    case 6:
                        return;
                    default:
                        System.out.println("Ungültige Auswahl. Bitte eine Zahl zwischen 1 und 6 eingeben.");
                        break;
                }
            }
            dtb.update(id, transaction.type, transaction.amount, transaction.description, transaction.date);
        }
        catch (Exception e) {
            System.out.println("Fehler beim Bearbeiten der Transaktion. Bitte erneut versuchen.");
        }
    }
}

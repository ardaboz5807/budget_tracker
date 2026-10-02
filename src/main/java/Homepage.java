import java.util.Scanner;

// Steuert das Hauptmenü und zeigt die wichtigsten Budgetwerte an.
public class Homepage {
    public Scanner scanner = new Scanner(System.in);

    // Hintergrundklassen für Datenbank und Transaktionen
    Database dtb = new Database();
    AddTransaction addTransaction = new AddTransaction(scanner,dtb);
    DeleteTransaction deleteTransaction = new DeleteTransaction(scanner,dtb);
    UpdateTransaction updateTransaction = new UpdateTransaction(scanner,dtb);

    // Werte für die aktuelle Übersicht
    public double balance;
    public double inputThisMonth;
    public double outputThisMonth;

    // Startet das Hauptmenü des Budget Trackers.
    public void start() {
        boolean firstDisplay = true;

        while (true) {
            inputThisMonth = dtb.getTypeAmount("Einnahme");
            outputThisMonth = dtb.getTypeAmount("Ausgabe");
            balance = inputThisMonth - outputThisMonth;

            if(firstDisplay){
                System.out.println("========================================");
                System.out.println("             BUDGET TRACKER             ");
                System.out.println("========================================");
                System.out.println();
                System.out.println("Kontostand : " + balance + " €");
                System.out.println("Einnahmen  : " + inputThisMonth + " €");
                System.out.println("Ausgaben   : " + outputThisMonth + " €");
                System.out.println();
                firstDisplay = false;
            }

            System.out.println("--------------- HAUPTMENÜ ---------------");
            System.out.println();
            System.out.println("1. Transaktion hinzufügen");
            System.out.println("2. Transaktionen anzeigen");
            System.out.println("3. Transaktion bearbeiten");
            System.out.println("4. Transaktion löschen");
            System.out.println("5. Monatsübersicht");
            System.out.println();
            System.out.println("6. Beenden");
            System.out.println();
            System.out.println("----------------------------------------");
            System.out.print("Auswahl: ");
            switch(scanner.nextInt()){
                case 1:
                    addTransaction.start();
                    break;
                case 2:
                    dtb.printTable();
                    break;
                case 3:
                    updateTransaction.start();
                    break;
                case 4:
                    deleteTransaction.start();
                    break;
                case 5:
                    System.out.println("========================================");
                    System.out.println("           MONATSÜBERSICHT              ");
                    System.out.println("========================================");
                    System.out.println("Kontostand : " + balance + " €");
                    System.out.println("Einnahmen  : " + inputThisMonth + " €");
                    System.out.println("Ausgaben   : " + outputThisMonth + " €");
                    System.out.println();
                    System.out.println("----------------------------------------");
                    break;
                case 6:
                    System.out.println("Budget Tracker wird beendet.");
                    return;
                default:
                    System.out.println("Ungültige Auswahl. Bitte eine Zahl zwischen 1 und 6 eingeben.");
                    break;
            }
        }
    }
}

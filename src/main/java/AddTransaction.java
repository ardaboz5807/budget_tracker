import java.util.Scanner;

// Zuständig für das Hinzufügen neuer Einnahmen und Ausgaben.
public class AddTransaction {

    // Benötigte Objekte und Auswahlwert
    Scanner scanner;
    Database dtb;
    int decider = 0;

    // Übernimmt Scanner und Datenbank aus der Homepage.
    public AddTransaction(Scanner scanner, Database dtb) {
        this.scanner = scanner;
        this.dtb = dtb;
    }

    // Zeigt die Auswahl für Einnahme oder Ausgabe an.
    public void start() {
        System.out.println("========================================");
        System.out.println("        TRANSAKTION HINZUFÜGEN          ");
        System.out.println("========================================");
        System.out.println("1. Einnahme");
        System.out.println("2. Ausgabe");
        System.out.println();
        System.out.println("3. Zurück");

        boolean valid = true;
        while (valid) {
            System.out.print("Auswahl: ");
            int input = scanner.nextInt();
            if (input == 1) {
                valid = false;
                decider = 1;
                inp();
            }
            else if (input == 2) {
                valid = false;
                decider = 2;
                inp();
            }
            else if (input == 3){
                return;
            }
            else{
                System.out.println("Ungültige Auswahl. Bitte eine Zahl zwischen 1 und 3 eingeben.");
            }
        }
    }

    // Fragt die Transaktionsdaten ab und speichert sie in der Datenbank.
    public void inp(){
        try{
            String typ;
            if(decider == 1){
                typ = "EINNAHME";
            }
            else{
                typ = "AUSGABE";
            }
            System.out.print("Betrag in €: ");
            int amount = scanner.nextInt();
            scanner.nextLine();  //Um das Enter zu löschen bei amount
            if(decider == 1){
                System.out.print("Beschreibung (z. B. Gehalt): ");
            }
            else{
                System.out.print("Beschreibung (z. B. Tanken, Essen): ");
            }
            String description = scanner.nextLine();
            System.out.print("Datum: ");
            String date = scanner.nextLine();
            dtb.addInput(typ,amount,description,date);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}

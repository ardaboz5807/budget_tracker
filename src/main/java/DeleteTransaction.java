import java.util.Scanner;

// Zuständig für das Löschen einzelner oder aller Transaktionen.
public class DeleteTransaction {
    Database dtb;
    Scanner scanner;

    // Übernimmt Scanner und Datenbank aus der Homepage.
    public DeleteTransaction(Scanner scanner, Database dtb){
        this.dtb = dtb;
        this.scanner = scanner;
    }

    // Zeigt die Auswahl für das Löschen von Transaktionen an.
    public void start(){
        System.out.println("========================================");
        System.out.println("          TRANSAKTION LÖSCHEN           ");
        System.out.println("========================================");
        System.out.println("1. Bestimmte Transaktion löschen");
        System.out.println("2. Alle Transaktionen löschen");
        System.out.println();
        System.out.println("3. Beenden");
        System.out.print("Auswahl: ");
        boolean valid = true;
        while(valid){
            switch(scanner.nextInt()){
                case 1:
                    deleteTransaction();
                    valid = false;
                    break;
                case 2:
                    deleteAllTransaction();
                    valid = false;
                    break;
                case 3:
                    return;
            }
        }
    }

    // Löscht eine bestimmte Transaktion anhand ihrer ID.
    public void deleteTransaction(){
        System.out.print("ID der Transaktion: ");
        int id = scanner.nextInt();
        try{
            dtb.deleteOne(id);
            System.out.println("Transaktion mit der ID " + id + " erfolgreich gelöscht.");
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    // Löscht alle Transaktionen aus der Datenbank.
    public void deleteAllTransaction(){
        try{
            dtb.deleteAll();
            System.out.println("Alle Transaktionen wurden erfolgreich gelöscht.");
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}

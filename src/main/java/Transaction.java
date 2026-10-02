// Speichert die Daten einer einzelnen Transaktion als Objekt.
// Wird nur für getTransaction bei Database benutzt, einfacher darzustellen
public class Transaction {
    public int id;
    public String type;
    public double amount;
    public String description;
    public String date;

    public Transaction(int id, String type, double amount, String description, String date){
        this.id = id;
        this.type = type;
        this.amount = amount;
        this.description = description;
        this.date = date;
    }
}

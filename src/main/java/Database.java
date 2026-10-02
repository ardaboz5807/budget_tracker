import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

// Kümmert sich um alle Zugriffe auf die SQLite-Datenbank.
public class Database {

    public static String url = "jdbc:sqlite:databaseTracker.db";

    // Erstellt beim Start die benötigte Tabelle.
    public Database(){
        createTable();
    }

    // Erstellt die Account-Tabelle, falls sie noch nicht existiert.
    public void createTable(){
        String sql = "CREATE TABLE IF NOT EXISTS account (id integer PRIMARY KEY, type text, amount real, description text, date text)";
        try(Connection conn = DriverManager.getConnection(url);
            Statement stmt = conn.createStatement()){
            stmt.execute(sql);
            System.out.println("Datenbank erfolgreich geladen.");
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }

    // Fügt eine neue Transaktion in die Datenbank ein.
    public void addInput(String type, double amount, String description, String date){
        String sql = "INSERT INTO account (type,amount,description,date) VALUES (?,?,?,?)";
        try(Connection conn = DriverManager.getConnection(url);
            PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1,type);
            stmt.setDouble(2,amount);
            stmt.setString(3,description);
            stmt.setString(4,date);
            stmt.execute();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }

    // Gibt eine bestimmte Transaktion anhand ihrer ID zurück.
    public Transaction getTransaction(int id){
        String sql = "SELECT * FROM account WHERE id = ?";
        try(Connection conn = DriverManager.getConnection(url);
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1,id);
            ResultSet rs = stmt.executeQuery();
            Transaction transaction = null;
            if(rs.next()){
                transaction = new Transaction(id,rs.getString("type"),rs.getDouble("amount"),rs.getString("description"),rs.getString("date"));
            }
            return transaction;
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    // Gibt alle gespeicherten Transaktionen in der Konsole aus.
    public void printTable(){
        String sql = "SELECT * FROM account";
        try(Connection conn = DriverManager.getConnection(url);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)){   //Gibt die Liste zurück
            System.out.println("========================================");
            System.out.println("             TRANSAKTIONEN              ");
            System.out.println("========================================");
            while(rs.next()){
                System.out.println(
                        "ID: " + rs.getInt("id")
                                + " | Typ: " + rs.getString("type")
                                + " | Betrag: " + rs.getDouble("amount") + " €"
                                + " | Beschreibung: " + rs.getString("description")
                                + " | Datum: " + rs.getString("date")
                );
            }
            System.out.println("----------------------------------------");
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }

    // Aktualisiert eine vorhandene Transaktion anhand ihrer ID.
    public void update(int id, String type, double amount, String description, String date){
        String sql = "UPDATE account SET type = ?, amount = ?, description = ?, date = ? WHERE id = ?";
        try(Connection conn = DriverManager.getConnection(url);
            PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1, type);
            stmt.setDouble(2, amount);
            stmt.setString(3, description);
            stmt.setString(4, date);
            stmt.setInt(5, id);
            stmt.execute();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }

    // Löscht eine bestimmte Transaktion anhand ihrer ID.
    public void deleteOne(int id){
        String sql = "DELETE FROM account WHERE id = ?";
        try(Connection conn = DriverManager.getConnection(url);
            PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setInt(1,id);
            stmt.execute();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }

    // Löscht alle gespeicherten Transaktionen.
    public void deleteAll(){
        String sql = "DELETE FROM account";
        try(Connection conn = DriverManager.getConnection(url);
            Statement stmt = conn.createStatement()){
            stmt.execute(sql);
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }

    // Addiert alle Beträge eines bestimmten Transaktionstyps.
    public double getTypeAmount(String target){
        String sql = "SELECT type, amount FROM account";
        double res = 0;
        try(Connection conn = DriverManager.getConnection(url);
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()){
            while(rs.next()){
                String type = rs.getString("type");
                double amount = rs.getDouble("amount");
                if(target.equalsIgnoreCase(type)){
                    res += amount;
                }
            }
            return res;
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return res;
    }
}

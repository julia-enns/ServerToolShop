package Server.Database;

import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.*;
import java.util.Calendar;

public class MySQL implements DatabaseCreds {

    private Connection conn;
    private ResultSet rs;



    public void connect(){

        try{
            Driver driver = new com.mysql.cj.jdbc.Driver();
            DriverManager.registerDriver(driver);
            conn = DriverManager.getConnection(DB_URL, USERNAME,PASSWORD);
        }catch(SQLException e){
            System.out.println("Connection failed");
        }

    }


    public void closeConnection(){
        try{
            rs.close();
            conn.close();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertToolPrepared(int id, String name, int quant , double price, int supplierID){
        try{
           String query  = "INSERT INTO TOOL (ID, name, quantity, price, supplierid) values (?,?,?,?,?)";
           PreparedStatement pState = conn.prepareStatement(query);
           pState.setInt(1,id);
           pState.setString(2,name);
           pState.setInt(3,quant);
           pState.setDouble(4,price);
           pState.setInt(5,supplierID);
           int rowCount = pState.executeUpdate();
           pState.close();
        }catch (SQLException e){

        }
    }

    public void insertSupplier(int supId, String supName, String supAdd, String supContact ){
        try{
            String query  = "INSERT INTO SUPPLIER (ID, name, address, contact) values (?,?,?,?)";
            PreparedStatement pState = conn.prepareStatement(query);
            pState.setInt(1,supId);
            pState.setString(2,supName);
            pState.setString(3,supAdd);
            pState.setString(4,supContact);
            int rowCount = pState.executeUpdate();
            pState.close();
        }catch (SQLException e){

        }

    }

    public void createTables(){
        createToolTable();
        createSupplierTable();
        createOrderTable();
    }

    private void createOrderTable(){
        try{
            DatabaseMetaData meta = conn.getMetaData();
            ResultSet rs = meta.getTables(null, null, "toolorder", null );
            if(rs.next()==false) {
                String sql = "CREATE TABLE toolorder " + "(date VARCHAR(255), " + "orderid INTEGER not NULL, " +
                        "toolname VARCHAR(255), " + "quantity INTEGER not NULL, " + "PRIMARY KEY (orderid))";
                Statement st = conn.createStatement();
                st.executeUpdate(sql);
                st.close();
                System.out.println("Order table created");
            }
            else
                System.out.println("Order tool already exists");
        }catch (SQLException e){
            System.out.println("Cant create order table");
            e.printStackTrace();
        }

    }

    private void createToolTable(){
        try{
        DatabaseMetaData meta = conn.getMetaData();
        ResultSet rs = meta.getTables(null, null, "tool", null );
        if(rs.next()==false) {

            String sql = "CREATE TABLE tool " + "(id INTEGER not NULL, " + "name VARCHAR(255), " +
                    "quantity INTEGER not NULL, " + "price DOUBLE, " + "supplierid INTEGER not NULL, " + "PRIMARY KEY (id))";

            Statement st = conn.createStatement();
            st.executeUpdate(sql);
            st.close();
            System.out.println("Tool table created");
        }
        else
            System.out.println("Table tool already exists");
        }catch (SQLException e){
            System.out.println("Cant create tool table");
        }

    }

    private void createSupplierTable(){
        try{
            DatabaseMetaData meta = conn.getMetaData();
            ResultSet rs = meta.getTables(null, null, "supplier", null );
            if(rs.next()==false) {
                String sql = "CREATE TABLE supplier " + "(id INTEGER not NULL, " + "name VARCHAR(255), " +
                        "address VARCHAR(255), " + "contact VARCHAR(255), " + "PRIMARY KEY (id))";

                Statement st = conn.createStatement();
                st.executeUpdate(sql);
                st.close();
                System.out.println("Supplier table created");
            }
            else
                System.out.println("Table supplier already exists");
        }catch (SQLException e){
            System.out.println("Cant create supplier table");
        }

    }

    public String printAllTools(){
        String s = "";
        try{
            Statement stmt = conn.createStatement();
            String query = "SELECT * FROM tool";
            rs=stmt.executeQuery(query);
            while(rs.next()){
                s+= rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3)
                 + " " + rs.getString(4) + " " + rs.getString(5) + "\n";
            }
            stmt.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return s;
    }

    public String getTool(String name){
        String s = "";
        try{
            String query = "SELECT * FROM tool where name= ?";
            PreparedStatement pStmt = conn.prepareStatement(query);
            pStmt.setString(1, name);
            rs= pStmt.executeQuery();
            while (rs.next()){
                s+= rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3)
                        + " " + rs.getString(4) + " " + rs.getString(5);
            }
            pStmt.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return s;
    }

    public String getTool(int id){
        String s = "";
        try{
            String query = "SELECT * FROM tool where id= ?";
            PreparedStatement pStmt = conn.prepareStatement(query);
            pStmt.setInt(1, id);
            rs= pStmt.executeQuery();
            while (rs.next()){
                s+= rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3)
                        + " " + rs.getString(4) + " " + rs.getString(5);
            }
            pStmt.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return s;
    }

    public String getQuantity(String name) {
        String s = "";
        try {
            String query = "SELECT * FROM tool where name= ?";
            PreparedStatement pStmt = conn.prepareStatement(query);
            pStmt.setString(1, name);
            rs = pStmt.executeQuery();
            while (rs.next()) {
                s += "The quantity for "+ rs.getString(2) +" is: " + rs.getString(3) + "\n";
            }
            pStmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(s);
        return s;
    }

    public int getQuantityInt(String name){
        try {
            String query = "SELECT * FROM tool where name= ?";
            PreparedStatement pStmt = conn.prepareStatement(query);
            pStmt.setString(1, name);
            rs = pStmt.executeQuery();
            while (rs.next()) {
                return Integer.parseInt(rs.getString(3));
            }
            pStmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    private void increaseItem(String name, int amount){

        try {
            String query = "SELECT * FROM tool where name= ?";
            PreparedStatement pStmt = conn.prepareStatement(query);
            pStmt.setString(1, name);
            rs = pStmt.executeQuery();
            while (rs.next()) {
                String query2 = "UPDATE tool SET quantity= ? WHERE name= ?";
                PreparedStatement pStmt2 = conn.prepareStatement(query2);
                pStmt2.setInt(1, Integer.parseInt(rs.getString(3))+amount);
                pStmt2.setString(2,name);
                pStmt2.executeUpdate();
                pStmt2.close();
            }
            pStmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public String decreaseItem(String name){
        int quantityCurrent;
        try {
            String query = "SELECT * FROM tool where name= ?";
            PreparedStatement pStmt = conn.prepareStatement(query);
            pStmt.setString(1, name);
            rs = pStmt.executeQuery();
            quantityCurrent = Integer.parseInt(rs.getString(3));
            while (rs.next()) {
                String query2 = "UPDATE tool SET quantity= ? WHERE name= ?";
                PreparedStatement pStmt2 = conn.prepareStatement(query2);
                pStmt2.setInt(1, Integer.parseInt(rs.getString(3))-1);
                pStmt2.setString(2,name);
                pStmt2.executeUpdate();
                pStmt2.close();
            }
            pStmt.close();
            if(quantityCurrent < 21 ){
                System.out.println("Making order");
                makeOrder(name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "Quantity failed to be decreased.";
        }
        return "Quantity successfully decreased.";
    }

    private void makeOrder(String name){
        try{
            String query  = "INSERT INTO toolorder (date, orderid, toolname, quantity) values (?,?,?,?)";
            PreparedStatement pState = conn.prepareStatement(query);
            pState.setString(1,Calendar.getInstance().getTime().toString() );
            pState.setInt(2,  (int)(Math.random() * (99999) + 1));
            pState.setString(3,name);
            pState.setInt(4,40);
            int rowCount = pState.executeUpdate();
            System.out.println("Order made");
            pState.close();
            increaseItem(name, 40);
        }catch (SQLException e){

        }


    }

    public String buyItem(String name, int amount){
            int currentStock = getQuantityInt(name);
            if( currentStock>amount){
                try {
                    String query = "SELECT * FROM tool where name= ?";
                    PreparedStatement pStmt = conn.prepareStatement(query);
                    pStmt.setString(1, name);
                    rs = pStmt.executeQuery();
                    while (rs.next()) {
                        String query2 = "UPDATE tool SET quantity= ? WHERE name= ?";
                        PreparedStatement pStmt2 = conn.prepareStatement(query2);
                        pStmt2.setInt(1, Integer.parseInt(rs.getString(3))-amount);
                        pStmt2.setString(2,name);
                        pStmt2.executeUpdate();
                        pStmt2.close();
                        if(Integer.parseInt(rs.getString(3) ) < 20 ){
                            makeOrder(name);
                        }
                    }
                    pStmt.close();
                    return "Successfully purchased " + amount + " of " + name;
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
            else
                return "Not enough in stock to purchase " + amount;
            return "";
    }

    public String printOrder(){
        String s = "";
        try{
            Statement stmt = conn.createStatement();
            String query = "SELECT * FROM toolorder";
            rs=stmt.executeQuery(query);
            while(rs.next()){
                s+= rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3)
                        + " " + rs.getString(4) + "\n ----------------------------------- \n";
            }
            stmt.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        if(s.equals("")){
            return "No orders made currently.";
        }
        return s;
    }
}



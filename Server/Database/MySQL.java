package Server.Database;

import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.*;

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
           System.out.println("Row count: " + rowCount);
           pState.close();
        }catch (SQLException e){
            System.err.println("Error inserting tool");
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
            System.err.println("Error inserting supplier");
        }

    }

    public void createTables(){
        createToolTable();
        createSupplierTable();
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


}



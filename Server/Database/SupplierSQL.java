package Server.Database;

import java.sql.*;

/**
 * Supplier database
 */
public class SupplierSQL extends MySQL {

    /**
     * Creates table of suppliers
     */
    public void createSupplierTable(){
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

    /**
     * Inserts supplier
     * @param supId supplier ID
     * @param supName supplier name
     * @param supAdd supplier address
     * @param supContact supplier contact
     */
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
        }catch (SQLException e){ }
    }
}

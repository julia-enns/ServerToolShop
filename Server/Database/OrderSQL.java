package Server.Database;

import java.sql.*;
import java.util.Calendar;

/**
 * Makes database for order
 */
public class OrderSQL extends MySQL {

    /**
     * Makes order
     * @param name name of tool
     * @param toolSQL database for tools
     */
    public void makeOrder(String name, ToolSQL toolSQL){
        try{
            String query  = "INSERT INTO toolorder (date, orderid, toolname, quantity) values (?,?,?,?)";
            PreparedStatement pState = conn.prepareStatement(query);
            pState.setString(1, Calendar.getInstance().getTime().toString() );
            pState.setInt(2,  (int)(Math.random() * (99999) + 1));
            pState.setString(3,name);
            pState.setInt(4,40);
            int rowCount = pState.executeUpdate();
            System.out.println("Order made");
            pState.close();

        }catch (SQLException e){

        }
        increaseItem(name, 40, toolSQL);
    }

    /**
     * Creates order table
     */
    public void createOrderTable(){
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

    /**
     * Prints order
     * @return order
     */
    public String printOrder(){
        String s = "";
        try{
            Statement stmt = conn.createStatement();
            String query = "SELECT * FROM toolorder";
            rs=stmt.executeQuery(query);
            while(rs.next()){
                s+= "Date: " + rs.getString(1) + "\n\n" + rs.getString(2) + ":  " + rs.getString(3)
                        + " " + rs.getString(4) + "\n ----------------------------------- \n";
            }
            stmt.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        if(s.equals("")){
            return "There are currently no orderlines made";
        }
        return s;
    }

    /**
     * Increase stock if below 20
     * @param name tool name
     * @param amount tool amount increases
     * @param toolSQL tool database
     */
    private void increaseItem(String name, int amount, ToolSQL toolSQL){
        try {
            String query2 = "UPDATE tool SET quantity = ? WHERE name= ?";
            PreparedStatement pStmt2 = conn.prepareStatement(query2);
            pStmt2.setInt(1, toolSQL.getQuantityInt(name) + amount);
            pStmt2.setString(2,name);
            pStmt2.executeUpdate();
            pStmt2.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


import java.io.File;
import java.sql.*;

import com.informix.jdbc.IfxDriver;

public class DBConnection {

String url;
String username;
String password;
static Connection connection;
static Statement stmt = null;
static ResultSet rs ;
static PreparedStatement pstmt;

    public static void main(String[] args) throws Exception {
        DBConnection dbConnection = new DBConnection();

            String colName = dbConnection.selectQuery();
            System.out.println(colName);
            System.out.println(BaseHelper.filePath);

            System.out.println(System.getenv());
//            updateQuery();
       }

       public  String selectQuery() throws SQLException {

        String loc_code = null;

           try{
               Class.forName("com.informix.jdbc.IfxDriver");

               url = BaseHelper.readPropertyFile().getProperty("informix_db_url");
               username=BaseHelper.readPropertyFile().getProperty("qa_Informix_userName");
               password=BaseHelper.readPropertyFile().getProperty("qa_Informix_password");

               connection = DriverManager.getConnection(url,username,password);

               stmt = connection.createStatement();

               rs = stmt.executeQuery("select first 1 loc_code from informix.location where loc_part not in (\"RESLOTTED\")");

               while(rs.next()){
                 loc_code = rs.getString("loc_code");
               }
           } catch (Exception e) {
              e.printStackTrace();
           }finally{
                rs.close();
                stmt.close();
                connection.close();
           }
           return loc_code;
    }

    public void updateQuery() throws SQLException {

        String updateQuery = null;
        String loc_qty_units, loc_min, loc_max = null;

        try{
            Class.forName("com.informix.jdbc.IfxDriver");

            connection = DriverManager.getConnection(url,username,password);

            updateQuery =  "update informix.location set loc_qty_units = ?,loc_min = ?, loc_max = ? where loc_code='LTC0501A'";

            pstmt = connection.prepareStatement(updateQuery);
            pstmt.setDouble(1, 50);
            pstmt.setDouble(2, 20);
            pstmt.setDouble(3, 100);

            int rowUpdate = pstmt.executeUpdate();

//          rs = stmt.executeQuery("select loc_qty_units, loc_min, loc_max from informix.location where loc_code in ('LTC0501A')");
//
//            while(rs.next()){
//                loc_qty_units = rs.getString("loc_qty_units");
//                loc_min = rs.getString("loc_min");
//                loc_max = rs.getString("loc_max");
//            }

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }finally{
            pstmt.close();
            connection.close();
        }
    }

    public void insertQuery() throws SQLException{

        String insertQuery = null;
        try{
            Class.forName("com.informix.jdbc.IfxDriver");

            connection = DriverManager.getConnection(url,username,password);

            insertQuery =  "update informix.location set loc_qty_units = ?,loc_min = ?, loc_max = ? where loc_code='LTC0501A'";

            pstmt = connection.prepareStatement(insertQuery);
            pstmt.setDouble(1, 50);
            pstmt.setDouble(2, 20);
            pstmt.setDouble(3, 100);

            int rowUpdate = pstmt.executeUpdate();

//          rs = stmt.executeQuery("select loc_qty_units, loc_min, loc_max from informix.location where loc_code in ('LTC0501A')");
//
//            while(rs.next()){
//                loc_qty_units = rs.getString("loc_qty_units");
//                loc_min = rs.getString("loc_min");
//                loc_max = rs.getString("loc_max");
//            }

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }finally{
            pstmt.close();
            connection.close();
        }
    }
}


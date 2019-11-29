package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
    public static Connection cn;
    public  DbConnection(){

    }
    public static Connection myConnection(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            cn = DriverManager.getConnection("jdbc:mysql://localhost/bulletinboard", "root","");
            return  cn;

        } catch ( SQLException  | ClassNotFoundException e1) {
            System.out.println(e1);
            System.out.println("error connecting database");
        }catch (Exception e){
            System.out.println("Exception"+e);
        }
        return null;
    }



}

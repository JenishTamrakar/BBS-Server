package daoImpl;

import com.sun.org.apache.xpath.internal.operations.Bool;
import dao.LoginDao;
import utils.DbConnection;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDaoImpl extends UnicastRemoteObject implements LoginDao{
    Connection cn = DbConnection.myConnection();
    public LoginDaoImpl() throws RemoteException{
        super();
    }

    @Override
    public Boolean checkUser(String user_id, String user_password) throws RemoteException{
        try {
            System.out.println("Login method called");
            String sql ="SELECT * FROM user WHERE user_id =? and user_password=?";
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1,user_id);
            ps.setString(2,user_password);
            ResultSet rs = ps.executeQuery();
            return  rs.next();

        } catch (Exception e) {
            System.out.println("exception"+e);
            return null;
        }
    }
}

package daoImpl;

import com.sun.org.apache.xpath.internal.operations.Bool;
import dao.LoginDao;
import utils.DbConnection;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
            String sql ="SELECT user_id, user_password form user where user_id =? and user_password=?";

            PreparedStatement ps = cn.prepareStatement((sql));

        } catch (Exception e) {

            System.out.println("exception"+e);
        }
        return null;
    }
}

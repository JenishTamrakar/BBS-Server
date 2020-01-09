/**
 * @author Roshan Shrestha
 * This is the implementation of the interface RegisterDao.
 */

package daoImpl;

import bll.Register;
import dao.RegisterDao;
import utils.DbConnection;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RegisterDaoImpl extends UnicastRemoteObject implements RegisterDao {
    //database connection
    Connection cn = DbConnection.myConnection();

    //constructor
    public  RegisterDaoImpl()throws RemoteException{
        super();
    }

    /**
     * add user data into the user table
     * @param r
     * @throws RemoteException
     */
    @Override
    public void addUser(Register r)throws RemoteException{
        System.out.print(r.getUID());
        System.out.print(r.getPassword());

        try {
            String sql = "INSERT INTO user(user_id, user_password, user_role) VALUES(?,?,?)";
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1, r.getUID());
            ps.setString(2, r.getPassword());
            ps.setString(3, r.getUserType());
            ps.executeUpdate();
        }
        catch (SQLException e){
            System.out.println(e);
        }
    }

    /**
     * update user password into the user table
     * @param user_id
     * @param user_password
     * @throws RemoteException
     */
    public void updatePassword(String user_id, String user_password) throws RemoteException
    {
        try
        {
            String sql = "UPDATE user set user_password = ? where user_id = ?";
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1, user_password );
            ps.setString(2, user_id);
            ps.executeUpdate();
        }
        catch (SQLException e)
        {
            System.out.println(e);
        }
    }

}

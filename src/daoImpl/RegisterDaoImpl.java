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
    Connection cn = DbConnection.myConnection();
    public  RegisterDaoImpl()throws RemoteException{
        super();
    }
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

}

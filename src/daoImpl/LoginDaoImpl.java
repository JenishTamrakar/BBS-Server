package daoImpl;

import com.sun.org.apache.xpath.internal.operations.Bool;
import com.sun.rowset.CachedRowSetImpl;
import dao.LoginDao;
import utils.DbConnection;

import javax.sql.rowset.CachedRowSet;
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
    public ResultSet checkUser(String user_id,String user_password) throws RemoteException{
        try {
            System.out.println("Login method called");
            String sql ="SELECT * from user";
            PreparedStatement ps = cn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            CachedRowSetImpl crs= new CachedRowSetImpl();
            crs.populate(rs);
            return crs;

    } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ResultSet getStudentInfo(int uid) throws  RemoteException{
        try{
            String sql = "Select * form student where UID =? ";
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1, String.valueOf(uid));
            ResultSet rs = ps.executeQuery();
            CachedRowSetImpl crc= new CachedRowSetImpl();
            crc.populate(rs);
            return crc;

        }catch (Exception e){

            System.out.println(e);
        }return  null;
    }
}

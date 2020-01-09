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
    //datbase connection
    Connection cn = DbConnection.myConnection();

    //constructor
    public LoginDaoImpl() throws RemoteException{
        super();
    }

    /**
     * check user id and password from the user table
     * @param user_id
     * @param user_password
     * @return
     * @throws RemoteException
     */
    @Override
    public ResultSet checkUser(String user_id,String user_password) throws RemoteException{
        try {
            System.out.println("Login method called");
            String sql ="SELECT * from user where user_id=? and user_password=?";
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1,user_id);
            ps.setString(2,user_password);
            ResultSet rs = ps.executeQuery();
            CachedRowSetImpl crs= new CachedRowSetImpl();
            crs.populate(rs);
            return crs;

    } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * select student from the student id
     * @param uid
     * @return
     * @throws RemoteException
     */
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

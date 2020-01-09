/**
 * @author
 * This is the implementation of the interface signUpDao
 */

package daoImpl;

import com.sun.rowset.CachedRowSetImpl;
import dao.signUpDao;
import utils.DbConnection;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class signUpDaoImpl extends UnicastRemoteObject implements signUpDao {

    //database connection
    Connection cn = DbConnection.myConnection();

    //constructor
    public signUpDaoImpl()throws RemoteException{
        super();
    }

    /**
     * check user id from user table
     * @param user_id
     * @return
     * @throws RemoteException
     */
    @Override
    public ResultSet checkID(String user_id)throws RemoteException{
        try {
            System.out.printf("uid checking");
            ResultSet rs = cn.createStatement().executeQuery("SELECT * from user");
            CachedRowSetImpl crsi = new CachedRowSetImpl();
            crsi.populate(rs);
            return crsi;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
}

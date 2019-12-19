package daoImpl;

import bll.viewFeedback;
import com.sun.rowset.CachedRowSetImpl;
import dao.viewFeedbackDao;
import utils.DbConnection;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.*;
import java.time.LocalDate;

public class viewFeedbackImpl extends UnicastRemoteObject implements viewFeedbackDao {
    Connection cn = DbConnection.myConnection();
    public  viewFeedbackImpl() throws RemoteException{
        super();
    }
    @Override
    public ResultSet getFeedback() throws RemoteException{
        try {
            ResultSet rs =cn.createStatement().executeQuery("SELECT * from feedback");
            CachedRowSetImpl crs= new CachedRowSetImpl();
            crs.populate(rs);
            return crs;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    @Override
    public void addFeedback(viewFeedback vf) throws RemoteException{
        try {
            String sql = "INSERT INTO feedback(feedback_title, feedback_description, feedback_date) VALUES(?,?,?)";
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1,vf.getFeedback_title());
            ps.setString(2,vf.getFeedback_description());
            ps.setString(3,vf.getFeedback_date());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

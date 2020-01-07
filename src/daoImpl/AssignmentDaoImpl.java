package daoImpl;

import bll.Assignment;
import com.sun.rowset.CachedRowSetImpl;
import dao.AssignmentDao;
import utils.DbConnection;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.*;

public class AssignmentDaoImpl extends UnicastRemoteObject implements AssignmentDao
{
    Connection cn = DbConnection.myConnection();

    public AssignmentDaoImpl() throws RemoteException
    {
        super();
    }

    public void addAssDet(Assignment as) throws RemoteException
    {
        try
        {
           String sql = "INSERT INTO assignment(Assignment_title, Assignment_level, Assignment_course, Assignment_unit, Assignment_deadline_date) VALUES(?, ?, ?, ?, ?)";
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1, as.getAss_title());
            ps.setString(2, as.getAss_level());
            ps.setString(3, as.getAss_course());
            ps.setString(4, as.getAss_unit());
            ps.setDate(5, Date.valueOf(as.getAss_date()));
            ps.executeUpdate();
        }
        catch (SQLException e)
        {
            System.out.println(e);
        }
    }

    @Override
    public void updateAssDet(Assignment as)throws RemoteException
    {
        try
        {
            String sql = "UPDATE assignment set assignment_title = ?, assignment_level = ?, assignment_course = ?, assignment_unit = ?, assignment_deadline_date= ? where assignment_id = ?";
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1, as.getAss_title());
            ps.setString(2, as.getAss_level());
            ps.setString(3, as.getAss_course());
            ps.setString(4, as.getAss_unit());
            ps.setString(5, as.getAss_date().toString());
            ps.setString(6, as.getAss_ID());
            ps.executeUpdate();
        }
        catch (SQLException e)
        {
            System.out.println(e);
        }
    }

    @Override
    public void deleteAssDet(Assignment as)throws RemoteException
    {
        try
        {
            String sql = "DELETE from assignment where assignment_id = ?";
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1, as.getAss_ID());
            ps.executeUpdate();
        }
        catch (SQLException e)
        {
            System.out.println(e);
        }
    }

    public ResultSet getAssignDetails() throws RemoteException{
        try
        {
            ResultSet rs = cn.createStatement().executeQuery("select assignment_id, assignment_title, assignment_level, assignment_course, assignment_unit, assignment_deadline_date from assignment");
            CachedRowSetImpl crsi = new CachedRowSetImpl();
            crsi.populate(rs);
            return crsi;
        }

        catch(Exception e)
        {
            System.out.println(e);
        }
        return null;
    }

    public ResultSet getAssignDetailsByCourseAndLevel(String course, String level) throws RemoteException{
        try
        {
            ResultSet rs = cn.createStatement().executeQuery("select assignment_id, assignment_title, assignment_level, assignment_course, assignment_unit, assignment_deadline_date from assignment where assignment_course = '"+course+"' and assignment_level ="+level+"");
            CachedRowSetImpl crsi = new CachedRowSetImpl();
            crsi.populate(rs);
            return crsi;
        }

        catch(Exception e)
        {
            System.out.println(e);
        }
        return null;
    }
}

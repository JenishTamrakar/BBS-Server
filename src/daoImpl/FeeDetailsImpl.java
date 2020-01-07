package daoImpl;

import bll.FeeDetails;
import com.sun.rowset.CachedRowSetImpl;
import dao.FeeDetailsDao;
import utils.DbConnection;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.*;

public class FeeDetailsImpl extends UnicastRemoteObject implements FeeDetailsDao
{
    Connection cn = DbConnection.myConnection();

    public FeeDetailsImpl() throws RemoteException
    {
        super();
    }

    public void addFeeDet(FeeDetails fd) throws RemoteException
    {
        try
        {
            String sql = "INSERT INTO feedetails(fee_amount, fee_details, fee_deadline_date, student_course, student_level) VALUES(?, ?, ?, ?, ?)";
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1, fd.getFee_Amt());
            ps.setString(2, fd.getFee_Details());
            ps.setDate(3, Date.valueOf(fd.getDeadline_Date()));
            ps.setString(4, fd.getStudent_course());
            ps.setString(5, fd.getStudent_level());
            ps.executeUpdate();
        }
        catch (SQLException e)
        {
            System.out.println(e);
        }
    }

    @Override
    public void updateFeeDet(FeeDetails fd)throws RemoteException
    {
        try
        {
            String sql = "UPDATE feedetails set fee_amount = ?, fee_deadline_date = ?, fee_details = ?, student_course=?, student_level = ? where fee_id = ?";
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1, fd.getFee_Amt());
            ps.setString(2, fd.getDeadline_Date().toString());
            ps.setString(3, fd.getFee_Details());
            ps.setString(4, fd.getStudent_course());
            ps.setString(5, fd.getStudent_level());
            ps.setString(6, fd.getFee_ID());
            ps.executeUpdate();
//            System.out.println(ps);
        }
        catch (SQLException e)
        {
            System.out.println(e);
        }
    }

    @Override
    public void deleteFeeDet(FeeDetails fd)throws RemoteException
    {
        try
        {
            String sql = "DELETE from feedetails where fee_id = ?";
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1, fd.getFee_ID());
            ps.executeUpdate();
//            System.out.println(ps);
        }
        catch (SQLException e)
        {
            System.out.println(e);
        }
    }

    public ResultSet getFeeDetails() throws RemoteException{
        try
        {
            ResultSet rs = cn.createStatement().executeQuery("select fee_id, fee_amount, fee_deadline_date, fee_details, student_course, student_level from feedetails");
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

    public ResultSet getFeeDetailsByCourse(String course) throws RemoteException{
        try
        {
//            System.out.println("Student Course = "+course);
            ResultSet rs = cn.createStatement().executeQuery("select fee_id, fee_amount, fee_deadline_date, fee_details, student_course, student_level from feedetails where student_course = '"+course+"'");

//            ResultSet rs = cn.createStatement().executeQuery("select fee_id, fee_amount, fee_deadline_date, fee_details, student_course, student_level from feedetails where student_course ="+course);
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

package daoImpl;

import bll.Faculty;
import com.sun.rowset.CachedRowSetImpl;
import dao.FacultyDao;
import utils.DbConnection;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FacultyDaoImpl extends UnicastRemoteObject implements FacultyDao
{
    Connection cn = DbConnection.myConnection();

    public  FacultyDaoImpl()throws RemoteException
    {
        super();
    }

    @Override
    public void addFaculty(Faculty f)throws RemoteException
    {


        try
        {
            String sql = "INSERT INTO faculty(faculty_id, faculty_name, faculty_email, faculty_course) VALUES(?, ?, ?, ?)";
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1, f.getFaculty_ID());
            ps.setString(2, f.getName());

            ps.setString(3, f.getEmail());
            ps.setString(4, f.getCourse());
            ps.executeUpdate();
        }
        catch (SQLException e)
        {
            System.out.println(e);
        }
    }

    @Override
    public void updateFaculty(Faculty f)throws RemoteException
    {
        try
        {
            String sql = "UPDATE faculty set faculty_id = ?, faculty_name = ?,faculty_email = ?, faculty_course = ? where faculty_sn = ?";
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1, f.getFaculty_ID());
            ps.setString(2, f.getName());

            ps.setString(3, f.getEmail());
            ps.setString(4, f.getCourse());
            ps.executeUpdate();
//            System.out.println(ps);
        }
        catch (SQLException e)
        {
            System.out.println(e);
        }
    }

    @Override
    public void deleteFaculty(Faculty f)throws RemoteException
    {
        try
        {
            String sql = "DELETE from faculty where faculty_sn = ?";
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1, f.getFaculty_SN());
            ps.executeUpdate();
//            System.out.println(ps);
        }
        catch (SQLException e)
        {
            System.out.println(e);
        }
    }

    public ResultSet getFacultyRecords() throws RemoteException{
        try
        {
            ResultSet rs = cn.createStatement().executeQuery("select faculty_sn, faculty_id, faculty_name, faculty_course, faculty_email from faculty");
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
    @Override
    public ResultSet getProfile(String user_id) throws RemoteException {
        try
        {
            ResultSet rs = cn.createStatement().executeQuery("select faculty_sn, faculty_id, faculty_name, " +
                    "faculty_course, faculty_email from faculty WHERE faculty_id = "+user_id);
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

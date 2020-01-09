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
    //database connection
    Connection cn = DbConnection.myConnection();

    //constructor
    public  FacultyDaoImpl()throws RemoteException
    {
        super();
    }

    /**
     * add faculty details into the faculty table
     * @param f
     * @throws RemoteException
     */
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

    /**
     * update event details into the event table
     * @param f
     * @throws RemoteException
     */
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

    /**
     * delete event details from event table
     * @param f
     * @throws RemoteException
     */
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

    /**
     * retrieve faculty records from the faculty table
     * @return
     * @throws RemoteException
     */
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

    /**
     * retrieve faculty data from faculty table by course
     * @return
     * @throws RemoteException
     */
    public ResultSet getFacultyRecordsByCourse() throws RemoteException{
        try
        {
            ResultSet rs = cn.createStatement().executeQuery("select faculty_course, count(faculty_course) as total from faculty group by faculty_course");
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

    /**
     * retrieve faculty data for profile by the logged in user ID
     * @param user_id
     * @return
     * @throws RemoteException
     */
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

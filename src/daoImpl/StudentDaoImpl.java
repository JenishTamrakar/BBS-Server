package daoImpl;

import bll.Student;
import com.sun.rowset.CachedRowSetImpl;
import dao.StudentDao;
import utils.DbConnection;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentDaoImpl extends UnicastRemoteObject implements StudentDao
{
    Connection cn = DbConnection.myConnection();

    public  StudentDaoImpl()throws RemoteException
    {
        super();
    }

    @Override
    public void addStudent(Student s)throws RemoteException
    {
        try
        {
            String sql = "INSERT INTO student(student_id, student_name, student_email, student_course, student_level) VALUES(?, ?, ?, ?, ?)";
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1, s.getStudent_ID());
            ps.setString(2, s.getName());
            ps.setString(3, s.getEmail());
            ps.setString(4, s.getCourse());
            ps.setString(5, s.getLevel());
            ps.executeQuery();
        }
        catch (SQLException e)
        {
            System.out.println(e);
        }
    }

    @Override
    public void updateStudent(Student s)throws RemoteException
    {
        try
        {
            String sql = "UPDATE student set student_id = ?, student_name = ?, student_email = ?, student_course = ?, student_level = ? where student_sn = ?";
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1, s.getStudent_ID());
            ps.setString(2, s.getName());
            ps.setString(3, s.getEmail());
            ps.setString(4, s.getCourse());
            ps.setString(5, s.getLevel());
            ps.setString(6, s.getStudent_SN());
            ps.executeUpdate();
//            System.out.println(ps);
        }
        catch (SQLException e)
        {
            System.out.println(e);
        }
    }

    @Override
    public void deleteStudent(Student s)throws RemoteException
    {
        try
        {
            String sql = "DELETE from student where student_sn = ?";
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1, s.getStudent_SN());
            ps.executeUpdate();
        }
        catch (SQLException e)
        {
            System.out.println(e);
        }
    }

    public ResultSet getStudentRecords() throws RemoteException{
        try
        {
            ResultSet rs = cn.createStatement().executeQuery("select student_sn, student_id, student_name, student_course, student_email, student_level from student");
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
            ResultSet rs = cn.createStatement().executeQuery("select student_sn, student_id, student_name, student_course, student_email, student_level from student WHERE student_id = "+user_id);

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

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
    //database connection
    Connection cn = DbConnection.myConnection();

    //constructor
    public StudentDaoImpl()throws RemoteException
    {
        super();
    }

    /**
     * add student details into the student table
     * @param s
     * @throws RemoteException
     */
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
            ps.executeUpdate();
        }
        catch (SQLException e)
        {
            System.out.println(e);
        }
    }

    /**
     * update student details into the student table
     * @param s
     * @throws RemoteException
     */
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

    /**
     * delete student details from the student table
     * @param s
     * @throws RemoteException
     */
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

    /**
     * retrieve student details from the student table
     * @return
     * @throws RemoteException
     */
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

    /**
     * retrieve student details from student table by course
     * @return
     * @throws RemoteException
     */
    public ResultSet getStudentRecordsByCourse() throws RemoteException{
        try
        {
            ResultSet rs = cn.createStatement().executeQuery("select student_course, count(student_course) as total from student group by student_course");
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
     * retrieve student details from student table by level
     * @return
     * @throws RemoteException
     */
    public ResultSet getStudentRecordsByLevel() throws RemoteException{
        try
        {
            ResultSet rs = cn.createStatement().executeQuery("select student_level, count(student_level) as total from student group by student_level");
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
     * retrieve student data for profile by the logged in user ID
     * @param user_id
     * @return
     * @throws RemoteException
     */
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

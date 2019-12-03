package daoImpl;

import bll.Student;
import dao.StudentDao;
import utils.DbConnection;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
        System.out.print(s.getStudent_ID());
        System.out.print(s.getName());
        System.out.print(s.getEmail());
        System.out.print(s.getCourse());
        System.out.print(s.getLevel());

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
}

package daoImpl;

import bll.Faculty;
import dao.FacultyDao;
import utils.DbConnection;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class FacultyDaoImpl extends UnicastRemoteObject implements FacultyDao
{
    Connection cn = DbConnection.myConnection();

    public  FacultyDaoImpl()throws RemoteException
    {
        super();
    }

    @Override
    public void addStudent(Faculty f)throws RemoteException
    {
        System.out.print(f.getFaculty_ID());
        System.out.print(f.getName());
        System.out.print(f.getEmail());
        System.out.print(f.getCourse());

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
}

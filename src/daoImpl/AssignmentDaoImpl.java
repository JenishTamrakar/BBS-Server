package daoImpl;

import bll.Assignment;
import dao.AssignmentDao;
import utils.DbConnection;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
}

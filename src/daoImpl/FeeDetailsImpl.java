package daoImpl;

import bll.FeeDetails;
import dao.FeeDetailsDao;
import utils.DbConnection;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
}

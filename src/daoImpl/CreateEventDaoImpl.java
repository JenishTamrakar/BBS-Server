package daoImpl;

import bll.CreateEvent;
import dao.CreateEventDao;
import utils.DbConnection;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CreateEventDaoImpl extends UnicastRemoteObject implements CreateEventDao
{
    Connection cn = DbConnection.myConnection();

    public CreateEventDaoImpl() throws RemoteException
    {
        super();
    }

    @Override
    public void addEvent(CreateEvent ce) throws RemoteException
    {
        System.out.println(ce.getEvent_title());
        System.out.println(ce.getEvent_desc());
        System.out.println(ce.getEvent_date());
        System.out.println(ce.getEvent_time());
        try
        {
            String sql = "INSERT INTO event(event_title, event_description, event_date, event_time) VALUES(?, ?, ?, ?)";
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1, ce.getEvent_title());
            ps.setString(2, ce.getEvent_desc());
            ps.setString(3, ce.getEvent_date());
            ps.setString(4, ce.getEvent_time());
            ps.executeUpdate();
            System.out.println(ps);
        }
        catch(SQLException e)
        {
            System.out.println(e);
        }
    }
}

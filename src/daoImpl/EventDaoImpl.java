package daoImpl;

import bll.Event;
import dao.EventDao;
import utils.DbConnection;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.*;

public class EventDaoImpl extends UnicastRemoteObject implements EventDao
{
    Connection cn = DbConnection.myConnection();

    public  EventDaoImpl() throws RemoteException
    {
        super();
    }
    public void addEventDet(Event ev) throws RemoteException
    {
        try
        {
            String sql = "INSERT INTO event(event_title, event_description, event_date, event_time) VALUES(?, ?, ?, ?)";
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1, ev.getEvent_title());
            ps.setString(2, ev.getEvent_desc());
            ps.setDate(3, Date.valueOf(ev.getEvent_date()));
            ps.setTime(4, Time.valueOf(ev.getEvent_time()));
            ps.executeUpdate();
        }
        catch(SQLException e)
        {
            System.out.println(e);
        }
    }
}

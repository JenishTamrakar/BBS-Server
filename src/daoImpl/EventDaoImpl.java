package daoImpl;

import bll.Event;
import com.sun.rowset.CachedRowSetImpl;
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
            ps.setString(3, ev.getEvent_date().toString());
            ps.setString(4, ev.getEvent_time().toString());
            ps.executeUpdate();
        }
        catch(SQLException e)
        {
            System.out.println(e);
        }
    }

    @Override
    public void updateEventDet(Event ev)throws RemoteException
    {
        try
        {
            String sql = "UPDATE event set event_title = ?, event_description = ?, event_date = ?, event_time=? where event_id = ?";
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1, ev.getEvent_title());
            ps.setString(2, ev.getEvent_desc());
            ps.setString(3, ev.getEvent_date().toString());
            ps.setString(4, ev.getEvent_time().toString());
            ps.setString(5, ev.getEvent_ID());
            ps.executeUpdate();
//            System.out.println(ps);
        }
        catch (SQLException e)
        {
            System.out.println(e);
        }
    }

    @Override
    public void deleteEventDet(Event ev)throws RemoteException
    {
        try
        {
            String sql = "DELETE from event where event_id = ?";
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1, ev.getEvent_ID());
            ps.executeUpdate();
//            System.out.println(ps);
        }
        catch (SQLException e)
        {
            System.out.println(e);
        }
    }

    public ResultSet getEventDetails() throws RemoteException{
        try
        {
            ResultSet rs = cn.createStatement().executeQuery("select event_id, event_title, event_description, event_time, event_date from event");
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

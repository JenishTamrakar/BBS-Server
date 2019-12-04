package daoImpl;

import bll.CreateEvent;
import dao.CreateEventDao;
import utils.DbConnection;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CreateEventImpl extends UnicastRemoteObject implements CreateEventDao {
    Connection cn = DbConnection.myConnection();
    public CreateEventImpl() throws RemoteException{
        super();
    }

    @Override
    public void eventCreate(CreateEvent e) throws RemoteException {
        System.out.println(e.getEventTitle());

        try {
            String sql = "INSERT INTO event(event_title, event_description, event_date, event_time) VALUES(?,?,?,?)";
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1, e.getEventTitle());
            ps.setString(2, e.getEventDescription());
            ps.setString(3, e.getEventDate());
            ps.setString(4, e.getEventTime());
            ps.executeUpdate();
        }
        catch(SQLException p) {
            System.out.println(p);
        }
    }
}


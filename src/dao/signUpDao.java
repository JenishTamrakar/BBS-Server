package dao;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.ResultSet;

public interface signUpDao extends Remote {
    ResultSet checkID(String user_id)throws RemoteException;
}

package dao;

import bll.Register;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RegisterDao extends Remote {
    void addUser(Register r) throws RemoteException;
    void updatePassword(String user_id, String user_password) throws RemoteException;


}

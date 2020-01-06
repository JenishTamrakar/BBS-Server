package daoImpl;

import bll.changePassword;
import dao.changePasswordDao;
import utils.DbConnection;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;

public class changePasswordImpl extends UnicastRemoteObject implements changePasswordDao {
    Connection cn = DbConnection.myConnection();
    public changePasswordImpl()throws RemoteException{
        super();
    }

    @Override
    public void updatePassword(changePassword cp)throws RemoteException{


    }
}

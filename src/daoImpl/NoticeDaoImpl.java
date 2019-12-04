package daoImpl;

import bll.Notice;
import dao.NoticeDao;
import utils.DbConnection;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public  class  NoticeDaoImpl extends UnicastRemoteObject implements NoticeDao{
Connection cn = DbConnection.myConnection();
public NoticeDaoImpl()throws RemoteException{
    super();
}
    @Override
    public void addNotice(Notice n) throws RemoteException {
        System.out.println(n.getNoticeTitle());
        System.out.println(n.getNoticeDate());
        System.out.println(n.getNoticeDescription());
        try {
            String sql ="INSERT INTO notice(notice_title, notice_description, notice_date) VALUES(?,?,?)";
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1,n.getNoticeTitle());
            ps.setString(2,n.getNoticeDescription());
            ps.setString(3,n.getNoticeDate());

            ps.executeUpdate();
            System.out.println("hello");
        }catch (SQLException e){
            System.out.println(e);
        }

    }
}


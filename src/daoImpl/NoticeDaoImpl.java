package daoImpl;

import bll.Notice;
import com.sun.rowset.CachedRowSetImpl;
import dao.NoticeDao;
import utils.DbConnection;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public  class  NoticeDaoImpl extends UnicastRemoteObject implements NoticeDao{
Connection cn = DbConnection.myConnection();
public NoticeDaoImpl()throws RemoteException{
    super();
}
    @Override
    public void addNotice(Notice n) throws RemoteException {
//        System.out.println(n.getNoticeTitle());
//        System.out.println(n.getNoticeDate());
//        System.out.println(n.getNoticeDescription());
        try {
            String sql ="INSERT INTO notice(notice_title, notice_description, notice_date) VALUES(?,?,?)";
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1,n.getNoticeTitle());
            ps.setString(2,n.getNoticeDescription());
            ps.setString(3,n.getNoticeDate());

            ps.executeUpdate();
//            System.out.println("hello");
        }catch (SQLException e){
            System.out.println(e);
        }

    }

    @Override
    public void updateNotice(Notice n)throws RemoteException
    {
        try
        {
            String sql = "UPDATE notice set notice_title = ?, notice_description = ?, notice_date= ? where notice_id = ?";
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1, n.getNoticeTitle());
            ps.setString(2, n.getNoticeDescription());
            ps.setString(3, n.getNoticeDate());
            ps.setString(4, n.getNoticeID());
            ps.executeUpdate();
        }
        catch (SQLException e)
        {
            System.out.println(e);
        }
    }

    @Override
    public void deleteNotice(Notice n)throws RemoteException
    {
        try
        {
            String sql = "DELETE from notice where notice_id = ?";
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1, n.getNoticeID());
            ps.executeUpdate();
        }
        catch (SQLException e)
        {
            System.out.println(e);
        }
    }

    public ResultSet getNoticeDetails() throws RemoteException{
        try
        {
            ResultSet rs = cn.createStatement().executeQuery("select notice_id, notice_title, notice_description, notice_date from notice");
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


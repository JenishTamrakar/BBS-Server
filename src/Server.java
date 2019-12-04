import dao.FacultyDao;
import dao.LoginDao;
import dao.NoticeDao;
import dao.RegisterDao;
import dao.StudentDao;
import daoImpl.FacultyDaoImpl;
import daoImpl.LoginDaoImpl;
import daoImpl.NoticeDaoImpl;
import daoImpl.RegisterDaoImpl;
import daoImpl.StudentDaoImpl;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server {

    public static void main(String[] args) {
        try {
            RegisterDao rd= new RegisterDaoImpl();
            LoginDao ld = new LoginDaoImpl();
            StudentDao sd = new StudentDaoImpl();
            FacultyDao fd = new FacultyDaoImpl();
            NoticeDao nd = new NoticeDaoImpl();

            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind("Register", rd);
            registry.rebind("Login",ld);
            registry.rebind("Student", sd);
            registry.rebind("Faculty", fd);
            registry.rebind("Notice",nd);
            System.out.print("Server Started");
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}

import dao.LoginDao;
import dao.RegisterDao;
import dao.StudentDao;
import daoImpl.LoginDaoImpl;
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
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind("Register", rd);
            registry.rebind("Login",ld);
            registry.rebind("Student", sd);
            System.out.print("Server Started");
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}

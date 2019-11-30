import dao.LoginDao;
import dao.RegisterDao;
import daoImpl.LoginDaoImpl;
import daoImpl.RegisterDaoImpl;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server {

    public static void main(String[] args) {
        try {
            RegisterDao rd= new RegisterDaoImpl();
            LoginDao ld = new LoginDaoImpl();
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind("Register", rd);
            registry.rebind("Login",ld);
            System.out.print("Server Started");
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}

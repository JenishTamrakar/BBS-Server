import dao.RegisterDao;
import daoImpl.RegisterDaoImpl;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server {

    public static void main(String[] args) {
        try {
            RegisterDao rd= new RegisterDaoImpl();
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind("Register", rd);
            System.out.print("Server Started");
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}

import dao.*;
import daoImpl.*;

import java.rmi.Remote;
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
            EventDao ed = new EventDaoImpl();
            FeeDetailsDao fdd = new FeeDetailsImpl();
            AssignmentDao ad = new AssignmentDaoImpl();
            viewFeedbackDao vd =  new viewFeedbackImpl();
            signUpDao sud = new signUpDaoImpl();
            changePasswordDao cpd = new changePasswordImpl();


            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind("Register", rd);
            registry.rebind("Login",ld);
            registry.rebind("Student", sd);
            registry.rebind("Faculty", fd);
            registry.rebind("Notice", nd);
            registry.rebind("FeeDetails", fdd);
            registry.rebind("Event",ed);
            registry.rebind("Assignment", ad);
            registry.rebind("ViewFeedback",vd);
            registry.rebind("SignUp",sud);
            registry.rebind("changePassword",cpd);
            System.out.print("Server Started");
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}

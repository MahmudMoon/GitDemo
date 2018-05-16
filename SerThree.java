import java.rmi.*;
import java.rmi.server.*;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.net.*;
import java.io.*;


public class SerThree{
	public static void main(String arg[]) {
		try{
		int port=19869;
		String host;
		startRegistry(port);
        ImplThree ie = new ImplThree();
		host = "rmi://localhost:"+Integer.toString(port)+"/addition";
        Naming.rebind(host,ie);
		}catch(Exception e){
			System.out.println("EXCEPTION OCCURED");
		}
	}
	
	public static void startRegistry(int port)throws RemoteException{
		try{
			Registry registry = LocateRegistry.getRegistry(port);
			registry.list();
		}catch(RemoteException re){
			Registry registry = LocateRegistry.createRegistry(port);
			System.out.println("Port has been created");
		}
	}

}

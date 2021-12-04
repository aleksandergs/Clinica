package logic;

import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

import javax.swing.JFrame;

import logic.Flujo;

public class Servidor extends Thread {

	public static Vector usuarios = new Vector();
	  
	public static void main (String args[])
	{
		ServerSocket sfd = null;
		try
		{
			sfd = new ServerSocket(7000);
			System.out.println("Servidor Abierto exitosamente");
		}
		catch (IOException ioe)
		{
			System.out.println("Comunicación rechazada."+ioe);	
			System.exit(1);
		}

		//Ventana del servidor para poder cerrarlo facilmente sin necesidar del administrador de tareas
		JFrame window = new JFrame();
		window.setVisible(true);
		window.setTitle("Servidor");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setBounds(0, 0, 256, 128);
		window.setLocationRelativeTo(null);
		window.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.out.println("Cerrando servidor");
			}
		});
		
		while (true)
	    {
			try
			{
				Socket nsfd = sfd.accept();
				System.out.println("Conexion aceptada de: "+nsfd.getInetAddress());
				Flujo flujo = new Flujo(nsfd);
				Thread t = new Thread(flujo);
				t.start();
			}
			catch(IOException ioe)
			{
				System.out.println("Error: "+ioe);
			}
	    }
	}
}

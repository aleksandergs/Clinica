package logic;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Enumeration;

import logic.Flujo;
import logic.Servidor;

public class Flujo extends Thread{

	Socket nsfd;
	DataInputStream FlujoLectura;
	DataOutputStream FlujoEscritura;

	public Flujo (Socket sfd)
	{
		nsfd = sfd;
		try
		{
			FlujoLectura = new DataInputStream(new BufferedInputStream(sfd.getInputStream()));
			FlujoEscritura = new DataOutputStream(new BufferedOutputStream(sfd.getOutputStream()));
		}
		catch(IOException ioe)
		{
			System.out.println("IOException(Flujo): "+ioe);
		}
	}
	  
	public void run()
	{
		broadcast(nsfd.getInetAddress()+"> se ha conectado");
	    Servidor.usuarios.add ((Object) this);
	    while(true)
	    {
	    	try
	    	{
	    		String input = FlujoLectura.readUTF();
	    		if (!input.equals(""))
	    		{
	    			String command = input.substring(0, input.lastIndexOf('-'));
	    			switch (command) {
					case "Save":
						FileOutputStream clinica;
						ObjectOutputStream clinicaWrite;
						clinica = new  FileOutputStream(input.substring(input.lastIndexOf('-')+1, input.length()));
						clinicaWrite = new ObjectOutputStream(clinica);
						clinicaWrite.writeObject(Clinica.getInstance());
						clinicaWrite.close();
						break;

					default:
						break;
					}
	    		}
	    	}
	    	catch(IOException ioe)
	    	{
	    		Servidor.usuarios.removeElement(this);
	    		broadcast(nsfd.getInetAddress()+"> se ha desconectado");
	    		break;
	    	}
	    }
	}
	  
	public void broadcast(String mensaje)
	{
		synchronized (Servidor.usuarios)
		{
			Enumeration e = Servidor.usuarios.elements();
			while (e.hasMoreElements())
			{
				Flujo f = (Flujo) e.nextElement();
				try
				{
					synchronized(f.FlujoEscritura)
					{
						f.FlujoEscritura.writeUTF(mensaje);
						f.FlujoEscritura.flush();
					}
				}
				catch(IOException ioe)
				{
					System.out.println("Error: "+ioe);
				}
			}
		}
	}
}

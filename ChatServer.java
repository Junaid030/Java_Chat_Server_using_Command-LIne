//send a message using a single server and multiple clients
//chat server 
import java.io.*;
import java.net.*;
class ChatServer extends Thread
{
	ServerSocket server;
	Socket soc;
	String message = "";
	int totalclient = 0;
	public static void main(String args[]) throws Exception
	{
		new ChatServer();
		
	}
	public ChatServer() throws Exception
	{
		try
		{
			server = new ServerSocket(3006);
			System.out.println("server started");
			while(true)
			{
			soc = server.accept();
			totalclient++;
			System.out.println("client connected");
			ChildHandler obj = new ChildHandler (soc,totalclient);
			System.out.println("total client connected :" +totalclient);
			obj.start();
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		finally
		{
			server.close();
		}
	}
	class ChildHandler extends Thread
	{
		String cmessage = "";
		Socket soc;
		int number;
		public ChildHandler (Socket s, int num) throws Exception
		{
			this.soc = s;
			this.number = num;
			
		}
		public void run()
		{
			try 
			{
				while (!cmessage.equalsIgnoreCase("bye"))
				{
					ObjectInputStream ois = new ObjectInputStream(soc.getInputStream());
					cmessage = (String)ois.readObject();
					System.out.println("Client :" +number+" " +cmessage);
					BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
					ObjectOutputStream oos = new ObjectOutputStream(soc.getOutputStream());
					System.out.print("server :");
					String smessage = in.readLine();
					oos.writeObject(smessage);
				}
				soc.close();
			}
			catch(Exception e)
			{
				System.out.println(e);
			}
		}
}
}

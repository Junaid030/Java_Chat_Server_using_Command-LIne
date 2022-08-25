//chat client
import java.io.*;
import java.net.*;
class ChatClient 
{
	String cmessage = "";
	Socket client;
	public static void main(String args[]) throws Exception
	{
		new ChatClient();
	}
	public ChatClient() throws Exception
	{
		try
		{
		    client = new Socket("127.0.0.0",3006);
			System.out.println("server connected");
			while(!cmessage.equalsIgnoreCase("Bye"))
			{
				BufferedReader in = new BufferedReader(new InputStreamReader (System.in));
				System.out.println("client" );
				cmessage = in.readLine();
				ObjectOutputStream oos = new ObjectOutputStream(client.getOutputStream());
				oos.writeObject(cmessage);
				ObjectInputStream ois = new ObjectInputStream(client.getInputStream());
				String smessage = (String)ois.readObject();
				System.out.println("server :" +smessage);
			}
			client.close();
		}
		catch(Exception e )
		{
			System.out.println(e);
		}
		
	}
}
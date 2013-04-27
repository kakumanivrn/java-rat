import java.io.*;
import java.net.*;

public class TestClient 
{
   public static void main(String[] args)
   {
      String host = args[0];
      try 
      {
         Socket           client    = new Socket(host, 4321);
         DataOutputStream socketOut = new DataOutputStream(client.getOutputStream());
         DataInputStream  socketIn  = new DataInputStream(client.getInputStream());
         DataInputStream  console   = new DataInputStream(System.in);
         DataInputStream streamIn = new DataInputStream(new BufferedInputStream(client.getInputStream()));
         System.out.println("Connected to " + host);

         boolean done = false;
         String line = "ls";
         String line2 = null;
         while (!done) 
         {
            if (line.equalsIgnoreCase("bye"))
              done = true;
            else
            {
            	System.out.print("Enter CMD: ");
            	line = console.readLine();
		socketOut.writeBytes(line + '\n');
		String op = "";
		line2 = streamIn.readLine();
		op = line2 + "\n";
		op = op.replace("----", "\n");
		System.out.println(op);
            }
         }

         socketOut.close(); socketIn.close(); client.close();
      } 
      catch (UnknownHostException e) 
      { System.err.println(host + ": unknown host."); } 
      catch (IOException e) 
      { System.err.println("I/O error with " + host); }
   }
}


package ThreadProgramming;

import java.sql.*;
import java.io.*;
import java.net.*;
import java.util.Date;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class MyMultiThreadServer extends JFrame {
  // Text area for displaying contents
  private JTextArea jta = new JTextArea();
  
  public static void main(String[] args) {
    new MyMultiThreadServer();
  }

  public MyMultiThreadServer() {
    // Place text area on the frame
    setLayout(new BorderLayout());
    add(new JScrollPane(jta), BorderLayout.CENTER);

    setTitle("MultiThreadServer");
    setSize(500, 300);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true); // It is necessary to show the frame here!

    try {
      // Create a server socket
      ServerSocket serverSocket = new ServerSocket(8000);
      jta.append("MultiThreadServer started at " + new Date() + '\n');

      // Number a client
      int clientNo = 1;

      while (true) {
        // Listen for a new connection request
        Socket socket = serverSocket.accept();

        // Display the client number
        jta.append("\n" + "Starting thread for client " + clientNo +" at " + new Date() + '\n');

        // Find the client's host name, and IP address
        InetAddress inetAddress = socket.getInetAddress();
        jta.append("Client " + clientNo + "'s host name is "
          + inetAddress.getHostName() + "\n");
        jta.append("Client " + clientNo + "'s IP Address is "
          + inetAddress.getHostAddress() + "\n" + "\n");

        // Create a new thread for the connection
        HandleAClient thread = new HandleAClient(socket, clientNo);

        // Start the new thread
        thread.start();

        // Increment clientNo
        clientNo++;
      }
    }
    catch(IOException ex) {
      jta.append(ex.toString() + '\n');
    }
  }

  // Define the thread class for handling new connection
  class HandleAClient extends Thread {
    private Socket socket; // A connected socket
    private Integer cno;

    /** Construct a thread */
    public HandleAClient(Socket socket, Integer cno) {
      this.socket = socket;
      this.cno = cno;
    }

    /** Run a thread */
    public void run() {
        // Continuously serve the client
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        ResultSetMetaData rsmd = null;
        List<String> records = new ArrayList<String>();
                    // Create data input and output streams
                    
                    //ObjectOutputStream outObjectToClient = new ObjectOutputStream(socket.getOutputStream());
        try{
            ObjectOutputStream outObjectToClient = new ObjectOutputStream(socket.getOutputStream());
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            con = DriverManager.getConnection("jdbc:mysql:///customer", "root", "");
            String query = "SELECT * FROM Customer";
            st = con.createStatement();  
            rs = st.executeQuery(query);
            rsmd = rs.getMetaData();
            int columnsNumber = rsmd.getColumnCount();
            
            //print table to server
            String formats = "%-17s %-18s %-44s %-89s %-18s %-17s %s";
            String format = "%s\t";
            jta.append("Retrieve data table from Customer database for client " + cno + '\n');
            jta.append(String.format(formats,"Cust_ID", "Name", "Class", "Address", "CreditLimit", "Balance", "Comment" + "\n"));
            while (rs.next()) {
            //Print one row          
              for(int i = 1 ; i <= columnsNumber; i++){
                jta.append(String.format(format, rs.getString(i) + " ")); //Print one element of a row
                records.add(rs.getString(i));
              }
              jta.append("\n");//Move to the next line to print the next row.           
            }     
            outObjectToClient.writeObject(records); 
            outObjectToClient.flush();
            }catch (Exception ex){
                jta.append(ex.toString() + '\n');
            }

            try{
                DataInputStream inputFromClient = new DataInputStream(socket.getInputStream());
                DataOutputStream outputToClient = new DataOutputStream(socket.getOutputStream());
                    // Receive quote no. from the client
                    String s1 = "SELECT Name, Class, Address, CreditLimit, Balance, Comment FROM Customer where Cust_ID = 1";
                    String s2 = "SELECT Name, Class, Address, CreditLimit, Balance, Comment FROM Customer where Cust_ID = 2";
                    String s3 = "SELECT Name, Class, Address, CreditLimit, Balance, Comment FROM Customer where Cust_ID = 3";
                    String s4 = "SELECT Name, Class, Address, CreditLimit, Balance, Comment FROM Customer where Cust_ID = 4";
                    while(true){
                        st = con.createStatement();
                        int rowNo = inputFromClient.readInt();
                        String selection[] = {s1, s2, s3, s4};
                        String data = selection[--rowNo];
                        rs = st.executeQuery(data);
                        
                        while (rs.next()) {   
                                String Name = rs.getString(1);
                                String Class = rs.getString(2);
                                String Address = rs.getString(3);
                                String CreditLimit = rs.getString(4);
                                String Balance = rs.getString(5);
                                String Comment = rs.getString(6);
                                String info = Name + ", " + Class + ", " + Address + ", $" + CreditLimit + ", $" + Balance + ", " + Comment;
                                // Send quote back to the client
                                outputToClient.writeUTF(info);
                                jta.append("\n" + "Row data requested from client " + cno + " is Row " + ++rowNo + '\n');
                                jta.append("Row " + rowNo + " : " + info + '\n');    
                        }
                    }
                    
            } catch(IOException ex) {
                jta.append(ex.toString() + '\n');
            } catch (SQLException ex) { 
                jta.append(ex.toString() + '\n');
            }
            finally {
            try { 
              if(rs != null) 
              rs.close();
              if(st != null) 
              st.close(); 
              if(con != null) 
              con.close(); 
            } catch (SQLException ex) {
                jta.append(ex.toString() + '\n');
            }
        }
        }
        
        
      
    }
  }


       




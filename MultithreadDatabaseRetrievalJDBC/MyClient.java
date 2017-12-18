/**
 *
 * @author Karen
 */

package ThreadProgramming;

import java.io.*;
import java.net.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class MyClient extends JFrame {
  // Text field for receiving quote no.
  private JTextField jtf = new JTextField();

  // Text area to display contents
  private JTextArea jta = new JTextArea();

  // IO streams
  private DataOutputStream toServer;
  private DataInputStream quotefromServer;
  
  private ObjectInputStream inFromServer;
  public static void main(String[] args) {
    new MyClient();
  }

  public MyClient() {
    // Panel p to hold the label and text field
    JPanel p = new JPanel();
    p.setLayout(new BorderLayout());
    p.add(new JLabel("Enter quote no."), BorderLayout.WEST);
    p.add(jtf, BorderLayout.CENTER);
    jtf.setHorizontalAlignment(JTextField.RIGHT);

    setLayout(new BorderLayout());
    add(p, BorderLayout.NORTH);
    add(new JScrollPane(jta), BorderLayout.CENTER);

    jtf.addActionListener(new ButtonListener()); // Register listener

    setTitle("Client");
    setSize(500, 300);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true); // It is necessary to show the frame here!

    try {
      // Create a socket to connect to the server
      Socket socket = new Socket("localhost", 8000);
      // Create an input stream to receive data from the server
      quotefromServer = new DataInputStream(socket.getInputStream());
      inFromServer = new ObjectInputStream(socket.getInputStream());
      // Create an output stream to send data to the server
      toServer = new DataOutputStream(socket.getOutputStream());   
      // get arraylist from server to display table content
      List<String> result = new ArrayList<String>();
      String formats = "%-17s %-18s %-44s %-89s %-18s %-17s %s";
      String format = "%s\t";
      jta.append("Retrieve data table from Customer database." + "\n");
      jta.append(String.format(formats,"Cust_ID", "Name", "Class", "Address", "CreditLimit", "Balance", "Comment" + "\n"));   
      try{
          result = (ArrayList<String>)inFromServer.readObject();
          Iterator itr = result.listIterator();
          
          while(itr.hasNext()) {
            for(int i = 0 ; i <= 6; i++){
            jta.append(String.format(format, itr.next() + " " ));
            }
            jta.append("\n");
           }
      }catch (Exception ex) {
        jta.append(ex.toString() + '\n');
      }
      jta.append("\n" + "Enter row number to retrieve row information..." + "\n");
    }catch (IOException ex) {
      jta.append(ex.toString() + '\n');
    }
  }

  private class ButtonListener implements ActionListener {
    public void actionPerformed(ActionEvent e) {
      try {
        // Get the row no. from the text field
        int rowNo = Integer.parseInt(jtf.getText().trim());
        if(rowNo >= 5 || rowNo <= 0){
            jta.append("\n" + "Row number out of table range..." + "\n");
        }
        else{
        // Send the quote no. to the server
        toServer.writeInt(rowNo);
        toServer.flush();

        // Get quote from the server
        String rowData = quotefromServer.readUTF();

        // Display to the text area

            jta.append("\n" + "Retrieve row " + rowNo + " from Customer table" + "\n");
            jta.append("Row " + rowNo + " : " + rowData + '\n');

        }
      }catch (IOException ex) {
        jta.append(ex.toString() + '\n');
      }
    }
  }
}

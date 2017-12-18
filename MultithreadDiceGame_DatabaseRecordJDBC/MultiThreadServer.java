package DiceGame;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

/**
 *
 * @author Karen
 */
public class MultiThreadServer extends JFrame {

    /**
     * Creates new form MultiThreadServer
     */
    public MultiThreadServer() {
        initComponents();
        setTitle("MultiThreadServer");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true); // It is necessary to show the frame here!
        outer:
        try {
            int maxConnections = 3;
            // Create a server socket
            ServerSocket serverSocket = new ServerSocket(8000, maxConnections);
            jTextArea1.append("MultiThreadServer started" + '\n');
            
            // Number a client
            int clientNo = 1;
            int serverDice[] = new int [3];
            // when server roll dice
            
            while(true){
                // Listen for a new connection request
                Socket socket = serverSocket.accept();
                DataOutputStream outputToClient = new DataOutputStream(socket.getOutputStream());
                // Display the client number
                jTextArea1.append('\n' + "Starting thread for player " + clientNo + '\n');

                // Find the client's host name, and IP address
                InetAddress inetAddress = socket.getInetAddress();
                jTextArea1.append("Player " + clientNo + ": connection established..." + "\n" + '\n');
                outputToClient.writeUTF("You are player " + clientNo + ": connection established..."); 
                outputToClient.flush();
                // Increment clientNo
                if (clientNo == 3) {
                    int min = 1;
                    int max = 6;
                    // server to roll dice between number 1-6
                    for (int i = 0; i < 3; i++) {
                        
                        serverDice[i] = (int) (Math.floor(Math.random() * (max - min + 1)) + min);
                        //serverDice[i] = (int) (Math.random() * 6) + 1;
                    }
                    Arrays.sort(serverDice);
                    jTextArea1.append("\n" + "Server rolled three dice, three dice rolled by server are " + Arrays.toString(serverDice) + "\n" + "\n");
                    
                    // store server dice into databse
                    // need to enter command in XAMPP Console; GRANT ALL ON Players.* to ''@'localhost' for access privaliage
                    try{
                        Connection con = null;
                        PreparedStatement st = null;
                        ResultSet rs = null;
                    
                        String clientquery = "INSERT INTO Players(Player, dice1, dice2, dice3) VALUES(?,?,?,?)";
                        try{
                        Class.forName("com.mysql.jdbc.Driver").newInstance();
                        con = DriverManager.getConnection("jdbc:mysql:///Players", "", "");
                        st = con.prepareStatement(clientquery);
                        st.setString(1, "Server");
                        st.setInt(2, serverDice[0]);
                        st.setInt(3, serverDice[1]);
                        st.setInt(4, serverDice[2]);
                        st.executeUpdate();
                        //con.commit();
                        

                        }catch (Exception e) {
                        System.err.println(e);
                        }
                        finally {
                        try { 
                        if(rs != null) 
                        rs .close();
                        if(st != null) 
                        st.close(); 
                        if(con != null) 
                        con.close(); 
                        } catch (Exception e) {
                        }
                    }
                    }catch (Exception e) {
                       jTextArea1.append(e.toString()); 
                    }
                }  
                // Create a new thread for the connection
                HandleAClient thread = new HandleAClient(socket, clientNo, serverDice);
                // Start the new thread
                thread.start();
                clientNo++;

                if (clientNo > maxConnections) {   
                    try{
                        serverSocket.close();
                        break outer;
                    }catch (Exception e) {
                        jTextArea1.append(e.toString());            
                    }
                }
                
            }          
        } catch (Exception e) {
            jTextArea1.append(e.toString());
        }   
    }
    
    
    class HandleAClient extends Thread {

        private Socket socket; // A connected socket
        private Integer cno;
        private int[] serverDice;
        //Construct a thread
        public HandleAClient(Socket socket, Integer cno, int[] serverDice) {
            this.socket = socket;
            this.cno = cno;
            this.serverDice = serverDice;
        }
        
        // Run a thread
        public void run() {
            try {
                DataInputStream inputFromClient = new DataInputStream(socket.getInputStream());
                DataOutputStream outputToClient = new DataOutputStream(socket.getOutputStream());
                //get dices guess from client
                int diceFromClient[] = new int[3];
                for(int i=0;i<3;i++){
                   diceFromClient[i] = inputFromClient.readInt();
                }
                Arrays.sort(diceFromClient);
                jTextArea1.append("Guess of the three dice from player " + cno + " are " + Arrays.toString(diceFromClient) + "\n");
                jTextArea1.append("Server three dice are " + Arrays.toString(serverDice) + "\n");
                
                //store all client dice into DB
                try{
                    Connection con = null;
                    PreparedStatement st = null;
                    ResultSet rs = null;
                    String clientquery = "INSERT INTO Players(Player, dice1, dice2, dice3) VALUES(?,?,?,?)";
                    try{
                        Class.forName("com.mysql.jdbc.Driver").newInstance();
                        con = DriverManager.getConnection("jdbc:mysql:///Players", "", "");
                        st = con.prepareStatement(clientquery);
                        st.setString(1, "Client"+cno);
                        st.setInt(2, diceFromClient[0]);
                        st.setInt(3, diceFromClient[1]);
                        st.setInt(4, diceFromClient[2]);
                        st.executeUpdate();
                        //con.commit();
                        

                    }catch (Exception e) {
                       jTextArea1.append(e.toString()); 
                    }
                    finally { 
                      try{
                      if(rs != null) 
                        rs .close();
                      if(st != null) 
                        st.close(); 
                      if(con != null) 
                        con.close(); 
                      }catch(Exception e){
                          
                      }
                    }
                
                }catch (Exception e) {
                   jTextArea1.append(e.toString()); 
                }
                
                //do comparison
                int clientSum = 0;
                int serverSum = 0;
                
                for(int i : diceFromClient) {       
                    clientSum += i;
                }
                for(int i : serverDice) {       
                    serverSum += i;
                }
                
                if(Arrays.equals(diceFromClient, serverDice)){
                    jTextArea1.append("Guessed all three of the server dice!" + "\n" + "\n");
                    outputToClient.writeUTF("Guessed all three of the server dice!" + "\n"); 
                    outputToClient.flush();
                }else if(clientSum == serverSum){
                    jTextArea1.append("Only sum of the guessed dice match with the sum of the server dice." + "\n" + "\n");
                    outputToClient.writeUTF("Only sum of the guessed dice match with the sum of the server dice." + "\n"); 
                    outputToClient.flush();
                }else{
                    jTextArea1.append("No match." + "\n" + "\n");
                    outputToClient.writeUTF("No match" + "\n"); 
                    outputToClient.flush();
                }
                
            } catch (IOException e) {
                jTextArea1.append(e.toString()); 
            }
        }
    }
        /**
         * This method is called from within the constructor to initialize the
         * form. WARNING: Do NOT modify this code. The content of this method is
         * always regenerated by the Form Editor.
         */
        @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 388, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 288, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

        /**
         * @param args the command line arguments
         */
        public static void main(String args[]) {
            new MultiThreadServer();
            /* Set the Nimbus look and feel */
            //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
            /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
             */
            try {
                for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                    if ("Nimbus".equals(info.getName())) {
                        javax.swing.UIManager.setLookAndFeel(info.getClassName());
                        break;
                    }
                }
            } catch (ClassNotFoundException ex) {
                java.util.logging.Logger.getLogger(MultiThreadServer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            } catch (InstantiationException ex) {
                java.util.logging.Logger.getLogger(MultiThreadServer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                java.util.logging.Logger.getLogger(MultiThreadServer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            } catch (javax.swing.UnsupportedLookAndFeelException ex) {
                java.util.logging.Logger.getLogger(MultiThreadServer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }
            //</editor-fold>

            /* Create and display the form */
            java.awt.EventQueue.invokeLater(new Runnable() {
                public void run() {
                    //new MultiThreadServer().setVisible(true);
                }
            });
        }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    // End of variables declaration//GEN-END:variables
}

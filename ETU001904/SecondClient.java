import java.awt.event.KeyEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class SecondClient extends javax.swing.JFrame 
{

    static Socket SendSocket = null;
    //************************************************************************//
    public SecondClient() 
    {
        initComponents();
        SendArea.requestFocusInWindow();
        setLocation(getWidth(), 0);
    }

    @SuppressWarnings("unchecked")
    private void initComponents() 
    {

        jSplitPane1 = new javax.swing.JSplitPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        RecievedArea = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        SendArea = new javax.swing.JTextArea();
        SendButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Second Client");

        jSplitPane1.setDividerLocation(240);
        jSplitPane1.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);

        RecievedArea.setColumns(20);
        RecievedArea.setRows(5);
        jScrollPane1.setViewportView(RecievedArea);

        jSplitPane1.setTopComponent(jScrollPane1);

        SendArea.setColumns(20);
        SendArea.setRows(1);
        SendArea.addKeyListener
        (
            new java.awt.event.KeyAdapter() 
            {
                public void keyPressed(java.awt.event.KeyEvent evt) 
                {
                    SendAreaKeyPressed(evt);
                }
            }
        );
        jScrollPane2.setViewportView(SendArea);

        jSplitPane1.setRightComponent(jScrollPane2);

        SendButton.setText("Envoyer");
        SendButton.addActionListener
        (
            new java.awt.event.ActionListener() 
            {
                public void actionPerformed(java.awt.event.ActionEvent evt) 
                {
                    SendButtonActionPerformed(evt);
                }
            }
        );

        jLabel1.setForeground(new java.awt.Color(204, 0, 0));
        jLabel1.setText("Devoir socket ETU001904");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup
        (
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 420, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(163, 163, 163)
                        .addComponent(SendButton))
                    .addComponent(jLabel1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup
        (
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jSplitPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(SendButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addContainerGap())
        );

        pack();
    }

    //************************************************************************//
    private void SendButtonActionPerformed(java.awt.event.ActionEvent evt) 
    {
        SendMessage(SendArea.getText());
        SendArea.setText("");
    }
    
//************************************************************************//
    //Cliquez sur l'événement
    private void SendAreaKeyPressed(java.awt.event.KeyEvent evt) 
    {
        if (KeyEvent.getKeyText(evt.getKeyChar()).equals("Enter")) 
        {
            SendButtonActionPerformed(null);
            SendArea.setCaretPosition(0);
        }
    }
    
    //************************************************************************//
    public static void main(String args[]) 
    {
        java.awt.EventQueue.invokeLater
        (
            new Runnable() 
            {
                public void run() {
                    new SecondClient().setVisible(true);
                }
            }
        );
          OpenSocketAndRecieve();
    }
    
    //************************************************************************//
    public static void OpenSocketAndRecieve() 
    {
        new Thread() 
        {
            @Override
            public void run() 
            {
                try 
                {
                    ServerSocket server = new ServerSocket(12346);
                    while (true) 
                    {
                        Socket socket = server.accept();
                        DataInputStream input = new DataInputStream(socket.getInputStream());
                        RecievedArea.append("\n" + input.readUTF());
                    }

                } 
                catch (Exception e) 
                {
                    e.printStackTrace();
                }
            }
        }.start();

    }
    
    //************************************************************************//
    public static void SendMessage(String text) 
    {
        try 
        {
            SendSocket = new Socket("127.0.0.1", 12345);
            DataOutputStream out = new DataOutputStream(SendSocket.getOutputStream());
            out.writeUTF("L'autre : " + text);
            RecievedArea.append("\n Moi : " + text);
        } 
        catch (Exception e) 
        {
            System.out.println(e);
        }

    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private static javax.swing.JTextArea RecievedArea;
    private javax.swing.JTextArea SendArea;
    private javax.swing.JButton SendButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSplitPane jSplitPane1;
    // End of variables declaration//GEN-END:variables
}

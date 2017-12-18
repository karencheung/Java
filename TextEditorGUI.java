/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TextEditor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FileDialog;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Document;
import javax.swing.text.Highlighter;
import javax.swing.text.JTextComponent;

/**
 *
 * @author K
 */
public class TextEditorGUI extends javax.swing.JFrame {

    String filename;
    Clipboard clipboard = getToolkit().getSystemClipboard();  // create object for clipboard, getToolkit get the toolkit of this component to use in the jframe
    
    public TextEditorGUI() {
        initComponents();
        jPanel1.setVisible(false);
        jToolBar1.setFloatable(false);
        this.setLayout(new BorderLayout());
        this.setBounds(0,0,600,600);
        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        TextArea = new javax.swing.JTextArea();
        jPanel1 = new javax.swing.JPanel();
        SearchButton = new javax.swing.JButton();
        SearchBar = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jToolBar1 = new javax.swing.JToolBar();
        newIcon = new javax.swing.JButton();
        openIcon = new javax.swing.JButton();
        saveIcon = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        New = new javax.swing.JMenuItem();
        Open = new javax.swing.JMenuItem();
        Save = new javax.swing.JMenuItem();
        Exit = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        Cut = new javax.swing.JMenuItem();
        Copy = new javax.swing.JMenuItem();
        Paste = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        Search = new javax.swing.JMenuItem();

        TextArea.setColumns(20);
        TextArea.setRows(5);
        jScrollPane2.setViewportView(TextArea);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setMaximumSize(new java.awt.Dimension(32757, 32767));
        jPanel1.setPreferredSize(new java.awt.Dimension(500, 400));

        SearchButton.setText("Search");
        SearchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SearchButtonActionPerformed(evt);
            }
        });

        SearchBar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SearchBarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(SearchBar, javax.swing.GroupLayout.DEFAULT_SIZE, 503, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(SearchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(SearchButton)
                .addComponent(SearchBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jToolBar1.setRollover(true);

        newIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/TextEditor/newFile.png"))); // NOI18N
        newIcon.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        newIcon.setFocusable(false);
        newIcon.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        newIcon.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        newIcon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newIconActionPerformed(evt);
            }
        });
        jToolBar1.add(newIcon);

        openIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/TextEditor/openFile.png"))); // NOI18N
        openIcon.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        openIcon.setFocusable(false);
        openIcon.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        openIcon.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        openIcon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openIconActionPerformed(evt);
            }
        });
        jToolBar1.add(openIcon);

        saveIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/TextEditor/saveFile.png"))); // NOI18N
        saveIcon.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        saveIcon.setFocusable(false);
        saveIcon.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        saveIcon.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        saveIcon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveIconActionPerformed(evt);
            }
        });
        jToolBar1.add(saveIcon);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 5, Short.MAX_VALUE))
        );

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 463, Short.MAX_VALUE)
                .addContainerGap())
        );

        jMenu1.setText("File");

        New.setText("New");
        New.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NewActionPerformed(evt);
            }
        });
        jMenu1.add(New);

        Open.setText("Open");
        Open.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OpenActionPerformed(evt);
            }
        });
        jMenu1.add(Open);

        Save.setText("Save");
        Save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SaveActionPerformed(evt);
            }
        });
        jMenu1.add(Save);

        Exit.setText("Exit");
        Exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExitActionPerformed(evt);
            }
        });
        jMenu1.add(Exit);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");

        Cut.setText("Cut");
        Cut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CutActionPerformed(evt);
            }
        });
        jMenu2.add(Cut);

        Copy.setText("Copy");
        Copy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CopyActionPerformed(evt);
            }
        });
        jMenu2.add(Copy);

        Paste.setText("Paste");
        Paste.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PasteActionPerformed(evt);
            }
        });
        jMenu2.add(Paste);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("Help");

        Search.setText("Search");
        Search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SearchActionPerformed(evt);
            }
        });
        jMenu3.add(Search);

        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void SearchBarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SearchBarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SearchBarActionPerformed

    private void SaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SaveActionPerformed
        FileDialog fileDialog = new FileDialog(TextEditorGUI.this, "Save File", FileDialog.SAVE);// need file Dialog window to save file, use .SAVE method
        fileDialog.setVisible(true);  // set window visible
        if(fileDialog.getFile() != null){
            filename = fileDialog.getDirectory() + fileDialog.getFile();
            setTitle(filename);
        }
        try{
            FileWriter fileWriter = new FileWriter(filename);   // write character to a new save file
            fileWriter.write(jTextArea1.getText());    // take text from text area
            setTitle(filename);
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("File Not Found");
        }
    }//GEN-LAST:event_SaveActionPerformed

    private void NewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NewActionPerformed
        // reset text area and filename to empty string
        jTextArea1.setText("");  // jTextArea1 is the text area, an object ofJTextArea
        filename = " ";
        setTitle(filename);
    }//GEN-LAST:event_NewActionPerformed

    private void OpenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OpenActionPerformed
        // FileDialog display a dialog window which use can select file from, FileDialog extends Dialog from java.awt
        FileDialog fileDialog = new FileDialog(TextEditorGUI.this, "Open File", FileDialog.LOAD);  // inside parameter the JFrame class name, title, .LOAD method load the file
        fileDialog.setVisible(true);  // set window visible
        if(fileDialog.getFile() != null){
            filename = fileDialog.getDirectory() + fileDialog.getFile();
            setTitle(filename);
        }
        // to read the file
        try{
            BufferedReader reader = new BufferedReader(new FileReader(filename)); //read text from character input stream, create file reader to read file from Dialog window
            StringBuilder sb = new StringBuilder();   //to build string
            String line = null;
            while((line = reader.readLine()) != null){  //check the condition by use of bufferread read object
                sb.append(line + "\n"); //pass in line object with escape sequence so loop end after read line
                jTextArea1.setText(sb.toString());   //use the string builder and convert to String when set new text in text area
            }
            reader.close();
        }catch(Exception e){
            System.out.println("File Not Found");
        }
             
    }//GEN-LAST:event_OpenActionPerformed

    private void ExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExitActionPerformed
        System.exit(0);
    }//GEN-LAST:event_ExitActionPerformed

    private void SearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SearchActionPerformed
        /*if ((!SearchButton.isVisible())&&(!SearchBar.isVisible())) {
            SearchButton.setVisible(true);
            SearchBar.setVisible(true);
        }
        else if ((SearchButton.isVisible())&&(SearchBar.isVisible())) {
            SearchButton.setVisible(false);
            SearchBar.setVisible(false);*/
        if (!jPanel1.isVisible()) {
            jPanel1.setVisible(true);
        }
        else if (jPanel1.isVisible()) {
            jPanel1.setVisible(false);
        }
    }//GEN-LAST:event_SearchActionPerformed

    private void SearchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SearchButtonActionPerformed
       //searchTextArea(jTextArea1, SearchBar.getText());
        if(!"".equals(SearchBar.getText())){
           searchTextArea(jTextArea1, SearchBar.getText());
       }else{
           removeHighLight(jTextArea1);
       }
       
    }//GEN-LAST:event_SearchButtonActionPerformed

    private void CopyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CopyActionPerformed
        String copyText = jTextArea1.getSelectedText();
        StringSelection copySelection = new StringSelection(copyText);
        clipboard.setContents(copySelection, copySelection);
    }//GEN-LAST:event_CopyActionPerformed

    private void CutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CutActionPerformed
        String cutString = jTextArea1.getSelectedText();
        StringSelection cutSelection = new StringSelection(cutString);
        clipboard.setContents(cutSelection, cutSelection);  // inside parameter is the range start to end selection
        //to past it after saving
        jTextArea1.replaceRange("", jTextArea1.getSelectionStart(), jTextArea1.getSelectionEnd());
    }//GEN-LAST:event_CutActionPerformed

    private void PasteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PasteActionPerformed
        try{
            Transferable pasteText = clipboard.getContents(TextEditorGUI.this);
            String sel = (String) pasteText.getTransferData(DataFlavor.stringFlavor);
            jTextArea1.replaceRange(sel, jTextArea1.getSelectionStart(), jTextArea1.getSelectionEnd());
        }catch(Exception e){
            System.out.println("Didn't Work");
        }
    }//GEN-LAST:event_PasteActionPerformed

    private void newIconActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newIconActionPerformed
        jTextArea1.setText("");
        filename = " ";
        setTitle(filename);
    }//GEN-LAST:event_newIconActionPerformed

    private void openIconActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openIconActionPerformed
        FileDialog fileDialog = new FileDialog(TextEditorGUI.this, "Open File", FileDialog.LOAD);  // inside parameter the JFrame class name, title, .LOAD method load the file
        fileDialog.setVisible(true);  // set window visible
        if(fileDialog.getFile() != null){  //Gets the selected file of this file dialog, if user selected CANCEL, the returned file is null
            filename = fileDialog.getDirectory() + fileDialog.getFile();   // gets the directory of this file dialog and gets the selected file of this file dialog
            setTitle(filename);
        }
        // to read the file
        try{
            BufferedReader reader = new BufferedReader(new FileReader(filename)); //read text from character input stream, create file reader to read file from Dialog window
            StringBuilder sb = new StringBuilder();   //to build string
            String line = null;
            while((line = reader.readLine()) != null){  //check the condition by use of bufferread read object
                sb.append(line + "\n"); //pass in line object with escape sequence so loop end after read line
                jTextArea1.setText(sb.toString());   //use the string builder and convert to String when set new text in text area
            }
            reader.close();
        }catch(Exception e){
            System.out.println("File Not Found");
        }
    }//GEN-LAST:event_openIconActionPerformed

    private void saveIconActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveIconActionPerformed
        FileDialog fileDialog = new FileDialog(TextEditorGUI.this, "Save File", FileDialog.SAVE);// need file Dialog window to save file, use .SAVE method
        fileDialog.setVisible(true);  // set window visible
        if(fileDialog.getFile() != null){
            filename = fileDialog.getDirectory() + fileDialog.getFile();
            setTitle(filename);
        }
        try{
            FileWriter fileWriter = new FileWriter(filename);   // write character to a new save file
            fileWriter.write(jTextArea1.getText());    // take text from text area
            setTitle(filename);
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("File Not Found");
        }
    }//GEN-LAST:event_saveIconActionPerformed

// need highlighter class for searched text
class myHighlighter extends DefaultHighlighter.DefaultHighlightPainter{  // constructs new highlight painter
    public myHighlighter(Color color){  //constructor
        super(color);
    }
}
DefaultHighlighter.HighlightPainter highlighter = new myHighlighter(Color.yellow);  //create object

public void removeHighLight(JTextComponent textComp){  // JTextComponent will take a jTextArea
    Highlighter removeHighlighter = textComp.getHighlighter();  // public Highlighter getHighlighter() fetches the object responsible for making highlights.
    Highlighter.Highlight[] remove =  removeHighlighter.getHighlights();  // Highlighter.Highlight[] getHighlights() fetches the current list of highlights
    
    for(int i = 0; i<remove.length; i++){
        if(remove[i].getPainter() instanceof myHighlighter){
        removeHighlighter.removeHighlight(remove[i]);   // removeHighlight(Object tag) removes highlight from the view.
        }
    }
}
// method that search text from text area
public void searchTextArea(JTextComponent textComp, String textString){
    removeHighLight(textComp);  // remove previous text
    try{
        Highlighter highlight = textComp.getHighlighter(); // new highlighter object using parameter textComp and pass in method getHighlighter
        Document doc = textComp.getDocument();  
        String text = doc.getText(0, doc.getLength());  //use doc object
        
        int pos = 0;
        while((pos = text.toUpperCase().indexOf(textString.toUpperCase(), pos)) >= 0){
            highlight.addHighlight(pos, pos+textString.length(), highlighter);
            pos += textString.length();
        }
    }catch(Exception e){
        System.out.println("error");
    }
}
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
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
            java.util.logging.Logger.getLogger(TextEditorGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TextEditorGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TextEditorGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TextEditorGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TextEditorGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem Copy;
    private javax.swing.JMenuItem Cut;
    private javax.swing.JMenuItem Exit;
    private javax.swing.JMenuItem New;
    private javax.swing.JMenuItem Open;
    private javax.swing.JMenuItem Paste;
    private javax.swing.JMenuItem Save;
    private javax.swing.JMenuItem Search;
    private javax.swing.JTextField SearchBar;
    private javax.swing.JButton SearchButton;
    private javax.swing.JTextArea TextArea;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JButton newIcon;
    private javax.swing.JButton openIcon;
    private javax.swing.JButton saveIcon;
    // End of variables declaration//GEN-END:variables
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectafterupdatev1.pkg2;

import java.io.*;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Bassel
 */
public class Games_Records extends javax.swing.JFrame {
    Object[][] tabledata;
    int selectedgameid=0;
    Object[] tableheader={"GameID","Player Name", "Enemy name", "Result", "Start Date", "End Date"};
    int x=-1;
    /**
     * Creates new form Games_Records
     */
    public Games_Records() {
        
        initComponents();
        
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        jTable1.setDefaultRenderer(Object.class, centerRenderer);
        setLocation(480,175);
        setSize(700,460);
    }
 public  void setData(Object[][] t){
        tabledata=t;
        jTable1.setModel(new DefaultTableModel(tabledata,tableheader){
        public boolean isCellEditable(int rowIndex, int columnIndex) {
                return false;
            }
        
        });
        /*if (tabledata.length<=6)
        jScrollPane1.setSize(680,25*(tabledata.length+1));
        else
        jScrollPane1.setSize(680,200);*/
        jScrollPane1.setSize(680,350);
    }
  public void sort(){
        jTextField1.setVisible(false);
        showbtn.setVisible(false);
        TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(jTable1.getModel());
        jTable1.setRowSorter(sorter);

        List<RowSorter.SortKey> sortKeys = new ArrayList<>(25);
        sortKeys.add(new RowSorter.SortKey(0, SortOrder.ASCENDING));
        sortKeys.add(new RowSorter.SortKey(1, SortOrder.ASCENDING));
        //sortKeys.add(new RowSorter.SortKey(4, SortOrder.ASCENDING));
        sorter.setSortKeys(sortKeys);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDialog1 = new javax.swing.JDialog();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        showbtn = new javax.swing.JButton();
        selected = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();

        jDialog1.setResizable(false);
        jDialog1.getContentPane().setLayout(null);

        jLabel3.setBackground(new java.awt.Color(255, 0, 0));
        jLabel3.setFont(new java.awt.Font("Tahoma", 3, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("There is no game with this ID");
        jDialog1.getContentPane().add(jLabel3);
        jLabel3.setBounds(30, 60, 400, 50);

        jLabel2.setBackground(new java.awt.Color(255, 0, 51));
        jLabel2.setOpaque(true);
        jDialog1.getContentPane().add(jLabel2);
        jLabel2.setBounds(0, 0, 470, 180);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(null);

        jTable1.setBackground(new java.awt.Color(102, 102, 102));
        jTable1.setForeground(new java.awt.Color(255, 255, 255));
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "GameID", "Player Name", "Enemy name", "Result", "Start Date", "End Date"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setRowHeight(24);
        jTable1.setRowSelectionAllowed(false);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(0, 0, 680, 290);

        showbtn.setBackground(new java.awt.Color(0, 153, 153));
        showbtn.setForeground(new java.awt.Color(255, 255, 255));
        showbtn.setText("Show Toturial");
        showbtn.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        showbtn.setFocusPainted(false);
        showbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showbtnActionPerformed(evt);
            }
        });
        getContentPane().add(showbtn);
        showbtn.setBounds(550, 390, 120, 40);

        selected.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        selected.setForeground(new java.awt.Color(255, 0, 0));
        selected.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        selected.setText("Select a game");
        getContentPane().add(selected);
        selected.setBounds(270, 390, 160, 30);

        jTextField1.setEditable(false);
        jTextField1.setForeground(new java.awt.Color(255, 255, 255));
        jTextField1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField1.setText("0");
        jTextField1.setOpaque(false);
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        getContentPane().add(jTextField1);
        jTextField1.setBounds(460, 400, 59, 20);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/projectafterupdatev1/pkg2/0b705f621e0ae6c716e845c95a67715c.jpg"))); // NOI18N
        getContentPane().add(jLabel1);
        jLabel1.setBounds(0, 0, 680, 440);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        showbtn.setVisible(true);
        jTextField1.setVisible(true);
        selected.setText("Selected Game");
        //System.out.println(tabledata[jTable1.getSelectedRow()][0]);
        jTextField1.setText(jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString());
        selectedgameid=Integer.parseInt(jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString());
        repaint();
        
    }//GEN-LAST:event_jTable1MouseClicked

    private void showbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showbtnActionPerformed
           
                //test if in the tuturial file there is the game id 
        boolean foundit=false;
        WritableToturialClass temp=null;
            File f=new File(new File("").getAbsolutePath()+"\\src\\projectafterupdatev1\\pkg2\\ToturialFile");
            ObjectInputStream obj=null;
        try {
            obj=new ObjectInputStream(new FileInputStream(f));
            
            while (true){
                temp=(WritableToturialClass)obj.readObject();
                System.out.println(temp.gameID);
                if(Objects.equals(temp.gameID,selectedgameid))
                {
                    foundit=true;
                    break;
                }
            }
        }catch(EOFException e){   System.out.println("Sds1");
        } catch (IOException ex) {System.out.println("Sds2");
        } catch (ClassNotFoundException ex) {System.out.println("Sds3");
        }finally{try {
            obj.close();
            } catch (IOException ex) {
            }
        }
        if (foundit==true){
            //show the toturial show and shwo the table
                this.setVisible(false);
            Player_Board myplayerboard= new Player_Board();
            myplayerboard.setTheDataForToturial(temp.grid,temp.note,temp.header,temp.moves);
            myplayerboard.setVisible(true);
        }else
        {
            jDialog1.setSize(480,200);
            jDialog1.setVisible(true);
        }
        
    }//GEN-LAST:event_showbtnActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

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
            java.util.logging.Logger.getLogger(Games_Records.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Games_Records.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Games_Records.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Games_Records.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Games_Records().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDialog jDialog1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel selected;
    private javax.swing.JButton showbtn;
    // End of variables declaration//GEN-END:variables
}
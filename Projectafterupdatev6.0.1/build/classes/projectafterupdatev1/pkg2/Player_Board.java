/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectafterupdatev1.pkg2;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import java.util.*;
import javax.swing.DefaultComboBoxModel;
import java.awt.*;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javafx.beans.binding.Bindings.length;
import javax.swing.*;
import javax.swing.table.*;

/**
 *
 * @author Bassel
 */
public class Player_Board extends javax.swing.JFrame implements Serializable {

    List<Character> rowdata = new ArrayList<Character>();
    List<String> rownumber = new ArrayList<String>();
    static boolean dontAttackAgain[][] = new boolean[1000][1000];
    static Integer timer = 0;
    Object[][] note, board;
    List<String> moves = new ArrayList<String>();

    /**
     * Creates new form Player_Board
     */
    //refixing the table size
    public void fixtablessize(int n, int n1) {
        if (n <= 1) {
            n = 2;
        }
        if (n1 <= 1) {
            n1 = 2;
        }
        n++;
        n1++;
        //the sizable tables after knowing the number of elemnts
        jScrollPane3.setSize(n * 320 / 11, n1 * 233 / 11);
        jScrollPane4.setSize(n * 320 / 11, n1 * 233 / 11);
        int x = jScrollPane4.getX();
        int y = jScrollPane4.getY();
        //location of the second table
        jScrollPane3.setLocation(x + n * 320 / 11 + 60, y);
        //size of the JFrame
        this.setSize(10 + n * 320 / 11 + n * 320 / 11 + 60 + 30, 90 + 20 + n1 * 233 / 11 + 90 + 90 + 50 + 20);
        playerBackGround.setSize(10 + n * 320 / 11 + n * 320 / 11 + 60 + 30, 90 + 20 + n1 * 233 / 11 + 90 + 90 + 50);

        //the position of the words
        jLabel1.setLocation(x, y - 20);
        jLabel2.setLocation(x + n * 320 / 11 + 60, y - 20);
        //change the position of the attack button and panel
        attackbtn.setLocation(getWidth() / 2, getHeight() - 100);
        jPanel1.setLocation(getWidth() / 2 - 110, getHeight() - 120);
        turntimer.setLocation(getWidth() / 2 - 110, getHeight() - 150);
        time.setLocation(getWidth() / 2 + 20, getHeight() - 150);
        turnname.setLocation(getWidth() / 2 - 50, 70);
        SaveButton.setLocation(1, 1);
        SaveButton.setSize(60, 30);
    }

    public void repaintaftersecondscount() {
        timer++;
        time.setText(timer.toString());
        this.repaint();
    }

    public void secondstimerreset() {
        timer = 0;
        time.setText(timer.toString());
        this.repaint();
    }

    //repaint after updating the table

    public void repaintafterupdate() {
        boardtable.setModel(new javax.swing.table.DefaultTableModel(
                BattleShipGame.boarddata,
                BattleShipGame.boardsheader
        ) {

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                // return canEdit [columnIndex];
                return false;
            }
        });
        notetable.setModel(new javax.swing.table.DefaultTableModel(
                BattleShipGame.notedata,
                BattleShipGame.boardsheader
        ) {

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                //return canEdit [columnIndex];
                return false;
            }
        });

    }

    public void SetTurn(String s) {
        turnname.setText(s);
    }

    public Player_Board() {

        initComponents();
        jLabel5.setVisible(false);
        jComboBox1.setVisible(false);
        //initilize the timer with the time 0 
        time.setText(timer + "");
        for (int i = 1; i <= GameOptions.Lengthoftheship; i++) {
            rownumber.add(i + "");
        }
        axisrow.setModel(new DefaultComboBoxModel(rownumber.toArray()));
        rowdata.clear();
        for (int i = 0; i < GameOptions.Widthoftheship; i++) {
            rowdata.add((char) (i + 'a'));
        }
        axiscol.setModel(new DefaultComboBoxModel(rowdata.toArray()));
        boardtable.setModel(new javax.swing.table.DefaultTableModel(
                BattleShipGame.boarddata,
                BattleShipGame.boardsheader
        ) {

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return false;
            }
        });
        notetable.setModel(new javax.swing.table.DefaultTableModel(
                BattleShipGame.notedata,
                BattleShipGame.boardsheader
        ) {

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return false;
            }
        });

      //mouse listener testing new methode
        //boardtable.addMouseListener(mouseshit);
        //notetable.addMouseListener(mouseshit);
        //centering the cells in tables
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        boardtable.setDefaultRenderer(Object.class, centerRenderer);
        notetable.setDefaultRenderer(Object.class, centerRenderer);
    }

    //load and update the combox box
    public void LoadingComboBox(int width, int length) {
        rownumber.clear();
        for (int i = 1; i <= width; i++) {
            rownumber.add(i + "");
        }
        axisrow.setModel(new DefaultComboBoxModel(rownumber.toArray()));
        rowdata.clear();
        for (int i = 0; i < length; i++) {
            rowdata.add((char) (i + 'a'));
        }
        axiscol.setModel(new DefaultComboBoxModel(rowdata.toArray()));
        repaint();
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
        jTable2 = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        boardtable = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        notetable = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        axiscol = new javax.swing.JComboBox();
        axisrow = new javax.swing.JComboBox();
        attackbtn = new javax.swing.JButton();
        time = new javax.swing.JLabel();
        turntimer = new javax.swing.JLabel();
        turnname = new javax.swing.JLabel();
        SaveButton = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        playerBackGround = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(jTable2);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Player Board");
        setResizable(false);
        getContentPane().setLayout(new java.awt.GridLayout(1, 0));

        jPanel2.setLayout(null);

        boardtable.setBackground(new java.awt.Color(102, 102, 102));
        boardtable.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, null, java.awt.Color.lightGray, java.awt.Color.lightGray));
        boardtable.setForeground(new java.awt.Color(255, 255, 255));
        boardtable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"1", null, null, null, null, null, null, null, null, null, null},
                {"2", null, null, null, null, null, null, null, null, null, null},
                {"3", null, null, null, null, null, null, null, null, null, null},
                {"4", null, null, null, null, null, null, null, null, null, null},
                {"5", null, null, null, null, null, null, null, null, null, null},
                {"6", null, null, null, null, null, null, null, null, null, null},
                {"7", null, null, null, null, null, null, null, null, null, null},
                {"8", null, null, null, null, null, null, null, null, null, null},
                {"9", null, null, null, null, null, null, null, null, null, null},
                {"10", null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        boardtable.setFillsViewportHeight(true);
        boardtable.setRowHeight(20);
        boardtable.setRowSelectionAllowed(false);
        jScrollPane4.setViewportView(boardtable);
        if (boardtable.getColumnModel().getColumnCount() > 0) {
            boardtable.getColumnModel().getColumn(0).setResizable(false);
            boardtable.getColumnModel().getColumn(1).setResizable(false);
            boardtable.getColumnModel().getColumn(2).setResizable(false);
            boardtable.getColumnModel().getColumn(3).setResizable(false);
            boardtable.getColumnModel().getColumn(4).setResizable(false);
            boardtable.getColumnModel().getColumn(5).setResizable(false);
            boardtable.getColumnModel().getColumn(6).setResizable(false);
            boardtable.getColumnModel().getColumn(7).setResizable(false);
            boardtable.getColumnModel().getColumn(8).setResizable(false);
            boardtable.getColumnModel().getColumn(9).setResizable(false);
            boardtable.getColumnModel().getColumn(10).setResizable(false);
        }

        jPanel2.add(jScrollPane4);
        jScrollPane4.setBounds(20, 140, 320, 230);

        notetable.setBackground(new java.awt.Color(102, 102, 102));
        notetable.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.gray, java.awt.Color.gray, java.awt.Color.lightGray, java.awt.Color.lightGray));
        notetable.setForeground(new java.awt.Color(255, 255, 255));
        notetable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"1", null, null, null, null, null, null, null, null, null, null},
                {"2", null, null, null, null, null, null, null, null, null, null},
                {"3", null, null, null, null, null, null, null, null, null, null},
                {"4", null, null, null, null, null, null, null, null, null, null},
                {"5", null, null, null, null, null, null, null, null, null, null},
                {"6", null, null, null, null, null, null, null, null, null, null},
                {"7", null, null, null, null, null, null, null, null, null, null},
                {"8", null, null, null, null, null, null, null, null, null, null},
                {"9", null, null, null, null, null, null, null, null, null, null},
                {"10", null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        notetable.setFillsViewportHeight(true);
        notetable.setRowHeight(20);
        notetable.setRowSelectionAllowed(false);
        notetable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                notetableMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(notetable);
        if (notetable.getColumnModel().getColumnCount() > 0) {
            notetable.getColumnModel().getColumn(0).setResizable(false);
            notetable.getColumnModel().getColumn(0).setHeaderValue("");
            notetable.getColumnModel().getColumn(1).setResizable(false);
            notetable.getColumnModel().getColumn(1).setHeaderValue("A");
            notetable.getColumnModel().getColumn(2).setResizable(false);
            notetable.getColumnModel().getColumn(2).setHeaderValue("B");
            notetable.getColumnModel().getColumn(3).setResizable(false);
            notetable.getColumnModel().getColumn(3).setHeaderValue("C");
            notetable.getColumnModel().getColumn(4).setResizable(false);
            notetable.getColumnModel().getColumn(4).setHeaderValue("D");
            notetable.getColumnModel().getColumn(5).setResizable(false);
            notetable.getColumnModel().getColumn(5).setHeaderValue("E");
            notetable.getColumnModel().getColumn(6).setResizable(false);
            notetable.getColumnModel().getColumn(6).setHeaderValue("F");
            notetable.getColumnModel().getColumn(7).setResizable(false);
            notetable.getColumnModel().getColumn(7).setHeaderValue("G");
            notetable.getColumnModel().getColumn(8).setResizable(false);
            notetable.getColumnModel().getColumn(8).setHeaderValue("H");
            notetable.getColumnModel().getColumn(9).setResizable(false);
            notetable.getColumnModel().getColumn(9).setHeaderValue("I");
            notetable.getColumnModel().getColumn(10).setResizable(false);
            notetable.getColumnModel().getColumn(10).setHeaderValue("J");
        }

        jPanel2.add(jScrollPane3);
        jScrollPane3.setBounds(390, 140, 320, 230);

        jLabel1.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 0, 0));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Board");
        jPanel2.add(jLabel1);
        jLabel1.setBounds(20, 120, 60, 21);

        jLabel2.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 0, 0));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Note");
        jPanel2.add(jLabel2);
        jLabel2.setBounds(400, 120, 50, 22);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setOpaque(false);
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 0, 0));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("row");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 0, -1, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 0, 0));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("col");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(82, 0, 20, -1));

        axiscol.setBackground(new java.awt.Color(0, 153, 153));
        axiscol.setForeground(new java.awt.Color(255, 255, 255));
        axiscol.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        axiscol.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel1.add(axiscol, new org.netbeans.lib.awtextra.AbsoluteConstraints(58, 25, 40, -1));

        axisrow.setBackground(new java.awt.Color(0, 153, 153));
        axisrow.setForeground(new java.awt.Color(255, 255, 255));
        axisrow.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        axisrow.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel1.add(axisrow, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 25, 40, -1));

        jPanel2.add(jPanel1);
        jPanel1.setBounds(250, 460, 102, 47);

        attackbtn.setBackground(new java.awt.Color(0, 153, 153));
        attackbtn.setForeground(new java.awt.Color(255, 255, 255));
        attackbtn.setText("Attack");
        attackbtn.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        attackbtn.setFocusPainted(false);
        attackbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                attackbtnActionPerformed(evt);
            }
        });
        jPanel2.add(attackbtn);
        attackbtn.setBounds(410, 470, 88, 34);

        time.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        time.setForeground(new java.awt.Color(255, 0, 0));
        jPanel2.add(time);
        time.setBounds(390, 420, 40, 30);

        turntimer.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        turntimer.setForeground(new java.awt.Color(255, 0, 0));
        turntimer.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        turntimer.setText("Time Left");
        jPanel2.add(turntimer);
        turntimer.setBounds(250, 420, 100, 30);

        turnname.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        turnname.setForeground(new java.awt.Color(255, 255, 255));
        turnname.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel2.add(turnname);
        turnname.setBounds(310, 390, 100, 30);

        SaveButton.setBackground(new java.awt.Color(0, 153, 153));
        SaveButton.setForeground(new java.awt.Color(255, 255, 255));
        SaveButton.setText("Save");
        SaveButton.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        SaveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SaveButtonActionPerformed(evt);
            }
        });
        jPanel2.add(SaveButton);
        SaveButton.setBounds(0, 0, 29, 19);

        jComboBox1.setBackground(new java.awt.Color(0, 153, 153));
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel2.add(jComboBox1);
        jComboBox1.setBounds(60, 470, 350, 30);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 0, 0));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Moves");
        jPanel2.add(jLabel5);
        jLabel5.setBounds(90, 420, 90, 30);

        playerBackGround.setForeground(new java.awt.Color(0, 153, 204));
        playerBackGround.setIcon(new javax.swing.ImageIcon(getClass().getResource("/projectafterupdatev1/pkg2/0b705f621e0ae6c716e845c95a67715c.jpg"))); // NOI18N
        playerBackGround.setMaximumSize(new java.awt.Dimension(999999, 999999));
        playerBackGround.setOpaque(true);
        jPanel2.add(playerBackGround);
        playerBackGround.setBounds(0, 0, 1920, 1080);

        getContentPane().add(jPanel2);

        jMenuBar1.setBackground(new java.awt.Color(0, 0, 0));

        jMenu1.setBackground(new java.awt.Color(0, 0, 0));
        jMenu1.setForeground(new java.awt.Color(255, 51, 0));
        jMenu1.setText("Help");

        jMenu2.setText("# Mean -->");

        jMenuItem2.setText("Ship part");
        jMenu2.add(jMenuItem2);

        jMenu1.add(jMenu2);

        jMenu3.setText("N Mean -->");

        jMenuItem3.setText("Unknown");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem3);

        jMenu1.add(jMenu3);

        jMenu4.setText(". Mean -->");

        jMenuItem1.setText("Water");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem1);

        jMenu1.add(jMenu4);

        jMenu5.setText("& Mean -->");

        jMenuItem4.setText("Bomb");
        jMenu5.add(jMenuItem4);

        jMenu1.add(jMenu5);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents
static int waitforhumen = 1;
    private void attackbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_attackbtnActionPerformed
        /*synchronized(waitforhumen){
         try {
         waitforhumen.wait();
         } catch (InterruptedException ex) {
         }
         }*/
        if (waitforhumen == 10) {
            waitforhumen = 1;
            int tempx, tempy;
            tempx = Integer.parseInt(axisrow.getSelectedItem().toString());
            tempy = (axiscol.getSelectedItem().toString().charAt(0) - 'a') + 1;
            //System.out.println("x"+tempx+" "+tempy+ " "+dontAttackAgain[tempx][tempy]);
            if (dontAttackAgain[tempx][tempy] == false) {
                synchronized (HumenPlayer.x) {
                    HumenPlayer.x.notify();
                }
                //change to combobox
                HumenPlayer.x = axisrow.getSelectedItem().toString();
                HumenPlayer.y = axiscol.getSelectedItem().toString();
                //testing new things
                dontAttackAgain[tempx][tempy] = true;
            }
        }
    }//GEN-LAST:event_attackbtnActionPerformed

    public void fixtablessize2(int n, int n1) {
        if (n <= 1) {
            n = 2;
        }
        if (n1 <= 1) {
            n1 = 2;
        }
        n++;
        n1++;
        //the sizable tables after knowing the number of elemnts
        jScrollPane3.setSize(n * 320 / 11, n1 * 233 / 11);
        jScrollPane4.setSize(n * 320 / 11, n1 * 233 / 11);
        int x = jScrollPane4.getX();
        int y = jScrollPane4.getY();
        //location of the second table
        jScrollPane3.setLocation(x + n * 320 / 11 + 60, y);
        //size of the JFrame
        this.setSize(10 + n * 320 / 11 + n * 320 / 11 + 60 + 30 + 60, 90 + 20 + n1 * 233 / 11 + 90 + 90 + 50);
        playerBackGround.setSize(10 + n * 320 / 11 + n * 320 / 11 + 60 + 30 + 60, 90 + 20 + n1 * 233 / 11 + 90 + 90 + 50);

        //the position of the words
        jLabel1.setLocation(x, y - 20);
        jLabel2.setLocation(x + n * 320 / 11 + 60, y - 20);
           //change the position of the attack button and panel
        //attackbtn.setLocation(getWidth()/2,getHeight()-100);
        //jPanel1.setLocation(getWidth()/2-110,getHeight()-120);
        jComboBox1.setLocation(getWidth() / 2 - 160, getHeight() - 120);
        jLabel5.setLocation(getWidth() / 2 - 110, getHeight() - 150);
           //turntimer.setLocation(getWidth()/2-110,getHeight()-150);
        //time.setLocation(getWidth()/2+20,getHeight()-150);
        //turnname.setLocation(getWidth()/2-50,70);
        //SaveButton.setLocation(1,1);
        //SaveButton.setSize(60,30);
    }

    public void setTheDataForToturial(Object[][] Board, Object[][] Note, Object[] header, ToturialClass[] ttt) {
        board = Board;
        note = Note;
        SaveButton.setVisible(false);
        turntimer.setVisible(false);
        attackbtn.setVisible(false);
        jPanel1.setVisible(false);
        notetable.setModel(new javax.swing.table.DefaultTableModel(
                note,
                header
        ) {

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return false;
            }
        });

        boardtable.setModel(new javax.swing.table.DefaultTableModel(
                board,
                header
        ) {

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return false;
            }
        });
        //initlizing the moves combo box
        for (int i = 0; i < ttt.length; i++) {
            this.moves.add(new String((i + 1) + "_" + ttt[i].nameoftheattacker + " (" + ttt[i].xaxis + "," + ttt[i].yaxis + ")" + "  " + ttt[i].date));
        }

        //sizing stuff
        fixtablessize2(board.length, board[0].length);
        jComboBox1.setModel(new DefaultComboBoxModel(moves.toArray()));
        jComboBox1.setVisible(true);

    }
    private void notetableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_notetableMouseClicked
        // TODO add your handling code here:
        Integer row = notetable.rowAtPoint(evt.getPoint()) + 1;
        Integer col = notetable.columnAtPoint(evt.getPoint());
        if (col != 0) {
            if (waitforhumen == 10) {
                waitforhumen = 1;

            }
            if (dontAttackAgain[row][col] == false) {

                synchronized (HumenPlayer.x) {
                    HumenPlayer.x.notify();
                }
                HumenPlayer.x = row.toString();
                HumenPlayer.y = col.toString();
                notetable.repaint();
                dontAttackAgain[row][col] = true;

            }

        }


    }//GEN-LAST:event_notetableMouseClicked

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void SaveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SaveButtonActionPerformed
        //save the game dataB plane b
        //Projectafterupdatev12.game1.SaveGameDate(null, null);
        NewGame newgame = new NewGame();
        newgame.setLocation(380, 250);
        newgame.setSize(500, 250);
        newgame.setVisible(true);

        // TODO add your handling code here:
    }//GEN-LAST:event_SaveButtonActionPerformed

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
            java.util.logging.Logger.getLogger(Player_Board.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Player_Board.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Player_Board.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Player_Board.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Player_Board().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton SaveButton;
    private javax.swing.JButton attackbtn;
    private javax.swing.JComboBox axiscol;
    private javax.swing.JComboBox axisrow;
    private javax.swing.JTable boardtable;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable notetable;
    private javax.swing.JLabel playerBackGround;
    private javax.swing.JLabel time;
    private javax.swing.JLabel turnname;
    private javax.swing.JLabel turntimer;
    // End of variables declaration//GEN-END:variables
}

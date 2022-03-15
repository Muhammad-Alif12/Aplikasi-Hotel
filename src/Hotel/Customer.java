/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hotel;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

/**
 *
 * @author Asus
 */
public class Customer extends javax.swing.JInternalFrame {
    koneksi Konek = new koneksi();
    private Connection Con;
    Statement Stm;
    ResultSet Rs;
    String Sql;
    DefaultTableModel Dtm;

    /**
     * Creates new form Customer
     */
    public Customer() {
        initComponents();
        KosongkanObjek();
        LoadDataKelamin();
        LoadDataAgama();
        TampilCustomer();
        
    }
    
    void KosongkanObjek(){
        jTextField1.setText("");
        jTextField2.setText("");
        jTextField3.setText("");
        jTextField4.setText("");
        jTextField5.setText("");
        jTextField6.setText("");
        jTextField7.setText("");
        jTextField8.setText("");
        jTextField9.setText("");  
        jComboBox1.setSelectedIndex(0);
        jComboBox2.setSelectedIndex(0);      
        jDateChooser1.setCalendar(null);
        jTextField1.requestFocus();
    }
    
    private void LoadDataAgama(){
         jComboBox2.addItem("islam");
        jComboBox2.addItem("kristen");
         jComboBox2.addItem("hindu");
    }
    
    private void LoadDataKelamin(){
        jComboBox1.addItem("L");
        jComboBox1.addItem("P");
    }
    
    private void AturJTable(JTable Lihat, int Lebar[]){
        try{
            Lihat.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            int banyak = Lihat.getColumnCount();
            for (int i = 0; i < banyak; i++) {
            TableColumn Kolom = Lihat.getColumnModel().getColumn(i);
            Kolom.setPreferredWidth(Lebar[i]);
            Lihat.setRowHeight(20);
            }
        }catch (Exception e) {
            JOptionPane.showMessageDialog(null, "salah"+e);
        }
    }
    private void TampilModelJTabel(){
        try {
            String[]kolom={"NIK","NAMA CUSTOMER","ALAMAT",
            "AGAMA","JENIS KELAMIN","PEKERJAAN","NO HP","TEMPAT LAHIR",
            "TANGGAL LAHIRi"};
            Dtm = new DefaultTableModel(null, kolom){
            @Override
                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return false;
                }
            };
            jTable1.setModel(Dtm);
            AturJTable(jTable1, new int[]{100,100,100,100,100,100,100,100,100,100} );
         } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "salah"+e);
           }
    }
    void TampilCustomer(){
        try {
            Con=Konek.getKoneksiDatabase();
            Stm = Con.createStatement();
            TampilModelJTabel();
            try{
                Sql = "SELECT * "+
                " FROM "+
                " customer ";
                Rs = Stm.executeQuery(Sql);
                while(Rs.next()){
                    Dtm.addRow(new Object[]{
                    Rs.getString("nik"),
                    Rs.getString("nama"),
                    Rs.getString("alamat"),
                    Rs.getString("agama"),
                    Rs.getString("jkl"),
                    Rs.getString("pekerjaaan"),
                    Rs.getString("no_hp"),
                    Rs.getString("tempat_lahir"),
                    Rs.getString("tanggal_lahir"),
                    });
                    jTable1.setModel(Dtm);
                }
            }catch(Exception e){
                System.out.println("Ada Kesalahan " + e.toString());
            }
            }catch (SQLException e){
                System.out.println("koneksi gagal " + e.toString());
            }
    }
    
    void cari(){
        try {
            Con=Konek.getKoneksiDatabase();
            Stm = Con.createStatement();
            TampilModelJTabel();
            try{
                Sql = "SELECT * "+
                " FROM "+
                " customer "+
                " where "+jComboBox3.getSelectedItem()+" LIKE'%"+jTextField7.getText()+"%' ";
                Rs = Stm.executeQuery(Sql);
                while(Rs.next()){
                    Dtm.addRow(new Object[]{
                    Rs.getString("nik"),
                    Rs.getString("nama"),
                    Rs.getString("alamat"),
                    Rs.getString("agama"),
                    Rs.getString("jkl"),
                    Rs.getString("pekerjaaan"),
                    Rs.getString("no_hp"),
                    Rs.getString("tempat_lahir"),
                    Rs.getString("tanggal_lahir"),
                    });
                    jTable1.setModel(Dtm);
                }
            }catch(Exception e){
                System.out.println("Ada Kesalahan " + e.toString());
            }
            }catch (SQLException e){
                System.out.println("koneksi gagal " + e.toString());
            }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jTextField6 = new javax.swing.JTextField();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jTextField7 = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jTextField8 = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jTextField9 = new javax.swing.JTextField();
        jComboBox3 = new javax.swing.JComboBox<>();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jLabel1.setText("NIK : ");

        jLabel2.setText("NAMA : ");

        jLabel3.setText("ALAMAT : ");

        jLabel4.setText("PEKERJAAN : ");

        jLabel5.setText("NO HP :");

        jLabel6.setText("TANGGAL LAHIR");

        jLabel7.setText("TEMPAT LAHIR : ");

        jTextField1.setText("jTextField1");

        jTextField2.setText("jTextField2");

        jTextField3.setText("jTextField3");

        jTextField4.setText("jTextField4");

        jTextField5.setText("jTextField5");

        jTextField6.setText("jTextField6");

        jButton1.setText("SIMPAN");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("EDIT");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("HAPUS");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("BATAL");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jTextField7.setText("jTextField7");

        jLabel9.setText("CARI DATA : ");

        jButton5.setText("CARI");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setText("REFF");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jLabel8.setText("JENIS KELAMIN : ");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "[pilih]" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jTextField8.setText("jTextField8");

        jLabel10.setText("AGAMA :");

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "[pilih]" }));
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });

        jTextField9.setText("jTextField9");

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "[pilih]", "nik", "nama", "alamat" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton1)
                                .addGap(18, 18, 18)
                                .addComponent(jButton2)
                                .addGap(18, 18, 18)
                                .addComponent(jButton3)
                                .addGap(18, 18, 18)
                                .addComponent(jButton4))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel4))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(12, 12, 12)
                                        .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel6)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jTextField5, javax.swing.GroupLayout.DEFAULT_SIZE, 312, Short.MAX_VALUE)
                                            .addComponent(jTextField6))
                                        .addGap(152, 152, 152))))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addGap(18, 18, 18)
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3))
                                .addGap(49, 49, 49)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 312, Short.MAX_VALUE)
                                    .addComponent(jTextField2)
                                    .addComponent(jTextField3))
                                .addGap(39, 39, 39)
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jTextField9))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jComboBox3, 0, 107, Short.MAX_VALUE)
                                .addGap(11, 11, 11)
                                .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 404, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton6)))
                        .addGap(7, 7, 7)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel7)
                        .addComponent(jLabel6)
                        .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3)
                    .addComponent(jButton4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(jButton5)
                    .addComponent(jButton6)
                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(79, 79, 79))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
        if (jComboBox1.getSelectedItem().equals("L")){
            jTextField4.setText("Laki - Laki");
        }
        else if (jComboBox1.getSelectedItem().equals("P")){
            jTextField4.setText("Perempuan");
        }
        else {
            jTextField4.setText("");
        }
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
        // TODO add your handling code here:
        if (jComboBox2.getSelectedItem().equals("islam")){
            jTextField9.setText("ISLAM");
        }
        else if (jComboBox2.getSelectedItem().equals("kristen")){
            jTextField9.setText("KRISTEN");
        }
        else if (jComboBox2.getSelectedItem().equals("hindu")){
            jTextField9.setText("HINDU");
        }
        else {
            jTextField9.setText("");
        }
    }//GEN-LAST:event_jComboBox2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        String Tampilan="yyyy-MM-dd";
        SimpleDateFormat Fm = new SimpleDateFormat(Tampilan);
        String TanggalLahir = String.valueOf(Fm.format(jDateChooser1.getDate()));
        try {
            Con = Konek.getKoneksiDatabase();
            Stm = null;
            Sql = "insert into customer (id, nik, nama, alamat, agama, jkl," +
                    "pekerjaaan, no_hp, tempat_lahir, tanggal_lahir )VALUES (NULL,'"+jTextField1.getText()+"', " +
                 "'"+jTextField2.getText()+"','"+jTextField3.getText()+"',"
                    + " '"+jComboBox2.getSelectedItem()+"', '"+jComboBox1.getSelectedItem()+"', "
                    + "'"+jTextField5.getText()+"'," +"'"+jTextField6.getText()+"','"+jTextField8.getText()+"',"
                    + "'"+TanggalLahir+"')";
            Stm= Con.createStatement();
            int AdaPenambahanRecord= Stm.executeUpdate(Sql);
            TampilCustomer();
            if (AdaPenambahanRecord>0)
                JOptionPane.showMessageDialog(this,"Data Berhasil Tersimpan","Informasi",JOptionPane.INFORMATION_MESSAGE);
            else
                JOptionPane.showMessageDialog(this,"Data Gagal Tersimpan","Informasi",JOptionPane.INFORMATION_MESSAGE);
            Stm.close();
            KosongkanObjek();
            
            } catch (SQLException e){
                System.out.println("Koneksi Gagal " +e.toString());
            }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        String Tampilan="yyyy-MM-dd";
        SimpleDateFormat Fm = new SimpleDateFormat(Tampilan);
        String TanggalLahir = String.valueOf(Fm.format(jDateChooser1.getDate()));
        try {
            Con = Konek.getKoneksiDatabase();
            Stm = null;
            Sql = "update customer set nama = '"+jTextField2.getText()+"', alamat = '"+jTextField3.getText()+"', tanggal_lahir= '"+TanggalLahir+"', jkl ='"+jComboBox1.getSelectedItem()+"', pekerjaaan = '"+jTextField5.getText()+"',no_hp='"+jTextField6.getText()+"', agama ='"+jComboBox2.getSelectedItem()+"', tempat_lahir = '"+jTextField8.getText()+"' where nik = '"+jTextField1.getText()+"' ";
            Stm= Con.createStatement();
            int AdaPerubahanRecord= Stm.executeUpdate(Sql);
            TampilCustomer();
            if (AdaPerubahanRecord>0){
                JOptionPane.showMessageDialog(this,"Data Berhasil Di Edit","Informasi",JOptionPane.INFORMATION_MESSAGE);
                jButton1.setEnabled(true);
            }else
                JOptionPane.showMessageDialog(this,"Data Gagal Di Edit","Informasi",JOptionPane.INFORMATION_MESSAGE);
                Stm.close();
                KosongkanObjek();
         } catch (SQLException e){
            System.out.println("Koneksi Gagal " +e.toString());
         } 
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
         try {
             Con = Konek.getKoneksiDatabase();
             Stm = null;
             Sql = "delete from customer where nik = '"+jTextField1.getText()+"'";
             Stm= Con.createStatement();
             int AdaPerubahanRecord= Stm.executeUpdate(Sql);
             TampilCustomer();
             if (AdaPerubahanRecord>0){
                JOptionPane.showMessageDialog(this,"Data Berhasil Di Hapus","Informasi",JOptionPane.INFORMATION_MESSAGE);
                jButton1.setEnabled(true);
             }else
                JOptionPane.showMessageDialog(this,"Data Gagal Di Hapus","Informasi",JOptionPane.INFORMATION_MESSAGE);
                Stm.close();
             KosongkanObjek();
         } catch (SQLException e){
                System.out.println("Koneksi Gagal " +e.toString());
            }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        KosongkanObjek();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        int i = jTable1.getSelectedRow();
        TableModel Model = jTable1.getModel();
        jTextField1.setText(Model.getValueAt(i,0).toString());
        jTextField2.setText(Model.getValueAt(i,1).toString());
        jTextField3.setText(Model.getValueAt(i,2).toString());
        jComboBox2.setSelectedItem(Model.getValueAt(i,3).toString());
        jComboBox1.setSelectedItem(Model.getValueAt(i,4).toString());
        jTextField5.setText(Model.getValueAt(i,5).toString());
        jTextField6.setText(Model.getValueAt(i,6).toString());
        jTextField8.setText(Model.getValueAt(i,7).toString());
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd").parse((String)Model.getValueAt(i,8).toString());
        } catch (ParseException ex) {
            Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, null, ex);
        }
        jDateChooser1.setDate(date);
    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
         cari();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        TampilCustomer();
    }//GEN-LAST:event_jButton6ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    // End of variables declaration//GEN-END:variables
}

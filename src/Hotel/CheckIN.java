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
public class CheckIN extends javax.swing.JInternalFrame {
    koneksi Konek = new koneksi();
    private Connection Con;
    Statement Stm;
    ResultSet Rs;
    String Sql;
    DefaultTableModel Dtm;

    /**
     * Creates new form CheckIN
     */
    public CheckIN() {
        initComponents();
        KosongkanObjek();
        LoadDataCustomer();
        LoadDataKamar();
        TampilCheckIN();
    }
    
    void KosongkanObjek(){
        jTextField1.setText("");
        jTextField2.setText("");
        jTextField3.setText("");
        jTextField4.setText("");
        jTextField5.setText("");
        jComboBox1.setSelectedIndex(0);
        jComboBox2.setSelectedIndex(0);      
        jDateChooser1.setCalendar(null);
        jTextField1.requestFocus();
    }
    
    private void LoadDataCustomer(){
        String kd="";
        try{
            Con=Konek.getKoneksiDatabase();
            Stm = Con.createStatement();
            Sql = "select * from customer";
            Rs=Stm.executeQuery(Sql);
            while(Rs.next()) {
                jComboBox1.addItem(Rs.getString("nik"));
            }
        } catch(SQLException e){
            System.out.println("Koneksi Gagal"+e.toString());
        }
    }
    private void LoadDataKamar(){
        String kd="";
        try{
            Con=Konek.getKoneksiDatabase();
            Stm = Con.createStatement();
            Sql = "select * from kamar";
            Rs=Stm.executeQuery(Sql);
            while(Rs.next()) {
                jComboBox2.addItem(Rs.getString("kode"));
            }
        } catch(SQLException e){
            System.out.println("Koneksi Gagal"+e.toString());
        }
    }
    
    void updateStatus(){
         String Status ="Terpakai";
         try{
            Con=Konek.getKoneksiDatabase();
            Stm = null;
            Sql = "update kamar set status='"+Status+"' where kode = '"+jComboBox2.getSelectedItem().toString()+"' ";
            Stm = Con.createStatement();
            int AdaPenambahanRecord= Stm.executeUpdate(Sql);        
        } catch(SQLException e){
            System.out.println("koneksi gagal"+e.toString());
        }     
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
            String[]kolom={"KODE USER","NAMA CUSTOMER","ALAMAT CUSTOMER","NO HP",
            "KODE KAMAR","KAMAR","TYPE","HARGA","CHECK IN","STATUS"};
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
    void TampilCheckIN(){
        try {
            Con=Konek.getKoneksiDatabase();
            Stm = Con.createStatement();
            TampilModelJTabel();
            try{
                Sql = "SELECT transaksi.checkin , customer.nama As NamaCustomer, "+
                "transaksi.kode_user, "+
                "transaksi.kode_kamar, "+
                "customer.alamat, "+
                "customer.no_hp, "+
                "kamar.nama As NamaKamar, "+
                "kamar.type, "+
                "kamar.harga, "+
                "kamar.status "+               
                " FROM "+
                " transaksi "+
                " LEFT OUTER JOIN customer ON(transaksi.kode_user=customer.nik)"+
                " LEFT OUTER JOIN kamar ON(transaksi.kode_kamar=kamar.kode)"+
                " where checkout IS NULL";
                Rs = Stm.executeQuery(Sql);
                while(Rs.next()){
                    Dtm.addRow(new Object[]{
                        Rs.getString("kode_user"),
                    Rs.getString("NamaCustomer"),
                    Rs.getString("alamat"),
                    Rs.getString("no_hp"),
                    Rs.getString("kode_kamar"),
                    Rs.getString("NamaKamar"),
                    Rs.getString("type"),
                    Rs.getString("harga"),
                    Rs.getString("checkin"),
                    Rs.getString("status"),

                    
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
                Sql = "SELECT transaksi.checkin , customer.nama As NamaCustomer, "+
                "transaksi.kode_user, "+
                "transaksi.kode_kamar, "+
                "customer.alamat, "+
                "customer.no_hp, "+
                "kamar.nama As NamaKamar, "+
                "kamar.type, "+
                "kamar.harga, "+
                "kamar.status "+               
                " FROM "+
                " transaksi "+
                " LEFT OUTER JOIN customer ON(transaksi.kode_user=customer.nik)"+
                " LEFT OUTER JOIN kamar ON(transaksi.kode_kamar=kamar.kode)"+
                " where "+jComboBox3.getSelectedItem()+" LIKE'%"+jTextField5.getText()+"%' ";
                Rs = Stm.executeQuery(Sql);
                while(Rs.next()){
                    Dtm.addRow(new Object[]{
                        Rs.getString("kode_user"),
                    Rs.getString("NamaCustomer"),
                    Rs.getString("alamat"),
                    Rs.getString("no_hp"),
                    Rs.getString("kode_kamar"),
                    Rs.getString("NamaKamar"),
                    Rs.getString("type"),
                    Rs.getString("harga"),
                    Rs.getString("checkin"),
                    Rs.getString("status"),

                    
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

        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox<>();
        jComboBox2 = new javax.swing.JComboBox<>();
        jTextField2 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jComboBox3 = new javax.swing.JComboBox<>();
        jTextField5 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();

        jLabel2.setText("Kode Kamar : ");

        jLabel3.setText("Kode Customer : ");

        jTextField1.setText("jTextField1");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "[pilih]" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "[pilih]" }));
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });

        jTextField2.setText("jTextField2");

        jLabel4.setText("Type : ");

        jTextField3.setText("jTextField3");

        jLabel5.setText("Harga : ");

        jTextField4.setText("jTextField4");

        jLabel6.setText("Check In : ");

        jButton2.setText("SIMPAN");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("EDIT");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("HAPUS");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("BATAL");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

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

        jLabel1.setText("Cari");

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "[pilih]" }));

        jTextField5.setText("jTextField5");

        jButton1.setText("CARI");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton6.setText("REFF");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton2)
                        .addGap(18, 18, 18)
                        .addComponent(jButton3)
                        .addGap(18, 18, 18)
                        .addComponent(jButton4)
                        .addGap(18, 18, 18)
                        .addComponent(jButton5)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextField2, javax.swing.GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE)
                                    .addComponent(jTextField1)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(5, 5, 5)
                                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jTextField3)
                                            .addComponent(jTextField4)
                                            .addComponent(jDateChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
                        .addGap(409, 409, 409))))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 761, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 404, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton6)))
                .addGap(0, 61, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel6)
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton3)
                    .addComponent(jButton4)
                    .addComponent(jButton5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton1)
                        .addComponent(jButton6)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(71, 71, 71))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
        String Kode="";
        try{
            Con=Konek.getKoneksiDatabase();
            Stm = Con.createStatement();
            Sql = "select * from customer where nik='"+jComboBox1.getSelectedItem().toString()+"'";
            Rs=Stm.executeQuery(Sql);
            while(Rs.next()) {
                Kode= Rs.getString("nama");
            }
            jTextField1.setText(Kode);
        } catch(SQLException e){
            System.out.println("koneksi gagal"+e.toString());
        }
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
        // TODO add your handling code here:
        String Nama="";
        String Type= "";
        int Harga = 0;
        String Status="";       
        try{
            Con=Konek.getKoneksiDatabase();
            Stm = Con.createStatement();
            Sql = "select * from kamar where kode='"+jComboBox2.getSelectedItem().toString()+"' ";
            Rs=Stm.executeQuery(Sql);
            while(Rs.next()) {
                Nama= Rs.getString("nama");
                Type= Rs.getString("type");
                Status= Rs.getString("status");
                Harga= Rs.getInt("harga");
            }
            if("Terpakai".equals(Status)){
                   jButton2.setEnabled(false);
                   JOptionPane.showMessageDialog(null, "Kamar Telah Diguanakan");
                   
            }
            else if("Tersedia".equals(Status)) {
                   jButton2.setEnabled(true);
                   JOptionPane.showMessageDialog(null, "Kamar Tersedia");
            }
            String s=String.valueOf(Harga);  
            jTextField2.setText(Nama);
            jTextField3.setText(Type);
            jTextField4.setText(s);

        } catch(SQLException e){
            System.out.println("koneksi gagal"+e.toString());
        }
        
    }//GEN-LAST:event_jComboBox2ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        String Tampilan="yyyy-MM-dd";
        SimpleDateFormat Fm = new SimpleDateFormat(Tampilan);
        String CheckIN = String.valueOf(Fm.format(jDateChooser1.getDate()));
        try {
            Con = Konek.getKoneksiDatabase();
            Stm = null;
            Sql = "insert into transaksi (id, kode_kamar, kode_user, checkin)VALUES (NULL,'"+jComboBox2.getSelectedItem()+"','"+jComboBox1.getSelectedItem()+"','"+CheckIN+"')";
            Stm= Con.createStatement();
            int AdaPenambahanRecord= Stm.executeUpdate(Sql);
            
            if (AdaPenambahanRecord>0){
                updateStatus();
                JOptionPane.showMessageDialog(this,"Data Berhasil Tersimpan","Informasi",JOptionPane.INFORMATION_MESSAGE);
            }else
                JOptionPane.showMessageDialog(this,"Data Gagal Tersimpan","Informasi",JOptionPane.INFORMATION_MESSAGE);
            Stm.close();
            KosongkanObjek();
            TampilCheckIN();
            } catch (SQLException e){
                System.out.println("Koneksi Gagal " +e.toString());
            }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
       String Tampilan="yyyy-MM-dd";
        SimpleDateFormat Fm = new SimpleDateFormat(Tampilan);
        String CheckIN = String.valueOf(Fm.format(jDateChooser1.getDate()));
        try {
            Con = Konek.getKoneksiDatabase();
            Stm = null;
            Sql = "update transaksi set checkin= '"+CheckIN+"' where kode_user = '"+jComboBox1.getSelectedItem()+"' "
                    + "AND kode_kamar = '"+jComboBox2.getSelectedItem()+"' ";
            Stm= Con.createStatement();
            int AdaPerubahanRecord= Stm.executeUpdate(Sql);
            TampilCheckIN();
            if (AdaPerubahanRecord>0){
                JOptionPane.showMessageDialog(this,"Data Berhasil Di Simpan","Informasi",JOptionPane.INFORMATION_MESSAGE);
                jButton1.setEnabled(true);
            }else
                JOptionPane.showMessageDialog(this,"Data Gagal Di Simpan","Informasi",JOptionPane.INFORMATION_MESSAGE);
                Stm.close();
                KosongkanObjek();
         } catch (SQLException e){
            System.out.println("Koneksi Gagal " +e.toString());
         } 
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
         try {
             Con = Konek.getKoneksiDatabase();
             Stm = null;
             Sql = "delete from transaksi where kode_kamar='"+jComboBox2.getSelectedItem()+"' AND kode_user='"+jComboBox1.getSelectedItem()+"' ";
             Stm= Con.createStatement();
             int AdaPerubahanRecord= Stm.executeUpdate(Sql);
             TampilCheckIN();
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
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
         KosongkanObjek();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        cari();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        TampilCheckIN();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        int i = jTable1.getSelectedRow();
        TableModel Model = jTable1.getModel();
        jComboBox1.setSelectedItem(Model.getValueAt(i,0).toString());
        jComboBox2.setSelectedItem(Model.getValueAt(i,4).toString());
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd").parse((String)Model.getValueAt(i,8).toString());
        } catch (ParseException ex) {
            Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, null, ex);
        }
        jDateChooser1.setDate(date);
    }//GEN-LAST:event_jTable1MouseClicked


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
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    // End of variables declaration//GEN-END:variables
}

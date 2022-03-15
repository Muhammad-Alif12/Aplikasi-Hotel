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
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Asus
 */
public class CheckOUT extends javax.swing.JInternalFrame {
    koneksi Konek = new koneksi();
    private Connection Con;
    Statement Stm;
    ResultSet Rs,Rs2;
    String Sql;
    DefaultTableModel Dtm;
    Date TanggalMasuk = null;
    String tglpengembalian;
    String tglpeminjaman;
    String strbulanpinjam;
    String strbulankembali;
    int intbulanpinjam;
    int intbulankembali;
    int Harga2;
    JasperReport JR;
    JasperPrint JP;

    /**
     * Creates new form CheckOUT
     */
    public CheckOUT() {
        initComponents();
        KosongkanObjek();
        LoadDataCustomer();
        LoadDataKamar();
        TampilCheckOUT();
    }
    void KosongkanObjek(){
        jTextField1.setText("");
        jTextField2.setText("");
        jTextField3.setText("");
        jTextField4.setText("");
        jTextField5.setText("");
        jTextField6.setText("");
        jTextField7.setText("");
        jDateChooser1.setCalendar(null);
        jDateChooser2.setCalendar(null);
        jComboBox1.setSelectedIndex(0);
        jComboBox2.setSelectedIndex(0);
        jComboBox3.setSelectedIndex(0);
        jDateChooser1.setEnabled(false);        
    }
    
    void hitung(){       
        try{        
           int denda;
           int tahunP = Integer.parseInt(tglpeminjaman.substring(7, 10));
           int hariP = Integer.parseInt(tglpeminjaman.substring(0, 2));
           strbulanpinjam = (String) tglpeminjaman.substring(3, 6);
           if(strbulanpinjam.compareTo("Jan") == 0){
               intbulanpinjam = 1;
           }
           else if(strbulanpinjam.compareTo("Feb") == 0){
               intbulanpinjam = 2;
           }
           else if(strbulanpinjam.compareTo("Mar") == 0){
               intbulanpinjam = 3;
           }
           else if(strbulanpinjam.compareTo("Apr") == 0){
               intbulanpinjam = 4;
           }
           else if(strbulanpinjam.compareTo("May") == 0){
               intbulanpinjam = 5;
           }
           else if(strbulanpinjam.compareTo("Jun") == 0){
               intbulanpinjam = 6;
           }
           else if(strbulanpinjam.compareTo("Jul") == 0){
               intbulanpinjam = 7;
           }
           else if(strbulanpinjam.compareTo("Aug") == 0){
               intbulanpinjam = 8;
           }
           else if(strbulanpinjam.compareTo("Sep") == 0){
               intbulanpinjam = 9;
           }
           else if(strbulanpinjam.compareTo("Oct") == 0){
               intbulanpinjam = 10;
           }
           else if(strbulanpinjam.compareTo("Nov") == 0){
               intbulanpinjam = 11;
           }
           else {
               intbulanpinjam = 12;
           }
           int tahunK = Integer.parseInt(tglpengembalian.substring(7, 10));
           int hariK = Integer.parseInt(tglpengembalian.substring(0, 2));
           strbulankembali = (String) tglpengembalian.substring(3, 6);
            if(strbulankembali.compareTo("Jan") == 0){
               intbulankembali = 1;
           }
           else if(strbulankembali.compareTo("Feb") == 0){
               intbulankembali = 2;
           }
           else if(strbulankembali.compareTo("Mar") == 0){
               intbulankembali = 3;
           }
           else if(strbulankembali.compareTo("Apr") == 0){
               intbulankembali = 4;
           }
           else if(strbulankembali.compareTo("May") == 0){
               intbulankembali = 5;
           }
           else if(strbulankembali.compareTo("Jun") == 0){
               intbulankembali = 6;
           }
           else if(strbulankembali.compareTo("Jul") == 0){
               intbulankembali = 7;
           }
           else if(strbulankembali.compareTo("Aug") == 0){
               intbulankembali = 8;
           }
           else if(strbulankembali.compareTo("Sep") == 0){
               intbulankembali = 9;
           }
           else if(strbulankembali.compareTo("Oct") == 0){
               intbulankembali = 10;
           }
           else if(strbulankembali.compareTo("Nov") == 0){
               intbulankembali = 11;
           }
           else {
               intbulankembali = 12;
           }
           int hasilHari = hariK - hariP;
           int hasilBulan = (intbulankembali - intbulanpinjam) * 30;
           int hasilTahun = (tahunK - tahunP) * 365;
           int totalWaktu = (hasilHari + hasilBulan + hasilTahun);
           int lamaDenda, totDenda;
           if(totalWaktu > 0){
               lamaDenda = totalWaktu - 0;
               totDenda =  lamaDenda * Harga2;
               jTextField5.setText(String.valueOf(lamaDenda));
               jTextField6.setText(String.valueOf(totDenda));
               
           }
           else {
               jTextField5.setText("0");
               jTextField6.setText("0");              
           }
         
       }catch(Exception e){
            System.out.println(e.getMessage());
       }
    }
    
    private void LoadDataCustomer(){
        String kd="";
        try{
            Con=Konek.getKoneksiDatabase();
            Stm = Con.createStatement();
            Sql = "select distinct(kode_user) from transaksi";
            Rs=Stm.executeQuery(Sql);
            while(Rs.next()) {
                jComboBox1.addItem(Rs.getString("kode_user"));
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
            Sql = "select * from transaksi";
            Rs=Stm.executeQuery(Sql);
            while(Rs.next()) {
                jComboBox2.addItem(Rs.getString("kode_kamar"));
            }
        } catch(SQLException e){
            System.out.println("Koneksi Gagal"+e.toString());
        }
    }
    
    void updateStatus(){
         String Status ="Tersedia";
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
            "KODE KAMAR","KAMAR","TYPE","HARGA","CHECK IN","CHECK OUT","TOTAL HARI","JUMLAH BAYAR","STATUS"};
            Dtm = new DefaultTableModel(null, kolom){
            @Override
                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return false;
                }
            };
            jTable1.setModel(Dtm);
            AturJTable(jTable1, new int[]{100,100,100,100,100,100,100,100,100,100,100,100,100} );
         } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "salah"+e);
           }
    }
    void TampilCheckOUT(){
        try {
            Con=Konek.getKoneksiDatabase();
            Stm = Con.createStatement();
            TampilModelJTabel();
            try{
                Sql = "SELECT transaksi.checkin , customer.nama As NamaCustomer, "+
                "transaksi.kode_user, "+
                "transaksi.kode_kamar, "+
                "transaksi.checkout, "+
                "transaksi.hari, "+
                "transaksi.total, "+
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
                " where checkout IS NOT NULL";
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
                    Rs.getString("checkout"),
                    Rs.getString("hari"),
                    Rs.getString("total"),
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
                "transaksi.checkout, "+
                "transaksi.hari, "+
                "transaksi.total, "+
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
                 " where "+jComboBox3.getSelectedItem()+" LIKE'%"+jTextField7.getText()+"%' ";
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
                    Rs.getString("checkout"),
                    Rs.getString("hari"),
                    Rs.getString("total"),
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

        jLabel1 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jTextField1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jTextField2 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        jLabel7 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jTextField6 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jTextField7 = new javax.swing.JTextField();
        jComboBox3 = new javax.swing.JComboBox<>();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();

        jLabel1.setText("Kode Customer : ");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "[pilih]" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jTextField1.setText("jTextField1");

        jLabel2.setText("Kode Kamar : ");

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "[pilih]" }));
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });

        jTextField2.setText("jTextField2");

        jLabel3.setText("Harga : ");

        jTextField3.setText("jTextField3");

        jLabel4.setText("Type :");

        jTextField4.setText("jTextField4");

        jLabel5.setText("Check IN :");

        jLabel6.setText("Check OUT");

        jDateChooser2.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jDateChooser2PropertyChange(evt);
            }
        });

        jLabel7.setText("Total Hari : ");

        jTextField5.setText("jTextField5");

        jTextField6.setText("jTextField6");

        jLabel8.setText("Total Bayar :");

        jButton1.setText("SIMPAN");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
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

        jButton2.setText("EDIT");
        jButton2.setToolTipText("");
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

        jLabel9.setText("Cari : ");

        jTextField7.setText("jTextField7");

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "[pilih]" }));

        jButton5.setText("Cari");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setText("Reff");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setText("Cetak Bukti");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(39, 39, 39)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel1)
                                            .addComponent(jLabel2)
                                            .addComponent(jLabel3)
                                            .addComponent(jLabel7)
                                            .addComponent(jButton1))
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jButton2)
                                                .addGap(31, 31, 31)
                                                .addComponent(jButton3)
                                                .addGap(18, 18, 18)
                                                .addComponent(jButton4)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jButton7))
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addGroup(layout.createSequentialGroup()
                                                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGroup(layout.createSequentialGroup()
                                                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                    .addComponent(jTextField2)))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                    .addComponent(jTextField5, javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jTextField3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jDateChooser1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addComponent(jLabel8)
                                                        .addGap(18, 18, 18)
                                                        .addComponent(jTextField6))
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addComponent(jLabel4)
                                                        .addGap(18, 18, 18)
                                                        .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addComponent(jLabel6)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                        .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE))))))))
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 383, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton6)))
                        .addGap(0, 108, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel5))
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3)
                    .addComponent(jButton4)
                    .addComponent(jButton7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 53, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton5)
                    .addComponent(jButton6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38))
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
        Date CheckIN = null;
        Date CheckOUT = null;
        try{
            Con=Konek.getKoneksiDatabase();
            Stm = Con.createStatement();
            Sql = "SELECT kamar.nama ,kamar.type, "+
                " kamar.status,"+
                " kamar.harga,"+
                " transaksi.checkin,"+
                " transaksi.checkout"+
                " FROM "+
                " transaksi "+
                " LEFT OUTER JOIN kamar ON(transaksi.kode_kamar=kamar.kode)" +
                " where transaksi.kode_kamar='"+jComboBox2.getSelectedItem().toString()+"' "+
                " AND transaksi.kode_user= '"+jComboBox1.getSelectedItem().toString()+"' ";
            Rs=Stm.executeQuery(Sql);
            while(Rs.next()) {
                Nama= Rs.getString("nama");
                Type= Rs.getString("type");
                Status= Rs.getString("status");
                Harga= Rs.getInt("harga");
                Harga2= Rs.getInt("harga");
                CheckIN= Rs.getDate("checkin");
                CheckOUT= Rs.getDate("checkout");
                
                if(CheckOUT != null){
                    jButton1.setEnabled(false);
                    JOptionPane.showMessageDialog(this,"Telah Check OUT", "Informasi" ,JOptionPane.INFORMATION_MESSAGE);                                  
                }
                else{
                    jButton1.setEnabled(true);
                }
            }
            String s=String.valueOf(Harga);  
            jTextField2.setText(Nama);
            jTextField4.setText(Type);
            jDateChooser1.setDate(CheckIN);
            jTextField3.setText(s);

        } catch(SQLException e){
            System.out.println("koneksi gagal"+e.toString());
        }
        
    }//GEN-LAST:event_jComboBox2ActionPerformed

    private void jDateChooser2PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jDateChooser2PropertyChange
        // TODO add your handling code here:
        if(jDateChooser2.getDate() != null){
        String Tampilan="dd MM yyyy";
        SimpleDateFormat Fm = new SimpleDateFormat(Tampilan);
        tglpeminjaman = String.valueOf(Fm.format(jDateChooser1.getDate()));
        tglpengembalian = String.valueOf(Fm.format(jDateChooser2.getDate()));       
        hitung();
        }
    }//GEN-LAST:event_jDateChooser2PropertyChange

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        String Tampilan="yyyy-MM-dd";
        SimpleDateFormat Fm = new SimpleDateFormat(Tampilan);
        String CheckOUT = String.valueOf(Fm.format(jDateChooser2.getDate()));
        try {
            Con = Konek.getKoneksiDatabase();
            Stm = null;
            Sql = "update transaksi set checkout= '"+CheckOUT+"', hari='"+jTextField5.getText()+"',total='"+jTextField6.getText()+"' where kode_user = '"+jComboBox1.getSelectedItem()+"' "
                    + "AND kode_kamar = '"+jComboBox2.getSelectedItem()+"' ";
            Stm= Con.createStatement();
            int AdaPerubahanRecord= Stm.executeUpdate(Sql);
            
            if (AdaPerubahanRecord>0){
                updateStatus();
                JOptionPane.showMessageDialog(this,"Data Berhasil Di Simpan","Informasi",JOptionPane.INFORMATION_MESSAGE);
                jButton1.setEnabled(true);
            }else
                JOptionPane.showMessageDialog(this,"Data Gagal Di Simpan","Informasi",JOptionPane.INFORMATION_MESSAGE);
                Stm.close();
            TampilCheckOUT();
            KosongkanObjek();
         } catch (SQLException e){
            System.out.println("Koneksi Gagal " +e.toString());
         } 
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
         String Tampilan="yyyy-MM-dd";
        SimpleDateFormat Fm = new SimpleDateFormat(Tampilan);
        String CheckOUT = String.valueOf(Fm.format(jDateChooser2.getDate()));
        try {
            Con = Konek.getKoneksiDatabase();
            Stm = null;
            Sql = "update transaksi set checkout= '"+CheckOUT+"', hari='"+jTextField5.getText()+"',total='"+jTextField6.getText()+"' where kode_user = '"+jComboBox1.getSelectedItem()+"' "
                    + "AND kode_kamar = '"+jComboBox2.getSelectedItem()+"' ";
            Stm= Con.createStatement();
            int AdaPerubahanRecord= Stm.executeUpdate(Sql);
            
            if (AdaPerubahanRecord>0){
                updateStatus();
                JOptionPane.showMessageDialog(this,"Data Berhasil Di Simpan","Informasi",JOptionPane.INFORMATION_MESSAGE);
                jButton1.setEnabled(true);
            }else
                JOptionPane.showMessageDialog(this,"Data Gagal Di Simpan","Informasi",JOptionPane.INFORMATION_MESSAGE);
                Stm.close();
            TampilCheckOUT();
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
             Sql = "delete from transaksi where kode_kamar='"+jComboBox2.getSelectedItem()+"' AND kode_user='"+jComboBox1.getSelectedItem()+"' ";
             Stm= Con.createStatement();
             int AdaPerubahanRecord= Stm.executeUpdate(Sql);
             TampilCheckOUT();
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

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        cari();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        TampilCheckOUT();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        int i = jTable1.getSelectedRow();
        TableModel Model = jTable1.getModel();
        jComboBox1.setSelectedItem(Model.getValueAt(i,0).toString());
        jComboBox2.setSelectedItem(Model.getValueAt(i,4).toString());
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd").parse((String)Model.getValueAt(i,9).toString());
        } catch (ParseException ex) {
            Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, null, ex);
        }
        jDateChooser2.setDate(date);
    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        try {
            Con=Konek.getKoneksiDatabase();
            try {
                String path ="C:\\Users\\Asus\\My Hotel\\src\\Laporan\\BuktiTransaksi.jasper" ;
                Map parameter = new HashMap();
//                JR = JasperCompileManager.compileReport(path);
//                JP = JasperFillManager.fillReport(JR,null,Con);
//                JasperViewer.viewReport(JP);

                parameter.put("name_customer", jComboBox1.getSelectedItem());
                JR = (JasperReport)JRLoader.loadObject(path);
                JP = JasperFillManager.fillReport(path,parameter,Con);                    
                JasperViewer.viewReport(JP,false);
            }catch (Exception ex){
                JOptionPane.showMessageDialog(rootPane,"Dokumen tidak ada"+ex);
            }          
            Con.close();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(rootPane, e);
            }
    }//GEN-LAST:event_jButton7ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private javax.swing.JLabel jLabel1;
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
    // End of variables declaration//GEN-END:variables
}

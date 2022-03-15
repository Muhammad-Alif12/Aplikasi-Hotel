/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hotel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Asus
 */
public class koneksi {
    private Connection koneksiDatabase;
    
    public Connection getKoneksiDatabase(){
        if(koneksiDatabase == null){
            try {
                Class.forName("com.mysql.jdbc.Driver");
                System.out.println("Driver Ditemukan");
                try {
                    koneksiDatabase = DriverManager.getConnection("jdbc:mysql://localhost:3306/myhotel","root","");
                    System.out.println("Koneksi database ditemukan");
                }catch(SQLException ex){
                    System.out.print("Koneksi tidak ditemuka : \n dengan pesan error" + ex.toString());
                }
            } catch(ClassNotFoundException ex){
                System.out.print("Class driver database tidak ditemuka : \n dengan pesan error" + ex.toString());
            }
            
        }
        return koneksiDatabase;
    
    }
}

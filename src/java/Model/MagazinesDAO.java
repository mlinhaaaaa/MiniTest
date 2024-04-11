/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import dbcontext.ConnectDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author This PC
 */
public class MagazinesDAO {
    
    public MagazinesDAO() {
    }

    public ArrayList<Magazine> getAll(String Id){
        try{
            //Connect database
            ConnectDB db = ConnectDB.getInstance();
            Connection con = db.openConnection();
            String sql = "Select * from Magazine_93156";
            if(Id!="") sql=sql+" where ID='"+Id+"'";
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            ArrayList<Magazine> list = new ArrayList<>();
            while(rs.next()){
                String id = rs.getString("ID");
                String title = rs.getString("Title");
                String publisher = rs.getString("Publisher");
                double price = rs.getDouble("Price");
                Magazine m = new Magazine(id, title, publisher, price);
                list.add(m);
            }
            System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@"+list);
            return list;
        } catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public boolean newMagazine(Magazine m) throws Exception{
        String sql = "Insert into Magazine_93156 values(?,?,?,?)";
        try{
            ConnectDB db = ConnectDB.getInstance();
            Connection con = db.openConnection();
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, m.getID());
            pstmt.setString(2, m.getTitle());
            pstmt.setString(3, m.getPublisher());
            pstmt.setDouble(4, m.getPrice());
            return pstmt.executeUpdate()>0;
        } catch(Exception ex){
            System.out.println(ex);
        }
        return false;
    }
}

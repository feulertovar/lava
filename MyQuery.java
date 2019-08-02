import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

//---------------------------------------------------------------------------
//
//LAVAFLO Client Management App  
//
//
//Author: Feuler Tovar
//Date: 12/01/18
//Issues: None known
//
//Description:
//This application will hold client information for a photography and other media businesses
//Data will be persisted using MySQL database schemas
//
//
//
//Assumptions:
//MySQL AND Java Swing dependencies are properly installed
//
//
public class MyQuery {

	public MyQuery() {
		// TODO Auto-generated constructor stub
	}
	
////////////////////////////////////
/// Insert contact			     ///
/// Input : Contact 			 ///
/// Output: None 				 ///
/// Returns nothing 			 ///
/// 							 ///
///////////////////////////////////
	public void insertContact(Contact client)
    {
        Connection conn = MyDatabase.getJdbc();
        PreparedStatement pstm;
        
        try {
        	pstm = conn.prepareStatement("INSERT INTO `contactlist`(`firstname`, `lastname`,`phone`, `email`, `address`,`fburl`,`igurl`,`twurl`,`userid`) VALUES (?,?,?,?,?,?,?,?,?)");
        	pstm.setString(1, client.getFname());
        	pstm.setString(2, client.getLname());
        	pstm.setString(3, client.getPhone());
        	pstm.setString(4, client.getEmail());
        	pstm.setString(5, client.getAddress());
        	pstm.setString(6, client.getFburl());
        	pstm.setString(7, client.getIgurl());
        	pstm.setString(8, client.getTwurl());
        	pstm.setInt(9, client.getUid());
        	
            if(pstm.executeUpdate() != 0){
                JOptionPane.showMessageDialog(null, "New contact added...");
                
                }
                else{
                    JOptionPane.showMessageDialog(null, "Contacts: Something went wrong");
                    
                }
            
        } catch (SQLException ex) {
            Logger.getLogger(MyQuery.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
	
////////////////////////////////////
/// Insert invoice		     ///
/// Input : Invoice 			 ///
/// Output: None 				 ///
/// Returns nothing 			 ///
/// 							 ///
///////////////////////////////////	
	public void insertInvoice(Invoice client)
    {
        Connection conn = MyDatabase.getJdbc();
        PreparedStatement pstm;
        
        try {
        	pstm = conn.prepareStatement("INSERT INTO `invoices`(`firstname`, `lastname`,`pkg`, `price`,`number`,`userid`) VALUES (?,?,?,?,?,?)");
        	pstm.setString(1, client.getFname());
        	pstm.setString(2, client.getLname());
        	pstm.setString(3, client.getPkg());
           	pstm.setString(4, client.getPrice());
        	pstm.setString(5, client.getNumber());
           	pstm.setInt(6, client.getUid());
        	
            if(pstm.executeUpdate() != 0){
                JOptionPane.showMessageDialog(null, "New invoice added...");
                
                }
                else{
                    JOptionPane.showMessageDialog(null, "Invoices: Something went wrong");
                    
                }
            
        } catch (SQLException ex) {
            Logger.getLogger(MyQuery.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
	
	
////////////////////////////////////
/// update a contact  	     	///
/// Input : Contact 			 ///
/// Output: None 				 ///
/// Returns nothing 			 ///
/// 							 ///
///////////////////////////////////	
    public void updateContact(Contact client)
    {
    	 Connection conn = MyDatabase.getJdbc();
         PreparedStatement pstm;
         String updateQuery = "";
  
         updateQuery = "UPDATE `contactlist` SET `firstname`= ?,`lastname`= ?,`phone`= ?,`email`= ?,`address`= ?,`fburl`= ?,`igurl`= ?,`twurl`= ? WHERE `id` == ?";
         
         try {
         pstm = conn.prepareStatement(updateQuery);
     	pstm.setString(1, client.getFname());
     	pstm.setString(2, client.getLname());
     	pstm.setString(3, client.getPhone());
     	pstm.setString(4, client.getEmail());
     	pstm.setString(5, client.getAddress());
     	pstm.setString(6, client.getFburl());
     	pstm.setString(7, client.getIgurl());
     	pstm.setString(8, client.getTwurl());
     	pstm.setInt(9, client.getCid());
         
         if(pstm.executeUpdate() != 0){
             JOptionPane.showMessageDialog(null, "Contact data edited...");
             
             }
             else{
                 JOptionPane.showMessageDialog(null, "Contact Update: Something went wrong");
                 
             }
         
     } catch (SQLException ex) {
         Logger.getLogger(MyQuery.class.getName()).log(Level.SEVERE, null, ex);
     }

     }

////////////////////////////////////
/// update an invoice 			 ///
/// Input : Contact 			 ///
/// Output: None 				 ///
/// Returns nothing 			 ///
/// 							 ///
///////////////////////////////////
    public void updateInvoice(Invoice client)
    {
    	 Connection conn = MyDatabase.getJdbc();
         PreparedStatement pstm;
         String updateQuery = "";
  
         updateQuery = "UPDATE `invoices` SET `firstname`= ?,`lastname`= ?,`pkg`= ?,`price`= ?,`number`= ? WHERE `id` == ?";
         
         try {
         pstm = conn.prepareStatement(updateQuery);
     	pstm.setString(1, client.getFname());
     	pstm.setString(2, client.getLname());
     	pstm.setString(3, client.getPkg());
        pstm.setString(4, client.getPrice());
    	pstm.setString(5, client.getNumber());
       	pstm.setInt(6, client.getUid());
         
         if(pstm.executeUpdate() != 0){
             JOptionPane.showMessageDialog(null, "Invoice data edited...");
             
             }
             else{
                 JOptionPane.showMessageDialog(null, "Invoice Update: Something went wrong");
                 
             }
         
     } catch (SQLException ex) {
         Logger.getLogger(MyQuery.class.getName()).log(Level.SEVERE, null, ex);
     }

     }

 

////////////////////////////////////
/// delete a contact 			 ///
/// Input : id 			 		 ///
/// Output: None 				 ///
/// Returns nothing 			 ///
/// 							 ///
///////////////////////////////////     
public void deleteContact(int cid)
 {
	 Connection conn = MyDatabase.getJdbc();
     PreparedStatement pstm;
     
     try {
         pstm = conn.prepareStatement("DELETE FROM `contactlist` WHERE `id` = ?");
         pstm.setInt(1, cid);
         
         if(pstm.executeUpdate() != 0){
             JOptionPane.showMessageDialog(null, "Contact deleted...");
             
             }
             else{
                 JOptionPane.showMessageDialog(null, "Contact Delete: Something went wrong");;
                 
             }
         
     } catch (SQLException ex) {
         Logger.getLogger(MyQuery.class.getName()).log(Level.SEVERE, null, ex);
     }

 }

////////////////////////////////////
/// delete an invoice 			 ///
/// Input : invoice 			 ///
/// Output: None 				 ///
/// Returns nothing 			 ///
/// 							 ///
///////////////////////////////////  
public void deleteInvoice(int numid)
{
	 Connection conn = MyDatabase.getJdbc();
    PreparedStatement pstm;
    
    try {
        pstm = conn.prepareStatement("DELETE FROM `invoices` WHERE `number` = ?");
        pstm.setInt(1, numid);
        
        if(pstm.executeUpdate() != 0){
            JOptionPane.showMessageDialog(null, "Invoice deleted...");
            
            }
            else{
                JOptionPane.showMessageDialog(null, "Invoice Delete: Something went wrong");;
                
            }
        
    } catch (SQLException ex) {
        Logger.getLogger(MyQuery.class.getName()).log(Level.SEVERE, null, ex);
    }

}    
	
	
////////////////////////////////////
/// create a list of contacts    ///
/// Input : UserId 			 	 ///
/// Output: None 				 ///
/// Returns Contact list		 ///
///////////////////////////////////  
    public ArrayList<Contact> createList(int UserId){
        
        ArrayList<Contact> clist = new ArrayList<Contact>();
        
        Connection conn = MyDatabase.getJdbc();
        Statement stm;
        ResultSet rset;
        
        try {
            stm = conn.createStatement();
            rset = stm.executeQuery("SELECT `id`, `firstname`, `lastname`, `phone`, `email`, `address`, `fburl`, `igurl`, `twurl` FROM `contactlist` WHERE userid = " + UserId);
           
            Contact client = null;
            
            while(rset.next()){
            	client = new Contact(rset.getInt("id"), 
                                 rset.getString("firstname"), 
                                 rset.getString("lastname"),
                                 rset.getString("phone"),
                                 rset.getString("email"),
                                 rset.getString("address"),
                                 rset.getString("fburl"),
                                 rset.getString("igurl"),
                                 rset.getString("twurl"),
                                 UserId);
                
                clist.add(client);
            }
        
        } catch (SQLException ex) {
            Logger.getLogger(MyQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
        return clist;
        
    }
    
	
////////////////////////////////////
/// create a list of Invoices    ///
/// Input : UserId 			 	 ///
/// Output: None 				 ///
/// Returns Invoice list		 ///
/////////////////////////////////// 
    public ArrayList<Invoice> createInvoiceList(int UserId){
        
        ArrayList<Invoice> ilist = new ArrayList<Invoice>();
        
        Connection conn = MyDatabase.getJdbc();
        Statement stm;
        ResultSet rset;
        
        try {
            stm = conn.createStatement();
            rset = stm.executeQuery("SELECT `id`, `firstname`, `lastname`, `pkg`, `price`, `number` FROM `invoices`");
            Invoice client = null;
            
            while(rset.next()){
            	client = new Invoice(rset.getInt("id"), 
                                 rset.getString("firstname"), 
                                 rset.getString("lastname"),
                                 rset.getString("pkg"),
                                 rset.getString("price"),
                                 rset.getString("number"),
                                 UserId);
                
                ilist.add(client);
            }
        
        } catch (SQLException ex) {
            Logger.getLogger(MyQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ilist;
        
    }
		

}

import javax.swing.*;
import java.net.URL;
import java.io.IOException;
import java.awt.Dimension;
import javax.swing.text.*;
import java.awt.event.*;
import java.awt.Color;
import java.lang.System;
import java.util.Calendar;
import java.util.Date;
import java.io.*;

import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

class ItemBilling {

    String Page="";
    public String CustName;
    public Date DateOfPurchase;
    public String ContactNumber;
    public String[] FileItem = new String[15];
    int totalCost = 0;

    ItemBilling() {
    /* Declaration of Swing components */ 
    JFrame f = new JFrame("Invoice Generating Tool");
    JTextField tf1 = new JTextField();
    JTextField tf2 = new JTextField();
    JTextField tf3 = new JTextField();
    JTextField tf4 = new JTextField();
    JLabel lb1 = new JLabel("Customer Name");
    JLabel lb2 = new JLabel("Contact Number");
    JLabel lb3 = new JLabel("Item Name");
    JLabel lb4 = new JLabel("Quantity");
    JButton b1 = new JButton("Add item");
    JButton b2 = new JButton("Done");
    JButton b3 = new JButton("Exit");
    JButton b4 = new JButton("Print");
    JEditorPane editorPane = new JEditorPane();
    
    /* JEditorPane Section */
    editorPane.setContentType("text/html");
    editorPane.setEditable(false);
    String Header = "<html><br>";
    Page = Page+Header;
    editorPane.setText(Page);
    Page = Page+"<b><center>ITEM LIST</center></b><br>";
    editorPane.setText(Page);
    Page = Page+"<body><br><table style="+"width:100%"+"><br>";
    editorPane.setText(Page);
    Page = Page+"<tr><th align=\"left\">Item Name</th><th align=\"left\">Quantity</th><th align=\"left\">Cost</th></tr><br>";
    editorPane.setText(Page);
  
    
    /* Date and Time section */
    Date date=java.util.Calendar.getInstance().getTime();  
    

    /* setBounds swing components to frame */
    lb1.setBounds(50,50,200,30);
    tf1.setBounds(200,50,200,30);
    lb2.setBounds(50,100,200,30);
    tf2.setBounds(200,100,200,30);
    lb3.setBounds(50,150,200,30);
    lb4.setBounds(50,200,200,30);
    tf4.setBounds(200,200,200,30);
    b1.setBounds(50, 300, 200, 30);
    b2.setBounds(250, 300, 200, 30);
    b3.setBounds(50, 350, 400, 30);
    b4.setBounds(50,550,100,80);
    editorPane.setBounds(470,50,500,600);

    /*  Adding Components to frame  */
    f.add(lb1);
    f.add(lb2);
    f.add(lb3);
    f.add(lb3);
    f.add(lb4);
    f.add(tf1);
    f.add(tf2);
    f.add(tf3);
    f.add(tf4);
    f.add(b1);
    f.add(b2);
    f.add(b3);
    f.add(b4);
    f.add(editorPane);
    

    /* CSV file reading section */
    String line = "";  
    String splitBy = ",";
    int i = 0;
    try   
    {  
    /* Load CSV File items in FileItem array */  
        BufferedReader br = new BufferedReader(new FileReader("menulist.csv"));  
        while ((line = br.readLine()) != null)    
        {  
            String[] ItemDescription = line.split(splitBy);    // use comma as separator  
            FileItem[i] = ItemDescription[0];
            i++;
        }    
        br.close();
    }   
    catch (IOException e)   
    {  
        e.printStackTrace();  
    }     

     /* JComboBox Sectin */
    JComboBox cb = new JComboBox<>(FileItem);  
    cb.setEditable(true);
    cb.setBounds(200, 150,200,30);    
    f.add(cb);              
    AutoCompleteDecorator.decorate(cb);

/* Action Listener Section*/
    b2.addActionListener(new ActionListener() {
       public void actionPerformed(ActionEvent e) {
            CustName = tf1.getText();
            ContactNumber = tf2.getText();
            DateOfPurchase = date;

            Page = Page+"</table><br>";
            Page = Page+"<hr><br>";
            editorPane.setText(Page);
            Page = Page+"<center><b>Total Cost: "+totalCost+"</b></center><br><br><br>";
            editorPane.setText(Page);
            Page = Page+"<b>Customer  Name: "+CustName+"</b><br>";
            editorPane.setText(Page);
            Page = Page+"<b>Contact Number: "+ContactNumber+"</b><br>";
            editorPane.setText(Page);
            Page = Page+"<b>Date of Purchase: "+DateOfPurchase+"</b><br>";
            editorPane.setText(Page);
            Page = Page+"<br><br><br><b><center>Thank you for shopping with us</center></b><br>";
            editorPane.setText(Page);
            Page = Page+"</body></html>";
            
        }
    }
    );


    b1.addActionListener(new ActionListener() {
        String line = "";  
        String splitBy = ",";  

        public void actionPerformed(ActionEvent e) {
            String ItemName = cb.getSelectedItem().toString();
            String ItemQty = tf4.getText();
            addItemList(ItemName, ItemQty);
        }
        void addItemList(String ItemName, String ItemQty) {

            int totalItemCost = 0;
        try   
        {  
            //parsing a CSV file into BufferedReader class constructor  
            BufferedReader br = new BufferedReader(new FileReader("menulist.csv"));  
            while ((line = br.readLine()) != null)   //returns a Boolean value  
            {  
                String[] ItemDescription = line.split(splitBy);    // use comma as separator  
                if(ItemName.equals(ItemDescription[0])) {
                    totalItemCost = (Integer.parseInt(ItemQty))*(Integer.parseInt(ItemDescription[1]));
                    totalCost += totalItemCost;
                    Page = Page+"<tr><td>"+ItemName+"</td>"+"<td>"+ItemQty+"</td>"+"<td>"+totalItemCost+"</td></tr>";
                    editorPane.setText(Page);
                }
                else {
                    tf4.setText("");    
                } 
            }
            br.close();    
        }      
        catch (IOException e)   
        {  
            e.printStackTrace();  
        }     

        }
    });

    b3.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent closing) {
            System.exit(0);
        }
    });
    
    b4.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter("outputBill.html"));
                writer.write(editorPane.getText());
                writer.close();
                String[] commands1 = {"cmd", "/c", "C:/Program Files/Mozilla Firefox/firefox.exe","outputBill.html"};  // For Windows
                String[] commands2 = {"firefox", "outputBill.html"};  // For Linux 
                
                Runtime rt = Runtime.getRuntime();
                Process proc = rt.exec(commands2); 
            } catch (IOException err) {
                err.printStackTrace();
            }
            
            }
    });
  


    /* Frame layout section */
    f.setSize(1000,1000);
    f.setLayout(null);
    f.setVisible(true);
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    f.getContentPane().setBackground( Color.LIGHT_GRAY); 
   
}
    public static void main(String[] args) {
        ItemBilling ib = new ItemBilling();
    }
}


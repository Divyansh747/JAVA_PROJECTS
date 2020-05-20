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


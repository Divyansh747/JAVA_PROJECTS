import java.awt.*;  
import java.awt.event.*;  

class Calculator {

    static String s="";
    static int a,b;
    static boolean flag=false;

public static void main(String[] args) {  
    
    Frame f=new Frame("Button Example");  
    final TextField tf=new TextField();  
    tf.setBounds(50,50, 300,40);  

    Button b1=new Button("1");  
    Button b2=new Button("2");  
    Button b3=new Button("3");  
    Button b4=new Button("4");  
    Button b5=new Button("5");  
    Button b6=new Button("6");  
    Button b7=new Button("7");  
    Button b8=new Button("8");  
    Button b9=new Button("9");  
    Button b0=new Button("0");  
    Button badd=new Button("+");  
    Button bdiv=new Button("/");  
    Button bmul=new Button("x");  
    Button bsub=new Button("-");
    Button bres=new Button("="); 
    Button bcls=new Button("cls");  

    b1.setBounds(50,100,60,30);  
    b2.setBounds(150,100,60,30);  
    b3.setBounds(250,100,60,30);  
    b4.setBounds(50,150,60,30);  
    b5.setBounds(150,150,60,30);  
    b6.setBounds(250,150,60,30);  
    b7.setBounds(50,200,60,30);  
    b8.setBounds(150,200,60,30);  
    b9.setBounds(250,200,60,30);  
    b0.setBounds(50,250,60,30);  
    badd.setBounds(150,250,60,30);  
    bsub.setBounds(250,250,60,30);  
    bdiv.setBounds(50,300,60,30);  
    bmul.setBounds(150,300,60,30); 
    bres.setBounds(250,300,60,30);  
    bcls.setBounds(50,350,60,30);  

	
    b1.addActionListener(new ActionListener(){  
        public void actionPerformed(ActionEvent e){  
            tf.setText(s+"1"); 
            s=s+"1"; 
        }   });  
    b2.addActionListener(new ActionListener(){  
        public void actionPerformed(ActionEvent e){  
            tf.setText(s+"2");  
            s=s+"2";
        }   });  
    b3.addActionListener(new ActionListener(){  
        public void actionPerformed(ActionEvent e){  
            tf.setText(s+"3");
            s=s+"3";  
        }   });  
    b4.addActionListener(new ActionListener(){  
        public void actionPerformed(ActionEvent e){  
            tf.setText(s+"4");  
            s=s+"4";
        }   });  
    b5.addActionListener(new ActionListener(){  
        public void actionPerformed(ActionEvent e){  
            tf.setText(s+"5");  
            s=s+"5"; 
       }   });  
    b6.addActionListener(new ActionListener(){  
        public void actionPerformed(ActionEvent e){  
            tf.setText(s+"6");
            s=s+"6";  
        }   });  
    b7.addActionListener(new ActionListener(){  
        public void actionPerformed(ActionEvent e){  
            tf.setText(s+"7");  
            s=s+"7";
        }   });  
    b8.addActionListener(new ActionListener(){  
        public void actionPerformed(ActionEvent e){  
            tf.setText(s+"8");  
            s=s+"8";
        }   });  
    b9.addActionListener(new ActionListener(){  
        public void actionPerformed(ActionEvent e){  
            tf.setText(s+"9");  
            s=s+"9";
        }   });  
    b0.addActionListener(new ActionListener(){  
        public void actionPerformed(ActionEvent e){  
            tf.setText(s+"0"); 
            s=s+"0"; 
        }   });  
    badd.addActionListener(new ActionListener(){  
        public void actionPerformed(ActionEvent e){  
            tf.setText(s+"+");  
            s=s+"+";  
      }   });  
    bsub.addActionListener(new ActionListener(){  
        public void actionPerformed(ActionEvent e){  
            tf.setText(s+"-");  
            s=s+"-";    
    }   });  
    bdiv.addActionListener(new ActionListener(){  
        public void actionPerformed(ActionEvent e){  
            tf.setText(s+"/");  
            s=s+"/";  
      }   });  
    bmul.addActionListener(new ActionListener(){  
        public void actionPerformed(ActionEvent e){  
            tf.setText(s+"*");  
            s=s+"*";  
      }   });  
    bres.addActionListener(new ActionListener(){  
        public void actionPerformed(ActionEvent e){  
           tf.setText(s+"="); 
           //s=s+"=";
           //EvaluateString(s);
        } });
    bcls.addActionListener(new ActionListener(){  
        public void actionPerformed(ActionEvent e){  
            tf.setText(s+" "); 
            s=" ";
            flag=false; 
        }   });

    f.add(b1);
    f.add(b2);
    f.add(b3);
    f.add(b4);
    f.add(b5);
    f.add(b6);
    f.add(b7);
    f.add(b8);
    f.add(b9);
    f.add(b0);
    f.add(badd);
    f.add(bsub);
    f.add(bmul);
    f.add(bdiv);
    f.add(bres);
    f.add(bcls);
    f.add(tf);  
    
    f.setSize(400,500);  
    f.setLayout(null);  
    f.setVisible(true);
    
/*    string result;
    
    string EvaluateString(String s){
        
        return result;
    
    }
*/
}  
} 

import java.awt.*;
import javax.swing.event.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.nio.file.attribute.*;
import java.nio.file.*;
import java.io.IOException;
import static java.nio.file.AccessMode.*;


public class File{
public static void main(String args[]){
JFrame f = new JFrame("Activity #1");
//Labels
JLabel l1 = new JLabel("Please enter a file name :");


//TextFields
JTextField t1 = new JTextField();



//Buttons
JButton b1 = new JButton("OPTION 1");
JButton b2 = new JButton("OPTION 2");
JButton b3 = new JButton("OPTION 3");
JButton b4 = new JButton("CLEAR");
JButton b5 = new JButton("EXIT");

// Text Area
TextArea area = new TextArea();
JScrollPane scrollPane = new JScrollPane(area);
JTextArea area2 = new JTextArea("\n\tWHAT TO DO:" + "\n\n" + "OPTION 1:Display file attributes."
 + "\n" + "OPTION 2: Dispaly Elements of the file path." + "\n" + "OPTION 3: Delete a file");

area.setBounds(100,450,700,200);
area2.setBounds(200,180,500,180);
area.setFont (new Font ("TimesRoman", Font.BOLD | Font.ITALIC, 20));
area2.setFont (new Font ("TimesRoman", Font.BOLD | Font.ITALIC, 20));
area2.setEditable(false); 


l1.setBounds(100,100,450,40);
l1.setFont (new Font ("TimesRoman", Font.BOLD | Font.ITALIC, 30));


t1.setBounds(450,100,350,40);
t1.setFont (new Font ("TimesRoman", Font.BOLD | Font.ITALIC, 20));


b1.setBackground(Color.lightGray);
b2.setBackground(Color.lightGray);
b3.setBackground(Color.lightGray);
b4.setBackground(Color.lightGray);
b5.setBackground(Color.lightGray);
b1.setBounds(100,380,110,40);
b2.setBounds(250,380,110,40);
b3.setBounds(400,380,110,40);
b4.setBounds(550,380,110,40);
b5.setBounds(700,380,110,40);
f.add(l1);
f.add(t1);
f.add(b1);
f.add(b2);
f.add(b3);
f.add(b4);
f.add(b5);
f.add(area);
f.add(area2);

 
f.setLayout(null);
f.setVisible(true);
f.setSize(900,700);
f.setLocation(0,0);
f.setResizable(false);
f.getContentPane().setBackground(Color.pink);
    
      
       f.addWindowListener(new WindowAdapter(){
         public void windowClosing(WindowEvent e){
            System.exit(0);
            }
            });
   
     b1.addActionListener(new ActionListener(){

   @Override
   public void actionPerformed(ActionEvent e){
   
         
      String filename = t1.getText();
      Path filepath = Paths.get(filename);
      
      
      try{
         
         BasicFileAttributes attrib = Files.readAttributes(filepath, BasicFileAttributes.class);
         filepath.getFileSystem().provider().checkAccess(filepath, READ, WRITE, EXECUTE);
         
         area.setText("Permission to read, write and execute is allowed."+"\n"+"File: " + filepath.toString()+"\n"+ "Creation time: " + attrib.creationTime()+"\n"+
          "File size: "+ attrib.size()+"\n"+"Last access: "+ attrib.lastAccessTime()+"\n"
          +"Last modified: "+ attrib.lastModifiedTime());
         
      }
     catch(IOException b){
         area.setText("File cannot be used.");
      }
}
}
   
);
 b2.addActionListener(new ActionListener(){

   @Override
   public void actionPerformed(ActionEvent e){
   
      String filename = t1.getText();
      Path filepath = Paths.get(filename);
      String sum= " ";
   
            for (int i = 0; i<filepath.getNameCount();i++){
            
            sum += "Element " + i + " : " + filepath.getName(i)+"\n";
           
           }
          
           
            area.setText("Path name: " + filepath.toString()+"\n" +"File name: " + filepath.getFileName()+
      "\n" +"Number of elements: " + filepath.getNameCount()+"\n"+sum );
           
        } 
      
   });
    b3.addActionListener(new ActionListener(){

   @Override
   public void actionPerformed(ActionEvent e){
   
      String filename = t1.getText();
      Path filepath = Paths.get(filename);
      
      try{
         Files.delete(filepath);
         area.setText("File or directory is deleted. ");
         
      }catch(NoSuchFileException b){
      
         area.setText("Error : No such file or directory.");
         
      }catch(DirectoryNotEmptyException c){
         area.setText("Error : Directory is not empty.");
         
      }catch(SecurityException d){
         area.setText("Error : No permission to delete. ");
         
      }catch(IOException f){
      
         area.setText("Error : IO Exception");
      } 
      }     
      
   });
   b4.addActionListener(new ActionListener(){

   @Override
   public void actionPerformed(ActionEvent e){
   area.setText(" ");
   t1.setText(" ");
   }
   }
  );

b5.addActionListener(new ActionListener(){

@Override
public void actionPerformed(ActionEvent e){
System.exit(0);
}
});

}

}


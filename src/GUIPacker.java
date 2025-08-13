import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;

class Packer implements ActionListener 
{
    public JFrame fobj;
    public JLabel labellNo1, labelNo2;
    public JTextField tfield1, tfield2;
    public JButton packButton;

    public Packer(String Title, int Width, int Height) 
    {
        
        fobj = new JFrame();
        fobj.setTitle(Title);
        fobj.setSize(Width, Height);
        fobj.setLayout(null);

        labellNo1 = new JLabel("Folder Name :");
        labellNo1.setBounds(50, 30, 100, 30);
        fobj.add(labellNo1);

        labelNo2 = new JLabel("File Name :");
        labelNo2.setBounds(50, 70, 100, 30);
        fobj.add(labelNo2);

        tfield1 = new JTextField();
        tfield1.setBounds(150, 30, 150, 30);
        fobj.add(tfield1);

        tfield2 = new JTextField();
        tfield2.setBounds(150, 70, 150, 30);
        fobj.add(tfield2);
        
        packButton = new JButton("PACK");
        packButton.setBounds(150, 190, 150, 30);
        packButton.addActionListener(this);
        fobj.add(packButton);
        
        fobj.getContentPane().setBackground(Color.PINK);
        fobj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fobj.setVisible(true);

    }
    public void actionPerformed(ActionEvent ae) 
    {
        try
        {
            int i = 0,j = 0,iRet = 0,iCountFile = 0;

            if(ae.getSource() == packButton)
            {
                File fobj = new File(tfield1.getText());
                
                // Check the existance of Directory
                if((fobj.exists()) && (fobj.isDirectory()))
                {
                    File Packobj = new File(tfield2.getText());

                    //Create a Packed File
                    boolean bRet = Packobj.createNewFile();

                    if (bRet == false)
                    {
                        return;
                    }

                    //Retrived All files from Directory
                    File Arr[] = fobj.listFiles();           //file class objects after that into Arr[]

                    //*System.out.println("Number of Files in the "+labellNo1+" Directory are : "+Arr.length);

                    //Packed File object
                    FileOutputStream foobj = new FileOutputStream(Packobj);

                    //Buffer for Read and Write Activity
                    byte Buffer[] = new byte[1024];

                    String Header = null;

                    //Directory Traversal
                    for(i=0;i < Arr.length;i++)
                    {
                        Header = Arr[i].getName()+" "+Arr[i].length();

                        //Loop to form 100 bytes Header
                        for(j=Header.length();j<100;j++)
                        {
                            Header = Header + " ";
                        }
                        //write Header into packed file
                        
                        foobj.write(Header.getBytes());
                        
                        //Open file from directory for reading
                        FileInputStream fiobj = new FileInputStream(Arr[i]);

                        //write contents of file from packed file
                        while((iRet = fiobj.read(Buffer))!= -1)    
                        {
                            foobj.write(Buffer,0,iRet);
                        }
                        fiobj.close();
                        iCountFile++;
                    }
                }
            }
        }
        catch(Exception eobj)
        {}
    }
}


class GUIPacker
{
    public static void main(String A[]) 
    {
        new Packer("Marvellous Packer", 400, 300);
    }
}
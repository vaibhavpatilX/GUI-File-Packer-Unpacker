///////////////////////////////////////////////////////////////////////////////////////////
//
//	Class Name		: 	Packer
//	Description 	: 	It provides a GUI to pack multiple files from a directory into a single file.
//	Author			: 	Vaibhav Patil
//	Date			:	20/07/2025
//
///////////////////////////////////////////////////////////////////////////////////////////

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

    ///////////////////////////////////////////////////////////////////////////////////////////
    //
    //	Function Name	: 	Packer (Constructor)
    //	Input			: 	String (Title), int (Width), int (Height)
    //	Output			: 	None
    //	Description 	: 	Constructor to initialize GUI components for the Packer Application
    //	Author			: 	Vaibhav Patil
    //	Date			:	20/07/2025
    //
    ///////////////////////////////////////////////////////////////////////////////////////////
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

    ///////////////////////////////////////////////////////////////////////////////////////////
    //
    //	Function Name	: 	actionPerformed
    //	Input			: 	ActionEvent (ae)
    //	Output			: 	None
    //	Description 	: 	Handles the click event of the PACK button. 
    //					  It reads all files from the given folder and writes them into a single packed file.
    //	Author			: 	Vaibhav Patil
    //	Date			:	21/07/2025
    //
    ///////////////////////////////////////////////////////////////////////////////////////////
    public void actionPerformed(ActionEvent ae) 
    {
        try
        {
            int i = 0, j = 0, iRet = 0, iCountFile = 0;

            if(ae.getSource() == packButton)
            {
                File fobj = new File(tfield1.getText());
                
                // Check the existence of Directory
                if((fobj.exists()) && (fobj.isDirectory()))
                {
                    File Packobj = new File(tfield2.getText());

                    // Create a Packed File
                    boolean bRet = Packobj.createNewFile();

                    if (bRet == false)
                    {
                        return;
                    }

                    // Retrieved All files from Directory
                    File Arr[] = fobj.listFiles();  

                    // Packed File object
                    FileOutputStream foobj = new FileOutputStream(Packobj);

                    // Buffer for Read and Write Activity
                    byte Buffer[] = new byte[1024];

                    String Header = null;

                    // Directory Traversal
                    for(i=0;i < Arr.length;i++)
                    {
                        Header = Arr[i].getName()+" "+Arr[i].length();

                        // Loop to form 100 bytes Header
                        for(j=Header.length();j<100;j++)
                        {
                            Header = Header + " ";
                        }

                        // Write Header into packed file
                        foobj.write(Header.getBytes());
                        
                        // Open file from directory for reading
                        FileInputStream fiobj = new FileInputStream(Arr[i]);

                        // Write contents of file into packed file
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
        {
            // Exception Handling (Ignored)
        }
    }
}

///////////////////////////////////////////////////////////////////////////////////////////
//
//	Class Name		: 	GUIPacker
//	Description 	: 	Main class to run the Packer Application
//	Author			: 	Vaibhav Patil
//	Date			:	20/07/2025
//
///////////////////////////////////////////////////////////////////////////////////////////
class GUIPacker
{
    ///////////////////////////////////////////////////////////////////////////////////////////
    //
    //	Function Name	: 	main
    //	Input			: 	String[] (Command line arguments)
    //	Output			: 	None
    //	Description 	: 	Entry point for the program. Creates an object of Packer.
    //	Author			: 	Vaibhav Patil
    //	Date			:	21/07/2025
    //
    ///////////////////////////////////////////////////////////////////////////////////////////
    public static void main(String A[]) 
    {
        new Packer("Marvellous Packer", 400, 300);
    }
}

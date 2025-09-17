///////////////////////////////////////////////////////////////////////////////////////////
//
//	Class Name		: 	Unpacker
//	Description 	: 	It provides a GUI to unpack a packed file into its original multiple files.
//	Author			: 	Vaibhav Patil
//	Date			:	20/07/2025
//
///////////////////////////////////////////////////////////////////////////////////////////

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;

class Unpacker implements ActionListener 
{
    public JFrame fobj;
    public JLabel labellNo1;
    public JTextField tfield1, tfield2;
    public JButton UnpackButton;

    ///////////////////////////////////////////////////////////////////////////////////////////
    //
    //	Function Name	: 	Unpacker (Constructor)
    //	Input			: 	String (Title), int (Width), int (Height)
    //	Output			: 	None
    //	Description 	: 	Constructor to initialize GUI components for the Unpacker Application
    //	Author			: 	Vaibhav Patil
    //	Date			:	20/07/2025
    //
    ///////////////////////////////////////////////////////////////////////////////////////////
    public Unpacker(String Title, int Width, int Height) 
    {
        fobj = new JFrame();
        fobj.setTitle(Title);
        fobj.setSize(Width, Height);
        fobj.setLayout(null);

        labellNo1 = new JLabel("File Name :");
        labellNo1.setBounds(50, 70, 100, 30);
        fobj.add(labellNo1);

        tfield1 = new JTextField();
        tfield1.setBounds(150, 70, 150, 30);
        fobj.add(tfield1);
        
        UnpackButton = new JButton("UNPACK");
        UnpackButton.setBounds(150, 190, 150, 30);
        UnpackButton.addActionListener(this);
        fobj.add(UnpackButton);
        
        fobj.getContentPane().setBackground(Color.PINK);
        fobj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fobj.setVisible(true);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////
    //
    //	Function Name	: 	actionPerformed
    //	Input			: 	ActionEvent (ae)
    //	Output			: 	None
    //	Description 	: 	Handles the click event of the UNPACK button.
    //					  It reads a packed file and reconstructs the original files with their content.
    //	Author			: 	Vaibhav Patil
    //	Date			:	21/07/2025
    //
    ///////////////////////////////////////////////////////////////////////////////////////////
    public void actionPerformed(ActionEvent ae) 
    {
        try
        {
            String Header = null;
            File fobjnew = null;
            int FileSize = 0, iRet = 0;

            if(ae.getSource() == UnpackButton)
            {
                File fobj = new File(tfield1.getText());

                if(!fobj.exists())
                {
                    return;
                }

                FileInputStream fiobj = new FileInputStream(fobj);

                // Buffer to read the header
                byte HeaderBuffer[] = new byte[100];
                
                while((iRet = fiobj.read(HeaderBuffer,0,100)) != -1)
                {
                    // Convert byte array to String
                    Header = new String(HeaderBuffer);
                    Header = Header.trim();

                    String Tokens[] = Header.split(" ");

                    fobjnew = new File(Tokens[0]);
                    fobjnew.createNewFile();

                    FileSize = Integer.parseInt(Tokens[1]);

                    byte Buffer[] = new byte[FileSize];
                    FileOutputStream foobj = new FileOutputStream(fobjnew);

                    fiobj.read(Buffer,0,FileSize);
                    foobj.write(Buffer,0,FileSize);

                    foobj.close();
                } // End of while           
                fiobj.close();
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
//	Class Name		: 	GUIUnpacker
//	Description 	: 	Main class to run the Unpacker Application
//	Author			: 	Vaibhav Patil
//	Date			:	20/07/2025
//
///////////////////////////////////////////////////////////////////////////////////////////
class GUIUnpacker
{
    ///////////////////////////////////////////////////////////////////////////////////////////
    //
    //	Function Name	: 	main
    //	Input			: 	String[] (Command line arguments)
    //	Output			: 	None
    //	Description 	: 	Entry point for the program. Creates an object of Unpacker.
    //	Author			: 	Vaibhav Patil
    //	Date			:	21/07/2025
    //
    ///////////////////////////////////////////////////////////////////////////////////////////
    public static void main(String A[]) 
    {
        new Unpacker("MARVELLOUS UNPACKER", 400, 300);
    }
}

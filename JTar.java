import java.io.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class JTar extends JFrame implements ActionListener , KeyListener
{
	JDesktopPane jpane;
	JInternalFrame jif;
	JTextField txt_file,txt_creation;
	JButton btn_untar,btn_create,btn_view;
	JLabel lbl_file,lbl_verbose;
	StringBuilder con = new StringBuilder();

	public void showGUI()
	{
		this.setSize(1000,800);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle("JTar");
		this.setLayout(null);

		jpane = new JDesktopPane();
		jpane.setSize(1000,800);
		jpane.setLayout(null);

		jif = new JInternalFrame("JTAR",true,true,true,true);
		jif.setSize(1000,800);
		jif.setLayout(null);

		lbl_file = new JLabel("Filename");
		lbl_file.setBounds(50,50,100,35);
		jif.add(lbl_file);

		txt_file = new JTextField("File");
		txt_file.setBounds(150,50,300,35);
		jif.add(txt_file);

		txt_creation = new JTextField();	
		txt_creation.setBounds(200,200,300,200);
		jif.add(txt_creation);

		lbl_verbose = new JLabel("");
		lbl_verbose.setBounds(400,50,200,50);
		jif.add(lbl_verbose);

		btn_untar = new JButton("Extract");		
		btn_untar.setBounds(100,100,80,35);
		btn_untar.addActionListener(this);
		btn_untar.addKeyListener(this);
		btn_untar.setMnemonic(KeyEvent.VK_X);
		jif.add(btn_untar);

		btn_create = new JButton("Create");
		btn_create.setBounds(250,100,80,35);
		btn_create.addActionListener(this);
                btn_create.addKeyListener(this);
                btn_create.setMnemonic(KeyEvent.VK_C);
                jif.add(btn_create);


		jpane.add(jif);
		jif.setVisible(true);
		this.add(jpane);
		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent e)
	{
		if(e.getActionCommand().equals("Extract"))	
		{
			 try
			 {
            			Process p = Runtime.getRuntime().exec("path_to_directory/tryzip.sh "+txt_file.getText());
            			p.waitFor();
            			BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
            			String line=reader.readLine();

            			while (line != null)
				{
                			System.out.println(line);
                			line = reader.readLine();
            			}

        		}		
        		catch(IOException e1) {}
        		catch(InterruptedException e2) {}

        		System.out.println("finished.");
    		}

			if(e.getActionCommand().equals("Create"))
			{
				try
				{
					Process p = Runtime.getRuntime().exec("path_to_directory/createtar.sh "+txt_file.getText());
					p.waitFor();
                                	BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
                                	String line=reader.readLine();
					String line2 = line;	
					
                                while (line != null)
                                {
                                        System.out.println(line);
					line2 = line;
					
                                        line =  reader.readLine();
					con.append(line);
                               //System.out.println(con);
                                }
					String con1 = con.toString();
                                        line +=  reader.readLine();
					
					txt_creation.setText(con1);
				
                               //System.out.println(con);
				 
				while(line == null)
				{
					//txt_creation.setText(line);
				}
				}

				catch(IOException ei){}
				catch(InterruptedException e3){}
			}

			if(e.getActionCommand().equals("View"))
			{
				try
				{
					Process p = Runtime.getRuntime().exec("path_to_directory/listtar.sh "+txt_file.getText());
                                	p.waitFor();
                                	BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
                                	String line=reader.readLine();

                                while (line != null)
                                {
                                        System.out.println(line);
                                        line = reader.readLine();
                                }

				}

				catch(IOException ei){}
				catch(InterruptedException e3){}
			}

	}	

	public void keyTyped(KeyEvent ke)
        {
        }

        public void keyPressed(KeyEvent ke)
        {
        }

        public void keyReleased(KeyEvent ke)
        {
        }


	public static void main(String args[])
	{
		JTar jtar = new JTar();
		jtar.showGUI();
	}
}
 

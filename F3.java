import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import javax.swing.JPanel;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JFrame;
import java.awt.Color;
import java.sql.*;
public class F3 extends JFrame 
{
	F3()
	{
		setLayout(null);

		Font font1 = new Font("times new roman", Font.BOLD,12);
		Font font = new Font("times new roman", Font.BOLD,20);

		JLabel lbl01=new JLabel("STUDENT MANAGEMENT SYSTEM");
		lbl01.setHorizontalAlignment(JLabel.CENTER);
		lbl01.setFont(font);
		lbl01.setForeground(Color.white);
		lbl01.setBounds(000,000,890,50);
		lbl01.setOpaque(true);
		lbl01.setBackground(Color.BLACK);
		add(lbl01);

		JTextField txt1=new JTextField();
		txt1.setBounds(150,80,100,30);
		add(txt1);
	
		JTextField txt2=new JTextField();
		txt2.setBounds(150,110,100,30);
		add(txt2);

		JLabel label=new JLabel();
		label.setBounds(50,140,300,30);
		label.setForeground(Color.red);
		add(label);

		JLabel lbl1=new JLabel("RNO : ");
		lbl1.setBounds(50,80,100,30);
		add(lbl1);

		JLabel lbl2=new JLabel("NAME :");
		lbl2.setBounds(50,110,100,30);
		add(lbl2);

		JButton btn7=new JButton("Add");
		JButton btn8=new JButton("Delete");
		JButton btn9=new JButton("Update");
		JButton btn10=new JButton("View");
		JButton btn12=new JButton("Clear");

		TextArea area=new TextArea();

		area.setFont(font1);
		area.setForeground(Color.GRAY);
		area.setBounds(50,200,485,250);

		btn7.setBounds(540,200,100,40);
		btn8.setBounds(540,250,100,40);
		btn9.setBounds(540,300,100,40);
		btn10.setBounds(540,350,100,40);
		btn12.setBounds(540,400,100,40);

		add(area);
		add(btn7);
		add(btn8);
		add(btn9);
		add(btn10);
		add(btn12);

		btn7.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				try
				{
					DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
					Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","abc123");
			

					String query="insert into student values(?,?)";
					PreparedStatement statement=con.prepareStatement(query);
					statement.setString(1,txt1.getText());
					statement.setString(2,txt2.getText());
					statement.execute();

					JOptionPane.showMessageDialog(null,"Data inserted successfully");
					txt1.setText("");
					txt1.requestFocus();
					txt2.setText("");
				//	dispose();
				}
				catch(SQLException e)
				{
					JOptionPane.showMessageDialog(null,"Data is not inserted.");
					txt1.setText("");
					txt1.requestFocus();
					txt2.setText("");
				}
			}
		});
    	btn8.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				try
				{
					DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
					Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","abc123");
					
					String sql = "DELETE FROM student WHERE ROLL=?";
 					PreparedStatement statement = con.prepareStatement(sql);
					statement.setString(1,txt1.getText());
					
					int rowsUpdated = statement.executeUpdate();
					if (rowsUpdated > 0)
					{
						JOptionPane.showMessageDialog(null,"Data deleted successfully!");
						txt1.setText("");
						txt1.requestFocus();
						txt2.setText("");
    					//	dispose();
					}
					else
					{
						JOptionPane.showMessageDialog(null,"Data is not deleted.");
						txt1.setText("");
						txt1.requestFocus();
						txt2.setText("");
					}
				}
				catch(SQLException e)
				    {
        
         				System.out.println(e);
      				}  
			}
		});
		btn9.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				try
				{
					DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
					Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","abc123");
					
					String sql = "UPDATE student SET NAME=? WHERE ROLL=?";
 					PreparedStatement statement = con.prepareStatement(sql);
					statement.setString(1,txt2.getText());
					statement.setString(2,txt1.getText());
				//	dispose();

					int rowsUpdated = statement.executeUpdate();
					if (rowsUpdated > 0)
					{
						JOptionPane.showMessageDialog(null,"Data updated successfully!");
						txt1.setText("");
						txt1.requestFocus();
						txt2.setText("");
    					//	dispose();
					}
					else
					{
						JOptionPane.showMessageDialog(null,"Data is not updated.");
						txt1.setText("");
						txt1.requestFocus();
						txt2.setText("");
					}
				}
				catch(SQLException e){System.out.println(e);}
			}
		});

		btn10.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				try
				{
					DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
					Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","abc123");
					
      				String  sql  = "select * from student order by ROLL";
     				Statement  stmt  =  con.createStatement();
					ResultSet  rs = stmt.executeQuery(sql);
					
       				while(rs.next())
     				{
      					int ROLL  = rs.getInt(1);
      					String  NAME  = rs.getString(2);
      					area.append("RNO. " +ROLL + "   NAME: " +NAME+ "  \n");
      				}
      			}

        			catch(SQLException  se)
      				{
        
         				System.out.println(se);
      				}     			
      		}
		});

		btn12.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				area.setText("");
			}
		});

		txt1.addKeyListener(new KeyAdapter() 
		{
			public void keyPressed(KeyEvent EVT) 
			{
				String value = txt1.getText();
				int l = value.length();
				if (EVT.getKeyChar() >= '0' && EVT.getKeyChar() <= '9') 
				{
					txt1.setEditable(true);
					label.setText("");
				} 
				else 
				{
					txt1.setEditable(false);
					label.setText("Enter only numeric digits (0-9)");
				}
			}
		});
		txt2.addKeyListener(new KeyAdapter() 
		{
			public void keyPressed(KeyEvent EVT) 
			{
				String value = txt2.getText();
				int l = value.length();
				if (EVT.getKeyChar() >= 'A' && EVT.getKeyChar() <= 'Z') 
				{
					txt2.setEditable(true);
					label.setText("");
				} 
				else 
				{
					txt2.setEditable(false);
					label.setText("Enter only letters only (A - Z)");
				}
			}
		});
	}		

	public static void main(String args[])
	{
		F3 f3=new F3();
		f3.setSize(800,588);
		f3.setTitle("Mumbai Hotel");
		f3.setLocation(200,120);
		Image icon = Toolkit.getDefaultToolkit().getImage("icon1.png");    
		f3.setIconImage(icon);    
		f3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f3.setVisible(true);
	//	f3.setResizable(false);
	}
}
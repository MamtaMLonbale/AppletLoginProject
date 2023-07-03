package javaGui;
import java.applet.Applet;
import java.awt.Button;
import java.awt.Graphics;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
public class loginApplet extends Applet implements ActionListener
{
String str="login successful";
TextField name,pass;

public void init()
{
Label N=new Label("Name:",Label.CENTER);
add(N);
name=new TextField(20);
add(name);
//String username=name.getText();
//System.out.println("user name:"+username);
Label P=new Label("Password:",Label.CENTER);
add(P);
pass=new TextField(20);
add(pass);
pass.setEchoChar('$');
Button b1=new Button("Submit");
add(b1);
b1.addActionListener(this);
//Button b2=new Button("Cancel");
//add(b2);
N.setBounds(70,90,90,60);
N.setBounds(70,130,90,60);
}
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("I am checking database connection");
		try
		{

			//register the driver
		Class.forName("com.mysql.jdbc.Driver");
		System.out.println("I am trying to connect with data base");
		//getting connection
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3308/logindetails","root","root");
		System.out.println("using connection interface");
		
		String username=name.getText();
		System.out.println("getting username:"+username);
		String password=pass.getText();
		System.out.println("getting password:"+password);
		PreparedStatement ps=con.prepareStatement("Insert into login values(?,?)");
		System.out.println("prepared STatement for insert query");
		ps.setString(1, username);
		System.out.println("set username:"+username);
		ps.setString(2, password);
		System.out.println("set password:"+password);
		ps.executeUpdate();
		System.out.println("for prepared Statment:"+ps);
		JOptionPane.showMessageDialog(null, "successfully Inserted");
		System.out.println("popup msg");	
		con.close();
		
		}
		catch(ClassNotFoundException ce)
		{
		System.out.println(e);
		} catch (Throwable te) {
			// TODO Auto-generated catch block
			te.printStackTrace();
		}
	}

	public void paint(Graphics g)
	{
		g.drawString(str,50,100);
		
		repaint();
	}
		
}

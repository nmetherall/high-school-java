import javax.swing.*;
import java.awt.*;

class Student extends JPanel
{
	JTextField firstName, lastName, id;

	public Student()
	{
		JTextField firstName = new JTextField();
		firstName.setBounds(0,0,25,100);
		add(firstName);
		setVisible(true);
	}
}
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
// This class tests an image icon.
public class IconTest extends JFrame 
{
   private static String IMAGE_NAME = "Pictures\\DSCF0930.jpg";
   private static int FRAME_X = 150,      FRAME_Y = 200, 
                  FRAME_WIDTH = 268, FRAME_HEIGHT = 286;
   private Icon imageIcon = null, imageIconProxy = null;
   static public void main(String args[]) {
      IconTest app = new IconTest();
      app.show();
   }
   public IconTest() {
      super("Icon Test");
      imageIcon = new ImageIcon(IMAGE_NAME);
      setBounds(FRAME_X, FRAME_Y, FRAME_WIDTH, FRAME_HEIGHT);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   }
   public void paint(Graphics g) {
      super.paint(g);
      Insets insets = getInsets();
      imageIcon.paintIcon(this, g, insets.left, insets.top);
   }
}

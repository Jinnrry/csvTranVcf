import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;



public class Help implements MouseListener {
	JFrame Window;
	
	Help(){
		Window = new JFrame("说明");
		Window.setLayout(null);
		Window.setSize(425,100);
		Window.setLocationRelativeTo(null);
		Window.setResizable(false);
		Window.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		
		JTextArea out = new JTextArea();
		out.setText("CSV文件只允许有3列，第一列为姓名，第二列为电话号码，第三列为公司名称,具体格式请查看示例。注意！修改示例后保存时不可另存为其他格式");
		out.setLocation(10,10);
		out.setSize(400,200);
		out.setEditable(false);
		out.setLineWrap(true);
		out.setBackground(null);
		

		Window.add(out);
	}

	public void mouseClicked(MouseEvent arg0) {

		Window.setVisible(true);
		
		

	}

	public void mouseEntered(MouseEvent arg0) {}
	public void mouseExited(MouseEvent arg0) {}
	public void mousePressed(MouseEvent arg0) {}
	public void mouseReleased(MouseEvent arg0) {}

}

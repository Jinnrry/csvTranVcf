import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;



public class Help implements MouseListener {
	JFrame Window;
	
	Help(){
		Window = new JFrame("˵��");
		Window.setLayout(null);
		Window.setSize(425,100);
		Window.setLocationRelativeTo(null);
		Window.setResizable(false);
		Window.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		
		JTextArea out = new JTextArea();
		out.setText("CSV�ļ�ֻ������3�У���һ��Ϊ�������ڶ���Ϊ�绰���룬������Ϊ��˾����,�����ʽ��鿴ʾ����ע�⣡�޸�ʾ���󱣴�ʱ�������Ϊ������ʽ");
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

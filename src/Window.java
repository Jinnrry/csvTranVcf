

import javax.swing.*;

public class Window{
	String openPath="";
	String savaPath="";
	JFrame Window;
	JTextField path,savapath;
	JLabel put,out;
	JButton start;
	JButton open;
	JButton help;
	JButton download;
	
	
	
	Window(){
		Window=new JFrame("CSV��VCF�ļ���ʽת����");
		Window.setLayout(null);
		Window.setSize(450,200);
		Window.setLocationRelativeTo(null);
		Window.setResizable(false);
		Window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		put=new JLabel("��ѡ����Ҫת�����ļ���");
		put.setLocation(10,10);
		put.setSize(145,20);
		
		
		out=new JLabel("������ת������ļ���:(���Ӻ�׺)");
		out.setLocation(10,70);
		out.setSize(400,20);
		
		
		savapath=new JTextField();
		savapath.setSize(400, 20);
		savapath.setLocation(10, 100);
		
		path=new JTextField();
		path.setSize(400, 20);
		path.setLocation(10, 40);
		
		start=new JButton("ת��");
		start.setLocation(330, 125);
		start.setSize(100,40);
		
		help=new JButton("˵��");
		help.setLocation(10, 125);
		help.setSize(100,40);
		
		open=new JButton("ѡ���ļ�");
		open.setLocation(155, 10);
		open.setSize(100,23);
		
		
		download=new JButton("����ʾ��");
		download.setLocation(170, 125);
		download.setSize(100,40);
		
		
		
		Window.add(download);
		Window.add(put);
		Window.add(help);
		Window.add(open);
		Window.add(out);
		Window.add(path);
		Window.add(savapath);
		Window.add(start);
		Window.setVisible(true);
		
		
	}
}








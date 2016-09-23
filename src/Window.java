

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
		Window=new JFrame("CSV与VCF文件格式转换器");
		Window.setLayout(null);
		Window.setSize(450,200);
		Window.setLocationRelativeTo(null);
		Window.setResizable(false);
		Window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		put=new JLabel("请选择需要转换的文件：");
		put.setLocation(10,10);
		put.setSize(145,20);
		
		
		out=new JLabel("请输入转换后的文件名:(不加后缀)");
		out.setLocation(10,70);
		out.setSize(400,20);
		
		
		savapath=new JTextField();
		savapath.setSize(400, 20);
		savapath.setLocation(10, 100);
		
		path=new JTextField();
		path.setSize(400, 20);
		path.setLocation(10, 40);
		
		start=new JButton("转换");
		start.setLocation(330, 125);
		start.setSize(100,40);
		
		help=new JButton("说明");
		help.setLocation(10, 125);
		help.setSize(100,40);
		
		open=new JButton("选择文件");
		open.setLocation(155, 10);
		open.setSize(100,23);
		
		
		download=new JButton("下载示例");
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








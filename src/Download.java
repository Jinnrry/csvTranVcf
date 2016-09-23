import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JOptionPane;
import javax.swing.filechooser.FileSystemView;

public class Download implements MouseListener {

	
	private void createFile(String savaPath) throws IOException{//向文件中写入一个VCF格式的头信息
		 FileWriter fw=new FileWriter(new File(savaPath),true);
	        //写入中文字符时会出现乱码
	        BufferedWriter  bw=new BufferedWriter(fw);
	        bw.append("姓名1,13888888888,公司名称1");
	        bw.newLine();//换行
	        bw.append("姓名2,13000000000,公司名称2");
	        bw.newLine();//换行
	        bw.close();
	        fw.close();
	}
	
	
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		File desktopDir = FileSystemView.getFileSystemView().getHomeDirectory();
		String desktopPath = desktopDir.getAbsolutePath();
		desktopPath+="\\demo.csv";
//		System.out.println(desktopPath);;
		try {
			createFile(desktopPath);
			JOptionPane.showMessageDialog(null, "请查看桌面的demo.csv文件。", "提示", JOptionPane.INFORMATION_MESSAGE);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	      
	
	
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}

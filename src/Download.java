import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JOptionPane;
import javax.swing.filechooser.FileSystemView;

public class Download implements MouseListener {

	
	private void createFile(String savaPath) throws IOException{//���ļ���д��һ��VCF��ʽ��ͷ��Ϣ
		 FileWriter fw=new FileWriter(new File(savaPath),true);
	        //д�������ַ�ʱ���������
	        BufferedWriter  bw=new BufferedWriter(fw);
	        bw.append("����1,13888888888,��˾����1");
	        bw.newLine();//����
	        bw.append("����2,13000000000,��˾����2");
	        bw.newLine();//����
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
			JOptionPane.showMessageDialog(null, "��鿴�����demo.csv�ļ���", "��ʾ", JOptionPane.INFORMATION_MESSAGE);
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

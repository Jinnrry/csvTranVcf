import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.UIManager;


public class Open implements MouseListener {
	Window w;
	String openPath;
	String savaPath;
	Open(Window w){
		this.w=w;
		
		/*
		 * �����ļ�ѡ������ʽ
		 * */
		if(UIManager.getLookAndFeel().isSupportedLookAndFeel())
		{
			final String platform = UIManager.getSystemLookAndFeelClassName();
			if (!UIManager.getLookAndFeel().getName().equals(platform)) {
				try {
					UIManager.setLookAndFeel(platform);
				} catch (Exception exception) {
					exception.printStackTrace();
				}
			}
		}
		
		
	}
	public void mouseClicked(MouseEvent arg0) {
		//��ʼ���ļ�ѡ���
		JFileChooser fDialog = new JFileChooser();
		//�����ļ�ѡ���ı��� 
		fDialog.setDialogTitle("��ѡ��csv�ļ�");
		//����ѡ���
		int returnVal = fDialog.showOpenDialog(null);
		// �����ѡ�����ļ�
		if(JFileChooser.APPROVE_OPTION == returnVal){
		       //��ӡ���ļ���·����������޸�λ ��·��ֵ д�� textField ��
			openPath=fDialog.getSelectedFile().getPath();
			String name=fDialog.getSelectedFile().getName();
			String type=name.substring(name.length()-3);
			int point=openPath.lastIndexOf('\\');
			savaPath=openPath.substring(0,point+1);
			if(type.equals("csv")){
				w.openPath=openPath;
				w.path.setText(openPath);
				
				w.savaPath=savaPath;
			}
			else{
				JOptionPane.showMessageDialog(null, "�ļ����ͽ���CSV��", "��ʾ", JOptionPane.INFORMATION_MESSAGE);
			}
		}
		
	
	
	}
	public void mouseEntered(MouseEvent arg0) {}
	public void mouseExited(MouseEvent arg0) {}
	public void mousePressed(MouseEvent arg0) {}
	public void mouseReleased(MouseEvent arg0) {}

}

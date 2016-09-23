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
		 * 设置文件选择框的样式
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
		//初始化文件选择框
		JFileChooser fDialog = new JFileChooser();
		//设置文件选择框的标题 
		fDialog.setDialogTitle("请选择csv文件");
		//弹出选择框
		int returnVal = fDialog.showOpenDialog(null);
		// 如果是选择了文件
		if(JFileChooser.APPROVE_OPTION == returnVal){
		       //打印出文件的路径，你可以修改位 把路径值 写到 textField 中
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
				JOptionPane.showMessageDialog(null, "文件类型仅限CSV！", "提示", JOptionPane.INFORMATION_MESSAGE);
			}
		}
		
	
	
	}
	public void mouseEntered(MouseEvent arg0) {}
	public void mouseExited(MouseEvent arg0) {}
	public void mousePressed(MouseEvent arg0) {}
	public void mouseReleased(MouseEvent arg0) {}

}

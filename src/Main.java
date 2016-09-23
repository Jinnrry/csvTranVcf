
public class Main {

	public static void main(String[] args) {
		Window w=new Window();
		w.start.addMouseListener(new ZhuanHuan(w));
		w.open.addMouseListener(new Open(w));
		w.help.addMouseListener(new Help());
		w.download.addMouseListener(new Download());
	}

}

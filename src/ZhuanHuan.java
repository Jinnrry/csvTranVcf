import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.*;
import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.JOptionPane;

class ZhuanHuan implements MouseListener{
	Window w;

	ZhuanHuan(Window w){
		this.w=w;
	}
	private void sava(String savaPath,String data) throws IOException //向文件中保存一条信息
	{
		savaHead(savaPath);//写入VCF头格式
		savaBody(savaPath,data);//写入主要信息
		savaEnd(savaPath);//写入VCF尾格式
	}
	
    private  String qpEncodeing(String str)    //将中文名字进行quoted-printable编码
    {
        char[] encode = str.toCharArray();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < encode.length; i++)
        {
            if ((encode[i] >= '!') && (encode[i] <= '~') && (encode[i] != '=')
                    && (encode[i] != '\n'))
            {
                sb.append(encode[i]);
            }
            else if (encode[i] == '=')
            {
                sb.append("=3D");
            }
            else if (encode[i] == '\n')
            {
                sb.append("\n");
            }
            else
            {
                StringBuffer sbother = new StringBuffer();
                sbother.append(encode[i]);
                String ss = sbother.toString();
                byte[] buf = null;
                try
                {
                    buf = ss.getBytes("utf-8");
                }
                catch (UnsupportedEncodingException e)
                {
                    e.printStackTrace();
                }
                if (buf.length == 3)
                {
                    for (int j = 0; j < 3; j++)
                    {
                        String s16 = String.valueOf(Integer.toHexString(buf[j]));
                        // 抽取中文字符16进制字节的后两位,也就是=E8等号后面的两位,
                        // 三个代表一个中文字符
                        char c16_6;
                        char c16_7;
                        if (s16.charAt(6) >= 97 && s16.charAt(6) <= 122)
                        {
                            c16_6 = (char) (s16.charAt(6) - 32);
                        }
                        else
                        {
                            c16_6 = s16.charAt(6);
                        }
                        if (s16.charAt(7) >= 97 && s16.charAt(7) <= 122)
                        {
                            c16_7 = (char) (s16.charAt(7) - 32);
                        }
                        else
                        {
                            c16_7 = s16.charAt(7);
                        }
                        sb.append("=" + c16_6 + c16_7);
                    }
                }
            }
        }
        return sb.toString();
    }
	
	private void savaEnd(String savaPath) throws IOException{  //向文件中写入一个VCF格式的尾信息
		FileWriter fw=new FileWriter(new File(savaPath),true);
        //写入中文字符时会出现乱码
        BufferedWriter  bw=new BufferedWriter(fw);
//        bw.append("X-WDJ-STARRED:0");
//        bw.newLine();//换行
        bw.append("END:VCARD");
        bw.newLine();//换行
        bw.close();
        fw.close();
	}
	
	private void savaHead(String savaPath) throws IOException{//向文件中写入一个VCF格式的头信息
		 FileWriter fw=new FileWriter(new File(savaPath),true);
	        //写入中文字符时会出现乱码
	        BufferedWriter  bw=new BufferedWriter(fw);
	        bw.append("BEGIN:VCARD");
	        bw.newLine();//换行
	        bw.append("VERSION:2.1");
	        bw.newLine();//换行
	        bw.close();
	        fw.close();
	}
	
	private void savaBody(String savaPath,String data) throws IOException{
		String str; 
		String phone;
		int point;
		int point2;
		
		FileWriter fw=new FileWriter(new File(savaPath),true);
	        //写入中文字符时会出现乱码
	    BufferedWriter  bw=new BufferedWriter(fw);
	        
	    str="N;CHARSET=UTF-8;ENCODING=QUOTED-PRINTABLE:"+qpEncodeing(data.substring(0,1))+";";//写入姓与名
	    point=data.indexOf(",");
	    str+=qpEncodeing(data.substring(1,point))+";;;";
	    bw.append(str);    //写入信息
	    bw.newLine();//换行
	    
	    
	    str="FN;CHARSET=UTF-8;ENCODING=QUOTED-PRINTABLE:"+qpEncodeing(data.substring(0, point));//写入全称
	    bw.append(str);    //写入信息
	    bw.newLine();//换行
	    str="TEL;X-mobile:";
	    
	    point2=data.indexOf(",",point+1);  //写入电话号码
	    phone=data.substring(point+1,point2);
	    str+=phone.substring(0,1 )+"-"+phone.substring(1,4 )+"-"+phone.substring(4,7)+"-"+phone.substring(7,11);
	    bw.append(str);    //写入信息
	    bw.newLine();//换行
	    
	    str="ORG;CHARSET=UTF-8;ENCODING=QUOTED-PRINTABLE:";  //写入公司
	    str+=qpEncodeing(data.substring(point2+1, data.length()));
	    bw.append(str);    //写入信息
	    bw.newLine();//换行
	    
	    
	    bw.close();
	    fw.close();
	}
	
	private LinkedList<String> read(String openPath)  //读取文件，将存到一个链表中，一行存为链表中的一个节点
	{
		LinkedList<String> data=new LinkedList<String>();
		String str = "";
	    FileInputStream fis = null;
		InputStreamReader isr = null;
		BufferedReader br = null; //用于包装InputStreamReader,提高处理性能。因为BufferedReader有缓冲的，而InputStreamReader没有。
		try {
		     fis = new FileInputStream(openPath);// FileInputStream
		     // 从文件系统中的某个文件中获取字节
		     isr = new InputStreamReader(fis);// InputStreamReader 是字节流通向字符流的桥梁,
		     br = new BufferedReader(isr);// 从字符输入流中读取文件中的内容,封装了一个new InputStreamReader的对象
		     while ((str = br.readLine()) != null) 
		     {
		    	 data.add( str );
		     }
		   // 当读取的一行不为空时,把读到的str的值赋给str1
			  } catch (FileNotFoundException e) {
			   System.out.println("找不到指定文件");
			  } catch (IOException e) {
			   System.out.println("读取文件失败");
			  } finally {
			   try {
			     br.close();
			     isr.close();
			     fis.close();
			    // 关闭的时候最好按照先后顺序关闭最后开的先关闭所以先关s,再关n,最后关m
			   } catch (IOException e) {
			    e.printStackTrace();
			   }
			  }
			  return data;
	}
	
 	public void mouseClicked(MouseEvent arg0) {//鼠标点击“转换”按钮时相应的事件
		Iterator<String> data;
		String savaPath=w.savaPath+w.savapath.getText()+".vcf";
		String openPath=w.openPath;
		if(openPath.length()==0)
			JOptionPane.showMessageDialog(null, "你没选择文件呢！", "提示", JOptionPane.INFORMATION_MESSAGE);
		else if(w.savapath.getText().length()==0)
			JOptionPane.showMessageDialog(null, "你没输入文件名呢！", "提示", JOptionPane.INFORMATION_MESSAGE);
		else
		{
			
			data=read(openPath).iterator();
			while(data.hasNext())
			{
				try {
					sava(savaPath,data.next());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			JOptionPane.showMessageDialog(null, "你的文件已生成，生成路径："+savaPath, "成功", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	public void mouseEntered(MouseEvent arg0) {}
	public void mouseExited(MouseEvent arg0) {}
	public void mousePressed(MouseEvent arg0) {}
	public void mouseReleased(MouseEvent arg0) {}


}

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
	private void sava(String savaPath,String data) throws IOException //���ļ��б���һ����Ϣ
	{
		savaHead(savaPath);//д��VCFͷ��ʽ
		savaBody(savaPath,data);//д����Ҫ��Ϣ
		savaEnd(savaPath);//д��VCFβ��ʽ
	}
	
    private  String qpEncodeing(String str)    //���������ֽ���quoted-printable����
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
                        // ��ȡ�����ַ�16�����ֽڵĺ���λ,Ҳ����=E8�Ⱥź������λ,
                        // ��������һ�������ַ�
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
	
	private void savaEnd(String savaPath) throws IOException{  //���ļ���д��һ��VCF��ʽ��β��Ϣ
		FileWriter fw=new FileWriter(new File(savaPath),true);
        //д�������ַ�ʱ���������
        BufferedWriter  bw=new BufferedWriter(fw);
//        bw.append("X-WDJ-STARRED:0");
//        bw.newLine();//����
        bw.append("END:VCARD");
        bw.newLine();//����
        bw.close();
        fw.close();
	}
	
	private void savaHead(String savaPath) throws IOException{//���ļ���д��һ��VCF��ʽ��ͷ��Ϣ
		 FileWriter fw=new FileWriter(new File(savaPath),true);
	        //д�������ַ�ʱ���������
	        BufferedWriter  bw=new BufferedWriter(fw);
	        bw.append("BEGIN:VCARD");
	        bw.newLine();//����
	        bw.append("VERSION:2.1");
	        bw.newLine();//����
	        bw.close();
	        fw.close();
	}
	
	private void savaBody(String savaPath,String data) throws IOException{
		String str; 
		String phone;
		int point;
		int point2;
		
		FileWriter fw=new FileWriter(new File(savaPath),true);
	        //д�������ַ�ʱ���������
	    BufferedWriter  bw=new BufferedWriter(fw);
	        
	    str="N;CHARSET=UTF-8;ENCODING=QUOTED-PRINTABLE:"+qpEncodeing(data.substring(0,1))+";";//д��������
	    point=data.indexOf(",");
	    str+=qpEncodeing(data.substring(1,point))+";;;";
	    bw.append(str);    //д����Ϣ
	    bw.newLine();//����
	    
	    
	    str="FN;CHARSET=UTF-8;ENCODING=QUOTED-PRINTABLE:"+qpEncodeing(data.substring(0, point));//д��ȫ��
	    bw.append(str);    //д����Ϣ
	    bw.newLine();//����
	    str="TEL;X-mobile:";
	    
	    point2=data.indexOf(",",point+1);  //д��绰����
	    phone=data.substring(point+1,point2);
	    str+=phone.substring(0,1 )+"-"+phone.substring(1,4 )+"-"+phone.substring(4,7)+"-"+phone.substring(7,11);
	    bw.append(str);    //д����Ϣ
	    bw.newLine();//����
	    
	    str="ORG;CHARSET=UTF-8;ENCODING=QUOTED-PRINTABLE:";  //д�빫˾
	    str+=qpEncodeing(data.substring(point2+1, data.length()));
	    bw.append(str);    //д����Ϣ
	    bw.newLine();//����
	    
	    
	    bw.close();
	    fw.close();
	}
	
	private LinkedList<String> read(String openPath)  //��ȡ�ļ������浽һ�������У�һ�д�Ϊ�����е�һ���ڵ�
	{
		LinkedList<String> data=new LinkedList<String>();
		String str = "";
	    FileInputStream fis = null;
		InputStreamReader isr = null;
		BufferedReader br = null; //���ڰ�װInputStreamReader,��ߴ������ܡ���ΪBufferedReader�л���ģ���InputStreamReaderû�С�
		try {
		     fis = new FileInputStream(openPath);// FileInputStream
		     // ���ļ�ϵͳ�е�ĳ���ļ��л�ȡ�ֽ�
		     isr = new InputStreamReader(fis);// InputStreamReader ���ֽ���ͨ���ַ���������,
		     br = new BufferedReader(isr);// ���ַ��������ж�ȡ�ļ��е�����,��װ��һ��new InputStreamReader�Ķ���
		     while ((str = br.readLine()) != null) 
		     {
		    	 data.add( str );
		     }
		   // ����ȡ��һ�в�Ϊ��ʱ,�Ѷ�����str��ֵ����str1
			  } catch (FileNotFoundException e) {
			   System.out.println("�Ҳ���ָ���ļ�");
			  } catch (IOException e) {
			   System.out.println("��ȡ�ļ�ʧ��");
			  } finally {
			   try {
			     br.close();
			     isr.close();
			     fis.close();
			    // �رյ�ʱ����ð����Ⱥ�˳��ر���󿪵��ȹر������ȹ�s,�ٹ�n,����m
			   } catch (IOException e) {
			    e.printStackTrace();
			   }
			  }
			  return data;
	}
	
 	public void mouseClicked(MouseEvent arg0) {//�������ת������ťʱ��Ӧ���¼�
		Iterator<String> data;
		String savaPath=w.savaPath+w.savapath.getText()+".vcf";
		String openPath=w.openPath;
		if(openPath.length()==0)
			JOptionPane.showMessageDialog(null, "��ûѡ���ļ��أ�", "��ʾ", JOptionPane.INFORMATION_MESSAGE);
		else if(w.savapath.getText().length()==0)
			JOptionPane.showMessageDialog(null, "��û�����ļ����أ�", "��ʾ", JOptionPane.INFORMATION_MESSAGE);
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
			
			JOptionPane.showMessageDialog(null, "����ļ������ɣ�����·����"+savaPath, "�ɹ�", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	public void mouseEntered(MouseEvent arg0) {}
	public void mouseExited(MouseEvent arg0) {}
	public void mousePressed(MouseEvent arg0) {}
	public void mouseReleased(MouseEvent arg0) {}


}

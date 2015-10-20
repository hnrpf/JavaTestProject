package stream;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 将一个文件的内容写入另一个文件(按行来写)
 * @author weifu
 *
 */
public class FileTest {

	public static void main(String[] args)   {
		 File  file=new File("c:\\test.txt");
		 BufferedReader read=null;
		 BufferedWriter writer=null;
		 try {
		   writer=new BufferedWriter(new  FileWriter("c:\\zwm.txt"));
		 } catch (IOException e1) {
		  e1.printStackTrace();
		 }
		 try {
		   read=new BufferedReader(new  FileReader(file));
		   String tempString = null;
		   while((tempString=read.readLine())!=null){
		    writer.append(tempString);
		    writer.newLine();//换行
		    writer.flush();//需要及时清掉流的缓冲区，万一文件过大就有可能无法写入了
		   }
		   read.close();
		   writer.close();
		   System.out.println("文件写入完成...");
		 } catch (IOException e) {
		  e.printStackTrace();
		 }

		}
}

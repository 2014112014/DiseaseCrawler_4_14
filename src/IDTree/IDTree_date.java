package IDTree;

import DatabaseOperation.dateoperate;

import java.io.*;
import java.util.*;

/**
 * 创建时间：2018年4月7日 上午9:08:31
 * 项目名称：DiseaseCrawler
 * @author 彭桂平
 * @version 1.0
 * @since JDK 1.6.0_21
 * 文件名称：ID_main.java
 */
public class IDTree_date {
	public static void  Date_operate_first(String filepath,List<String> name_list) throws IOException
	{
		try {
			OutputStreamWriter pw =  new OutputStreamWriter(new FileOutputStream(filepath,true),"utf-8");
			pw.write("@relation Disease  "); pw.write("\n");pw.write("\n");
			for(String symptom:dateoperate.searchsome("dis_symptom", "symptom"))
			{
				pw.write("@attribute "+symptom+"{T, F}");pw.write("\n");
			}
			pw.write((String) ("@attribute symptom { "+name_list.toString().substring(1,name_list.toString().length()-1)+"}"));
			pw.write("\n"); pw.write("\n");
			pw.write("@data  ");
			pw.close();//关闭流
		} catch (UnsupportedEncodingException | FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	public static void Date_operate_second(String filepath,Map <Integer ,List<String>>map,int sym_num) throws IOException  {
		List<String>list = new ArrayList<String>();
		OutputStreamWriter file =  new OutputStreamWriter(new FileOutputStream(filepath,true),"utf-8");
		//确认流的输出文件和编码格式
		try {
			for (Integer key : map.keySet()) {
			list = map.get(key);
			ArrayList<String>  strArray = new ArrayList<> ();
			for(int i =0 ;i<=sym_num;i++)
			{
				strArray.add("F");
			}
			for(String symptom:list)
			{
				int TF_num = dateoperate.SearchTF(symptom);
				if(TF_num!=-1){
					strArray.set(TF_num, "T");}
			}
			strArray.set(sym_num,list.get(list.size()-1));
			String input = strArray.toString().substring(1, strArray.toString().length()-1).replace(" ", "");
			file.write("\n"); file.write(input);
			}
		} catch (UnsupportedEncodingException | FileNotFoundException e) {
			e.printStackTrace();
		}
		file.close();//关闭流
	}

	public static int Get_min_map(Map<Integer,String>map)
	{
		Object[] obj=map.keySet().toArray();
		Arrays.sort(obj);
		return (int)obj[0];
	}
	public static void Date_main(Map<Integer,String>IntMap,List<String> name_list,int sym_num) throws IOException {


		Map<Integer, List<String>> Trainmap = new HashMap<>();
		Map<Integer, List<String>> Testnmap = new HashMap<>();
		int allnumber, endnumber;
		File filepath = new File("D:\\Disease");
		if(!filepath.exists()){
			filepath.mkdir();
		}
		String Testfilepath = "D:\\Disease\\test.arff";
		String Trainfilepath = "D:\\Disease\\train.arff";
		//将文件头写入文件中
		Date_operate_first(Testfilepath,name_list);
		Date_operate_first(Trainfilepath,name_list);
		for (Integer Key : IntMap.keySet()) {
			allnumber = Key;endnumber = allnumber - 20;
			Trainmap=dateoperate.Search(IntMap.get(Key), endnumber, 0);
			Testnmap=dateoperate.Search(IntMap.get(Key), 20, endnumber);
			Date_operate_second(Trainfilepath,Trainmap,sym_num);
			Date_operate_second(Testfilepath,Testnmap,sym_num);
		}
	}
}






 
package disease;

import DatabaseOperation.dateoperate;
import IDTree.IDTree_date;
import org.apache.commons.httpclient.HttpException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 创建时间：2018年3月15日 上午9:25:29
 * 项目名称：select
 *
 * @author 彭桂平
 * @version 1.0
 * @since JDK 1.6.0_21
 * 文件名称：Diseasemain.java
 */
public class Diseasemain {
	public static void main(String[] args) throws HttpException, IOException {
		int sym_num = 0;
		Map<Integer,String> IntMap = new HashMap<>();
		List<String> name_list = new ArrayList<String>(){{
			add("气胸");
			add("肺炎");
			add("支气管炎");
			add("肺结核");
			add("哮喘");
		}};
		for (String disease_name : name_list) {
		//	sym_num = DiseaseFirstTest.searchdiease(disease_name,sym_num);
			IntMap.put(dateoperate.Searchnumber(disease_name),disease_name);
		}
		sym_num = 301;
		IDTree_date.Date_main(IntMap,name_list,sym_num);
	}

}

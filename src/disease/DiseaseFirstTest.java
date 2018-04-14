package disease;

import DatabaseOperation.dateoperate;
import org.apache.commons.httpclient.HttpException;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DiseaseFirstTest {
	public static int getMatherSubstrs(String destStr, String pat, String disease_name,int sym_num)
			throws HttpException, IOException {
		Pattern p = Pattern.compile(pat), p1 = Pattern.compile("class=\"next_page\" href=\"(.+?)\">");
		Matcher m = p.matcher(destStr), m1 = p1.matcher(destStr);
		boolean b = false;
		String[] nword,symall;
		while (m.find()) {
			List<String> list = new ArrayList<String>();
			b = true;
			symall = m.group(1).split("，");
			for(String disease_symptom:symall)
			{
				list.add(disease_symptom.replace(" ", ""));
				nword = disease_symptom.replace(" ", "").split("\\(");
				dateoperate.Insertname_symptom("nword", nword[0],0);
				sym_num = dateoperate.Insertname_symptom("symptom", disease_symptom.replace(" ", ""),sym_num);

			}
			if (list.size() != 0){
				dateoperate.Insert(disease_name, list);
			}
		}
		if (m1.find() && b) {
			destStr = Disease_Test.getURLcontent("http://www.yx129.com" + m1.group(1));
			sym_num = getMatherSubstrs(destStr, pat, disease_name,sym_num);
		}
		return sym_num;
	}

	public static  int searchdiease(String disease_name,int sym_num) throws HttpException, IOException {
		try {
			String message = URLEncoder.encode(disease_name, "utf-8");
			String path = "http://www.yx129.com/s.php?keyword=" + message;
			String destStr = Disease_Test.getURLcontent(path);
			String pat = "[\u4E00-\u9FA5]*"+disease_name+"[\u4E00-\u9FA5]*</a></strong>.+?symptoms_name highlight\">(.+?)</span>";
			sym_num = getMatherSubstrs(destStr, pat, disease_name,sym_num);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return sym_num;
	}

}

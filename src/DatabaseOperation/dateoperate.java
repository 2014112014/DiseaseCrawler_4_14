package DatabaseOperation;

import com.mongodb.*;

import java.util.*;

public class dateoperate {
	// 链接mongo数据库，数据库名：Disease，
	static Mongo conn = new Mongo("localhost", 27017);
	static DB db = conn.getDB("disease");

	public static int Insertname_symptom(String name, String name_sym, int sym_num) {
		DBCollection coll = db.getCollection("dis_" + name);
		BasicDBObject doc = new BasicDBObject();
		BasicDBObject query = new BasicDBObject();
		query.put(name, name_sym);
		DBCursor cur = coll.find(query);
		if (cur.count() == 0) {
			doc.put(name, name_sym);
			if (name == "symptom") {
				sym_num++;
				doc.put("sum", sym_num);
			}
			coll.insert(doc);
		}
		return sym_num;
	}

	// 向病症表中插入数据
	public static void Insert(String disease_name, List<String> list) {
		DBCollection coll = db.getCollection("disSym");
		BasicDBObject doc = new BasicDBObject();
		doc.put("disease_name", disease_name);
		doc.put("symtomp", list);
		coll.insert(doc);
	}

	public static List<String> searchsome(String tablename, String columnname) {
		DBCollection coll = db.getCollection(tablename);
		DBCursor cur01 = coll.find();
		List<String> list = new ArrayList<String>();
		while (cur01.hasNext()) {
			list.add(cur01.next().get(columnname).toString());
		}
		return list;
	}

	public static int SearchTF(String TF_columnname) {
		DBCollection coll = db.getCollection("dis_symptom");
		BasicDBObject query = new BasicDBObject();
		query.put("symptom", TF_columnname);
		DBCursor cur = coll.find(query);
		if (cur.size() != 0) {
			return Integer.parseInt(cur.next().get("sum").toString());
		} else return -1;
	}

	public static int Searchnumber(String columnname) {
		DBCollection coll = db.getCollection("disSym");
		BasicDBObject query = new BasicDBObject();
		query.put("disease_name", columnname);
		DBCursor cur = coll.find(query);
		if (cur.count() != 0) {
			return cur.count();
		}else return 0;
	}

	public static Map <Integer ,List<String>> Search(String columnname,int limitnum,int skipnum)
	{
		DBCollection coll = db.getCollection("disSym");
		Map <Integer ,List<String>>map =new HashMap<>();

		BasicDBObject query = new BasicDBObject();
		query.put("disease_name", columnname);
        DBCursor cur01 = coll.find(query).limit(limitnum).skip(skipnum);
        int i=0;
        List <String>list =new ArrayList<>();
        while (cur01.hasNext()) {
       	    DBObject dbObj= cur01.next();
             list = (List<String>) dbObj.get("symtomp");
             list.add((String)dbObj.get("disease_name"));
             map.put(i, list);
             i++;
        }
		return map;
	}

}

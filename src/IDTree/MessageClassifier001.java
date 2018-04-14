package IDTree;


import DatabaseOperation.dateoperate;
import weka.classifiers.Classifier;
import weka.core.Attribute;
import weka.core.FastVector;
import weka.core.Instance;
import weka.core.Instances;

import java.util.ArrayList;
import java.util.List;


import static weka.core.SerializationHelper.read;

public class MessageClassifier001 {
	public static void main(String[] args) throws Exception{
		 FastVector fvNominalVal = new FastVector(2);
		 fvNominalVal.addElement("F");
		 fvNominalVal.addElement("T");
		List<String>list = dateoperate.searchsome("dis_symptom","symptom");
		FastVector fvWekaAttributes = new FastVector(list.size());
		for (String Attribute_name:list){
		 Attribute Attribute = new Attribute(Attribute_name, fvNominalVal);
			fvWekaAttributes.addElement(Attribute);
		}
		fvNominalVal = new FastVector(5);
		fvNominalVal.addElement("气胸");
		fvNominalVal.addElement("肺炎");fvNominalVal.addElement("支气管炎");
		fvNominalVal.addElement("肺结核");fvNominalVal.addElement("哮喘");
		Attribute Attribute = new Attribute("symptom", fvNominalVal);
		fvWekaAttributes.addElement(Attribute);
		List<String>list1 = new ArrayList<>();
		List<Integer>list_TF = new ArrayList<>();
		list1.add("咯粉红色泡沫痰(轻)");
		list1.add("发烧(轻)");
		list1.add("咳嗽(轻)");
		Instance iExample = new Instance(list.size()+1);
		Instances isTrainingSet = new Instances("Disease", fvWekaAttributes, 301);
		for(String symptom:list1) {
			list_TF.add(dateoperate.SearchTF(symptom));
		}
		 for(int i = 1;i<=list.size();i++){
		 iExample.setValue((Attribute) fvWekaAttributes.elementAt(i-1), "F");
		 }
		for (int ah:list_TF)
		{
			iExample.setValue((Attribute) fvWekaAttributes.elementAt(ah-1), "T");
		}
		iExample.setValue((Attribute) fvWekaAttributes.elementAt(list.size()), "肺炎");
		isTrainingSet.add(iExample);
		isTrainingSet.setClassIndex(isTrainingSet.numAttributes()-1);
		Classifier classifier8 = (Classifier) read("D:\\Disease\\LibSVM.model");//模型储存位置
		System.out.println(isTrainingSet.classAttribute().value((int) classifier8.classifyInstance(isTrainingSet.instance(0))));

	}
}
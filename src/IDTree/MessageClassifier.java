package IDTree;

import DatabaseOperation.dateoperate;
import weka.core.Attribute;
import weka.core.FastVector;
import weka.core.Instance;
import weka.core.Instances;

import java.util.List;

public class MessageClassifier {
	public static void main(String[] args) throws Exception{
		 FastVector fvNominalVal = new FastVector(2);
		 fvNominalVal.addElement("T");
		 fvNominalVal.addElement("F");
		List<String>list = dateoperate.searchsome("symptom","symptom");
		FastVector fvWekaAttributes = new FastVector(list.size());
		for (String Attribute_name:list){
		 Attribute Attribute = new Attribute(Attribute_name, fvNominalVal);
			fvWekaAttributes.addElement(Attribute);
		}
		System.out.println(list);
		 Instances isTrainingSet = new Instances("Disease", fvWekaAttributes, 10);
		 // Set class index
		 isTrainingSet.setClassIndex(3);


		 Instance iExample = new Instance(4);

		 iExample.setValue((Attribute)fvWekaAttributes.elementAt(0), 1.0);
		iExample.setValue((Attribute)fvWekaAttributes.elementAt(1), 0.5);
		iExample.setValue((Attribute)fvWekaAttributes.elementAt(1), 50);
		iExample.setValue((Attribute)fvWekaAttributes.elementAt(1), 500);
		iExample.setValue((Attribute)fvWekaAttributes.elementAt(2), "gray");
		 iExample.setValue((Attribute)fvWekaAttributes.elementAt(3), "positive");
		System.out.println("ooooooooo");
		 // add the instance
		 isTrainingSet.add(iExample);
		 System.out.println(isTrainingSet);
	}
}
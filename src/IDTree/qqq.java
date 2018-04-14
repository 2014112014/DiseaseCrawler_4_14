package IDTree;
/**  
* 创建时间：2018年4月3日 上午8:27:26  
* 项目名称：testtt  
* @author 彭桂平 
* @version 1.0   
* @since JDK 1.6.0_21  
* 文件名称：qqq.java  
*/

import weka.classifiers.Classifier;
import weka.classifiers.trees.J48;
import weka.core.Instances;
import weka.core.converters.ArffLoader;

import java.io.File;

import static weka.core.SerializationHelper.write;

public class qqq {
    public static void main(String[] args) throws Exception {  
    	 File inputfile = new File("D:\\Disease\\train.arff");//训练预料文件
         ArffLoader loader = new ArffLoader();
         loader.setFile(inputfile);
         Instances insTrain = loader.getDataSet();
         insTrain.setClassIndex(insTrain.numAttributes()-1);
         
         inputfile = new File("D:\\Disease\\test.arff");//测试预料文件
         loader.setFile(inputfile);
         Instances insTest = loader.getDataSet();
         insTest.setClassIndex(insTest.numAttributes()-1);
         double sum = insTest.numInstances();
         int right = 0;
         Classifier clas = new J48();
         clas.buildClassifier(insTrain);
        write("D:\\Disease\\LibSVM.model", clas);//参数一为模型保存文件，classifier4为要保存的模型

   //    System.out.println("clas == \n"+clas.toString());
        right=0;
        System.out.println("sum===="+sum);
         for(int i = 0; i <sum; i++) {
             if(clas.classifyInstance(insTest.instance(i)) == insTest.instance(i).classValue()) {
                System.out.print("insTest.instance("+i+") ==="+insTest.instance(i)+"的预料结果是");
                System.out.print(insTest.classAttribute().value((int) clas.classifyInstance(insTest.instance(i)))+"\n");
                 //输出预料结果
                 right++;
             }
         }
      //  System.out.println(insTest.instance(0));
         System.out.println("分类准确率："+(right/sum));
     }
}  
 
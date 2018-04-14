package IDTree;

import weka.classifiers.Classifier;
import weka.classifiers.trees.RandomForest;
import weka.core.Instances;
import weka.core.converters.ArffLoader;

import java.io.File;

import static weka.core.SerializationHelper.read;
import static weka.core.SerializationHelper.write;

public class demo {

    public static void main(String[] args) throws Exception {
        
        Classifier m_classifier = new RandomForest();
   /*     m_classifier = (Classifier) Class.forName(
                "weka.classifiers.bayes.NaiveBayes").newInstance();
                朴素贝叶斯分类器
                */
        File inputFile = new File("D:\\Disease\\train1.arff");//训练语料文件
        ArffLoader atf = new ArffLoader();   
        atf.setFile(inputFile);
        System.out.println(atf.toString());
        Instances instancesTrain = atf.getDataSet(); // 读入训练文件
        inputFile = new File("D:\\Disease\\test1.arff");//测试语料文件
        atf.setFile(inputFile);
        Instances instancesTest = atf.getDataSet(); // 读入测试文件
        instancesTest.setClassIndex(301); //设置分类属性所在行号（第一行为0号），instancesTest.numAttributes()可以取得属性总数
        double sum = instancesTest.numInstances(),//测试语料实例数
        right = 0.0f;
        instancesTrain.setClassIndex(301);
        m_classifier.buildClassifier(instancesTrain); //训练
        System.out.println("m_classifier=="+m_classifier);
        System.out.println();
        // 保存模型
        write("D:\\export\\LibSVM.model", m_classifier);//参数一为模型保存文件，classifier4为要保存的模型

        for(int  i = 0;i<sum;i++)//测试分类结果  1
        {
            if(m_classifier.classifyInstance(instancesTest.instance(i))==instancesTest.instance(i).classValue())//如果预测值和答案值相等（测试语料中的分类列提供的须为正确答案，结果才有意义）
            {
                 right++;//正确值加1
            }
        }

        // 获取上面保存的模型
        Classifier classifier8 = (Classifier) read("D:\\export\\LibSVM.model");
        System.out.println("classifier8=="+classifier8);
        double right2 = 0.0f;
        for(int  i = 0;i<sum;i++)//测试分类结果  2 (通过)
        {
            if(m_classifier.classifyInstance(instancesTest.instance(i))==instancesTest.instance(i).classValue()){//如果预测值和答案值相等（测试语料中的分类列提供的须为正确答案，结果才有意义）
                System.out.print("insTest.instance("+i+") ==="+instancesTest.classAttribute().value((int) instancesTest.instance(i).classValue())+"的预料结果是");
            System.out.print(instancesTest.classAttribute().value((int) classifier8.classifyInstance(instancesTest.instance(i)))+"\n");
                right2++;
            }
           // System.out.println(classifier8.classifyInstance(instancesTest.instance(i)));
        }
        System.out.println("随机数森林准确率:"+(right2/sum));
    }
}
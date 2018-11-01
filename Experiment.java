import java.util.Arrays;
import java.util.Random;

public class Experiment {
    public static void main(String []args){
        int corpusSize = 1000;
        int numberOfQueries = 1000000;
        int vectorSize = 15;
        Experiment experiment = new Experiment(corpusSize, numberOfQueries, vectorSize);
        int[] result = experiment.runExperiment();
        if (experiment.testChiSquare(1105.916, result)){
            System.out.println("The uniform distribution hypothesis was rejected");
        }
        else{
            System.out.println("The uniform distribution hypothesis was accepted");
        }
    }
    int corpusSize;
    int numberOfQueries;
    int vectorSize;
    DocumentsCorpus corpus;
    Random rand = new Random();
    public Experiment(int corpusSize, int numberOfQueries, int vectorSize){
        this.corpusSize = corpusSize;
        this.vectorSize = vectorSize;
        this.numberOfQueries = numberOfQueries;
        corpus = new DocumentsCorpus(corpusSize, vectorSize);
    }

    public int[] runExperiment(){
        int[] results = new int[corpusSize];
        for (int i = 0; i<numberOfQueries; i++){
//            if (i%100 ==0){
//                System.out.println("i=" +i);
//            }
            QueryVector queryVector = new QueryVector(vectorSize);
            results[corpus.getIndexOfSelectedDocument(queryVector)]++;
        }
        return results;
    }

    public int[] runUniformExperiment(){
        int[] results = new int[corpusSize];
        for (int i = 0; i<numberOfQueries; i++){
            results[rand.nextInt(corpusSize)]++;
        }
        return results;

    }
    public boolean testNumberOfCollisions(double criticalValue, int[] result){
        double expectedNumOfSingletons = numberOfQueries*Math.pow((double)(corpusSize-1)/corpusSize, numberOfQueries-1);
        int actualNumberOfSingletons = 0;
        for (int i = 0; i< corpusSize; i++){
            if (result[i]==1){
                actualNumberOfSingletons++;
            }
            if (result[i] >1) {
                System.out.println(i+" has " + result[i] +" wins");
            }
        }
        System.out.println("the expected num of singletons is " + expectedNumOfSingletons
        + ", the actual number is " + actualNumberOfSingletons);
        double difference = (expectedNumOfSingletons - actualNumberOfSingletons);
        System.out.println("The difference is: " + difference);
        return difference > criticalValue;
    }
    public boolean testChiSquare(double criticalValue, int[] results){
        double commulativeError = 0;
        double expectedVal = (double)numberOfQueries/corpusSize;
        for (int i=0;i<corpusSize; i++){
            commulativeError += Math.pow(results[i]-expectedVal, 2)/expectedVal;
        }
        System.out.println("The critical value is: "
                + criticalValue+ ", and the MSE is: " + commulativeError);
        return commulativeError>criticalValue;
    }
}

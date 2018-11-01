import java.util.Arrays;
import java.util.Random;

public class Experiment {
    public static void main(String []args) {
        int[] corpusSizes = {10, 100, 1000, 10000};
        double[] criticalValues = {21.666, 134.6416, 1105.916, 36028.797};
        int numberOfQueries = 10000000;
        for (int i = 0; i< corpusSizes.length; i++) {
            int corpusSize = corpusSizes[i];
            double criticalValue = criticalValues[i];
            boolean uniformRejected = true;
            for (int vectorSize = 2; uniformRejected ; vectorSize+=5) {
                Experiment experiment = new Experiment(corpusSize, numberOfQueries, vectorSize, new FullScoreCalculator());
                int[] result = experiment.runExperiment();
                if (experiment.testChiSquare(criticalValue, result)) {
                    System.out.println("The uniform distribution hypothesis was rejected");
                } else {
                    System.out.println("The uniform distribution hypothesis was accepted");
                }
            }
        }
    }
    int corpusSize;
    int numberOfQueries;
    int vectorSize;
    DocumentsCorpus corpus;
    Random rand = new Random();
    public Experiment(int corpusSize, int numberOfQueries, int vectorSize, ScoreCalculator scoreCalculator){
        this.corpusSize = corpusSize;
        this.vectorSize = vectorSize;
        this.numberOfQueries = numberOfQueries;
        corpus = new DocumentsCorpus(corpusSize, vectorSize, scoreCalculator);
    }

    public int[] runExperiment(){
        int[] results = new int[corpusSize];
        for (int i = 0; i<numberOfQueries; i++){
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

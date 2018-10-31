import java.util.Arrays;
import java.util.Random;

public class Experiment {
    public static void main(String []args){
        int corpusSize = 100000;
        int numberOfQueries = 1000;
        int vectorSize = 20;
        double expectedNumOfSingletons = numberOfQueries*Math.pow((double)(corpusSize-1)/corpusSize, numberOfQueries-1);
        Experiment experiment = new Experiment(corpusSize, numberOfQueries, vectorSize);
        int[] result = experiment.runUniformExperiment();
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
        System.out.println("The difference is: " + (expectedNumOfSingletons - actualNumberOfSingletons));
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
}

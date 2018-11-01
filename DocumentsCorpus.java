public class DocumentsCorpus {
    int size;
    int vectorSize;
    DocumentVector[] documents;
    ScoreCalculator calculator;
    public DocumentsCorpus(int size, int vectorSize, ScoreCalculator calculator){
        this.size = size;
        this.calculator = calculator;
        documents = new DocumentVector[size];
        for (int i = 0; i<size; i++) {
            documents[i] = new DocumentVector(vectorSize);
        }
    }
    public int getIndexOfSelectedDocument (QueryVector query){
        int result =0;
        double maxScore = 0;
        for (int i = 0; i< size; i++){
            double curScore = calculator.getScore(documents[i],query);
            if (curScore > maxScore){
                maxScore = curScore;
                result = i;
            }
        }
        return result;
    }
}

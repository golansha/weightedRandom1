public class DocumentsCorpus {
    int size;
    int vectorSize;
    DocumentVector[] documents;
    public DocumentsCorpus(int size, int vectorSize){
        this.size = size;
        documents = new DocumentVector[size];
        for (int i = 0; i<size; i++) {
            documents[i] = new DocumentVector(vectorSize);
        }
    }
    public int getIndexOfSelectedDocument (QueryVector query){
        int result =0;
        double maxScore = 0;
        for (int i = 0; i< size; i++){
            double curScore = documents[i].getScore(query);
            if (curScore > maxScore){
                maxScore = curScore;
                result = i;
            }
        }
        return result;
    }
}

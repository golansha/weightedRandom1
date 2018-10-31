import java.util.Random;

public class IndexVector {
    double [] entries;
    int size;
    static final Random random = new Random();
    IndexVector(int size){
        this.size = size;
        entries = new double[size];
    }

    public double getScore(IndexVector other) {
        double result = 0;
        for (int i = 0; i<size; i++){
            result += entries[i]*other.entries[i];
        }
        return result - (int)result;
    }
}

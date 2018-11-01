import java.util.Random;

public class IndexVector {
    double [] entries;
    private int size;
    static final Random random = new Random();
    IndexVector(int size){
        this.setSize(size);
        entries = new double[size];
    }

    public double getScore(IndexVector other) {
        double result = 0;
        for (int i = 0; i< getSize(); i++){
            result += entries[i]*other.entries[i];
        }
        return result - (int)result;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}

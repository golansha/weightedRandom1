/**
 * Created by sgolan on 11/1/2018.
 */
public class ScoreCalculatorNoRandomRotation implements ScoreCalculator {

    @Override
    public double getScore(IndexVector vector1, IndexVector vector2) {
        double result = 0;
        for (int i = 0; i<vector1.getSize()-1; i++){
            result += vector1.entries[i]*vector2.entries[i];
        }
        return result - (int)result;
    }
}

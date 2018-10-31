public class QueryVector extends IndexVector {
    QueryVector(int size){
        super(size);
        boolean hasNonZero = false;
        while (!hasNonZero) {
            for (int i = 0; i < size - 1; i++) {
                entries[i] = random.nextInt(2);
                if (entries[i] !=0){
                    hasNonZero = true;
                }
            }
        }
        entries[size - 1] = random.nextDouble();
    }
}

public class DocumentVector extends IndexVector {
    DocumentVector(int size){
        super(size);
        for (int i = 0; i<size-1 ; i++){
            entries[i] = random.nextDouble();
        }
        entries[size - 1] = 1;
    }
}

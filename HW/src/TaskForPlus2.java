import java.util.Arrays;

public class TaskForPlus2 {
    public static void main(String[] args) {
        int[] arrayIn = {10, 15, 1, 6, 2, 10, 9, 1, 3};
        int size=arrayIn.length;
        int[] tempFront = new int[size];
        int[] tempEnd = new int[size];
        int[] arrayOut = new int[size];
        int tempA=1;
        int tempB=1;
        for(int i=0;i<size;i++){
            tempFront[i]=tempA;
            tempA*=arrayIn[i];
        }
        for(int i=size-1;i>=0;i--){
            tempEnd[i]=tempB;
            tempB*=arrayIn[i];
        }
        for(int i=0;i<size;i++){
            arrayOut[i]=tempFront[i]*tempEnd[i];
        }
        System.out.println(Arrays.toString(arrayOut));
    }
}

package exception;

/**
 * Created by ubuntu.
 */
public class ExceptionTest {
    public static void main(String[] args){
        int[] item = {1, 4, 0, 5, 5, 5};
        for(int i = 0; i < 6; i++){
            System.out.println("result is " + 20/item[i]);
//            try{
//                System.out.println("result is " + 20/item[i]);
//            } catch (Exception e){
//                e.printStackTrace();
//            }
        }
    }
}

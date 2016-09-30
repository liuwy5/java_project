package thread;

/**
 * Created by ubuntu.
 */
public class Thread1 extends Thread{
    private int content(){
        new Thread1().start();
        return 1;
    }
    public void run(){
        for(int i = 0; i< 50; i++){
            System.out.println(i);
        }
    }
    public static void main(String[] args){
        Thread1 thread1 = new Thread1();
        int s = thread1.content();
        System.out.println("sfdsfsdf" + s);
    }
}


package vectorthreadaddition;
import java.util.concurrent.*;

public class VectorThreadAddition {

    public static void main(String[] args) {
    final int thread_count = 2;
    int [] x = {1, 2, 3, 4, 5, 6};
    int [] y = {7, 8, 9, 10, 11, 12};
    int [] z = new int[x.length];
  long StartTime = System.currentTimeMillis();
  ExecutorService executor = Executors.newCachedThreadPool();
  
   for (int i = 0; i < thread_count; i++) {
          executor.execute(new VectorAddition(x,y,z,i,thread_count));
        }
   executor.shutdown();
        while (!executor.isTerminated()) {
       }
    long EndTime = System.currentTimeMillis();

        System.out.println("results Vector = ");
        for (int i = 0; i < z.length; i++) {
            System.out.println(z[i]);
            
        }
        System.out.println("With time of " + (EndTime-StartTime) + " milliseconds");
        
        

    }

static class VectorAddition implements Runnable {
int[] x;
int[] y;
int[] z;
int i;
int thread_count;
   public VectorAddition(int[] x, int[] y, int[] z, int i, int thread_count) {
       this.x = x; 
       this.y = y;
       this.z = z;
       this.i = i;
       this.thread_count = thread_count;
        }

        @Override
        public void run() {
         int  my_local_m = x.length/thread_count;
         int  myFirstRow = my_local_m * i;
         int  mylastrow = myFirstRow + my_local_m;
         
         for (int j = myFirstRow ; j < mylastrow; j++) {
              z[j] = x[j]+y[j];
           }      
        }
    }
    
}

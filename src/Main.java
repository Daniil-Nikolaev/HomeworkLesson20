import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //1.
        System.out.println("Сколько значений вы хотите ввести? ");
        Scanner scanner = new Scanner(System.in);
        int length = scanner.nextInt();
        int[] array = new int[length];

        System.out.println("Введите значения для массива: ");
        for (int i = 0; i < length; i++) {
            array[i] = scanner.nextInt();
        }

        Thread t1 = new Thread(() -> {
            int max=array[0];
            for (int i = 0; i < length; i++) {
                if (array[i]>max) {
                    max = array[i];
                }
            }
            System.out.println("Максимальное значение: "+max);
        });


        Thread t2 = new Thread(() -> {
            int min=array[0];
            for (int i = 0; i < length; i++) {
                if (array[i] < min) {
                    min = array[i];
                }
            }
            System.out.println("Минимальное значение: "+min);
        });

        t1.start();
        t2.start();

        //2

        Thread insertion=new Thread(()->{
            int j;
            long start= System.currentTimeMillis();
            for (int i = 1; i < array.length; i++) {
                int swap = array[i];
                for (j = i; j > 0 && swap < array[j - 1]; j--) {
                    array[j] = array[j- 1];
                }
                array[j] = swap;
            }
            long end= System.currentTimeMillis();
            System.out.println(Arrays.toString(array));
            System.out.println(end-start);
        });

        Thread selection=new Thread(()->{
            long start= System.currentTimeMillis();
            for (int i = 0; i < array.length; i++) {
                int pos = i;
                int min = array[i];
                for (int j = i + 1; j < array.length; j++) {
                    if (array[j] < min) {
                        pos = j;
                        min = array[j];
                    }
                }
                array[pos] = array[i];
                array[i] = min;
            }
            long end= System.currentTimeMillis();
            System.out.println(Arrays.toString(array));
            System.out.println(end-start);
        });

        Thread bubble=new Thread(()->{
            long start= System.currentTimeMillis();
            for (int i = 0; i < array.length - 1; i++) {
                for(int j = 0; j < array.length - i - 1; j++) {
                    if(array[j+1] < array[j]) {
                        int swap = array[j];
                        array[j] = array[j+1];
                        array[j+1] = swap;
                    }
                }
            }
            long end= System.currentTimeMillis();
            System.out.println(Arrays.toString(array));
            System.out.println(end-start);
        });
        insertion.start();
        selection.start();
        bubble.start();




    }
}
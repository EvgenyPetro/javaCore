package philosopher;

public class Philosopher extends Thread {

    int eating = 3;
    private String name;
    private Fork left, right;

    public Philosopher(String name, Fork left, Fork right) {
        this.name = name;
        this.left = left;
        this.right = right;
    }

    @Override
    public void run() {
        while (eating != 0) {
            try {
                eat();
                think();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void eat() throws InterruptedException {
        if (left.isFree && right.isFree) {
            left.isFree = false;
            right.isFree = false;
            System.out.printf("%s ест%n", name);
            eating--;
            sleep(50);
            left.isFree = true;
            right.isFree = true;
        } else
            sleep(50);
    }

    private void think() throws InterruptedException {

        System.out.printf("%s размышляет%n", name);
        sleep(100);
    }
}

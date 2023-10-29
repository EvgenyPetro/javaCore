package philosopher;

public class Fork {
    volatile boolean isFree;

    public Fork(boolean isFree) {
        this.isFree = isFree;
    }
}

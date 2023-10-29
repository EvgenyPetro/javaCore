package philosopher;

import java.util.ArrayList;
import java.util.List;

public class Table {
    List<Philosopher> table = new ArrayList<>(5);

    public void et() {
        Fork fork1 = new Fork(true);
        Fork fork2 = new Fork(true);
        Fork fork3 = new Fork(true);
        Fork fork4 = new Fork(true);
        Fork fork5 = new Fork(true);

        table.add(new Philosopher("1", fork1, fork2));
        table.add(new Philosopher("2", fork2, fork3));
        table.add( new Philosopher("3", fork3, fork4));
        table.add( new Philosopher("4", fork4, fork5));
        table.add( new Philosopher("5", fork5, fork1));

        table.forEach(Philosopher::start);

    }

}

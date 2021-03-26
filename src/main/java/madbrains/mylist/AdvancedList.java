package madbrains.mylist;

import java.util.Comparator;
import java.util.stream.Stream;

public interface AdvancedList<T> extends SimpleList<T> {
    AdvancedList<T> shuffle();
    AdvancedList<T> sort(Comparator<T> comparator);
    void print();
}
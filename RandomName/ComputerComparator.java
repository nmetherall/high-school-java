import java.util.Comparator;

public class ComputerComparator implements Comparator<NameRecord> {
 public int compare(NameRecord sr, NameRecord anotherSR) {
    String computer1 = (sr).computer;
    String computer2 = (anotherSR).computer;
      return computer1.compareTo(computer2);
  }
}
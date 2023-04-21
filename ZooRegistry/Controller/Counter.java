package ZooRegistry.Controller;

public class Counter implements AutoCloseable{
  private int count = 0;
  private boolean isOpen = true;

  public void add() throws IllegalStateException {
      if (!isOpen) {
          throw new IllegalStateException("Работа со счетчиком была не в ресурсном try или ресурс остался открыт");
      }
      count++;
  }

  public int getCount() {
      return count;
  }

  @Override
  public void close() {
      System.out.println("Создано питомцев: " + count);
      isOpen = false;
  }
}

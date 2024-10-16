package baseNoStates;

public interface DoorState {
  void open(Door door);
  void close(Door door);
  void lock(Door door);
  void unlock(Door door);
  String getStateName();
}

package baseNoStates.areas;

import baseNoStates.doors.Door;

public interface Visitor {
  void visitPartition(Partition partition);
  void visitSpace(Space space);
  void visitDoor(Door door);
}

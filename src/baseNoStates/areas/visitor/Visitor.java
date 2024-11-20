package baseNoStates.areas.visitor;

import baseNoStates.areas.Partition;
import baseNoStates.areas.Space;
import baseNoStates.doors.Door;

public interface Visitor {
  void visitPartition(Partition partition);
  void visitSpace(Space space);
  void visitDoor(Door door);
}

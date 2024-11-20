package basenostates.areas.visitor;

import basenostates.areas.Partition;
import basenostates.areas.Space;
import basenostates.doors.Door;

public interface Visitor {
  void visitPartition(Partition partition);
  void visitSpace(Space space);
  void visitDoor(Door door);
}

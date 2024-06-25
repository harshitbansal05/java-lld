package sa.com.barraq.chess.conditions;

import sa.com.barraq.chess.model.Piece;

public class NoMoveBaseCondition implements MoveBaseCondition {
    public boolean isBaseConditionFullfilled(Piece piece) {
        return true;
    }
}

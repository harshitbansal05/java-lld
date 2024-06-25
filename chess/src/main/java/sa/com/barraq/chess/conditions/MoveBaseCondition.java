package sa.com.barraq.chess.conditions;

import sa.com.barraq.chess.model.Piece;

public interface MoveBaseCondition {
    boolean isBaseConditionFullfilled(Piece piece);
}

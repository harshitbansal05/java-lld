package sa.com.barraq.chess.conditions;

import sa.com.barraq.chess.model.Piece;

public class MoveBaseConditionFirstMove implements MoveBaseCondition {

    public boolean isBaseConditionFullfilled(Piece piece) {
        return piece.getNumMoves() == 0;
    }
}

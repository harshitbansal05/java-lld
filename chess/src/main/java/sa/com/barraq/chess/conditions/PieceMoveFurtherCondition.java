package sa.com.barraq.chess.conditions;

import sa.com.barraq.chess.model.*;

public interface PieceMoveFurtherCondition {
    boolean canPieceMoveFurtherFromCell(Piece piece, Cell cell, Board board);
}

package sa.com.barraq.chess.conditions;

import sa.com.barraq.chess.model.Board;
import sa.com.barraq.chess.model.Cell;
import sa.com.barraq.chess.model.Piece;
import sa.com.barraq.chess.model.Player;

public class PieceCellOccupyBlockerSelfPiece implements PieceCellOccupyBlocker {

    @Override
    public boolean isCellNonOccupiableForPiece(Cell cell, Piece piece, Board board, Player player) {
        if (cell.isFree()) {
            return false;
        }
        return cell.getCurrentPiece().getColor() == piece.getColor();
    }
}

package sa.com.barraq.chess.conditions;

import sa.com.barraq.chess.model.*;

public class PieceCellOccupyBlockerKingCheck implements PieceCellOccupyBlocker {

    @Override
    public boolean isCellNonOccupiableForPiece(final Cell cell, final Piece piece, final Board board, Player player) {
        Cell pieceOriginalCell = piece.getCurrentCell();
        piece.setCurrentCell(cell);
        boolean playerGettingCheckByMove = board.isPlayerOnCheck(player);
        piece.setCurrentCell(pieceOriginalCell);
        return playerGettingCheckByMove;
    }
}

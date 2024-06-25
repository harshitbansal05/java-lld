package sa.com.barraq.chess.moves;

import sa.com.barraq.chess.conditions.MoveBaseCondition;
import sa.com.barraq.chess.conditions.PieceCellOccupyBlocker;
import sa.com.barraq.chess.conditions.PieceMoveFurtherCondition;
import sa.com.barraq.chess.model.*;

import java.util.List;

public class PossibleMovesProviderDiagonal extends PossibleMovesProvider {

    public PossibleMovesProviderDiagonal(int maxSteps, MoveBaseCondition baseCondition,
                                         PieceMoveFurtherCondition moveFurtherCondition, PieceCellOccupyBlocker baseBlocker) {
        super(maxSteps, baseCondition, moveFurtherCondition, baseBlocker);
    }

    @Override
    protected List<Cell> possibleMovesAsPerCurrentType(Piece piece, Board board, List<PieceCellOccupyBlocker> additionalBlockers, Player player) {
        return null;
    }
}

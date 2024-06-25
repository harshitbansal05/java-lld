package sa.com.barraq.chess.moves;

import sa.com.barraq.chess.conditions.MoveBaseCondition;
import sa.com.barraq.chess.conditions.PieceCellOccupyBlocker;
import sa.com.barraq.chess.conditions.PieceMoveFurtherCondition;
import sa.com.barraq.chess.model.*;

import java.util.ArrayList;
import java.util.List;

public class PossibleMovesProviderHorizontal extends PossibleMovesProvider {

    public PossibleMovesProviderHorizontal(int maxSteps, MoveBaseCondition baseCondition,
                                           PieceMoveFurtherCondition moveFurtherCondition, PieceCellOccupyBlocker baseBlocker) {
        super(maxSteps, baseCondition, moveFurtherCondition, baseBlocker);
    }

    protected List<Cell> possibleMovesAsPerCurrentType(Piece piece, final Board board, List<PieceCellOccupyBlocker> additionalBlockers, Player player) {
        List<Cell> result = new ArrayList<>();
        result.addAll(findAllNextMoves(piece, board::getLeftCell, board, additionalBlockers, player));
        result.addAll(findAllNextMoves(piece, board::getRightCell, board, additionalBlockers, player));
        return result;
    }
}

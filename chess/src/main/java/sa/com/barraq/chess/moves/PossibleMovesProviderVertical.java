package sa.com.barraq.chess.moves;

import sa.com.barraq.chess.model.*;

import sa.com.barraq.chess.conditions.MoveBaseCondition;
import sa.com.barraq.chess.conditions.PieceCellOccupyBlocker;
import sa.com.barraq.chess.conditions.PieceMoveFurtherCondition;

import java.util.ArrayList;
import java.util.List;

import static sa.com.barraq.chess.moves.VerticalMoveDirection.*;

public class PossibleMovesProviderVertical extends PossibleMovesProvider {
    private final VerticalMoveDirection verticalMoveDirection;

    public PossibleMovesProviderVertical(int maxSteps, MoveBaseCondition baseCondition,
                                         PieceMoveFurtherCondition moveFurtherCondition, PieceCellOccupyBlocker baseBlocker,
                                         VerticalMoveDirection verticalMoveDirection) {
        super(maxSteps, baseCondition, moveFurtherCondition, baseBlocker);
        this.verticalMoveDirection = verticalMoveDirection;
    }

    @Override
    protected List<Cell> possibleMovesAsPerCurrentType(Piece piece, Board board, List<PieceCellOccupyBlocker> additionalBlockers, Player player) {
        List<Cell> result = new ArrayList<>();
        if (this.verticalMoveDirection == UP || this.verticalMoveDirection == BOTH) {
            result.addAll(findAllNextMoves(piece, board::getUpCell, board, additionalBlockers, player));
        }
        if (this.verticalMoveDirection == DOWN || this.verticalMoveDirection == BOTH) {
            result.addAll(findAllNextMoves(piece, board::getDownCell, board, additionalBlockers, player));
        }
        return result;
    }
}

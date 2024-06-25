package sa.com.barraq.chess.moves;

import sa.com.barraq.chess.conditions.MoveBaseCondition;
import sa.com.barraq.chess.conditions.PieceCellOccupyBlocker;
import sa.com.barraq.chess.conditions.PieceMoveFurtherCondition;
import sa.com.barraq.chess.model.*;

import java.util.ArrayList;
import java.util.List;

public abstract class PossibleMovesProvider {
    int maxSteps;
    MoveBaseCondition baseCondition;
    PieceMoveFurtherCondition moveFurtherCondition;
    PieceCellOccupyBlocker baseBlocker;

    public PossibleMovesProvider(int maxSteps, MoveBaseCondition baseCondition, PieceMoveFurtherCondition moveFurtherCondition, PieceCellOccupyBlocker baseBlocker) {
        this.maxSteps = maxSteps;
        this.baseCondition = baseCondition;
        this.moveFurtherCondition = moveFurtherCondition;
        this.baseBlocker = baseBlocker;
    }

    /**
     * Public method which actually gives all possible cells which can be reached via current type of move.
     */
    public List<Cell> possibleMoves(Piece piece, Board inBoard, List<PieceCellOccupyBlocker> additionalBlockers, Player player) {
        if (baseCondition.isBaseConditionFullfilled(piece)) {
            return possibleMovesAsPerCurrentType(piece, inBoard, additionalBlockers, player);
        }
        return null;
    }

    /**
     * Abstract method which needs to be implemented by each type of move to give possible moves as per their behaviour.
     */
    protected abstract List<Cell> possibleMovesAsPerCurrentType(Piece piece, Board board, List<PieceCellOccupyBlocker> additionalBlockers, Player player);

    /**
     * Helper method used by all the subtypes to create the list of cells which can be reached.
     */
    protected List<Cell> findAllNextMoves(Piece piece, NextCellProvider nextCellProvider, Board board, List<PieceCellOccupyBlocker> cellOccupyBlockers, Player player) {
        List<Cell> result = new ArrayList<>();
        Cell nextCell = nextCellProvider.nextCell(piece.getCurrentCell());
        int numSteps = 1;
        while (nextCell != null && numSteps <= maxSteps) {
            if (checkIfCellCanBeOccupied(piece, nextCell, board, cellOccupyBlockers, player)) {
                result.add(nextCell);
            }
            if (!moveFurtherCondition.canPieceMoveFurtherFromCell(piece, nextCell, board)) {
                break;
            }

            nextCell = nextCellProvider.nextCell(nextCell);
            numSteps++;
        }
        return result;
    }

    /**
     * Helper method which checks if a given cell can be occupied by the piece or not. It makes use of list of
     * {@link PieceCellOccupyBlocker}s passed to it while checking. Also each move has one base blocker which it should
     * also check.
     */
    private boolean checkIfCellCanBeOccupied(Piece piece, Cell cell, Board board, List<PieceCellOccupyBlocker> additionalBlockers, Player player) {
        if (baseBlocker != null && baseBlocker.isCellNonOccupiableForPiece(cell, piece, board, player)) {
            return false;
        }
        for (PieceCellOccupyBlocker cellOccupyBlocker : additionalBlockers) {
            if (cellOccupyBlocker.isCellNonOccupiableForPiece(cell, piece, board, player)) {
                return false;
            }
        }
        return true;
    }
}

package sa.com.barraq.chess.model;

import lombok.Getter;
import sa.com.barraq.chess.exceptions.PieceNotFoundException;
import sa.com.barraq.gameplay.contracts.PlayerMove;

import java.util.List;

@Getter
public abstract class Player {
    List<Piece> pieces;

    public Player(List<Piece> pieces) {
        this.pieces = pieces;
    }

    public Piece getPiece(PieceType pieceType) {
        for (Piece piece : getPieces()) {
            if (piece.getPieceType() == pieceType) {
                return piece;
            }
        }
        throw new PieceNotFoundException();
    }

    abstract public PlayerMove makeMove();
}

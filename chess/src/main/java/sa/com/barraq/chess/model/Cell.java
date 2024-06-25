package sa.com.barraq.chess.model;

import lombok.Getter;
import lombok.Setter;

@Getter
public class Cell {

    private final int x;
    private final int y;

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Setter
    private Piece currentPiece;

    public boolean isFree() {
        return currentPiece == null;
    }
}

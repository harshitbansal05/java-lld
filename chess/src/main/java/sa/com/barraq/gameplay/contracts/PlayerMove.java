package sa.com.barraq.gameplay.contracts;

import lombok.Getter;
import sa.com.barraq.chess.model.Cell;
import sa.com.barraq.chess.model.Piece;

@Getter
public class PlayerMove {

    Piece piece;
    Cell toCell;
}

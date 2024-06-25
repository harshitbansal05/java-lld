package sa.com.barraq.chess.moves;

import sa.com.barraq.chess.model.Cell;

public interface NextCellProvider {
    Cell nextCell(Cell cell);
}

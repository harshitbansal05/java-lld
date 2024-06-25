package sa.com.barraq.chess.conditions;

import java.util.List;
import com.google.common.collect.ImmutableList;


public class PieceCellOccupyBlockerFactory {

    public static PieceCellOccupyBlocker defaultBaseBlocker() {
        return new PieceCellOccupyBlockerSelfPiece();
    }

    public static List<PieceCellOccupyBlocker> defaultAdditionalBlockers() {
        return ImmutableList.of(new PieceCellOccupyBlockerKingCheck());
    }

    public static List<PieceCellOccupyBlocker> kingCheckEvaluationBlockers() {
        return ImmutableList.of();
    }
}

package ntut.csie.islab.miro.entity.board;

import ntut.csie.islab.miro.entity.model.board.Board;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {
    private Board createBoard(){
        return new Board(UUID.randomUUID(), "board name");
    }
    @BeforeEach
    void setUp() {

    }

    @Test
    public void commit_a_figure(){
        Board board = createBoard();
        UUID figureId = UUID.randomUUID();
        board.commitFigure(figureId);

        assertEquals(1, board.getCommittedFigures().size());
        assertEquals(figureId, board.getCommittedFigures().get(0).getFigureId());
    }

}
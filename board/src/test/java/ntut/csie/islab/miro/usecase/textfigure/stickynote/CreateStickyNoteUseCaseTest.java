package ntut.csie.islab.miro.usecase.textfigure.stickynote;

import ntut.csie.islab.miro.adapter.repository.textfigure.TextFigureRepository;
import ntut.csie.islab.miro.entity.textfigure.Position;
import ntut.csie.islab.miro.entity.textfigure.ShapeKindEnum;
import ntut.csie.islab.miro.entity.textfigure.Style;
import ntut.csie.islab.miro.usecase.textFigure.stickynote.CreateStickyNoteUseCase;
import ntut.csie.islab.miro.usecase.textFigure.stickynote.CreateStickyNoteUseInput;
import ntut.csie.sslab.ddd.adapter.gateway.GoogleEventBus;
import ntut.csie.sslab.ddd.adapter.presenter.cqrs.CqrsCommandPresenter;
import ntut.csie.sslab.ddd.model.DomainEventBus;
import ntut.csie.sslab.ddd.usecase.cqrs.ExitCode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CreateStickyNoteUseCaseTest {
    public TextFigureRepository textFigureRepository;
    public DomainEventBus domainEventBus;

    @BeforeEach
    public void setUp(){
        textFigureRepository = new TextFigureRepository();
        domainEventBus = new GoogleEventBus();
    }

    @Test
    public void create_stickyNote_in_board_test() {
        CreateStickyNoteUseCase createStickyNoteUseCase = new CreateStickyNoteUseCase(textFigureRepository, domainEventBus);
        CreateStickyNoteUseInput input = createStickyNoteUseCase.newInput();
        CqrsCommandPresenter output = CqrsCommandPresenter.newInstance();
        UUID boardId = UUID.randomUUID();
        Position position = new Position(100, 100);
        String content = "";
        Style style = new Style(20, ShapeKindEnum.RECTANGLE, 200, 200, "#f9f900");
        input.setBoardId(boardId);
        input.setPosition(position);
        input.setContent(content);
        input.setStyle(style);
        createStickyNoteUseCase.execute(input, output);

        assertNotNull(output.getId());

        assertEquals(ExitCode.SUCCESS, output.getExitCode());

        assertEquals(boardId, textFigureRepository.findById(boardId, UUID.fromString(output.getId())).get().getBoardId());
    }
}

package ntut.csie.islab.miro.entity.model.textFigure.stickynote;


import ntut.csie.islab.miro.entity.model.textFigure.Style;
import ntut.csie.islab.miro.entity.model.textFigure.Position;
import ntut.csie.islab.miro.entity.model.textFigure.TextFigure;
import ntut.csie.islab.miro.entity.model.textFigure.stickynote.event.*;

import java.util.UUID;

public class StickyNote extends TextFigure {
    public StickyNote(UUID boardId, Position position, String content, Style style) {
        super(boardId, position, content, style);

        addDomainEvent(new StickyNoteCreatedDomainEvent(boardId, getTextFigureId()));
    }

    public StickyNote(UUID boardId, UUID stickyNoteId, Position position, String content, Style style) {
        super(boardId, position, content, style);

        addDomainEvent(new StickyNoteCreatedDomainEvent(boardId, getTextFigureId()));
    }

}

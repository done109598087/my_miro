package ntut.csie.islab.miro.entity.model.stickynote;

import ntut.csie.islab.miro.entity.model.textFigure.TextFigure;
import ntut.csie.islab.miro.entity.model.textFigure.Position;
import ntut.csie.islab.miro.entity.model.textFigure.ShapeKindEnum;
import ntut.csie.islab.miro.entity.model.textFigure.Style;
import ntut.csie.islab.miro.entity.model.textFigure.TextFigure;
import ntut.csie.islab.miro.entity.model.textFigure.stickynote.StickyNote;
import ntut.csie.islab.miro.entity.model.textFigure.stickynote.event.StickyNoteCreatedDomainEvent;
import ntut.csie.islab.miro.entity.model.textFigure.stickynote.event.StickyNoteDeletedDomainEvent;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StickyNoteDomainEventTest {

    @Test
    public void create_a_stickyNote_than_publishes_a_stickyNote_created_domain_event_test() {
        TextFigure stickyNote = new StickyNote(UUID.randomUUID(), new Position(1.0, 1.0), "stickynote_content", new Style(20, ShapeKindEnum.RECTANGLE, 100, 100, "f9f900"));
        assertEquals(1, stickyNote.getDomainEvents().size());
    }

    @Test
    public void stickyNote_deleted_domain_event_test() {
        TextFigure stickyNote = new StickyNote(UUID.randomUUID(), new Position(1.0, 1.0), "stickynote_content", new Style(20, ShapeKindEnum.RECTANGLE, 100, 100, "f9f900"));
        assertEquals(1, stickyNote.getDomainEvents().size());
        stickyNote.addDomainEvent(new StickyNoteDeletedDomainEvent(stickyNote.getBoardId(), stickyNote.getTextFigureId()));
        assertEquals(2, stickyNote.getDomainEvents().size());
    }
}

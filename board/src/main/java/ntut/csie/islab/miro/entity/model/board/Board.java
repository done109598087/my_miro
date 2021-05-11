package ntut.csie.islab.miro.entity.model.board;

import ntut.csie.islab.miro.entity.model.board.event.BoardCreatedDomainEvent;
import ntut.csie.islab.miro.entity.model.board.event.FigureChangedDomainEvent;
import ntut.csie.islab.miro.entity.model.board.event.FigureCommittedDomainEvent;
import ntut.csie.islab.miro.entity.model.board.event.FigureUncommittedDomainEvent;
import ntut.csie.islab.miro.entity.model.textFigure.TextFigure;
import ntut.csie.sslab.ddd.model.AggregateRoot;
import org.checkerframework.checker.units.qual.C;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static java.lang.String.format;
import static ntut.csie.sslab.ddd.model.common.Contract.ensure;
import static ntut.csie.sslab.ddd.model.common.Contract.requireNotNull;

public class Board extends AggregateRoot<UUID> {
    private UUID teamId;
    private String boardName;
    private List<CommittedFigure> figureList;

    public Board(UUID teamId,String boardName){
        super(UUID.randomUUID());
        this.teamId = teamId;
        this.boardName = boardName;
        this.figureList = new ArrayList<CommittedFigure>();
        addDomainEvent(new BoardCreatedDomainEvent(teamId, getBoardId()));

    }

    public Board(UUID teamId,UUID boardId,String boardName){
        super(boardId);
        this.teamId = teamId;
        this.boardName = boardName;
        this.figureList = new ArrayList<CommittedFigure>();
        addDomainEvent(new BoardCreatedDomainEvent(teamId, getBoardId()));

    }

    public UUID getTeamId() {
        return teamId;
    }

    public void setTeamId(UUID temaId) {
        this.teamId = temaId;
    }

    public String getBoardName() {
        return boardName;
    }

    public void setBoardName(String boardName) {
        this.boardName = boardName;
    }

    public UUID getBoardId(){
        return this.getId();
    }

    public void commitFigure(UUID figureId) {
        addFigure(figureId);
        addDomainEvent(new FigureCommittedDomainEvent(getBoardId(), figureId));

    }
    public void uncommitFigure(UUID figureId) {
        requireNotNull("figureId id", figureId);

        removeFigure(figureId);
        addDomainEvent(new FigureUncommittedDomainEvent(getBoardId(), figureId));

    }

    private void addFigure(UUID figureId) {
        CommittedFigure committedFigure = new CommittedFigure(getBoardId(), figureId);
        this.figureList.add(committedFigure);
    }

    private void removeFigure(UUID figureId) {
        for(int i = 0; i < figureList.size(); i++){
            if(figureList.get(i).getFigureId().equals(figureId)) {
                figureList.remove(i);
            }
        }
    }


    public List<CommittedFigure> getCommittedFigures() {
        return this.figureList;
    }
    public void setCommittedFigureListOrder(List<UUID> figureOrderList) {

        List<CommittedFigure> newCommittedFigureList = new ArrayList<CommittedFigure>();
        for (UUID figureId : figureOrderList) {
            if (figureList.stream().filter(s -> s.getFigureId().equals(figureId)).findFirst().isPresent()) {
                newCommittedFigureList.add(new CommittedFigure(this.getBoardId(),figureId));
            }
        }
        figureList = newCommittedFigureList;

        addDomainEvent(new FigureChangedDomainEvent(this.getBoardId(), this.figureList));

    }
}
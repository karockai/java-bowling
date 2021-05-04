package bowling.domain;

import bowling.WrongFrameNumberException;
import bowling.domain.state.FrameState;
import bowling.domain.state.FrameStateFinalReady;

import java.util.List;

public class FinalFrame implements Frame {
    private FrameState currentState = new FrameStateFinalReady();

    public FinalFrame(FrameNumber frameNumber) {
        if (!frameNumber.equals(new FrameNumber(10))) {
            throw new WrongFrameNumberException("프레임번호가 잘못되었습니다");
        }
    }

    public FinalFrame(Pinfall firstPinfall, Pinfall secondPinfall) {
        this();
        roll(firstPinfall);
        roll(secondPinfall);
    }

    public FinalFrame(Pinfall pinfall) {
        this();
        roll(pinfall);
    }

    public FinalFrame() {
    }


    @Override
    public FinalFrame roll(Pinfall pinfall) {
        currentState = currentState.roll(pinfall);
        return this;
    }

    @Override
    public boolean isDone() {
        return !currentState.isRollable();
    }

    @Override
    public FrameNumber frameNumber() {
        return new FrameNumber(10);
    }

    @Override
    public PointSymbols pointSymbols() {
        return currentState.pointSymbols();
    }

    @Override
    public Frame roll(Pinfall pinfall, FrameFatory frameFatory) {
        return roll(pinfall);
    }

    @Override
    public Score score() {
        return currentState.score();
    }

    @Override
    public List<Pinfall> bonusPinfalls(int bonusPinfallCount) {
        return currentState.pinfalls();
    }

    @Override
    public boolean hasNext() {
        return false;
    }

    @Override
    public Frame next() {
        return null;
    }
}
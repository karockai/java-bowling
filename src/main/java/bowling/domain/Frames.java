package bowling.domain;

import java.util.ArrayList;
import java.util.List;

public class Frames {
    private List<NormalFrame> normalFrames;
    private FinalFrame finalFrame = new FinalFrame();

    public List<NormalFrame> getNormalFrames() {
        return normalFrames;
    }

    public Frames() {
        this.normalFrames = new ArrayList<>();
    }

    public int currentFrame() {
        return normalFrames.size();
    }

    public void addNormalFrame(NormalFrame normalFrame) {
        normalFrames.add(normalFrame);
    }

    public void addFinalFrame(FinalFrame finalFrame) {
        this.finalFrame = finalFrame;
    }

    public boolean isNextFrame() {
        return normalFrames.get(currentFrame() - 1).isNextFrame();
    }

    public boolean isEndFinalFrame() {
        return finalFrame.isEndFinalFrame();
    }
}

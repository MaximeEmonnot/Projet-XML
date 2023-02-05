import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Animation {
    public enum EAnimationMode 
    {
        NoLoop,
        LoopBack,
        LoopAll
    }

    public Animation(ArrayList<String> _frames, float _holdTime, EAnimationMode _animMode){
        frames = _frames;
        holdTime = _holdTime;
        animMode = _animMode;
    }

    public void Update() {
        curFrameTime += Timer.GetInstance().DeltaTime();
        while(curFrameTime > holdTime) Advance();
    }

    public ImageIcon GetSprite() {
        return SpriteFactory.GetInstance().GetSprite(frames.get(iCurFrame));
    }

    public void Reset() {
        iCurFrame = 0;
        threshold = 1;
        bIsFinished = false;
    }

    public boolean IsFinished() {
        return bIsFinished;
    }

    private void Advance(){
        iCurFrame += threshold;
        if (iCurFrame >= frames.size() || iCurFrame < 0) {
            switch(animMode){
                case LoopAll:
                    iCurFrame = 0;
                    break;
                case LoopBack:
                    bIsFinished = (iCurFrame < 0);
                    threshold *= -1;
                    iCurFrame += 2 * threshold;
                    break;
                case NoLoop:
                    iCurFrame = frames.size() - 1;
                    bIsFinished = true;
                    break;
                default:
                    break;
            }
        }
        else bIsFinished = false;
        curFrameTime -= holdTime;
    }

    private ArrayList<String> frames = new ArrayList<String>();
    private float holdTime;
    private EAnimationMode animMode;
    private float curFrameTime = 0.f;
    private int iCurFrame = 0;
    private int threshold = 1;
    private boolean bIsFinished = false;
}

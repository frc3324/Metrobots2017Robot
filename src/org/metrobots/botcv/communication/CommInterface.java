package org.metrobots.botcv.communication;

public interface CommInterface {
    float[] getOrientation(boolean returnAngle);
    float[] getAccelerometer();
    int getFiringStatus();
    int getXStatus();
    int getMagnitude(); 
    int getDirection();
    int getXOffset();
    int getYOffset();
}

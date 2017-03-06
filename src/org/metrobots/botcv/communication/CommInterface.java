package org.metrobots.botcv.communication;

public interface CommInterface {
    float[] getOrientation(boolean returnAngle);
    float[] getAccelerometer();
    int getFiringStatus();
    int getXStatus();
    int getMagnitude(); //or maybe getMagnitude()
    int getDirection(); //or maybe getDirection()
//github.com/frc3324/Metrobots2017Robot.git
}

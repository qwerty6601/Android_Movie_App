package com.example.billard_app_02;

/***
 * Class for vector which will be drawn from white ball at current position to white ball when in contact with
 * colored ball, as well as for vector which will be drawn from colored ball to target hole
 */
public class WhiteBallVector {

    // X and Y coordinates of the target hole
    double targetHoleX, targetHoleY;
    // X and Y coordinates of the colored Ball
    double coloredBallX, coloredBallY;

    // X coordinate of white ball at original position
    double whiteBallX, whiteBallY;

    // Radius of ball
    double ballRadius;

    public WhiteBallVector(double targetHoleX, double targetHoleY, double coloredBallX, double coloredBallY, double whiteBallX, double whiteBallY, double ballRadius) {
        this.targetHoleX = targetHoleX;
        this.targetHoleY = targetHoleY;
        this.coloredBallX = coloredBallX;
        this.coloredBallY = coloredBallY;
        this.whiteBallX = whiteBallX;
        this.whiteBallY = whiteBallY;
        this.ballRadius = ballRadius;
    }

    /***
     * find where the white ball should be at when colliding with colored ball, in roder to hit colored ball into hole
     */
    public void findCollisionPoint(){
        double whiteCollideX = coloredBallX - 2*ballRadius*(targetHoleX-coloredBallX)/Math.sqrt((targetHoleX-coloredBallX)*(targetHoleX-coloredBallX) + (targetHoleY-coloredBallY)*(targetHoleY-coloredBallY));
        double whiteCollideY = coloredBallX - 2*ballRadius*(targetHoleX-coloredBallX)/Math.sqrt((targetHoleX-coloredBallX)*(targetHoleX-coloredBallX) + (targetHoleY-coloredBallY)*(targetHoleY-coloredBallY));

        //double whiteVectorX = (whiteCollideX- whiteBallX);
        //double whiteVectorY = (whiteCollideX- whiteBallY);
        drawVectorToBall(whiteCollideX, whiteCollideY); // better this way or wCollideX, wCollideY as fields?
        drawVectorToHole(whiteCollideX, whiteCollideY);
    }

    /***
     * startPoint: (whiteBallX, whiteBallY)
     * endPoint: (whiteCollideX, whiteCollideY)
     * draws a vector from original position of whiteBall to where the whiteball should collide with colored ball
     * @param whiteCollideX x coordinate of center of where white ball should collide w/ red ball
     *                      in order to hit target hole (when no other balls in the way)
     * @param whiteCollideY y coordinate of center of where white ball should collide w/ red ball
     *                      in order to hit target hole (when no other balls in the way)
     */
    public void drawVectorToBall(double whiteCollideX, double whiteCollideY){

    }

    /***
     * startPoint: (whiteCollideX, whiteCollideY)
     * endPoint: (targetHoleX, targetHoleY)
     * draws a vector from chosen colored ball to the target hole
     * @param whiteCollideX x coordinate of center of where white ball should collide w/ red ball
     *                       in order to hit target hole (when no other balls in the way)
     * @param whiteCollideY y coordinate of center of where white ball should collide w/ red ball
     *                       in order to hit target hole (when no other balls in the way)
     */
    public void drawVectorToHole(double whiteCollideX, double whiteCollideY){

    }

}

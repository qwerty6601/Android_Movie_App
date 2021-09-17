package com.example.billard_app_02;

import com.Helpers.MatrixSolver;

public class BallDetector{

    double totalX;
    double totalY;
    double totalXX;
    double totalXY;
    double totalYY;
    double one;
    double rightRowOne;
    double rightRowTwo;
    double rightRowThree;
    double [][] matrix= new double[3][3];
    double[] B= new double[3];
    MatrixSolver ge= new MatrixSolver();
    public double cx;
    public double cy;
    public double r;
    int counter;


    public void fit() {
        positEquationSystem();
        double[] solutionVector= new double[3];
        ge.solve(matrix, B, solutionVector);
        /*positEquationSystem();
        for(int i=0; i<3; i++){
            double sum=0;
            for (int j=0; j<3; j++){
                sum += matrix[i][j]*solutionVector[j];
            }
            System.out.println(sum-B[i]);
        } */


        double a = solutionVector[0];
        double b = solutionVector[1];
        double c = solutionVector[2];

        cx=a/-2;  // center's x-coordinate
        cy=b/-2;  // center's y-coordinate
        r= Math.sqrt(cx*cx + cy*cy -c); // radius

    }

    private void positEquationSystem(){
        matrix[0][0]= totalXX;
        matrix[0][1]= totalXY;
        matrix[0][2]= totalX;
        matrix[1][0]= totalXY;
        matrix[1][1]= totalYY;
        matrix[1][2]= totalY;
        matrix[2][0]= totalX;
        matrix[2][1]= totalY;
        matrix[2][2]= one;

        B[0]= rightRowOne;
        B[1]= rightRowTwo;
        B[2]= rightRowThree;

    }
    public void reset() {
        rightRowOne=0;
        rightRowTwo=0;
        rightRowThree=0;
        totalX=0;
        totalY=0;
        totalXX=0;
        totalXY=0;
        totalYY=0;
        one=0;
        counter=0;

    }
    public void add(double x, double y, double weight) {
        // one coeff is the sum of the x's
        totalX+=x*weight;
        totalY+=y*weight;
        totalXX+=x*x*weight;
        totalYY+=y*y*weight;
        totalXY+=x*y*weight;
        one+=weight;

        rightRowOne+= (-1*x*x*x-x*y*y)*weight;
        rightRowTwo+= (-1*x*x*y - y*y*y)*weight;
        rightRowThree+= (-1*x*x-1*y*y)*weight;

        counter++;

    }

}
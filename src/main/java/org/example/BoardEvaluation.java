package org.example;

public class BoardEvaluation {

    public static int boardEvaluation(long WP,long WN,long WB,long WR,long WQ,long WK,long BP,long BN,long BB,long BR,long BQ,long BK){
        int score = 0;
        score += evaluatePieces(WP, WN, WB, WR, WQ, WK, BP, BN, BB, BR, BQ, BK);
        return score;
    }

    public static int evaluatePieces(long WP,long WN,long WB,long WR,long WQ,long WK,long BP,long BN,long BB,long BR,long BQ,long BK){
        //BoardGeneration.drawArray(WP, WN, WB, WR, WQ, WK, BP, BN, BB, BR, BQ, BK);

        int score = 0 ;
        for (int i=0;i<64;i++) {
            if (((WP>>i)&1)==1) {score = UserInterface.WhiteToMove? score+1 : score-1;}
            if (((WN>>i)&1)==1) {score = UserInterface.WhiteToMove? score+3 : score-3;}
            if (((WB>>i)&1)==1) {score = UserInterface.WhiteToMove? score+3 : score-3;}
            if (((WR>>i)&1)==1) {score = UserInterface.WhiteToMove? score+5 : score-5;}
            if (((WQ>>i)&1)==1) {score = UserInterface.WhiteToMove? score+9 : score-9;}
            if (((WK>>i)&1)==1) {}
            if (((BP>>i)&1)==1) {score = UserInterface.WhiteToMove? score-1 : score+1;}
            if (((BN>>i)&1)==1) {score = UserInterface.WhiteToMove? score-3 : score+3;}
            if (((BB>>i)&1)==1) {score = UserInterface.WhiteToMove? score-3 : score+3;}
            if (((BR>>i)&1)==1) {score = UserInterface.WhiteToMove? score-5 : score+5;}
            if (((BQ>>i)&1)==1) {score = UserInterface.WhiteToMove? score-9 : score+9;}
            if (((BK>>i)&1)==1) {}
        }

        //System.out.println("score : " + score);

        return score;
    }


}

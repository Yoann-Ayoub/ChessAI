package org.example;

public class BoardEvaluation {

    static int pawnBoard[][]={
            { 0,  0,  0,  0,  0,  0,  0,  0},
            {50, 50, 50, 50, 50, 50, 50, 50},
            {10, 10, 20, 30, 30, 20, 10, 10},
            { 5,  5, 10, 25, 25, 10,  5,  5},
            { 0,  0,  0, 20, 20,  0,  0,  0},
            { 5, -5,-10,  0,  0,-10, -5,  5},
            { 5, 10, 10,-20,-20, 10, 10,  5},
            { 0,  0,  0,  0,  0,  0,  0,  0}};
    static int rookBoard[][]={
            { 0,  0,  0,  0,  0,  0,  0,  0},
            { 5, 10, 10, 10, 10, 10, 10,  5},
            {-5,  0,  0,  0,  0,  0,  0, -5},
            {-5,  0,  0,  0,  0,  0,  0, -5},
            {-5,  0,  0,  0,  0,  0,  0, -5},
            {-5,  0,  0,  0,  0,  0,  0, -5},
            {-5,  0,  0,  0,  0,  0,  0, -5},
            { 0,  0,  0,  5,  5,  0,  0,  0}};
    static int knightBoard[][]={
            {-50,-40,-30,-30,-30,-30,-40,-50},
            {-40,-20,  0,  0,  0,  0,-20,-40},
            {-30,  0, 10, 15, 15, 10,  0,-30},
            {-30,  5, 15, 20, 20, 15,  5,-30},
            {-30,  0, 15, 20, 20, 15,  0,-30},
            {-30,  5, 10, 15, 15, 10,  5,-30},
            {-40,-20,  0,  5,  5,  0,-20,-40},
            {-50,-40,-30,-30,-30,-30,-40,-50}};
    static int bishopBoard[][]={
            {-20,-10,-10,-10,-10,-10,-10,-20},
            {-10,  0,  0,  0,  0,  0,  0,-10},
            {-10,  0,  5, 10, 10,  5,  0,-10},
            {-10,  5,  5, 10, 10,  5,  5,-10},
            {-10,  0, 10, 10, 10, 10,  0,-10},
            {-10, 10, 10, 10, 10, 10, 10,-10},
            {-10,  5,  0,  0,  0,  0,  5,-10},
            {-20,-10,-10,-10,-10,-10,-10,-20}};
    static int queenBoard[][]={
            {-20,-10,-10, -5, -5,-10,-10,-20},
            {-10,  0,  0,  0,  0,  0,  0,-10},
            {-10,  0,  5,  5,  5,  5,  0,-10},
            { -5,  0,  5,  5,  5,  5,  0, -5},
            {  0,  0,  5,  5,  5,  5,  0, -5},
            {-10,  5,  5,  5,  5,  5,  0,-10},
            {-10,  0,  5,  0,  0,  0,  0,-10},
            {-20,-10,-10, -5, -5,-10,-10,-20}};
    static int kingMidBoard[][]={
            {-30,-40,-40,-50,-50,-40,-40,-30},
            {-30,-40,-40,-50,-50,-40,-40,-30},
            {-30,-40,-40,-50,-50,-40,-40,-30},
            {-30,-40,-40,-50,-50,-40,-40,-30},
            {-20,-30,-30,-40,-40,-30,-30,-20},
            {-10,-20,-20,-20,-20,-20,-20,-10},
            { 20, 20,  0,  0,  0,  0, 20, 20},
            { 20, 30, 10,  0,  0, 10, 30, 20}};
    static int kingEndBoard[][]={
            {-50,-40,-30,-20,-20,-30,-40,-50},
            {-30,-20,-10,  0,  0,-10,-20,-30},
            {-30,-10, 20, 30, 30, 20,-10,-30},
            {-30,-10, 30, 40, 40, 30,-10,-30},
            {-30,-10, 30, 40, 40, 30,-10,-30},
            {-30,-10, 20, 30, 30, 20,-10,-30},
            {-30,-30,  0,  0,  0,  0,-30,-30},
            {-50,-30,-30,-30,-30,-30,-30,-50}};


    public static int boardEvaluation(boolean whiteToMove,long WP,long WN,long WB,long WR,long WQ,long WK,long BP,long BN,long BB,long BR,long BQ,long BK){
        int score = 0;
        score += evaluatePieces(whiteToMove, WP, WN, WB, WR, WQ, WK, BP, BN, BB, BR, BQ, BK);
        score += evaluatePosition(whiteToMove, WP, WN, WB, WR, WQ, WK, BP, BN, BB, BR, BQ, BK);
        return score;
    }

    public static int evaluatePieces(boolean whiteToMove, long WP,long WN,long WB,long WR,long WQ,long WK,long BP,long BN,long BB,long BR,long BQ,long BK){
        //BoardGeneration.drawArray(WP, WN, WB, WR, WQ, WK, BP, BN, BB, BR, BQ, BK);

        int score = 0 ;
        for (int i=0;i<64;i++) {
            if (((WP>>i)&1)==1) {score = whiteToMove? score+100 : score-100;}
            if (((WN>>i)&1)==1) {score = whiteToMove? score+300 : score-300;}
            if (((WB>>i)&1)==1) {score = whiteToMove? score+300 : score-300;}
            if (((WR>>i)&1)==1) {score = whiteToMove? score+500 : score-500;}
            if (((WQ>>i)&1)==1) {score = whiteToMove? score+900 : score-900;}
            if (((WK>>i)&1)==1) {score = whiteToMove? score+10000 : score-10000;}
            if (((BP>>i)&1)==1) {score = whiteToMove? score-100 : score+100;}
            if (((BN>>i)&1)==1) {score = whiteToMove? score-300 : score+300;}
            if (((BB>>i)&1)==1) {score = whiteToMove? score-300 : score+300;}
            if (((BR>>i)&1)==1) {score = whiteToMove? score-500 : score+500;}
            if (((BQ>>i)&1)==1) {score = whiteToMove? score-900 : score+900;}
            if (((BK>>i)&1)==1) {score = whiteToMove? score-10000 : score+10000;}
        }

        //System.out.println("score : " + score);

        return score;
    }


    public static int evaluatePosition(boolean whiteToMove,long WP,long WN,long WB,long WR,long WQ,long WK,long BP,long BN,long BB,long BR,long BQ,long BK) {
        int score = 0;
        for (int i=0;i<64;i++){
            if (((WP>>i)&1)==1) {score = whiteToMove? score+pawnBoard[i/8][i%8] : score-pawnBoard[i/8][i%8];}
            if (((WN>>i)&1)==1) {score = whiteToMove? score+knightBoard[i/8][i%8] : score-knightBoard[i/8][i%8];}
            if (((WB>>i)&1)==1) {score = whiteToMove? score+bishopBoard[i/8][i%8] : score-bishopBoard[i/8][i%8];}
            if (((WR>>i)&1)==1) {score = whiteToMove? score+rookBoard[i/8][i%8] : score-rookBoard[i/8][i%8];}
            if (((WQ>>i)&1)==1) {score = whiteToMove? score+queenBoard[i/8][i%8] : score-queenBoard[i/8][i%8];}
            if (((BP>>i)&1)==1) {score = whiteToMove? score-pawnBoard[i/8][i%8] : score+pawnBoard[i/8][i%8];}
            if (((BN>>i)&1)==1) {score = whiteToMove? score-knightBoard[i/8][i%8] : score+knightBoard[i/8][i%8];}
            if (((BB>>i)&1)==1) {score = whiteToMove? score-bishopBoard[i/8][i%8] : score+bishopBoard[i/8][i%8];}
            if (((BR>>i)&1)==1) {score = whiteToMove? score-rookBoard[i/8][i%8] : score+rookBoard[i/8][i%8];}
            if (((BQ>>i)&1)==1) {score = whiteToMove? score-queenBoard[i/8][i%8] : score+queenBoard[i/8][i%8];}
        }
        return score;
    }


}

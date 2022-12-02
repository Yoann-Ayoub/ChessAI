package org.example;

public class BoardEvaluation {

    // evaluation from https://www.talkchess.com/forum3/viewtopic.php?f=2&t=68311&start=19
/*
    static int pawnBoard[][]={

            {0,   0,   0,   0,   0,   0,  0,   0},
            {98, 134,  61,  95,  68, 126, 34, -11},
            {-6,   7,  26,  31,  65,  56, 25, -20},
            {-14,  13,   6,  21,  23,  12, 17, -23},
            {-27,  -2,  -5,  12,  17,   6, 10, -25},
            {-26,  -4,  -4, -10,   3,   3, 33, -12},
            {-35,  -1, -20, -23, -15,  24, 38, -22},
            {0,   0,   0,   0,   0,   0,  0,   0}};

    static int rookBoard[][]={
            { 32,  42,  32,  51, 63,  9,  31,  43},
            { 27,  32,  58,  62, 80, 67,  26,  44},
            { -5,  19,  26,  36, 17, 45,  61,  16},
            { -24, -11,   7,  26, 24, 35,  -8, -20},
            { -36, -26, -12,  -1,  9, -7,   6, -23},
            { -45, -25, -16, -17,  3,  0,  -5, -33},
            { -44, -16, -20,  -9, -1, 11,  -6, -71},
            { -19, -13,   1,  17, 16,  7, -37, -26}};

    static int knightBoard[][]={
            {-167, -89, -34, -49,  61, -97, -15, -107},
            {-73, -41,  72,  36,  23,  62,   7,  -17},
            {-47,  60,  37,  65,  84, 129,  73,   44},
            {-9,  17,  19,  53,  37,  69,  18,   22},
            {-13,   4,  16,  13,  28,  19,  21,   -8},
            {-23,  -9,  12,  10,  19,  17,  25,  -16},
            {-29, -53, -12,  -3,  -1,  18, -14,  -19},
            {-105, -21, -58, -33, -17, -28, -19,  -23}};
    static int bishopBoard[][]={
            {-29,   4, -82, -37, -25, -42,   7,  -8},
            {-26,  16, -18, -13,  30,  59,  18, -47},
            {-16,  37,  43,  40,  35,  50,  37,  -2},
            {-4,   5,  19,  50,  37,  37,   7,  -2},
            {-6,  13,  13,  26,  34,  12,  10,   4},
            {0,  15,  15,  15,  14,  27,  18,  10},
            {4,  15,  16,   0,   7,  21,  33,   1},
            {-33,  -3, -14, -21, -13, -12, -39, -21}};
    static int queenBoard[][]={
            {-28,   0,  29,  12,  59,  44,  43,  45},
            {-24, -39,  -5,   1, -16,  57,  28,  54},
            {-13, -17,   7,   8,  29,  56,  47,  57},
            {-27, -27, -16, -16,  -1,  17,  -2,   1},
            {-9, -26,  -9, -10,  -2,  -4,   3,  -3},
            {-14,   2, -11,  -2,  -5,   2,  14,   5},
            {-35,  -8,  11,   2,   8,  15,  -3,   1},
            {-1, -18,  -9,  10, -15, -25, -31, -50}};

    static int kingBoard[][] = {
        {-65,  23,  16, -15, -56, -34,   2,  13},
        {29,  -1, -20,  -7,  -8,  -4, -38, -29},
        {-9,  24,   2, -16, -20,   6,  22, -22},
        {-17, -20, -12, -27, -30, -25, -14, -36},
        {-49,  -1, -27, -39, -46, -44, -33, -51},
        {-14, -14, -22, -46, -44, -30, -15, -27},
        {1,   7,  -8, -64, -43, -16,   9,   8},
        {-15,  36,  12, -54,   8, -28,  24,  14}};

*/

    // évaluation position de https://www.chessprogramming.org/Simplified_Evaluation_Function

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


/* // meilleure performance sans tableau pour le roi tant que l'on n'a pas un moyen d'évaluer milieu et fin de partie
    static int kingBoard[][]={ // idea for king mid board
            {-30,-40,-40,-50,-50,-40,-40,-30},
            {-30,-40,-40,-50,-50,-40,-40,-30},
            {-30,-40,-40,-50,-50,-40,-40,-30},
            {-30,-40,-40,-50,-50,-40,-40,-30},
            {-20,-30,-30,-40,-40,-30,-30,-20},
            {-10,-20,-20,-20,-20,-20,-20,-10},
            { 20, 20,  0,  0,  0,  0, 20, 20},
            { 20, 30, 10,  0,  0, 10, 30, 20}};
*/

    /*
    static int kingEndBoard[][]={
            {-50,-40,-30,-20,-20,-30,-40,-50},
            {-30,-20,-10,  0,  0,-10,-20,-30},
            {-30,-10, 20, 30, 30, 20,-10,-30},
            {-30,-10, 30, 40, 40, 30,-10,-30},
            {-30,-10, 30, 40, 40, 30,-10,-30},
            {-30,-10, 20, 30, 30, 20,-10,-30},
            {-30,-30,  0,  0,  0,  0,-30,-30},
            {-50,-30,-30,-30,-30,-30,-30,-50}};*/

    public static int boardEvaluation(int checkScore, boolean whiteToMove,long WP,long WN,long WB,long WR,long WQ,long WK,long BP,long BN,long BB,long BR,long BQ,long BK){
        int score = 0;
        score += evaluatePieces(whiteToMove, WP, WN, WB, WR, WQ, WK, BP, BN, BB, BR, BQ, BK);
        score += evaluatePosition(whiteToMove, WP, WN, WB, WR, WQ, WK, BP, BN, BB, BR, BQ, BK);
        score += checkScore;


        return score;
    }

    public static int evaluatePieces(boolean whiteToMove, long WP,long WN,long WB,long WR,long WQ,long WK,long BP,long BN,long BB,long BR,long BQ,long BK){
        //BoardGeneration.drawArray(WP, WN, WB, WR, WQ, WK, BP, BN, BB, BR, BQ, BK);

        int score = 0 ;
        int WBishopCount = 0;
        int BBishopCount = 0;
        for (int i=0;i<64;i++) {
            if (((WP>>i)&1)==1) {score = UserInterface.WhiteToMove? score+100 : score-100;}
            if (((WN>>i)&1)==1) {score = UserInterface.WhiteToMove? score+300 : score-300;}
            if (((WB>>i)&1)==1) {WBishopCount = WBishopCount+1;}
            if (((WR>>i)&1)==1) {score = UserInterface.WhiteToMove? score+500 : score-500;}
            if (((WQ>>i)&1)==1) {score = UserInterface.WhiteToMove? score+900 : score-900;}
            if (((WK>>i)&1)==1) {}
            if (((BP>>i)&1)==1) {score = UserInterface.WhiteToMove? score-100 : score+100;}
            if (((BN>>i)&1)==1) {score = UserInterface.WhiteToMove? score-300 : score+300;}
            if (((BB>>i)&1)==1) {BBishopCount = BBishopCount+1;}
            if (((BR>>i)&1)==1) {score = UserInterface.WhiteToMove? score-500 : score+500;}
            if (((BQ>>i)&1)==1) {score = UserInterface.WhiteToMove? score-900 : score+900;}
            if (((BK>>i)&1)==1) {}
        }

        if (WBishopCount == 2) {
            score = UserInterface.WhiteToMove? score+(350*WBishopCount) : score-(350*WBishopCount);
        } else {
            if (WBishopCount == 1) {score = UserInterface.WhiteToMove? score+250 : score-250;}
        }

        if (BBishopCount == 2) {
            score = UserInterface.WhiteToMove? score-(350*WBishopCount) : score+(350*WBishopCount);
        } else {
            if (BBishopCount == 1) {score = UserInterface.WhiteToMove? score-250 : score+250;}
        }

        return score;
    }


    public static int evaluatePosition(boolean whiteToMove, long WP,long WN,long WB,long WR,long WQ,long WK,long BP,long BN,long BB,long BR,long BQ,long BK) {
        int score = 0;
        for (int i=0;i<64;i++){
            if (((WP>>i)&1)==1) {score = UserInterface.WhiteToMove? score+pawnBoard[i/8][i%8] : score-pawnBoard[i/8][i%8];}
            if (((WN>>i)&1)==1) {score = UserInterface.WhiteToMove? score+knightBoard[i/8][i%8] : score-knightBoard[i/8][i%8];}
            if (((WB>>i)&1)==1) {score = UserInterface.WhiteToMove? score+bishopBoard[i/8][i%8] : score-bishopBoard[i/8][i%8];}
            if (((WR>>i)&1)==1) {score = UserInterface.WhiteToMove? score+rookBoard[i/8][i%8] : score-rookBoard[i/8][i%8];}
            if (((WQ>>i)&1)==1) {score = UserInterface.WhiteToMove? score+queenBoard[i/8][i%8] : score-queenBoard[i/8][i%8];}
            //if (((WK>>i)&1)==1) {score = UserInterface.WhiteToMove? score-kingBoard[i/8][i%8] : score+kingBoard[i/8][i%8];}
            if (((BP>>i)&1)==1) {score = UserInterface.WhiteToMove? score-pawnBoard[i/8][i%8] : score+pawnBoard[i/8][i%8];}
            if (((BN>>i)&1)==1) {score = UserInterface.WhiteToMove? score-knightBoard[i/8][i%8] : score+knightBoard[i/8][i%8];}
            if (((BB>>i)&1)==1) {score = UserInterface.WhiteToMove? score-bishopBoard[i/8][i%8] : score+bishopBoard[i/8][i%8];}
            if (((BR>>i)&1)==1) {score = UserInterface.WhiteToMove? score-rookBoard[i/8][i%8] : score+rookBoard[i/8][i%8];}
            if (((BQ>>i)&1)==1) {score = UserInterface.WhiteToMove? score-queenBoard[i/8][i%8] : score+queenBoard[i/8][i%8];}
            //if (((BK>>i)&1)==1) {score = UserInterface.WhiteToMove? score-kingBoard[i/8][i%8] : score+kingBoard[i/8][i%8];}
        }

        return score;
    }



}

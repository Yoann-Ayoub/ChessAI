package org.example;

public class SearchAlgorithm {


    public static String MinMaxAlgorithm(long WP,long WN,long WB,long WR,long WQ,long WK,long BP,long BN,long BB,long BR,long BQ,long BK){
        String moves;

        moves = UserInterface.WhiteToMove?
                Moves.possibleMovesW(UserInterface.WP,UserInterface.WN,UserInterface.WB,UserInterface.WR,UserInterface.WQ,UserInterface.WK,UserInterface.BP,UserInterface.BN,UserInterface.BB,UserInterface.BR,UserInterface.BQ,UserInterface.BK,UserInterface.EP,UserInterface.CWK,UserInterface.CWQ,UserInterface.CBK,UserInterface.CBQ)
                :Moves.possibleMovesB(UserInterface.WP,UserInterface.WN,UserInterface.WB,UserInterface.WR,UserInterface.WQ,UserInterface.WK,UserInterface.BP,UserInterface.BN,UserInterface.BB,UserInterface.BR,UserInterface.BQ,UserInterface.BK,UserInterface.EP,UserInterface.CWK,UserInterface.CWQ,UserInterface.CBK,UserInterface.CBQ);

        int bestScore = -1;
        String bestMove = "";
        int temporaryScore = -1;
        long WP2, WN2, WB2, WR2, WQ2, WK2, BP2, BN2, BB2, BR2, BQ2, BK2, EP2;

        System.out.print("MinMaxAlgo");
        //get evaluation for each son
        for(int i=0; i<moves.length(); i+=4){
            String move = moves.substring(i,i+4);
            UserInterface ui = new UserInterface();
            String algebraMove = UCI.moveToAlgebra(move);
            EP2=Moves.makeMoveEP(WP|BP,move);
            WR2=Moves.makeMoveCastle(WR, WK|BK, move, 'R');
            BR2=Moves.makeMoveCastle(BR, WK|BK, move, 'r');
            WP2=Moves.makeMove(WP, move, 'P');
            WN2=Moves.makeMove(WN, move, 'N');
            WB2=Moves.makeMove(WB, move, 'B');
            WR2=Moves.makeMove(WR, move, 'R');
            WQ2=Moves.makeMove(WQ, move, 'Q');
            WK2=Moves.makeMove(WK, move, 'K');
            BP2=Moves.makeMove(BP, move, 'p');
            BN2=Moves.makeMove(BN, move, 'n');
            BB2=Moves.makeMove(BB, move, 'b');
            BR2=Moves.makeMove(BR, move, 'r');
            BQ2=Moves.makeMove(BQ, move, 'q');
            BK2=Moves.makeMove(BK, move, 'k');

            temporaryScore = BoardEvaluation.boardEvaluation(WP2,WN2,WB2,WR2,WQ2,WK2,BP2,BN2,BB2,BR2,BQ2,BK2);
            if(temporaryScore>bestScore){
                bestScore = temporaryScore;
                bestMove = move;
            }

            System.out.println("Score : " + bestScore);
            System.out.println("Best Move : " + UCI.moveToAlgebra(bestMove));

        }

        return bestMove;
    }


}

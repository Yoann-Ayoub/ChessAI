package org.example;

import java.util.List;

public class SearchAlgorithm {

    static int maxDepth = 4;

    public static String MinMaxAlgorithm(int depth, long WP,long WN,long WB,long WR,long WQ,long WK,long BP,long BN,long BB,long BR,long BQ,long BK, boolean WhiteToMove){
        String moves;

        moves = WhiteToMove?
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



    public static void treeConstruction(int depth, Node root, long WP,long WN,long WB,long WR,long WQ,long WK,long BP,long BN,long BB,long BR,long BQ,long BK,long EP, boolean CWK, boolean CWQ, boolean CBK, boolean CBQ){

        int score;
        long WP2, WN2, WB2, WR2, WQ2, WK2, BP2, BN2, BB2, BR2, BQ2, BK2, EP2;
        boolean CWK2, CWQ2, CBK2, CBQ2;
        String moves;

        if(depth == 0){
            return;
        }

        moves = UserInterface.WhiteToMove?
                Moves.possibleMovesW(WP, WN, WB, WR, WQ, WK, BP, BN, BB, BR, BQ, BK, EP, CWK, CWQ, CBK, CBQ)
                :Moves.possibleMovesB(WP, WN, WB, WR, WQ, WK, BP, BN, BB, BR, BQ, BK, EP, CWK, CWQ, CBK, CBQ);

        System.out.print("tree construction");
        //get evaluation for each son
        for(int i=0; i<moves.length(); i+=4){

            String move = moves.substring(i,i+4);
            score = 0;

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
            EP2=Moves.makeMoveEP(WP|BP,move);
            WR2 = Moves.makeMoveCastle(WR2, WK | BK, move, 'R');
            BR2 = Moves.makeMoveCastle(BR2, WK | BK, move, 'r');

            CWK2 = CWK;
            CWQ2 = CWQ;
            CBK2 = CBK;
            CBQ2 = CBQ;

            // check if 'regular' move
            if (Character.isDigit(moves.charAt(i + 3))) {
                int start = (Character.getNumericValue(moves.charAt(i)) * 8) + (Character.getNumericValue(moves.charAt(i + 1)));
                if (((1L << start) & WK) != 0) { CWK2 = false; CWQ2 = false; }
                else if (((1L << start) & BK) != 0) { CBK2 = false; CBQ2 = false; }
                else if (((1L << start) & WR & (1L << 63)) != 0) { CWK2=false; }
                else if (((1L << start) & WR & (1L << 56)) != 0) { CWQ2=false; }
                else if (((1L << start) & BR & (1L << 7)) != 0) { CBK2=false; }
                else if (((1L << start) & BR & 1L) != 0) { CBQ2 = false; }
            }

            Node newNode = new Node(move,BoardEvaluation.boardEvaluation(WP2,WN2,WB2,WR2,WQ2,WK2,BP2,BN2,BB2,BR2,BQ2,BK2),root);
            root.addChild(newNode);

            treeConstruction(depth-1,newNode,WP2,WN2,WB2,WR2,WQ2,WK2,BP2,BN2,BB2,BR2,BQ2,BK2,EP2,CWK2,CWQ2,CBK2,CBQ2);


            //alpha beta

            //return move;
        }

        //return bestmove;
    }

    public static int MinMax(Node node, int depth, boolean isMaximizing){
        int value;
        if(depth==0 || node.getChildren() == null){
            return node.getScore();
        }

        List<Node> nodeList = node.getChildren();

        if(isMaximizing){
            value = -10000000;
            for (Node son:nodeList) {
                int tempValue = MinMax(son,depth-1,false);
                if(tempValue>value){
                    value = tempValue;
                    node.setSonChoosen(son);
                    //node.setScore(value);
                }
            }
        }

        else{
            value = 10000000;
            for (Node son:nodeList) {
                int tempValue = MinMax(son,depth-1,true);
                if(tempValue<value){
                    value = tempValue;
                    node.setSonChoosen(son);
                    //node.setScore(value);
                }
            }
        }

        return value;


    }


}

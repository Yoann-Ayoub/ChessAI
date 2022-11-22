package org.example;

import java.util.ArrayList;
import java.util.List;

public class SearchAlgorithm {

    static int maxDepth = 4;

    public static  String Search(long startTime) {

        int alpha = -1000000000;
        int beta = 1000000000;
        Node root = new Node("", 0, null);
        List<Node> nextNodeToExplore = new ArrayList<>();
        //nextNodeToExplore.add(root);
        int depth=2;
        /*try {
            nextNodeToExplore = SearchAlgorithm.treeConstruction(startTime,UserInterface.WhiteToMove, nextNodeToExplore,1, root, UserInterface.WP, UserInterface.WN, UserInterface.WB, UserInterface.WR, UserInterface.WQ, UserInterface.WK, UserInterface.BP, UserInterface.BN, UserInterface.BB, UserInterface.BR, UserInterface.BQ, UserInterface.BK, UserInterface.EP, UserInterface.CWK, UserInterface.CWQ, UserInterface.CBK, UserInterface.CBQ);
        } catch (Exception e) {

        }*/
       /* System.out.println("------------------START--------------------------");
        System.out.println("----------------------------------");
        System.out.println("Number of node to explore : " + nextNodeToExplore.size());
        System.out.println("----------------------------------");
        System.out.println("----------------------------------");*/

        while(System.currentTimeMillis() - startTime < 990) {

            List<Node> nextNodeToExploreTemp = new ArrayList<>();
            /*for (Node nodeToExplore: nextNodeToExplore) {
                if(nodeToExplore!=null){
                    try {
                        nextNodeToExploreTemp = SearchAlgorithm.treeConstruction(startTime,UserInterface.WhiteToMove, nextNodeToExploreTemp,1, nodeToExplore, UserInterface.WP, UserInterface.WN, UserInterface.WB, UserInterface.WR, UserInterface.WQ, UserInterface.WK, UserInterface.BP, UserInterface.BN, UserInterface.BB, UserInterface.BR, UserInterface.BQ, UserInterface.BK, UserInterface.EP, UserInterface.CWK, UserInterface.CWQ, UserInterface.CBK, UserInterface.CBQ);
                    } catch (Exception e) {
                        break;
                    }
                }
            }*/
            try {
                nextNodeToExploreTemp = SearchAlgorithm.treeConstruction(startTime,UserInterface.WhiteToMove, nextNodeToExploreTemp,depth +1, root, UserInterface.WP, UserInterface.WN, UserInterface.WB, UserInterface.WR, UserInterface.WQ, UserInterface.WK, UserInterface.BP, UserInterface.BN, UserInterface.BB, UserInterface.BR, UserInterface.BQ, UserInterface.BK, UserInterface.EP, UserInterface.CWK, UserInterface.CWQ, UserInterface.CBK, UserInterface.CBQ);
            } catch (Exception e) {
                System.out.println("TREE CONS EXCEPTION depth :" + depth);
                return root.getSonChoosen().getValue();
            }

            nextNodeToExplore = nextNodeToExploreTemp;
            /*System.out.println("------------------WHILE LOOP--------------------------");
            System.out.println("----------------------------------");
            System.out.println("Number of node to explore : " + nextNodeToExplore.size());
            System.out.println("----------------------------------");
            System.out.println("----------------------------------");*/

            try {
                SearchAlgorithm.AlphaBeta(root, alpha, beta, depth, true, startTime);
            } catch (Exception e) {
                System.out.println("AB EXCEPTION depth :" + depth);
                return root.getSonChoosen().getValue();
            }
            depth++;

        }
        System.out.println("depth :" + depth);
        return root.getSonChoosen().getValue();

    }

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

            temporaryScore = BoardEvaluation.boardEvaluation(WhiteToMove,WP2,WN2,WB2,WR2,WQ2,WK2,BP2,BN2,BB2,BR2,BQ2,BK2);
            if(temporaryScore>bestScore){
                bestScore = temporaryScore;
                bestMove = move;
            }

            System.out.println("Score : " + bestScore);
            System.out.println("Best Move : " + UCI.moveToAlgebra(bestMove));

        }

        return bestMove;
    }



    public static List<Node> treeConstruction(long startTime, boolean whiteToMove, List<Node> nodeList, int depth, Node root, long WP,long WN,long WB,long WR,long WQ,long WK,long BP,long BN,long BB,long BR,long BQ,long BK,long EP, boolean CWK, boolean CWQ, boolean CBK, boolean CBQ) throws Exception {

        int score;
        long WP2, WN2, WB2, WR2, WQ2, WK2, BP2, BN2, BB2, BR2, BQ2, BK2, EP2;
        boolean CWK2, CWQ2, CBK2, CBQ2;
        String moves;

        if(depth == 0){
            nodeList.add(root);
            return nodeList;
        }

        moves = whiteToMove?
                Moves.possibleMovesW(WP, WN, WB, WR, WQ, WK, BP, BN, BB, BR, BQ, BK, EP, CWK, CWQ, CBK, CBQ)
                :Moves.possibleMovesB(WP, WN, WB, WR, WQ, WK, BP, BN, BB, BR, BQ, BK, EP, CWK, CWQ, CBK, CBQ);

        //System.out.print("tree construction");
        //get evaluation for each son
        if(System.currentTimeMillis() - startTime >= 990) {
            throw new Exception();
        }
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

            Node newNode;
            if (((WK2 & Moves.unsafeForWhite(WP2, WN2, WB2, WR2, WQ2, WK2, BP2, BN2, BB2, BR2, BQ2, BK2)) == 0 && whiteToMove) ||
                    ((BK2 & Moves.unsafeForBlack(WP2, WN2, WB2, WR2, WQ2, WK2, BP2, BN2, BB2, BR2, BQ2, BK2)) == 0 && !whiteToMove)) {
                newNode = new Node(move,BoardEvaluation.boardEvaluation(whiteToMove,WP2,WN2,WB2,WR2,WQ2,WK2,BP2,BN2,BB2,BR2,BQ2,BK2),root);
                root.addChild(newNode);
                treeConstruction(startTime,!whiteToMove,nodeList,depth-1,newNode,WP2,WN2,WB2,WR2,WQ2,WK2,BP2,BN2,BB2,BR2,BQ2,BK2,EP2,CWK2,CWQ2,CBK2,CBQ2);
            }
        }

        return nodeList;
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
                    /*if(depth==UCI.depth){
                       node.setSonChoosen(son);
                    }*/
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
                    /*if(depth==UCI.depth){
                        node.setSonChoosen(son);
                    }*/
                    //node.setScore(value);
                }
            }
        }

        return value;


    }


    public static int AlphaBeta(Node node, int alpha, int beta, int depth, boolean isMaximizing, long startTime) throws Exception {
        int value;
        if(depth==0 || node.getChildren() == null){
            return node.getScore();
        }

        List<Node> nodeList = node.getChildren();

        if(isMaximizing){
            value = -10000000;
            for (Node son:nodeList) {
                if(System.currentTimeMillis() - startTime >= 990) {
                    throw new Exception();

                }
                int tempValue = AlphaBeta(son,alpha,beta ,depth-1,false, startTime);
                if(tempValue>value){
                    value = tempValue;
                    node.setSonChoosen(son);
                    /*if(depth==UCI.depth){
                       node.setSonChoosen(son);
                    }*/
                    //node.setScore(value);
                }

                if(value>=beta){
                    return value;
                }
                if(alpha<value){
                    alpha = value;
                }
            }
        }

        else{
            value = 10000000;
            for (Node son:nodeList) {
                if(System.currentTimeMillis() - startTime >= 990) {
                    throw new Exception();
                }
                int tempValue = AlphaBeta(son,alpha, beta , depth-1,true, startTime);
                if(tempValue<value){
                    value = tempValue;
                    node.setSonChoosen(son);
                    /*if(depth==UCI.depth){
                        node.setSonChoosen(son);
                    }*/
                    //node.setScore(value);
                }
                if(alpha>value){
                    return value;
                }
                if(value<beta){
                    beta = value;
                }
            }
        }

        return value;


    }


    public static int getFirstLegalMove(String moves, long WP, long WN, long WB, long WR, long WQ, long WK, long BP, long BN, long BB, long BR, long BQ, long BK, long EP, boolean CWK, boolean CWQ, boolean CBK, boolean CBQ, boolean WhiteToMove) {
        //TAKEN FROM GITLHUB STRESS BLABALA
        // for each move
        for (int i = 0; i < moves.length(); i += 4) {

            // make the move
            String substring = moves.substring(i, i + 4);
            long WPt = Moves.makeMove(WP, substring, 'P'), WNt = Moves.makeMove(WN, substring, 'N'),
                    WBt = Moves.makeMove(WB, substring, 'B'), WRt = Moves.makeMove(WR, substring, 'R'),
                    WQt = Moves.makeMove(WQ, substring, 'Q'), WKt = Moves.makeMove(WK, substring, 'K'),
                    BPt = Moves.makeMove(BP, substring, 'p'), BNt = Moves.makeMove(BN, substring, 'n'),
                    BBt = Moves.makeMove(BB, substring, 'b'), BRt = Moves.makeMove(BR, substring, 'r'),
                    BQt = Moves.makeMove(BQ, substring, 'q'), BKt = Moves.makeMove(BK, substring, 'k');
            WRt = Moves.makeMoveCastle(WRt, WK | BK, substring, 'R');
            BRt = Moves.makeMoveCastle(BRt, WK | BK, substring, 'r');

            if (((WKt & Moves.unsafeForWhite(WPt, WNt, WBt, WRt, WQt, WKt, BPt, BNt, BBt, BRt, BQt, BKt)) == 0 && WhiteToMove) ||
                    ((BKt & Moves.unsafeForBlack(WPt, WNt, WBt, WRt, WQt, WKt, BPt, BNt, BBt, BRt, BQt, BKt)) == 0 && !WhiteToMove)) {
                return i;
            }
        }

        // no legal move
        return -1;
    }


}

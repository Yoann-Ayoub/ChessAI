package org.example;

public class UserInterface  {
    static long WP=0L,WN=0L,WB=0L,WR=0L,WQ=0L,WK=0L,BP=0L,BN=0L,BB=0L,BR=0L,BQ=0L,BK=0L,EP=0L;
    static boolean CWK=true,CWQ=true,CBK=true,CBQ=true,WhiteToMove=true; //true=castle is possible

    public static void main(String[] args) {
        UCI.uciCommunication();
    }
}
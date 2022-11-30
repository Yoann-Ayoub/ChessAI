package org.example;

import java.util.*;
/*
 * PIECE=WHITE/black
 * pawn=P/p
 * kinght (horse)=N/n
 * bishop=B/b
 * rook (castle)=R/r
 * Queen=Q/q
 * King=K/k
 */
public class BoardGeneration {
    public static void importFEN(String fenString) {
        //not chess960 compatible
        UserInterface.WP=0; UserInterface.WN=0; UserInterface.WB=0;
        UserInterface.WR=0; UserInterface.WQ=0; UserInterface.WK=0;
        UserInterface.BP=0; UserInterface.BN=0; UserInterface.BB=0;
        UserInterface.BR=0; UserInterface.BQ=0; UserInterface.BK=0;
        UserInterface.CWK=false; UserInterface.CWQ=false;
        UserInterface.CBK=false; UserInterface.CBQ=false;
        int charIndex = 0;
        int boardIndex = 0;
        while (fenString.charAt(charIndex) != ' ')
        {
            switch (fenString.charAt(charIndex++))
            {
                case 'P': UserInterface.WP |= (1L << boardIndex++);
                    break;
                case 'p': UserInterface.BP |= (1L << boardIndex++);
                    break;
                case 'N': UserInterface.WN |= (1L << boardIndex++);
                    break;
                case 'n': UserInterface.BN |= (1L << boardIndex++);
                    break;
                case 'B': UserInterface.WB |= (1L << boardIndex++);
                    break;
                case 'b': UserInterface.BB |= (1L << boardIndex++);
                    break;
                case 'R': UserInterface.WR |= (1L << boardIndex++);
                    break;
                case 'r': UserInterface.BR |= (1L << boardIndex++);
                    break;
                case 'Q': UserInterface.WQ |= (1L << boardIndex++);
                    break;
                case 'q': UserInterface.BQ |= (1L << boardIndex++);
                    break;
                case 'K': UserInterface.WK |= (1L << boardIndex++);
                    break;
                case 'k': UserInterface.BK |= (1L << boardIndex++);
                    break;
                case '/':
                    break;
                case '1': boardIndex++;
                    break;
                case '2': boardIndex += 2;
                    break;
                case '3': boardIndex += 3;
                    break;
                case '4': boardIndex += 4;
                    break;
                case '5': boardIndex += 5;
                    break;
                case '6': boardIndex += 6;
                    break;
                case '7': boardIndex += 7;
                    break;
                case '8': boardIndex += 8;
                    break;
                default:
                    break;
            }
        }
        UserInterface.WhiteToMove = (fenString.charAt(++charIndex) == 'w');
        charIndex += 2;
        while (fenString.charAt(charIndex) != ' ')
        {
            switch (fenString.charAt(charIndex++))
            {
                case '-':
                    break;
                case 'K': UserInterface.CWK = true;
                    break;
                case 'Q': UserInterface.CWQ = true;
                    break;
                case 'k': UserInterface.CBK = true;
                    break;
                case 'q': UserInterface.CBQ = true;
                    break;
                default:
                    break;
            }
        }
        if (fenString.charAt(++charIndex) != '-')
        {
            UserInterface.EP = Moves.FileMasks8[fenString.charAt(charIndex++) - 'a'];
        }
        //the rest of the fenString is not yet utilized
    }
}
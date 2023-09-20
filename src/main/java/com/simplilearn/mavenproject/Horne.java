package com.simplilearn.mavenproject;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Horne {
	
	public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";
    
    public static void main(String[] args) {
        Scanner benutzerEingabeScanner = new Scanner(System.in);
        System.out.println(ANSI_GREEN +"Möchten Sie die Koeffizienten des Polynoms manuell eingeben? (j/n)"+ ANSI_RESET);
        String benutzerAuswahl = benutzerEingabeScanner.next();
        double[] polynomKoeffizienten;
        int anzahlNullstellen;
        double[] listeDerNullstellen;

        if ("j".equalsIgnoreCase(benutzerAuswahl)) {
            System.out.println(ANSI_PURPLE+"Wie viele Koeffizienten hat das Polynom?"+ANSI_PURPLE);
            int anzahlKoeffizienten = benutzerEingabeScanner.nextInt();
            polynomKoeffizienten = new double[anzahlKoeffizienten];
            System.out.println(ANSI_GREEN+"Geben Sie die Koeffizienten ein, beginnend mit dem höchsten Grad:"+ANSI_GREEN);
            for (int i = 0; i < anzahlKoeffizienten; i++) {
                polynomKoeffizienten[i] = benutzerEingabeScanner.nextDouble();
            }

            System.out.println(ANSI_CYAN + "Wie viele Nullstellen?" + ANSI_RESET);
            anzahlNullstellen = benutzerEingabeScanner.nextInt();
            listeDerNullstellen = new double[anzahlNullstellen];
            System.out.println("Geben Sie die " + anzahlNullstellen + " Nullstellen ein:");
            for (int i = 0; i < anzahlNullstellen; i++) {
                listeDerNullstellen[i] = benutzerEingabeScanner.nextDouble();
            }
        } else {
            Random zufallsGenerator = new Random();
            int anzahlKoeffizienten = zufallsGenerator.nextInt(9) + 2;  // Zufällige Anzahl zwischen 2 und 10
            polynomKoeffizienten = new double[anzahlKoeffizienten];
            for (int i = 0; i < anzahlKoeffizienten; i++) {
                polynomKoeffizienten[i] = zufallsGenerator.nextDouble() * 200 - 100;
            }

            anzahlNullstellen = anzahlKoeffizienten - 1;  
            listeDerNullstellen = new double[anzahlNullstellen];
            for (int i = 0; i < anzahlNullstellen; i++) {
                listeDerNullstellen[i] = zufallsGenerator.nextDouble() * 20 - 10;
            }

            System.out.println(ANSI_CYAN + "Generiertes Polynom hat " + anzahlKoeffizienten + " Koeffizienten und " + anzahlNullstellen + " Nullstellen." + ANSI_RESET);
        }
        System.out.println(ANSI_BLUE + "Ursprüngliches Polynom: " + urspruenglichesPolynomAlsString(polynomKoeffizienten) + ANSI_RESET);
        System.out.println(ANSI_GREEN + "Zu entwickelndes Polynom: " + zuEntwickelndesPolynomAlsString(listeDerNullstellen) + ANSI_RESET);
       

        
        double[][] hornerTabelle = new double[anzahlNullstellen * 2 + 1][polynomKoeffizienten.length];
        System.arraycopy(polynomKoeffizienten, 0, hornerTabelle[0], 0, polynomKoeffizienten.length);

        int aktuelleZeileIndex = 1;
        List<Double> listeDerAlphas = new ArrayList<>();

        for (double nullstelle : listeDerNullstellen) {
            int aktuelleSpalteIndex = 0;
            hornerTabelle[aktuelleZeileIndex][aktuelleSpalteIndex] = hornerTabelle[aktuelleZeileIndex - 1][aktuelleSpalteIndex];

            // Ausgabe der aktuellen Zeile vor der Berechnung
            System.out.println(ANSI_PURPLE + "Vor der Berechnung:" + ANSI_RESET);
            for (double wert : hornerTabelle[aktuelleZeileIndex]) {
                System.out.print(wert + "\t");
            }
            System.out.println("\n---------------------");

            for (aktuelleSpalteIndex = 1; aktuelleSpalteIndex < polynomKoeffizienten.length - (aktuelleZeileIndex - 1) / 2; aktuelleSpalteIndex++) {
                hornerTabelle[aktuelleZeileIndex][aktuelleSpalteIndex] = hornerTabelle[aktuelleZeileIndex - 1][aktuelleSpalteIndex] + nullstelle * hornerTabelle[aktuelleZeileIndex][aktuelleSpalteIndex - 1];
                System.out.println("Berechnung: " + hornerTabelle[aktuelleZeileIndex - 1][aktuelleSpalteIndex] + " + " + nullstelle + " * " + hornerTabelle[aktuelleZeileIndex][aktuelleSpalteIndex - 1] + " = " + hornerTabelle[aktuelleZeileIndex][aktuelleSpalteIndex]);
            }

            // Ausgabe der aktuellen Zeile nach der Berechnung
            System.out.println(ANSI_YELLOW + "Nach der Berechnung:" + ANSI_RESET);
            for (double wert : hornerTabelle[aktuelleZeileIndex]) {
                System.out.print(wert + "\t");
            }
            System.out.println("\n=====================");

            aktuelleZeileIndex++;
            System.arraycopy(hornerTabelle[aktuelleZeileIndex - 1], 0, hornerTabelle[aktuelleZeileIndex], 0, aktuelleSpalteIndex);
            aktuelleZeileIndex++;
        }
        // Sammeln der Alpha-Werte
        for (int i = 0; i < aktuelleZeileIndex - 1; i++) {
            if (i % 2 != 0) {
                listeDerAlphas.add(hornerTabelle[i][polynomKoeffizienten.length - i / 2 - 1]);
            }
        }
        listeDerAlphas.add(hornerTabelle[aktuelleZeileIndex - 1][0]);
        
     // Zeige die Horner-Tabelle an
        System.out.println(ANSI_BLUE + "Horner-Tabelle:" + ANSI_RESET);
        for (int i = 0; i < aktuelleZeileIndex; i++) {
            for (int j = 0; j < polynomKoeffizienten.length - i / 2; j++) {
                // Prüfen, ob der aktuelle Wert ein Alpha-Wert ist
                if ((i % 2 != 0 && j == polynomKoeffizienten.length - i / 2 - 1) || (i == aktuelleZeileIndex - 1 && j == 0)) {
                    System.out.print(ANSI_RED);  
                }
                System.out.print(hornerTabelle[i][j] + "\t");
                if ((i % 2 != 0 && j == polynomKoeffizienten.length - i / 2 - 1) || (i == aktuelleZeileIndex - 1 && j == 0)) {
                    System.out.print(ANSI_RESET);  
                }
            }
            System.out.println();
        }
        
        System.out.println(ANSI_RED + "Alphas: " + listeDerAlphas + ANSI_RESET);

     // Erstellen des endgültigen Polynoms
        System.out.print(ANSI_YELLOW + "Endgültiges Polynom: P(z) = ");

        // Alpha an der Position 0 ist speziell, also fangen wir damit an
        System.out.print(ANSI_RED + listeDerAlphas.get(0) + ANSI_YELLOW);

        for (int i = 1; i < listeDerAlphas.size() && i <= listeDerNullstellen.length; i++) {
            System.out.print(" + ");
            
         
            System.out.print(ANSI_RED + listeDerAlphas.get(i) + ANSI_YELLOW + "*");
            
            // Rest des Polynoms
            for (int j = 0; j < i && j < listeDerNullstellen.length; j++) {
                System.out.print("(z - " + listeDerNullstellen[j] + ")");
            }
        }

        System.out.println(ANSI_RESET);

        benutzerEingabeScanner.close();
    }
    public static String urspruenglichesPolynomAlsString(double[] koeffizienten) {
        StringBuilder sb = new StringBuilder();
        sb.append("P(z) = ");
        for (int i = 0; i < koeffizienten.length; i++) {
            if (i > 0) sb.append(" + ");
            sb.append(koeffizienten[i]);
            if (i < koeffizienten.length - 1) {
                sb.append("z^").append(koeffizienten.length - i - 1);
            }
        }
        return sb.toString();
    }
    public static String zuEntwickelndesPolynomAlsString(double[] nullstellen) {
        StringBuilder sb = new StringBuilder();
        sb.append("P(z) = alpha0");
        for (int i = 1; i <= nullstellen.length; i++) {
            sb.append(" + alpha").append(i);
            for (int j = 0; j < i; j++) {
                sb.append("*(z - ").append(nullstellen[j]).append(")");
            }
        }
        return sb.toString();
    }
}
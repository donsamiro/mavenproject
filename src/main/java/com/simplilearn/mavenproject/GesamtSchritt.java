package com.simplilearn.mavenproject;

import java.util.Random;
import Jama.Matrix;
import java.util.Scanner;


public class GesamtSchritt {
	
	public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
	
	public Matrix generiereMatrix(int m) {
	    Random zufall = new Random();
	    Matrix matrix;  
	    int versuche = 0;  // Zählen der Versuche

	    while (true) {
	        versuche++;
	        double[][] matrixDaten = new double[m][m];
	        for (int i = 0; i < m; i++) {
	            for (int j = 0; j < m; j++) {
	                if (i == j) {
	                    matrixDaten[i][j] = zufall.nextInt(1000) + 1; // Werte zwischen 1 und 1000 für Diagonalelemente
	                    if (zufall.nextBoolean()) {
	                        matrixDaten[i][j] *= -1;  // Möglichkeit, das Diagonalelement negativ zu machen
	                    }
	                } else {
	                    matrixDaten[i][j] = zufall.nextInt(201) - 100; // Werte zwischen -100 und 100 für nicht-Diagonalelemente
	                }
	            }
	        }

	        matrix = new Matrix(matrixDaten);  // Verwendung der Daten zur Initialisierung der Matrix

	        if (!istMatrixSingular(matrix) && istDiagonalDominant(matrix)) {
	            System.out.println(ANSI_RED+ "Matrix gefunden nach "+ANSI_RED + versuche + ANSI_RED+" Versuchen."+ANSI_RED);  // Feedback über die Anzahl der Versuche
	            return matrix;
	        }
	    }
	}

    public Matrix generiereVektor(int m) {
        Random zufall = new Random();
        double[][] matrixDaten = new double[m][1];
        for (int i = 0; i < m; i++) {
            matrixDaten[i][0] = zufall.nextInt(100) + 1;
        }
        return new Matrix(matrixDaten);
    }

    public Matrix generiereNullVektor(int m) {
        double[][] matrixDaten = new double[m][1];
        for (int i = 0; i < m; i++) {
            matrixDaten[i][0] = 0;
        }
        return new Matrix(matrixDaten);
    }
    
    public Matrix macheDiagonalDominantDurchZeilenTausch(Matrix matrix) {
        int groesse = matrix.getRowDimension();
        double[][] matrixDaten = matrix.getArrayCopy(); // Kopie der Matrix-Daten

        for (int i = 0; i < groesse; i++) {
            double diagonalesElement = Math.abs(matrixDaten[i][i]);
            double zeilensumme = 0;
            for (int j = 0; j < groesse; j++) {
                if (i != j) {
                    zeilensumme += Math.abs(matrixDaten[i][j]);
                }
            }

            if (diagonalesElement < zeilensumme) {
                // Zeilenvertauschung versuchen
                for (int k = i + 1; k < groesse; k++) {
                    double neuesDiagonalesElement = Math.abs(matrixDaten[k][i]);
                    double neueZeilensumme = 0;
                    for (int l = 0; l < groesse; l++) {
                        if (i != l) {
                            neueZeilensumme += Math.abs(matrixDaten[k][l]);
                        }
                    }

                    if (neuesDiagonalesElement > neueZeilensumme) {
                        // Zeilen i und k vertauschen
                        double[] tempZeile = matrixDaten[i];
                        matrixDaten[i] = matrixDaten[k];
                        matrixDaten[k] = tempZeile;
                        break;
                    }
                }
            }
        }
        return new Matrix(matrixDaten);
    }
    

    public boolean istDiagonalDominant(Matrix matrix) {
        int groesse = matrix.getRowDimension();
        for (int i = 0; i < groesse; i++) {
            double summe = 0;
            for (int j = 0; j < groesse; j++) {
                if (i != j) {
                    summe += Math.abs(matrix.get(i, j));
                }
            }
            if (Math.abs(matrix.get(i, i)) < summe) {
                return false;
            }
        }
        return true;
    }

    public boolean istMatrixSingular(Matrix matrix) {
        double determinante = matrix.det();
        double epsilon = 1e-10;
        return Math.abs(determinante) < epsilon;
    }

    public void druckeMatrix(Matrix matrix) {
        for (int i = 0; i < matrix.getRowDimension(); i++) {
            for (int j = 0; j < matrix.getColumnDimension(); j++) {
                System.out.print(matrix.get(i, j) + " ");
            }
            System.out.println();
        }
    }

    public void druckeVektor(Matrix vektor) {
        for (int i = 0; i < vektor.getRowDimension(); i++) {
            System.out.println(vektor.get(i, 0));
        }
    }

    public Matrix holeDiagonalmatrix(Matrix matrix) {
        int groesse = Math.min(matrix.getRowDimension(), matrix.getColumnDimension());
        double[][] diagonaleDaten = new double[groesse][groesse];
        for (int i = 0; i < groesse; i++) {
            diagonaleDaten[i][i] = matrix.get(i, i);
        }
        return new Matrix(diagonaleDaten);
    }

    public Matrix holeElementeRechtsDerDiagonalen(Matrix matrix) {
        int zeilen = matrix.getRowDimension();
        int spalten = matrix.getColumnDimension();
        double[][] resultDaten = new double[zeilen][spalten];
        for (int i = 0; i < zeilen; i++) {
            for (int j = i + 1; j < spalten; j++) {
                resultDaten[i][j] = matrix.get(i, j);
            }
        }
        return new Matrix(resultDaten);
    }

    public Matrix holeElementeLinksDerDiagonalen(Matrix matrix) {
        int zeilen = matrix.getRowDimension();
        int spalten = matrix.getColumnDimension();
        double[][] resultDaten = new double[zeilen][spalten];
        for (int i = 0; i < zeilen; i++) {
            for (int j = 0; j < i; j++) {
                resultDaten[i][j] = matrix.get(i, j);
            }
        }
        return new Matrix(resultDaten);
    }
    

    public Matrix berechneT(Matrix ad, Matrix al, Matrix ar) {
        System.out.println(ANSI_GREEN+"Berechnung von T:"+ANSI_GREEN);
        Matrix t = ad.inverse().times(al.plus(ar)).times(-1);
        System.out.println(ANSI_GREEN+"T Matrix:"+ANSI_GREEN);
        druckeMatrix(t);
        return t;
    }

    public Matrix iteriere(Matrix ad, Matrix b, Matrix T, Matrix x, int n) {
        System.out.println(ANSI_GREEN+"Iteration beginnt:"+ANSI_GREEN);

        Matrix adInvers = ad.inverse();
        Matrix ergebnis = adInvers.times(b);

        Matrix vorherigesX = x.copy();
        int anzahlGleicherErgebnisse = 0;

        for (int i = 0; i < n; i++) {
            System.out.println(ANSI_PURPLE+"Iteration " + (i + 1) + " beginnt:"+ANSI_PURPLE);

            Matrix Tx = T.times(vorherigesX);
            Matrix neuesX = ergebnis.plus(Tx);
            
            System.out.println(ANSI_CYAN+"Lösung für Iteration "+ANSI_CYAN + (i + 1) + ANSI_CYAN+":"+ANSI_CYAN);
            druckeVektor(neuesX);

            // Prüfen, ob die Differenz exakt 0 ist
            Matrix delta = neuesX.minus(vorherigesX);
            boolean sindGleich = true;
            for (int j = 0; j < delta.getRowDimension(); j++) {
                if (delta.get(j, 0) != 0) {
                    sindGleich = false;
                    break;
                }
            }

            if (sindGleich) {
                anzahlGleicherErgebnisse++;
                if (anzahlGleicherErgebnisse >= 2) {
                    break;
                }
            } else {
                anzahlGleicherErgebnisse = 0; // Zurücksetzen des Zählers
            }

            vorherigesX = neuesX.copy();
        }

        return vorherigesX;
    }

    private void druckeGleichungssystem(Matrix A, Matrix b) {
        int zeilen = A.getRowDimension();
        int spalten = A.getColumnDimension();

       
        System.out.println(ANSI_BLUE+"Gleichungssystem (x und b sind Vektoren):"+ANSI_BLUE);
        System.out.println(ANSI_BLUE+"A * x = b"+ANSI_BLUE);
        System.out.println();

        for (int i = 0; i < zeilen; i++) {
            StringBuilder zeilenString = new StringBuilder();

            for (int j = 0; j < spalten; j++) {
                zeilenString.append(A.get(i, j)).append(" * x").append(j + 1);
                if (j < spalten - 1) {
                    zeilenString.append(" + ");
                }
            }

            zeilenString.append(" = ").append(b.get(i, 0));
            System.out.println(zeilenString);
        }
    }
    
    public static void main(String[] args) {
        GesamtSchritt g = new GesamtSchritt();
        Scanner scanner = new Scanner(System.in);

        System.out.println(ANSI_RED+"Möchten Sie eine Matrix und einen Vektor manuell eingeben? (ja/nein)"+ANSI_RED);
        String nutzerAntwort = scanner.nextLine();

        Matrix matrix;
        Matrix vektor;

        if ("ja".equalsIgnoreCase(nutzerAntwort)) {
            System.out.println(ANSI_GREEN+"Bitte geben Sie die Größe der Matrix ein (ein Integer):"+ANSI_GREEN);
            int groesse = scanner.nextInt();
            double[][] manuelleMatrix = new double[groesse][groesse];
            double[][] manuellerVektor = new double[groesse][1];

            // Eingabe für Matrix
            System.out.println(ANSI_RED+"Bitte geben Sie die Elemente der Matrix ein:"+ANSI_RED);
            for (int i = 0; i < groesse; i++) {
                for (int j = 0; j < groesse; j++) {
                    manuelleMatrix[i][j] = scanner.nextDouble();
                }
            }
           
            scanner.nextLine(); // Konsument der nächsten Zeile
            matrix = new Matrix(manuelleMatrix); // Angenommen, Ihr Matrix-Objekt kann so initialisiert werden

         
            System.out.println("Die Matrix A lautet:");
            g.druckeMatrix(matrix); 
            
            if (g.istMatrixSingular(matrix)) {
                System.out.println(ANSI_RED + "Die Matrix A ist singulär. Gesamtschrittverfahren kann nicht angewendet werden." + ANSI_RESET);
                return;
            }

            if (!g.istDiagonalDominant(matrix)) {
                System.out.println(ANSI_RED + "Die Matrix A ist nicht diagonal dominant. Versuche Zeilen zu vertauschen..." + ANSI_RESET);
                matrix = g.macheDiagonalDominantDurchZeilenTausch(matrix);
                if (!g.istDiagonalDominant(matrix)) {
                    System.out.println(ANSI_RED + "Die Matrix A konnte nicht diagonal dominant gemacht werden." + ANSI_RESET);
                    return;
                } else {
                    System.out.println(ANSI_GREEN + "Die Matrix A ist nun diagonal dominant." + ANSI_RESET);
                }
            }
           


            // Eingabe für Vektor
            System.out.println(ANSI_GREEN+"Bitte geben Sie die Elemente des Lösungsvektors ein:"+ANSI_GREEN);
            for (int i = 0; i < groesse; i++) {
                manuellerVektor[i][0] = scanner.nextDouble();
            }

           
            vektor = new Matrix(manuellerVektor);

        } else {
            System.out.println(ANSI_RED+"Bitte geben Sie die Größe der zu generierenden Matrix ein (ein Integer):"+ANSI_RED);
            int groesse = scanner.nextInt();
            matrix = g.generiereMatrix(groesse);
            vektor = g.generiereVektor(groesse);
        }

       
        g.druckeGleichungssystem(matrix, vektor);

        System.out.println(ANSI_YELLOW+"Matrix A:"+ANSI_YELLOW);
        g.druckeMatrix(matrix);
        System.out.println(ANSI_GREEN+"Vektor b:"+ANSI_GREEN);
        g.druckeVektor(vektor);

        Matrix ad = g.holeDiagonalmatrix(matrix);
        Matrix al = g.holeElementeLinksDerDiagonalen(matrix);
        Matrix ar = g.holeElementeRechtsDerDiagonalen(matrix);
        
        System.out.println(ANSI_BLUE+"Matrix ad:"+ANSI_BLUE);
        g.druckeMatrix(ad);
        System.out.println(ANSI_BLUE+"Matrix al:"+ANSI_BLUE);
        g.druckeMatrix(al);
        System.out.println(ANSI_BLUE+"Matrix ar:"+ANSI_BLUE);
        g.druckeMatrix(ar);

        Matrix T = g.berechneT(ad, al, ar);

        System.out.println(ANSI_RED+"Bitte geben Sie die Anzahl der Iterationen ein (ein Integer):"+ANSI_RED);
        int iterationen = scanner.nextInt();
        
        

        Matrix startVektor = g.generiereNullVektor(ad.getRowDimension());

        Matrix loesung = g.iteriere(ad, vektor, T, startVektor, iterationen);

        System.out.println(ANSI_RED+"Berechnete Lösung:"+ANSI_RED);
        g.druckeVektor(loesung);
    } }
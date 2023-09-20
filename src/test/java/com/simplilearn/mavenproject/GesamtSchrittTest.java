package com.simplilearn.mavenproject;
import org.junit.Before;
import org.junit.Test;
import Jama.Matrix;
import static org.junit.Assert.*;

import java.io.PrintStream;
import java.io.ByteArrayOutputStream;



public class GesamtSchrittTest {
	
	private GesamtSchritt gesamtSchritt;

    @Before
    public void setUp() {
        gesamtSchritt = new GesamtSchritt();
    }
    
    @Test
    public void testGeneriereMatrix() {
        int matrixSize = 3; // Ändere die Matrixgröße nach Bedarf

        Matrix matrix = gesamtSchritt.generiereMatrix(matrixSize);

        assertNotNull(matrix);
        assertEquals(matrixSize, matrix.getRowDimension());
        assertEquals(matrixSize, matrix.getColumnDimension());

        // Überprüfung der Diagonalendominanz
        assertTrue(gesamtSchritt.istDiagonalDominant(matrix));

        // Überprüfung der Singulärität
        assertFalse(gesamtSchritt.istMatrixSingular(matrix));
    }
    
    @Test
    public void testGeneriereVektor() {
        Matrix vektor = gesamtSchritt.generiereVektor(3); // Passen Sie die Größe nach Bedarf an
        assertNotNull(vektor);
        assertEquals(3, vektor.getRowDimension()); // Überprüfen Sie, ob die Vektorgröße korrekt ist
    }

    @Test
    public void testGeneriereNullVektor() {
        Matrix nullVektor = gesamtSchritt.generiereNullVektor(3); // Passen Sie die Größe nach Bedarf an
        assertNotNull(nullVektor);
        assertEquals(3, nullVektor.getRowDimension()); // Überprüfen Sie, ob die Vektorgröße korrekt ist
        for (int i = 0; i < nullVektor.getRowDimension(); i++) {
            assertEquals(0, nullVektor.get(i, 0), 0.001); // Überprüfen Sie, ob alle Elemente 0 sind (mit einer kleinen Toleranz)
        }
    }

    @Test
    public void testMacheDiagonalDominantDurchZeilenTausch() {
        double[][] testData = {{4, -1, 1}, {-1, 3, 2}, {2, 1, 5}};
        Matrix matrix = new Matrix(testData);
        Matrix diagonalDominantMatrix = gesamtSchritt.macheDiagonalDominantDurchZeilenTausch(matrix);
        assertTrue(gesamtSchritt.istDiagonalDominant(diagonalDominantMatrix)); // Überprüfen Sie, ob die resultierende Matrix diagonal dominant ist
    }
    
    @Test
    public void testIstDiagonalDominant() {
        GesamtSchritt gesamtSchritt = new GesamtSchritt();

        // Erstellen Sie eine diagonal dominante Matrix
        double[][] diagonalDominanteDaten = {{4, -1, 0}, {-1, 4, -1}, {0, -1, 4}};
        Matrix diagonalDominanteMatrix = new Matrix(diagonalDominanteDaten);

        // Überprüfen Sie, ob die Methode istDiagonalDominant die Matrix als diagonal dominant erkennt
        assertTrue(gesamtSchritt.istDiagonalDominant(diagonalDominanteMatrix));

        // Erstellen Sie eine nicht diagonal dominante Matrix
        double[][] nichtDiagonalDominanteDaten = {{4, 1, 0}, {2, 4, -1}, {0, 3, 4}};
        Matrix nichtDiagonalDominanteMatrix = new Matrix(nichtDiagonalDominanteDaten);

        // Überprüfen Sie, ob die Methode istDiagonalDominant die Matrix als nicht diagonal dominant erkennt
        assertFalse(gesamtSchritt.istDiagonalDominant(nichtDiagonalDominanteMatrix));
    }

    @Test
    public void testIstMatrixSingular() {
        GesamtSchritt gesamtSchritt = new GesamtSchritt();

        // Erstellen Sie eine reguläre (nicht singuläre) Matrix
        double[][] nichtSingulaereDaten = {{1, 2}, {3, 4}};
        Matrix nichtSingulaereMatrix = new Matrix(nichtSingulaereDaten);

        // Überprüfen Sie, ob die Methode istMatrixSingular die Matrix als nicht singulär erkennt
        assertFalse(gesamtSchritt.istMatrixSingular(nichtSingulaereMatrix));

        // Erstellen Sie eine singuläre Matrix
        double[][] singulaereDaten = {{1, 2}, {2, 4}};
        Matrix singulaereMatrix = new Matrix(singulaereDaten);

        // Überprüfen Sie, ob die Methode istMatrixSingular die Matrix als singulär erkennt
        assertTrue(gesamtSchritt.istMatrixSingular(singulaereMatrix));
    }

    @Test
    public void testDruckeMatrix() {
        GesamtSchritt gesamtSchritt = new GesamtSchritt();
        // Erstellen Sie eine Testmatrix
        double[][] matrixDaten = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        Matrix testMatrix = new Matrix(matrixDaten);

        // Umleitung der Standardausgabe zum Erfassen der gedruckten Ausgabe
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(baos));

        // Rufen Sie die Methode druckeMatrix auf
        gesamtSchritt.druckeMatrix(testMatrix);

        // Stellen Sie die Standardausgabe wieder her
        System.setOut(originalOut);

        // Erwartete Ausgabe
        String expectedOutput = "1.0 2.0 3.0 \n4.0 5.0 6.0 \n7.0 8.0 9.0 \n";
        assertEquals(expectedOutput, baos.toString());
    }

    @Test
    public void testDruckeVektor() {
        GesamtSchritt gesamtSchritt = new GesamtSchritt();
        // Erstellen Sie einen Testvektor
        double[][] vektorDaten = {{1}, {2}, {3}};
        Matrix testVektor = new Matrix(vektorDaten);

        // Umleitung der Standardausgabe zum Erfassen der gedruckten Ausgabe
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(baos));

        // Rufen Sie die Methode druckeVektor auf
        gesamtSchritt.druckeVektor(testVektor);

        // Stellen Sie die Standardausgabe wieder her
        System.setOut(originalOut);

        // Erwartete Ausgabe
        String expectedOutput = "1.0\n2.0\n3.0\n";
        assertEquals(expectedOutput, baos.toString());
    }
    
}






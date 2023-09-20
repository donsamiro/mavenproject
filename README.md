# Mathematic Java Projekt

## Beschreibung

Dieses Java-Projekt konzentriert sich auf mathematische Berechnungen. Es enthält verschiedene Java-Klassen, die unterschiedliche Arten von mathematischen Operationen durchführen.

## Struktur des Repositorys

- `src/`: Enthält den Java-Quellcode.
  - `GesamtSchritt.java`: Diese Klasse implementiert das Gesamtschrittverfahren zur Lösung von linearen Gleichungssystemen. Sie verwendet die Jama-Bibliothek für Matrixoperationen und bietet verschiedene Methoden zur Generierung von Matrizen, Vektoren und zur Durchführung von Iterationen.

## Abhängigkeiten

- Java Development Kit (JDK)
- Jama-1.0.3 Bibliothek

## Installation und Ausführung

1. Klonen Sie das Repository.
   ```
   git clone https://github.com/XXXX/mathematic.git
   ```
2. Öffnen Sie das Projekt in Ihrer bevorzugten IDE.
3. Bauen und starten Sie das Projekt.

## GesamtSchritt.java

### Methoden

- `generiereMatrix(int m)`: Generiert eine quadratische Matrix der Größe `m x m`.
- `generiereVektor(int m)`: Generiert einen Vektor der Größe `m`.
- `generiereNullVektor(int m)`: Generiert einen Nullvektor der Größe `m`.
- `istDiagonalDominant(Matrix matrix)`: Überprüft, ob die Matrix diagonal dominant ist.
- `istMatrixSingular(Matrix matrix)`: Überprüft, ob die Matrix singulär ist.
- `iteriere(Matrix ad, Matrix b, Matrix T, Matrix x, int n)`: Führt die Iteration durch, um das lineare Gleichungssystem zu lösen.

### Verwendung

Die Hauptmethode `main` bietet eine Benutzeroberfläche, um entweder eine Matrix und einen Vektor manuell einzugeben oder sie automatisch generieren zu lassen. Anschließend wird das Gesamtschrittverfahren angewendet, um das lineare Gleichungssystem zu lösen.

Natürlich, hier ist eine README-Datei, die den Java-Code für die Klasse `Gradi` beschreibt:

---

# Gradi Java Klasse

## Beschreibung

Die Java-Klasse `Gradi` ist für die Lösung von Gleichungssystemen mit dem Gradientenverfahren konzipiert. Sie bietet die Möglichkeit, Gleichungssysteme entweder manuell einzugeben oder automatisch zu generieren. Die Klasse enthält verschiedene Methoden zur Überprüfung der Lösbarkeit des Systems und zur Durchführung des Gradientenverfahrens.

## Methoden

### `Gradi(boolean automatisch)`

Der Konstruktor der Klasse. Wenn `automatisch` auf `true` gesetzt ist, wird ein zufälliges Gleichungssystem generiert. Andernfalls wird der Benutzer zur manuellen Eingabe aufgefordert.

### `generiereZufaelligeGleichungen()`

Generiert zufällige Koeffizienten und Konstanten für das Gleichungssystem.

### `ueberpruefeUnendlichVieleLoesungen()`

Überprüft, ob das Gleichungssystem unendlich viele Lösungen hat.

### `ueberpruefeKeineLoesung()`

Überprüft, ob das Gleichungssystem keine Lösung hat.

### `manuelleEingabe()`

Ermöglicht die manuelle Eingabe der Koeffizienten, Konstanten und Exponenten für das Gleichungssystem.

### `zeigeGleichungssystem()`

Zeigt das aktuelle Gleichungssystem an.

### `gradientenVerfahren()`

Führt das Gradientenverfahren zur Lösung des Gleichungssystems durch.

## Verwendung

Die Hauptmethode `main` bietet eine Benutzeroberfläche, um entweder ein Gleichungssystem manuell einzugeben oder es automatisch generieren zu lassen. Anschließend wird das Gradientenverfahren angewendet, um das Gleichungssystem zu lösen.

## Beispiel

```java
public static void main(String[] args) {
    try (Scanner scanner = new Scanner(System.in)) {
        System.out.println(ANSI_BLUE+"Möchten Sie das Gleichungssystem automatisch generieren? (ja/nein)"+ANSI_BLUE);
        String antwort = scanner.nextLine();
        boolean automatisch = "ja".equalsIgnoreCase(antwort);
        new Gradi(automatisch);
    }
}
```

---

Sie können diese README-Datei an den bestehenden Inhalt Ihrer aktuellen README-Datei anhängen.
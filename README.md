# Budget Tracker

Der Budget Tracker ist ein kleines Java-Projekt, das ich selbst entwickelt habe, um meine Kenntnisse in Java, SQL und Datenbankanbindung praktisch anzuwenden.

Das Programm läuft aktuell vollständig über die Konsole. Man kann Einnahmen und Ausgaben hinzufügen, vorhandene Transaktionen ansehen, bearbeiten oder löschen. Die Daten werden dabei in einer lokalen SQLite-Datenbank gespeichert.

## Was kann das Programm?

Aktuell sind folgende Funktionen eingebaut:

* Einnahmen hinzufügen
* Ausgaben hinzufügen
* Alle gespeicherten Transaktionen anzeigen
* Eine bestehende Transaktion bearbeiten
* Einzelne Transaktionen löschen
* Alle Transaktionen löschen
* Gesamte Einnahmen und Ausgaben berechnen
* Aktuellen Kontostand anzeigen
* Monatsübersicht anzeigen

## Wie ist eine Transaktion aufgebaut?

Jede gespeicherte Transaktion besteht aus fünf Werten:

### ID

Jede Transaktion bekommt eine eigene ID.

Beispiel:

```text
ID: 4
```

Über diese ID kann eine bestimmte Transaktion später wiedergefunden, bearbeitet oder gelöscht werden.

### Type

`type` gibt an, um welche Art von Transaktion es sich handelt.

Aktuell gibt es:

```text
EINNAHME
```

und

```text
AUSGABE
```

Eine Einnahme wäre zum Beispiel ein Gehalt oder Geld aus einem Nebenjob.

Eine Ausgabe könnte beispielsweise Tanken, Essen oder ein Einkauf sein.

### Amount

`amount` ist der Geldbetrag der jeweiligen Transaktion.

Zum Beispiel:

```text
500
```

bei einer Einnahme von 500 €.

Oder:

```text
45
```

bei einer Ausgabe von 45 €.

### Description

`description` ist eine kurze Beschreibung der Transaktion.

Dadurch weiß man später noch, wofür Geld ausgegeben wurde oder woher eine Einnahme gekommen ist.

Beispiele für Einnahmen:

```text
Gehalt
Nebenjob
Verkauf
```

Beispiele für Ausgaben:

```text
Tanken
Essen
Einkaufen
Handyvertrag
```

### Date

`date` speichert das Datum der Transaktion.

Zum Beispiel:

```text
02.10.2026
```

So kann später nachvollzogen werden, wann eine Einnahme oder Ausgabe stattgefunden hat.

## Beispiel

Eine Ausgabe könnte beispielsweise so gespeichert sein:

```text
ID: 3 | Typ: AUSGABE | Betrag: 45.0 € | Beschreibung: Tanken | Datum: 02.10.2026
```

Eine Einnahme könnte so aussehen:

```text
ID: 4 | Typ: EINNAHME | Betrag: 600.0 € | Beschreibung: Nebenjob | Datum: 01.10.2026
```

## Kontostand

Der Kontostand wird aus allen Einnahmen und Ausgaben berechnet:

```text
Kontostand = Einnahmen - Ausgaben
```

Beispiel:

```text
Einnahmen: 1500 €
Ausgaben:   500 €

Kontostand: 1000 €
```

## Hauptmenü

Über das Hauptmenü kann zwischen den verschiedenen Funktionen gewechselt werden:

```text
1. Transaktion hinzufügen
2. Transaktionen anzeigen
3. Transaktion bearbeiten
4. Transaktion löschen
5. Monatsübersicht
6. Beenden
```

## Datenbank

Für die Speicherung der Daten benutze ich SQLite.

Die Datenbank wird lokal als:

```text
databaseTracker.db
```

gespeichert.

Beim Start überprüft das Programm, ob die benötigte Tabelle bereits existiert. Falls nicht, wird sie automatisch erstellt.

Die Tabelle enthält:

```text
id
type
amount
description
date
```

Die Kommunikation zwischen Java und der Datenbank läuft über JDBC.

## Projektstruktur

```text
BudgetTracker
│
├── src
│   └── main
│       └── java
│           ├── Main.java
│           ├── Homepage.java
│           ├── Database.java
│           ├── Transaction.java
│           ├── AddTransaction.java
│           ├── UpdateTransaction.java
│           └── DeleteTransaction.java
│
├── pom.xml
├── .gitignore
└── README.md
```

## Aufbau des Codes

### Main

Startet das Programm.

### Homepage

Enthält das Hauptmenü und verbindet die verschiedenen Bereiche des Programms miteinander.

Hier werden außerdem der aktuelle Kontostand sowie Einnahmen und Ausgaben angezeigt.

### Database

Die `Database`-Klasse kümmert sich um die Kommunikation mit SQLite.

Dazu gehören unter anderem:

* Tabelle erstellen
* Transaktionen speichern
* Transaktionen auslesen
* Transaktionen bearbeiten
* Transaktionen löschen
* Einnahmen und Ausgaben zusammenrechnen

### Transaction

Die Klasse `Transaction` stellt eine einzelne Transaktion dar.

Sie enthält:

```text
id
type
amount
description
date
```

### AddTransaction

Kümmert sich um das Hinzufügen neuer Einnahmen und Ausgaben.

### UpdateTransaction

Kümmert sich darum, bereits vorhandene Transaktionen zu bearbeiten.

### DeleteTransaction

Kümmert sich um das Löschen einzelner oder aller Transaktionen.

## Verwendete Technologien

* Java
* SQLite
* JDBC
* Maven
* Git
* GitHub

## Warum habe ich das Projekt gemacht?

Mit diesem Projekt wollte ich vor allem lernen, wie eine Java-Anwendung mit einer echten Datenbank zusammenarbeitet.

Dabei habe ich unter anderem praktisch mit folgenden Themen gearbeitet:

* CRUD-Operationen
* Objektorientierte Programmierung
* SQL
* JDBC
* Prepared Statements
* Datenbankverbindungen
* Fehlerbehandlung
* Benutzereingaben
* Aufteilung eines Programms auf mehrere Klassen

Das Projekt ist bewusst als Konsolenanwendung aufgebaut. Dadurch liegt der Fokus aktuell hauptsächlich auf der Programmlogik und der Datenbank.

## Urheberrecht / Hinweis

Die Idee, Struktur und konkrete Umsetzung dieser Anwendung sowie der von mir geschriebene Quellcode wurden von mir selbst entwickelt und implementiert.

MIT License

Verwendete externe Technologien und Bibliotheken wie Java, SQLite, JDBC und Maven gehören selbstverständlich ihren jeweiligen Rechteinhabern und unterliegen deren jeweiligen Lizenzen.

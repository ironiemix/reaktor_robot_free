Ein kleines Roboter-Szenario inspiriert von Karel The Robot und verändert von Thomas Kempe & David Tepaße
(Informatik 1 - Softwareentwicklung mit Greenfoot und BlueJ, Schöningh-Verlag)

Das ReaktorRobot-Szenario basiert auf dem Roboter-Szenarion vom Schöningh-Verlag.

Wir haben die Zusage bekommen dieses Szenario weiter zu verwenden, „wenn das veränderte Szenario dieses Bandes ausschließlich für interne Zwecke eingesetzt wird [...]. Abgedeckt wäre davon z.B. auch das Einstellen in ein internes Schulnetzwerk oder der Download von einem geschützten Server.“ (Hanno Vienken, Mitarbeiter beim Schöningh-Verlag am 23.03.2016)
Dafür danken wir dem Schöningh-Verlag und weisen alle Nutzer auf die Einhaltung der Einschränkungen bei der Weitergabe des Szenarios hin.


In diesem Szenario können verschiedene Roboter (Unterklassen von Roboter) erzeugt werden.
Mit Hilfe einiger Sensoren können diese Roboter auch komplexe Aufgaben erledigen.

Dieses "Schöningh-Szenario" wurde von uns angepasst, damit mehrere Aufgabenstellungen möglich sind.
Folgende Objekte mit ihren Eigenschaften und Fähigkeiten existieren darin:
1) Roboter tragen zwei Informationen mit sich:
   1. die Anzahl der Schrauben, die sie mit sich herumtragen (0-99)
   2. wie viel Energie sie noch haben (0-100) - ist der Balken komplett grün, so ist die Batterie voll.
2) Schrauben können aufgenommen werden (solange der Vorrat nicht voll ist - 99) und abgelegt werden,
   falls nicht schon ene Schraube auf dem Feld liegt und der Vorrat nicht leer (> 0) ist.
3) Battrien können aufgenommen werden und erhöhen die Energie um 25 Punkte (100 Energiepunkte
   werden nicht überschritten)
4) Ölflecken verringern die Energie um 50 Punkte, schlechtestenfalls auf 0, wenn man darüber läuft.
5) Ladestationen laden den Roboter sofort auf 100 Punkte beim darüber laufen.
6) Schraubenkisten können nicht überlaufen werden. Ein Roboter kann Schrauben aus einer Kiste nehmen,
   falls er direkt vor der Kiste steht, sie einen Vorrat (>0) enthält und der Roboter noch Platz
   in seinem Vorrat (max. 99) hat. Ein Roboter kann auch aus seinem Vorrat Schrauben in eine Kiste
   legen.
7) Eine Wand kann nicht überlaufen werden.
8) Es gibt weitere Gegenstände und Wände
9) Über Leveltests kommt man jeweils in ein weiteres Level
10) ...

Dieses Szenario wurde für eine Lehrerfortbildung entwickelt.

Karlsruhe, den 14.09.2016
Thomas Schaller und Dirk Zechnall
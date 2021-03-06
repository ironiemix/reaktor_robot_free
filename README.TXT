Ein kleines Roboter-Szenario inspiriert von Karel The Robot und ver�ndert von Thomas Kempe & David Tepa�e
(Informatik 1 - Softwareentwicklung mit Greenfoot und BlueJ, Sch�ningh-Verlag)

Das ReaktorRobot-Szenario basiert auf dem Roboter-Szenario vom Sch�ningh-Verlag.
Alle Grafiken stamen aus freien Quellen, insbesondere http://www.openclipart.com

In diesem Szenario k�nnen verschiedene Roboter (Unterklassen von Roboter) erzeugt werden.
Mit Hilfe einiger Sensoren k�nnen diese Roboter auch komplexe Aufgaben erledigen.

Dieses "Sch�ningh-Szenario" wurde von uns angepasst, damit mehrere Aufgabenstellungen m�glich sind.
Folgende Objekte mit ihren Eigenschaften und F�higkeiten existieren darin:
1) Roboter tragen zwei Informationen mit sich:
   1. die Anzahl der Schrauben, die sie mit sich herumtragen (0-99)
   2. wie viel Energie sie noch haben (0-100) - ist der Balken komplett gr�n, so ist die Batterie voll.
2) Schrauben k�nnen aufgenommen werden (solange der Vorrat nicht voll ist - 99) und abgelegt werden,
   falls nicht schon ene Schraube auf dem Feld liegt und der Vorrat nicht leer (> 0) ist.
3) Battrien k�nnen aufgenommen werden und erh�hen die Energie um 25 Punkte (100 Energiepunkte
   werden nicht �berschritten)
4) �lflecken verringern die Energie um 50 Punkte, schlechtestenfalls auf 0, wenn man dar�ber l�uft.
5) Ladestationen laden den Roboter sofort auf 100 Punkte beim dar�ber laufen.
6) Schraubenkisten k�nnen nicht �berlaufen werden. Ein Roboter kann Schrauben aus einer Kiste nehmen,
   falls er direkt vor der Kiste steht, sie einen Vorrat (>0) enth�lt und der Roboter noch Platz
   in seinem Vorrat (max. 99) hat. Ein Roboter kann auch aus seinem Vorrat Schrauben in eine Kiste
   legen.
7) Eine Wand kann nicht �berlaufen werden.
8) Es gibt weitere Gegenst�nde und W�nde
9) �ber Leveltests kommt man jeweils in ein weiteres Level
10) ...

Dieses Szenario wurde von Thomas Schaller und Dirk Zechnall f�r eine Lehrerfortbildung entwickelt.

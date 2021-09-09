# E-Games

Beschreibung:
Website zur Ausstellung, zum Verkauf und zum Austausch von Actionfiguren und Videospielen für Verkäufer und Kunden in der Region São Paulo, Brasilien. Ein Administratorkonto kann User (Verkäufer) registrieren und freischalten, diese können sich dann über ein Login-Interface anmelden. Dann laden sie ihre Verkaufsobjekte dort selbständig hoch. Über ein Formular geben sie Informationen wie den Preis, Produktbezeichnung und -beschreibung ein und können Bilder der Spiele bzw. Actionfiguren hochladen. Externe Nutzer (potenzielle Kunden) können dann auf die Informationen aus den bereits in die Datenbank eingegebenen Sammlungen zugreifen.

Verwendete Technologien:
Für ein übersichtliches Frontend habe ich das Framework Bootstrap verwendet. Die Passwörter der User wurden verschlüsselt mit BCryptPasswordEncoder in der Datenbank gespeichert. Bilder wurden aus Platzgründen mit der Imgscalr Java-Bibliothek in MongoDB verkleinert. Als nächster Schritt wird ein Chat-Interface mit Hilfe von Javascript zur Kommunikation zwischen Verkäufern und Käufern umgesetzt.


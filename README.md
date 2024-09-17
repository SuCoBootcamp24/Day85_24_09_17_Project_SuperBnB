# Buchungssystems SuperBnB für Ferienwohnungen mit Spring Boot

Projektbeschreibung:
In den nächsten Tagen widmen wir uns einer Anwendung für ein Appartment-Buchungssystem.
Dieses System ermöglicht Benutzern, Appartments zu suchen, zu buchen und zu verwalten. Es wird eine Spring Boot API verwendet, um mit einer relationalen Datenbank (MySQL oder PostgreSQL) zu kommunizieren, die als persistente Datenquelle dient. Zusätzlich wird ein Frontend entwickelt, das diese API konsumiert und eine benutzerfreundliche Oberfläche bereitstellt. Docker wird verwendet, um das System zu containerisieren, und Umgebungsvariablen werden über eine .env-Datei gesteuert.
Das Projekt vermittelt die grundlegenden Konzepte von Spring Boot, Datenbankintegration, Sicherheitsmechanismen mit Spring Security und die Arbeit mit Docker-Containern.
Schritt-für-Schritt-Aufgaben:

1. Projektsetup:
   Erstelle ein Spring Boot-Projekt mit den notwendigen Abhängigkeiten (Spring Web, Spring Data JPA, Spring Security, PostgreSQL (wegen Render).
   Erstellt eine Datenbank auf Render (optional)
   Erstelle die Grundstruktur für die REST-Controller, Services und Repositories.

2. Docker-Containerisierung
   Containerisiere die Spring Boot-Anwendung, das Frontend und die Datenbank mit Docker.
   Verwende Docker Compose, um alle Services (Backend, Frontend, Datenbank) als zusammenhängende Container zu starten.

3. Umgebungsvariablen
   Lege eine .envDatei an, um Umgebungsvariablen für folgende Konfigurationswerte bereitzustellen:
   Datenbank-URL
   Datenbank-Benutzername und Passwort
   Spring-Profil (z.B. dev, prod)
   Port-Konfigurationen
   Verwende in deinem Spring Boot-Projekt die Umgebungsvariablen zur Konfiguration der Datenbank und anderer Parameter (denkt dran, dass die .env Datei nicht in die Versionsverwaltung gelangen darf)
   Agenda für die nächsten Tage → Projektanforderungen :

0. Gedanken machen:
   Entwerfe ein Datenbankmodell für Ferienwohnungen, Buchungen und Benutzer. Es könnte in etwa so aussehen:
   Ferienwohnungen (properties): ID, Adresse, Stadt, Preis pro Nacht, Verfügbarkeit
   Buchungen (bookings): ID, Ferienwohnungs-ID, Benutzer-ID, Buchungsdatum, Check-in-Datum, Check-out-Datum
   Benutzer (users): ID, Name, E-Mail, Passwort (verschlüsselt), Rolle (ADMIN oder USER)

1. Backend (Spring Boot)
   Entwickele ein RESTful-Backend in Spring Boot. Es sollte folgende Funktionen bereitstellen:
   Wohnungsverwaltung:
   GET /api/properties: Liste aller verfügbaren Ferienwohnungen anzeigen
   POST /api/properties: Eine neue Ferienwohnung hinzufügen (nur für Administratoren)
   PUT /api/properties/{id}: Eine bestehende Ferienwohnung aktualisieren (nur für Administratoren)
   DELETE /api/properties/{id}: Eine Ferienwohnung löschen (nur für Administratoren)
   Buchungsverwaltung:
   GET /api/bookings: Liste aller Buchungen anzeigen (nur für Administratoren)
   POST /api/bookings: Eine Ferienwohnung buchen
   DELETE /api/bookings/{id}: Eine Buchung stornieren
   Benutzerverwaltung (für Administratoren):
   GET /api/users: Liste aller Benutzer anzeigen (nur für Administratoren)
   POST /api/users: Einen neuen Benutzer anlegen (nur für Administratoren)
   DELETE /api/users/{id}: Einen Benutzer löschen (nur für Administratoren)

2. Frontend (HTML/CSS/JavaScript)
   Entwickele ein einfaches und benutzerfreundliches Frontend mit HTML, CSS und JavaScript, das die folgenden Funktionen bietet:
   Darstellung der verfügbaren Ferienwohnungen (Daten kommen von der API)
   eine Suchfunktion, um Ferienwohnungen nach Ort, Preis oder Verfügbarkeit zu filtern
   Formulare für Buchungen, die Erstellung neuer Ferienwohnungen (nur für Administratoren) und deren Verwaltung (nur für Administratoren)
   Anmelde- und Registrierungsformulare

3. Datenbankintegration (MySQL oder PostgreSQL)
   Setze eine relationale Datenbank auf (MySQL oder PostgreSQL), um die Daten zu speichern.
   Ausblick auf die nächsten Tage → Spring Security
   Implementiere grundlegende Benutzerauthentifizierung und -autorisierung mit Spring Security:
   Benutzer müssen sich anmelden, um Ferienwohnungen buchen zu können.
   Administratoren (Rolle: ADMIN) können Wohnungen hinzufügen, löschen und die Benutzerverwaltung durchführen.
   Normale Benutzer (Rolle: USER) können Ferienwohnungen buchen, aber keine verwalten.
   Im Laufe der Woche kommen weitere Features zur unserer Anwendung hinzu.
   

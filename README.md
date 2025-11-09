# CityHub – Backend API  
**Platforma ogłoszeń lokalnych: sprzedaż przedmiotów i rezerwacja usług**

![Java](https://img.shields.io/badge/Java-21-blue)  
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.7-brightgreen)  
![MongoDB](https://img.shields.io/badge/MongoDB-7.0-green)  
![JWT](https://img.shields.io/badge/JWT-Auth-yellow)  
![Lombok](https://img.shields.io/badge/Lombok-1.18.34-orange)

---

## Spis treści
- [Funkcjonalności](#funkcjonalności)
- [Aktorzy (UML)](#aktorzy-uml)
- [Technologie](#technologie)
- [Struktura projektu](#struktura-projektu)
- [Uruchomienie](#uruchomienie)
- [API – Endpointy](#api-endpointy)
- [Testy w Postmanie](#testy-w-postmanie)
- [Admin Panel](#admin-panel)
- [Bezpieczeństwo](#bezpieczeństwo)
- [Rozwój](#rozwój)

---

## Funkcjonalności

| Funkcja | Status |
|-------|--------|
| Rejestracja / logowanie (JWT) | Done |
| Ogłoszenia: **przedmioty** (`ItemAd`) | Done |
| Ogłoszenia: **usługi** (`JobAd`) | Done |
| Rezerwacja usług (co 30 min) | Done |
| Filtrowanie i wyszukiwanie | Done |
| Zarządzanie kontem | Done |
| Panel admina (statystyki, usuwanie) | Done |
| Autoryzacja: Guest / User / Admin | Done |

---

## Aktorzy (UML)

```mermaid
classDiagram
    class Guest {
        + Przeglądanie ogłoszeń
        + Wyszukiwanie
        + Podgląd szczegółów
    }
    class User {
        + Rejestracja
        + Logowanie
        + Zarządzanie kontem
        + Dodaj/edytuj/usun (swoje)
        + Rezerwacja usługi
        + Zakup przedmiotu
    }
    class Admin {
        + Zarządzanie użytkownikami
        + Usuwanie dowolnych ogłoszeń
        + Statystyki systemu
    }

    Guest <|-- User
    User <|-- Admin

# HealthCare+
> API REST de gestion médicale développée avec Spring Boot

## Description
HealthCare+ est une application backend permettant de gérer les patients, les médecins, les rendez-vous et les dossiers médicaux d'un système de santé, tout en assurant la sécurité des données sensibles.

## Problématique
La gestion manuelle ou non sécurisée des dossiers médicaux et des plannings de soins présente des risques majeurs en termes de fuite de données confidentielles et de désorganisation des flux de patients. Ce projet répond au besoin de centraliser, d'automatiser et de sécuriser les accès aux informations de santé grâce à une architecture backend robuste et cloisonnée par rôles.

## Fonctionnalités
| Module          | Opérations |
|-----------------|-----------|
| Patient         | Ajouter, Modifier, Supprimer, Lister, Consulter |
| Médecin         | Ajouter, Modifier, Supprimer, Lister |
| Rendez-vous     | Créer, Modifier, Annuler, Lister, Rechercher par patient/médecin |
| Dossier Médical | Créer, Ajouter diagnostic, Ajouter observations, Consulter |

* **Sécurité Avancée (v2)** : Authentification via **JWT** (JSON Web Token) avec Spring Security 6 et gestion des rôles (RBAC).
* **Pagination et Tri** : Prise en charge de la pagination et du tri dynamique sur les entités pour garantir des performances optimales.
* **Gestion des Erreurs & Validation** : Centralisation des exceptions (`GlobalExceptionHandler`) et validation stricte des données entrantes (`Jakarta Validation`).

## Technologies utilisées
- **Java 17**
- **Spring Boot 3**
- **Spring Security 6** (JWT)
- **Spring Data JPA / Hibernate**
- **Flyway** (migrations BDD)
- **MySQL**
- **MapStruct** (DTO Mapper)
- **Swagger / Postman** (documentation & tests)
- **Maven**

## Installation et Guide d'utilisation
1. **Cloner le projet :**
   ```bash
   git clone [https://github.com/votre-nom-d-utilisateur/healthcare-backend.git](https://github.com/votre-nom-d-utilisateur/healthcare-backend.git)
   cd healthcare-backend
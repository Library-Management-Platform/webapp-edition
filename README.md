# 📚 Plateforme de Gestion de Bibliothèque

## 📖 Aperçu du Projet

Un système complet de gestion de bibliothèque construit avec **Spring Boot**, conçu pour moderniser les flux de travail de réservation et de prêt de ressources pour un réseau de bibliothèques intelligentes. Cette plateforme permet la recherche, la réservation et l'emprunt en temps réel de livres, de documents numériques et de ressources multimédias, tout en favorisant l'engagement communautaire et l'amélioration de la collection basée sur les données.

## 🎯 Fonctionnalités Clés

### 👥 Gestion des Utilisateurs et Authentification
*   Enregistrement sécurisé avec vérification par e-mail.
*   Contrôle d'accès basé sur les rôles (Client, Bibliothécaire, Administrateur).
*   Authentification basée sur JWT avec Spring Security.
*   Gestion de profil et suivi de l'historique des prêts.

### 📚 Gestion des Ressources
*   Catalogue interactif avec recherche et filtrage avancés.
*   Classification des ressources multi-catégories (Littérature, Science, Multimédia).
*   Téléchargement de ressources avec images de couverture stockées dans S3.
*   Suivi de la disponibilité en temps réel.

### 🔄 Automatisation du Flux de Prêt
*   Gestion complète du cycle de vie du prêt :
    1.  Réservé → 2. Emprunté → 3. Actif → 4. Rendu → 5. Fermé
*   Calculs automatisés des dates d'échéance et suivi des retards.
*   Système de commentaires et d'évaluation du client après la clôture du prêt.

### 📊 Analyse et Rapports
*   Tableaux de bord spécifiques aux rôles avec des visualisations Chart.js.
*   Statistiques de prêt par catégorie, bibliothèque et période.
*   Calcul du taux de rotation et analyse des tendances.
*   Fonctionnalité d'exportation CSV pour les rapports d'administration.

### 🔔 Notifications et Communication
*   Notifications par e-mail automatisées via Spring Mail.
*   Notifications en temps réel basées sur WebSocket.
*   Alertes pour la disponibilité, les dates d'échéance et les articles en retard.

## 🛠️ Pile Technologique

| Catégorie | Composant | Détails |
| :--- | :--- | :--- |
| **Backend** | Framework | Spring Boot 4.0.0 |
| | Langage | Java 21 |
| | Base de Données | MySQL avec Spring Data JPA |
| | Sécurité | Spring Security avec JWT |
| | Documentation API | Springdoc OpenAPI 2.2.0 |
| | Migration de BD | Flyway |
| **Frontend** | Templating | Thymeleaf |
| | Style | Bootstrap/Custom CSS |
| | Graphiques | Chart.js |
| | Cartes | Leaflet (pour la localisation des bibliothèques) |
| **Services** | E-mail | Spring Mail (SMTP) |
| **Outils de Développement** | Outil de Construction | Maven |
| | Tests | JUnit, Spring Boot Test |
| | Monitoring | Spring Boot Actuator |
| | Rechargement à Chaud | Spring Boot DevTools |

## 🚀 Démarrage Rapide

### Prérequis
Assurez-vous d'avoir installé les éléments suivants :
*   **Java 21 JDK**
*   **MySQL 8.0+**
*   **Maven 3.8+**

### Instructions de Configuration

1.  **Cloner et Configurer**
    ```bash
    git clone <repository-url>
    cd libraryManager
    ```

2.  **Configurer les Propriétés de l'Application**
    Créez un fichier `application.properties` dans `src/main/resources/` et configurez les propriétés suivantes :

```properties
# Fichier d'exemple de configuration des propriétés de l'application
# Renommez ce fichier en 'application.properties' et remplacez les valeurs de substitution par vos informations d'identification réelles.

spring.application.name=libraryManager

# --- Configuration HOST ---
server.host=localhost
server.port=8080
server.protocol=http

# --- Paramètres JPA / Hibernate ---
spring.jpa.properties.hibernate.hbm2ddl.auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.properties.hibernate.hbm2ddl.schema_export.show=true
spring.jpa.properties.hibernate.hbm2ddl.schema_export.delimiter=;
spring.jpa.properties.hibernate.hbm2ddl.schema_export.create_source=metadata
spring.jpa.properties.hibernate.hbm2ddl.schema_export.create_script_source=src/main/resources/schema.sql
spring.jpa.database-platform=org.hibernate.dialect.MySQLDialect
spring.jpa.hibernate.ddl-auto=update
# Exemple pour PostgreSQL :
# spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect


# --- Configuration de la Base de Données ---
# Configuration MySQL
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.url=jdbc:mysql://<votre_hote_mysql>:<votre_port_mysql>/<votre_base_de_donnees>?ssl-mode=REQUIRED
spring.datasource.username=<votre_nom_utilisateur_mysql>
spring.datasource.password=<votre_mot_de_passe_mysql>

# Exemple de configuration PostgreSQL (décommenter pour utiliser)
# spring.datasource.driver-class-name=org.postgresql.Driver
# spring.datasource.url=jdbc:postgresql://<votre_hote_postgres>:<votre_port_postgres>/<votre_base_de_donnees>?sslmode=require&channel_binding=require
# spring.datasource.username=<votre_nom_utilisateur_postgres>
# spring.datasource.password=<votre_mot_de_passe_postgres>


# --- Configuration de Hachage Argon2 ---
spring.security.password.argon2.salt-length=16
spring.security.password.argon2.hash-length=32
spring.security.password.argon2.parallelism=1
spring.security.password.argon2.memory=4096
spring.security.password.argon2.iterations=3

# --- Clé Secrète JWT ---
# Générez une clé secrète longue et sécurisée pour la production
spring.security.jwt.secret="<votre_cle_secrete_jwt_securisee>"
spring.security.jwt.expiration-ms=3600000
spring.security.jwt.issuer=libraryManager


# --- Configuration SMTP (E-mail) ---
spring.mail.smtp.host=<votre_hote_smtp>
spring.mail.smtp.port=<votre_port_smtp>
spring.mail.smtp.username=<votre_nom_utilisateur_smtp>
spring.mail.smtp.password=<votre_mot_de_passe_smtp>
spring.mail.smtp.auth=true
spring.mail.smtp.starttlsEnable=true
spring.mail.smtp.starttlsRequired=true


# --- Valeurs par Défaut des E-mails ---
spring.mail.email.from=<votre_adresse_email_expediteur>
spring.mail.email.defaultSubject=Notification
spring.mail.email.templatesPath=/templates/emails

# --- Configuration Filebase IPFS (Stockage de Fichiers) ---
filebase.access-key=<votre_cle_acces_filebase>
filebase.secret-key=<votre_cle_secrete_filebase>
filebase.bucket=<votre_nom_de_bucket_filebase>
filebase.endpoint=https://s3.filebase.com
filebase.ipfs-gateway=<votre_passerelle_ipfs_filebase>
filebase.region=us-east-1


# --- Clés API Radar.com (pour la géolocalisation) ---
radar.geolocation.secret-key=<votre_cle_secrete_live_radar>
radar.geolocation.publish-key=<votre_cle_publique_live_radar>
radar.geolocation.test-secret-key=<votre_cle_secrete_test_radar>
radar.geolocation.test-publish-key=<votre_cle_publique_test_radar>
```

### Construire et Exécuter

```bash
mvn clean install
mvn spring-boot:run
```

### Accéder à l'Application

*   **Interface Web :** `http://localhost:8080`
*   **Documentation API :** `http://localhost:8080/swagger-ui.html`

### Exécution avec Docker (Production)

1. Construire et lancer le stack Docker :
``` docker-compose up -d --build ```

2. Variables sensibles et production :

   * Les credentials pour DB, SMTP, JWT, etc. doivent être injectés via docker-compose.yml ou secrets GitHub Actions.
   * application.properties pour la prod peut être fourni via secret ou volume monté.

3. Arrêter le stack : ``` docker compose down ```

## 🔄 Pipeline CI/CD

Le pipeline CI/CD comprend :

### 1. Stratégie des Branches Git

 * main/master (branche de production) → push directe interdites 🔒
 * dev (branche de développement) → push directes interdites 🔒
 * c. Branches de fonctionnalité (feature/, test/, refactor/, ...etc.)
   

### 2. Github Actions
pour github actions on a 2 workflows :

#### CI (Intégration Continue) : Build et Tests ```.github/workflows/ci.yml```:

  * Charger le code source sur le runner CI
  * Configurer l'environnement de développement (JDK 21 et Maven)
  * Télécharger et installer les dépendances Maven
  * Injecter les variables d'environnement (en utilisant les secrets GitHub) c'est-à-dire application.properties
  * Construire l'application (JAR) ===> Build
  * Exécuter les tests unitaires et d'intégration et afficher les résultats

**Déclenchement** : Lorsqu'une Pull Request est créée vers la branche de développement (dev) ou la branche de production (master)

#### CD (Déploiement Continu) : Build et Déploiement ```.github/workflows/cd.yml```

  * Charger le code source sur le runner CD
  * Configurer Docker
  * Configurer les variables d'environnement Docker (tag d'image ```TAG``` et ```ACR_HOST``` - le lien vers Azure Container Registry)
  * Se connecter à Azure ACR (nom d'utilisateur et mot de passe ACR pris depuis **GitHub secrets**  dans ce cas ```AZURE_ACR_USERNAME``` et ```AZURE_ACR_PASSWORD```)
  * Injecter les variables d'environnement application.properties (en utilisant **GitHub secrets**)
  * Construire l'image Docker avec le hash du commit comme tag en utilisant la configuration docker-compose.yml
  * Créer une copie de l'image Docker créée avec le tag ```latest```
  * faire un push des 2 images Docker vers ACR

### 3. Microsoft Azure
Ressources créées manuellement depuis le portail Azure :

### Azure Container Registry (repository : library-app) 

GitHub Actions déploie 2 images à chaque push vers master :
* Image avec le hash du commit actuel comme tag
* Image avec le tag ```latest``` (si ```latest``` existe déjà → remplace l'image avec le tag ```latest``` déjà présente dans le registry)

![Azure_ACR.png](https://tropical-moccasin-unicorn.myfilebase.com/ipfs/Qmai6FpPW3VeVePAAJPzBXGr1UwAdxRhXea5sdzy6mw35N)

### Web Application (library-app-container)

* La ressource est mappée sur le registry de conteneurs ```library-app``` avec l'image tag ```latest```
* La ressource a l'option "déploiement continu" activée, donc chaque fois que l'image Docker avec le tag ```latest``` est mise à jour dans le registry, un nouveau conteneur est créé pour refléter la nouvelle mise à jour et l'ancien est supprimé

![Azure_Webapp.png](https://tropical-moccasin-unicorn.myfilebase.com/ipfs/QmWj4K26JpMr7ZxoKUvJqSxFkhLUcN13aEFoR6Cfr6pfmm)


## 🔗 Points d'Accès API

### 🔐 Points d'Accès d'Authentification

| Méthode | Point d'Accès | Description | Accès |
| :--- | :--- | :--- | :--- |
| `GET` | `/login` | Page de connexion | Public |
| `POST` | `/login` | Authentifier l'utilisateur | Public |
| `GET` | `/logout` | Déconnecter l'utilisateur | Authentifié |
| `GET` | `/sign-up` | Page d'inscription | Public |
| `POST` | `/sign-up` | Enregistrer un nouvel utilisateur | Public |
| `GET` | `/email-verification/{token}` | Vérifier l'e-mail | Public |

### 👤 Points d'Accès Client

| Méthode | Point d'Accès | Description |
| :--- | :--- | :--- |
| `GET` | `/client/dashboard` | Tableau de bord client |
| `GET` | `/client/resources` | Parcourir les ressources |
| `GET` | `/client/resources/search` | Recherche avancée |
| `GET` | `/client/resources/type/{type}` | Filtrer par type |
| `GET` | `/client/loans` | Voir mes prêts |
| `GET` | `/client/loans/new` | Formulaire de nouveau prêt |
| `POST` | `/client/loans/reserve` | Réserver une ressource |
| `POST` | `/client/loans/{loanId}/close` | Clôturer le prêt avec commentaires |

### 📚 Points d'Accès Bibliothécaire

| Méthode | Point d'Accès | Description |
| :--- | :--- | :--- |
| `GET` | `/librarian/dashboard` | Tableau de bord bibliothécaire |
| `GET` | `/librarian/loans` | Voir les prêts de la bibliothèque |
| `POST` | `/librarian/loans/{loanId}/borrow` | Valider l'emprunt |
| `POST` | `/librarian/loans/{loanId}/return` | Valider le retour |
| `GET` | `/librarian/manage-resources` | Gestion des ressources |
| `POST` | `/librarian/manage-resources/add` | Ajouter une ressource |
| `PATCH` | `/librarian/manage-resources/edit/{id}` | Modifier une ressource |
| `DELETE` | `/librarian/manage-resources/remove/{id}` | Supprimer une ressource |

### ⚙️ Points d'Accès Administrateur

| Méthode | Point d'Accès | Description |
| :--- | :--- | :--- |
| `GET` | `/admin/dashboard` | Tableau de bord analytique |
| `GET` | `/admin/dashboard/export` | Exporter les analyses CSV |
| `POST` | `/admin/manage-admins/add` | Ajouter un administrateur |
| `PATCH` | `/admin/manage-admins/edit/{id}` | Modifier un administrateur |
| `DELETE` | `/admin/manage-admins/remove/{id}` | Supprimer un administrateur |
| `POST` | `/admin/manage-libraries/add` | Ajouter une bibliothèque |
| `PATCH` | `/admin/manage-libraries/edit/{id}` | Modifier une bibliothèque |
| `DELETE` | `/admin/manage-libraries/remove/{id}` | Supprimer une bibliothèque |
| `POST` | `/admin/manage-librarians/add` | Ajouter un bibliothécaire |
| `PATCH` | `/admin/manage-librarians/edit/{id}` | Modifier un bibliothécaire |
| `DELETE` | `/admin/manage-librarians/remove/{id}` | Supprimer un bibliothécaire |
| `GET` | `/admin/loans` | Tous les prêts actifs |
| `GET` | `/admin/loans/overdue` | Prêts en retard |

### 🔔 API de Notification (REST)

| Méthode | Point d'Accès | Description |
| :--- | :--- | :--- |
| `GET` | `/api/notifications` | Obtenir les notifications de l'utilisateur |
| `POST` | `/api/notifications/{id}/read` | Marquer comme lu |
| `POST` | `/api/notifications/read-all` | Marquer tout comme lu |

## 🗄️ Schéma de Base de Données

### Entités Principales
*   **User** (Abstrait) → Admin, Librarian, Client
*   **Resource** (Livres, Médias Numériques, Multimédia)
*   **Loan** (Réservation → Emprunt → Retour → Commentaire)
*   **Library** (Support de plusieurs succursales)
*   **Notification** (Notifications utilisateur)
*   **Feedback** (Évaluations et commentaires de prêt)

### Relations
*   Une Bibliothèque → Plusieurs Bibliothécaires
*   Une Bibliothèque → Plusieurs Ressources
*   Un Client → Plusieurs Prêts
*   Une Ressource → Plusieurs Prêts (historique)
*   Un Prêt → Un Commentaire

## 🔒 Fonctionnalités de Sécurité
*   **Authentification :** Jetons JWT avec Spring Security.
*   **Autorisation :** Contrôle d'accès basé sur les rôles (RBAC).
*   **Sécurité des Mots de Passe :** Hachage crypté.
*   **Protection CSRF :** Activée pour tous les formulaires.
*   **Validation des Entrées :** API de validation de Bean.
*   **Sécurité du Téléchargement de Fichiers :** Restrictions de taille et de type.
*   **En-têtes Sécurisés :** Auto-configurés par Spring Security.



## 📈 Optimisations de Performance
*   Indexation de la base de données sur les champs fréquemment interrogés.
*   Pagination pour les grands ensembles de résultats.
*   Chargement paresseux (Lazy loading) pour les relations d'entité.
*   Pool de connexions avec HikariCP.
*   Mise en cache des ressources statiques.
*   Intégration CDN pour les fichiers téléchargés.

## 📄 Licence

Ce projet est sous licence **MIT** - voir le fichier `LICENSE` pour plus de détails.

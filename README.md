# Proof of concept Medhead

Ce guide vous explique comment configurer et lancer la Proof of Concept d'une API pour Medhead.

## Prérequis

- [Java Development Kit (JDK) 17 ou supérieur](https://www.oracle.com/java/technologies/javase-downloads.html)
- [Maven](https://maven.apache.org/)
- [Git](https://git-scm.com/)

## Installation

1. Clonez le dépôt du projet :
   ```bash
   git clone https://github.com/Oiwawa/OC_P11_POC_MEDHEAD.git
   cd OC_P11_POC_MEDHEAD
   ``` 

2. Build le projet avec Maven
    ```bash
   mvn clean install
   ```

   **Si le projet nécessite une configuration spécifique (comme les paramètres de base de données), éditez le fichier application.properties situé dans src/main/resources et ajustez les valeurs selon vos besoins.**


3. Lancer l'application
   ```bash
   mvn spring-boot:run
   ```
   Vous devriez voir l'application démarrer dans la console. Par défaut, elle sera accessible à l'adresse suivante : http://localhost:9090


4. Tests

   ```bash
   mvn test
   ```
   


## Configuration
- SDK 17
- Maven
- Database: h2
- Port 9090

# Proof of concept Medhead

Ce guide vous explique comment configurer et lancer la Proof of Concept d'une API pour Medhead.

## Prérequis

- [Java Development Kit (JDK) 17 ou supérieur](https://www.oracle.com/java/technologies/javase-downloads.html)
- [Maven](https://maven.apache.org/)
- [Git](https://git-scm.com/)

## Installation

1. Cloner le dépôt du projet :
   ```bash
   git clone https://github.com/Oiwawa/OC_P11_POC_MEDHEAD.git
   cd OC_P11_POC_MEDHEAD
   ``` 

2. Build le projet avec Maven
    ```bash
   mvn clean install
   ```

   **Si le projet nécessite une configuration spécifique (comme les paramètres de base de données), éditez le fichier application.properties situé dans "/src/main/resources" et ajustez les valeurs selon vos besoins.**


3. Lancer l'application
   ```bash
   mvn spring-boot:run
   ```
   Vous devriez voir l'application démarrer dans la console. Par défaut, elle sera accessible à l'adresse suivante : http://localhost:9090


4. Tests

   ```bash
   mvn test
   ```
   ou via les fonctionnalités de lancement tests d'IntelliJ Idea
   
5. Lancer la base de données h2
 - Lancer l'application
 - Lancer le script data.sql qui se trouve dans "/src/resources"
 - Accéder à "http://localhost:9090/h2-console"
 - Première connexion : Tester la connection ([Voir la documentation H2 ](https://www.h2database.com/html/main.html) en cas de problème)
 - Se connecter

6. Lancer le front
 - Voir le readme du repository front-end


## Documentation :
- Lancer l'application Java 
- Accéder à : http://localhost:9090/swagger-ui/index.html


## Configuration
- SDK 17
- Maven
- Database: h2
- Port 9090

## Workflow Git
Pour assurer une bonne gestion du code source, nous utilisons un workflow Git structuré. Voici les principales règles et branches utilisées :

## Branches
- `main` : Contient la version stable du code qui est prête pour la production.

- `develop` : Contient le code en cours de développement. Toutes les nouvelles fonctionnalités doivent être intégrées ici avant d'être fusionnées dans main. Cette branche est à utiliser pour la mise en place d'une plateforme de pré-production et non pas pour développer directement des fonctionnalités sur cette branche.

- `develop/*` : Chaque nouvelle fonctionnalité doit être développée dans une branche distincte à partir de develop. Le merge de ces branches dans `develop` permet la mise en ligne sur les plateformes de pré-production.

- `fix/*` : Chaque correction de bug doit être effectuée dans une branche distincte à partir de develop ou main (selon la criticité).


## Tests

### Configuration JMeter

1. Après l'installation de JMeter, alle dans le dossier "bin" puis lancer la commande
   ```bash
   $ jmeter.sh
   ```

2. Propriétés du Groupe d'unités
   - Nombre d'unités: **2000**
   - Nombre d'itérations : **10**

3. Créer une requête HTTP
  - Nom ou adresse IP: localhost 
  - Port: 9090
  - Chemin: `hopistal/search`
  - Ajouter le corps de la requête

    Exemple de corps :
      ```
      latInit=51.509865
      longInit=-0.118092
      distance=100
      speciality=allergy
      availableBeds=true
      ```


4. Visualiser les résultats en ajoutant comme récépteurs un "**Tableau des résultats**"


## CI/CD
Ce projet utilise GitHub Actions pour automatiser le processus de build, de test et de déploiement. Le fichier de configuration du pipeline CI/CD se trouve dans `.github/workflows/ci.yml.`

### Étapes clés du pipeline CI/CD :
1. **Checkout code** : Récupère le code source du repository.
2. **Setup JDK 17** : Installe la version 17 du JDK.
3. **Cache des dépendances Maven** : Met en cache les dépendances Maven pour accélérer les builds.
4. **Build avec Maven** : Compile le projet et génère les artéfacts.
5. **Run tests** : Exécute les tests unitaires, d'intégration et end-to-end
6. **Upload build artifacts** : Sauvegarde les artéfacts générés.

Les artéfacts de build sont clairement versionnés et traçables grâce aux branches et commits.

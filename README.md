![Image du projet](https://drive.google.com/uc?export=view&id=10cGfM-noIL_E6wjTzp1_dZdvDWGN4OqZ)

# Sommaire
- [Avant de commencer](#avant-de-commencer)
- [Quelques conventions de nommages](#quelques-conventions-de-nommages)
- [Creation de branches pour les evolutions](#creation-de-branches-pour-les-evolutions)
- [Importer le projet git dans Eclipse](#importer-le-projet-git-dans-eclipse)
- [Changer de branche git dans Eclipse](#changer-de-branche-git-dans-eclipse)
- [Mettre a jour son repertoire git local avec le repertoire distant](#mettre-a-jour-son-repertoire-git-local-avec-le-repertoire-distant)
- [Fusionner les modifications faites sur une autre branche avec la branche courante](#fusionner-les-modifications-faites-sur-une-autre-branche-avec-la-branche-courante)
- [Generation de l’artefact JAVA : JAR](#generation-de-l-artefact-java-jar)
	- [Prerequis](#prerequis)
	- [Etapes de generation](#etapes-de-generation)
		- [generation via Eclipse](#generation-via-eclipse)
		- [generation en ligne de commande](#generation-en-ligne-de-commande)
- [Scenario de demonstration pour R1](#scenario-de-demonstration-pour-r1)
- [Si des erreurs apparaissent](#si-des-erreurs-apparaissent)

# Avant de commencer
- Dans certains dossiers, vous trouverez un fichier oneFileIsRequired.txt. Vous pourrez supprimer ces fichiers lorsque le dossier contiendra au moins un autre fichier (par exemple un fichier .java). Si vous supprimez ce fichier .txt et que le dossier devient vide, alors le dossier ne pourra pas être 'commit' ce qui pourra entraîner des erreurs de compilation.
- N'oubliez pas de faire des commits réguliers afin que les autres membres du groupe puissent connaitre votre avancement.

# Quelques conventions de nommages
- Le nom des variables ainsi que le nom des fonctions seront en anglais
- Le nom des interfaces java débutera toujours par un 'I'

# Creation de branches pour les evolutions
1. Cliquez sur le bouton + (situé à côté du nom de la branche courante)
2. Cliquez sur 'New branch'
3. Vous serez rediriger vers une fenêtre qui vous demandera d'indiquer le nom de votre nouvelle branche. Ce nom doit être le nom de l'évolution de manière succincte et les mots seront séparés par des underscores. Vous devrez également sélectionner la branche  partir de laquelle vous voulez créer la branche : vérifiez que c'est bien la branche master (ou une autre branche si elle est bien à jour)
4. Puis pour travailler sur cette branche dans Eclipse, [mettez à jour le projet](#mettre-a-jour-son-repertoire-git-local-avec-le-repertoire-distant) et [changez de branche](#changer-de-branche-git-dans-eclipse).

# Importer le projet git dans Eclipse
1. Copiez le lien suivant : https://gitlab.com/climg/monitrack.git (Ce lien apparaît lorsque vous cliquez sur le bouton clone en haut à droite de la page puis dans la partie 'Clone with HTTPS')
2. Rendez-vous dans Eclipse
3. Cliquez sur Window > Perspective > Open Perspective > Git
4. Cliquez sur Clone (soit le petit nuage avec une flèche verte ou alors un lien qui s'affiche)
5. Dans l'URI, collez le lien que vous avez copié précédemment.
6. Renseignez votre user (prenom.nom) et votre password.
7. Sélectionnez toutes les branches, puis faites 'Next'.
8. Choisissez l'emplacement du projet, puis faites'Finish'.
9. Une fois que le projet apparaît dans la fenêtre 'Git Repositories', faites une clic-droit sur le projet puis 'Import projects'

# Changer de branche git dans Eclipse
1. Assurez-vous d'avoir [mis à jour votre répertoire avec le répertoire distant](#mettre-a-jour-son-repertoire-git-local-avec-le-repertoire-distant)
2. Dans 'Project Explorer', faites un clic-droit sur 'monitrack', puis Team > Switch To > Other

	- Si vous n'avez pas utilisé la branche dans le projet :
		-> cherchez dans le dossier 'Local'	et la sélectionner
	- Si vous avez déjà utilisé la branche : 
		-> cherchez dans le dossier 'Remote Tracking'
		-> Sélectionnez la branche puis cliquez sur 'Checkout as a new Local Branch'
3. Faites un clic-droit sur monitrack > Maven > Update Project
4. Puis clic-droit sur monitrack > Run As > Maven Clean
5. Puis clic-droit sur monitrack > Run As > Maven Install
6. Vous pouvez ensuite travailler sur cette branche

# Mettre a jour son repertoire git local avec le repertoire distant
1. Dans 'Project Explorer', faites un clic-droit sur 'monitrack', puis Team > Fetch from Upstream
2. Enfin clic-droit sur 'monitrack', Team > Pull

# Fusionner les modifications faites sur une autre branche avec la branche courante
1. Assurez-vous d'avoir [mis à jour votre répertoire avec le répertoire distant](#mettre-a-jour-son-repertoire-git-local-avec-le-repertoire-distant)
2. Puis clic-droit sur 'monitrack', Team > Merge
3. Sélectionnez la branche que vous voulez copier
4. Cliquez sur 'Merge'
5. Si des conflits apparaissent, réglez-les en conservant les parties que vous souhaitez garder
6. Enfin clic-droit sur 'monitrack', Team > Push To Upstream

# Generation de l’artefact JAVA (JAR)
Source : https://www.codejava.net/coding/how-to-create-executable-jar-file-with-resources-and-dependencies-using-maven-in-eclipse
## Prerequis
1.Assurez-vous d'avoir cette partie de code xml dans le fichier pom.xml du module que vous allez exporter au format .jar
```xml
<build>
	<plugins>
		<plugin>
			<artifactId>maven-assembly-plugin</artifactId>
			<configuration>
				<archive>
					<manifest>
						<mainClass>[package de la classe contenant la fonction main].[nom
							de la classe contenant la méthode main]</mainClass>
					</manifest>
				</archive>
				<descriptorRefs>
					<descriptorRef>jar-with-dependencies</descriptorRef>
				</descriptorRefs>
			</configuration>
		</plugin>
	</plugins>
</build>
```

## Etapes de generation
### Generation via Eclipse
1. Faites : clic-droit sur le projet Monitrack > Run As > Run Configurations
![image](https://drive.google.com/uc?export=view&id=1JKQCFFMCMsfCDiVzvwkVdmby03sFkPm9)

2. Dans la fenêtre qui va s'ouvrir et dans la partie Maven Build, sélectionnez le module que vous voulez générer
3. Dans la partie goals, y écrire :

```
assembly:single
```
![image](https://drive.google.com/uc?export=view&id=13ITCwIcN86TdpqeYqLgScUV-zd9kkOsC)

4. N'hésitez pas à changer le nom de la configuration afin de faciliter les Run ultérieurs
5. Appuyez sur le bouton "Apply" en bas, puis Run.
6. Sur la console devrait s'afficher les différentes étapes
7. Une fois que le "Run" a terminé avec succès, vous pourrez trouver votre fichier .jar généré en suivant le chemin indiqué après "Building jar"
![image](https://drive.google.com/uc?export=view&id=1RPDmLLlS__I3e628636povbH-AkUbaH_)
 


### Generation en ligne de commande
1. Assurez-vous d'avoir installé Maven sur votre ordinateur
2. Rendez- vous dans le dossier du module du projet que vous voulez générer en .jar
3. Ouvrez la console en faisant "Shift + Clic-Droit" dans le dossier courant
4. Lancer la commande suivante :

```
D:\GitHub\PDS_ESIPE_CYCLE_INGENIEUR\monitrack\monitrackGUI> mvn clean compile assembly:single
```
![image](https://drive.google.com/uc?export=view&id=13GuC_9-UF1D1ohc8cM2zj-ORsN1_8QAz)

5. Enfin pour trouver le fichier .jar généré, il vous suffira de suivre le chemin indiqué après "Building jar" ou alors vous pouvez exécuter la commande suivante :

```
D:\GitHub\PDS_ESIPE_CYCLE_INGENIEUR\monitrack\monitrackGUI> java -jar <chemin complet du jar indiqué après "Building jar">
```
![image](https://drive.google.com/uc?export=view&id=1ZCq0y3x-RnhTh-vBt6nWiXexaDG1ODuY)


# Scenario de demonstration pour R1
1. Lancer la machine virtuelle contenant la base de données de production
2. Ouvrir, l'IHM sur un ordinateur (en local)
3. Remplir le champ nom et appuyer sur le bouton "valider" (ce qui devrait envoyer une requête à la base de données)
4. Refaire l'étape 3 avec plusieurs noms différents
5. Appuyer sur le bouton "Tout voir"
6. Une liste avec tous les noms qui ont été ajoutés précédemment devrait apparaître

# Si des erreurs apparaissent
- En cas de problèmes lors de l'exportation du projet Maven en fichier .jar, vous pouvez consulter le site suivant :
https://www.codejava.net/coding/how-to-create-executable-jar-file-with-resources-and-dependencies-using-maven-in-eclipse

- "Error when trying to fetch or push" (Source : https://stackoverflow.com/questions/22824170/eclipse-egit-error-when-trying-to-fetch-or-push) :
	Clique-droit sur le projet -> Team -> Remote -> Configure push to upstream->URI, Change-> Add authentication details
	
[Revenir en haut de la page](#sommaire)
	
![bas de page](https://drive.google.com/uc?export=view&id=1lVkDmyl7kpe0XZHS8s5atJ4CMEI7vSSa)


![Image du projet](https://drive.google.com/uc?export=view&id=10cGfM-noIL_E6wjTzp1_dZdvDWGN4OqZ)

# Sommaire
- [Avant de commencer](#avant-de-commencer)
- [Quelques conventions de nommages](#quelques-conventions-de-nommages)
- [Creation de branches pour les evolutions](#creation-de-branches-pour-les-evolutions)
- [Importer le projet git dans Eclipse](#importer-le-projet-git-dans-eclipse)
- [Changer de branche git dans Eclipse](#changer-de-branche-git-dans-eclipse)
- [Mettre a jour son repertoire git local avec le repertoire distant](#mettre-a-jour-son-repertoire-git-local-avec-le-repertoire-distant)
- [Fusionner les modifications faites sur une autre branche avec la branche courante](#fusionner-les-modifications-faites-sur-une-autre-branche-avec-la-branche-courante)
- [Si des erreurs apparaissent](#si-des-erreurs-apparaissent)

# Avant de commencer
- Dans certains dossiers, vous trouverez un fichier oneFileIsRequired.txt. Vous pourrez supprimer ces fichiers lorsque le dossier contiendra au moins un autre fichier (par exemple un fichier .java). Si vous supprimez ce fichier .txt et que le dossier devient vide, alors le dossier ne pourra pas �tre 'commit' ce qui pourra entra�ner des erreurs de compilation.

# Quelques conventions de nommages
- Le nom des variables ainsi que le nom des fonctions sont en anglais
- Le nom des interfaces java d�butera toujours par un 'I'

# Creation de branches pour les evolutions
1. Cliquez sur le bouton + (situ� � c�t� du nom de la branche courante)
2. Cliquez sur 'New branch'
3. Vous serez redirigez vers une fen�tre qui vous demandera d'indiquer le nom de votre nouvelle branche. Ce nom doit �tre le nom de l'�volution de mani�re succinte et les mots seront s�par�s par des underscores. Vous devrez �galement s�lectionner la branche  partir de laquelle vous voulez cr�er la branche : v�rifiez que c'est bien la branche master (ou une autre branche si elle est bien � jour)
4. Puis pour travailler sur cette branche dans Eclipse, suivez mettez � jour le projet(#mettre-a-jour-son-repertoire-git-local-avec-le-repertoire-distant) et changez de branche(#changer-de-branche-git-dans-eclipse).

# Importer le projet git dans Eclipse
1. Copiez le lien suivant : https://gitlab.com/climg/monitrack.git (Ce lien apparait lorsque vous cliquez sur le bouton clone en haut � droite de la page puis dans la partie 'Clone with HTTPS')
2. Rendez-vous dans Eclipse
3. Cliquez sur Window > Perspective > Open Perspective > Git
4. Cliquez sur Clone (soit le petit nuage avec une fl�che verte ou alors un lien qui s'affiche)
5. Dans l'URI, collez le lien que vous avez copier pr�c�demment.
6. Renseignez votre user (prenom.nom) et votre password.
7. S�lectionnez toutes les branches, puis faites 'Next'.
8. Choisissez l'emplacement du projet, puis faites'Finish'.
9. Une fois que le projet appara�t dans la fen�tre 'Git Repositories', faites une clique-droit sur le projet puis 'Import projects'

# Changer de branche git dans Eclipse
1. Assurez-vous d'avoir mis � jour votre r�pertoire avec le r�pertoire distant(#mettre-a-jour-son-repertoire-git-local-avec-le-repertoire-distant)
2. Dans 'Project Explorer', faites un clic-droit sur 'monitrack', puis Team > Switch To > Other

	- Si vous n'avez pas utilis� la branche dans le projet :
		-> cherchez dans le dossier 'Local'	et la s�lectionner
	- Si vous avez d�j� utilis� la branche : 
		-> cherchez dans le dossier 'Remote Tracking'
		-> S�lectionnez la branche puis cliquez sur 'Checkout as a new Local Branch'
3. Faites un clic-droit sur monitrack > Maven > Update Project
4. Puis clic-droit sur monitrack > Run As > Maven Clean
5. Puis clic-droit sur monitrack > Run As > Maven Install
6. Vous pouvez ensuite travailler sur cette branche

# Mettre a jour son repertoire git local avec le repertoire distant
1. Dans 'Project Explorer', faites un clic-droit sur 'monitrack', puis Team > Fetch from Upstream
2. Puis clic-droit sur 'monitrack', Team > Pull

# Fusionner les modifications faites sur une autre branche avec la branche courante
1. Assurez-vous d'avoir mis � jour votre r�pertoire avec le r�pertoire distant(#mettre-a-jour-son-repertoire-git-local-avec-le-repertoire-distant)
2. Puis clic-droit sur 'monitrack', Team > Merge
3. S�lectionnez la branche que vous voulez copier
4. Cliquez sur 'Merge'
5. Si des conflits apparaissent, r�glez-les
6. Enfin clic-droit sur 'monitrack', Team > Push To Upstream

# Si des erreurs apparaissent
- En cas de probl�mes lors de l'exportation du projet Maven en fichier .jar, vous pouvez consulter le site suivant :
https://www.codejava.net/coding/how-to-create-executable-jar-file-with-resources-and-dependencies-using-maven-in-eclipse

- "Error when trying to fetch or push" (Source : https://stackoverflow.com/questions/22824170/eclipse-egit-error-when-trying-to-fetch-or-push) :
	Clique-droit sur le projet -> Team -> Remote -> Configure push to upstream->URI, Change-> Add authentication detailsls

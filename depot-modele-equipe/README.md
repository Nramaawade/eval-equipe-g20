# Épreuve d'équipe IsepCash — POO Java (1 h 30, 3 personnes)

ISEP Amadou Trawaré de Diamniadio · Département Informatique · Développement Back-End

Le sujet complet est distribué sur papier. Ce dépôt est **la copie de votre équipe** : tout le code y arrive par Git, petit à petit, et chaque membre pousse **son propre travail depuis son propre poste**.

## 1. Mise en place (10 min)

1. **Le membre A** crée le dépôt depuis ce modèle (**Use this template**). Il le nomme `eval-equipe-gNN` (NN = numéro du groupe sur 2 chiffres, par exemple `eval-equipe-g07`) et le met en **Private**. Ensuite, dans **Settings → Collaborators**, il ajoute B, C et `pape1283`.
2. B et C acceptent l'invitation. Les trois clonent le dépôt dans VS Code : `Ctrl+Shift+P` (ou `Cmd+Shift+P`) → **Git: Clone**.
3. Chacun règle son identité Git, avec le nom qu'il inscrira dans `EQUIPE.md` :
   ```bash
   git config user.name "Prénom Nom"
   git config user.email "votre.email@exemple.com" 
   ```
4. **A** remplit `src/Parametres.java` (numéro de groupe, membres) et le tableau de `EQUIPE.md`, puis pousse avec le message `[A] checkpoint-0`.
5. **Chacun** récupère ce commit, enregistre ses extensions et pousse (remplacez `X` par votre lettre) :
   ```bash
   git pull --no-rebase
   code --list-extensions > extensions-X.txt
   git add extensions-X.txt
   git commit -m "[X] checkpoint-0"
   git push
   ```

## 2. Qui écrit quoi

**Chacun ne modifie que ses fichiers.** C'est ce qui évite les conflits Git.

| Membre | Fichiers |
|--------|----------|
| A — Comptes | `Compte.java`, `CompteStandard.java`, `ComptePremium.java`, `CompteMarchand.java` |
| B — Plateforme | `Plateforme.java`, puis `Main.java` (intégration) |
| C — Chaînes et USSD | `Ussd.java` (y compris `executer` pendant l'intégration) |
| Tous | sa propre partie du bilan dans `EQUIPE.md` |

**Jalon important :** A pousse une première version de `Compte.java` **avant + 20 min** (attributs, constructeur, accesseurs, méthodes abstraites). B en a besoin pour compiler `Plateforme`.

## 3. Le cycle à répéter (au moins après chaque méthode terminée)

```bash
git add src/MonFichier.java
git commit -m "[B] transferer"
git pull --no-rebase
git push
```

- Messages de commit : toujours votre lettre entre crochets, puis ce que vous avez fait.
- Si `git push` est refusé : `git pull --no-rebase`, puis `git push`.
- **Interdits :** `git push --force`, `git commit --amend`, `git rebase`, modifier les dates.

## 4. Checkpoints annoncés par l'enseignant

À chaque annonce, **chaque membre** commite et pousse, même si son code n'est pas fini :

| Moment | Message |
|--------|---------|
| Départ | `[X] checkpoint-0` |
| + 20 min | `[X] jalon-1` |
| + 45 min | `[X] jalon-2` |
| + 70 min | `[X] jalon-3` |
| + 90 min (fin) | `[X] rendu-final` |

## 5. Vérifier son travail à tout moment

```bash
javac -d bin src/*.java
java -cp bin Verification
```

`Verification` affiche chaque test, les points obtenus et ce qui ne va pas, partie par partie. Ne le modifiez pas. Tant qu'une classe manque, seuls ses tests échouent. Pour lancer le scénario final : `java -cp bin Main`.

## 6. Règles (contrôlées automatiquement)

- **Aucune exception** : ni `throw`, ni `try`/`catch`, ni `throws`. Une méthode signale un problème en renvoyant `false`, `null` ou `-1`, comme indiqué dans le sujet.
- **Tableaux statiques uniquement** : ni `ArrayList`, ni `List`, `Map`, `Set`, ni streams.
- **Partie C :** ni `split`, ni `matches`, ni `replaceAll`, ni `Integer.parseInt`/`Long.parseLong` : on travaille caractère par caractère.
- **Aucun assistant d'IA** (Copilot, ChatGPT, Claude, Gemini…) : ne modifiez pas les réglages du dossier `.vscode`.
- **Messages sans accents** dans les affichages imposés (`effectue`, `Numero`, `collectes`), pour éviter les problèmes d'encodage.
- **Oral d'équipe après l'épreuve** : chacun explique sa partie **et celle d'un coéquipier**, puis fait une modification en direct.

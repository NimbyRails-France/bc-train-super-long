# BC Train super long

BC Train super long permet de choisir la longueur totale maximale d'une composition, avec **850 mètres par défaut**. Cette longueur comprend les locomotives et tous les autres véhicules ; le réglage ne fixe pas un nombre de voitures.

Le projet est **en développement**. L'activation dans le jeu dépend du SDK compatible et de la validation de son adaptation native. La déclaration du mod ne suffit pas à rendre une ancienne version du SDK compatible.

## Réglage / Settings

Dans le jeu, ouvrir **Options → NRF Hub → BC Train super long**, puis modifier **Longueur maximale du train (m)**. Le SDK conserve cette préférence et applique les changements validés. Le mod n'ouvre aucune fenêtre dédiée.

La longueur de la composition candidate est vérifiée avant chaque ajout de véhicule. Un ajout dépassant la limite est refusé avec un message indiquant les deux longueurs ; la composition actuelle est conservée. Réduire le réglage ne raccourcit pas les trains déjà composés.

Le SDK propose actuellement une plage de configuration de **1 à 10 000 mètres**. Il s'agit d'une borne de développement ; la compatibilité des compositions reste soumise aux contrôles techniques du SDK.

BC Train super long lets players choose the maximum total composition length, with **850 metres by default**. Locomotives and every other vehicle are included; the setting does not specify a carriage count.

Open **Options → NRF Hub → BC Train super long** and edit **Maximum train length (m)**. The SDK stores the preference and applies validated changes. Each proposed addition is checked before changing the composition. When the total would exceed the limit, the vehicle is not added and a message shows the candidate length and the maximum. Lowering the setting does not shorten existing trains. The mod has no dedicated window.

The project is **in development**. In-game support requires a compatible SDK with a validated native implementation. The current development configuration range is **1 to 10,000 metres** and does not remove native compatibility and technical checks.

## Développement / Development

Les journaux du SDK suivent chaque demande de composition : action, véhicules concernés, longueurs mesurées, total proposé, limite active et motif précis d'un refus. Ils indiquent aussi les changements du réglage, les contrôles d'attelage et le résultat renvoyé par le jeu. Une donnée manquante ou illisible est distinguée d'un dépassement de longueur. Les traces sont écrites en arrière-plan ; une saturation est signalée avec le nombre de messages perdus. Le mod n'ajoute pas de fenêtre de journaux.

SDK logs track each composition request: action, vehicle models, measured lengths, candidate total, active limit and the exact rejection reason. They also record preference changes, coupling checks and the native command result. Missing or unreadable data is distinguished from a length-limit violation. Logs are written asynchronously; saturation reports the number of dropped messages. The mod adds no logging window.

Le mod contient une préférence `IntegerOption` et une déclaration `trainEditor { maximumLength(...) }`. Les messages de refus sont définis en français et en anglais dans `assets/translations.json`, puis traduits par le SDK selon la langue du jeu. Le SDK gère les contrôles du jeu, les changements d'option et le cycle de vie. Aucun accès mémoire, parcours de carte ou callback périodique n'est nécessaire dans le mod.

Les sources se trouvent dans `src/main/kotlin`, les traductions dans `assets/translations.json` et les tests dans `src/test/kotlin`.
The mod declares an `IntegerOption` and a `trainEditor { maximumLength(...) }` rule. Its rejection messages are defined in French and English in `assets/translations.json` and resolved by the SDK using the game's language. The SDK handles game validation, option changes and lifecycle. The mod needs no memory access, map scan or periodic callback. Sources are in `src/main/kotlin`, translations in `assets/translations.json` and tests in `src/test/kotlin`.

`mod.json` déclare `developmentStatus` avec la valeur `in-development` pour ce nouveau projet.
Ce statut est affiché par le Hub, indépendamment du canal de version. Choisissez `stable` lorsque le projet est prêt, ou retirez le champ pour ne pas afficher de badge.
`mod.json` declares `developmentStatus` as `in-development` for this new project.
The Hub displays this status independently from the release channel. Choose `stable` when the project is ready, or remove the field to show no badge.

- Sous Windows x64, utiliser le kit du SDK **0.9.0-alpha.3** ou une version compatible ultérieure qui expose `trainEditor`. Les versions précédentes ne proposent pas cette fonction. Kotlin : 2.2.20 ; Gradle : 8.14.3 ; Java : 21.
- On Windows x64, use the SDK **0.9.0-alpha.3** kit or a later compatible version that provides `trainEditor`. Earlier SDK versions do not provide this feature. Kotlin: 2.2.20; Gradle: 8.14.3; Java: 21.
- `gradle.properties` choisit le kit local et reste exclu de Git. Chaque développeur renseigne son propre `nrfSdkDir`.
- `gradle.properties` selects the local kit and is ignored by Git. Each developer configures their own `nrfSdkDir`.
- Le Hub passe explicitement le kit choisi à Gradle. Hors du Hub, un `nrfSdkDir` dans le `gradle.properties` utilisateur peut prendre priorité sur celui du projet : utilisez `-PnrfSdkDir=chemin-du-kit` pour choisir explicitement.
- The Hub passes the selected kit explicitly to Gradle. Outside the Hub, a user-level `gradle.properties` can override this project's `nrfSdkDir`: use `-PnrfSdkDir=kit-directory` to select it explicitly.
- Le plugin est relu depuis le kit choisi à chaque configuration Gradle. Ne mélangez pas API, plugin et bibliothèque de kits différents.
- The plugin is read from the selected kit at Gradle configuration time. Keep API, plugin and native library from the same kit.

Sous Windows / On Windows:

```powershell
.\gradlew.bat windowsTest -PnrfSdkDir=chemin-du-kit
.\gradlew.bat verifyNativeMod -PnrfSdkDir=chemin-du-kit
.\gradlew.bat packageMod -PnrfSdkDir=chemin-du-kit
```

Sous Linux, utiliser les tâches équivalentes uniquement avec un kit annonçant la prise en charge de cette fonction sur cette plateforme.
On Linux, use equivalent Linux tasks only when the selected kit announces support for this feature on that platform. The first build downloads Gradle and compiler dependencies.

`packageMod` teste, vérifie et crée l’archive dans `build/gradle/distributions` sous Windows (`build/gradle-linux/distributions` sous Linux).
`packageMod` tests, verifies and creates the archive. No command above installs or publishes the mod.

Ces commandes testent et préparent le paquet ; elles ne l'installent ni ne le publient.
These commands test and prepare the package; they do not install or publish it.

## Compatibilité / Compatibility

`mod.json` conserve l'identité `bc-train-super-long` et les empreintes du jeu sélectionné. La version minimale du SDK est **0.9.0-alpha.3** : elle inclut l'API et les contrôles natifs nécessaires à la limite de longueur.
La borne supérieure initiale `0.10.0` exclue est une plage de développement ; testez les versions annoncées avant de publier.
`mod.json` retains the `bc-train-super-long` identity and the selected game's hashes. The minimum SDK version is **0.9.0-alpha.3**, which includes the API and native checks needed for the length limit.
The initial exclusive upper bound `0.10.0` is a development range; test any SDK versions you claim before publishing.

## Publication

`VERSION` et `mod.json` doivent annoncer la même version. Pour **0.1.0**, le canal est `stable` et la branche de publication est `main` ; le badge `in-development` décrit séparément l'état du projet.
`VERSION` and `mod.json` must declare the same version. For **0.1.0**, the release channel is `stable` and the release branch is `main`; the `in-development` badge describes the project's status separately.

La CI utilise `.woodpecker/sdk-revision.txt` pour choisir un commit exact du SDK, puis télécharge et vérifie le kit Windows publié pour cette version. Avant une publication, ce fichier doit contenir les 40 caractères de l'empreinte du commit SDK **0.9.0-alpha.3** retenu. Le kit correspondant doit déjà être disponible. Aucune configuration de kit locale n'est publiée.
CI selects an exact SDK commit through `.woodpecker/sdk-revision.txt`, then downloads and verifies the published Windows kit for that version. Before a release, this file must contain the selected **0.9.0-alpha.3** SDK commit's full 40-character hash. Its matching kit must already be available. Local kit settings are not published.

Un commit ordinaire lance les contrôles sans publier. La publication est demandée par un sujet `release 0.1.0`, avec une entrée datée dans `CHANGELOG.md`. Woodpecker est le parcours habituel ; ajouter `Release-Runner: github` au corps du commit choisit le workflow GitHub Actions. Celui-ci conserve les paquets vérifiés, publie la release GitHub et actualise son catalogue public. La réplication vers le serveur NRF est une étape distincte lorsque le runner GitHub est utilisé.
Ordinary commits run checks without publishing. A `release 0.1.0` commit subject requests publication, with a dated entry in `CHANGELOG.md`. Woodpecker is the usual path; adding `Release-Runner: github` to the commit body selects GitHub Actions. It preserves verified packages, publishes the GitHub release and updates its public catalogue. Mirroring to the NRF server is a separate step when the GitHub runner is used.

## Licences / Licenses

Le wrapper Gradle conserve ses notices ; sa licence est dans `gradle/LICENSE.txt`.
The Gradle wrapper retains its notices; its license is in `gradle/LICENSE.txt`.
Choisissez une licence pour votre code avant publication. No license is assigned to your own code by this template.

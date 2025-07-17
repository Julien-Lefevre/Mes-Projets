Ce projet de site web à été réalisé en 3ème année de licence Informatique en React et Node.js et mené par : 
	- Téo Itsweire-Krebs
	- Julien Lefevre

## 🏠 Page d’accueil

Nous arrivons tout d'abord sur une **page d'accueil** présentant l’histoire de The Shaggs.

En haut de cette page, on retrouve :
- Un **lien vers le tableau des skills**
- Un **bouton de connexion via Discord**

![Accueil](accueil.png)

---

## 🔐 Connexion via Discord

Lorsque l'on clique sur **"Connexion avec Discord"**, tout se fait automatiquement.  
L’utilisateur doit simplement **valider une autorisation finale** :

![Connexion Discord](auth_discord.png)

---

## 📊 Tableau des skills

Une fois connecté, on peut accéder au **tableau des compétences**.

La page affiche :
- Les compétences de tous les membres
- **Uniquement les vôtres sont modifiables**

![Tableau des skills](tab_skills_1.png)

---

## ✏️ Modification des compétences

Vous pouvez modifier les valeurs de vos compétences.  
Voici un exemple **avant modification** :

![Avant modification](avant_modif.png)

---

## ✅ Après validation

Une fois les changements validés, les nouvelles compétences sont enregistrées et affichées immédiatement :

![Après modification](apres_modif.png)

---

## 🛠️ Tech Stack

- Frontend : React
- Backend : Node.js (Express)
- Authentification : OAuth2 via Discord
- Base de données : PostgreSQL (Clever Cloud)

---

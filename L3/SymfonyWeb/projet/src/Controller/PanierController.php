<?php

namespace App\Controller;

use App\Entity\Panier;
use App\Service\UserService;
use Doctrine\ORM\EntityManagerInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Bundle\SecurityBundle\Security;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

#[Route('/panier', name: 'panier')]
class PanierController extends AbstractController
{

    //Fonction affichant le panier de l'utilisateur
    #[Route('/affichage', name: '_affichage')]
    #[\Sensio\Bundle\FrameworkExtraBundle\Configuration\Security(
    "is_granted('ROLE_CLIENT')"
    )]
    public function ajouterpanierAction(UserService $userservice, Security $security): Response
    {
        $user = $security->getUser();
        $args = ['paniers' => $user->getPaniers(), 'totalproduits' => $userservice->getTotalQuantiteProduit(), 'totalprix' => $userservice->getTotalPrixCommande()];
        return $this->render('Panier/panier_affichage.html.twig', $args);
    }

    //Fonction permettant de supprimer le produit sélectionner présent dans le panier
    #[Route('/supprimer1produit/{idpanier}', name: '_supprimer1produit',
    requirements:['idpanier' => '[1-9][0-9]*'])]
    #[\Sensio\Bundle\FrameworkExtraBundle\Configuration\Security(
        "is_granted('ROLE_CLIENT')"
    )]
    public function supprimerUnProduitPanierAction(int $idpanier, EntityManagerInterface $em): Response
    {
        //On récupère le produit dans la table Panier
        $panierRepository = $em->getRepository(Panier::class);
        $panier = $panierRepository->find($idpanier);
        $produit = $panier->getProduit();

        //On remet à jour la quantité dans la table produit puis on supprime le produit du panier
        $produit->setQuantite($produit->getQuantite() + $panier->getQuantite());
        $em->remove($panier);
        $em->flush();
        $this->addFlash('info', 'Le produit a bien été supprimé de votre panier.');
        return $this->redirectToRoute('panier_affichage');
    }


    //Fonction permettant de commander les produits présents dans le panier de l'utilisateur
    #[Route('/commander', name: '_commander')]
    #[\Sensio\Bundle\FrameworkExtraBundle\Configuration\Security(
        "is_granted('ROLE_CLIENT')"
    )]
    public function commanderAction(EntityManagerInterface $em, Security $security): Response
    {
        //On récupère tout les produits présents dans le panier de l'utilisateur
        $user = $security->getUser();
        $paniers = $user->getPaniers();

        //On les supprime de la table Panier
        foreach ($paniers as $panier)
        {$em->remove($panier);}
        $em->flush();
        $this->addFlash('info', 'Vous avez validé votre commande.');
        return $this->redirectToRoute('panier_affichage');
    }


    //Fonction permettant de vider le panier de l'utilisateur
    #[Route('/vider', name: '_vider')]
    #[\Sensio\Bundle\FrameworkExtraBundle\Configuration\Security(
        "is_granted('ROLE_CLIENT')"
    )]
    public function viderAction(Security $security, UserService $userservice): Response
    {
        $userid = $security->getUser()->getId();

        //On utilise la fonction présente dans le Service UserService
        $userservice->viderLePanier($userid);
        $this->addFlash('info', 'Vous avez vidé votre panier.');
        return $this->redirectToRoute('panier_affichage');
    }
}

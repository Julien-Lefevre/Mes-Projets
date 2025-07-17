<?php

namespace App\Service;

use App\Entity\User;
use Doctrine\ORM\EntityManagerInterface;
use Symfony\Bundle\SecurityBundle\Security;
use App\Entity\Produit;
use Twig\Extension\AbstractExtension;
use Twig\TwigFunction;

class UserService extends AbstractExtension
{
    private $entityManager;
    private $security;

    //Constructeur permettant d'accéder à Security et à l'EntityManagerInterface
    public function __construct(EntityManagerInterface $entityManager, Security $security)
    {
        $this->entityManager = $entityManager;
        $this->security = $security;
    }

    //Permet l'utilisation d'une fonction dans un fichier Twig
    public function getFunctions()
    {
        return [
            new TwigFunction('getTotalQuantiteProduit', [$this, 'getTotalQuantiteProduit']),
        ];
    }


    //Fonction renvoyant la quantité d'un produit dans le panier de l'utilisateur
    public function getQuantiteProduit(Produit $produit): int
    {
        $user = $this->security->getUser();
        foreach ($user->getPaniers() as $panier){
            if ($panier->getProduit() === $produit){
                return $panier->getQuantite();
            }
        }
        return 0;
    }


    //Fonction renvoyant l'identifiant de l'instance Panier si le produit est présent dans le panier de l'utilisateur
    public function isProduitInPanier(Produit $produit): ?int
    {
        //On récupère le panier de l'utilisateur concerné
        $user = $this->security->getUser();
        $paniers = $user->getPaniers();

        // On parcours chaque panier pour vérifier si le produit est présent
        foreach ($paniers as $panier) {
            $panierProduit = $panier->getProduit();

            //Si le produit est présent on retourne l'identifiant du panier
            if ($panierProduit->getId() === $produit->getId()) {
                return $panier->getId();
            }
        }
        //Si le produit n'y est pas, on retourne null
        return null;
    }

    //Renvoi la quantite de produits présents dans le panier d'un utilisateur
    public function getTotalQuantiteProduit() : int
    {
        $total = 0;
        $user = $this->security->getUser();
        foreach($user->getPaniers() as $panier)
        {
            $total += $panier->getQuantite();
        }
        return $total;
    }


    //Fonction renvoyant le prix total du panier d'un utilisateur
    public function getTotalPrixCommande() : float
    {
        $total = 0.0;
        $user = $this->security->getUser();
        foreach($user->getPaniers() as $panier)
        {
            $total += ($panier->getQuantite() * $panier->getProduit()->getPrix());
        }
        return $total;
    }


    //Fonction vidant le panier d'un utilisateur sélectionné
    public function viderLePanier(int $iduser): void
    {
        $userRepository = $this->entityManager->getRepository(User::class);
        $user = $userRepository->find($iduser);
        $paniers = $user->getPaniers();
        $produitRepository = $this->entityManager->getRepository(Produit::class);
        foreach ($paniers as $panier)
        {
            $produit = $produitRepository->find($panier->getProduit()->getId());
            $produit->setQuantite($produit->getQuantite() + $panier->getQuantite());
            $this->entityManager->remove($panier);
            $this->entityManager->flush();
        }

    }

}
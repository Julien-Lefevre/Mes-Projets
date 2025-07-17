<?php

namespace App\Controller;

use App\Entity\Panier;
use App\Entity\Produit;
use App\Form\PanierType;
use App\Form\ProduitType;
use App\Service\UserService;
use Doctrine\ORM\EntityManagerInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Bundle\SecurityBundle\Security;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\HttpKernel\Exception\NotFoundHttpException;
use Symfony\Component\Routing\Annotation\Route;

#[Route('/produit/form', name: 'produit_form')]
class ProduitFormController extends AbstractController
{

    //Fonction permettant de lister les produits et créer le formulaire permettant l'ajout au panier de chaque produit
    #[Route('/liste', name: '_liste')]
    #[\Sensio\Bundle\FrameworkExtraBundle\Configuration\Security(
        "is_granted('ROLE_CLIENT')"
    )]
    public function listeProduits(UserService $userservice, EntityManagerInterface $em, Security $security): Response
    {
        //On récupère tous les produits existants dans la base de données
        $produitRepository = $em->getRepository(Produit::class);
        $produits = $produitRepository->findAll();

        //Initialisation d'un tableau dans lequel on rangera les formulaires
        $forms = array();


        foreach ($produits as $produit){
            $panier = new Panier();
            $panier->setUser($security->getUser());

            //Si il n'y a pas de stock pour le produit et que le client n'en a pas dans son panier, on ne créé pas de formulaire
             if (($userservice->isProduitInPanier($produit) == null) && $produit->getQuantite() == 0)
             {
                 $form = null;
             }
             ///Sinon, on créé un formulaire pour l'ajout au panier
             else
             {
                 $form = $this->createForm(PanierType::class, $panier, ['produit' => $produit]);
             }

             //On ajoute le formulaire au tableau de formulaires
             if ($form == null)
                array_push($forms, $form);
             else {
                array_push($forms, $form->createView());
            }
        }

        //La gestion des formulaires de cette fonction se fait dans la fonction gestionFormulaireAjoutPanier dans ce même fichier

        $args = array('produits' => $produits, 'forms' => $forms);

        return $this->render('form/produit_liste.html.twig', $args);
    }



    //Fonction permettant de créer un produit
    #[Route('/ajouter', name: '_ajouter')]
    #[\Sensio\Bundle\FrameworkExtraBundle\Configuration\Security(
        "is_granted('ROLE_ADMIN')"
    )]
    public function creerunproduitAction(EntityManagerInterface $em, Request $request): Response
    {
        $produit = new Produit();

        //On créé le formulaire de création d'un produit
        $form = $this->createForm(ProduitType::class, $produit);
        $form->add('send', SubmitType::class, ['label' => 'Ajouter le produit']);
        $form->handleRequest($request);

        //On fait la gestion du formulaire
        if ($form->isSubmitted() && $form->isValid())
        {
            $em->persist($produit);
            $em->flush();
            $this->addFlash('info', 'Ajout du produit réussi.');
            return $this->redirectToRoute('app_accueil');
        }

        if ($form->isSubmitted()) {
            $this->addFlash('info', 'Formulaire de création de produit incorrect.');
        }

        $args = array(
            'form' => $form->createView(),
        );
        return $this->render('form/produit_ajouter.html.twig', $args);
    }





    //Fonction permettant de gérer les formulaires d'ajout au panier
    #[Route('/gestion', name: '_gestion')]
    #[\Sensio\Bundle\FrameworkExtraBundle\Configuration\Security(
        "is_granted('ROLE_CLIENT')"
    )]
    public function gestionFormulaireAjoutPanier(EntityManagerInterface $em, Request $request, Security $security, UserService $userService): Response
    {
        //On récupère les données envoyées par le fichier twig
        $donneesFormulaire = $request->request->all();

        // On vérifie si les données du formulaire sont présentes et si elles contiennent une clé 'produitcache'
        if (isset($donneesFormulaire['panier']['produitcache'])) {
            // On récupère l'id présent dans 'produitcache' référant le produit concerné
            $idProduit = $donneesFormulaire['panier']['produitcache'];

            // On le récupère dans la base de données
            $produitRepository = $em->getRepository(Produit::class);
            $produit = $produitRepository->find($idProduit);

            if ($produit) {

                //On créé une nouvelle instance Panier et on assigne aux attributs leur valeur
                $panier = new Panier();
                $panier->setUser($security->getUser());
                $panier->setProduit($produit);
                $panier->setQuantite($donneesFormulaire['panier']['quantite']);

                //Si le produit est déjà présent dans le panier de l'utilisateur
                if (($idPanier=$userService->isProduitInPanier($produit))!==null)
                {
                    //On récupère l'instance du panier concernée
                    $panierRepository = $em->getRepository(Panier::class);
                    $thepanier = $panierRepository->find($idPanier);

                    //On met à jour les quantités pour le produit dans les tables Produit et Panier
                    $thepanier->setQuantite($thepanier->getQuantite()+$donneesFormulaire['panier']['quantite']);
                    $produit->setQuantite($produit->getQuantite() - $donneesFormulaire['panier']['quantite']);

                    //Si la mise à jour fait que la quantité du produit dans le panier de l'utilisateur est à 0, on supprime cette instance de la table
                    if ($thepanier->getQuantite() == 0)
                        $em->remove($thepanier);
                    $em->flush();
                }

                //Sinon, on ajoute la ,nouvelle instance de Panier dans la table
                else{

                    //Sauf si c'est 0 qui a été sélectionné
                    if ($donneesFormulaire['panier']['quantite'] != 0) {
                        $produit->setQuantite($produit->getQuantite() - $donneesFormulaire['panier']['quantite']);
                        $em->persist($panier);
                        $em->flush();
                    }
                }

                $this->addFlash('success', 'Le produit a été ajouté au panier avec succès.');

                return $this->redirectToRoute('produit_form_liste');
            } else {
                //Le produit n'a pas été trouvé, on déclenche une exception
                throw new NotFoundHttpException('ProduitFormController, gestion : Le produit n\'existe pas dans la base de données');
            }
        } else {
            // Si la clé 'produitcache' n'est pas présente dans les données du formulaire, on déclenche une exception
            throw new NotFoundHttpException('ProduitFormController, gestion : Le formulaire n\'a pas gardé les informations necéssaires' );
        }

    }
}
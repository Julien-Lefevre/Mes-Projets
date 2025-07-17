<?php

namespace App\Controller;

use App\Entity\Panier;
use App\Entity\Pays;
use App\Entity\User;
use App\Form\UserAccountType;
use App\Service\UserService;
use Doctrine\ORM\EntityManagerInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Bundle\SecurityBundle\Security;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\PasswordHasher\Hasher\UserPasswordHasherInterface;
use Symfony\Component\Security\Core\Exception\AccessDeniedException;
use Symfony\Component\Security\Core\Exception\AuthenticationException;
use Symfony\Component\Security\Core\Exception\InvalidCsrfTokenException;
use Symfony\Component\Security\Core\User\PasswordAuthenticatedUserInterface;

#[Route('/useraccount', name: 'useraccount')]
class UserAccountController extends AbstractController
{

    //Focntion permettant de créer un compte utilisateur
    #[Route('/ajouter', name: '_ajouter')]
    public function ajouterunutilisateur(UserPasswordHasherInterface $passwordHasher,EntityManagerInterface $em, Request $request, Security $security): Response
    {
        //Seuls les super-administrateurs et les utilisateurs non authentifiés on le droit d'accéder à cette fonction
        if ($security->isGranted('ROLE_CLIENT'))
            throw new AccessDeniedException('Vous avez déjà un compte logué');

        //On créé une nouvelle instance User
        $user = new User();

        //On créé le formulaire
        $form = $this->createForm(UserAccountType::class, $user);

        //En fonction de si on est super-administrateur ou non, on ne créé pas le même type de compte
        if ($security->isGranted('ROLE_SUPERADMIN'))
        {$form->add('send', SubmitType::class, ['label' => 'Valider la création de l\'administrateur']);}
        else
        {$form->add('send', SubmitType::class, ['label' => 'Valider la création de mon compte']);}
        $form->handleRequest($request);

        //Gestion du formulaire une fois envoyé.
        if ($form->isSubmitted() && $form->isValid())
        {
            //On hashe le mot de passe
            $hashedPassword = $passwordHasher->hashPassword($user, $user->getPassword());
            $user->setPassword($hashedPassword);

            //Si on est un superadmin, le rôle est défini sur Administrateur
            if ($this->isGranted('ROLE_SUPERADMIN'))
                $user->setRoles(['ROLE_ADMIN']);
            //Sinon, il est défini sur Client
            else $user->setRoles(['ROLE_CLIENT']);
            $em->persist($user);
            $em->flush();
            $this->addFlash('info', 'Création de compte réussie');
            return $this->redirectToRoute('app_accueil');
        }

        if ($form->isSubmitted())
            $this->addFlash('info', 'Formulaire de création de compte incorrect');

        $args = array(
            'form' => $form->createView(),
        );
        return $this->render('form/useraccount_ajouter.html.twig', $args);
    }


    //Fonction permettant à un utilisateur de modifier son profil
    //Pas totalement fonctionnel
    #[Route('/editer', name: '_editer')]
    #[\Sensio\Bundle\FrameworkExtraBundle\Configuration\Security(
        "is_granted('ROLE_CLIENT') or is_granted('ROLE_SUPERADMIN')"
    )]
    public function editerunutilisateur(Security $security, UserPasswordHasherInterface $passwordHasher,EntityManagerInterface $em, Request $request): Response
    {
        $user = $security->getUser();
        $form = $this->createForm(UserAccountType::class, $user);
        $form->add('send', SubmitType::class, ['label' => 'Valider les modifications']);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid())
        {
            if ($user instanceof PasswordAuthenticatedUserInterface) {
                $hashedPassword = $passwordHasher->hashPassword($user, $form->getData()->getPassword());
                dump($form->getData()->getPassword());
                $user->setPassword($hashedPassword);

                $em->flush();
                $this->addFlash('info', 'Vos informations ont bien été modifiées.');
                if ($this->isGranted('ROLE_SUPERADMIN'))
                    return $this->redirectToRoute('app_accueil');
                else return $this->redirectToRoute('produit_form_liste');
            }
        }

        if ($form->isSubmitted())
            $this->addFlash('info', 'Des informations modifiées sont incorrectes.');


        $args = array(
            'form' => $form->createView(),
        );
        return $this->render('form/useraccount_ajouter.html.twig', $args);
    }



    //Fonction permettant à un administrateur de gérer (supprimer) les autres utlisateurs
    #[Route('/gererusers', name: '_gererusers')]
    #[\Sensio\Bundle\FrameworkExtraBundle\Configuration\Security(
        "is_granted('ROLE_ADMIN')"
    )]
    public function gererUtilisateursAction (EntityManagerInterface $em): Response
    {
        //On récupère tous les utilisateurs existants dans la base de données
        $usersRepository = $em->getRepository(User::class);
        $users = $usersRepository->findAll();

        //On les envoie dans le twig
        return $this->render('gestion_utilisateurs.html.twig', ['users' => $users]);
    }


    //Fonction permettant de supprimer l'utiloisateur sélectionné
    #[Route('/supprimeruser/{iduser}', name: '_supprimeruser',
    requirements: ['iduser' => '[1-9][0-9]*'])]
    #[\Sensio\Bundle\FrameworkExtraBundle\Configuration\Security(
        "is_granted('ROLE_ADMIN')"
    )]
    public function supprimerUser (int $iduser,UserService $userservice, EntityManagerInterface $em, Security $security): Response
    {
        //On vide le Panier de l'utilisateur concerné
        $userservice->viderLePanier($iduser);

        $userrepository = $em->getRepository(User::class);
        $user = $userrepository->find($iduser);

        //Si l'utilisateur sélectionné est super-admin, on ne peut pas le supprimer
        if (in_array('ROLE_SUPERADMIN', $user->getRoles()))
            $this->addFlash('info', 'Vous ne pouvez pas supprimer un super-administrateur');
        //Pareillement si l'utilisateur sélectionné est celui actuellement connecté
        else
        {
            if ($user === $security->getUser())
                $this->addFlash('info', 'Vous ne pouvez pas vous supprimer vous même');
            //Sinon, on supprime l'utilisateur
            else
            {
                $em->remove($user);
                $em->flush();
            }
        }

        return $this->RedirectToRoute('useraccount_gererusers');
    }


}

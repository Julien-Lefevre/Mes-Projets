<?php

namespace App\DataFixtures;

use App\Entity\Pays;
use App\Entity\Produit;
use App\Entity\User;
use Doctrine\Bundle\FixturesBundle\Fixture;
use Doctrine\Persistence\ObjectManager;
use Symfony\Component\PasswordHasher\Hasher\UserPasswordHasherInterface;

class TestFixtures extends Fixture
{
    private $passwordHasher;

    public function __construct(UserPasswordHasherInterface $passwordHasher,)
    {
        $this->passwordHasher = $passwordHasher;
    }

    public function load(ObjectManager $em): void
    {

        //Création des pays
        $pays1 = new Pays();
        $pays1
            ->setNom('France')
            ->setCode('FR');
        $em->persist($pays1);

        $pays2 = new Pays();
        $pays2
            ->setNom('Espagne')
            ->setCode('ES');
        $em->persist($pays2);

        $pays3 = new Pays();
        $pays3
            ->setNom('Japon')
            ->setCode('JA');
        $em->persist($pays3);

        $pays4 = new Pays();
        $pays4
            ->setNom('Brésil')
            ->setCode('BR');
        $em->persist($pays4);

        $pays5 = new Pays();
        $pays5
            ->setNom('Belgique')
            ->setCode('BE');
        $em->persist($pays5);

        $pays6 = new Pays();
        $pays6
            ->setNom('Algérie')
            ->setCode('AL');
        $em->persist($pays6);
        $em->flush();



        //Création des utlisateurs
        $user1 = new User();
        $user1
            ->setLogin('sadmin')
            ->setNom('Lefevre')
            ->setPrenom('Julien')
            ->setPays($pays1)
            ->setDatenaissance(new \DateTime('2000-10-04'))
            ->setRoles(['ROLE_SUPERADMIN']);
        $hashedPassword = $this->passwordHasher->hashPassword($user1, 'nimdas');
        $user1->setPassword($hashedPassword);
        $em->persist($user1);
        $em->flush();

        $user2 = new User();
        $user2
            ->setLogin('gilles')
            ->setNom('Subrenat')
            ->setPrenom('Gilles')
            ->setDatenaissance(new \DateTime('1990-01-01'))
            ->setRoles(['ROLE_ADMIN']);
        $hashedPassword = $this->passwordHasher->hashPassword($user2, 'sellig');
        $user2->setPassword($hashedPassword);
        $em->persist($user2);
        $em->flush();

        $user3 = new User();
        $user3
            ->setLogin('rita')
            ->setNom('Zrour')
            ->setPrenom('Rita')
            ->setDatenaissance(new \DateTime('1990-01-01'))
            ->setRoles(['ROLE_CLIENT']);
        $hashedPassword = $this->passwordHasher->hashPassword($user3, 'atir');
        $user3->setPassword($hashedPassword);
        $em->persist($user3);
        $em->flush();


        $user4 = new User();
        $user4
            ->setLogin('boumediene')
            ->setNom('Saidi')
            ->setPrenom('Boumediene')
            ->setDatenaissance(new \DateTime('1990-01-01'))
            ->setRoles(['ROLE_CLIENT']);
        $hashedPassword = $this->passwordHasher->hashPassword($user4, 'eneidemuob');
        $user4->setPassword($hashedPassword);
        $em->persist($user4);
        $em->flush();


        //Création des produits
        $produit1 = new Produit();
        $produit1
            ->setNom('Raviolis pur boeuf')
            ->setQuantite(50)
            ->setPrix(3.49)
            ->addPay($pays1)
            ->addPay($pays5);
        $em->persist($produit1);

        $produit2 = new Produit();
        $produit2
            ->setNom('Chocolat en poudre')
            ->setQuantite(25)
            ->setPrix(2)
            ->addPay($pays2);
        $em->persist($produit2);

        $produit3 = new Produit();
        $produit3
            ->setNom('Farine')
            ->setQuantite(50)
            ->setPrix(1.50)
            ->addPay($pays5);
        $em->persist($produit3);

        $produit4 = new Produit();
        $produit4
            ->setNom('Taboulet Oriental')
            ->setQuantite(4)
            ->setPrix(2.79)
            ->addPay($pays3);
        $em->persist($produit4);

        $produit5 = new Produit();
        $produit5
            ->setNom('Café')
            ->setQuantite(89)
            ->setPrix(15.99)
            ->addPay($pays4);
        $em->persist($produit5);

        $produit6 = new Produit();
        $produit6
            ->setNom('Yatch')
            ->setQuantite(1)
            ->setPrix(33000)
            ->addPay($pays5);
        $em->persist($produit6);

        $produit7 = new Produit();
        $produit7
            ->setNom('Télévision')
            ->setQuantite(14)
            ->setPrix(399.99)
            ->addPay($pays6);
        $em->persist($produit7);

        $produit8 = new Produit();
        $produit8
            ->setNom('Cordon Bleu')
            ->setQuantite(23)
            ->setPrix(5.49)
            ->addPay($pays1)
            ->addPay($pays2)
            ->addPay($pays4)
            ->addPay($pays5);
        $em->persist($produit8);

        $produit9 = new Produit();
        $produit9
            ->setNom('Ceinture')
            ->setQuantite(10)
            ->setPrix(119)
            ->addPay($pays1);
        $em->persist($produit9);

        $em->flush();
    }
}

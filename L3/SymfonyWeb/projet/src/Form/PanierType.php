<?php

namespace App\Form;

use App\Service\UserService;
use App\Entity\Panier;
use App\Entity\Produit;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\Extension\Core\Type\ChoiceType;
use Symfony\Component\Form\Extension\Core\Type\HiddenType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;

class PanierType extends AbstractType
{
    private $userService;

    public function __construct(UserService $userService)
    {
        $this->userService = $userService;
    }
    public function buildForm(FormBuilderInterface $builder, array $options): void
    {
        $builder
            ->add('quantite',
                ChoiceType::class, [
                    'choices' => $this->getQuantiteChoices($options['produit']),
                    'data' => 0,
                    'label' => false
                ])

            //Champ caché permettant de connaitre le produit concerné par le formulaire
            ->add( 'produitcache',
                HiddenType::class,[
                    //'label' => 'ajouter au panier',
                    'mapped' => false,
                    'data' => $options['produit']->getId()
                ])
        ;
    }


    //Fonction permettant de déterminer toutes les valeurs possibles dans le champ quantité
    private function getQuantiteChoices(Produit $produit): array
    {
        $quantites = [];
        for ($i = -($this->userService->getQuantiteProduit($produit)); $i <= $produit->getQuantite(); $i++) {
            $quantites[$i] = $i;
        }
        return $quantites;
    }

    public function configureOptions(OptionsResolver $resolver): void
    {
        $resolver->setDefaults([
            'data_class' => Panier::class,
            'produit' => null, // Définition de l'option produit par défaut
        ]);
    }
}

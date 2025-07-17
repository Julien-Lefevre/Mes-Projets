<?php

namespace App\Form;

use App\Entity\Pays;
use App\Entity\User;
use Symfony\Bridge\Doctrine\Form\Type\EntityType;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\Extension\Core\Type\BirthdayType;
use Symfony\Component\Form\Extension\Core\Type\PasswordType;
use Symfony\Component\Form\Extension\Core\Type\TextType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;

class UserAccountType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options): void
    {
        $builder
            ->add('login',
                TextType::class,
                [
                    'label' => 'Login',
                    'attr' => ['placeholder' => 'Saisissez votre identifiant']
                ])

            ->add('password',
                PassWordType::class,
                ['label' => 'Mot de passe'])

            ->add('nom',
                TextType::class,
                [
                    'label' => 'Nom',
                    'attr' => ['placeholder' => 'Saisissez votre nom']
                ])
            ->add('prenom',
                TextType::class,
                [
                    'label' => 'Prenom',
                    'attr' => ['placeholder' => 'Saisissez votre prenom']
                ])

            ->add('datenaissance',
                BirthdayType::class,
                [
                'label' => 'Date de Naissance',
                ]
            )


            ->add('pays', EntityType::class, [
                'class' => Pays::class,
                'choice_label' => 'nom', // Remplacez 'nom' par le champ que vous souhaitez afficher dans le choix
                'expanded' => false, // Vous pouvez également utiliser 'expanded' => true si vous voulez afficher les options sous forme de cases à cocher plutôt que dans une liste déroulante
                'by_reference' => true, // Pour que la valeur sélectionnée soit automatiquement liée à l'entité Pays
                'placeholder' => 'Sélectionnez un pays',
                'required' => false
            ])
        ;
    }

    public function configureOptions(OptionsResolver $resolver): void
    {
        $resolver->setDefaults([
            'data_class' => User::class,
        ]);
    }
}

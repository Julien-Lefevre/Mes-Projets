<?php

namespace App\Entity;

use App\Repository\UserRepository;
use Doctrine\Common\Collections\ArrayCollection;
use Doctrine\Common\Collections\Collection;
use Doctrine\DBAL\Types\Types;
use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert;
use Symfony\Component\Security\Core\User\PasswordAuthenticatedUserInterface;
use Symfony\Component\Security\Core\User\UserInterface;
use Symfony\Bridge\Doctrine\Validator\Constraints\UniqueEntity;
use Symfony\Component\Validator\Context\ExecutionContextInterface;

#[ORM\Table(name: 'lic_users')]
#[ORM\Entity(repositoryClass: UserRepository::class)]
#[UniqueEntity(fields: ['nom', 'prenom'], message: "Ce couple nom/prenom existe déjà.", errorPath: 'nom')]
#[UniqueEntity(fields: ['login'], message: "Ce login est déjà utilisé", errorPath: 'login')]
class User implements UserInterface, PasswordAuthenticatedUserInterface
{
    #[ORM\Id]
    #[ORM\GeneratedValue]
    #[ORM\Column]
    private ?int $id = null;

    #[ORM\Column(length: 180, unique: true, nullable: false)]
    #[Assert\NotNull(message: 'Vous devez saisir un login')]
    private ?string $login = null;

    #[ORM\Column]
    private array $roles = [];

    /**
     * @var string The hashed password
     */
    #[ORM\Column]
    #[Assert\Length(
        min: 3,
        max: 30,
        minMessage: "Le texte doit contenir au moins {{ limit }} caractères",
        maxMessage : "Le texte ne peut pas dépasser {{ limit }} caractères"
    )]
    private ?string $password = null;

    #[ORM\Column(length: 100, nullable: false)]
    #[Assert\NotNull(message: 'Le nom ne peut pas être vide.')]
    private ?string $nom = null;

    #[ORM\Column(length: 100, nullable: false)]
    #[Assert\NotNull(message: 'Le prenom ne peut pas être vide.')]
    private ?string $prenom = null;

    #[ORM\Column(type: Types::DATE_MUTABLE, nullable: false)]
    #[Assert\NotNull(message: 'Vous devez saisir votre date de naissance.')]
    private ?\DateTimeInterface $datenaissance = null;

    #[ORM\ManyToOne(targetEntity: Pays::class)]
    #[ORM\JoinColumn(nullable: true)]
    #[Assert\Valid]
    private ?Pays $pays = null;

    #[ORM\OneToMany(targetEntity: Panier::class, mappedBy: 'user')]
    #[Assert\Valid]
    private Collection $paniers;

    public function __construct()
    {
        $this->pays = null;
        $this->paniers = new ArrayCollection();
    }

    public function getId(): ?int
    {
        return $this->id;
    }

    public function getLogin(): ?string
    {
        return $this->login;
    }

    public function setLogin(string $login): static
    {
        $this->login = $login;

        return $this;
    }

    /**
     * A visual identifier that represents this user.
     *
     * @see UserInterface
     */
    public function getUserIdentifier(): string
    {
        return (string) $this->login;
    }

    /**
     * @see UserInterface
     */
    public function getRoles(): array
    {
        $roles = $this->roles;
        // guarantee every user at least has ROLE_USER
        $roles[] = 'ROLE_USER';

        return array_unique($roles);
    }

    public function setRoles(array $roles): static
    {
        $this->roles = $roles;

        return $this;
    }

    /**
     * @see PasswordAuthenticatedUserInterface
     */
    public function getPassword(): string
    {
        return $this->password;
    }

    public function setPassword(string $password): static
    {
        $this->password = $password;

        return $this;
    }

    /**
     * @see UserInterface
     */
    public function eraseCredentials(): void
    {
        // If you store any temporary, sensitive data on the user, clear it here
        // $this->plainPassword = null;
    }

    public function getNom(): ?string
    {
        return $this->nom;
    }

    public function setNom(string $nom): static
    {
        $this->nom = $nom;

        return $this;
    }

    public function getPrenom(): ?string
    {
        return $this->prenom;
    }

    public function setPrenom(string $prenom): static
    {
        $this->prenom = $prenom;

        return $this;
    }

    public function getDatenaissance(): ?\DateTimeInterface
    {
        return $this->datenaissance;
    }

    public function setDatenaissance(\DateTimeInterface $datenaissance): static
    {
        $this->datenaissance = $datenaissance;

        return $this;
    }

    public function getPays(): ?Pays
    {
        return $this->pays;
    }

    public function setPays(?Pays $pays): static
    {
        $this->pays = $pays;

        return $this;
    }

    /**
     * @return Collection<int, Panier>
     */
    public function getPaniers(): Collection
    {
        return $this->paniers;
    }


    //Fonction permettant de vérifier que le login est différent du mot de passe
    #[Assert\Callback]
    public function logindiffpassword (ExecutionContextInterface $context)
    {
        if ($this->login == $this->password)
        {
            $context
                ->buildViolation('Le login et le mot de passe doivent être différents.')
                ->atPath('password')
                ->addViolation();
        }
    }

}

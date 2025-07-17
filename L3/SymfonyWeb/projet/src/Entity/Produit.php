<?php

namespace App\Entity;

use App\Repository\ProduitRepository;
use Doctrine\Common\Collections\ArrayCollection;
use Doctrine\Common\Collections\Collection;
use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert;

#[ORM\Table(name: 'lic_produit')]
#[ORM\Entity(repositoryClass: ProduitRepository::class)]
class Produit
{
    #[ORM\Id]
    #[ORM\GeneratedValue]
    #[ORM\Column]
    private ?int $id = null;

    #[ORM\Column(length: 100, nullable: false)]
    #[Assert\NotNull(message: 'Vous devez saisir un nom pour le produit')]
    #[Assert\Length(
        max: 100,
        maxMessage: 'Le nom ne peut faire plus de {{ limit }} caractères.'
    )]
    private ?string $nom = null;

    #[ORM\Column(type: 'float', nullable: false)]
    #[Assert\NotNull(message: 'Vous devez saisir un prix.')]
    #[Assert\Type(type: 'float')]
    #[Assert\GreaterThanOrEqual(value: 0.0, message: 'Le prix doit être positif.')]
    private ?float $prix = null;

    #[ORM\Column(options: ['default' => 0])]
    #[Assert\Type(type: 'integer')]
    #[Assert\GreaterThanOrEqual(value: 0, message: 'Le stock ne peut pas être négatif.')]
    private ?int $quantite = null;


    #[ORM\ManyToMany(targetEntity: Pays::class, inversedBy: 'produits')]
    #[Assert\Valid]
    private Collection $pays;

    #[ORM\OneToMany(targetEntity: Panier::class, mappedBy: 'produit')]
    #[Assert\Valid]
    private Collection $paniers;

    public function __construct()
    {
        $this->quantite = 0;
        $this->pays = new ArrayCollection();
        $this->paniers = new ArrayCollection();
    }

    public function getId(): ?int
    {
        return $this->id;
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

    public function getPrix(): ?float
    {
        return $this->prix;
    }

    public function setPrix(float $prix): static
    {
        $this->prix = $prix;

        return $this;
    }

    public function getQuantite(): ?int
    {
        return $this->quantite;
    }

    public function setQuantite(int $quantite): static
    {
        $this->quantite = $quantite;

        return $this;
    }


    /**
     * @return Collection<int, Pays>
     */
    public function getPays(): Collection
    {
        return $this->pays;
    }

    public function addPay(Pays $pay): static
    {
        if (!$this->pays->contains($pay)) {
            $this->pays->add($pay);
        }

        return $this;
    }

    public function removePay(Pays $pay): static
    {
        $this->pays->removeElement($pay);

        return $this;
    }

    /**
     * @return Collection<int, Panier>
     */
    public function getPaniers(): Collection
    {
        return $this->paniers;
    }

    public function addPanier(Panier $panier): static
    {
        if (!$this->paniers->contains($panier)) {
            $this->paniers->add($panier);
            $panier->setProduit($this);
        }

        return $this;
    }

    public function removePanier(Panier $panier): static
    {
        if ($this->paniers->removeElement($panier)) {
            // set the owning side to null (unless already changed)
            if ($panier->getProduit() === $this) {
                $panier->setProduit(null);
            }
        }

        return $this;
    }
}

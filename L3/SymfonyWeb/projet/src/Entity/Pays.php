<?php

namespace App\Entity;

use App\Repository\PaysRepository;
use Doctrine\Common\Collections\ArrayCollection;
use Doctrine\Common\Collections\Collection;
use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert;

#[ORM\Table(name: 'lic_pays')]
#[ORM\Entity(repositoryClass: PaysRepository::class)]
class Pays
{
    #[ORM\Id]
    #[ORM\GeneratedValue]
    #[ORM\Column]
    private ?int $id = null;

    #[ORM\Column(length: 100, nullable: false)]
    #[Assert\NotNull]
    #[Assert\Length(
        max: 100,
        maxMessage: 'Le nom ne peut faire plus de 100 caractères.'
    )]
    private ?string $nom = null;

    #[ORM\ManyToMany(targetEntity: Produit::class, mappedBy: 'pays')]
    #[Assert\Valid]
    private Collection $produits;

    #[ORM\Column(length: 2, nullable: true)]
    #[Assert\Length(
        max: 2,
        maxMessage: 'Le code ne peut faire plus de {{ limit }} caractères.',
        min: 2,
        minMessage: 'Le code ne peut faire moins de {{ limit }} caractères.'
    )]
    private ?string $code = null;

    public function __construct()
    {
        $this->code = null;
        $this->produits = new ArrayCollection();
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

    /**
     * @return Collection<int, Produit>
     */
    public function getProduits(): Collection
    {
        return $this->produits;
    }

    public function addProduit(Produit $produit): static
    {
        if (!$this->produits->contains($produit)) {
            $this->produits->add($produit);
            $produit->addPay($this);
        }

        return $this;
    }

    public function removeProduit(Produit $produit): static
    {
        if ($this->produits->removeElement($produit)) {
            $produit->removePay($this);
        }

        return $this;
    }

    public function getCode(): ?string
    {
        return $this->code;
    }

    public function setCode(?string $code): static
    {
        $this->code = $code;

        return $this;
    }
}

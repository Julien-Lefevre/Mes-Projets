<?php

declare(strict_types=1);

namespace DoctrineMigrations;

use Doctrine\DBAL\Schema\Schema;
use Doctrine\Migrations\AbstractMigration;

/**
 * Auto-generated Migration: Please modify to your needs!
 */
final class Version20240412141614 extends AbstractMigration
{
    public function getDescription(): string
    {
        return '';
    }

    public function up(Schema $schema): void
    {
        // this up() migration is auto-generated, please modify it to your needs
        $this->addSql('CREATE TEMPORARY TABLE __temp__lic_produit AS SELECT id, nom, prix, quantite FROM lic_produit');
        $this->addSql('DROP TABLE lic_produit');
        $this->addSql('CREATE TABLE lic_produit (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, nom VARCHAR(100) NOT NULL, prix DOUBLE PRECISION NOT NULL, quantite INTEGER NOT NULL)');
        $this->addSql('INSERT INTO lic_produit (id, nom, prix, quantite) SELECT id, nom, prix, quantite FROM __temp__lic_produit');
        $this->addSql('DROP TABLE __temp__lic_produit');
    }

    public function down(Schema $schema): void
    {
        // this down() migration is auto-generated, please modify it to your needs
        $this->addSql('ALTER TABLE lic_produit ADD COLUMN quantite2 INTEGER NOT NULL');
    }
}

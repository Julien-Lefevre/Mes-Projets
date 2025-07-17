<?php

declare(strict_types=1);

namespace DoctrineMigrations;

use Doctrine\DBAL\Schema\Schema;
use Doctrine\Migrations\AbstractMigration;

/**
 * Auto-generated Migration: Please modify to your needs!
 */
final class Version20240412141037 extends AbstractMigration
{
    public function getDescription(): string
    {
        return '';
    }

    public function up(Schema $schema): void
    {
        // this up() migration is auto-generated, please modify it to your needs
        $this->addSql('CREATE TABLE lic_pays (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, nom VARCHAR(100) NOT NULL)');
        $this->addSql('CREATE TABLE lic_produit (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, nom VARCHAR(100) NOT NULL, prix DOUBLE PRECISION NOT NULL, quantite INTEGER NOT NULL, quantite2 INTEGER NOT NULL)');
        $this->addSql('CREATE TABLE produit_pays (produit_id INTEGER NOT NULL, pays_id INTEGER NOT NULL, PRIMARY KEY(produit_id, pays_id), CONSTRAINT FK_1D074141F347EFB FOREIGN KEY (produit_id) REFERENCES lic_produit (id) ON DELETE CASCADE NOT DEFERRABLE INITIALLY IMMEDIATE, CONSTRAINT FK_1D074141A6E44244 FOREIGN KEY (pays_id) REFERENCES lic_pays (id) ON DELETE CASCADE NOT DEFERRABLE INITIALLY IMMEDIATE)');
        $this->addSql('CREATE INDEX IDX_1D074141F347EFB ON produit_pays (produit_id)');
        $this->addSql('CREATE INDEX IDX_1D074141A6E44244 ON produit_pays (pays_id)');
    }

    public function down(Schema $schema): void
    {
        // this down() migration is auto-generated, please modify it to your needs
        $this->addSql('DROP TABLE lic_pays');
        $this->addSql('DROP TABLE lic_produit');
        $this->addSql('DROP TABLE produit_pays');
    }
}

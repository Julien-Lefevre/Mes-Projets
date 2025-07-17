<?php

declare(strict_types=1);

namespace DoctrineMigrations;

use Doctrine\DBAL\Schema\Schema;
use Doctrine\Migrations\AbstractMigration;

/**
 * Auto-generated Migration: Please modify to your needs!
 */
final class Version20240414134741 extends AbstractMigration
{
    public function getDescription(): string
    {
        return '';
    }

    public function up(Schema $schema): void
    {
        // this up() migration is auto-generated, please modify it to your needs
        $this->addSql('CREATE TABLE lic_users_produits (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, user_id INTEGER DEFAULT NULL, produit_id INTEGER NOT NULL, quantite INTEGER NOT NULL, CONSTRAINT FK_50353E8FA76ED395 FOREIGN KEY (user_id) REFERENCES lic_users (id) NOT DEFERRABLE INITIALLY IMMEDIATE, CONSTRAINT FK_50353E8FF347EFB FOREIGN KEY (produit_id) REFERENCES lic_produit (id) NOT DEFERRABLE INITIALLY IMMEDIATE)');
        $this->addSql('CREATE INDEX IDX_50353E8FA76ED395 ON lic_users_produits (user_id)');
        $this->addSql('CREATE INDEX IDX_50353E8FF347EFB ON lic_users_produits (produit_id)');
    }

    public function down(Schema $schema): void
    {
        // this down() migration is auto-generated, please modify it to your needs
        $this->addSql('DROP TABLE lic_users_produits');
    }
}

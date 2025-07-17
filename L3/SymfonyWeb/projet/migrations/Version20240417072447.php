<?php

declare(strict_types=1);

namespace DoctrineMigrations;

use Doctrine\DBAL\Schema\Schema;
use Doctrine\Migrations\AbstractMigration;

/**
 * Auto-generated Migration: Please modify to your needs!
 */
final class Version20240417072447 extends AbstractMigration
{
    public function getDescription(): string
    {
        return '';
    }

    public function up(Schema $schema): void
    {
        // this up() migration is auto-generated, please modify it to your needs
        $this->addSql('CREATE TEMPORARY TABLE __temp__lic_pays AS SELECT id, nom FROM lic_pays');
        $this->addSql('DROP TABLE lic_pays');
        $this->addSql('CREATE TABLE lic_pays (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, nom VARCHAR(100) NOT NULL)');
        $this->addSql('INSERT INTO lic_pays (id, nom) SELECT id, nom FROM __temp__lic_pays');
        $this->addSql('DROP TABLE __temp__lic_pays');
    }

    public function down(Schema $schema): void
    {
        // this down() migration is auto-generated, please modify it to your needs
        $this->addSql('ALTER TABLE lic_pays ADD COLUMN code VARCHAR(2) DEFAULT NULL');
    }
}

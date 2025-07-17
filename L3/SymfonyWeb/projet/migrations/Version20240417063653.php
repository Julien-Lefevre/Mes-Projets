<?php

declare(strict_types=1);

namespace DoctrineMigrations;

use Doctrine\DBAL\Schema\Schema;
use Doctrine\Migrations\AbstractMigration;

/**
 * Auto-generated Migration: Please modify to your needs!
 */
final class Version20240417063653 extends AbstractMigration
{
    public function getDescription(): string
    {
        return '';
    }

    public function up(Schema $schema): void
    {
        // this up() migration is auto-generated, please modify it to your needs
        $this->addSql('CREATE TEMPORARY TABLE __temp__lic_users AS SELECT id, pays_id, login, roles, password, nom, prenom, datenaissance FROM lic_users');
        $this->addSql('DROP TABLE lic_users');
        $this->addSql('CREATE TABLE lic_users (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, pays_id INTEGER DEFAULT NULL, login VARCHAR(180) NOT NULL, roles CLOB NOT NULL --(DC2Type:json)
        , password VARCHAR(255) NOT NULL, nom VARCHAR(100) NOT NULL, prenom VARCHAR(100) NOT NULL, datenaissance DATE NOT NULL, CONSTRAINT FK_6DD6320A6E44244 FOREIGN KEY (pays_id) REFERENCES lic_pays (id) ON UPDATE NO ACTION ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE)');
        $this->addSql('INSERT INTO lic_users (id, pays_id, login, roles, password, nom, prenom, datenaissance) SELECT id, pays_id, login, roles, password, nom, prenom, datenaissance FROM __temp__lic_users');
        $this->addSql('DROP TABLE __temp__lic_users');
        $this->addSql('CREATE INDEX IDX_6DD6320A6E44244 ON lic_users (pays_id)');
        $this->addSql('CREATE UNIQUE INDEX UNIQ_6DD6320AA08CB10 ON lic_users (login)');
    }

    public function down(Schema $schema): void
    {
        // this down() migration is auto-generated, please modify it to your needs
        $this->addSql('CREATE TEMPORARY TABLE __temp__lic_users AS SELECT id, pays_id, login, roles, password, nom, prenom, datenaissance FROM lic_users');
        $this->addSql('DROP TABLE lic_users');
        $this->addSql('CREATE TABLE lic_users (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, pays_id INTEGER NOT NULL, login VARCHAR(180) NOT NULL, roles CLOB NOT NULL --(DC2Type:json)
        , password VARCHAR(255) NOT NULL, nom VARCHAR(100) NOT NULL, prenom VARCHAR(100) NOT NULL, datenaissance DATE NOT NULL, CONSTRAINT FK_6DD6320A6E44244 FOREIGN KEY (pays_id) REFERENCES lic_pays (id) NOT DEFERRABLE INITIALLY IMMEDIATE)');
        $this->addSql('INSERT INTO lic_users (id, pays_id, login, roles, password, nom, prenom, datenaissance) SELECT id, pays_id, login, roles, password, nom, prenom, datenaissance FROM __temp__lic_users');
        $this->addSql('DROP TABLE __temp__lic_users');
        $this->addSql('CREATE UNIQUE INDEX UNIQ_6DD6320AA08CB10 ON lic_users (login)');
        $this->addSql('CREATE INDEX IDX_6DD6320A6E44244 ON lic_users (pays_id)');
    }
}

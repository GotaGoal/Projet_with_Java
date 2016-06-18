-- phpMyAdmin SQL Dump
-- version 4.4.10
-- http://www.phpmyadmin.net
--
-- Client :  localhost:8889
-- Généré le :  Ven 17 Juin 2016 à 20:40
-- Version du serveur :  5.5.42
-- Version de PHP :  7.0.0
SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";
--
-- Base de données :  `bd`
--
-- --------------------------------------------------------
--
-- Structure de la table `composition`
--
CREATE TABLE `composition` (
  `id_composition` int(11) NOT NULL,
  `x` int(11) DEFAULT NULL,
  `y` int(11) DEFAULT NULL,
  `id_element` int(11) DEFAULT NULL,
  `num_map` int(11) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
--
-- --------------------------------------------------------
--
-- Structure de la table `element`
--
CREATE TABLE `element` (
  `id_element` int(11) NOT NULL,
  `name_element` varchar(25) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
--
-- Contenu de la table `element`
--
INSERT INTO `element` (`id_element`, `name_element`) VALUES
(1, 'bone_v'),
(2, 'bone_h'),
(3, 'land'),
(4, 'stone');
-- --------------------------------------------------------
--
-- Structure de la table `map`
--
CREATE TABLE `map` (
  `num_map` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
--
--
-- Index pour les tables exportées
--
--
-- Index pour la table `composition`
--
ALTER TABLE `composition`
  ADD PRIMARY KEY (`id_composition`),
  ADD KEY `FK_composition_id_element` (`id_element`),
  ADD KEY `FK_composition_num_map` (`num_map`);
--
-- Index pour la table `element`
--
ALTER TABLE `element`
  ADD PRIMARY KEY (`id_element`);
--
-- Index pour la table `map`
--
ALTER TABLE `map`
  ADD PRIMARY KEY (`num_map`);
--
-- AUTO_INCREMENT pour les tables exportées
--
--
-- AUTO_INCREMENT pour la table `composition`
--
ALTER TABLE `composition`
  MODIFY `id_composition` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT pour la table `element`
--
ALTER TABLE `element`
  MODIFY `id_element` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3;
--
-- Contraintes pour les tables exportées
--
--
-- Contraintes pour la table `composition`
--
ALTER TABLE `composition`
  ADD CONSTRAINT `FK_composition_id_element` FOREIGN KEY (`id_element`) REFERENCES `element` (`id_element`),
  ADD CONSTRAINT `FK_composition_num_map` FOREIGN KEY (`num_map`) REFERENCES `map` (`num_map`);
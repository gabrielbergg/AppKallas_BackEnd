
CREATE TABLE `tasks` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name_service` varchar(255) DEFAULT NULL,
  `value` double DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK6s1ob9k4ihi75xbxe2w0ylsdh` (`user_id`),
  CONSTRAINT `FK6s1ob9k4ihi75xbxe2w0ylsdh` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
);
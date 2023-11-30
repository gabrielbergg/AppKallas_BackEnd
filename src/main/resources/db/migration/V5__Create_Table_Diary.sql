
CREATE TABLE `diary` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `time_appointment` datetime(6) DEFAULT NULL,
  `total_value` double DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  `client_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK74rd0bn5raxejw2ukenelbdmt` (`user_id`),
  CONSTRAINT `FK74rd0bn5raxejw2ukenelbdmt` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
);
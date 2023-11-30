
CREATE TABLE `user_login`(
    `id` bigint NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `login` varchar(255) UNIQUE KEY DEFAULT NULL,
    `role` enum('ADMIN','USER') NOT NULL,
    `password` varchar(255) DEFAULT NULL
)

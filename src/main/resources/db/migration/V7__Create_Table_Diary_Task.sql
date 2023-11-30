
CREATE TABLE `diary_task` (
  `diary_id` bigint NOT NULL,
  `task_id` bigint NOT NULL,
  PRIMARY KEY (`diary_id`,`task_id`),
  KEY `FKey9w6u2m1xbhftao52c2ncdij` (`task_id`),
  CONSTRAINT `FKey9w6u2m1xbhftao52c2ncdij` FOREIGN KEY (`task_id`) REFERENCES `tasks` (`id`),
  CONSTRAINT `FKeyb430lsxckva9v7vi8ke3ul1` FOREIGN KEY (`diary_id`) REFERENCES `diary` (`id`)
);
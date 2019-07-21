CREATE TABLE IF NOT EXISTS `Player`
(
    `id`                INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    `authentication_id` TEXT,
    `player`            TEXT,
    `scene_id`          INTEGER                           NOT NULL,
    FOREIGN KEY (`scene_id`) REFERENCES `Scene` (`id`) ON UPDATE NO ACTION ON DELETE RESTRICT
);

CREATE UNIQUE INDEX `index_Player_authentication_id` ON `Player` (`authentication_id`);

CREATE INDEX `index_Player_scene_id` ON `Player` (`scene_id`);

CREATE TABLE IF NOT EXISTS `Scene`
(
    `id`    INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    `scene` TEXT                              NOT NULL
);

CREATE TABLE IF NOT EXISTS `Input`
(
    `id`        INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    `name`      TEXT COLLATE NOCASE,
    `value`     INTEGER                           NOT NULL,
    `date`      INTEGER,
    `player_id` INTEGER                           NOT NULL,
    `scene_id`  INTEGER                           NOT NULL,
    FOREIGN KEY (`player_id`) REFERENCES `Player` (`id`) ON UPDATE NO ACTION ON DELETE CASCADE,
    FOREIGN KEY (`scene_id`) REFERENCES `Scene` (`id`) ON UPDATE NO ACTION ON DELETE CASCADE
);

CREATE INDEX `index_Input_name` ON `Input` (`name`);

CREATE INDEX `index_Input_player_id` ON `Input` (`player_id`);

CREATE INDEX `index_Input_scene_id` ON `Input` (`scene_id`);

CREATE TABLE IF NOT EXISTS `Choice`
(
    `id`            INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    `name`          TEXT COLLATE NOCASE,
    `from_scene_id` INTEGER                           NOT NULL,
    `to_scene_id`   INTEGER                           NOT NULL,
    FOREIGN KEY (`from_scene_id`) REFERENCES `Scene` (`id`) ON UPDATE NO ACTION ON DELETE CASCADE,
    FOREIGN KEY (`to_scene_id`) REFERENCES `Scene` (`id`) ON UPDATE NO ACTION ON DELETE CASCADE
);

CREATE UNIQUE INDEX `index_Choice_from_scene_id_name` ON `Choice` (`from_scene_id`, `name`);

CREATE INDEX `index_Choice_from_scene_id` ON `Choice` (`from_scene_id`);

CREATE INDEX `index_Choice_to_scene_id` ON `Choice` (`to_scene_id`);

CREATE TABLE IF NOT EXISTS `ChoiceSynonym`
(
    `id`        INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    `name`      TEXT,
    `choice_id` INTEGER                           NOT NULL,
    FOREIGN KEY (`choice_id`) REFERENCES `Choice` (`id`) ON UPDATE NO ACTION ON DELETE CASCADE
);

CREATE INDEX `index_ChoiceSynonym_choice_id` ON `ChoiceSynonym` (`choice_id`);




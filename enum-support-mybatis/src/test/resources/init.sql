CREATE TABLE `user`
(
    `id`  int(11),
    `sex` tinyint(1) NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `user2`
(
    `id`  int(11),
    `sex` int(1) NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `user3`
(
    `id`  int(11),
    `sex` varchar(1) NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `user4`
(
    `id`  int(11),
    `sex` char(1) NOT NULL,
    PRIMARY KEY (`id`)
);

insert into `user`
values (1, 1);
insert into `user`
values (2, 2);

insert into `user2`
values (1, 1);
insert into `user2`
values (2, 2);

insert into `user3`
values (1, 1);
insert into `user3`
values (2, 2);

insert into `user4`
values (1, 1);
insert into `user4`
values (2, 2);
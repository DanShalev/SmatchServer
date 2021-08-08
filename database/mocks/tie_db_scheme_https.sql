INSERT INTO groups_table (id, name, description, number_of_members, avatar)
VALUES (
           '1',
           'CS Study Buddies',
           'Description to be filled',
           '53 Members','https://images.unsplash.com/photo-1610563166150-b34df4f3bcd6?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=969&q=80'

       ),
       (
           '2',
           'Tel Aviv Flatmates',
           'Description to be filled',
           '8,745 Members','https://images.unsplash.com/photo-1615555237155-02682d979a31?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=387&q=80'

       ),
       (
           '3',
           'India Trip Partners',
           'Description to be filled',
           '546 Members',
           'https://images.unsplash.com/photo-1524492412937-b28074a5d7da?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=1051&q=80'
       ),

       (
           '4',
           'Personal Trainer',
           'Description to be filled',
           '546 Members',
           'https://images.unsplash.com/photo-1541534741688-6078c6bfb5c5?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1049&q=80'
       );


INSERT INTO `users_table` (`id`,`name`,`push_notification_token`, `sex`, `age`, `image1`, `image2`, `image3`)
VALUES ('1','Alex Gloss',NULL,'Male',26,'https://images.unsplash.com/photo-1517841905240-472988babdf9?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=1868&q=80', NULL, NULL),
       ('2','Lia Franco',NULL,'Female',23,'https://images.unsplash.com/photo-1529626455594-4ff0802cfb7e?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=334&q=80', NULL, NULL),
       ('3','Sandra Banks',NULL,'Female',23,'https://images.unsplash.com/photo-1488426862026-3ee34a7d66df?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=334&q=80', NULL, NULL),
       ('4','Rob Sanders',NULL,'Female',24,'https://images.unsplash.com/photo-1539571696357-5a69c17a67c6?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=334&q=80', NULL, NULL),
       ('5','Sandra Banks',NULL,'Female',31,'https://images.unsplash.com/photo-1563306406-e66174fa3787?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=334&q=80', NULL, NULL),
       ('6','Paula Garcia',NULL,'Female',29,'https://images.unsplash.com/photo-1484399172022-72a90b12e3c1?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=1050&q=80', NULL, NULL);


INSERT INTO `subscriptions` (`group_id`, `user_id`)
VALUES ('1', '1'),
       ('1', '2'),
       ('1', '3'),
       ('1', '4'),
       ('1', '5'),
       ('1', '6'),
       ('2', '6');

INSERT INTO `group_fields` (`group_id`, `field_id`, `field_name`)
VALUES ('1', 1, 'area'),
       ('1', 2, 'budget'),
       ('2', 1, 'area'),
       ('2', 2, 'budget'),
       ('3', 1, 'area'),
       ('3', 2, 'budget'),
       ('4', 1, 'area'),
       ('4', 2, 'budget');

INSERT INTO `matches_table` (`first_user_id`, `group_id`, `second_user_id`, `first_user_like`, `second_user_like`)
VALUES ('1', '1', '2', null, true),
       ('1', '1', '3', true, null),
       ('1', '1', '4', true, null),
       ('1', '2', '2', true, null),
       ('1', '2', '3', true, null),
       ('1', '2', '4', true, null),
       ('1', '2', '6', true, null),
       ('1', '2', '7', true, null);
INSERT INTO groups_table (id, name, description, number_of_members, avatar_url)
VALUES (
        '1',
        'CS Study Buddies',
        'Description to be filled',
        '53 Members',
        'https://materials.technion.ac.il/wp-content/uploads/2020/09/%D7%99%D7%A8%D7%93%D7%9F-%D7%95%D7%A0%D7%95%D7%99.jpg'
        ),
       (
        '2',
        'Tel Aviv Flatmates',
        'Description to be filled',
        '8,745 Members',
        'https://cf.bstatic.com/images/hotel/max1024x768/125/125231042.jpg'
        ),
       (
        '3',
        'India Trip Partners',
        'Description to be filled',
        '546 Members',
        'https://thecommonwealth.org/sites/default/files/styles/press_release_large/public/images/hero/taj-mahal-620.jpg?itok=PKSpaEMm'
        ),

       (
        '4',
        'Personal Trainer',
        'Description to be filled',
        '546 Members',
        'https://images2.minutemediacdn.com/image/upload/c_crop,h_1192,w_2119,x_0,y_0/f_auto,q_auto,w_1100/v1554734334/shape/mentalfloss/568122-istock-869062004.jpg'
        );


INSERT INTO `users_table` (`id`, `age`, `image_url`, `image_url2`, `image_url3`, `name`, `sex`)
VALUES ('1', 28,
        'https://images.generated.photos/Gi7-cTTPR4LFHJG6GWYCEz6u6LWQ-Bz1X-FbsKQtvnQ/rs:fit:512:512/wm:0.95:sowe:18:18:0.33/Z3M6Ly9nZW5lcmF0/ZWQtcGhvdG9zL3Yz/XzAwOTA2OTAuanBn.jpg',
        NULL, NULL, 'Dan Shalev', 'Male'),
       ('10', 36, 'https://4.bp.blogspot.com/-xRlv-wrImO8/TrfvcQTRLXI/AAAAAAAAAP0/ees0IdNzoCQ/s1600/heath.JPG',
        'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQwif6-XPFG6c016mDQAX8bro3Uq0s7_D-Usg&usqp=CAU', NULL,
        'The Gift', 'Male'),
       ('11', 26,
        'https://networthheightsalary.com/wp-content/uploads/2020/02/Kai-Greene-%E2%80%93-Bio-Height-Age-Net-Worth-Wife-Weight-Is-He-Gay-1200x900.jpg',
        'https://en.24smi.org/public/media/celebrity/2019/11/05/spliazqtfxta-kai-greene.jpg', NULL, 'Kai Greene',
        'Male'),
       ('12', 45, 'https://fitnessvolt.com/wp-content/uploads/2019/08/rich-piana-750x422.jpg', NULL, NULL, 'Rich',
        'Male'),
       ('2', 28,
        'https://images.generated.photos/Gi7-cTTPR4LFHJG6GWYCEz6u6LWQ-Bz1X-FbsKQtvnQ/rs:fit:512:512/wm:0.95:sowe:18:18:0.33/Z3M6Ly9nZW5lcmF0/ZWQtcGhvdG9zL3Yz/XzAwOTA2OTAuanBn.jpg',
        NULL, NULL, 'Itai Peled', 'Male'),
       ('3', 28,
        'https://images.generated.photos/Gi7-cTTPR4LFHJG6GWYCEz6u6LWQ-Bz1X-FbsKQtvnQ/rs:fit:512:512/wm:0.95:sowe:18:18:0.33/Z3M6Ly9nZW5lcmF0/ZWQtcGhvdG9zL3Yz/XzAwOTA2OTAuanBn.jpg',
        NULL, NULL, 'Guy Moshe', 'Female'),
       ('4', 28, 'Female', 'https://i.ibb.co/Tbd1V5Y/11350537-10206656141262671-8647580228424100134-n.jpg', NULL, NULL,
        'Elad Ben David'),
       ('5', 28,
        'https://images.generated.photos/Gi7-cTTPR4LFHJG6GWYCEz6u6LWQ-Bz1X-FbsKQtvnQ/rs:fit:512:512/wm:0.95:sowe:18:18:0.33/Z3M6Ly9nZW5lcmF0/ZWQtcGhvdG9zL3Yz/XzAwOTA2OTAuanBn.jpg',
        NULL, NULL, 'Fake user 1', 'Male'),
       ('6', 60,
        'https://images.generated.photos/Gi7-cTTPR4LFHJG6GWYCEz6u6LWQ-Bz1X-FbsKQtvnQ/rs:fit:512:512/wm:0.95:sowe:18:18:0.33/Z3M6Ly9nZW5lcmF0/ZWQtcGhvdG9zL3Yz/XzAwOTA2OTAuanBn.jpg',
        NULL, NULL, 'Fake user 2', 'Male'),
       ('7', 103,
        'https://images.generated.photos/Gi7-cTTPR4LFHJG6GWYCEz6u6LWQ-Bz1X-FbsKQtvnQ/rs:fit:512:512/wm:0.95:sowe:18:18:0.33/Z3M6Ly9nZW5lcmF0/ZWQtcGhvdG9zL3Yz/XzAwOTA2OTAuanBn.jpg',
        NULL, NULL, 'Fake user 3', 'Female'),
       ('8', 47,
        'https://images.generated.photos/Gi7-cTTPR4LFHJG6GWYCEz6u6LWQ-Bz1X-FbsKQtvnQ/rs:fit:512:512/wm:0.95:sowe:18:18:0.33/Z3M6Ly9nZW5lcmF0/ZWQtcGhvdG9zL3Yz/XzAwOTA2OTAuanBn.jpg',
        NULL, NULL, 'Fake user 4', 'Female'),
       ('9', 63, 'https://www.vindobona.org/images/text/arnold-schwarzenegger-1974-big.jpg',
        'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR_kEAvyWQzNH3dkEQ-sztZD73huybIjzAvEQ&usqp=CAU',
        'https://globalnews.ca/wp-content/uploads/2020/06/22098805.jpg?quality=85&strip=all', 'Arnold', 'Male');

INSERT INTO `subscriptions` (`group_id`, `user_id`)
VALUES ('1', '1'),
       ('2', '1'),
       ('3', '1'),
       ('4', '1'),
       ('4', '10'),
       ('4', '11'),
       ('4', '12'),
       ('1', '2'),
       ('2', '2'),
       ('1', '3'),
       ('3', '3'),
       ('1', '4'),
       ('3', '4'),
       ('4', '4'),
       ('3', '5'),
       ('2', '6'),
       ('2', '7'),
       ('3', '8'),
       ('4', '9');

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
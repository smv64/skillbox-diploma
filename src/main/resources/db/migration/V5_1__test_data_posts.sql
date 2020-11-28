INSERT INTO POSTS
(`is_active`,
`moderation_status`,
`moderator_id`,
`user_id`,
`time`,
`title`,
`text`,
`view_count`)
VALUES
(true,
'ACCEPTED',
(SELECT id from USERS where name = 'TEST_ADMIN1'),
(SELECT id from USERS where name = 'TEST_ADMIN1'),
current_timestamp,
'Active post',
'Text post',
0);

INSERT INTO POSTS
(`is_active`,
`moderation_status`,
`moderator_id`,
`user_id`,
`time`,
`title`,
`text`,
`view_count`)
VALUES
(true,
'ACCEPTED',
(SELECT id from USERS where name = 'TEST_ADMIN1'),
(SELECT id from USERS where name ='TEST_ADMIN1'),
(select adddate(current_timestamp, interval 1 year)),
'Feature post',
'Text post',
0);

INSERT INTO POSTS
(`is_active`,
`moderation_status`,
`moderator_id`,
`user_id`,
`time`,
`title`,
`text`,
`view_count`)
VALUES
(true,
'NEW',
(SELECT id from USERS where name = 'TEST_ADMIN1'),
(SELECT id from USERS where name ='TEST_ADMIN1'),
current_timestamp,
'New post',
'Text post',
0);

INSERT INTO POSTS
(`is_active`,
`moderation_status`,
`moderator_id`,
`user_id`,
`time`,
`title`,
`text`,
`view_count`)
VALUES
(false,
'ACCEPTED',
(SELECT id from USERS where name = 'TEST_ADMIN1'),
(SELECT id from USERS where name ='TEST_ADMIN1'),
current_timestamp,
'Not active post',
'Text post',
0);

INSERT INTO POST_VOTES
(`user_id`,
`post_id`,
`time`,
`value`)
VALUES
((SELECT id from USERS where name = 'TEST_USER1'),
(SELECT id from POSTS where title = 'Active post'),
current_timestamp,
1);

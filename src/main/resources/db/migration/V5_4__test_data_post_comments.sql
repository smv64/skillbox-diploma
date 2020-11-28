INSERT INTO POST_COMMENTS
(`parent_id`,
`post_id`,
`user_id`,
`time`,
`text`)
VALUES
(null,
(SELECT id from POSTS where title = 'Active post'),
(SELECT id from USERS where name = 'TEST_USER1'),
current_timestamp,
'comment :D');

CREATE VIEW V_POSTS as (
    SELECT
        id,
        is_active,
        moderation_status,
        moderator_id,
        user_id,
        time,
        title,
        text,
        view_count,
        coalesce(like_count, 0) as like_count,
        coalesce(dislike_count, 0) as dislike_count,
        coalesce(comment_count, 0) as comment_count
    FROM POSTS p
    LEFT OUTER JOIN (
        SELECT
            post_id,
            count(case when value = 1 then 1 end) as like_count,
            count(case when value = 0 then 1 end) as dislike_count
        FROM POST_VOTES
        GROUP BY post_id
    ) as post_likes
        on p.id = post_likes.post_id
    LEFT OUTER JOIN (
        SELECT
            post_id,
            count(id) as comment_count
        FROM POST_COMMENTS
        GROUP BY post_id
    ) post_comments
        on p.id = post_comments.post_id
);

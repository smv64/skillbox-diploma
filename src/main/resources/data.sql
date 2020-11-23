INSERT INTO GLOBAL_SETTINGS(code, name, value)
SELECT
    code, name, value
FROM (
    SELECT
        'MULTIUSER_MODE' as code,
        'Многопользовательский режим' as name,
        'NO' as value
    UNION ALL
    SELECT
        'POST_PREMODERATION' as code,
        'Премодерация постов' as name,
        'NO' as value
    UNION ALL
    SELECT
        'STATISTICS_IS_PUBLIC' as code,
        'Показывать всем статистику блога' as name,
        'NO' as value
) as init_values
WHERE code not in (
    SELECT code
    FROM GLOBAL_SETTINGS
);

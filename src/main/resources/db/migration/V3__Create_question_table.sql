create table question
(
-- 问题详情表
    id int auto_increment primary key,
    -- 标题
    title varchar(50),
    -- 问题内容
    description text,
    -- 校验
    gmt_create bigint,
    --
    gmt_modified bigint,
    -- 创建人
    creator int,
    comment_count int default 0,
    view_count int default 0,
    like_count int default 0,
    tag varchar(256)
);
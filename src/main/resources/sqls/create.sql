CREATE TABLE ck_likes (
  ID BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  like_author BIGINT NOT NULL DEFAULT '0' COMMENT '点赞者',
  like_post BIGINT NOT NULL DEFAULT '0' COMMENT '点赞文章',
  like_comment BIGINT NOT NULL DEFAULT '0' COMMENT '点赞评论',
  like_date DATETIME NOT NULL DEFAULT current_timestamp COMMENT '点赞时间',
  PRIMARY KEY (ID)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
CREATE TABLE ck_followers (
  ID BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  user_fan BIGINT NOT NULL DEFAULT '0' COMMENT '关注发起者，也就是粉丝',
  user_follower BIGINT NOT NULL DEFAULT '0' COMMENT '关注对象',
  follower_date DATETIME NOT NULL DEFAULT current_timestamp COMMENT '关注时间',
  follower_status VARCHAR(20) NOT NULL DEFAULT 'exist' COMMENT '关注状态',
  PRIMARY KEY (ID)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
CREATE TABLE ck_comments (
  ID BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  comment_author BIGINT UNSIGNED NOT NULL DEFAULT '0' COMMENT '评论者',
  comment_post BIGINT UNSIGNED NOT NULL DEFAULT '0' COMMENT '文章',
  comment_date DATETIME NOT NULL DEFAULT current_timestamp COMMENT '评论时间',
  comment_content TEXT NOT NULL COMMENT '评论',
  comment_parent BIGINT NOT NULL DEFAULT '0',
  comment_likes INT NOT NULL DEFAULT '0',
  PRIMARY KEY (ID)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
CREATE TABLE ck_posts (
  ID BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  post_author BIGINT UNSIGNED NOT NULL DEFAULT '0' COMMENT '文章作者',
  post_date DATETIME NOT NULL DEFAULT current_timestamp COMMENT '文章创建时间',
  post_modified DATETIME NOT NULL DEFAULT current_timestamp COMMENT '文章修改时间',
  post_content LONGTEXT NOT NULL COMMENT '文章内容',
  post_title TEXT NOT NULL COMMENT '文章标题',
  post_excerpt TEXT NOT NULL COMMENT '摘录',
  post_status VARCHAR(20) NOT NULL DEFAULT 'publish' COMMENT '文章状态',
  comment_status VARCHAR(20) NOT NULL DEFAULT 'open' COMMENT '评论状态',
  post_type VARCHAR(20) NOT NULL DEFAULT 'post' COMMENT '文章类型',
  post_mime_type VARCHAR(20) NOT NULL DEFAULT '' COMMENT '文件类型',
  post_label INT UNSIGNED NOT NULL DEFAULT '0' COMMENT '文章标签',
  post_watchs INT UNSIGNED NOT NULL DEFAULT '0' COMMENT '浏览数',
  post_likes INT UNSIGNED NOT NULL DEFAULT '0' COMMENT '点赞数',
  post_comments INT UNSIGNED NOT NULL DEFAULT '0' COMMENT '评论数',
  PRIMARY KEY (ID)
)ENGINE=InnoDB CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
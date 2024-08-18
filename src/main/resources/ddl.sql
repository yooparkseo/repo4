-- 사용자 테이블 생성
CREATE TABLE t_user
(
    userId     INT AUTO_INCREMENT PRIMARY KEY,
    username   VARCHAR(50)  NOT NULL UNIQUE,
    password   VARCHAR(255) NOT NULL,
    name       VARCHAR(50),
    email      VARCHAR(100) NOT NULL,
    copNm      VARCHAR(100),
    deptNm     VARCHAR(100),
    role       VARCHAR(20)  NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    use_yn     CHAR      default 'Y'
);

-- 게시판 테이블 생성
CREATE TABLE t_board
(
    boardId         INT AUTO_INCREMENT PRIMARY KEY,
    username        VARCHAR(50) NOT NULL,
    title           VARCHAR(100) NOT NULL,
    content         TEXT         NOT NULL,
    views           INT          DEFAULT 0,
    isLocked        BOOLEAN      DEFAULT FALSE,
    viewPermission  VARCHAR(50)  DEFAULT 'public', -- 'public', 'private', 'restricted' 등의 값 사용 가능
    hasFile         BOOLEAN      DEFAULT FALSE,
    likes           INT          DEFAULT 0, -- 좋아요 개수 카운트
    type            VARCHAR(50), -- 게시글 유형 notice, board, all
    created_at      TIMESTAMP    DEFAULT CURRENT_TIMESTAMP,
    updated_at      TIMESTAMP    DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    use_yn          CHAR(1)      DEFAULT 'Y', -- 사용 여부
    FOREIGN KEY (username) REFERENCES t_user (username)
);


-- 게시판 댓글 생성
CREATE TABLE t_comment
(
    commentId       INT AUTO_INCREMENT PRIMARY KEY,
    boardId         INT  NOT NULL,
    username        VARCHAR(50) NOT NULL,
    content         TEXT NOT NULL,
    views           INT  DEFAULT 0,
    isLocked        BOOLEAN DEFAULT FALSE,
    viewPermission  VARCHAR(50) DEFAULT 'public', -- 'public', 'private', 'restricted' 등의 값 사용 가능
    hasFile         BOOLEAN DEFAULT FALSE,
    likes           INT DEFAULT 0, -- 좋아요 개수 카운트
    type            VARCHAR(50), -- 댓글 유형
    created_at      TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at      TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    use_yn          CHAR(1) DEFAULT 'Y', -- 사용 여부
    FOREIGN KEY (boardId) REFERENCES t_board (boardId),
    FOREIGN KEY (username) REFERENCES t_user (username)
);

-- 이미지 테이블 생성
CREATE TABLE t_image
(
    imageId    INT AUTO_INCREMENT PRIMARY KEY,
    boardId    INT          NOT NULL,
    filePath   VARCHAR(255) NOT NULL,
    fileName   VARCHAR(100) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (boardId) REFERENCES t_board (boardId)
);


-- 더미 데이터
INSERT INTO chami_dev.t_user (username, password, name, email, copNm, deptNm, role, use_yn)
VALUES
    ('user1', 'password1', 'User One', 'user1@example.com', 'Company A', 'Department A', 'ROLE_USER', 'Y'),
    ('user2', 'password2', 'User Two', 'user2@example.com', 'Company B', 'Department B', 'ROLE_USER', 'Y'),
    ('user3', 'password3', 'User Three', 'user3@example.com', 'Company C', 'Department C', 'ROLE_USER', 'Y'),
    ('user4', 'password4', 'User Four', 'user4@example.com', 'Company D', 'Department D', 'ROLE_USER', 'Y'),
    ('user5', 'password5', 'User Five', 'user5@example.com', 'Company E', 'Department E', 'ROLE_USER', 'Y');

INSERT INTO chami_dev.t_board (userId, title, content)
VALUES
    (1, 'Title 1', 'Content for board 1'),
    (2, 'Title 2', 'Content for board 2'),
    (3, 'Title 3', 'Content for board 3'),
    (4, 'Title 4', 'Content for board 4'),
    (5, 'Title 5', 'Content for board 5');

INSERT INTO chami_dev.t_comment (boardId, userId, content)
VALUES
    (1, 1, 'Comment 1 on board 1 by user 1'),
    (2, 2, 'Comment 2 on board 2 by user 2'),
    (3, 3, 'Comment 3 on board 3 by user 3'),
    (4, 4, 'Comment 4 on board 4 by user 4'),
    (5, 5, 'Comment 5 on board 5 by user 5');

INSERT INTO chami_dev.t_image (boardId, filePath, fileName)
VALUES
    (1, '/images/board1/', 'image1.jpg'),
    (2, '/images/board2/', 'image2.jpg'),
    (3, '/images/board3/', 'image3.jpg'),
    (4, '/images/board4/', 'image4.jpg'),
    (5, '/images/board5/', 'image5.jpg');

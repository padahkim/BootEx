INSERT INTO article(id, title, content) VALUES (1, '가가가가', '1111');
INSERT INTO article(id, title, content) VALUES (2, 'nanana', '2222');
INSERT INTO article(id, title, content) VALUES (3, 'dadada', '3333');

-- article 더미 데이터
INSERT INTO article(id, title, content) VALUES (4, '당신의 인생영화는', '댓글ㄱ');
INSERT INTO article(id, title, content) VALUES (5, '단신의 소울푸트', '댓글ㄱㄱ');
INSERT INTO article(id, title, content) VALUES (6, '당신의 취미', '댓글ㄲㄱㄱ');

--comment 더미 데이터
INSERT INTO comment(id, article_id, nickname, body) VALUES (1,4, 'Park', '굿 윌 헌팅');
INSERT INTO comment(id, article_id, nickname, body) VALUES (2,4, 'Kim', '아이 엠 샘');
INSERT INTO comment(id, article_id, nickname, body) VALUES (3,4, 'Choi', '쇼생크탈출');

INSERT INTO comment(id, article_id, nickname, body) VALUES (4,5, 'Park', '칰');
INSERT INTO comment(id, article_id, nickname, body) VALUES (5,5, 'Kim', 'ㅅㅄㅂ');
INSERT INTO comment(id, article_id, nickname, body) VALUES (6,5, 'Choi', 'ㅊㅂ');

INSERT INTO comment(id, article_id, nickname, body) VALUES (7,6, 'Park', '조깅');
INSERT INTO comment(id, article_id, nickname, body) VALUES (8,6, 'Kim', '유튭');
INSERT INTO comment(id, article_id, nickname, body) VALUES (9,6, 'Choi', '난파');
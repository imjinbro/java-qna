INSERT INTO USER (CREATED_DATE, MODIFIED_DATE, EMAIL, NAME, PASSWD, USER_ID) VALUES (CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 'colin@codesquad.kr', 'colin', '1234', 'colin');
INSERT INTO USER (CREATED_DATE, MODIFIED_DATE, EMAIL, NAME, PASSWD, USER_ID) VALUES (CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 'jinbro@codesquad.kr', 'jinbro', '1234', 'jinbro');

INSERT INTO QUESTION (CREATED_DATE, MODIFIED_DATE, CONTENTS, COUNT_OF_ANSWER, DELETED, TITLE, USER_ID) VALUES (CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), '안뇽',  0, FALSE, '랄랄라', 1);
INSERT INTO QUESTION (CREATED_DATE, MODIFIED_DATE, CONTENTS, COUNT_OF_ANSWER, DELETED, TITLE, USER_ID) VALUES (CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 'ㅎㅇ',  0, FALSE, 'ㅎㅇㅂㅇ', 1);

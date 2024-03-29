CREATE TABLE META_GAME_LIST (
  NAME                        VARCHAR(120) NOT NULL,
  PLATFORM                    VARCHAR(20) NOT NULL,
  META_SCORE                  INT NOT NULL,
  USER_SCORE                  FLOAT NOT NULL,
  GAME_INFO_LINK              VARCHAR(250),
  NORMALIZED_NAME             VARCHAR(120) NOT NULL
);

CREATE INDEX META_GAME_LIST_1 ON META_GAME_LIST (NORMALIZED_NAME);
CREATE INDEX META_GAME_LIST_2 ON META_GAME_LIST (PLATFORM, NORMALIZED_NAME);
CREATE INDEX META_GAME_LIST_3 ON META_GAME_LIST (PLATFORM, META_SCORE, USER_SCORE, NORMALIZED_NAME);
CREATE INDEX META_GAME_LIST_4 ON META_GAME_LIST (PLATFORM, USER_SCORE, META_SCORE, NORMALIZED_NAME);

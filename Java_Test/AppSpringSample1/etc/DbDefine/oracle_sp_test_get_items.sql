CREATE OR REPLACE PROCEDURE TEST_GET_ITEMS(
  itemName IN VARCHAR2,
  itemList OUT TYPES.cursorType)
AS 
BEGIN
  OPEN itemList FOR
    SELECT * FROM TEST_ITEM WHERE ITEM_NAME = itemName;
END;

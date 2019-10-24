CREATE TABLE URA_CONDOTRANS_HISTORY (
  PROJECT_NAME                VARCHAR(100) NOT NULL,
  STREET_NAME	              VARCHAR(100) NOT NULL,
  PROPERTY_TYPE               VARCHAR(50) NOT NULL,
  POSTAL_DISTRICT             INT NOT NULL,
  MARKET_SEGMENT              VARCHAR(5) NOT NULL,
  TENURE                      VARCHAR(50) NOT NULL,
  SALE_TYPE                   VARCHAR(20) NOT NULL,
  UNITS                       INT NOT NULL,
  PRICE                       INT NOT NULL,
  FLOOR_AREA                  INT NOT NULL,
  AREA_TYPE                   VARCHAR(20) NOT NULL,
  FLOOR_LEVEL                 VARCHAR(20) NOT NULL,
  PRICE_PSM                   INT NOT NULL,
  SALE_DATE                   VARCHAR(20) NOT NULL,
  TENURE_YEAR                 VARCHAR(10) NOT NULL,
  FLOOR_AREA_LOWER            INT NOT NULL,
  FLOOR_AREA_UPPER            INT NOT NULL
);

CREATE INDEX URA_CONDOTRANS_HISTORY_1 ON URA_CONDOTRANS_HISTORY (POSTAL_DISTRICT, PROJECT_NAME, PRICE);
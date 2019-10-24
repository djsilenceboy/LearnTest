CREATE TABLE URA_CONDORENT_HISTORY (
  PROJECT_NAME                VARCHAR(100) NOT NULL,
  STREET_NAME	              VARCHAR(100) NOT NULL,
  POSTAL_DISTRICT             INT NOT NULL,
  PROPERTY_TYPE               VARCHAR(50) NOT NULL,
  BEDROOM_NUM                 INT NOT NULL,
  MONTHLY_GROSS_RENT          INT NOT NULL,
  FLOOR_AREA                  VARCHAR(20) NOT NULL,
  LEASE_DATE                  VARCHAR(20) NOT NULL,
  YEARLY_GROSS_RENT           INT NOT NULL,
  FLOOR_AREA_LOWER            INT NOT NULL,
  FLOOR_AREA_UPPER            INT NOT NULL
);

CREATE INDEX URA_CONDORENT_HISTORY_1 ON URA_CONDORENT_HISTORY (POSTAL_DISTRICT, PROJECT_NAME, FLOOR_AREA, MONTHLY_GROSS_RENT);
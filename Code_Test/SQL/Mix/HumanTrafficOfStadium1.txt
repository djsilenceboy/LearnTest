================================================================================
https://leetcode.com/problems/human-traffic-of-stadium/description/
================================================================================
Test
------------------------------------------------------------
Accepted.

SELECT *
FROM stadium d
WHERE (d.people >= 100)
      AND (d.id IN (
SELECT a.id AS Id
FROM stadium a, stadium b, stadium c
WHERE (a.people >= 100)
      AND (b.people >= 100)
      AND (c.people >= 100)
      AND (b.id = a.id + 1)
      AND (c.id = a.id + 2)
UNION
SELECT b.id AS Id
FROM stadium a, stadium b, stadium c
WHERE (a.people >= 100)
      AND (b.people >= 100)
      AND (c.people >= 100)
      AND (b.id = a.id + 1)
      AND (c.id = a.id + 2)
UNION
SELECT c.id AS Id
FROM stadium a, stadium b, stadium c
WHERE (a.people >= 100)
      AND (b.people >= 100)
      AND (c.people >= 100)
      AND (b.id = a.id + 1)
      AND (c.id = a.id + 2)
))
ORDER BY d.id;
================================================================================
Other solutions
------------------------------------------------------------
SELECT DISTINCT a.id, a.date, a.people
FROM stadium AS a, stadium AS b, stadium as c
WHERE (((a.id + 1 = b.id) AND (a.id + 2 = c.id))
      OR ((a.id - 1 = b.id) AND (a.id + 1 = c.id))
      OR ((a.id - 2 = b.id) AND (a.id - 1 = c.id)))
      AND (a.people >= 100)
      AND (b.people >= 100)
      AND (c.people >= 100)
ORDER BY a.id;
================================================================================

================================================================================
https://leetcode.com/problems/trips-and-users/description/
================================================================================
Test
------------------------------------------------------------
Accepted.

SELECT e.Request_at AS Day, ROUND(IFNULL(f.Counter, 0) / e.Counter, 2) AS 'Cancellation Rate'
FROM
(SELECT a.Request_at, COUNT(*) AS Counter
 FROM Trips a, Users b
 WHERE (DATE(a.Request_at) BETWEEN '2013-10-01' AND '2013-10-03')
       AND (a.Client_Id = b.Users_Id)
       AND (b.Role = 'client')
       AND (b.Banned = 'No')
 GROUP BY a.Request_at) e
LEFT JOIN
(SELECT c.Request_at, COUNT(*) AS Counter
 FROM Trips c, Users d
 WHERE (DATE(c.Request_at) BETWEEN '2013-10-01' AND '2013-10-03')
       AND (c.Status IN ('cancelled_by_driver', 'cancelled_by_client'))
       AND (c.Client_Id = d.Users_Id)
       AND (d.Role = 'client')
       AND (d.Banned = 'No')
 GROUP BY c.Request_at) f
ON (e.Request_at = f.Request_at)
ORDER BY e.Request_at;
------------------------------------------------------------
Accepted. Better.

SELECT a.Request_at AS Day, ROUND(SUM(CASE WHEN a.Status IN ('cancelled_by_driver', 'cancelled_by_client') THEN 1 ELSE 0 END) / COUNT(*), 2) AS 'Cancellation Rate'
FROM Trips a, Users b
WHERE (DATE(a.Request_at) BETWEEN '2013-10-01' AND '2013-10-03')
      AND (a.Client_Id = b.Users_Id)
      AND (b.Role = 'client')
      AND (b.Banned = 'No')
GROUP BY a.Request_at
ORDER BY a.Request_at;
================================================================================
Other solutions
------------------------------------------------------------
================================================================================

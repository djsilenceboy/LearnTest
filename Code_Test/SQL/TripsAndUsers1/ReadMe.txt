================================================================================
https://leetcode.com/problems/trips-and-users/description/
================================================================================
Test
------------------------------------------------------------
Accepted.

SELECT e.Request_at, e.Count / f.Count AS 'Cancellation Rate'
(SELECT a.Request_at, COUNT(*) AS Counter
FROM Trips a, Users b
WHERE (a.Request_at BETWEEN '2013-10-01' AND '2013-10-03')
      AND (a.Status IN ('cancelled_by_driver', 'cancelled_by_client'))
      AND (a.Client_Id = b.User_Id)
      AND (b.Role = 'client')
      AND (NOT b.Banned)
GROUP BY a.Request_at
ORDER BY a.Request_at) e,
(SELECT c.Request_at, COUNT(*) AS Counter
FROM Trips c, Users d
WHERE (c.Request_at BETWEEN '2013-10-01' AND '2013-10-03')
      AND (c.Client_Id = d.User_Id)
      AND (d.Role = 'client')
      AND (NOT d.Banned)
GROUP BY c.Request_at
ORDER BY c.Request_at) f
WHERE (e.Request_at = f.Request_at)
ORDER BY e.Request_at;
================================================================================
Other solutions
------------------------------------------------------------
================================================================================

================================================================================
https://leetcode.com/problems/nth-highest-salary/description/
================================================================================
Test
------------------------------------------------------------
Accepted.

CREATE FUNCTION getNthHighestSalary(N INT) RETURNS INT
BEGIN
  RETURN (
SELECT DISTINCT Salary
FROM Employee a
WHERE ((SELECT COUNT(*)
        FROM (SELECT DISTINCT Salary FROM Employee) b
        WHERE (b.Salary > a.Salary)) = N - 1)
  );
END
------------------------------------------------------------
Accepted.

SELECT DISTINCT Salary
FROM Employee a
WHERE ((SELECT COUNT(*)
        FROM (SELECT DISTINCT Salary FROM Employee) b
        WHERE (b.Salary > a.Salary)
        ORDER BY Salary) = N - 1)
================================================================================
Other solutions
------------------------------------------------------------
SELECT DISTINCT Salary
FROM Employee a
WHERE ((SELECT COUNT(DISTINCT b.Salary)
        FROM Employee b
        WHERE (b.Salary > a.Salary)) = N - 1)
------------------------------------------------------------
SELECT a.Salary
FROM (SELECT DISTINCT Salary FROM Employee) a
WHERE ((SELECT COUNT(*)
        FROM (SELECT DISTINCT Salary FROM Employee) b
        WHERE (b.Salary > a.Salary)) = N - 1)
------------------------------------------------------------
SELECT a.Salary
FROM Employee a
     JOIN Employee b ON (b.Salary >= a.Salary)
GROUP BY a.Salary
HAVING COUNT(DISTINCT b.Salary) = N
================================================================================

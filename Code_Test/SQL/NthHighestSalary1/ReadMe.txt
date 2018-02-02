================================================================================
https://leetcode.com/problems/nth-highest-salary/description/
================================================================================
Test
------------------------------------------------------------
CREATE FUNCTION getNthHighestSalary(N INT) RETURNS INT
BEGIN
  RETURN (
SELECT Salary
FROM Employee a
WHERE ((SELECT COUNT(*)
       FROM Employee b
       WHERE (b.Salary > a.Salary)) = N - 1)
  );
END
================================================================================
Other solutions
------------------------------------------------------------
================================================================================

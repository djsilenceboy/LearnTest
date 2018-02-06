================================================================================
https://leetcode.com/problems/department-top-three-salaries/description/
================================================================================
Test
------------------------------------------------------------
Accepted.

SELECT a.Name AS Department, b.Name AS Employee, b.Salary
FROM Department a
     JOIN Employee b ON (a.Id = b.DepartmentId)
WHERE ((SELECT COUNT(DISTINCT Salary)
        FROM Department c
             JOIN Employee d ON (c.Id = d.DepartmentId)
        WHERE (a.Name = c.Name)
              AND (b.Salary < d.Salary)) < 3)
ORDER BY a.Name, b.Salary DESC;
------------------------------------------------------------
Accepted.

SELECT a.Name AS Department, b.Name AS Employee, b.Salary
FROM Department a
     JOIN Employee b ON (a.Id = b.DepartmentId)
WHERE ((SELECT COUNT(DISTINCT Salary)
        FROM Employee c
        WHERE (b.DepartmentId = c.DepartmentId)
              AND (b.Salary < c.Salary)) < 3)
ORDER BY a.Name, b.Salary DESC;
================================================================================
Other solutions
------------------------------------------------------------
================================================================================

To find the **2nd highest salary** from an `Employee` table in SQL, you can use multiple approaches.

---

### ✅ **Approach 1: Using `LIMIT` with `ORDER BY` (MySQL, PostgreSQL)**
```sql
SELECT DISTINCT salary 
FROM Employee 
ORDER BY salary DESC 
LIMIT 1 OFFSET 1;
```
🔹 **Explanation**:
- `ORDER BY salary DESC` → Sorts salaries in descending order.
- `LIMIT 1 OFFSET 1` → Skips the highest salary and selects the next one.

---

### ✅ **Approach 2: Using `SUBQUERY` (Works in All SQL Databases)**
```sql
SELECT MAX(salary) AS SecondHighestSalary 
FROM Employee 
WHERE salary < (SELECT MAX(salary) FROM Employee);
```
🔹 **Explanation**:
- First, find the highest salary using `MAX(salary)`.
- Then, find the highest salary **excluding the maximum salary**.

---

### ✅ **Approach 3: Using `DENSE_RANK()` (Best for Handling Duplicates)**
```sql
SELECT salary 
FROM (
    SELECT salary, DENSE_RANK() OVER (ORDER BY salary DESC) AS rnk 
    FROM Employee
) ranked 
WHERE rnk = 2;
```
🔹 **Explanation**:
- `DENSE_RANK()` assigns a rank to each salary **without skipping ranks for duplicates**.
- We filter for `rnk = 2` to get the **second highest unique salary**.

---

### ✅ **Which Approach to Use?**
| Approach | Works in | Handles Duplicates? | Performance |
|----------|---------|----------------|------------|
| `LIMIT OFFSET` | MySQL, PostgreSQL | ❌ No | ✅ Fast |
| `MAX() with Subquery` | All SQL Databases | ❌ No | ✅ Fast |
| `DENSE_RANK()` | SQL Server, PostgreSQL, Oracle | ✅ Yes | 🔄 Medium |

---

### 🚀 **Next Steps**
Would you like a solution that also works when there is **only one distinct salary** (handling `NULL` cases)? 🤔

### **🔥 Popular SQL Interview Queries**

SQL interviews often test problem-solving, performance optimization, and analytical thinking. Below are the **most common SQL interview queries**, categorized by difficulty.

---

## **✅ Basic SQL Queries**
1️⃣ **Find all employees with a salary greater than 50,000**
```sql
SELECT * FROM Employee WHERE salary > 50000;
```

2️⃣ **Find the count of employees in each department**
```sql
SELECT department, COUNT(*) AS employee_count 
FROM Employee 
GROUP BY department;
```

3️⃣ **Retrieve all employees who joined in the year 2023**
```sql
SELECT * FROM Employee WHERE YEAR(joining_date) = 2023;
```

4️⃣ **Find employees with duplicate salaries**
```sql
SELECT salary, COUNT(*) 
FROM Employee 
GROUP BY salary 
HAVING COUNT(*) > 1;
```

---

## **✅ Intermediate SQL Queries**
5️⃣ **Find the 2nd highest salary**
```sql
SELECT MAX(salary) 
FROM Employee 
WHERE salary < (SELECT MAX(salary) FROM Employee);
```

6️⃣ **Get the top 3 highest salaries in the company**
```sql
SELECT DISTINCT salary 
FROM Employee 
ORDER BY salary DESC 
LIMIT 3;
```

7️⃣ **Find employees who do not belong to any department**
```sql
SELECT * FROM Employee WHERE department_id IS NULL;
```

8️⃣ **Find employees who have the same salary as "John"**
```sql
SELECT * FROM Employee WHERE salary = (SELECT salary FROM Employee WHERE name = 'John');
```

---

## **✅ Advanced SQL Queries**
9️⃣ **Find the department with the highest average salary**
```sql
SELECT department, AVG(salary) AS avg_salary 
FROM Employee 
GROUP BY department 
ORDER BY avg_salary DESC 
LIMIT 1;
```

🔟 **Find employees who earn more than their manager**
```sql
SELECT e.name AS Employee, e.salary, m.name AS Manager, m.salary AS ManagerSalary
FROM Employee e 
JOIN Employee m ON e.manager_id = m.id
WHERE e.salary > m.salary;
```

1️⃣1️⃣ **Find employees with the longest tenure (earliest joining date)**
```sql
SELECT * FROM Employee ORDER BY joining_date ASC LIMIT 1;
```

1️⃣2️⃣ **Find the cumulative salary of each department**
```sql
SELECT department, SUM(salary) AS total_salary 
FROM Employee 
GROUP BY department;
```

1️⃣3️⃣ **Find the running total of salaries ordered by hire date**
```sql
SELECT name, salary, SUM(salary) OVER (ORDER BY joining_date) AS running_total 
FROM Employee;
```

---

## **✅ Complex Queries (Analytical & Window Functions)**
1️⃣4️⃣ **Rank employees by salary within each department**
```sql
SELECT name, department, salary, 
       RANK() OVER (PARTITION BY department ORDER BY salary DESC) AS rank 
FROM Employee;
```

1️⃣5️⃣ **Find employees who have a higher salary than the company average**
```sql
SELECT * FROM Employee 
WHERE salary > (SELECT AVG(salary) FROM Employee);
```

1️⃣6️⃣ **Find the highest salary in each department**
```sql
SELECT department, MAX(salary) AS highest_salary 
FROM Employee 
GROUP BY department;
```

1️⃣7️⃣ **Find the employees who joined in the last 3 months**
```sql
SELECT * FROM Employee WHERE joining_date >= DATE_SUB(CURDATE(), INTERVAL 3 MONTH);
```

1️⃣8️⃣ **Find the employee who gets the nth highest salary (Example: 5th highest salary)**
```sql
SELECT salary FROM (
    SELECT salary, DENSE_RANK() OVER (ORDER BY salary DESC) AS rnk 
    FROM Employee
) ranked WHERE rnk = 5;
```

---

## **💡 Bonus: SQL Optimization Techniques**
- Use **indexes** on frequently searched columns (`CREATE INDEX idx_emp_salary ON Employee(salary)`)
- Avoid `SELECT *` → Always specify required columns
- Use **JOINs instead of subqueries** for better performance
- Use `EXPLAIN` before running complex queries to analyze performance
- Use **partitioning** for large datasets to improve query speed

---

### **🚀 Next Steps**
Would you like **real-world SQL case studies** or **performance tuning techniques**? 🔥



## Questions
Create a table named HadoopExamint with two columns as (value int, property string), insert values between 1 to 10 also set the property whether they are odd or even and accomplish following activities.
1. Without using GROUP BY function calculate the count of even and odd numbers
2. Count odd and even number using group by function.
3. Write an analytical function, which can produce running count of even and odd numbers.
4. Write a query for moving window: 1 row before current row and 2 row after current row.

## Solutions
### Q1
Using Impala-shell
~~~
Drop table if already exist HadoopExamInt;
~~~
Create the table
~~~
CREATE TABLE HadoopExamInt (value int, property string);
~~~
Insert values in it.
~~~
insert overwrite HadoopExamInt values (1,'odd'),(2,'even'),(3,'odd'),(4,'even'),(5,odd'),(6,'even'),(7,'odd'),(8,'even'),(9,'odd'),(10,'even');
~~~
Check value inserted in it or not
~~~
Select * from HadoopExamInt;
~~~
count without using group by
~~~
select value, property, count(value) over (partition by property) as count from HadoopExamInt where property in ('odd','even');
~~~
### Q2
~~~
select count(*) from HadoopExamint group by property;
~~~
or
~~~
select property, count(property) from HadoopExamint group by property;
~~~
### Q3
~~~
select value, property, count(value) over (partition by property order by value) as 'cumulative count' from HadoopExamInt where property in ('odd','even');  
~~~
### Q4
~~~
select value, property.
count(value) over
(
partition by property
order by value
rows between 1 preceding and 2 following
) as 'Total count'
from HadoopExamInt where property in ('oddVeven');
~~~
Notes: The above query show how to construct a moving window, with a running count taking into account 1 row before and 2 row after the current row, within the same partition (all the even values or all the odd values). Therefore, the count is consistently 4 for rows in the middle of the window, and 3 and 2 for rows near the ends of the window, where there is no preceding or no following row in the partition. Because of a restriction in the Impala RANGE syntax, this type of moving window is possible with the ROWS BETWEEN clause but not the RANGE BETWEEN clause.

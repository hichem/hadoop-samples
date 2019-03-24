## Q4
Create table in Hive/Impala, with below definitions.
HadoopExamint(x int, property string);
Then accomplish following activities ;
1. Insert some values and mention their property whether odd or even number.
2. Calculate average values for each property.
3. Calculate the Cumulative Average for each property.
4. Calculate the Cumulative Average for each property, considering all preceding rows till current rows.
5. Calculate moving average by considering 3 rows (1 previous, 1 Current and 1 following)

## Solutions
### Q1
~~~
CREATE TABLE HadoopExamint ( x INT, property string) ROW FORMAT DELIMITED FIELDS TERMINATED BY ',' LINES TERMINATED BY '\n' STORED AS TEXTFILE; 

INSERT OVERWRITE TABLE HadoopExamInt values (1,'odd'),(2,'even'),(3,'odd'),(4.'even'),(5,'odd'),(6,'even'),(7,'odd'),(8,'even'),(9,'odd'),(10,'even');

SELECT * from HadoopExamint;
~~~
### Q2
~~~
select x, property, avg(x) over (partition by property) as avg from HadoopExamInt where property in ('odd','even');
~~~
?? possible solution but not in the spirit of this exercise
~~~
select property, avg(x) from HadoopExamint group by property;
~~~
### Q3
~~~
select x, property,
avg(x) over (partition by property order by x) as 'cumulative average' 
from HadoopExamInt;
~~~
or 
~~~
select x, property,
avg(x) over (partition by property order by x) as 'cumulative average' 
from HadoopExamInt where property in ('odd','even');
~~~
### Q4
~~~
select x, property,
avg(x) over
(
partition by property
order by x
range between unbounded preceding and current row 
) as 'cumulative average'
from HadoopExamint where property in ('odd','even');
~~~

Across all properties
~~~
select x, property,avg(x) over
order by x
range between unbounded preceding and current row ) as 'cumulative average'
from HadoopExannInt;
~~~
### Q5
~~~
select x, property, avg(x) over
(
partition by property
order by x
rows between 1 preceding and 1 following
) as 'moving average'
from HadoopExamInt where property in ('odd','even');
~~~

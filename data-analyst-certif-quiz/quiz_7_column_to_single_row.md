## Question
You have given following table: 
HadoopExamint (value int, property string);
1. Please transform all the values from value column in a single row, separeted by "|"
2. Please transform odd and even values (separetly) from value column in a single row, separated by "|" also save this results in a table named HE1B.

## Solutions
### Q1
~~~
select group_concat( cast(value as string),"|") from HadoopExamInt;
~~~
### Q2
~~~
Create table HE1B(valuel string, value2 string);
INSERT OVERWRITE table HE1B select property, group_concat( cast(value as string),"|") from HadoopExamInt group by property;
Select* from HE1B;
~~~

## Questions
You have following table structure in hive/impala: Table Name: he_order_items; columns: order_item_id int order_item_product int order_item_quantity tinyint order_item_subtotal double order_item_product_price double

1. Calculate the minimum, maximum and average of order_item_product_price.
2. Now find approximately the median of 'order_item_product_price'.
3. Count the all values higher than appx_median value
4. Count the all values lower than appx_median value.
5. Calculate appx_median of order_item_product_price where order_item_product_price between 20 and 80

## Solutions

#### Q1 
~~~
select min(order_item_product_price), max(order_item_product_price), avg(order_item_product_price) from he_order_items;
~~~
#### Q2 
~~~
select appx_median(order_item_product_price) from he_order_items;
~~~
#### Q3 
~~~
select count(order_item_product_price) from he_order_items where order_item_product_price >= (select appx_median(order_item_product_price) from he_order_items);
~~~
#### Q4 
~~~
select count(order_item_product_price) from he_order_items where order_item_product_price < (select appx_median(order_item_product_price) from he_order_items);
~~~
#### Q5
~~~
select appx_median(order_item_product_price) from he_order_items where order_item_product_price between 20 and 80 
~~~

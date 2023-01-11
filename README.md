## Table of contents
* [General info](#general-info)
* [Technologies](#technologies)
* [Setup](#setup)

## General info
This project is about IKEA Products. In this project you need to develop a UI (console based) which will help the user to work on the dataset. Explore the dataset (especially the datatypes of columns). Yes/No and True/False values must be stored as Boolean whereas id, item id, price and the dimensions seem to be numbers. There should be menu and it should be interactive, as the user might want to perform some sequence of requests. For example, user might want to sort the entities based on the date (request 1) and pick top 20 entities to list (request 2). To increase the reusability, after all the requests or sub-requests, return a collection of results so that you can print them, or use it in some subsequent requests. The main functionalities the program needs to provide are the following:
1. List all the entities 
a. List randomly selected 20 entities
b. List top 20 entities
c. List bottom 20 entities
d. Once list method returns the selected entities the user should be able to choose to
display
i. all the fields of each entity.
ii. only the selected fields of each entity.
2. Sort the entities 
a. Based on any field
b. In any order (i.e., ASC, DESC)
c. Once the sort is over, the initial menu must be displayed to the user. If user needs to
sort again, or list the already sorted entities, it should be possible as well.
3. Search entities based on any given field and value 
a. string fields and values must be checked based on contains not exact equality.
b. non-string fields and values must be checked based on exact equality.
4. List column names 
5. Filter entities 
a. Based on any given field or set of fields and according to some rules:
b. string fields
i. contains
ii. null (or missing)
c. numeric fields
i. equal (eq)
ii. greater than (gt)
iii. less than (lt)
iv. greater and equal to (ge)
v. less and equal to (le)
vi. between (bt)
vii. null (or missing) d. boolean
i. equal (eq)
6. Export a whole list of designers (exclude those values which start with a code (e.g.,
002.756.74) these are not needed to be stored). Please also note that, each record might contain several designers as well (e.g., A Fredriksson/J Hultqvist/W Chong). They must be separated too.
7. Products must be separated by each category and stored in separate .csv files. (i.e., chairs.csv will hold only the records that belong to the chairs category: about 481 records). However, in the exported category files, there should not be a column category anymore.

After search and filter requests we always print the results on the console. Giving an extra option to the user to EXPORT the result in the form of a report to a
.csv file AFTER LISTING IT
		
## Setup
To run this project, install IKEA Products from this link: https://www.kaggle.com/datasets/thedevastator/ikea-product . 

```
$ cd ../lorem
$ npm install
$ npm start
```
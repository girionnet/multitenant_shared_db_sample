This is a sample demo project for multitenant shared db 
Application provides greeting message to user  based on tenantid and username provided in request. 

# Steps to make it working:
----------------------------------

1. Create database in mysql 
   create database multitnenat_db;
   
2. Update db connecction properties as your DB in application.properties 
   
3. Start the project 

4. Insert entries into Greeting table
INSERT INTO multitnenat_db.GREETING VALUES (1,'hello giri','giri','tenant1');
INSERT INTO multitnenat_db.GREETING VALUES (2,'hello hari','hari','tenant2');

Testing:
------------------ 

![image](https://user-images.githubusercontent.com/119192146/226094380-8828d863-0230-4fb3-899a-634c6787bcdc.png)


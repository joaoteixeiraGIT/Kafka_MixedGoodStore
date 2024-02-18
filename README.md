# Kafka_MixedGoodStore

This repository is the work that I did on University of Maribor in a course named Data Technologies and Services. It contains code and configuration files for setting up a comprehensive data pipeline that involves various data processing and analysis tasks using Kafka, ksqlDB, MapReduce, and more. The pipeline spans from data generation to real-time analytics.

# Project Steps
# 1. Entity Relationship Model (ERM)
Designed an Entity Relationship Model (ERM) to represent the structure of the data and its relationships.
# 2. Azure MySQL Database Setup
Created a MySQL database on Azure for storing data.
Configured access credentials and permissions for the database.
# 3. Database Modeling
Created database schema and tables using MySQL Workbench.
Implemented views by joining multiple tables to facilitate data analysis.
# 4. MapReduce Implementation
Developed a MapReduce job to process data from CSV files.
Implemented the OrderMapper class to extract supplier type, item cost, and item quantity from CSV files and calculate order cost.
Implemented the OrderReducer class to calculate the total cost for each supplier type.
Configured and ran the MapReduce job using the OrderProcessing class.
# 5. Kafka Integration
Utilized Kafka for streaming data ingestion and processing.
Configured topics using Offset Explorer 2 to manage data streams.
Developed a Kafka Producer to generate data corresponding to the database view.
Developed a Kafka Consumer to read and process data from Kafka topics.
6. Kafka Connect Setup
Configured Kafka Connect to integrate Kafka with the MySQL database.
Tested the integration using Postman to ensure data flow between Kafka and MySQL.
7. ksqlDB Implementation
Implemented real-time data processing and analytics using ksqlDB.
Created continuous queries to perform various tasks:
Count the number of records (orders) in real-time.
Calculate the total cost of orders for each supplier.
Identify orders with quantities greater than 100.

# 8. Docker Utilization
Leveraged Docker for containerization of services such as Kafka, ksqlDB, MySQL, and more.
Ensured consistent and reproducible environment setup across different machines.

# Tools Used
Visual Paradigm Online
Azure for creation of  MySQL Database
MySQL Workbench
IntelliJ IDEA
Offset Explorer 2
Postman
Apache Kafka
Kafka Connect
ksqlDB
Docker



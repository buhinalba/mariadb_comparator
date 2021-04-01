# MARIADB COMPARATOR

This maven application is written in Java, and connects to two mariadb schemas.

for now:
- database connection details have to be specified in environment variables
- schemas (it's names) are configured in the main function
- it looks for:
  - new tables
  - deleted tables
  - altered tables
    - new columns
    - deleted columns
    - altered columns


for the future:
  - make it flexible, create the possibility to use it with other relational database management systems 
  - improved UI and UX
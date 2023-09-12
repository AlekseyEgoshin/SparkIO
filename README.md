# SparkIO
Пример параметров:
JDBC -> JDBC
```
"source==
{
    "uploadType": "jdbc",
    "url": "jdbc:postgresql://localhost:5432/postgres",
    "login": "postgres",
    "password": "postgres",
    "schema": "public",
    "table":"postgres_inc_check",
    "driver": "org.postgresql.Driver"
}"
"target==
{
    "uploadType": "jdbc",
    "url": "jdbc:postgresql://localhost:5432/postgres",
    "login": "postgres",
    "password": "postgres",
    "schema": "public",
    "table":"test_upload",
    "driver": "org.postgresql.Driver"
}"
```

```
"source==
{
    "uploadType": "file",
    "path": "file:///E:/Aleksey/Desktop/ОБУЧАЕМСЯ/python/generator/data.csv",
    "filetype":"csv",
    "delimiter": ";",
    "format":""
}"
"target==
{
    "uploadType": "jdbc",
    "url": "jdbc:postgresql://localhost:5432/postgres",
    "login": "postgres",
    "password": "postgres",
    "schema": "public",
    "table":"test_upload",
    "driver": "org.postgresql.Driver"
}"
```

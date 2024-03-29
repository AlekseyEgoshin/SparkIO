# SparkIO
Описание программы:
1) Обработка входных параметров;
2) Подключение к источнику;
3) Получение данных из источника;
4) Обработка полученных данных;
5) Запись данных в приемник.


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
    "table":"generated_table",
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

FILE -> JDBC
```
"source==
{
    "uploadType": "file",
    "path": "file:///E:/Aleksey/Desktop/ОБУЧАЕМСЯ/python/generator/data.csv",
    "filetype": "csv",
    "delimiter": ";"
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

KAFKA -> JDBC
```
"source==
{
    "uploadType": "kafka",
    "servers": "localhost:29092,localhost:9092",
    "topic": "test"
}"
"target==
{
    "uploadType": "jdbc",
    "url": "jdbc:postgresql://localhost:5432/postgres",
    "login": "postgres",
    "password": "postgres",
    "schema": "public",
    "table":"test_kafka_upload",
    "driver": "org.postgresql.Driver"
}"
```

# Usage

```
Usage: app [OPTIONS]

Options:
  --count INT  Query count
  --uri TEXT   MongoDB connection string
  -h, --help   Show this message and exit
```

## Example

```
java -jar app.jar --uri "mongodb://localhost:27017,localhost:27018,localhost:27019/?readPreference=secondaryPreferred&replicaSet=replset"
```
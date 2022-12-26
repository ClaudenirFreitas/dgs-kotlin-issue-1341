# dgs-issue-1341

## Pre requisites

| Name    | Required |
|---------|----------|
| Java 11 | true     |


## Step by step 
1. Clone this repo
```
git clone https://github.com/ClaudenirFreitas/dgs-kotlin-issue-1341.git
```
2. Run the application
```
./gradlew clean bootRun
```
3. Open the GraphiQL page
```
http://localhost:8080/graphiql
```
4. Execute this query below
```
{
    shows {
        title
        releaseYear
    }
}
```
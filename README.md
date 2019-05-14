# Forex API

API for managing foreign exchange for currencies

## Running the API

### With Docker

1. [Setup enviroment variables](#setup-environment-variables)
2. Run `docker-compose up` to start app

### Run Locally

1. Install openjdk-8 and postgresql
2. [Setup environment variables](#setup-environment-variables)
3. Run `gradlew build` then `gradlew test`
4. Run `gradlew bootRun` to start app

## API Reference

App will be running on **port 8080**. For more API details, see [here](). 

### Currency Pair

```sh
GET     /api/v1/currency-pairs/
POST    /api/v1/currency-pairs/
DELETE  /api/v1/currency-pairs/{id}
```

### Exchange Rate

```sh
GET   /api/v1/exchange-rates/
POST  /api/v1/exchange-rates/
GET   /api/v1/exchange-rates/track/
GET   /api/v1/exchange-rates/trend/
```

## Database Design

![Database Design](img/dbdesign.png)

There are two relations, currency_pair and exchange_rate. Relation currency_pair stores base and quote currency while relation exchange_rate stores exchange rate with specific date and currency pair (foreign key from relation currency_pair). 

## Setup Environment Variables

Environment variables contain database information. Before running the app, please create `.env` file. See [.env.example](.env.example) for more details. 

If you want to run the app locally, you should export the environment variables, either with command: 

```sh
export $(cat .env | xargs)
```

or by putting export variables in bashrc.

# Delivery Service 

Delivery service is a small service for receiving deliveries - a delivery tells us
what product we will receive, how many of them, and who it comes from. For example, every
day we expect to receive thousands of bananas from our banana supplier!
When a delivery arrives at one of our warehouses we need to make sure that we have marked it as received (so
that we can pay for it, and put it into our inventory). Deliveries are how we get the products into
our warehouses so that we can sell them to you later! This service will help us with this process.

## Installation
I used docker to make DB service easily available in dev.
So in case you don't have docker please follow this [link](https://docs.docker.com/get-docker/) to install it.
Then run:

```docker
docker-compose up -d
```

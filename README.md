## Spring Kafka

This module contains articles about Spring with Kafka

### Relevant articles

- [Apache Kafka Quickstart](http://kafka.apache.org/quickstart)
- [Intro to Apache Kafka with Spring](https://www.baeldung.com/spring-kafka)

### Intro

This is a simple Spring Boot app to demonstrate sending and receiving of messages in Kafka using **spring-kafka**.

As Kafka topics are not created automatically by default, this application requires that you create the following topics manually.

> Create Kafka Topics 

```shell
$ bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic lhfei
```

```shell
$ bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 5 --topic t_partitioned
```

```shell
$ bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic t_filtered
```

```shell
$ bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic greeting
```

When the application runs successfully, following output is logged on to console (along with spring logs):

Message received from the `lhfei` topic by the basic listeners with groups `foo` and `bar`

```ini
Received Message in group 'foo': Hello, World!
Received Message in group 'bar': Hello, World!
```

Message received from the `lhfei` topic, with the partition info

```ini
Received Message: Hello, World! from partition: 0
```

Message received from the `t_partitioned` topic, only from specific partitions

```ini
Received Message: Hello To Partioned Topic! from partition: 0
Received Message: Hello To Partioned Topic! from partition: 3
```

Message received from the `t_filtered` topic after filtering

```ini
Received Message in filtered listener: Hello Baeldung!
```

Message (Serialized Java Object) received from the `greeting` topic

```ini
Received greeting message: Greetings, World!!
```


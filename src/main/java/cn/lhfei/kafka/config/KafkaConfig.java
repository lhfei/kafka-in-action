/*
 * Copyright 2010-2011 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package cn.lhfei.kafka.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

/**
 * @version 0.1
 *
 * @author Hefei Li
 *
 * @created Apr 01, 2021
 */
@EnableKafka
@Configuration
@ConfigurationProperties(prefix = "kafka")
public class KafkaConfig {
  @Autowired
  private KafkaProperties kafkaProps;

  public ConsumerFactory<String, String> consumerFactory(String groupId) {
    Map<String, Object> props = new HashMap<>();
    props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
        kafkaProps.getConsumer().getBootstrapServers());
    props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
    props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,
        kafkaProps.getConsumer().getAutoOffsetReset());
    props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
    props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
    return new DefaultKafkaConsumerFactory<>(props);
  }

  public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory(
      String groupId) {
    ConcurrentKafkaListenerContainerFactory<String, String> factory =
        new ConcurrentKafkaListenerContainerFactory<>();
    factory.setConsumerFactory(consumerFactory(groupId));
    return factory;
  }

  @Bean
  public ConcurrentKafkaListenerContainerFactory<String, String> fooKafkaListenerContainerFactory() {
    return kafkaListenerContainerFactory("foo");
  }

  @Bean
  public ConcurrentKafkaListenerContainerFactory<String, String> barKafkaListenerContainerFactory() {
    return kafkaListenerContainerFactory("bar");
  }

  @Bean
  public ConcurrentKafkaListenerContainerFactory<String, String> headersKafkaListenerContainerFactory() {
    return kafkaListenerContainerFactory("headers");
  }

  @Bean
  public ConcurrentKafkaListenerContainerFactory<String, String> partitionsKafkaListenerContainerFactory() {
    return kafkaListenerContainerFactory("partitions");
  }

  @Bean
  public ConcurrentKafkaListenerContainerFactory<String, String> filterKafkaListenerContainerFactory() {
    ConcurrentKafkaListenerContainerFactory<String, String> factory =
        kafkaListenerContainerFactory("filter");
    factory.setRecordFilterStrategy(record -> record.value().contains("World"));
    return factory;
  }
  
  
  
  public Map<String, String> getTopics() {
    return topics;
  }

  public void setTopics(Map<String, String> topics) {
    this.topics = topics;
  }



  private Map<String, String> topics = new HashMap<>();
  
  
}

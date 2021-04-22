/*
 * Copyright 2010-2011 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package cn.lhfei.kafka.resources;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.lhfei.kafka.config.KafkaConfig;

/**
 * @version 0.1
 *
 * @author Hefei Li
 *
 * @created Apr 02, 2021
 */
@RestController
public class KafkaTopic {

  @GetMapping("/topics")
  public Map<String, String> getTopics(){
    return kafkaConfig.getTopics();
  }
  
  @Autowired
  private KafkaConfig kafkaConfig;
}

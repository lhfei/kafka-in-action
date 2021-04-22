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

package cn.lhfei.kafka;

import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.function.Consumer;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.ListTopicsOptions;
import org.apache.kafka.clients.admin.ListTopicsResult;

/**
 * @version 0.1
 *
 * @author Hefei Li
 *
 * @created Apr 02, 2021
 */
public class KafkaAdminTools {

  public static void main(String[] args) throws InterruptedException, ExecutionException {
    Properties properties = new Properties();
    properties.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, "11.91.142.3:9092,11.91.142.4:9092,11.91.142.5:9092");

    AdminClient adminClient = AdminClient.create(properties);

    ListTopicsOptions listTopicsOptions = new ListTopicsOptions();
    listTopicsOptions.listInternal(true);

    ListTopicsResult rs = adminClient.listTopics();
    rs.names().get().forEach(new Consumer<String>() {

        @Override
        public void accept(String t) {
            if(!t.startsWith("_")) {
                System.out.format("Topic : %s \n", t);
            }
        }
        
    });
}
}

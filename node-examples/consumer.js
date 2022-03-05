const { Kafka } = require('kafkajs')

const kafka = new Kafka({
  clientId: 'node-app',
  brokers: ['localhost:9101', 'localhost:9102', 'localhost:9103']
});

const consumer = kafka.consumer({ groupId: 'test_consumer' });

(async function () {
  await consumer.connect();
  await consumer.subscribe({ topic: 'test', fromBeginning: true });

  await consumer.run({
    eachMessage: async ({ topic, partition, message }) => {
      console.log(message.value.toString());
    }
  });
})().catch(console.error);
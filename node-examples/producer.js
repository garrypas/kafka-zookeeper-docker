const { Kafka } = require("kafkajs");
const { resolve } = require("path");
const readline = require("readline");

const kafka = new Kafka({
  clientId: "node-app",
  brokers: ["localhost:9101", "localhost:9102", "localhost:9103"],
});

const producer = kafka.producer({ groupId: "test_consumer" });

(async function () {
  await producer.connect();
  const rl = readline.createInterface({ input: process.stdin });

  while (true) {
    console.log("Enter a message to send to Kafka");
    const value = await new Promise(resolve => rl.question("enter", resolve));
    await producer.send({
      topic: "test",
      messages: [{ value }]
    });
  }
})().catch(console.error);

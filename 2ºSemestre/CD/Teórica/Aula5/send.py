import pika
import sys

connection = pika.BlockingConnection(pika.ConnectionParameters(host='localhost'))
channel = connection.channel()

#create anonymouse queue
result = channel.queue_declare(exclusive=True)
callback_queue = result.method.queue

def callback(ch, method, properties, body):
    if method.routing_key == callback_queue:
        print("Got my order {}".format(body))

channel.basic_consume(callback,
                      queue=callback_queue,
                      no_ack=True)


channel.basic_publish(exchange='',
                      routing_key='orders',
                      properties=pika.BasicProperties(reply_to = callback_queue,
                                                      correlation_id = 'abc123456789'),
                      body='Pizza')
print("Order placed")


channel.start_consuming()

connection.close()

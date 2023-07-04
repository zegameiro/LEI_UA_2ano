import pika

connection = pika.BlockingConnection(pika.ConnectionParameters(host='localhost'))
channel = connection.channel()

result = channel.queue_declare(queue='orders', exclusive=True)

print(' [*] Waiting for orders. To exit press CTRL+C')

def callback(ch, method, properties, body):
    print(" [x] %r:%r" % (method.routing_key, body))

    channel.basic_publish(exchange='',
                          routing_key=properties.reply_to,
                          properties=pika.BasicProperties(correlation_id = properties.correlation_id),
                          body=body)

channel.basic_consume(callback,
                      queue='orders')

channel.start_consuming()

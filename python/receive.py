#!/usr/bin/env python
import pika, sys, os
import app
import json

def main():
    connection = pika.BlockingConnection(pika.ConnectionParameters(host='localhost'))
    channel = connection.channel()

    channel.queue_declare(queue='hello')

    # def callback(ch, method, properties, body):
    #     print(" [x] Received %r" % body.decode())
        
    #     # Uso la clase app hacer consultas a la base de datos
    #     app.insertar(body)
    app2=app.App()
    def callback(ch, method, properties, body):
        print(" [x] Received %r" % json.loads(body))
        app2.collectionBD(json.loads(body))
    channel.basic_consume(queue='hello', on_message_callback=callback, auto_ack=True)

    print(' [*] Waiting for messages. To exit press CTRL+C')
    channel.start_consuming()

if __name__ == '__main__':
    try:
        main()
    except KeyboardInterrupt:
        print('Interrupted')
        try:
            sys.exit(0)
        except SystemExit:
            os._exit(0)
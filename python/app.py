from pymongo import MongoClient
import json

# Direccion de la base de datos
MONGO_URI='mongodb+srv://jose:12345@cluster0.kklra.mongodb.net/myFirstDatabase?retryWrites=true&w=majority'

client=MongoClient(MONGO_URI)

db=client['test']
collection=db['products']

#Insertar un elemento en la base de datos
def insertar(body):
    lista=[]
    print(body)

    #Se convierte a Stringy se divide
    lista=(body.decode("utf-8") ).split(sep='-')
    collection.insert_one({"name":lista[0],"price":lista[1]})
    print('Se ha insertado ',lista[0])
    # for i in lista:
    #     print('elemento: ',i)

#Insertar un elemento en la base de datos
def insertar2(body):
    print(type(body))
    print(body)

    #Se convierte a Stringy se divide
    # lista=(body.decode("utf-8") ).split(sep='-')
    collection.insert(body)
    print('Se ha insertado ')
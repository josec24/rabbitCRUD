from pymongo import MongoClient
import json

# Direccion de la base de datos
MONGO_URI='mongodb+srv://jose:12345@cluster0.kklra.mongodb.net/myFirstDatabase?retryWrites=true&w=majority'

client=MongoClient(MONGO_URI)

db=client['test']
collection=db['products']

#Insertar un elemento en la base de datos
def insertar(body):
    print(type(body))
    print(body)
    body[0].pop('collection')
    #Se convierte a Stringy se divide
    collection.insert(body)
    print('Se ha insertado ')

def leer(body):
    id=(body[0])['bookID']
    print('ID buscado: ',id)
    if collection.find_one({'bookID':id}) :
        result=collection.find_one({'bookID':id})
        print(result)
        print('Se ha encontrado')
    else:
        print('No se ha encontrado')


def actualizar(body):
    # body={'bookID':1,'author':'joses','bookDescription':'ola k ase'}
    id=(body[0])['bookID']
    print(body[0])
    body[0].pop('bookID')
    body[0].pop('collection')
    print(body[0])
    print('ID buscado: ',id)
    if collection.find_one({'bookID':id}) :
        collection.update_one({'bookID':id},{"$set":body[0]})
        print('Se ha actualizado')
    else:
        print('No se ha actualizado')


def eliminar(body):
    id=(body[0])['bookID']
    print('ID buscado: ',id)
    if collection.find_one({'bookID':id}) :
        collection.delete_one({'bookID':id})
        print('Se ha eliminado')
    else:
        print('No se ha eliminado')
from pymongo import MongoClient
import json

# Direccion de la base de datos
MONGO_URI='mongodb+srv://jose:12345@cluster0.kklra.mongodb.net/myFirstDatabase?retryWrites=true&w=majority'

client=MongoClient(MONGO_URI)



class App:
    def __init__(self):
        self.n = 0  # Atributo de instancia

    # Obtiene todo el mensaje, se identifica la accion y la base de datos a usar
    def collectionBD(self,body):
        collec=(body[0])['collection']
        opt=(body[0])['option']
        db=client['test']
        collection=db[collec]
        body[0].pop('collection')
        body[0].pop('option')
        
        if collec=='books':
            operaciones = {
            1: self.CreateBook,
            2: self.ReadBook,
            3: self.UpdateBook,
            4: self.DeleteBook
            }
            # self.insertar(body,collection)
            operaciones[opt](body,collection)
        elif collec=='authors':
            operaciones = {
            1: self.CreateAuthor,
            2: self.ReadAuthor,
            3: self.UpdateAuthor,
            4: self.DeleteAuthor
            }
            # self.insertar(body,collection)
            operaciones[opt](body,collection)

    #Crear una colección libros
    def CreateBook(self,body,collection):
        #Se convierte a String y se divide
        collection.insert(body)
        print('Se ha insertado ')

    def ReadBook(self,body,collection):
        id=(body[0])['bookID']
        print('ID buscado: ',id)
        if collection.find_one({'bookID':id}) :
            result=collection.find_one({'bookID':id})
            print(result)
            print('Se ha encontrado')
        else:
            print('No se ha encontrado')


    def UpdateBook(self,body,collection):
        id=(body[0])['bookID']
        body[0].pop('bookID')
        print('ID buscado: ',id)
        if collection.find_one({'bookID':id}) :
            collection.update_one({'bookID':id},{"$set":body[0]})
            print('Se ha actualizado')
        else:
            print('No se ha actualizado')


    def DeleteBook(self,body,collection):
        id=(body[0])['bookID']
        print('ID buscado: ',id)
        if collection.find_one({'bookID':id}) :
            collection.delete_one({'bookID':id})
            print('Se ha eliminado')
        else:
            print('No se ha eliminado')


    #Crear una colección author
    def CreateAuthor(self,body,collection):
        #Se convierte a String y se divide
        collection.insert(body)
        print('Se ha insertado ')

    def ReadAuthor(self,body,collection):
        id=(body[0])['authorID']
        print('ID buscado: ',id)
        if collection.find_one({'authorID':id}) :
            result=collection.find_one({'authorID':id})
            print(result)
            print('Se ha encontrado')
        else:
            print('No se ha encontrado')


    def UpdateAuthor(self,body,collection):
        id=(body[0])['authorID']
        body[0].pop('authorID')
        print('ID buscado: ',id)
        if collection.find_one({'authorID':id}) :
            collection.update_one({'authorID':id},{"$set":body[0]})
            print('Se ha actualizado')
        else:
            print('No se ha actualizado')


    def DeleteAuthor(self,body,collection):
        id=(body[0])['authorID']
        print('ID buscado: ',id)
        if collection.find_one({'authorID':id}) :
            collection.delete_one({'authorID':id})
            print('Se ha eliminado')
        else:
            print('No se ha eliminado')


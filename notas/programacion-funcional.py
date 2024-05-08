
def componer_saludo_formal(nombre):
        return "Estimado " + nombre


def componer_saludo_informal(nombre):
        return "Hola " + nombre
    

def imprimir_saludo(funcion_generadora_de_saludos, nombre):
    print(funcion_generadora_de_saludos(nombre))


imprimir_saludo(componer_saludo_formal, "Felipe")
imprimir_saludo(componer_saludo_informal, "Felipe")